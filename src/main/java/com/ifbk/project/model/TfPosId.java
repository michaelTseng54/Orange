package com.ifbk.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Generated 2014/4/22 下午 03:40:33 by Hibernate Tools 3.4.0.CR1

/**
 * TfPosId generated by hbm2java
 */
@Embeddable
public class TfPosId implements java.io.Serializable {

	@Column(name = "OS_ID")
	private String osId;
	
	@Column(name = "OS_NO")
	private String osNo;
	
	@Column(name = "ITM")
	private int itm;

	public TfPosId() {
	}

	public TfPosId(String osId, String osNo, int itm) {
		this.osId = osId;
		this.osNo = osNo;
		this.itm = itm;
	}

	public String getOsId() {
		return this.osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public String getOsNo() {
		return this.osNo;
	}

	public void setOsNo(String osNo) {
		this.osNo = osNo;
	}

	public int getItm() {
		return this.itm;
	}

	public void setItm(int itm) {
		this.itm = itm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TfPosId))
			return false;
		TfPosId castOther = (TfPosId) other;

		return ((this.getOsId() == castOther.getOsId()) || (this.getOsId() != null
				&& castOther.getOsId() != null && this.getOsId().equals(
				castOther.getOsId())))
				&& ((this.getOsNo() == castOther.getOsNo()) || (this.getOsNo() != null
						&& castOther.getOsNo() != null && this.getOsNo()
						.equals(castOther.getOsNo())))
				&& (this.getItm() == castOther.getItm());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOsId() == null ? 0 : this.getOsId().hashCode());
		result = 37 * result
				+ (getOsNo() == null ? 0 : this.getOsNo().hashCode());
		result = 37 * result + this.getItm();
		return result;
	}

}
