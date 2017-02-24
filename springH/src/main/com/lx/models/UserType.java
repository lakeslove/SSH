package com.lx.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654637344402912296L;

	@Id
//	@Size(max = 1,message = "{typeMismatch.user.id}")
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
	@Column(name = "user_type")
	private int userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
}
