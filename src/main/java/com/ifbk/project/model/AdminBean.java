// default package
// Generated 2013/5/23 �W�� 11:36:16 by Hibernate Tools 3.3.0.GA
package com.ifbk.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pmt001 generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ADMIN")
public class AdminBean implements java.io.Serializable {

	public AdminBean() {}

	@Id
	@Column(name="ACCOUNT")
	private String account;


	@Column(name="PASSWORD")
	private byte[] password;
	
	@Column(name="PRIVILEGE")
	private String privilege;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
