package com.lx.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Abstract implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7631564826501648289L;

	/**
	 * 作成者ID
	 */
	@Column(name = "create_user_id")
	protected long createUserId;
	
	/**
	 * 作成日時
	 */
	@Column(name = "create_date", updatable = false)
	protected Timestamp createDate;
	
	/**
	 * 更新者ID
	 */
	@Column(name = "update_user_id")
	protected long updateUserId;

	/**
	 * 更新日時
	 */
	@Column(name = "update_date")
	protected Timestamp updateDate;

	public long getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateAndUpdate(Long userId){
		setCreateUserId(userId);
		setCreateDate(new Timestamp(System.currentTimeMillis()));
		setUpdateUserId(userId);
		setUpdateDate(new Timestamp(System.currentTimeMillis()));
	}
	
	public void setUpdate(Long userId){
		setUpdateUserId(userId);
		setUpdateDate(new Timestamp(System.currentTimeMillis()));
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
