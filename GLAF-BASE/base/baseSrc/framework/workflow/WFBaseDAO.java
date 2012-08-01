package baseSrc.framework.workflow;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intasect.hibernate.DBContext;
import com.intasect.hibernate.DbPersistence;

import baseSrc.common.BaseDaoSupport;
import baseSrc.framework.BaseDAO;

public class WFBaseDAO extends BaseDAO {
	
	private Transaction transaction = null;
	private Session session = null;
	
	public BaseDaoSupport hds;
	
	public void setHds(BaseDaoSupport hds) {
		this.hds = hds;
	}
	
	public void openDBContext()
	{
		session = hds.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		DBContext dbContext = new DBContext(hds.getSessionFactory().getCurrentSession(),session);
		DbPersistence.pushDBContext(dbContext);
	}
	
	public void closeDBContext()
	{
		if(null != transaction)
		{
			transaction.commit();
			session.close();
		}
		
		DBContext dbContext = DBContext.getCurrentDBContext();
		//dbContext.commitTransaction();
		dbContext.getJbpmContext().close();
		DbPersistence.popDBContext();
	}
	
	public void rollBackDBContext()
	{
		if(null != transaction)
		{
			transaction.commit();
			session.close();
		}
		
		DBContext dbContext = DBContext.getCurrentDBContext();
		//dbContext.rollbackTransaction();
		dbContext.getJbpmContext().close();
		DbPersistence.popDBContext();
	}
}
