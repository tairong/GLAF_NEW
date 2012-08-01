package demoSrc.orm;

/**
 * VTodo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToDo implements java.io.Serializable {

	// Fields

	private ToDoId id;
	
	private Long taskId;
	private String taskName;
	
	private String userEMail;
	private String creatTaskTime;
	//private String currentTaskStatusName;
	private String currentTaskStatusValue;
	private String startUserId;
	private String oldUserId;
	private Long status;

	// Constructors

	/** default constructor */
	public ToDo() {
	}

	/** full constructor */
	public ToDo(ToDoId id) {
		this.id = id;
	}

	public ToDoId getId() {
		return id;
	}

	public void setId(ToDoId id) {
		this.id = id;
	}
	
	// Constructors

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public String getUserEMail() {
		return userEMail;
	}

	public void setUserEMail(String userEMail) {
		this.userEMail = userEMail;
	}

	public String getCreatTaskTime() {
		return creatTaskTime;
	}

	public void setCreatTaskTime(String creatTaskTime) {
		this.creatTaskTime = creatTaskTime;
	}

//	public String getCurrentTaskStatusName() {
//		return currentTaskStatusName;
//	}
//
//	public void setCurrentTaskStatusName(String currentTaskStatusName) {
//		this.currentTaskStatusName = currentTaskStatusName;
//	}

	public String getCurrentTaskStatusValue() {
		return currentTaskStatusValue;
	}

	public void setCurrentTaskStatusValue(String currentTaskStatusValue) {
		this.currentTaskStatusValue = currentTaskStatusValue;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	public String getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

}