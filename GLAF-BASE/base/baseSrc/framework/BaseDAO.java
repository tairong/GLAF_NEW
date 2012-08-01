package baseSrc.framework;

import baseSrc.common.DbAccess;
import baseSrc.common.LogHelper;

public class BaseDAO{
	
	protected static LogHelper logger = new LogHelper(BaseDAO.class);
	
	private String defaultMethod;

	protected DbAccess dbAccess;	
	

	public String getDefaultMethod() {
		return defaultMethod;
	}
	
	public void setDefaultMethod(String defaultMethod) {
		this.defaultMethod = defaultMethod;
	}	

	public void setDbAccess(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}
	
}
