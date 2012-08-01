package baseSrc.framework.workflow;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import baseSrc.common.BaseCom;
import baseSrc.framework.BaseActionForm;
import baseSrc.framework.BaseDAO;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.intasect.hibernate.DBContext;
import com.intasect.manage.ISC_ProcessInstance;
import com.intasect.query.NodeParameter;
import com.intasect.query.ProcessInstanceManage;
import com.intasect.query.ProcessNodeManage;

public class WorkFlowProcessInterceptor extends BaseDAO implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Object result = null;
		Object[] obj = null;
		BaseCom bc  = null;
		BaseActionForm baf = null;
		
		result = invocation.proceed(); 

		// 取得方法名
		String methodName = invocation.getMethod().getName(); 

		// 通过方法名判断是否处理工作流
		if(methodName.length()>=5
				&& "runWF".equals(methodName.substring(0,5))){

			obj = invocation.getArguments();
			baf = (BaseActionForm)obj[0];
			bc = (BaseCom)obj[1];
			
			// 调用工作流处理
			this.setWorkFlow(baf, bc);
		}

		return result;
	}
	
	/**
	 * 推动工作流
	 * @param baf 业务DAO传入的接口
	 * @param bc 用户信息
	 */
	private void setWorkFlow(BaseActionForm baf, BaseCom bc)
	{
		logger.info("workflow start-------");
		
		TaskInstance taskInstance = null;
		long taskInstanceID = 0;
		// TODO: tmp
		long userid = 1979444;
		
		// 取得用户 ID
		if(!"".equals(bc.getUserId()))
		{
			userid = Long.valueOf(bc.getUserId()).longValue();
		}
		
		// 取得TaskInstanceId和任务实例
		if(baf.getTaskInstanceId()>0)
		{
			taskInstanceID = baf.getTaskInstanceId();
			taskInstance = getTaskInstance(taskInstanceID);
		}
		else
		{
			// 没有指定TaskInstanceId,直接返回
			logger.info("没有指定TaskInstanceId");
			return ;
		}
		
		// 设置参数
		setNodeParemeters(taskInstance.getId(), baf.getProcessParems());
		
		// 推动流程
		updateProcessInstanceInfo(taskInstance ,userid);

		logger.info("workflow end-------");
	};
	
	/**
	 * 检查任务实例是否已经被推动
	 * @param taskInstanceID 任务实例ID
	 * @throws taskInstanceHasEndedException 已推动时抛出异常
	 */
	private TaskInstance getTaskInstance(long taskInstanceID)
	{
		// 取得任务实例
		TaskInstance taskInstance = (TaskInstance) this.dbAccess
			.load(TaskInstance.class, taskInstanceID);
		
		// 判断是否结束
		if(taskInstance.getEnd() != null)
		{
			// 抛出异常
			throw new taskInstanceHasEndedException("该任务已完成,不能在操作");
		}
		
		return taskInstance;
	}
	
	/**
	 * 设置节点参数
	 * @param taskInstanceID 要设置的任务实例ID
	 * @param paremsMap 传如的节点参数Map
	 */
	private void setNodeParemeters(long taskInstanceID,Map<String,Object> paremsMap)
	{
		ProcessNodeManage processNodeManage = new ProcessNodeManage(DBContext.getCurrentDBContext());
		List<NodeParameter> variableList = processNodeManage.getVariable(taskInstanceID);
		
		if(variableList.size() > 0)
		{
			Iterator iterator = variableList.iterator();
			while(iterator.hasNext())
			{
				NodeParameter nodeParameter = (NodeParameter)iterator.next();
				if(paremsMap.containsKey(nodeParameter.getLabel()))
				{
					nodeParameter.setValue(paremsMap.get(nodeParameter.getLabel()));
				}
				else
				{
					throw new ParemIsNotExistException("指定的参数[" + nodeParameter.getLabel() + "]不存在");
				}
			}
			
			processNodeManage.setVariable(taskInstanceID, variableList);
		}
	}
	
	/**
	 * 流程推动
	 * @param taskInstance 要推动的任务实例
	 * @param userid 推动用户
	 */
	private void updateProcessInstanceInfo(TaskInstance taskInstance, long userid)
	{
		try
		{
			ProcessInstanceManage piManage = new ProcessInstanceManage(DBContext.getCurrentDBContext());
			ISC_ProcessInstance processInstance = piManage.loadProcessInstance(taskInstance.getToken().getProcessInstance().getId());
			
			processInstance.signalNext(taskInstance.getId(), userid);
		}
		catch(Exception ex)
		{
			throw new ProcessActionExcpetion(ex);
		}
	}
	
}
