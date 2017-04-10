package com.ifbk.project.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;

public class Utils {

	private static Map<String, Long> timerMap = new HashMap<String, Long>();

	 public static boolean isWindows() {
	        String os = System.getProperty("os.name").toLowerCase();	//windows
	        return (os.indexOf("win") >= 0);
	 }
	 
    public static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();	//Mac
        return (os.indexOf("mac") >= 0);
    }

    public String userLoginStatus(HttpSession session, String classNameTmp) {
		String className = classNameTmp.replace("com.ifbk.project.controller.", "").substring(0, 4) 
				+ "tion" + classNameTmp.replace("com.ifbk.project.controller.", "").substring(4, 7);
		if (session.getAttribute("userId") == null) {
			return "redirect:index.jsp"; 
		} else {
			return "/front/" + className;
		}
	}
	
	/**
	 * 多種物件比較方法
	 * @param a Object 物件a
	 * @param b Object 物件b
	 * @param type String 物件類型
	 * @return boolean
	 */
	public boolean objectCompare(Object a, Object b, String type) {
		boolean flag = false;
		if (type == null) {
			if ((a == null || a.equals("")) && (!b.equals("") && b != null)) {
				flag = true;
			} else if (!a.equals(b)){
				flag = true;
			}
		} else if (type.equals("date")){
			if (b != null && (b + "").trim().length() != 0) {
				if (((Date) a + "").equals(b)) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} else if (type.equals("byte")){
			if (Arrays.equals((byte[])a, (byte[])b)) {
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}
	
//	public boolean isEmailAddress(String email) {
//		boolean result = true;
//		
//		try {
//			InternetAddress emailAddr = new InternetAddress(email);
//			emailAddr.validate();
//		
//		} catch (AddressException ex) {
//			result = false;
//		
//		}
//		
//		return result;
//	}
	
	/**
	 * 字串檢查
	 * @param type String 別名
	 * @param str String 要檢查的字串
	 * @param map Map<String, String> 裝載錯誤的map
	 * @return Map<String, String> 回傳map
	 */
	public Map<String, String> stringCheck(String name, Map<String, String> map, String str, String type) {
		if (str.trim().length() == 0 || str == null) {	//空白值檢查
			map.put(name, "");
		} else {
			if (type.equalsIgnoreCase("password")) {	//password 不作filter
				map.put(name, str);	
			} else if (type.equalsIgnoreCase("phone") || type.equalsIgnoreCase("mobile")) { //電話數字檢查
				map.put(name, isNumberNoDecimal(str) ? str : "FormatError");
				
//			} else if (type.equalsIgnoreCase("email")) {	//電子郵件格式檢查
//				map.put(name, isEmailAddress(str) ? str : "FormatError");
				
			} else if (type.equalsIgnoreCase("date")) {		//日期格式檢查
				map.put(name, isDate(str, "yyyy/MM/dd") ? str : "FormatError");
				
			} else if (type.equalsIgnoreCase("others")) {
				map.put(name, str);
			} 
		}
		return map;
	}
	
	/**
	 * 密碼編碼
	 * 
	 * @param encrypt
	 *            String
	 * @return byte[]
	 */
	public byte[] encrypt(String encrypt) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encode = md.digest(encrypt.getBytes());
		return encode;
	}

	/**
	 * 產生認證碼
	 * @param pwlength integer 認證碼長度
	 * @return String
	 */
	public static String validatecode(int pwlength) {

		String regsn = "";

		String[] RegSNContent = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z" };
		
		for (int i = 0; i < pwlength; i++)
			regsn += RegSNContent[(int) (Math.random() * RegSNContent.length)];
		
		return regsn;
	}

	/**
	 * 讀取 config.properties
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public String getConfigProperties(String str) {
		ResourceBundle rb = ResourceBundle.getBundle("properties/config");
		return rb.getString(str);
	}

	/**
	 * 讀取 sql.properties
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public String getSqlProperties(String str) {
		ResourceBundle rb = ResourceBundle.getBundle("properties/sql");
		return rb.getString(str);
	}

	/**
	 * 讀取 zhTW.properties
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public String getZh_TWProperties(String str) {
		ResourceBundle rb = ResourceBundle.getBundle("properties/message_zhTW");
		return rb.getString(str);
	}

	/**
	 * 字串變換日期 (String to java.util.Date)
	 * 
	 * @param dates
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public Date stringToDate(String dates) throws ParseException {
		if (dates != null && dates.trim().length() != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			try {
				return sdf.parse(dates);
			} catch (ParseException e) {
				throw new ParseException(dates, 0);
			}
		} else {
			return null;
		}
	}

	/**
	 * 檢查是否為數字，不含小數檢查
	 * @param str String
	 * @return boolean
	 */
	public boolean isNumberNoDecimal(String str) {
		if (str.trim().length() == 0) {
			return false;
		} else {
			for (int i = str.length(); --i >= 0;) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 檢查是否為數字，含小數檢查
	 * @param str String
	 * @return boolean
	 */
	public boolean isNumberWithDecimal(String str) {
		boolean flag = true;
		if (str.trim().length() == 0) {
			flag = false;
		} else {
			try {
				double tmp = Double.parseDouble(str);
				if (!isIntegral(tmp) && !isDecimal(tmp)) {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 檢查是否為整數
	 * @param value double 輸入的數字
	 * @return boolean
	 */
	public static boolean isIntegral(double value) {
        return value % 1.0 == 0;
    }

	/**
	 * 檢查是否為小數
	 * @param value double 輸入的數字
	 * @return boolean
	 */
	public static boolean isDecimal(double value) {
        return value % 1.0 != 0;
    }

	/**
	 * util date to sql date 
	 * @param date Date
	 * @return Date
	 */
	public Date utilDateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 檢查字串是否為空
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isEmpty(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * 檢查日期格式是否正確
	 * 
	 * @param value
	 *            String
	 * @param format
	 *            String
	 * @return boolean
	 */
	public boolean isDate(String value, String format) {

		SimpleDateFormat sdf = null;
		ParsePosition pos = new ParsePosition(0);

		if (value == null || isEmpty(format)) {
			return false;
		}
		try {
			sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			Date date = sdf.parse(value, pos);
			if (date == null) {
				return false;
			} else {
				if (pos.getIndex() > sdf.format(date).length()) {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String dateMinusSlash(Date date) {
		if (date != null) {
			return date.toString().replace("-", "/");
		} else {
			return "";
		}
	}

	public String filter(String str_in) {
		String str_out = null;
		if (str_in != null) {
			str_out = "";
			char[] chars = str_in.toCharArray();
			int length = chars.length;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("a", "a"); map.put("b", "b"); map.put("c", "c"); map.put("d", "d");
			map.put("e", "e"); map.put("f", "f"); map.put("g", "g"); map.put("h", "h");
			map.put("i", "i"); map.put("j", "j"); map.put("k", "k"); map.put("l", "l");
			map.put("m", "m"); map.put("n", "n"); map.put("o", "o"); map.put("p", "p");
			map.put("q", "q"); map.put("r", "r"); map.put("s", "s"); map.put("t", "t");
			map.put("u", "u"); map.put("v", "v"); map.put("w", "w"); map.put("x", "x");
			map.put("y", "y"); map.put("z", "z"); map.put("A", "A"); map.put("B", "B");
			map.put("C", "C"); map.put("D", "D"); map.put("E", "E"); map.put("F", "F");
			map.put("G", "G"); map.put("H", "H"); map.put("I", "I"); map.put("J", "J");
			map.put("K", "K"); map.put("L", "L"); map.put("M", "M"); map.put("N", "N");
			map.put("O", "O"); map.put("P", "P"); map.put("Q", "Q"); map.put("R", "R");
			map.put("S", "S"); map.put("T", "T"); map.put("U", "U"); map.put("V", "V");
			map.put("W", "W"); map.put("X", "X"); map.put("Y", "Y"); map.put("Z", "Z");
			map.put("0", "0"); map.put("1", "1"); map.put("2", "2"); map.put("3", "3");
			map.put("4", "4"); map.put("5", "5"); map.put("6", "6"); map.put("7", "7");
			map.put("8", "8"); map.put("9", "9"); map.put("/", "/"); map.put("\\", "\\");
			map.put(".", "."); map.put(":", ":"); map.put("_", "_");
			for (int i = 0; i < length; i++) {
				if (map.get(String.valueOf(chars[i])) != null) {
					str_out += map.get(String.valueOf(chars[i]));
				}
			}
		}
		return str_out;
	}

	public void myTimer(String enterPoint, long now) {
		
		if(timerMap.containsKey(enterPoint)) {
			if ((now - timerMap.get(enterPoint).longValue()) < 1000) {
				System.out.println(enterPoint + " : " 
						+ (now - timerMap.get(enterPoint).longValue())+" msec");
			} else {
				System.out.println(enterPoint + " : " 
						+ (now - timerMap.get(enterPoint).longValue()) / 1000 + " sec");
			}
			timerMap.remove(enterPoint);
		} else {
			timerMap.put(enterPoint, now);
		}
	}
	
	public boolean folderBuilder(String path) {
		File file = new File(path);
		if(!file.exists()) {
			if(file.mkdirs()){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public boolean cNul(String str) {
		if (str == null || str.trim().length() == 0) {
			return false ;
		} else {
			return true;
		}
	}
	
	public void copyFile(File src, File dst) {
		int BUFFER_SIZE = 16 * 1024;

		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
		
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
			
		} finally {
			
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
    /**
     * @param source File
     * @param dest File
     * @throws IOException .
     */
    public static void copyFileUsingFileStreams(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
    /**
     * Rotates an image. Actually rotates a new copy of the image.
     * 
     * @param img The image to be rotated
     * @param angle The angle in degrees
     * @return The rotated image
     */
    public static Image rotate(Image img, double angle){
        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = img.getWidth(null), h = img.getHeight(null);
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                * cos + w * sin);
        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
        Graphics2D g = bimg.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(toBufferedImage(img), null);
        g.dispose();
        return toImage(bimg);
    }
    
    /**
     * Makes a color in an Image transparent.
     */
    public static Image mask(Image img, Color color){
        BufferedImage bimg = toBufferedImage(getEmptyImage(img.getWidth(null), img.getHeight(null)));
        Graphics2D g = bimg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        for (int y=0; y<bimg.getHeight(); y++){
            for (int x=0; x<bimg.getWidth(); x++){
                int col = bimg.getRGB(x, y);
                if (col==color.getRGB()){
                    bimg.setRGB(x, y, col & 0x00ffffff);
                }
            }
        }
        return toImage(bimg);
    }

    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }
    /**
     * Creates an empty image with transparency
     * 
     * @param width The width of required image
     * @param height The height of required image
     * @return The created image
     */
    public static Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }
    
    /**
     * Converts a given BufferedImage into an Image
     * 
     * @param bimage The BufferedImage to be converted
     * @return The converted Image
     */
    public static Image toImage(BufferedImage bimage){
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
    
    /**
     * 轉換西元年字串為民國年字串
     * @param str 輸入西元年，格式為 yyyy/MM/dd
     * @return String 民國年，格式為 yyyMMdd
     */
    public String toRocYear(String str) {
    	str = str.replace("-", "/");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf3 = new SimpleDateFormat("MMdd");
		
		try {
			String x = (Integer.parseInt(sdf2.format(sdf.parse(str))) - 1911) + sdf3.format(sdf.parse(str));
			return x;
		} catch (ParseException e) {
			return "0";
		}
    }
}
class myErrException extends Throwable {
	public myErrException() {
		System.out.println("Exceptiooooooooooon happend!");
	}
}
