package gestioneOrdini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

import gestioneOcchiali.OcchialeBean;
import gestioneOcchiali.OcchialeDao;
import util.ConnectionPool;

import java.util.UUID;

	/**
	* Questa classe e' un oggetto manager che si interfaccia con il DB. Gestisce le query riguardanti l'oggetto OcchialeOrdine.
	 * @author Mario Ranieri 
	 * @author Roberto Piscopo
	 */
	public class OcchialeOrdineDao {
	  private static final String TABLE_NAME="occhiale_ordine";
	  private DataSource ds;

	  /**
	   * @param obj connessione al database
	   * @return
	   */
	  public void setDB(DataSource obj) {
		this.ds=obj;
	  }

	  /**
	   * @param occhialeOrdine occhialeOrdine da salvare nel DB
	   * @return
	   * @throws SQLException
	   */
	  public void doSave(OcchialeOrdineBean occhialeOrdine) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		String insertSQL="INSERT INTO " + TABLE_NAME + " (id_occhiale, id_ordine, prezzo_reale, iva ,quantita) VALUES(?,?,?,?,?)";
		
		try {
			con=ConnectionPool.getConnection();
			ps=con.prepareStatement(insertSQL);
			//ps.setString(1, occhialeOrdine.getProdotto().getIdGlasses());
			ps.setString(1, occhialeOrdine.getIdProdotto());
			ps.setString(2, occhialeOrdine.getIdOrdine().toString());
			ps.setFloat(3, occhialeOrdine.getPrezzoEffettivo());
			ps.setFloat(4, occhialeOrdine.getIva());
			ps.setInt(5, occhialeOrdine.getQuantita());
			System.out.println("doSave: "+ps);
			ps.executeUpdate();
		} finally {
			try {
				if(ps!=null)
					ps.close();
			} finally {
				if(con!=null)
					ConnectionPool.rilasciaConnessione(con);
			}
		}
	  }

	  /**
	 	 * @param occhialeOrdine occhialeOrdine da rimuovere nel DB
	 	 * @return
	 	 * @throws SQLException
	 	 */
	  public void doDelete(OcchialeOrdineBean occhialeOrdine) throws SQLException {
		  Connection con=null;
		  PreparedStatement prep=null;
		  String deleteSQL="DELETE FROM " + OcchialeOrdineDao.TABLE_NAME + " WHERE id = ?";
		
		try {
			con=ConnectionPool.getConnection();
			prep=con.prepareStatement(deleteSQL);
			prep.setInt(1, occhialeOrdine.getIdOcchialeOrdine());
			System.out.println("doDelete: "+prep);
			prep.executeUpdate();
		} finally {
			prep.close();
			ConnectionPool.rilasciaConnessione(con);;
		}
	}

	  /**
	   * @param idOcchialeOrdine stringa id da controllare
	   * @precondition idOcchiale!=NULL
	   * @postcondition OcchialeOrdine=db.occhialeOrdine->(select(o|o.id=idOcchialeOrdine))
	   * @return listaProdotti.get(0) lista di prodotti partendo dall'inizio
	   * @throws SQLException
	   */
	  public OcchialeOrdineBean doRetrieveByKey(int idOcchialeOrdine) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<OcchialeOrdineBean> listaProdotti=new ArrayList<>();
		String query="SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		
		try {
			con=ConnectionPool.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, idOcchialeOrdine);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				OcchialeOrdineBean prodotto=new OcchialeOrdineBean(rs.getInt(1), UUID.fromString(rs.getString(3)), rs.getString(2)	, rs.getInt(4), rs.getFloat(5), rs.getInt(6));
				listaProdotti.add(prodotto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			ConnectionPool.rilasciaConnessione(con);
		}
		return listaProdotti.get(0);
	}

	  /**
	   * @param order stringa ordine da controllare
	   * @return occhiali lista di occhiali
	   * @throws SQLException
	   */
	  public Collection<OcchialeOrdineBean> doRetrieveAll(String order) throws SQLException {
		Collection<OcchialeOrdineBean> occhiali=new ArrayList<OcchialeOrdineBean>();
		Connection con=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		ArrayList<String> key=new ArrayList<>();
		String query="SELECT * FROM " + TABLE_NAME;
		if (order!=null && !order.equals("")) {
			query+=" ORDER BY " + order;
		}

		try {
			con=ConnectionPool.getConnection();
			prep=con.prepareStatement(query);
			rs=prep.executeQuery();

			while(rs.next()) {
				key.add(rs.getString(2));
				OcchialeBean prod=(OcchialeBean) new OcchialeDao().doRetrieveByKey(key);
				OcchialeOrdineBean bean=new OcchialeOrdineBean(rs.getInt(1), UUID.fromString(rs.getString(3)), prod.getIdGlasses(), rs.getInt(4), rs.getFloat(5), rs.getInt(6));
				occhiali.add(bean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			prep.close();
			ConnectionPool.rilasciaConnessione(con);
		}
		return occhiali;
	}

	/*public ArrayList<OcchialeOrdineBean> doRetrivebyOrder(OrdineBean ordine,DataSource dataS) throws SQLException {
		Connection con=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		ArrayList<OcchialeOrdineBean> ordini=new ArrayList<OcchialeOrdineBean>();
		ArrayList<String> key=new ArrayList<>();
		String query="SELECT * FROM " + TABLE_NAME + " WHERE id_ordine = '" + ordine.getIdOrder() + "'";
     
		try {
			con=ConnectionPool.getConnection();
			prep=con.prepareStatement(query);
			rs=prep.executeQuery();

			while(rs.next()) {
				key.add(rs.getString(2));
				OcchialeBean prod=new OcchialeBean();
				OcchialeDao oDao=new OcchialeDao();
				oDao.setDB(dataS);
				//Da modificare
				Collection<OcchialeBean> list=oDao.doRetrieveByKey(key);
				OcchialeOrdineBean bean=new OcchialeOrdineBean(rs.getInt(1), UUID.fromString(rs.getString(3)), prod, rs.getFloat(4), rs.getFloat(5), rs.getInt(6));
				System.out.println("Metodo Dao: "+bean);
				ordini.add(bean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			prep.close();
			ConnectionPool.rilasciaConnessione(con);
		}
		return ordini;
	}*/

	  /**
	   * @param ordine ordine da controllare
	   * @precondition ordine!=null AND data!=null //DATA NON C'E' NEL METODO
	   * @postcondition ordini=db.occhialeOrdine->(select(o|o.idOrdine=ordine))
	   * @return ordini
	   * @throws SQLException
	   */
	  public ArrayList<OcchialeOrdineBean> doRetrivebyOrder(String ordine) throws SQLException {
		  Connection con=null;
		  PreparedStatement prep=null;
		  ResultSet rs=null;
		  ArrayList<OcchialeOrdineBean> ordini=new ArrayList<OcchialeOrdineBean>();
		  String query="SELECT * FROM " + TABLE_NAME + " WHERE id_ordine = ?";
 
		  try {
			  con=ConnectionPool.getConnection();
			  prep=con.prepareStatement(query);
			  prep.setString(1, ordine);
			  rs=prep.executeQuery();

			  while(rs.next()) {
			/*OcchialeDao oDao=new OcchialeDao();
			OcchialeBean prod=oDao.doRetrieveOcchiale(rs.getString(2));
			System.out.println("Metodo OcchialeOrdineDao  OcchialeBean: "+prod);*/
			//(int idOcchialeOrdine, UUID idOrdine, OcchialeBean occhiale, int prezzoEffettivo, float iva, int quantita)
				 
				  OcchialeOrdineBean bean=new OcchialeOrdineBean(rs.getInt(1), UUID.fromString(rs.getString("id_ordine")), rs.getString("id_occhiale"),rs.getInt("prezzo_reale"), rs.getFloat("iva"), rs.getInt("quantita"));
			//System.out.println("Metodo OcchialeOrdineDao  doRetrivebyOrder: "+bean);
				  ordini.add(bean);
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  rs.close();
			  prep.close();
			  ConnectionPool.rilasciaConnessione(con);
		  }
		  //System.out.println("tutti gli ordini doRetrivebyOrder: "+ordini);
		  return ordini;
	  }
	  
	  /**
	   * @return index ultimo indice aggiunto
	   * @throws SQLException
	   */
	  public int getLastIndexAdded() throws SQLException {
		Connection con=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		int index=0;
		String query="SELECT MAX(id) FROM  " + TABLE_NAME;
	 
		try {
			con=ConnectionPool.getConnection();
			prep=con.prepareStatement(query);
			rs=prep.executeQuery();
			
			while(rs.next()) {
				index=rs.getInt("Max(id)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			prep.close();
			ConnectionPool.rilasciaConnessione(con);
	     }
		return index;
	}
}