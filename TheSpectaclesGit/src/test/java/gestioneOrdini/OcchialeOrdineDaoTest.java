package gestioneOrdini;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneOrdini.*;
class OcchialeOrdineDaoTest {
	
	private OcchialeOrdineDao dao=new OcchialeOrdineDao();
	private OcchialeOrdineBean bean=new OcchialeOrdineBean(UUID.fromString("d61fc843-a44a-49e4-909b-f190800e0e91"),"Gucci_GG0926S_1", 200,22,1);
	
	@BeforeEach
	void setUp() throws Exception {
		dao.doSave(bean);
		bean.setIdOcchialeOrdine(dao.getLastIndexAdded());
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.doDelete(bean);
	}
	
	@Test 
	void testDoRetrieveByKey() throws SQLException {	
	   assertTrue(dao.doRetrieveByKey(bean.getIdOcchialeOrdine())!=null);
	  }
	 
	  
	@Test
	void testDoRetrieveAll() throws SQLException {   
		 assertTrue(dao.doRetrieveAll(null)!=null);
	  }
	  
	@Test
	void testDoSave() throws SQLException {
		OcchialeOrdineBean bean2=new OcchialeOrdineBean(UUID.fromString("d61fc843-a44a-49e4-909b-f190800e0e91"),"Montana_BLFBOX67_BB", 250,22,1);;
	    
		try {
			dao.doSave(bean2);
			int id=dao.getLastIndexAdded();
			assertTrue(dao.doRetrieveByKey(id) != null);
	    	} finally {
	    		dao.doDelete(bean2);
	    	}
	  	}
	 
	@Test
	void testDoRetrieveByOrder() throws SQLException {   
		assertTrue(dao.doRetrivebyOrder(bean.getIdOrdine().toString())!=null);
	  }
}