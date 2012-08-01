package baseSrc.framework.workflow;

import org.jbpm.JbpmException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.intasect.hibernate.DBContext;

public class WorkFlowInterceptor extends WFBaseDAO implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		boolean isDBContextOpen = false;
		
		Object result = null;

		WFBaseDAO wfc = null;
		
		if(WFBaseDAO.class 
				== invocation.getMethod().getDeclaringClass().getSuperclass())
		{
			Class<WFBaseDAO> wfdaoClass = (Class<WFBaseDAO>)invocation.getMethod().getDeclaringClass();
			wfc =wfdaoClass.newInstance();
			wfc.setHds(this.hds);
			try
			{
				DBContext dbContext = DBContext.getCurrentDBContext();
				if(!dbContext.getSession().isOpen())
				{
					isDBContextOpen = true;
					wfc.openDBContext();
				}
			}
			catch(JbpmException openException)
			{
				isDBContextOpen = true;
				wfc.openDBContext();
			}
		}
		
		try
		{
			result = invocation.proceed(); 
		}
		catch(Exception ex)
		{
			if(isDBContextOpen
					&& null != wfc)
			{
				wfc.rollBackDBContext();
				//wfc.closeDBContext();
			}
			
			throw ex;
		}
//		TDaonoderelation daonoderelation = getTDaonoderelation(daoName);
//		
//		if(null == daonoderelation)
//		{
//			return result;
//		}
//		
//		updateProcessInstanceInfo_test();

		
		if(isDBContextOpen
				&& null != wfc)
		{
//			ISC_ProcessLog log = new ISC_ProcessLog();
//			log.setInstanceID(123);
//			log.setlogmessage("测试日志事务提交");
//			LogManage logger = new LogManage(new Log4DB(DBContext.getCurrentDBContext()));
//			logger.write(log);
			
			wfc.closeDBContext();
		}
			
		return result;
	}
	
}
