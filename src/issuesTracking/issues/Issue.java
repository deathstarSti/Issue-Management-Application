package issuesTracking.issues;

import java.util.Date;

public class Issue {
	
	protected int id;
	protected String title;
	protected String description;
	protected String type;
	protected int assignedTo;
	protected Date createdAt;
	protected Date editedAt;
	protected int editedBy;
	protected int createdBy;
	protected String status;
	
	
	public Issue() {
		super();
	}

	

	public Issue(String title, String description, String type, Date createdAt, int createdBy, int assignedTo) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		
	}



	public Issue(String title, String description, String type, int assignedTo, Date createdAt, Date editedAt,
			int editedBy, int createdBy, String status) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.assignedTo = assignedTo;
		this.createdAt = createdAt;
		this.editedAt = editedAt;
		this.editedBy = editedBy;
		this.createdBy = createdBy;
		this.status = status;
	}


	public Issue(int id, String title, String description, String type, int assignedTo, Date editedAt, int editedBy,
			String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.assignedTo = assignedTo;
		this.editedAt = editedAt;
		this.editedBy = editedBy;
		this.status = status;
	}



	public Issue(int id, String title, String description, String type, int assignedTo, Date createdAt, Date editedAt,
			int editedBy, int createdBy, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.assignedTo = assignedTo;
		this.createdAt = createdAt;
		this.editedAt = editedAt;
		this.editedBy = editedBy;
		this.createdBy = createdBy;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getEditedAt() {
		return editedAt;
	}


	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}


	public int getEditedBy() {
		return editedBy;
	}


	public void setEditedBy(int editedBy) {
		this.editedBy = editedBy;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
