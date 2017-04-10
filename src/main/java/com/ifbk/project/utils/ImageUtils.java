package com.ifbk.project.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtils {
	public BufferedImage rotate180(BufferedImage inputImage) {
		int width = inputImage.getWidth(); // the Width of the original image
		int height = inputImage.getHeight();// the Height of the original image

		BufferedImage returnImage = new BufferedImage(width, height,
				inputImage.getType());

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(width - x - 1, height - y - 1, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}

	public BufferedImage rotate90ToLeft(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(height, width,
				inputImage.getType());

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}

	public BufferedImage rotate90ToRight(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(height, width,
				inputImage.getType());

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}
	
	/**
	 * 旋轉影像
	 * @param source File 檔案來源
	 * @param dest File 檔案目的地
	 * @param rotate String 順時針或逆時針或180度旋轉，填入left、right、reverse。
	 * @param imgType String 檔案種類，填入jpg、png、gif，不填預設為jpg
	 * @return boolean 成功就回傳true。
	 */
	public boolean rotateImage(File source, File dest, String rotate, String imgType) {
		if (dest.exists()) {
			return true;
		} else {
			BufferedImage inputImage = null;
			try {
				inputImage = ImageIO.read(source);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			int width = inputImage.getWidth();
			int height = inputImage.getHeight();
			BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());
			
			try {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						if (rotate != null && rotate.equals("left")) {
							returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
						} else if (rotate != null && rotate.equals("right")) {
							returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));
						} else if (rotate != null && rotate.equals("reverse")) {
							returnImage.setRGB(width - x - 1, height - y - 1, inputImage.getRGB(x, y));
						} else {
							return false;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("#IMAGE ROTATION EXCEPTION : " + e.getMessage());
			}
	        try {
				ImageIO.write(returnImage, imgType == null ? "jpg" : imgType, dest);
				if (dest.exists()) {
					return true;
				} else {
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean roratee(File source, File dest, String rotate) {
		Rotation ro = null;
		if (rotate.equals("left")) {
			ro = Scalr.Rotation.CW_270;
		} else if (rotate.equals("right")) {
			ro = Scalr.Rotation.CW_90;
		} else if (rotate.equals("reverse")) {
			ro = Scalr.Rotation.CW_180;
		}
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(source.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = source.toString() != null ? source.toString()
			.substring(source.toString().lastIndexOf("."), source.toString().length()).replace(".", "") : "";

		try {
			ImageIO.write(new Scalr().rotate(bimg, ro, null), type == null ? "jpg" : type, dest);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImagingOpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dest.exists()) {
			return true;
		} else {
			return false;
		}
		//return new Scalr().rotate(bimg, Scalr.Rotation.CW_270, null);
	}
	
	/**
     * Decode string to image
     * @param imageString The string to decode
     * @return decoded image
     */
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

//    public static void main (String args[]) throws IOException {
//        /* Test image to string and string to image start */
//        BufferedImage img = ImageIO.read(new File("files/img/TestImage.png"));
//        BufferedImage newImg;
//        String imgstr;
//        imgstr = encodeToString(img, "png");
//        System.out.println(imgstr);
//        newImg = decodeToImage(imgstr);
//        ImageIO.write(newImg, "png", new File("files/img/CopyOfTestImage.png"));
//        /* Test image to string and string to image finish */
//    }

}