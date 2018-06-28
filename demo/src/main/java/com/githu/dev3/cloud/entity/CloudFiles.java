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
@Table(name = "CloudFiles" )
@Getter
@Setter
public class CloudFiles {

	@Id
    @GeneratedValue
    @Column(name = "ID")
	private Long id;
	
	@Column(name = "FILE_NAME", nullable = true, length = 500)
	private String fileName;
	
	@Column(name = "UPLOAD_TIME")
	private Date uploadTime;
	
	@Column(name = "UPLOAD_USER", nullable = true, length = 50)
	private String uploadUser;

	public CloudFiles(Long id, String fileName, Date uploadTime, String uploadUser) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.uploadTime = uploadTime;
		this.uploadUser = uploadUser;
	}


}
