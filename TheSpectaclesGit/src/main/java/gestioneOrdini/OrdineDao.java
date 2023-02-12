package gestioneOrdini;

import java.sql.Connection;
import java.util.Date;
import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.sql.DataSource;

import gestioneUtenza.UtenteBean;

import java.text.SimpleDateFormat;

import util.ConnectionPool;

	/**
	 * Questa classe e' un oggetto manager che si interfaccia con il database. Gestisce le query riguardanti l'oggetto Ordine.
	 * @author Mario Ranieri 
	 * @author Roberto Piscopo
	 */
	 public class OrdineDao {
	 	private static final String TABLE_NAME="ordine";
	 	private DataSource ds;

	 	/**
		 * @param obj connessione al database
		 * @return
		 */
		public void setDB(DataSource obj) {
			this.ds=obj;
			System.out.println("DataSource : "+ds+"\n");
		}
		
		/**
	 	 * @param ordine ordine da salvare nel DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doSave(OrdineBean ordine) throws SQLException {
			OrdineBean bean=new OrdineBean();
			Connection con=null;
			PreparedStatement prep=null;
			
			String query="INSERT INTO " + TABLE_NAME + " (idOrdine, email, data, stato)" + " VALUES (?,?,CURRENT_TIMESTAMP(),?)";

			try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(query);
				prep.setString(1, ordine.getIdOrder().toString());
				prep.setString(2, ordine.getEmail());
				prep.setString(3, ordine.getStato());
				prep.executeUpdate();		
	 		} finally {
				try {
					if(prep!=null)
						prep.close();
				} finally {
					if(con!=null)
						ConnectionPool.rilasciaConnessione(con);;
				}
			}
		}
		
		/**
	 	 * @param idOrdine stringaId da usare per rimuovere delle istanze nel DB
	 	 * @return bean nuovo ordine (OrdineBean)
	 	 * @throws SQLException
	 	 */
		public OrdineBean doRetrieveByKey(String idOrdine) throws SQLException {
			OrdineBean bean=new OrdineBean();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
	
	 		String selectSQL="SELECT * FROM " + OrdineDao.TABLE_NAME + " WHERE idOrdine = ?";
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(selectSQL);
				prep.setString(1, idOrdine);
				rs=prep.executeQuery();
				//System.out.println("DoRetrieveByKey OrdineDao: "+prep);
	 			while(rs.next()) {
	 				bean.setIdOrder(UUID.fromString(rs.getString(1)));
	 				bean.setDate(new Date(rs.getTimestamp(2).getTime()));
	 				bean.setEmail(rs.getString(3));
	 				bean.setStato(rs.getString(4));
	 				//System.out.println("DoRetrieveByKey OrdineDao: "+bean);
	 			}
	 		} catch(Exception e){
	 			e.printStackTrace();
	 		}finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);;
	 		}
	 		return bean;
		}
		
		/**
	 	 * @param email email da usare per rimuovere un utente dal DB
	 	 * @precondition order!=null
	 	 * @postcondition ordini=db.ordine->(select(o|o.email=user)
	 	 * @return ordini nuova lista di ordini 
	 	 * @throws SQLException
	 	 */
		public ArrayList<OrdineBean> doRetrieveByUser(String email) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			ArrayList<OrdineBean> ordini=new ArrayList<OrdineBean>();
	 		String selectSQL="SELECT idOrdine,data,stato,SUM(prezzo_reale) AS totale FROM ecommerce.occhiale_ordine INNER JOIN ordine on occhiale_ordine.id_ordine=ordine.idOrdine  where email=? group by idOrdine";
	 		
	 		try {
	 			con=ConnectionPool.getConnection();
				prep=con.prepareStatement(selectSQL);
				prep.setString(1, email);
				rs=prep.executeQuery();
				//System.out.println("DoRetrieveByKey OrdineDao: "+prep);
	 			while(rs.next()) {
	 				OrdineBean bean=new OrdineBean();
	 				bean.setIdOrder(UUID.fromString(rs.getString("idOrdine")));
	 				bean.setDate(new Date(rs.getTimestamp("data").getTime()));
	 				bean.setStato(rs.getString("stato"));
	 				bean.setTot(rs.getInt("totale"));
	 				//System.out.println("DoRetrieveByKey OrdineDao: "+bean);
	 				ordini.add(bean);
	 			}
	 		} catch(Exception e){
	 			e.printStackTrace();
	 		}finally {
	 			rs.close();
				prep.close();
				ConnectionPool.rilasciaConnessione(con);;
	 		}
	 		return ordini;
		}
		
		/**
	 	 * @param order stringa ordine da usare per rimuovere delle istanze nel DB
	 	 * @precondition user!=null
	 	 * @postcondition ordini=db.ordine->(select(o).orderBy order)
	 	 * @return ordini nuova lista di ordini 
	 	 * @throws SQLException
	 	 */
		public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
			Collection<OrdineBean> ordine=new ArrayList<OrdineBean>();
			Connection con=null;
			PreparedStatement prep=null;
			ResultSet rs=null;
			String sql="SELECT * FROM " + OrdineDao.TABLE_NAME;
			if(order!=null && !order.equals("")) {
				sql+=" ORDER BY " + order;
			}
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				rs=prep.executeQuery();
			
			while(rs.next()) {
				OrdineBean bean=new OrdineBean();
				bean.setIdOrder(UUID.fromString(rs.getString(1)));
 				bean.setDate(new Date(rs.getTimestamp(2).getTime()));
 				bean.setEmail(rs.getString(3));
 				bean.setStato(rs.getString(4));
				ordine.add(bean);
				}
		 } finally {
			rs.close();
			prep.close();
			ConnectionPool.rilasciaConnessione(con);;
		}
		return ordine;
	}
		
		/**
	 	 * @param ordine ordine da aggiornare nel DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doUpdate(OrdineBean ordine) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String sql="UPDATE ordine SET idOrder=?,  email=?";

			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(sql);
				prep.setString(1, ordine.getIdOrder().toString());
				prep.setString(2, ordine.getEmail());
				prep.executeUpdate();
				con.commit();
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);;
			}
		}

		/**
	 	 * @param ordine ordine da rimuovere nel DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
		public void doDelete(OrdineBean ordine) throws SQLException {
			Connection con=null;
			PreparedStatement prep=null;
			String deleteSQL="DELETE FROM " + OrdineDao.TABLE_NAME + " WHERE idOrdine = ?";
			
			try {
				con=ConnectionPool.getConnection();
				prep=con.prepareStatement(deleteSQL);
				prep.setString(1, ordine.getIdOrder().toString());
				prep.executeUpdate();		
			} finally {
				prep.close();
				ConnectionPool.rilasciaConnessione(con);;
			}
		}

		/**
		 * @param user utente da controllare
	 	 * @param order stringa ordine da controllare
	 	 * @return ordini nuova lista di ordini 
	 	 * @throws SQLException
	 	 */
		public ArrayList<OrdineBean> doRetriveByUser(UtenteBean user, String order) throws SQLException {
				ArrayList<OrdineBean> ordine=new ArrayList<OrdineBean>();
				Connection con=null;
				PreparedStatement prep=null;
				ResultSet rs=null;
				String query="SELECT * FROM " + TABLE_NAME + " WHERE email = ?";
				
				if(order!=null) {
					query+=" ORDER BY " + order;
				}
				try {
					con=ConnectionPool.getConnection();
					prep=con.prepareStatement(query);
					prep.setString(1, user.getEmail());
					rs=prep.executeQuery();
					
					while(rs.next()) {
						OrdineBean bean=new OrdineBean();
						bean.setIdOrder(UUID.fromString(rs.getString(1)));
						bean.setEmail(rs.getString(3));
						bean.setDate(new Date(rs.getTimestamp(2).getTime()));
						bean.setStato(rs.getString(4));
						ordine.add(bean);
					}
				} finally {
					prep.close();
					ConnectionPool.rilasciaConnessione(con);;
				}
				return ordine;
			}
		
		/**
		 * @param init //
	 	 * @param end //
	 	 * @param skip //
	 	 * @param limit //
	 	 * @return ordini nuova lista di ordini 
	 	 * @throws SQLException
	 	 */
		public ArrayList<OrdineBean> doRetriveByDate(Date init, Date end, int skip, int limit) throws SQLException {
				ArrayList<OrdineBean> ordine=new ArrayList<OrdineBean>();
				Connection con=null;
				PreparedStatement prep=null;
				ResultSet rs=null;
				String query="SELECT idOrdine,data,email,stato,SUM(prezzo_reale) AS totale FROM ecommerce.occhiale_ordine INNER JOIN ordine on occhiale_ordine.id_ordine=ordine.idOrdine " + " WHERE data >= ? AND data <= ? group by idOrdine ORDER BY data DESC LIMIT  ?, ? "; // LIMIT skip, limit
				
				try {
					con=ConnectionPool.getConnection();
					prep=con.prepareStatement(query);
					prep.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(init));
					prep.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(end) + " 23:59:59");
					prep.setInt(3, skip);
					prep.setInt(4, limit);
					System.out.println(prep+"\n");
					rs=prep.executeQuery();
					
					while(rs.next()) {
						OrdineBean bean=new OrdineBean();
						bean.setIdOrder(UUID.fromString(rs.getString("idOrdine")));
						bean.setEmail(rs.getString("email"));
						bean.setDate(new Date(rs.getTimestamp("data").getTime()));
						bean.setStato(rs.getString("stato"));
						bean.setTot(rs.getInt("totale"));
						ordine.add(bean);
					}
				} finally {
					prep.close();
					ConnectionPool.rilasciaConnessione(con);
				}
				return ordine;
			}
	 }