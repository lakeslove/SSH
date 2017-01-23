package com.lx.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137279744955162255L;

	@Id
//	@Size(max = 1,message = "{typeMismatch.user.id}")
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
	@Size(max = 1,message = "{typeMismatch.user.id}")
	@Column(name = "NAME")
	private String name;

	@Column(name = "age")
	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	

}
