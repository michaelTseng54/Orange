package com.ifbk.project.model;

// Generated 2014/4/22 下午 03:40:33 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TfPos generated by hbm2java
 */
@Entity
@Table(name="TF_POS")
public class TfPos implements java.io.Serializable {

	/**
	 */
	private static final long serialVersionUID = 7216063403791942986L;

	@EmbeddedId
	private TfPosId id;

//	private MfPos mfPos;
	
	@Column(name = "PRD_NO")
	private String prdNo;
	
	@Column(name = "PRD_NAME")
	private String prdName;
	
	@Column(name = "WH")
	private String wh;
	
	@Column(name = "UNIT")
	private String unit;

	@Column(name = "QTY")
	private BigDecimal qty;
	
	@Column(name = "UP")
	private BigDecimal up;
	
	@Column(name = "AMT")
	private BigDecimal amt;
	
	@Column(name = "AMTN")
	private BigDecimal amtn;

	@Column(name = "QTY_PS")
	private BigDecimal qtyPs;
	
	@Column(name = "QTY1_PS")
	private BigDecimal qty1Ps;

	@Column(name = "EST_DD")
	@Temporal(TemporalType.DATE) 
	private Date estDd;

	@Column(name = "PRE_EST_DD")
	@Temporal(TemporalType.DATE) 
	private Date preEstDd;

	@Column(name = "OS_DD")
	@Temporal(TemporalType.DATE) 
	private Date osDd;

	@Column(name = "TAX_RTO")
	private BigDecimal taxRto;

	@Column(name = "EST_ITM")
	private Integer estItm;

	@Column(name = "PRE_ITM")
	private Integer preItm;

	@Column(name = "OTH_ITM")
	private Integer othItm;

//	@Column(name = "QT_NO")
//	private String qtNo;
//
//	@Column(name = "PRD_MARK")
//	private String prdMark;
//	
//	@Column(name = "DIS_CNT")
//	private BigDecimal disCnt;
//	
//	@Column(name = "TAX")
//	private BigDecimal tax;
//	
//	@Column(name = "QTY1")
//	private BigDecimal qty1;
//	
//	@Column(name = "PAK_UNIT")
//	private String pakUnit;
//	
//	@Column(name = "PAK_EXC")
//	private BigDecimal pakExc;
//	
//	@Column(name = "PAK_NW")
//	private BigDecimal pakNw;
//	
//	@Column(name = "PAK_WEIGHT_UNIT")
//	private String pakWeightUnit;
//	
//	@Column(name = "PAK_GW")
//	private BigDecimal pakGw;
//	
//	@Column(name = "PAK_MEAST")
//	private BigDecimal pakMeast;
//	
//	@Column(name = "PAK_MEAST_UNIT")
//	private String pakMeastUnit;
//	
//	@Column(name = "VALID_DD")
//	@Temporal(TemporalType.DATE) 
//	private Date validDd;
//	
//	@Column(name = "REM")
//	private String rem;
//	
//	@Column(name = "AMTN_COM")
//	private BigDecimal amtnCom;
//	
//	@Column(name = "CLS_MP_ID")
//	private String clsMpId;
//	
//	@Column(name = "CST_STD")
//	private BigDecimal cstStd;
//	
//	@Column(name = "UP_QTY1")
//	private BigDecimal upQty1;
//	
//	@Column(name = "SEND_WH")
//	private String sendWh;
//	
//	@Column(name = "UT")
//	private String ut;
//	
//	@Column(name = "CODE_NO")
//	private String codeNo;
//	
//	@Column(name = "ATTR")
//	private String attr;
//	
//	@Column(name = "QTY_RK")
//	private BigDecimal qtyRk;
//	
//	@Column(name = "BAT_NO")
//	private String batNo;
//	
//	@Column(name = "QTY_PO")
//	private BigDecimal qtyPo;
//	
//	@Column(name = "QTY_PRE")
//	private BigDecimal qtyPre;
//	
//	@Column(name = "ID_NO")
//	private String idNo;
//	
//	@Column(name = "QTY_YS")
//	private BigDecimal qtyYs;
//	
//	@Column(name = "FREE_ID")
//	private String freeId;
//	
//	@Column(name = "BIL_ID")
//	private String bilId;
//	
//	@Column(name = "BZ_KND")
//	private String bzKnd;
//	
//	@Column(name = "PRICE_ID")
//	private String priceId;
//	
//	@Column(name = "AMT_LC")
//	private BigDecimal amtLc;
//	
//	@Column(name = "QTY_BACK")
//	private BigDecimal qtyBack;
//	
//	@Column(name = "CHK_TAX")
//	private String chkTax;
//	
//	@Column(name = "SUP_PRD_NO")
//	private String supPrdNo;
//	
//	@Column(name = "COMPOSE_IDNO")
//	private String composeIdno;
//	
//	@Column(name = "QTY_SL")
//	private BigDecimal qtySl;
//	
//	@Column(name = "CUS_OS_NO")
//	private String cusOsNo;
//	
//	@Column(name = "OTH_ID")
//	private String othId;
//	
//	@Column(name = "OTH_NO")
//	private String othNo;
//	
//	@Column(name = "ISVIR")
//	private String isvir;
//	
//	@Column(name = "JH_ID")
//	private String jhId;
//	
//	@Column(name = "QTY_FH")
//	private BigDecimal qtyFh;
//	
//	@Column(name = "PRD_NO_SO_RES")
//	private String prdNoSoRes;
//	
//	@Column(name = "FH_NO")
//	private String fhNo;
//	
//	@Column(name = "FH_DD")
//	@Temporal(TemporalType.DATE) 
//	private Date fhDd;
//	
//	@Column(name = "QTY_IC")
//	private BigDecimal qtyIc;
//	
//	@Column(name = "BOX_ITM")
//	private Integer boxItm;
//	
//	@Column(name = "QTY_ARK")
//	private BigDecimal qtyArk;
//	
//	@Column(name = "QTY_PS_ARK")
//	private BigDecimal qtyPsArk;
//	
//	@Column(name = "QTY_PRE_ARK")
//	private BigDecimal qtyPreArk;
//	
//	@Column(name = "QTY_CK_ARK")
//	private BigDecimal qtyCkArk;
//	
//	@Column(name = "MAI_NO")
//	private String maiNo;
//	
//	@Column(name = "SCM_USR")
//	private String scmUsr;
//	
//	@Column(name = "SCM_DD")
//	@Temporal(TemporalType.DATE) 
//	private Date scmDd;
//	
//	@Column(name = "QTY_SQ")
//	private BigDecimal qtySq;
//	@Column(name = "UP_PO")
//	private BigDecimal upPo;
//	
//	@Column(name = "SQ_ID")
//	private String sqId;
//	
//	@Column(name = "SQ_NO")
//	private String sqNo;
//	
//	@Column(name = "SQ_ITM")
//	private Integer sqItm;
//	
//	@Column(name = "QTY_FRAC1")
//	private BigDecimal qtyFrac1;
//	
//	@Column(name = "QTY_FRAC2")
//	private BigDecimal qtyFrac2;
//	
//	@Column(name = "QTY_FRAC3")
//	private BigDecimal qtyFrac3;
//	
//	@Column(name = "QTY_FRAC4")
//	private BigDecimal qtyFrac4;
//	
//	@Column(name = "QTY_USE_WH")
//	private BigDecimal qtyUseWh;
//	
//	@Column(name = "TRD_ID")
//	private String trdId;
//	
//	@Column(name = "TRD_NO")
//	private String trdNo;
//	
//	@Column(name = "TRD_ITM")
//	private Integer trdItm;
//	
//	@Column(name = "WC_NO")
//	private String wcNo;
//	
//	@Column(name = "PRD_NAME_SO_RES")
//	private String prdNameSoRes;
//	
//	@Column(name = "PICPATH")
//	private String picpath;
//	
//	@Column(name = "MTN_TYPE")
//	private String mtnType;
//	
//	@Column(name = "MTN_ALL_ID")
//	private String mtnAllId;
//	
//	@Column(name = "MTN_DD")
//	@Temporal(TemporalType.DATE) 
//	private Date mtnDd;
//	
//	@Column(name = "RTN_DD")
//	@Temporal(TemporalType.DATE) 
//	private Date rtnDd;
//	
//	@Column(name = "MTN_REM")
//	private String mtnRem;
//	
//	@Column(name = "CHK_RTN")
//	private String chkRtn;
//	
//	@Column(name = "MO_NO")
//	private String moNo;
//	
//	@Column(name = "MO_ID")
//	private String moId;
//	
//	@Column(name = "AMT_DIS_CNT")
//	private BigDecimal amtDisCnt;
//	
//	@Column(name = "QTY_PO_UNSH")
//	private BigDecimal qtyPoUnsh;

	public TfPos() {
	}

	public TfPos(TfPosId id) {
		this.id = id;
	}

	//	public TfPos(TfPosId id, MfPos mfPos) {
//		this.id = id;
//		this.mfPos = mfPos;
//	}

	public TfPos(TfPosId id, 
//			MfPos mfPos, 
			String qtNo, String prdNo,
			String prdName, String prdMark, String wh, String unit, Date osDd,
			BigDecimal qty, BigDecimal up, BigDecimal disCnt, BigDecimal amt,
			BigDecimal amtn, BigDecimal tax, BigDecimal qty1, BigDecimal qtyPs,
			BigDecimal qty1Ps, Date estDd, String pakUnit, BigDecimal pakExc,
			BigDecimal pakNw, String pakWeightUnit, BigDecimal pakGw,
			BigDecimal pakMeast, String pakMeastUnit, Date validDd, String rem,
			BigDecimal amtnCom, String clsMpId, BigDecimal cstStd,
			BigDecimal upQty1, Integer estItm, String sendWh, String ut,
			String codeNo, String attr, BigDecimal qtyRk, String batNo,
			Date preEstDd, BigDecimal qtyPo, BigDecimal qtyPre, Integer preItm,
			String idNo, BigDecimal qtyYs, String freeId, String bilId,
			String bzKnd, String priceId, Integer othItm, BigDecimal taxRto,
			BigDecimal amtLc, BigDecimal qtyBack, String chkTax,
			String supPrdNo, String composeIdno, BigDecimal qtySl,
			String cusOsNo, String othId, String othNo, String isvir,
			String jhId, BigDecimal qtyFh, String prdNoSoRes, String fhNo,
			Date fhDd, BigDecimal qtyIc, Integer boxItm, BigDecimal qtyArk,
			BigDecimal qtyPsArk, BigDecimal qtyPreArk, BigDecimal qtyCkArk,
			String maiNo, String scmUsr, Date scmDd, BigDecimal qtySq,
			BigDecimal upPo, String sqId, String sqNo, Integer sqItm,
			BigDecimal qtyFrac1, BigDecimal qtyFrac2, BigDecimal qtyFrac3,
			BigDecimal qtyFrac4, BigDecimal qtyUseWh, String trdId,
			String trdNo, Integer trdItm, String wcNo, String prdNameSoRes,
			String picpath, String mtnType, String mtnAllId, Date mtnDd,
			Date rtnDd, String mtnRem, String chkRtn, String moNo, String moId,
			BigDecimal amtDisCnt, BigDecimal qtyPoUnsh) {
		this.id = id;
//		this.mfPos = mfPos;
//		this.qtNo = qtNo;
		this.prdNo = prdNo;
		this.prdName = prdName;
//		this.prdMark = prdMark;
		this.wh = wh;
		this.unit = unit;
		this.osDd = osDd;
		this.qty = qty;
		this.up = up;
//		this.disCnt = disCnt;
		this.amt = amt;
		this.amtn = amtn;
//		this.tax = tax;
//		this.qty1 = qty1;
		this.qtyPs = qtyPs;
		this.qty1Ps = qty1Ps;
		this.estDd = estDd;
//		this.pakUnit = pakUnit;
//		this.pakExc = pakExc;
//		this.pakNw = pakNw;
//		this.pakWeightUnit = pakWeightUnit;
//		this.pakGw = pakGw;
//		this.pakMeast = pakMeast;
//		this.pakMeastUnit = pakMeastUnit;
//		this.validDd = validDd;
//		this.rem = rem;
//		this.amtnCom = amtnCom;
//		this.clsMpId = clsMpId;
//		this.cstStd = cstStd;
//		this.upQty1 = upQty1;
		this.estItm = estItm;
//		this.sendWh = sendWh;
//		this.ut = ut;
//		this.codeNo = codeNo;
//		this.attr = attr;
//		this.qtyRk = qtyRk;
//		this.batNo = batNo;
		this.preEstDd = preEstDd;
//		this.qtyPo = qtyPo;
//		this.qtyPre = qtyPre;
		this.preItm = preItm;
//		this.idNo = idNo;
//		this.qtyYs = qtyYs;
//		this.freeId = freeId;
//		this.bilId = bilId;
//		this.bzKnd = bzKnd;
//		this.priceId = priceId;
		this.othItm = othItm;
		this.taxRto = taxRto;
//		this.amtLc = amtLc;
//		this.qtyBack = qtyBack;
//		this.chkTax = chkTax;
//		this.supPrdNo = supPrdNo;
//		this.composeIdno = composeIdno;
//		this.qtySl = qtySl;
//		this.cusOsNo = cusOsNo;
//		this.othId = othId;
//		this.othNo = othNo;
//		this.isvir = isvir;
//		this.jhId = jhId;
//		this.qtyFh = qtyFh;
//		this.prdNoSoRes = prdNoSoRes;
//		this.fhNo = fhNo;
//		this.fhDd = fhDd;
//		this.qtyIc = qtyIc;
//		this.boxItm = boxItm;
//		this.qtyArk = qtyArk;
//		this.qtyPsArk = qtyPsArk;
//		this.qtyPreArk = qtyPreArk;
//		this.qtyCkArk = qtyCkArk;
//		this.maiNo = maiNo;
//		this.scmUsr = scmUsr;
//		this.scmDd = scmDd;
//		this.qtySq = qtySq;
//		this.upPo = upPo;
//		this.sqId = sqId;
//		this.sqNo = sqNo;
//		this.sqItm = sqItm;
//		this.qtyFrac1 = qtyFrac1;
//		this.qtyFrac2 = qtyFrac2;
//		this.qtyFrac3 = qtyFrac3;
//		this.qtyFrac4 = qtyFrac4;
//		this.qtyUseWh = qtyUseWh;
//		this.trdId = trdId;
//		this.trdNo = trdNo;
//		this.trdItm = trdItm;
//		this.wcNo = wcNo;
//		this.prdNameSoRes = prdNameSoRes;
//		this.picpath = picpath;
//		this.mtnType = mtnType;
//		this.mtnAllId = mtnAllId;
//		this.mtnDd = mtnDd;
//		this.rtnDd = rtnDd;
//		this.mtnRem = mtnRem;
//		this.chkRtn = chkRtn;
//		this.moNo = moNo;
//		this.moId = moId;
//		this.amtDisCnt = amtDisCnt;
//		this.qtyPoUnsh = qtyPoUnsh;
	}

	public TfPosId getId() {
		return this.id;
	}

	public void setId(TfPosId id) {
		this.id = id;
	}

//	public MfPos getMfPos() {
//		return this.mfPos;
//	}
//
//	public void setMfPos(MfPos mfPos) {
//		this.mfPos = mfPos;
//	}

//	public String getQtNo() {
//		return this.qtNo;
//	}
//
//	public void setQtNo(String qtNo) {
//		this.qtNo = qtNo;
//	}

	public String getPrdNo() {
		return this.prdNo;
	}

	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}

	public String getPrdName() {
		return this.prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

//	public String getPrdMark() {
//		return this.prdMark;
//	}
//
//	public void setPrdMark(String prdMark) {
//		this.prdMark = prdMark;
//	}

	public String getWh() {
		return this.wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getOsDd() {
		return this.osDd;
	}

	public void setOsDd(Date osDd) {
		this.osDd = osDd;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getUp() {
		return this.up;
	}

	public void setUp(BigDecimal up) {
		this.up = up;
	}

//	public BigDecimal getDisCnt() {
//		return this.disCnt;
//	}
//
//	public void setDisCnt(BigDecimal disCnt) {
//		this.disCnt = disCnt;
//	}

	public BigDecimal getAmt() {
		return this.amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getAmtn() {
		return this.amtn;
	}

	public void setAmtn(BigDecimal amtn) {
		this.amtn = amtn;
	}

//	public BigDecimal getTax() {
//		return this.tax;
//	}
//
//	public void setTax(BigDecimal tax) {
//		this.tax = tax;
//	}
//
//	public BigDecimal getQty1() {
//		return this.qty1;
//	}
//
//	public void setQty1(BigDecimal qty1) {
//		this.qty1 = qty1;
//	}

	public BigDecimal getQtyPs() {
		return this.qtyPs;
	}

	public void setQtyPs(BigDecimal qtyPs) {
		this.qtyPs = qtyPs;
	}

	public BigDecimal getQty1Ps() {
		return this.qty1Ps;
	}

	public void setQty1Ps(BigDecimal qty1Ps) {
		this.qty1Ps = qty1Ps;
	}

	public Date getEstDd() {
		return this.estDd;
	}

	public void setEstDd(Date estDd) {
		this.estDd = estDd;
	}

//	public String getPakUnit() {
//		return this.pakUnit;
//	}
//
//	public void setPakUnit(String pakUnit) {
//		this.pakUnit = pakUnit;
//	}
//
//	public BigDecimal getPakExc() {
//		return this.pakExc;
//	}
//
//	public void setPakExc(BigDecimal pakExc) {
//		this.pakExc = pakExc;
//	}
//
//	public BigDecimal getPakNw() {
//		return this.pakNw;
//	}
//
//	public void setPakNw(BigDecimal pakNw) {
//		this.pakNw = pakNw;
//	}
//
//	public String getPakWeightUnit() {
//		return this.pakWeightUnit;
//	}
//
//	public void setPakWeightUnit(String pakWeightUnit) {
//		this.pakWeightUnit = pakWeightUnit;
//	}
//
//	public BigDecimal getPakGw() {
//		return this.pakGw;
//	}
//
//	public void setPakGw(BigDecimal pakGw) {
//		this.pakGw = pakGw;
//	}
//
//	public BigDecimal getPakMeast() {
//		return this.pakMeast;
//	}
//
//	public void setPakMeast(BigDecimal pakMeast) {
//		this.pakMeast = pakMeast;
//	}
//
//	public String getPakMeastUnit() {
//		return this.pakMeastUnit;
//	}
//
//	public void setPakMeastUnit(String pakMeastUnit) {
//		this.pakMeastUnit = pakMeastUnit;
//	}
//
//	public Date getValidDd() {
//		return this.validDd;
//	}
//
//	public void setValidDd(Date validDd) {
//		this.validDd = validDd;
//	}
//
//	public String getRem() {
//		return this.rem;
//	}
//
//	public void setRem(String rem) {
//		this.rem = rem;
//	}
//
//	public BigDecimal getAmtnCom() {
//		return this.amtnCom;
//	}
//
//	public void setAmtnCom(BigDecimal amtnCom) {
//		this.amtnCom = amtnCom;
//	}
//
//	public String getClsMpId() {
//		return this.clsMpId;
//	}
//
//	public void setClsMpId(String clsMpId) {
//		this.clsMpId = clsMpId;
//	}
//
//	public BigDecimal getCstStd() {
//		return this.cstStd;
//	}
//
//	public void setCstStd(BigDecimal cstStd) {
//		this.cstStd = cstStd;
//	}
//
//	public BigDecimal getUpQty1() {
//		return this.upQty1;
//	}
//
//	public void setUpQty1(BigDecimal upQty1) {
//		this.upQty1 = upQty1;
//	}

	public Integer getEstItm() {
		return this.estItm;
	}

	public void setEstItm(Integer estItm) {
		this.estItm = estItm;
	}

//	public String getSendWh() {
//		return this.sendWh;
//	}
//
//	public void setSendWh(String sendWh) {
//		this.sendWh = sendWh;
//	}
//
//	public String getUt() {
//		return this.ut;
//	}
//
//	public void setUt(String ut) {
//		this.ut = ut;
//	}

//	public String getCodeNo() {
//		return this.codeNo;
//	}
//
//	public void setCodeNo(String codeNo) {
//		this.codeNo = codeNo;
//	}
//
//	public String getAttr() {
//		return this.attr;
//	}
//
//	public void setAttr(String attr) {
//		this.attr = attr;
//	}
//
//	public BigDecimal getQtyRk() {
//		return this.qtyRk;
//	}
//
//	public void setQtyRk(BigDecimal qtyRk) {
//		this.qtyRk = qtyRk;
//	}
//
//	public String getBatNo() {
//		return this.batNo;
//	}
//
//	public void setBatNo(String batNo) {
//		this.batNo = batNo;
//	}

	public Date getPreEstDd() {
		return this.preEstDd;
	}

	public void setPreEstDd(Date preEstDd) {
		this.preEstDd = preEstDd;
	}

//	public BigDecimal getQtyPo() {
//		return this.qtyPo;
//	}
//
//	public void setQtyPo(BigDecimal qtyPo) {
//		this.qtyPo = qtyPo;
//	}
//
//	public BigDecimal getQtyPre() {
//		return this.qtyPre;
//	}
//
//	public void setQtyPre(BigDecimal qtyPre) {
//		this.qtyPre = qtyPre;
//	}

	public Integer getPreItm() {
		return this.preItm;
	}

	public void setPreItm(Integer preItm) {
		this.preItm = preItm;
	}

//	public String getIdNo() {
//		return this.idNo;
//	}
//
//	public void setIdNo(String idNo) {
//		this.idNo = idNo;
//	}
//
//	public BigDecimal getQtyYs() {
//		return this.qtyYs;
//	}
//
//	public void setQtyYs(BigDecimal qtyYs) {
//		this.qtyYs = qtyYs;
//	}
//
//	public String getFreeId() {
//		return this.freeId;
//	}
//
//	public void setFreeId(String freeId) {
//		this.freeId = freeId;
//	}
//
//	public String getBilId() {
//		return this.bilId;
//	}
//
//	public void setBilId(String bilId) {
//		this.bilId = bilId;
//	}
//
//	public String getBzKnd() {
//		return this.bzKnd;
//	}
//
//	public void setBzKnd(String bzKnd) {
//		this.bzKnd = bzKnd;
//	}
//
//	public String getPriceId() {
//		return this.priceId;
//	}
//
//	public void setPriceId(String priceId) {
//		this.priceId = priceId;
//	}
//
	public Integer getOthItm() {
		return this.othItm;
	}

	public void setOthItm(Integer othItm) {
		this.othItm = othItm;
	}

	public BigDecimal getTaxRto() {
		return this.taxRto;
	}

	public void setTaxRto(BigDecimal taxRto) {
		this.taxRto = taxRto;
	}

//	public BigDecimal getAmtLc() {
//		return this.amtLc;
//	}
//
//	public void setAmtLc(BigDecimal amtLc) {
//		this.amtLc = amtLc;
//	}
//
//	public BigDecimal getQtyBack() {
//		return this.qtyBack;
//	}
//
//	public void setQtyBack(BigDecimal qtyBack) {
//		this.qtyBack = qtyBack;
//	}
//
//	public String getChkTax() {
//		return this.chkTax;
//	}
//
//	public void setChkTax(String chkTax) {
//		this.chkTax = chkTax;
//	}
//
//	public String getSupPrdNo() {
//		return this.supPrdNo;
//	}
//
//	public void setSupPrdNo(String supPrdNo) {
//		this.supPrdNo = supPrdNo;
//	}
//
//	public String getComposeIdno() {
//		return this.composeIdno;
//	}
//
//	public void setComposeIdno(String composeIdno) {
//		this.composeIdno = composeIdno;
//	}
//
//	public BigDecimal getQtySl() {
//		return this.qtySl;
//	}
//
//	public void setQtySl(BigDecimal qtySl) {
//		this.qtySl = qtySl;
//	}
//
//	public String getCusOsNo() {
//		return this.cusOsNo;
//	}
//
//	public void setCusOsNo(String cusOsNo) {
//		this.cusOsNo = cusOsNo;
//	}
//
//	public String getOthId() {
//		return this.othId;
//	}
//
//	public void setOthId(String othId) {
//		this.othId = othId;
//	}
//
//	public String getOthNo() {
//		return this.othNo;
//	}
//
//	public void setOthNo(String othNo) {
//		this.othNo = othNo;
//	}
//
//	public String getIsvir() {
//		return this.isvir;
//	}
//
//	public void setIsvir(String isvir) {
//		this.isvir = isvir;
//	}
//
//	public String getJhId() {
//		return this.jhId;
//	}
//
//	public void setJhId(String jhId) {
//		this.jhId = jhId;
//	}
//
//	public BigDecimal getQtyFh() {
//		return this.qtyFh;
//	}
//
//	public void setQtyFh(BigDecimal qtyFh) {
//		this.qtyFh = qtyFh;
//	}
//
//	public String getPrdNoSoRes() {
//		return this.prdNoSoRes;
//	}
//
//	public void setPrdNoSoRes(String prdNoSoRes) {
//		this.prdNoSoRes = prdNoSoRes;
//	}
//
//	public String getFhNo() {
//		return this.fhNo;
//	}
//
//	public void setFhNo(String fhNo) {
//		this.fhNo = fhNo;
//	}
//
//	public Date getFhDd() {
//		return this.fhDd;
//	}
//
//	public void setFhDd(Date fhDd) {
//		this.fhDd = fhDd;
//	}
//
//	public BigDecimal getQtyIc() {
//		return this.qtyIc;
//	}
//
//	public void setQtyIc(BigDecimal qtyIc) {
//		this.qtyIc = qtyIc;
//	}
//
//	public Integer getBoxItm() {
//		return this.boxItm;
//	}
//
//	public void setBoxItm(Integer boxItm) {
//		this.boxItm = boxItm;
//	}
//
//	public BigDecimal getQtyArk() {
//		return this.qtyArk;
//	}
//
//	public void setQtyArk(BigDecimal qtyArk) {
//		this.qtyArk = qtyArk;
//	}
//
//	public BigDecimal getQtyPsArk() {
//		return this.qtyPsArk;
//	}
//
//	public void setQtyPsArk(BigDecimal qtyPsArk) {
//		this.qtyPsArk = qtyPsArk;
//	}
//
//	public BigDecimal getQtyPreArk() {
//		return this.qtyPreArk;
//	}
//
//	public void setQtyPreArk(BigDecimal qtyPreArk) {
//		this.qtyPreArk = qtyPreArk;
//	}
//
//	public BigDecimal getQtyCkArk() {
//		return this.qtyCkArk;
//	}
//
//	public void setQtyCkArk(BigDecimal qtyCkArk) {
//		this.qtyCkArk = qtyCkArk;
//	}
//
//	public String getMaiNo() {
//		return this.maiNo;
//	}
//
//	public void setMaiNo(String maiNo) {
//		this.maiNo = maiNo;
//	}
//
//	public String getScmUsr() {
//		return this.scmUsr;
//	}
//
//	public void setScmUsr(String scmUsr) {
//		this.scmUsr = scmUsr;
//	}
//
//	public Date getScmDd() {
//		return this.scmDd;
//	}
//
//	public void setScmDd(Date scmDd) {
//		this.scmDd = scmDd;
//	}
//
//	public BigDecimal getQtySq() {
//		return this.qtySq;
//	}
//
//	public void setQtySq(BigDecimal qtySq) {
//		this.qtySq = qtySq;
//	}
//
//	public BigDecimal getUpPo() {
//		return this.upPo;
//	}
//
//	public void setUpPo(BigDecimal upPo) {
//		this.upPo = upPo;
//	}
//
//	public String getSqId() {
//		return this.sqId;
//	}
//
//	public void setSqId(String sqId) {
//		this.sqId = sqId;
//	}
//
//	public String getSqNo() {
//		return this.sqNo;
//	}
//
//	public void setSqNo(String sqNo) {
//		this.sqNo = sqNo;
//	}
//
//	public Integer getSqItm() {
//		return this.sqItm;
//	}
//
//	public void setSqItm(Integer sqItm) {
//		this.sqItm = sqItm;
//	}
//
//	public BigDecimal getQtyFrac1() {
//		return this.qtyFrac1;
//	}
//
//	public void setQtyFrac1(BigDecimal qtyFrac1) {
//		this.qtyFrac1 = qtyFrac1;
//	}
//
//	public BigDecimal getQtyFrac2() {
//		return this.qtyFrac2;
//	}
//
//	public void setQtyFrac2(BigDecimal qtyFrac2) {
//		this.qtyFrac2 = qtyFrac2;
//	}
//
//	public BigDecimal getQtyFrac3() {
//		return this.qtyFrac3;
//	}
//
//	public void setQtyFrac3(BigDecimal qtyFrac3) {
//		this.qtyFrac3 = qtyFrac3;
//	}
//
//	public BigDecimal getQtyFrac4() {
//		return this.qtyFrac4;
//	}
//
//	public void setQtyFrac4(BigDecimal qtyFrac4) {
//		this.qtyFrac4 = qtyFrac4;
//	}
//
//	public BigDecimal getQtyUseWh() {
//		return this.qtyUseWh;
//	}
//
//	public void setQtyUseWh(BigDecimal qtyUseWh) {
//		this.qtyUseWh = qtyUseWh;
//	}
//
//	public String getTrdId() {
//		return this.trdId;
//	}
//
//	public void setTrdId(String trdId) {
//		this.trdId = trdId;
//	}
//
//	public String getTrdNo() {
//		return this.trdNo;
//	}
//
//	public void setTrdNo(String trdNo) {
//		this.trdNo = trdNo;
//	}
//
//	public Integer getTrdItm() {
//		return this.trdItm;
//	}
//
//	public void setTrdItm(Integer trdItm) {
//		this.trdItm = trdItm;
//	}
//
//	public String getWcNo() {
//		return this.wcNo;
//	}
//
//	public void setWcNo(String wcNo) {
//		this.wcNo = wcNo;
//	}
//
//	public String getPrdNameSoRes() {
//		return this.prdNameSoRes;
//	}
//
//	public void setPrdNameSoRes(String prdNameSoRes) {
//		this.prdNameSoRes = prdNameSoRes;
//	}
//
//	public String getPicpath() {
//		return this.picpath;
//	}
//
//	public void setPicpath(String picpath) {
//		this.picpath = picpath;
//	}
//
//	public String getMtnType() {
//		return this.mtnType;
//	}
//
//	public void setMtnType(String mtnType) {
//		this.mtnType = mtnType;
//	}
//
//	public String getMtnAllId() {
//		return this.mtnAllId;
//	}
//
//	public void setMtnAllId(String mtnAllId) {
//		this.mtnAllId = mtnAllId;
//	}
//
//	public Date getMtnDd() {
//		return this.mtnDd;
//	}
//
//	public void setMtnDd(Date mtnDd) {
//		this.mtnDd = mtnDd;
//	}
//
//	public Date getRtnDd() {
//		return this.rtnDd;
//	}
//
//	public void setRtnDd(Date rtnDd) {
//		this.rtnDd = rtnDd;
//	}
//
//	public String getMtnRem() {
//		return this.mtnRem;
//	}
//
//	public void setMtnRem(String mtnRem) {
//		this.mtnRem = mtnRem;
//	}
//
//	public String getChkRtn() {
//		return this.chkRtn;
//	}
//
//	public void setChkRtn(String chkRtn) {
//		this.chkRtn = chkRtn;
//	}
//
//	public String getMoNo() {
//		return this.moNo;
//	}
//
//	public void setMoNo(String moNo) {
//		this.moNo = moNo;
//	}
//
//	public String getMoId() {
//		return this.moId;
//	}
//
//	public void setMoId(String moId) {
//		this.moId = moId;
//	}
//
//	public BigDecimal getAmtDisCnt() {
//		return this.amtDisCnt;
//	}
//
//	public void setAmtDisCnt(BigDecimal amtDisCnt) {
//		this.amtDisCnt = amtDisCnt;
//	}
//
//	public BigDecimal getQtyPoUnsh() {
//		return this.qtyPoUnsh;
//	}
//
//	public void setQtyPoUnsh(BigDecimal qtyPoUnsh) {
//		this.qtyPoUnsh = qtyPoUnsh;
//	}

}