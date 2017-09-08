import java.util.Date;

public class Datastoreobject {
	String name;
	String taskDiscription;
	Date startDate;
	Date endDate;
	int id;
	
	public Datastoreobject(String name, String taskDiscription, int id) {
		super();
		this.name = name;
		this.taskDiscription = taskDiscription;
		this.autoStartDate();
		this.endDate = null;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaskDiscription() {
		return taskDiscription;
	}
	public void setTaskDiscription(String taskDiscription) {
		this.taskDiscription = taskDiscription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void autoEndDate() {
		this.endDate = new Date();
	}
	
	public void autoStartDate() {
		this.startDate = new Date();
	}
	
	public boolean isCompleted() {
		return this.endDate != null;
	}
	
}
