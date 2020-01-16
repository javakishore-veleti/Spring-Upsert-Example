package spring.upsert.common.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String status;

	private Date createdOn;
	private Date updatedOn;

	public BaseDomain() {
		super();
	}

	public BaseDomain(Long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "BaseDomain [id=" + id + ", status=" + status + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", toString()=" + super.toString() + "]";
	}

}
