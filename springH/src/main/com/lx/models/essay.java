package com.lx.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "essay")
public class essay extends Abstract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -514581174913242868L;

	@Id
//	@Size(max = 1,message = "{typeMismatch.user.id}")
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
}
