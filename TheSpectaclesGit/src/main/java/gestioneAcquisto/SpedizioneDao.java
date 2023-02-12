package gestioneAcquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.sql.DataSource;

import util.ConnectionPool;
import util.Model;

	/**
	 * Questa classe e' un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l'oggetto Spedizione.
	 * @author Mario Ranieri 
	 * @author Roberto Piscopo
	 */
	 public class SpedizioneDao implements Model<SpedizioneBean, DataSource> {
	 	private static final String TABLE_NAME="spedizione";
	 	private DataSource ds;

	 	/**
		 * @param obj connessione al database
		 * @return
		 */
		public void setDB(DataSource obj) {
			this.ds=obj;
		}

		/**
	 	 * @param keys chiavi da usare per rimuovere delle istanze nel DB
	 	 * @precondition code!=null
	 	 * @postcondition spedizione=db.spedizione->(select(s|s.code=code)
	 	 * @return bean nuova spedizione (SpedizioneBean)
	 	 * @throws SQLException
	 	 */
		public SpedizioneBean doRetrieveByKey(ArrayList<String> keys) throws SQLException {
			SpedizioneBean bean=new SpedizioneBean();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
	 		String selectSQL="SELECT * FROM " + SpedizioneDao.TABLE_NAME + " WHERE CODE = ?";

	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(selectSQL);
				prep.setString(1, keys.get(0));
				rs=prep.executeQuery();

	 			while(rs.next()) {
	            	bean.setIdShipment(rs.getInt("idShipment"));
	            	bean.setDateShipment(rs.getDate("dateShipment"));
	            	bean.setIdOrder(rs.getInt("idOrder"));
	            	bean.setShipmentType(rs.getInt("shipmentType"));
	 			}
	 		} finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
	 		}
	 		return bean;
		}
		
		/**
	 	 * @param order stringa ordine da controllare
	 	 * @return bean nuova spedizione (SpedizioneBean)
	 	 * @throws SQLException
	 	 */
		public Collection<SpedizioneBean> doRetrieveAll(String order) throws SQLException {
			Collection<SpedizioneBean> spedizione=new ArrayList<SpedizioneBean>();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			String sql="SELECT idShipmentType FROM " + SpedizioneDao.TABLE_NAME+" ORDER BY ?";
			/*if(order!=null && !order.equals("")) {
				sql+=" ORDER BY " + order;
			}*/
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1, order);
				rs=prep.executeQuery();
			
			while(rs.next()) {
				SpedizioneBean bean=new SpedizioneBean();
				bean.setIdShipment(rs.getInt("idShipment"));
				bean.setDateShipment(rs.getDate("DateShipment"));
				bean.setIdOrder(rs.getInt("idOrder"));
				bean.setShipmentType(rs.getInt("shipmentType"));
				spedizione.add(bean);
			}
		 } finally {
				rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
			return spedizione;
		}
		
		/**
	 	 * @param spedizione spedizione da aggiornare nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doUpdate(SpedizioneBean spedizione) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String sql="UPDATE spedizione SET idShipment=?, dateShipment=?, idOrder=?, shipmentType=?";

			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setInt(1, spedizione.getIdShipment());
				prep.setDate(2, spedizione.getDateShipment(), Calendar.getInstance());
				prep.setInt(3, spedizione.getIdOrder());
				prep.setInt(4, spedizione.getShipmentType());
				prep.executeUpdate();
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}

		/**
	 	 * @param bean spedizione da rimuovere dal DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doDelete(SpedizioneBean bean) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String deleteSQL="DELETE FROM " + SpedizioneDao.TABLE_NAME + " WHERE CODE = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(deleteSQL);
				prep.setInt(1, bean.getIdShipment());
				prep.executeUpdate();
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}

		/**
	 	 * @param spedizione spedizione da salvare nel DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doSave(SpedizioneBean spedizione) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String insertSQL="INSERT INTO " + SpedizioneDao.TABLE_NAME + " (idShipment, dateShipment, idOrder, shipmentType) VALUES (?, ?, ?, ?)";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(insertSQL);
				prep.setInt(1, spedizione.getIdShipment());
				prep.setDate(2, spedizione.getDateShipment(), Calendar.getInstance());
				prep.setInt(3, spedizione.getIdOrder());
				prep.setInt(4, spedizione.getShipmentType());
				prep.executeUpdate();
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}
	 }