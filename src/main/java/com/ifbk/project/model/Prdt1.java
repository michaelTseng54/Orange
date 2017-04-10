package com.ifbk.project.model;

// Generated 2014/9/26 下午 05:06:34 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Prdt1 generated by hbm2java

 */
@Entity
@Table(name="PRDT1")
public class Prdt1 implements java.io.Serializable {

	/**
	 */
	private static final long serialVersionUID = -2975145296324739282L;

	@EmbeddedId
	private Prdt1Id id;
	@Column(name = "QTY_INT")
	private BigDecimal qtyInt;
	@Column(name = "QTY")
	private BigDecimal qty;
	@Column(name = "QTY1")
	private BigDecimal qty1;
	@Column(name = "AMT_CST")
	private BigDecimal amtCst;
	@Column(name = "QTY_MAX")
	private BigDecimal qtyMax;
	@Column(name = "QTY_MIN")
	private BigDecimal qtyMin;
	@Column(name = "QTY_LRN")
	private BigDecimal qtyLrn;
	@Column(name = "QTY_BRW")
	private BigDecimal qtyBrw;
	@Column(name = "QTY_ON_WAY")
	private BigDecimal qtyOnWay;
	@Column(name = "QTY_ON_PRC")
	private BigDecimal qtyOnPrc;
	@Column(name = "QTY_ON_RSV")
	private BigDecimal qtyOnRsv;
	@Column(name = "QTY_ON_ODR")
	private BigDecimal qtyOnOdr;
	@Column(name = "LST_OTD")
	private Date lstOtd;
	@Column(name = "LST_IND")
	private Date lstInd;
	@Column(name = "LST_SFD")
	private Date lstSfd;
	@Column(name = "UP_INT")
	private BigDecimal upInt;
	@Column(name = "CST_INT")
	private BigDecimal cstInt;
	@Column(name = "CST_STD")
	private BigDecimal cstStd;
	@Column(name = "CST_PO")
	private BigDecimal cstPo;
	@Column(name = "CST_SAL")
	private BigDecimal cstSal;
	@Column(name = "UP_STD")
	private BigDecimal upStd;
	@Column(name = "UP_SAL")
	private BigDecimal upSal;
	@Column(name = "QTY1_LRN")
	private BigDecimal qty1Lrn;
	@Column(name = "QTY1_BRW")
	private BigDecimal qty1Brw;
	@Column(name = "UP_PO")
	private BigDecimal upPo;
	@Column(name = "QTY_SA")
	private BigDecimal qtySa;
	@Column(name = "QTY_RK")
	private BigDecimal qtyRk;
	@Column(name = "QTY_ZG")
	private BigDecimal qtyZg;
	@Column(name = "QTY_CK")
	private BigDecimal qtyCk;
	@Column(name = "QTY_SQ")
	private BigDecimal qtySq;
	@Column(name = "REM")
	private String rem;


	public Prdt1() {
	}

	public Prdt1(Prdt1Id id) {
		this.id = id;
	}

	public Prdt1(Prdt1Id id, BigDecimal qtyInt, BigDecimal qty,
			BigDecimal qty1, BigDecimal amtCst, BigDecimal qtyMax,
			BigDecimal qtyMin, BigDecimal qtyLrn, BigDecimal qtyBrw,
			BigDecimal qtyOnWay, BigDecimal qtyOnPrc, BigDecimal qtyOnRsv,
			BigDecimal qtyOnOdr, Date lstOtd, Date lstInd, Date lstSfd,
			BigDecimal upInt, BigDecimal cstInt, BigDecimal cstStd,
			BigDecimal cstPo, BigDecimal cstSal, BigDecimal upStd,
			BigDecimal upSal, BigDecimal qty1Lrn, BigDecimal qty1Brw,
			BigDecimal upPo, BigDecimal qtySa, BigDecimal qtyRk,
			BigDecimal qtyZg, BigDecimal qtyCk, BigDecimal qtySq, String rem) {
		this.id = id;
		this.qtyInt = qtyInt;
		this.qty = qty;
		this.qty1 = qty1;
		this.amtCst = amtCst;
		this.qtyMax = qtyMax;
		this.qtyMin = qtyMin;
		this.qtyLrn = qtyLrn;
		this.qtyBrw = qtyBrw;
		this.qtyOnWay = qtyOnWay;
		this.qtyOnPrc = qtyOnPrc;
		this.qtyOnRsv = qtyOnRsv;
		this.qtyOnOdr = qtyOnOdr;
		this.lstOtd = lstOtd;
		this.lstInd = lstInd;
		this.lstSfd = lstSfd;
		this.upInt = upInt;
		this.cstInt = cstInt;
		this.cstStd = cstStd;
		this.cstPo = cstPo;
		this.cstSal = cstSal;
		this.upStd = upStd;
		this.upSal = upSal;
		this.qty1Lrn = qty1Lrn;
		this.qty1Brw = qty1Brw;
		this.upPo = upPo;
		this.qtySa = qtySa;
		this.qtyRk = qtyRk;
		this.qtyZg = qtyZg;
		this.qtyCk = qtyCk;
		this.qtySq = qtySq;
		this.rem = rem;
	}

	public Prdt1Id getId() {
		return this.id;
	}

	public void setId(Prdt1Id id) {
		this.id = id;
	}

	public BigDecimal getQtyInt() {
		return this.qtyInt;
	}

	public void setQtyInt(BigDecimal qtyInt) {
		this.qtyInt = qtyInt;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty1() {
		return this.qty1;
	}

	public void setQty1(BigDecimal qty1) {
		this.qty1 = qty1;
	}

	public BigDecimal getAmtCst() {
		return this.amtCst;
	}

	public void setAmtCst(BigDecimal amtCst) {
		this.amtCst = amtCst;
	}

	public BigDecimal getQtyMax() {
		return this.qtyMax;
	}

	public void setQtyMax(BigDecimal qtyMax) {
		this.qtyMax = qtyMax;
	}

	public BigDecimal getQtyMin() {
		return this.qtyMin;
	}

	public void setQtyMin(BigDecimal qtyMin) {
		this.qtyMin = qtyMin;
	}

	public BigDecimal getQtyLrn() {
		return this.qtyLrn;
	}

	public void setQtyLrn(BigDecimal qtyLrn) {
		this.qtyLrn = qtyLrn;
	}

	public BigDecimal getQtyBrw() {
		return this.qtyBrw;
	}

	public void setQtyBrw(BigDecimal qtyBrw) {
		this.qtyBrw = qtyBrw;
	}

	public BigDecimal getQtyOnWay() {
		return this.qtyOnWay;
	}

	public void setQtyOnWay(BigDecimal qtyOnWay) {
		this.qtyOnWay = qtyOnWay;
	}

	public BigDecimal getQtyOnPrc() {
		return this.qtyOnPrc;
	}

	public void setQtyOnPrc(BigDecimal qtyOnPrc) {
		this.qtyOnPrc = qtyOnPrc;
	}

	public BigDecimal getQtyOnRsv() {
		return this.qtyOnRsv;
	}

	public void setQtyOnRsv(BigDecimal qtyOnRsv) {
		this.qtyOnRsv = qtyOnRsv;
	}

	public BigDecimal getQtyOnOdr() {
		return this.qtyOnOdr;
	}

	public void setQtyOnOdr(BigDecimal qtyOnOdr) {
		this.qtyOnOdr = qtyOnOdr;
	}

	public Date getLstOtd() {
		return this.lstOtd;
	}

	public void setLstOtd(Date lstOtd) {
		this.lstOtd = lstOtd;
	}

	public Date getLstInd() {
		return this.lstInd;
	}

	public void setLstInd(Date lstInd) {
		this.lstInd = lstInd;
	}

	public Date getLstSfd() {
		return this.lstSfd;
	}

	public void setLstSfd(Date lstSfd) {
		this.lstSfd = lstSfd;
	}

	public BigDecimal getUpInt() {
		return this.upInt;
	}

	public void setUpInt(BigDecimal upInt) {
		this.upInt = upInt;
	}

	public BigDecimal getCstInt() {
		return this.cstInt;
	}

	public void setCstInt(BigDecimal cstInt) {
		this.cstInt = cstInt;
	}

	public BigDecimal getCstStd() {
		return this.cstStd;
	}

	public void setCstStd(BigDecimal cstStd) {
		this.cstStd = cstStd;
	}

	public BigDecimal getCstPo() {
		return this.cstPo;
	}

	public void setCstPo(BigDecimal cstPo) {
		this.cstPo = cstPo;
	}

	public BigDecimal getCstSal() {
		return this.cstSal;
	}

	public void setCstSal(BigDecimal cstSal) {
		this.cstSal = cstSal;
	}

	public BigDecimal getUpStd() {
		return this.upStd;
	}

	public void setUpStd(BigDecimal upStd) {
		this.upStd = upStd;
	}

	public BigDecimal getUpSal() {
		return this.upSal;
	}

	public void setUpSal(BigDecimal upSal) {
		this.upSal = upSal;
	}

	public BigDecimal getQty1Lrn() {
		return this.qty1Lrn;
	}

	public void setQty1Lrn(BigDecimal qty1Lrn) {
		this.qty1Lrn = qty1Lrn;
	}

	public BigDecimal getQty1Brw() {
		return this.qty1Brw;
	}

	public void setQty1Brw(BigDecimal qty1Brw) {
		this.qty1Brw = qty1Brw;
	}

	public BigDecimal getUpPo() {
		return this.upPo;
	}

	public void setUpPo(BigDecimal upPo) {
		this.upPo = upPo;
	}

	public BigDecimal getQtySa() {
		return this.qtySa;
	}

	public void setQtySa(BigDecimal qtySa) {
		this.qtySa = qtySa;
	}

	public BigDecimal getQtyRk() {
		return this.qtyRk;
	}

	public void setQtyRk(BigDecimal qtyRk) {
		this.qtyRk = qtyRk;
	}

	public BigDecimal getQtyZg() {
		return this.qtyZg;
	}

	public void setQtyZg(BigDecimal qtyZg) {
		this.qtyZg = qtyZg;
	}

	public BigDecimal getQtyCk() {
		return this.qtyCk;
	}

	public void setQtyCk(BigDecimal qtyCk) {
		this.qtyCk = qtyCk;
	}

	public BigDecimal getQtySq() {
		return this.qtySq;
	}

	public void setQtySq(BigDecimal qtySq) {
		this.qtySq = qtySq;
	}

	public String getRem() {
		return this.rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

}