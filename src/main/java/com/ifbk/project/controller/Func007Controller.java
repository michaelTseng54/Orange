package com.ifbk.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ifbk.project.manager.InsertService;
import com.ifbk.project.manager.ModifyService;
import com.ifbk.project.manager.QueryService;
import com.ifbk.project.model.Biln1;
import com.ifbk.project.model.CurId;
import com.ifbk.project.model.FileObject;
import com.ifbk.project.model.MfIj;
import com.ifbk.project.model.MfIjId;
import com.ifbk.project.model.MfPos;
import com.ifbk.project.model.MfPosId;
import com.ifbk.project.model.MyWh;
import com.ifbk.project.model.Prdt;
import com.ifbk.project.model.Prdt1;
import com.ifbk.project.model.Prdt1Id;
import com.ifbk.project.model.PrdtCus1;
import com.ifbk.project.model.PrdtCus1Id;
import com.ifbk.project.model.TfIj;
import com.ifbk.project.model.TfIjId;
import com.ifbk.project.model.TfPos;
import com.ifbk.project.model.TfPosId;
import com.ifbk.project.utils.FileValidator;
import com.ifbk.project.utils.Utils;

@Controller
@RequestMapping("/FUNC007")
public class Func007Controller {

	private static final Logger logger = Logger.getLogger(Func007Controller.class);

	@Autowired
	protected InsertService insertService;

	@Autowired
	protected QueryService queryService;
	
	@Autowired
	protected ModifyService modifyService;

	@Autowired
	FileValidator fileValidator; 

	public final static Utils utils = new Utils();

	public Func007Controller() {}

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(HttpSession session) {
		return utils.userLoginStatus(session, this.getClass().getName());
	}
	
	/**
	 * Query Object
	 * @param session HttpSession
	 * @param response HttpServletResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/query")
	public void query(HttpSession session, HttpServletResponse response,
		@RequestParam(value = "test", required = false) String test) {
		insertService.txTestStoreProcedure("SelectMyWh");	
		//TODO

	}

	/**
	 * Update Object
	 * @param session HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView updateDataPost(HttpSession session,
		@RequestParam(value = "test", required = false) String test) {
		return new ModelAndView("/front/Function007");
	}
	
	/**
	 * Insert Object
	 * @param session HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ModelAndView addDataPost(HttpSession session,
		@RequestParam(value = "test", required = false) String test) {
		return new ModelAndView("/front/Function007");
	}

	/**
	 * Delete Object
	 * @param session HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/delete")
	public ModelAndView deleteDataPost(HttpSession session,
		@RequestParam(value = "test", required = false) String test) {
		return new ModelAndView("/front/Function007");
	}	

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST, value = "/fileUpload")
	public ModelAndView fileUploaded(HttpSession session,
			@RequestParam(value = "curId", required = false) String curId,
			@ModelAttribute("uploadedFile") FileObject uploadedFile,
			BindingResult result) throws IOException, ParseException {
		
		String place = "";
		if (utils.isMac()) {
			place = "/Users/swallow/applications/tmp/";
		} else if (utils.isWindows()) {
			place = "D:/ProjectDocumentFolder/";
		}

		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);
		String fileName = file.getOriginalFilename();

		if (result.hasErrors()) {
			return new ModelAndView("/front/Function007");
		}

		try {
			inputStream = file.getInputStream();
			File newFile = new File(place + fileName);  //擺放的檔案位置
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean bool = false;
		try {
			bool = excelParse(new File(place + fileName), session, curId);
		} catch (Exception e) {
			return new ModelAndView("/front/Main", "message", "Exception happened.");
		}
		
		if (bool) {
			return new ModelAndView("/front/Main", "message", "process success!");
		} else {
			return new ModelAndView("/front/Main", "message", "process false!");
		}
	}
	
	public boolean excelParse(File excel, HttpSession session, String curId) throws Exception {
		boolean flag = true;
		if(excel.exists()) {
	        try {
                FileInputStream fis = new FileInputStream(excel);	
	            Workbook workbook = null;							
	            if (excel.toString().toLowerCase().endsWith("xlsx")) {
	                workbook = new XSSFWorkbook(fis);
	                XSSFCell cell;
	                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		            List<Map<String, String>> listObj = new ArrayList<Map<String, String>>();
	                
		            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
	                	Map<String, String> mapObj = new HashMap<String, String>();
	                	XSSFRow row = sheet.getRow(i); // 取得第 i Row
	                	loop:
	                	for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
	                		cell = row.getCell(j);
	                		if (j == 0 || j == 8) {
	                			try {
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString().replace(".0", ""));
	                			} catch (Exception e) {
	                				continue loop;
	                			}
	                		} else if (j == 1) {
                				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                				mapObj.put(j + "", cell.toString());

	                		} else if (j == 7) {
	                			try {
	                				SimpleDateFormat sdf= new SimpleDateFormat("yyyy/M/dd");
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : sdf.format(new Date(cell.getDateCellValue().getTime())));
	                			} catch (Exception e) {
	                				System.out.println("date parse format exception " + e.getMessage());
	                			}
	                		} else {
	                			try {
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString());
	                			} catch (Exception e) {
	                				System.out.println("parse others error : " + e.getMessage());
	                			}
	                		}
	                	}
	                	listObj.add(mapObj);
	                }
//		            insertDataTest(listObj, session, curId);
		            insertData(listObj, session, curId);
	            	//TODO HERE
	            	
	            } else if (excel.toString().toLowerCase().endsWith("xls")) {
	                workbook = new HSSFWorkbook(fis);
	                HSSFCell cell;
	                HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
	                List<Map<String, String>> listObj = new ArrayList<Map<String, String>>();
	                
	                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
	                	Map<String, String> mapObj = new HashMap<String, String>();
	                	HSSFRow row = sheet.getRow(i); // 取得第 i Row
	                	for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
	                		cell = row.getCell(j);
	                		if (j == 0 || j == 8) {
	                			try {
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString().replace(".0", ""));
	                			} catch (Exception e) {
	                				System.out.println("0 or 8 parse exception : " + e.getMessage());
	                			}
	                		} else if (j == 7) {
	                			try {
	                				SimpleDateFormat sdf= new SimpleDateFormat("yyyy/M/dd");
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : sdf.format(new Date(cell.getDateCellValue().getTime())));
	                			} catch (Exception e) {
	                				System.out.println("date parse format exception " + e.getMessage());
	                			}
	                		} else {
	                			try {
	                				mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString());
	                			} catch (Exception e) {
	                				System.out.println("parse others error : " + e.getMessage());
	                			}
	                		}
	                	}
	                	System.out.println("PARSE OBJ DONE.");
	                	listObj.add(mapObj);
	                }
	            	insertDataTest(listObj, session, curId);
	            }
	            fis.close(); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} else {
			flag = false;
		}
		
		return flag;
	}
	public boolean excelParse2(File excel, HttpSession session, String curId) {
		boolean flag = true;
		if(excel.exists()) {
			try {
				FileInputStream fis = new FileInputStream(excel);	
				Workbook workbook = null;							
				if (excel.toString().toLowerCase().endsWith("xlsx")) {
					workbook = new XSSFWorkbook(fis);
					XSSFCell cell;
					XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
					List<Map<String, String>> listObj = new ArrayList<Map<String, String>>();
					
					for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
						Map<String, String> mapObj = new HashMap<String, String>();
						XSSFRow row = sheet.getRow(i); // 取得第 i Row
						for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
							cell = row.getCell(j);
							System.out.println(j + ", " + cell);
		                		if (j == 0) {
		                			try {
			                			DecimalFormat df = new DecimalFormat("0");  
										mapObj.put(j + "", df.format(cell.getNumericCellValue()));
									} catch (Exception e) {
										System.out.println("1, parse excel exeption : " + e.getMessage());
									}
		                		} else if (j == 3) {
		                			try {
		                				mapObj.put(j + "", cell.toString());
									} catch (Exception e) {
										System.out.println("4, parse excel exeption : " + e.getMessage());
									}

		                		} else if (j == 5) {
		                			try {
//		                				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		                				mapObj.put(j + "", cell.toString());
									} catch (Exception e) {
										System.out.println("2, parse excel exeption : " + e.getMessage());
									}
		                		} else if (j == 7) {
									mapObj.put(j + "", returnDateString(cell.toString()));

		                		} else if (j == 8) {
		                			try {
		                				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		                				mapObj.put(j + "", cell.getStringCellValue().replace(".0", ""));
									} catch (Exception e) {
										System.out.println("3, parse excel exeption : " + e.getMessage());
									}
		                		} else {
		                			try {
		                				mapObj.put(j + "", cell.toString());
									} catch (Exception e) {
										System.out.println("4, parse excel exeption : " + e.getMessage());
									}
		                		}								
							
//							if (j == 0 || j == 8) {
//								mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString().replace(".0", ""));
//							} else if (j == 7) {
//								SimpleDateFormat sdf= new SimpleDateFormat("yyyy/M/dd");
//								mapObj.put(j + "", cell.getCellType() == 3 ? null : sdf.format(new Date(cell.getDateCellValue().getTime())));
//							} else {
//								mapObj.put(j + "", cell.getCellType() == 3 ? null : cell.toString());
//							}
						}
						listObj.add(mapObj);
					}
//					insertData(listObj, session, curId);
				} else if (excel.toString().toLowerCase().endsWith("xls")) {
					workbook = new HSSFWorkbook(fis);
					HSSFCell cell;
					HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
					List<Map<String, String>> listObj = new ArrayList<Map<String, String>>();
					
					for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
						Map<String, String> mapObj = new HashMap<String, String>();
						HSSFRow row = sheet.getRow(i); // 取得第 i Row
						for (int j = 0; j < 17; j++) {	//寫死
							String cellString = "";
							cell = row.getCell(j);
							if (j == 0) {
                				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							}
							if (j == 8 || j == 9) { 
								cellString = (Math.ceil(cell.getNumericCellValue()) + "").replace(".0", "");
							} else {
								cellString = cell == null 
										|| (cell + "").length() == 0 ? "Nul" : (cell + "").replace(".0", "");
							}
							mapObj.put(j + "", cellString);

						}
						listObj.add(mapObj);
					}
					try {
						insertDataTest(listObj, session, curId);
					} catch (Exception e) {
						System.out.println("INSERT DATA2 EXCEPTION " + e.getMessage());
					}
				}
				fis.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			flag = false;
		}
		
		return flag;
	}
	
	public void insertDataTest(List<Map<String, String>> obj, HttpSession session, String cur) throws Exception {
		System.out.println("# enter insertDataTest # " + obj.size());
		for (int i = 0; i < obj.size(); i ++) {
			try {
				String prodNo = obj.get(i).get("" + 1);			//產品編號		v
				System.out.println("prodNo : " + prodNo);
			} catch (Exception e) {
				System.out.println("EXCEPTION : " + e.getMessage());
			}
		}
	}
	public void insertData2(List<Map<String, String>> obj, HttpSession session, String cur) throws Exception {
//		for (int i = 0; i < obj.size(); i ++) {
//			String qtyStore = obj.get(i).get("" + 12);		//盤點庫存
//			if (!qtyStore.equals("0")) {
//				String prodNo = obj.get(i).get("" + 0);			//產品編號		
//				String qtyMin = obj.get(i).get("" + 13);		//安全存量
//				String wh = obj.get(i).get("" + 15);			//儲位
//				System.out.println("#prodNo : " + prodNo);
//				System.out.println("#qtyMin : " + qtyMin);
//				System.out.println("#wh : " + wh);
//				try {
//					Prdt1 prdt1 = new Prdt1();
//					Prdt1Id prdt1Id = new Prdt1Id();
//					prdt1Id.setPrdNo(prodNo);
//					prdt1Id.setWh(wh);
//					prdt1Id.setPrdMark("");
//					prdt1.setQtyMin(new BigDecimal(qtyMin));
//					prdt1.setId(prdt1Id);
//					modifyService.txUpdate(prdt1);
//				} catch (Exception e) {
//					System.out.println("failed : " + prodNo + " / " + wh + " / " + new BigDecimal(qtyMin));
//					System.out.println("UPDATE prdt1 EXCEPTION : " + e.getMessage());
//				}
//			}
//		}
		
//		Date today = now();
//		boolean boo = true;
//
//		String patPk = "IJ" + nowToRocYear() ;
//		Biln1 biln1 = (Biln1) queryService.selectById("Biln1SelectById", patPk + "%");
//		int t = 0;
//		if (biln1 == null) {
//			t = 1;
//			Biln1 biln1Bean = new Biln1();
//			biln1Bean.setPat(patPk + "NNNN");
//			biln1Bean.setSq(t);
//			insertService.txInsertEntity(biln1Bean);
//		} else {
//			t = biln1.getSq();
//		}
//		
//		String x = t + "";
//		if (x.length() == 1) {
//			x = "000" + x;
//		} else if (x.length() == 2) {
//			x = "00" + x;
//		} else if (x.length() == 3) {
//			x = "0" + x;
//		}
//		try {
//			MfIj mfIj = new MfIj();
//			MfIjId mfIjId = new MfIjId();
//			mfIjId.setIjId("IJ");
//			mfIjId.setIjNo(patPk + x); //TODO IJ + now() + (SQ + 1)
//			mfIj.setId(mfIjId);
//			mfIj.setIjReason("1");
//			mfIj.setFixCst("1");
//			mfIj.setManNo("ADMIN");
//			mfIj.setRem("開帳寫入庫存帳");
//			mfIj.setUsr("ADMIN");
//			mfIj.setChkMan("ADMIN");
//			mfIj.setPrtSw("N");
//			mfIj.setIjDd(now2());
//			mfIj.setDep("10010006");
//			mfIj.setClsDate(now2());
//			mfIj.setSysDate(now());
//			mfIj.setTzzg("F");
//			insertService.txInsertEntity(mfIj);
//		} catch (Exception e) {
//			boo = false;
//			System.out.println("INSERT mfIj EXCEPTION : " + e.getMessage());
//		}		
//		if (boo) {
//			for (int i = 0; i < obj.size(); i ++) {
////				String spec = obj.get(i).get("" + 3);			//規格			v
////				String unit = "PCS";							//單位
////				String storeType = obj.get(i).get("" + 5);		//庫存類別
////				String buyDate = obj.get(i).get("" + 6);		//購入日期		v
////				String costDollar = obj.get(i).get("" + 7);		//購入金額(美元)	v	使用標準成本, 不乘匯率
////				String uprLevel5 = obj.get(i).get("" + 10);		//優惠價
////				String qtyMin = obj.get(i).get("" + 11);		//經銷價
////				String qtyStore = obj.get(i).get("" + 12);		//盤點庫存
////				String qty = obj.get(i).get("" + 13);			//安全存量
//				String prodNo = obj.get(i).get("" + 0);			//產品編號		v
//				String prodNm = obj.get(i).get("" + 1);			//產品名稱		v
//				String supplier = obj.get(i).get("" + 2);		//供應商			v
//				String unit = obj.get(i).get("" + 4);			//單位
//				String upr = obj.get(i).get("" + 8);			//標準成本
//				String ord = obj.get(i).get("" + 9);			//訂價
//				String qty = obj.get(i).get("" + 14);			//庫存
//				String wh = obj.get(i).get("" + 15);			//儲位
//				String carType = obj.get(i).get("" + 16);		//車型
//				if (carType.equals("全球") || carType.trim().length() != 4) {
//					carType = "通用";
//				}
//				
//				try {
//					if (biln1 != null) {
//						Biln1 biln1Bean = (Biln1) queryService.selectById("Biln1SelectById", patPk + "%");
//						Biln1 b = new Biln1();
//						b.setPat(patPk + "NNNN");
//						b.setSq(biln1Bean.getSq() + 1);
//						modifyService.txUpdate(b);
//						t = biln1Bean.getSq() + 1;
//					}
//				} catch (Exception e) {
//					boo = false;
//					System.out.println("INSERT/UPDATE biln1 EXCEPTION : " + e.getMessage());
//				}
//				try {
//
//					Prdt prdt = (Prdt) queryService.selectById("SelectWhfromPrdtById", prodNo);
//					if (prdt == null) {	//空的，塞值
//						Prdt prddt = new Prdt();
//						prddt.setPrdNo(prodNo);  //1	//3
//						prddt.setName(prodNm);
//						prddt.setSnm(prodNm != null && prodNm.length() > 10 ? prodNm.substring(0, 10) : "");
//						prddt.setKnd("5");
//						prddt.setUt(unit);
//						prddt.setDfuUt("1");
//						prddt.setSpcPrd("F");
//						prddt.setSpcTax(new BigDecimal("5"));
//						prddt.setUprLevel5(new BigDecimal(ord));
//						prddt.setChkBat("F");
//						prddt.setChkNum("F");
//						prddt.setChkTax("F");
//						prddt.setChkMan("ADMIN");
//						prddt.setClsDate(today);
//						prddt.setUsr("ADMIN");
//						prddt.setSysDate(today);
//						prddt.setMlUt("1");
//						prddt.setQuoteUt1("1");
//						prddt.setQuoteUt2("1");
//						prddt.setQuoteUt3("1");
//						prddt.setWh(wh);
//						prddt.setUpr(new BigDecimal(ord));
//						prddt.setStartDd(today);	//insert才塞 startDd
//						insertService.txInsertEntity(prddt);
//					} else {
//						prdt.setUpr(new BigDecimal(ord));
//						prdt.setUprLevel5(new BigDecimal(ord));
//						prdt.setWh(wh);
//						modifyService.txUpdate(prdt);
//					}
//				} catch (Exception e) {
//					boo = false;
//					System.out.println("INSERT/UPDATE prdt EXCEPTION : " + e.getMessage());
//				}
//				
//				try {
//					PrdtCus1 prdtCus1 = (PrdtCus1) queryService.selectById("SelectWhfromPrdtCus1tById", prodNo);
//					if (prdtCus1 == null) {
//						PrdtCus1Id prdtCus1Id = new PrdtCus1Id();
//						PrdtCus1 prdtCus = new PrdtCus1();
//						prdtCus1Id.setPrdNo(prodNo);
//						prdtCus1Id.setCusNo(supplierCode(supplier));
//						prdtCus1Id.setAreaNo("");
//						prdtCus1Id.setSupPrdNo("");
//						prdtCus.setId(prdtCus1Id);
//						insertService.txInsertEntity(prdtCus);
//					} else {
//						PrdtCus1Id prdtCus1Id = new PrdtCus1Id();
//						PrdtCus1 prdtCus = new PrdtCus1();
//						prdtCus1Id.setPrdNo(prodNo);
//						prdtCus1Id.setCusNo(supplierCode(supplier));
//						prdtCus.setId(prdtCus1Id);
//						modifyService.txUpdate(prdtCus);
//					}
//				} catch (Exception e) {
//					System.out.println("INSERT/UPDATE prdtCus1 Exception : " + e.getMessage());
//				}
//				
//				MyWh myWh = (MyWh) queryService.selectById("SelectMyWhById", wh);
//				try {
//					if (myWh == null) {
//						MyWh myWhh = new MyWh();
//						myWhh.setWh(wh);
//						myWhh.setName(prodNm.length() > 100 ? prodNm.substring(0, 100) : prodNm);
//						myWhh.setChkWh("F");
//						myWhh.setAttrib("4");
//						myWhh.setSpcId("1");
//						myWhh.setDep("10010006");
//						String upWh = "";
//						if (carType.equals("6118")) {
//							upWh = "A003";
//						} else if (carType.equals("6128")) {
//							upWh = "A002";
//						} else if (carType.equals("6129")) {
//							upWh = "A004";
//						} else if (carType.equals("6180")) {
//							upWh = "A005";
//						} else if (carType.equals("6759")) {
//							upWh = "A006";
//						} else if (carType.equals("6858")) {
//							upWh = "A001";
//						} else if (carType.equals("通用")) {
//							upWh = "A007";
//						}
//						myWhh.setUpWh(upWh);
//						insertService.txInsertEntity(myWhh);
//					}
//				} catch (Exception e) {
//					boo = false;
//					System.out.println("INSERT myWh EXCEPTION : " + e.getMessage());
//				}
//
//				try {
//					TfIjId tfIjPk = new TfIjId();
//					TfIj tfIj = new TfIj();
//					tfIjPk.setItm(i + 1);
//					tfIjPk.setIjNo(patPk + x); //TODO
//					tfIjPk.setIjId("IJ");
//					tfIj.setId(tfIjPk);
//					tfIj.setPrdNo(prodNo);
//					tfIj.setWh(wh);
//					tfIj.setUnit("1");	//set unit = 1
//					tfIj.setQty(new BigDecimal(qty));
//					tfIj.setCst(new BigDecimal(upr).multiply(new BigDecimal(qty)));
//					tfIj.setIjDd(now2());
//					tfIj.setPreItm(i + 1);
//					tfIj.setKeyItm(i + 1);
//					tfIj.setFixCst("1");
////					System.out.println((i + 1) + ", " + patPk + ", " + prodNo + ", " + wh + ", " + qtyStore + ", " + upr);
//					insertService.txInsertEntity(tfIj);
//					
//				} catch (Exception e) {
//					boo = false;
//					System.out.println("INSERT tfIj EXCEPTION : " + e.getMessage());
//				}
//			}
//			
////			try {
////				if (boo) {
////					System.out.println("#StoreProcedure : " + (patPk + x));
////					insertService.txStoreProcedure(patPk + x);
////				}
////			} catch (Exception e) {
////				System.out.println("#txStoreProcedure EXCEPTION : " + e.getMessage());
////			}
////
////			for (int i = 0; i < obj.size(); i ++) {
////				String qtyStore = obj.get(i).get("" + 12);		//盤點庫存
////				if (!qtyStore.equals("0")) {
////					String prodNo = obj.get(i).get("" + 0);			//產品編號		
////					String qtyMin = obj.get(i).get("" + 13);		//安全存量
////					String wh = obj.get(i).get("" + 15);			//儲位
////					try {
////						Prdt1 prdt1 = new Prdt1();
////						Prdt1Id prdt1Id = new Prdt1Id();
////						prdt1Id.setPrdNo(prodNo);
////						prdt1Id.setWh(wh);
////						prdt1Id.setPrdMark("");
////						prdt1.setQtyMin(new BigDecimal(qtyMin));
////						prdt1.setId(prdt1Id);
////						modifyService.txUpdate(prdt1);
////					} catch (Exception e) {
////						System.out.println("failed : " + prodNo + " / " + wh + " / " + new BigDecimal(qtyMin));
////						System.out.println("UPDATE prdt1 EXCEPTION : " + e.getMessage());
////					}
////				}
////			}			
//		} else {
//			System.out.println("insert mfij error " + (patPk + x));
//		}
//		System.out.println("done");
	}
	
	@SuppressWarnings("unchecked")
	public void insertData(List<Map<String, String>> obj, HttpSession session, String cur) throws InterruptedException {
		String curId = cur == null || cur.trim().length() == 0 ? "USD" : cur;
		CurId curIdBean = (CurId) queryService.selectById("selectExcRtoFromCurId", curId);
		BigDecimal excRto = curIdBean.getExcRto();
		excRto = new BigDecimal(4.676);
		System.out.println("rate="+ excRto);
		System.out.println("1");
		Date today = now();
		List<TfPos> list = new ArrayList<TfPos>();
		
		for (int i = 0; i < obj.size(); i ++) {
			if (obj.get(i).get("0") == null) {
				continue;
			}
//			for ( int j=0 ; j <=10 ; j ++) {
//				System.out.println(j+"="+obj.get(i).get(Integer.toString(j)));
//			}
			System.out.println("1.5");
			int itm =  Integer.parseInt(obj.get(i).get("0"));			//行號
			String prdNo = obj.get(i).get("1");							//物料號
			String prdName = obj.get(i).get("2");						//物料描述
			BigDecimal qty = new BigDecimal(obj.get(i).get("3"));		//數量	BigDecimal
			String unit = obj.get(i).get("4");							//單位	
			BigDecimal up = new BigDecimal(obj.get(i).get("5")).multiply(new BigDecimal(1.3).multiply(excRto)).setScale(2,BigDecimal.ROUND_HALF_UP);	//單價 
			BigDecimal amt = qty.multiply(up).setScale(2,BigDecimal.ROUND_HALF_UP);//new BigDecimal(obj.get(i).get("6"));	//數量	BigDecimal
			String osDd = obj.get(i).get("7"); //noww() + "";//obj.get(i).get("7");							//訂單日期
//			System.out.println("#obj.get(i).get(5) : " + obj.get(i).get("5"));
//			System.out.println("#excRto : " + excRto);
//			System.out.println("#up : " + up);
//			System.out.println("#osDd : " + osDd);
			String cusOsNo = obj.get(i).get("8") == null || obj.get(i).get("8").length() == 0 ? "" : obj.get(i).get("8");						//OSN_訂單號碼
			
			//TODO remember to remove it, 103/11/11
			String wh = obj.get(i).get("9");						//儲位
//			String carType = obj.get(i).get("10");	//車型
//			System.out.println("#WH : " + wh);
//			MyWh myWh = (MyWh) queryService.selectById("SelectMyWhById", wh);
//			try {
//				if (myWh == null) {
//					MyWh myWhh = new MyWh();
//					myWhh.setWh(wh);
//					myWhh.setName(prdName.length() > 100 ? prdName.substring(0, 100) : prdName);
//					myWhh.setChkWh("F");
//					myWhh.setAttrib("4");
//					myWhh.setSpcId("1");
//					myWhh.setDep("10010006");
//					String upWh = "";
//					if (carType.equals("6118")) {
//						upWh = "A003";
//					} else if (carType.equals("6128")) {
//						upWh = "A002";
//					} else if (carType.equals("6129")) {
//						upWh = "A004";
//					} else if (carType.equals("6180")) {
//						upWh = "A005";
//					} else if (carType.equals("6759")) {
//						upWh = "A006";
//					} else if (carType.equals("6858")) {
//						upWh = "A001";
//					} else if (carType.equals("通用")) {
//						upWh = "A007";
//					}
//					myWhh.setUpWh(upWh);
//					insertService.txInsertEntity(myWhh);
//				}
//			} catch (Exception e) {
//				System.out.println("INSERT myWh EXCEPTION : " + e.getMessage());
//			}
			
			Prdt prdt = (Prdt) queryService.selectById("SelectWhfromPrdtById", prdNo);
//			String wh = "0000";
			System.out.println("2");
			if (prdt == null) {
				PrdtCus1 prdtCus1 = (PrdtCus1) queryService.selectById("SelectWhfromPrdtCus1tById", prdNo);
				if (prdtCus1 == null) {
					try {
						PrdtCus1 pc1 = new PrdtCus1();
						PrdtCus1Id pc1Id = new PrdtCus1Id();
						pc1Id.setPrdNo(prdNo);
						pc1Id.setCusNo("B0002");
						//TODO once
//						pc1Id.setCusNo("C0010");
						pc1Id.setSupPrdNo(prdNo);
						pc1Id.setAreaNo("");
						pc1Id.setPrdMark("");
						pc1Id.setSupPrdMark("");
						pc1.setId(pc1Id);
						insertService.txInsertEntity(pc1);
					} catch (Exception e) {
						System.out.println("1, Insert PrdtCus1 exception : " + e.getMessage());
					}
				}
				try {
					Prdt prddt = new Prdt();
					prddt.setPrdNo(prdNo);  //1	//3
					prddt.setName(prdName);
					prddt.setSnm(prdName != null && prdName.length() > 10 ? prdName.substring(0, 10) : prdName);
					prddt.setKnd("5");
					prddt.setUt(unit);
					System.out.println("-----------------------------------------------");
					System.out.println("UNIT : " + unit);
					System.out.println("-----------------------------------------------");
					prddt.setDfuUt("1");
					prddt.setSpcPrd("F");
					prddt.setSpcTax(new BigDecimal("5"));
					prddt.setChkBat("F");
					prddt.setChkNum("F");
					prddt.setChkTax("F");
					prddt.setStartDd(today);
					prddt.setChkMan("ADMIN");
					prddt.setClsDate(today);
					prddt.setUsr("ADMIN");
					prddt.setSysDate(today);
					prddt.setMlUt("1");
					prddt.setQuoteUt1("1");
					prddt.setQuoteUt2("1");
					prddt.setQuoteUt3("1");
					prddt.setWh(wh); //TODO remember remove it. 103/11/11
					BigDecimal x = up.divide(new BigDecimal("0.75"), 2, BigDecimal.ROUND_DOWN).divide(new BigDecimal("0.75"), 0, BigDecimal.ROUND_UP);
					prddt.setUpr(x);	// /0.75 2 times
					prddt.setUprLevel5(x);
					insertService.txSoUEntity(prddt);	
				} catch (Exception e) {
					Thread.sleep(10000);
					System.out.println("Insert Prdt exception : " + e.getMessage());
				}
			} else {
				System.out.println("3");
				PrdtCus1 prdtCus1 = (PrdtCus1) queryService.selectById("SelectWhfromPrdtCus1tById", prdNo);
				if (prdtCus1 == null) {
					try {
						PrdtCus1 pc1 = new PrdtCus1();
						PrdtCus1Id pc1Id = new PrdtCus1Id();
						pc1Id.setPrdNo(prdNo);
						pc1Id.setCusNo("B0002");
						//TODO once
//						pc1Id.setCusNo("C0010");
						pc1Id.setSupPrdNo(prdNo);
						pc1Id.setAreaNo("");
						pc1Id.setPrdMark("");
						pc1Id.setSupPrdMark("");
						pc1.setId(pc1Id);
						insertService.txInsertEntity(pc1);
					} catch (Exception e) {
						System.out.println("2, Insert PrdtCus1 exception : " + e.getMessage());
					}
				}
				//TODO remember remark it. 103/11/11 
				wh = prdt.getWh() == null || prdt.getWh().trim().length() == 0 ? "0000" : prdt.getWh();
				
				//TODO remember remove it, 103/11/11
//				if (prdt.getWh() == null || prdt.getWh().trim().length() == 0) {
					prdt.setPrdNo(prdNo);  //1	//3
					prdt.setWh(wh);
					modifyService.txUpdate(prdt);
//				} else {
//					wh = prdt.getWh() == null || prdt.getWh().trim().length() == 0 ? "" : prdt.getWh();
//				}
			}				
			System.out.println("5");
//			Date orderDate = orderDate(osDd);
			String osNo = "PO" + utils.toRocYear(noww() + ""); 
			try {
				if (osNo.length() != 9) {
					System.out.println("--OsNo length is wrong!-- : " + osNo);
				} else {
					if (i == 0) {
						Biln1 biln1 = (Biln1) queryService.selectById("Biln1SelectById", osNo + "%");
						if (biln1 == null) {		//沒有查到資料
							System.out.println("#################### biln1 is null");
							try {
								Biln1 bean = new Biln1();
								bean.setPat(osNo + "NNNN");
								bean.setSq(1);
								bean.setBilId("PO");
								insertService.txInsertEntity(bean);
								osNo += "0001";
								session.setAttribute("osNo", osNo);
							} catch (Exception e) {
								System.out.println("Insert biln1 exception : " + e.getMessage());
							}
						} else {					//有查到資料
							try {
								int tmp = biln1.getSq() + 1;
								biln1.setSq(tmp);
								modifyService.txUpdate(biln1);
								if ((tmp + "").length() == 1) osNo += ("000" + tmp);
								if ((tmp + "").length() == 2) osNo += ("00" + tmp);
								if ((tmp + "").length() == 3) osNo += ("0" + tmp);
								session.setAttribute("osNo", osNo);
								System.out.println("#################### biln1 is not null : " + tmp + " , osNo : " + osNo);
							} catch (Exception e) {
								System.out.println("update biln1 exception : " + e.getMessage());
							}
						}
						try {
							/* CREATE MF_POS OBJECT */
							System.out.println("6");
							MfPosId mfId = new MfPosId();
							mfId.setOsId("PO");
							mfId.setOsNo(osNo);				//讀BILN1檔，以POYYYMMDDNNNN讀取序號，SQ+1後寫回，並使用該號
							//SQ , 中間的日期暫時使用訂單日期 
							MfPos mf = new MfPos();
							mf.setId(mfId);				
							mf.setOsDd(now2(osDd));			//EXCEL.訂單日期
							mf.setCusNo("B0002");	//TODO once
//							mf.setCusNo("B0002");
							mf.setCurId(null);	
							mf.setEstDd(now2(osDd));			//EXCEL.訂單日期
							mf.setTaxId("1");
							mf.setClsId("F");
							mf.setAmtnInt(BigDecimal.ZERO);
							mf.setUsr("ADMIN");
							mf.setChkMan("ADMIN");
							mf.setPrtSw("N");
							mf.setCusOsNo(cusOsNo);			//EXCEL.OSN_訂單號碼, OK
							mf.setPayMth("1");
							mf.setPayDays(Short.valueOf("1"));
							mf.setChkDays(Short.valueOf("30"));
							mf.setSendMth("1");
							mf.setExcRto(BigDecimal.ONE);
							mf.setDisCnt(BigDecimal.ZERO);
							mf.setSendWh("0000");
							mf.setPayDd(now2(osDd));			//EXCEL.訂單日期 
							mf.setChkDd(now2(osDd));			//EXCEL.訂單日期 
							mf.setIntDays(Short.valueOf("30"));
							mf.setHisPrice("T");
							mf.setBackId("PC");
							mf.setPreId("F");
							mf.setClsDate(now());		//EXCEL.訂單日期
							mf.setHsId("T");
							mf.setIsoversh("F");
							mf.setSysDate(today);
							mf.setPoDep("10010006");
							mf.setUseDep("10010006");
							insertService.txInsertEntity(mf);
						} catch (Exception e) {
							System.out.println("insert mfpos exception : " + e.getMessage());
						}
					}
					osNo = (String) session.getAttribute("osNo");
					try {
						/* CREATE TF_POS OBJECT */
						System.out.println("7");
						TfPosId tfId = new TfPosId();
						tfId.setOsId("PO");
						tfId.setOsNo(osNo); 			//MF_POS.OS_NO, "AC1000620NNNN"測試用
						tfId.setItm(itm);				//EXCEL.行號
						TfPos tf = new TfPos();
						tf.setId(tfId);
						//		tf.setMfPos(mf);
						tf.setPrdNo(prdNo);				//EXCEL.物料號, 測試用, OK
						tf.setPrdName(prdName);			//EXCEL.物料描述, OK
						tf.setWh(wh);					//QUERY PRDT.WH BY PRD_NO
						tf.setUnit("1");
						tf.setQty(qty);					//EXCEL.數量, OK
						System.out.println("qty : " + qty);
						tf.setUp(up);					//EXCEL.單價, OK
						System.out.println("up : " + up);
						tf.setAmt(amt);					//EXCEL.總價, OK
						System.out.println("amt : " + amt);
						tf.setAmtn(amt);				
						System.out.println("excRto : " + excRto);
						tf.setQtyPs(null);
						tf.setQty1Ps(null);
						tf.setEstDd(now());			//EXCEL.訂單日期
						tf.setPreEstDd(now());		//EXCEL.訂單日期
						tf.setOsDd(now());			//EXCEL.訂單日期
						System.out.println("now : " + now());
						tf.setTaxRto(BigDecimal.ZERO);
						tf.setEstItm(0);
						tf.setPreItm(0);
						tf.setOthItm(0);
						list.add(tf);
					} catch (Exception e) {
						System.out.println("add obj to list exception : " + e.getMessage());
					}
				}				
			} catch (Exception e) {
				System.out.println("tf exception");
			}
		}
		try {
			for (int j = 0; j < list.size(); j ++) {
				System.out.println("insert " + list.get(j).getPrdNo() + " now.....");
				insertService.txInsertEntity(list.get(j));
			}
		} catch (Exception e) {
			System.out.println("#INSERT tfpost EXCEPTION : " + e.getMessage());
		}
		session.removeAttribute("osNo");
		System.out.println("done");
	}
	
	/**
	 * 去除時間的毫秒，並回傳時間格式 
	 * @return
	 */
	public static Date now2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null ;
		try {
			now = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("now date exception happend");
		}
		return now;
	}	
	/**
	 * 去除時間的毫秒，並回傳時間格式 
	 * @return
	 */
	public static Date orderDate(String dateStr) {
		String date = dateStr != null ? dateStr.replace("/", "-") + " 00:00:00": "1970-01-01 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null ;
		try {
			now = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("now date exception happend");
		}
		return now;
	}

	/**
     * 轉換目前時間字串為民國年字串
     * @return String 民國年，格式為 yyyMMdd
     */
    public static String nowToRocYear() {
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMMdd = new SimpleDateFormat("MMdd");
		return Integer.parseInt(sdfYear.format(new Date())) - 1911 + sdfMMdd.format(new Date());
    }

    public static String supplierCode(String supplier) {
		String str = "";
		if (supplier.equals("康擎")) {
			str = "B0049";
		} else if (supplier.equals("弘唯")) {
			str = "LS121";
		} else if (supplier.equals("挺宇")) {
			str = "C0104";
		} else if (supplier.equals("海格")) {
			str = "B0056";
		} else if (supplier.equals("清必")) {
			str = "B0032";
		} else if (supplier.equals("舒和")) {
			str = "B0009";
		} else if (supplier.equals("詠晨")) {
			str = "B0039";
		} else if (supplier.equals("上韋")) {
			str = "C0280";
		} else if (supplier.equals("峻崙")) {
			str = "VM021";
		} else if (supplier.equals("統力")) {
			str = "VM046";
		} else if (supplier.equals("礱德")) {
			str = "B0020";
		} else if (supplier.equals("台灣采埃孚")) {
			str = "B0030";
		} else if (supplier.equals("香港宇通國際有限公司")) {
			str = "B0002";
		}
		return str;
    }
    
//    public String unitCode(String code) {
//    	PrdtUt prdtUt = new PrdtUt();
//    	PrdtUtId prdtUtId = new PrdtUtId();
//    	List<PrdtUt> prdtUtList = (List<PrdtUt>) queryService.selectAll("SelectPrdtUt");
//    	return null;
//    }

    /**
	 * 去除時間的毫秒，並回傳時間格式 
	 * @return
	 */
	public static Date now() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null ;
		try {
			now = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("now date exception happend");
		}
		return now;
	}
	
	/**
	 * 去除時間的毫秒，並回傳時間格式 
	 * @return
	 */
	public static Date now(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null ;
		try {
			now = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("now date exception happend");
		}
		return now;
	}
	/**
	 * 去除時間的毫秒，並回傳時間格式 
	 * @return
	 */
	public static Date now2(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date now = null ;
		try {
			now = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("now date exception happend");
		}
		return now;
	}
	
    /**
	 * 去除時間的毫秒，並回傳字串格式 
	 * @return
	 */
	public static String noww() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}	

	public static String returnDateString(String str) {
		if (str.contains("一月")) {
			str = str.replace("ㄧ月", "01");
		} else if (str.contains("二月")) {
			str = str.replace("二月", "02");
		} else if (str.contains("三月")) {
			str = str.replace("三月", "03");
		} else if (str.contains("四月")) {
			str = str.replace("四月", "04");
		} else if (str.contains("五月")) {
			str = str.replace("五月", "05");
		} else if (str.contains("六月")) {
			str = str.replace("六月", "06");
		} else if (str.contains("七月")) {
			str = str.replace("七月", "07");
		} else if (str.contains("八月")) {
			str = str.replace("八月", "08");
		} else if (str.contains("九月")) {
			str = str.replace("九月", "09");
		} else if (str.contains("十月")) {
			str = str.replace("十月", "10");
		} else if (str.contains("十一月")) {
			str = str.replace("十ㄧ月", "11");
		} else if (str.contains("十二月")) {
			str = str.replace("十二月", "12");
		}
		String[] strr = str.split("-");
		return strr[2] + "-" + strr[1] + "-" + strr[0];
	}
}