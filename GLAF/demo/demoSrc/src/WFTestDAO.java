package demoSrc.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.taskmgmt.exe.TaskInstance;

import sysSrc.common.SysBaseCom;
import sysSrc.framework.SysBaseDTOMap;

import demoSrc.orm.TTestWfdemo;
import demoSrc.orm.TTestWfdemoBak;
import demoSrc.orm.TTestWfdemoBakId;
import demoSrc.orm.ToDo;

import baseSrc.common.AutoArrayList;
import baseSrc.common.BaseUtility;
import baseSrc.framework.BaseConstants;
import baseSrc.framework.workflow.WFBaseDAO;

public class WFTestDAO extends WFBaseDAO {
	public SysBaseDTOMap runPageLoad(WFTestForm form,SysBaseCom baseCom){
		
		SysBaseDTOMap dtoMap = new SysBaseDTOMap();	
		
		dtoMap.setForwardId("testPageGo");

		return dtoMap;
	}
	
	public SysBaseDTOMap runPageCtrl(WFTestForm form,SysBaseCom baseCom){

		//创建控制接口对象
		SysBaseDTOMap dtoMap = new SysBaseDTOMap();
		
		//取得每一页的数据对象
		List<?> list = getDataFormDB(form,baseCom);
		
		//把取得的数据放到画面对应FORMBEAN中

		setActionForm(list,form);
		
		dtoMap.setForwardId("testPageGo");

		return dtoMap;
	}
	
	public SysBaseDTOMap runWFSave(WFTestForm form,SysBaseCom baseCom){

		//创建控制接口对象
		SysBaseDTOMap dtoMap = new SysBaseDTOMap();
		
		Map<String,Object> parems = new HashMap<String,Object>();
		parems.put("isUDCLPass", "OK");
		
		form.setProcessParems(parems);
		
		dtoMap.setForwardId("testPageGo");
		
		// 数据备份
		long tid = form.getTaskInstanceId();
		backup(tid);

		return dtoMap;
	}
	
	private List<?> getDataFormDB(WFTestForm form,SysBaseCom baseCom){
		
		//定义检索表名

		String tabName = "ToDo A";
		
		//创建检索条件参数对象
		Map<String, Object> params = new HashMap<String, Object>();		
		//定义删除条件
		StringBuffer whereHql = new StringBuffer(" WHERE ");
		whereHql.append(" A.id.processDefinitionId = :processDefinitionId");
		params.put("processDefinitionId", BaseUtility.getTestProcessDifination());
		
		int count = this.dbAccess.getResutlsTotal(tabName,whereHql.toString(),params);
		
		//取得每一页的显示条数
		int pageSize = BaseConstants.ISC_PAGE_SIZE_FIVE;
		
		//取得每一页的数据对象
		List<?> list = this.dbAccess.getResutlsForPage(tabName,whereHql.toString(),params,form,count,pageSize);
		return list;
	}
	
	private void setActionForm(List<?> list,WFTestForm form){
		
		//定义画面中数据元素

		AutoArrayList detailList = new AutoArrayList(WFTestDetails.class);
		
		//根据取得数值设置画面元素值

		if(!BaseUtility.isListNull(list)){
			for(int i = 0;i<list.size();i++){
				ToDo tabObj = (ToDo)list.get(i);
				WFTestDetails detail = new WFTestDetails();
				detail.setProcessInstanceId(tabObj.getId().getProcessInstanceId());
				detail.setTaskInstanceId(tabObj.getId().getTaskInstanceId());
				detail.setTaskId(tabObj.getTaskId());
				detail.setTaskName(tabObj.getTaskName());
				detail.setUserId(tabObj.getId().getUserId());
				detail.setUserName(tabObj.getId().getUserName());
				detail.setUserEmail(tabObj.getUserEMail());
				detailList.add(detail);
				}
		}
		
		//把设置好的值存放到画面对应FORMBEAN中

		form.setWfTestDetails(detailList);
	}
	
	private void backup(long taskInstanceId)
	{
		// 取得任务实例
		TaskInstance taskInstance = (TaskInstance) this.dbAccess
			.load(TaskInstance.class, taskInstanceId);
		
		long processInstanceId = taskInstance.getTaskMgmtInstance().getProcessInstance().getId();
		
		String tabName = "TTestWfdemo A";
		
		//创建检索条件参数对象
		Map<String, Object> params = new HashMap<String, Object>();		
		//定义删除条件
		StringBuffer whereHql = new StringBuffer(" WHERE ");
		whereHql.append(" A.processinstanceid = :processinstanceid");
		params.put("processinstanceid", processInstanceId);
		
		List<TTestWfdemo> ttestWfdemolist = (List<TTestWfdemo>) this.dbAccess.find(tabName, whereHql.toString(), params);
		if(ttestWfdemolist.size()>0)
		{
			TTestWfdemo ttestWfdemo = ttestWfdemolist.get(0);
			
			TTestWfdemoBakId ttestWfdemoBakId = new TTestWfdemoBakId();
			ttestWfdemoBakId.setFStuffapplyno(ttestWfdemo.getFStuffapplyno());
			ttestWfdemoBakId.setFTaskinstanceid(taskInstanceId);
			
			TTestWfdemoBak ttestWfdemoBak = new TTestWfdemoBak();
			ttestWfdemoBak.setId(ttestWfdemoBakId);
			ttestWfdemoBak.setFChangeereason(ttestWfdemo.getFChangeereason());
			ttestWfdemoBak.setFSafetypeid(ttestWfdemo.getFSafetypeid());
			ttestWfdemoBak.setFApplytypeid(ttestWfdemo.getFApplytypeid());
			ttestWfdemoBak.setProcessinstanceid(processInstanceId);
			
			this.dbAccess.saveOrUpdate(ttestWfdemoBak);
		}
		
	}

}
