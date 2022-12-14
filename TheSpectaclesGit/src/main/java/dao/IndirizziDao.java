package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import model.*;


	 public class IndirizziDao  {

	 	private static final String TABLE_NAME = "indirizzi";

	 	private DataSource ds;

		public void setDB(DataSource obj) {
			this.ds=obj;
		}

		public IndirizziBean doRetrieveActive(String email) throws SQLException {
			IndirizziBean bean = new IndirizziBean();
			Connection con = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
	
	 		String selectSQL = "SELECT * FROM " + IndirizziDao.TABLE_NAME + " WHERE email = ? and attivo=1";

	 		try {
	 			con = ds.getConnection();
				prep = con.prepareStatement(selectSQL);
				prep.setString(1, email);
				rs = prep.executeQuery();

	 			while (rs.next()) {
	 				
	            	bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
	            	bean.setAddress(rs.getString("indirizzo"));
	            	bean.setStatus(rs.getInt("attivo"));
	            	bean.setCity(rs.getString("citta"));
	            	bean.setProvince(rs.getString("provincia"));
	            	bean.setCap(rs.getInt("cap"));
	            	bean.setEmail(rs.getString("email"));
	            	bean.setTelefono(rs.getString("telefono"));
	 			}

	 		} finally {
	 			rs.close();
				prep.close();
				con.close();
	 		}
	 		return bean;
		}
		

		public IndirizziBean search(String email, String via) throws SQLException {
			IndirizziBean bean = new IndirizziBean();
			Connection con = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
	
	 		String selectSQL = "SELECT * FROM " + IndirizziDao.TABLE_NAME + " WHERE email = ? and indirizzo= ?";

	 		try {
	 			con = ds.getConnection();
				prep = con.prepareStatement(selectSQL);
				prep.setString(1, email);
				prep.setString(2, via);
				rs = prep.executeQuery();

	 			while (rs.next()) {
	 				
	            	bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
	            	bean.setAddress(rs.getString("indirizzo"));
	            	bean.setStatus(rs.getInt("attivo"));
	            	bean.setCity(rs.getString("citta"));
	            	bean.setProvince(rs.getString("provincia"));
	            	bean.setCap(rs.getInt("cap"));
	            	bean.setEmail(rs.getString("email"));
	            	bean.setTelefono(rs.getString("telefono"));
	 			}

	 		} finally {
	 			rs.close();
				prep.close();
				con.close();
	 		}
	 		return bean;
		}
		public Collection<IndirizziBean> doRetrieveAllAddress(String email) throws SQLException {
			Collection<IndirizziBean> indirizzi = new ArrayList<IndirizziBean>();
			Connection con = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM " + IndirizziDao.TABLE_NAME+"  WHERE email = ?";
			
			try {
				con = ds.getConnection();
				prep = con.prepareStatement(sql);
				prep.setString(1, email);
				rs = prep.executeQuery();
			
			while (rs.next()) {
				IndirizziBean bean = new IndirizziBean();
				bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
            	bean.setAddress(rs.getString("indirizzo"));
            	bean.setStatus(rs.getInt("attivo"));
            	bean.setCity(rs.getString("citta"));
            	bean.setProvince(rs.getString("provincia"));
            	bean.setCap(rs.getInt("cap"));
            	bean.setEmail(rs.getString("email"));
            	bean.setTelefono(rs.getString("telefono"));
				
				indirizzi.add(bean);
		}
		 } finally {
				rs.close();
				prep.close();
				con.close();
			}
			return indirizzi;
		}
	
		public Collection<IndirizziBean> doRetrieveAll(String order) throws SQLException {
			Collection<IndirizziBean> indirizzi = new ArrayList<IndirizziBean>();
			Connection con = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			String sql = "SELECT idIndirizzo FROM " + IndirizziDao.TABLE_NAME+" ORDER BY ?";
			/*if(order !=null && !order.equals("")) {
				sql += " ORDER BY " + order;
			}*/
			try {
				con = ds.getConnection();
				prep = con.prepareStatement(sql);
				prep.setString(1, order);
				rs = prep.executeQuery();
			
			while (rs.next()) {
				IndirizziBean bean = new IndirizziBean();
				bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
				
				indirizzi.add(bean);
		}
		 } finally {
				rs.close();
				prep.close();
				con.close();
			}
			return indirizzi;
		}
		
		public void doUpdate(IndirizziBean indirizzo) throws SQLException {
			Connection con = null;
			PreparedStatement prep = null;
			String sql = "UPDATE indirizzo SET idIndirizzo=?, indirizzo=?, attivo=?, citta=?, provincia=?, cap=?, email=?, telefono=?";

			try {
				con = ds.getConnection();
				prep = con.prepareStatement(sql);

				prep.setInt(1, indirizzo.getIdIndirizzo());
				prep.setString(2, indirizzo.getAddress());
				prep.setInt(3, indirizzo.getStatus());
				prep.setString(4, indirizzo.getCity());
				prep.setString(5, indirizzo.getProvince());
				prep.setInt(6, indirizzo.getCap());
				prep.setString(7, indirizzo.getEmail());
				prep.setString(8, indirizzo.getTelefono());
			
				prep.executeUpdate();

			} finally {
				prep.close();
				con.close();
			}
		}

		public void doDelete(IndirizziBean indirizzo) throws SQLException {
			Connection con = null;
			PreparedStatement prep = null;
			String deleteSQL = "DELETE FROM " + IndirizziDao.TABLE_NAME + " WHERE CODE = ?";
			
			try {
				con = ds.getConnection();
				prep = con.prepareStatement(deleteSQL);

				prep.setInt(1, indirizzo.getIdIndirizzo());

				prep.executeUpdate();

			} finally {
				prep.close();
				con.close();
			}
		}

		public void doSave(IndirizziBean indirizzo) throws SQLException {
			Connection con = null;
			PreparedStatement prep = null;
		
			String insertSQL = "INSERT INTO " + IndirizziDao.TABLE_NAME
					+ " (indirizzo, attivo, citta, provincia, cap, email, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try {
				con = ds.getConnection();
				prep = con.prepareStatement(insertSQL);
				
				System.out.println("Do Save "+prep);
				
				//prep.setInt(1, indirizzo.getIdIndirizzo());
				prep.setString(1, indirizzo.getAddress());
				prep.setInt(2, indirizzo.getStatus());
				prep.setString(3, indirizzo.getCity());
				prep.setString(4, indirizzo.getProvince());
				prep.setInt(5, indirizzo.getCap());
				prep.setString(6, indirizzo.getEmail());
				prep.setString(7, indirizzo.getTelefono());
			
				prep.executeUpdate();

			} finally {
				prep.close();
				con.close();
			}
		}
	 }