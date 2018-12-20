package com.sb.myblog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Every model(Bean) class must extend this class. It consists of basic details that every database table must have
 * for tracebility of data in long run.
 */
@MappedSuperclass
public class GenericBean implements Serializable{
	
	static final long serialVersionUID = 0L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
    private long id;
	
	@Column(nullable = false, updatable = false)
    private Date creationDate = new Date();
	
	@Column(nullable = false)
    private Date lastUpdateDate = new Date();
	
	
    private String createBy = "SYSTEM";
    private String lastUpdateBy ="SYSTEM";
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	
}
