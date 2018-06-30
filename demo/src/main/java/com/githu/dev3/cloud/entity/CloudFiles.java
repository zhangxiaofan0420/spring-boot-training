package com.githu.dev3.cloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Cloud File Entity
 * 
 * @author Daniel Z Zhou
 *
 */
@Entity
@Table(name = "cloud_files" )
//@Getter
//@Setter
public class CloudFiles {

	@Id
    @GeneratedValue
    @Column(name = "ID")
	private Long id;
	
	@Column(name = "FILE_NAME", nullable = true, length = 200)
	private String fileName;
	
	@Column(name = "REAL_NAME", nullable = true, length = 200)
	private String realName;
	
	@Column(name = "UPLOAD_TIME")
	private Date uploadTime;
	
	@Column(name = "UPLOAD_USER", nullable = true, length = 50)
	private String uploadUser;

	
	public CloudFiles() {
	}

	public CloudFiles(Long id, String fileName, String realName, Date uploadTime, String uploadUser) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.realName = realName;
		this.uploadTime = uploadTime;
		this.uploadUser = uploadUser;
	}
	
	public CloudFiles(String fileName, String realName, Date uploadTime, String uploadUser) {
		super();
		this.fileName = fileName;
		this.realName = realName;
		this.uploadTime = uploadTime;
		this.uploadUser = uploadUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}


}
