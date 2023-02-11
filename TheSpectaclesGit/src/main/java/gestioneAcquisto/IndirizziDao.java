package gestioneAcquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

import util.ConnectionPool;

	/**
	 * Questa classe è un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l'oggetto Indirizzi.
	 * @author Mario Ranieri 
	 * @author Roberto Piscopo
	 */
	 public class IndirizziDao {
	 	private static final String TABLE_NAME="indirizzi";
	 	private DataSource ds;

	 	/**
		 * @param obj connessione al database
		 * @return
		 */
		public void setDB(DataSource obj) {
			this.ds=obj;
		}

		/**
	 	 * @param email email da usare per rimuovere delle istanze nel db
	 	 * @precondition email!=NULL
	 	 * @postcondition indirizzo=db.indirizzi->(select(i|i.email=email AND i.attivo=1)
	 	 * @return bean nuovo indirizzo(IndirizziBean)
	 	 * @throws SQLException
	 	 */
		public IndirizziBean doRetrieveActive(String email) throws SQLException {
			IndirizziBean bean=new IndirizziBean();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
	
	 		String selectSQL="SELECT * FROM " + IndirizziDao.TABLE_NAME + " WHERE email = ? and attivo=1";

	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(selectSQL);
				prep.setString(1, email);
				rs=prep.executeQuery();

	 			while(rs.next()) {
	            	bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
	            	bean.setAddress(rs.getString("indirizzo"));
	            	bean.setStatus(rs.getInt("attivo"));
	            	bean.setCity(rs.getString("citta"));
	            	bean.setProvince(rs.getString("provincia"));
	            	bean.setCap(rs.getInt("cap"));
	            	bean.setEmail(rs.getString("email"));
	            	bean.setTelefono(rs.getString("telefono"));
	            	bean.setName(rs.getString("nome"));
	            	bean.setName(rs.getString("cognome"));
	 			}
	 		} finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
	 		}
	 		return bean;
		}
		
		/**
	 	 * @param email stringa email da usare per cercare delle istanze nel db
	 	 * @param via stringa via da usare per cercare delle istanze nel db
	 	 * @return bean nuovo indirizzo(IndirizziBean)
	 	 * @throws SQLException
	 	 */
		public IndirizziBean search(String email, String via) throws SQLException {
			IndirizziBean bean=new IndirizziBean();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
	
	 		String selectSQL="SELECT * FROM " + IndirizziDao.TABLE_NAME + " WHERE email = ? and indirizzo= ?";

	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(selectSQL);
				prep.setString(1, email);
				prep.setString(2, via);
				rs=prep.executeQuery();

	 			while(rs.next()) {
	            	bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
	            	bean.setAddress(rs.getString("indirizzo"));
	            	bean.setStatus(rs.getInt("attivo"));
	            	bean.setCity(rs.getString("citta"));
	            	bean.setProvince(rs.getString("provincia"));
	            	bean.setCap(rs.getInt("cap"));
	            	bean.setEmail(rs.getString("email"));
	            	bean.setTelefono(rs.getString("telefono"));
	            	bean.setName(rs.getString("nome"));
	            	bean.setSurname(rs.getString("cognome"));
	 			}
	 		} finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
	 		}
	 		return bean;
		}
		
		/**
	 	 * @param email stringa email da usare per rimuovere delle istanze nel db
	 	 * @return bean nuovo indirizzo(IndirizziBean)
	 	 * @throws SQLException
	 	 */
		public Collection<IndirizziBean> doRetrieveAllAddress(String email) throws SQLException {
			Collection<IndirizziBean> indirizzi=new ArrayList<IndirizziBean>();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			String sql="SELECT * FROM " + IndirizziDao.TABLE_NAME+"  WHERE email = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1, email);
				rs=prep.executeQuery();
			
			while(rs.next()) {
				IndirizziBean bean=new IndirizziBean();
				bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
            	bean.setAddress(rs.getString("indirizzo"));
            	bean.setStatus(rs.getInt("attivo"));
            	bean.setCity(rs.getString("citta"));
            	bean.setProvince(rs.getString("provincia"));
            	bean.setCap(rs.getInt("cap"));
            	bean.setEmail(rs.getString("email"));
            	bean.setTelefono(rs.getString("telefono"));
            	bean.setName(rs.getString("nome"));
            	bean.setSurname(rs.getString("cognome"));
				
				indirizzi.add(bean);
		}
		 } finally {
				rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
			return indirizzi;
		}
	
		/**
	 	 * @param order stringa ordine da usare per rimuovere delle istanze nel db
	 	 * @return bean nuovo indirizzo(IndirizziBean)
	 	 * @throws SQLException
	 	 */
		public Collection<IndirizziBean> doRetrieveAll(String order) throws SQLException {
			Collection<IndirizziBean> indirizzi=new ArrayList<IndirizziBean>();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			String sql="SELECT idIndirizzo FROM " + IndirizziDao.TABLE_NAME+" ORDER BY ?";
			/*if(order !=null && !order.equals("")) {
				sql += " ORDER BY " + order;
			}*/
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1, order);
				rs=prep.executeQuery();
			
			while(rs.next()) {
				IndirizziBean bean=new IndirizziBean();
				bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
				
				indirizzi.add(bean);
				}
		 } finally {
				rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
			return indirizzi;
		}
		
		/**
	 	 * @param indirizzo indirizzo da aggiornare nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doUpdate(IndirizziBean indirizzo) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String sql="UPDATE indirizzo SET idIndirizzo=?, indirizzo=?, attivo=?, citta=?, provincia=?, cap=?, email=?, telefono=?, nome=?, cognome=?";

			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);

				prep.setInt(1, indirizzo.getIdIndirizzo());
				prep.setString(2, indirizzo.getAddress());
				prep.setInt(3, indirizzo.getStatus());
				prep.setString(4, indirizzo.getCity());
				prep.setString(5, indirizzo.getProvince());
				prep.setInt(6, indirizzo.getCap());
				prep.setString(7, indirizzo.getEmail());
				prep.setString(8, indirizzo.getTelefono());
				prep.setString(9, indirizzo.getName());
				prep.setString(10, indirizzo.getSurname());
			
				prep.executeUpdate();

			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}

		/**
	 	 * @param indirizzo indirizzo da rimuovere nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doDelete(IndirizziBean indirizzo) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String deleteSQL="DELETE FROM " + IndirizziDao.TABLE_NAME + " WHERE idIndirizzo = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(deleteSQL);

				prep.setInt(1, indirizzo.getIdIndirizzo());
				prep.executeUpdate();

			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}

		/**
	 	 * @param indirizzo indirizzo da salvare nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doSave(IndirizziBean indirizzo) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
		
			String insertSQL="INSERT INTO " + IndirizziDao.TABLE_NAME + " (indirizzo, attivo, citta, provincia, cap, email, telefono, nome, cognome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(insertSQL);
				
				
				
				
				prep.setString(1, indirizzo.getAddress());
				prep.setInt(2, indirizzo.getStatus());
				prep.setString(3, indirizzo.getCity());
				prep.setString(4, indirizzo.getProvince());
				prep.setInt(5, indirizzo.getCap());
				prep.setString(6, indirizzo.getEmail());
				prep.setString(7, indirizzo.getTelefono());
				prep.setString(8,indirizzo.getName());
				prep.setString(9,indirizzo.getSurname());
				
				System.out.println("Do Save "+prep);
				prep.executeUpdate();

			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		
		public int getLastIndexAdded() throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			int index=0;
			String query="SELECT MAX(idIndirizzo) FROM  " + IndirizziDao.TABLE_NAME;
		 
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(query);
				rs=prep.executeQuery();
				
				while(rs.next()) {
					index=rs.getInt("Max(idIndirizzo)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
		     }
			return index;
		}
	 }