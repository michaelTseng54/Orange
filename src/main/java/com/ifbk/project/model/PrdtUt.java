package com.ifbk.project.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

// Generated 2014/10/1 下午 02:42:57 by Hibernate Tools 3.4.0.CR1

/**
 * PrdtUt generated by hbm2java
 */
@Entity
@Table(name="PRDT_UT")
public class PrdtUt implements java.io.Serializable {

	@EmbeddedId
	private PrdtUtId id;

	public PrdtUt() {
	}

	public PrdtUt(PrdtUtId id) {
		this.id = id;
	}

	public PrdtUtId getId() {
		return this.id;
	}

	public void setId(PrdtUtId id) {
		this.id = id;
	}

}
