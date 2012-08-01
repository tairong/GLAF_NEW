package demoSrc.src;

import sysSrc.common.SysBaseCom;
import sysSrc.framework.SysBaseDTOMap;

import com.intasect.hibernate.DBContext;
import com.intasect.manage.ISC_ProcessInstance;
import com.intasect.query.ProcessInstanceManage;
import demoSrc.orm.TTestWfdemo;

import baseSrc.common.BaseUtility;
import baseSrc.framework.workflow.WFBaseDAO;

public class StartFlowDAO extends WFBaseDAO {
	public SysBaseDTOMap runPageLoad(StartFlowForm form,SysBaseCom baseCom){
		
		SysBaseDTOMap dtoMap = new SysBaseDTOMap();	
		
		dtoMap.setForwardId("startFlowGo");

		return dtoMap;
	}
	
	public SysBaseDTOMap runPageSave(StartFlowForm form,SysBaseCom baseCom)
	{
		SysBaseDTOMap dtoMap = new SysBaseDTOMap();
		
		if(!BaseUtility.isStringNull(form.getFstuffapplyno()))
		{
			TTestWfdemo testwfdemo = new TTestWfdemo();
			testwfdemo.setFStuffapplyno(form.getFstuffapplyno());
			testwfdemo.setFChangeereason(form.getFchangeereason());
			testwfdemo.setFSafetypeid(form.getFsafetypeid());
			testwfdemo.setFApplytypeid(form.getFapplytypeid());

			long processDefinitionID = BaseUtility.getTestProcessDifination();
			long userid = 1978848;
			long deptid = 11;
			
			ProcessInstanceManage piManage = new ProcessInstanceManage(DBContext.getCurrentDBContext());
			ISC_ProcessInstance processInstance = piManage.creatProcessInstance(processDefinitionID, userid, deptid);

			testwfdemo.setProcessinstanceid(processInstance.getId());
			
			this.dbAccess.saveOrUpdate(testwfdemo);
			
			dtoMap.setMsgId("info.success");
			dtoMap.addMsgArg("保存");
		}
		
		dtoMap.setForwardId("startFlowGo");

		return dtoMap;
	}
	

}
