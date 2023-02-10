package gestioneOcchiali;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

import util.ConnectionPool;

	/**
	 * Questa classe è un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l'oggetto Occhiale.
 	 * @author Mario Ranieri 
 	 * @author Roberto Piscopo
 	 */
	 public class OcchialeDao {
	 	private static final String TABLE_NAME = "occhiale";
	 	private DataSource ds;

	 	/**
		 * @param obj connessione al database
		 * @return
		 */
		public void setDB(DataSource obj) {
			this.ds=obj;
		}

		/**
	 	 * @param keys chiavi da usare per rimuovere delle istanze nel db
	 	 * @return bean nuovo occhiale(OcchialiBean)
	 	 * @throws SQLException
	 	 */
		public Collection<OcchialeBean> doRetrieveByKey(ArrayList<String> keys) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			Collection<OcchialeBean> occhiali=new ArrayList<OcchialeBean>();
	 		String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE categoria = ?";
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1,keys.get(0));
				rs=prep.executeQuery();

	 			while (rs.next()) {
	 				OcchialeBean bean=new OcchialeBean();
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo"));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setCategory(rs.getString("categoria"));
	            	bean.setImage(rs.getString("img"));
	            	bean.setImage2(rs.getString("img2"));
	            	bean.setDescription(rs.getString("descrizione"));
	            	
	            	occhiali.add(bean);
	 			}
	 		} 
		    catch(Exception e){
			  e.printStackTrace();
			} finally {
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}
				if(con!=null) {
					ConnectionPool.rilasciaConnessione(con);
				}	
	 		}
	 		return occhiali;
		}
		
		/**
	 	 * @param keys chiavi da usare per rimuovere delle istanze nel db
	 	 * @return bean nuovo occhiale(OcchialiBean)
	 	 * @throws SQLException
	 	 */
		public Collection<OcchialeBean> doRetrieveBySex(ArrayList<String> keys) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			Collection<OcchialeBean> occhiali=new ArrayList<OcchialeBean>();
	 		String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE tipo = ?";

	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1,keys.get(1));
				rs=prep.executeQuery();

	 			while(rs.next()) {
	 				OcchialeBean bean=new OcchialeBean();
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo"));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setCategory(rs.getString("categoria"));
	            	bean.setImage(rs.getString("img"));
	            	bean.setImage2(rs.getString("img2"));
	            	bean.setDescription(rs.getString("descrizione"));
	            	
	            	occhiali.add(bean);
	 			}
	 		} 
		    catch(Exception e){
			  e.printStackTrace();
			} finally {
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}
				if(con!=null) {
					ConnectionPool.rilasciaConnessione(con);
				}
	 		}
	 		return occhiali;
		}
		
		/**
	 	 * @param id stringa id da controllare
	 	 * @precondition id!=NULL
	 	 * @postcondition occhiale=db.occhiale->(select(o|o.idOcchiale=id))
	 	 * @return bean nuovo occhiale(OcchialeBean)
	 	 * @throws SQLException
	 	 */
		public OcchialeBean doRetrieveOcchiale(String id) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			OcchialeBean bean=new OcchialeBean();
	 		String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE idOcchiale = ?";
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1,id);
				rs=prep.executeQuery();
				System.out.println("\nQuery doRetrieveOcchiale: "+prep);
	 			while(rs.next()) {
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo"));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setCategory(rs.getString("categoria"));
	            	bean.setImage(rs.getString("img"));
	            	bean.setImage2(rs.getString("img2"));
	            	bean.setDescription(rs.getString("descrizione"));
	 			}
	 		} 
		    catch(Exception e){
			  e.printStackTrace();
			} finally {
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}
				if(con!=null) {
					ConnectionPool.rilasciaConnessione(con);
				}
	 		}
	 		System.out.println("Metodo OcchialeDao  doRetrieveOcchiale: \n"+bean);
	 		return bean;
		}
		
		/**
	 	 * @param brand stringa brand da controllare
	 	 * @precondition brand!=NULL
	 	 * @postcondition occhiali=db.occhiale->(select(o|o.brand=brand))
	 	 * @return bean nuovo occhiale(OcchialeBean)
	 	 * @throws SQLException
	 	 */
		public Collection<OcchialeBean> doRetrieveByBrand(String brand) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			Collection<OcchialeBean> occhiali=new ArrayList<OcchialeBean>();
	 		String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE marca = ?";
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1,brand);
				rs=prep.executeQuery();

	 			while(rs.next()) {
	 				OcchialeBean bean=new OcchialeBean();
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo"));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setCategory(rs.getString("categoria"));
	            	bean.setImage(rs.getString("img"));
	            	bean.setImage2(rs.getString("img2"));
	            	bean.setDescription(rs.getString("descrizione"));
	            	
	            	occhiali.add(bean);
	 			}
	 		} 
		    catch(Exception e){
			  e.printStackTrace();
			} finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
	 		}
	 		return occhiali;
		}
		
		/**
	 	 * @param keys stringhe di chiavi da controllare
	 	 * @return bean nuovo occhiale(OcchialeBean)
	 	 * @throws SQLException
	 	 */
		public OcchialeBean singleProduct (ArrayList<String> keys) throws SQLException {
			OcchialeBean bean=new OcchialeBean();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			
	 		String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE idOcchiale = ?";
	 		
	 		System.out.println("Sono nel metodo del dao: "+ keys.get(0));
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1,keys.get(0));
				rs=prep.executeQuery();

	 			while(rs.next()) {
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo"));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setCategory(rs.getString("categoria"));
	            	bean.setImage(rs.getString("img"));   
	            	bean.setImage2(rs.getString("img2"));
	            	bean.setDescription(rs.getString("descrizione"));	            	
	            	System.out.println("\nSono nel while: "+ bean);
	 			}
	 		} 
		    catch(Exception e){
			  e.printStackTrace();
			} finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
	 		}
	 		System.out.println("OCchiale: \n "+ bean+ "\n");
	 		return bean;
		}

		/**
	 	 * @param order stringa ordine da controllare
	 	 * @return bean nuovo occhiale(OcchialeBean)
	 	 * @throws SQLException
	 	 */
		public Collection<OcchialeBean> doRetrieveAll(String order) throws SQLException {
			Collection<OcchialeBean> occhiali=new ArrayList<OcchialeBean>();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			String sql="SELECT * FROM " + OcchialeDao.TABLE_NAME;
			
			if(order!=null && !order.equals("")) {
				sql +=" ORDER BY " + order;
			}
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				rs=prep.executeQuery();
			
			while (rs.next()) {
				OcchialeBean bean=new OcchialeBean();

            	bean.setIdGlasses(rs.getString("idOcchiale"));
            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
            	bean.setBrand(rs.getString("marca"));
            	bean.setPrice(rs.getInt("prezzo"));
            	bean.setAvailability(rs.getInt("disponibilita"));
            	bean.setType(rs.getString("tipo"));
            	bean.setColor(rs.getString("colore"));
            	bean.setCategory(rs.getString("categoria"));
            	bean.setImage(rs.getString("img"));
            	bean.setImage2(rs.getString("img2"));
            	bean.setDescription(rs.getString("descrizione"));

				occhiali.add(bean);
				}
			}
			catch(Exception e){
			  e.printStackTrace();
			}
				finally {
				rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
			return occhiali;
		}
		
		/**
	 	 * @param occhiale occhiale da aggiornare nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doUpdate(OcchialeBean occhiale) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String sql="UPDATE occhiale SET  nomeOcchiale=?, prezzo=?, disponibilita=?, descrizione=? WHERE idOcchiale = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				
				prep.setString(1, occhiale.getNameGlasses());
				prep.setInt(2, occhiale.getPrice());
				prep.setInt(3, occhiale.getAvailability());
				prep.setString(4, occhiale.getDescription());
				prep.setString(5, occhiale.getIdGlasses());
				
				System.out.println("\nquery : "+prep);
				
				prep.executeUpdate();

			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		
		/**
	 	 * @param occhiale occhiale da diminuire quantità disponibile nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public int decreaseAvailability(OcchialeBean occhiale) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String sql="UPDATE occhiale SET disponibilita=? WHERE idOcchiale=?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setInt(1, occhiale.getAvailability()-occhiale.getQuantity());
				prep.setString(2,occhiale.getIdGlasses());
				prep.executeUpdate();
				
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
			
			return occhiale.getAvailability()-occhiale.getQuantity();
		}

		/**
	 	 * @param id stringa id da rimuovere nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doDelete(String id) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String deleteSQL="DELETE FROM " + OcchialeDao.TABLE_NAME + " WHERE idOcchiale = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(deleteSQL);

				prep.setString(1, id);
				prep.executeUpdate();
				//con.commit();

			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}

		/**
	 	 * @param occhiale occhiale da salvare nel db
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doSave(OcchialeBean occhiale) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
		
			String insertSQL="INSERT INTO " + OcchialeDao.TABLE_NAME + " (idOcchiale, nomeOcchiale, marca, prezzo,disponibilita,tipo,colore,categoria,img,img2,descrizione) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?)";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(insertSQL);

				prep.setString(1, occhiale.getIdGlasses());
				prep.setString(2, occhiale.getNameGlasses());
				prep.setString(3, occhiale.getBrand());
				prep.setInt(4, occhiale.getPrice());
				prep.setInt(5, occhiale.getAvailability() );
				prep.setString(6, occhiale.getType());
				prep.setString(7, occhiale.getColor());
				prep.setString(8, occhiale.getCategory());
				prep.setString(9, occhiale.getImage());
				prep.setString(10, occhiale.getImage2());
				prep.setString(11, occhiale.getDescription());
				
				System.out.println("doSve occhiale: "+prep);
				prep.executeUpdate();
				
				
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);
			}
		}
	 }