package com.djy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.freeswitch.esl.ESLconnection;

public class AndroidPx2DpUtils {

	/*
	 * 
	 * valuse-sw440dp 440 = 屏幕宽度像素*160/屏幕密度值
	 */

	private static final float MIN = 0.5F;

	private static final float TARGET_WIDTH = 480.0F;
	private static final float TARGET_HEIGHT = 800.0F;
	private static final float TARGET_DPI = 240.0F;

	private static final String FOLDER_NAME_START = "values-";

	private static final String DP_FILE_NAME = "dimens.xml";

	private static final String STRING_FILE_NAME = "strings.xml";

	private static final String STRING_START = "<string name=\"current_dpi\">";
	private static final String STRING_END = "</string>\r\n";

	private static final String XML_START = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n";
	private static final String XML_END = "\r\n</resources>\r\n";

	private static final String WIDTH_START = "<dimen name=\"wbank_width_";
	private static float width;
	private static final String WIDTH_NUM_PX = "px_basic_800x480\">";
	private static final String WIDTH_END = "dp</dimen>\r\n";

	private static final String HEIGHT_START = "<dimen name=\"wbank_height_";
	private static float height;
	private static final String HEIGHT_NUM_PX = "px_basic_800x480\">";
	private static final String HEIGHT_END = "dp</dimen>\r\n";

	private static final String TEXTSIZE_START = "<dimen name=\"wbank_textSize_";
	private static float textSize;
	private static final String TEXTSIZE_NUM_PX = "px_basic_800x480\">";
	private static final String TEXTSIZE_END = "sp</dimen>\r\n";

	private static final AndroidPx2DpUtils.PhoneS LDPI = new AndroidPx2DpUtils.PhoneS("", 240, 320, DPI.LDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS MDPI = new AndroidPx2DpUtils.PhoneS("", 320, 480, DPI.MDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS MDPI_480x320 = new AndroidPx2DpUtils.PhoneS("", 320, 480, DPI.MDPI.getValue(), ValuesType.W);

	private static final AndroidPx2DpUtils.PhoneS HDPI = new AndroidPx2DpUtils.PhoneS("1", 480, 800, DPI.HDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS HDPI_800x480 = new AndroidPx2DpUtils.PhoneS("1", 480, 800, DPI.HDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS HDPI_1848x1200 = new AndroidPx2DpUtils.PhoneS("2", 1200, 1848, DPI.HDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XHDPI = new AndroidPx2DpUtils.PhoneS("3", 720, 1280, DPI.XHDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS XHDPI_1280x720 = new AndroidPx2DpUtils.PhoneS("3", 720, 1280, DPI.XHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XHDPI_960x640 = new AndroidPx2DpUtils.PhoneS("3", 640, 960, DPI.XHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XHDPI_1000x720 = new AndroidPx2DpUtils.PhoneS("3", 720, 1000, DPI.XHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI = new AndroidPx2DpUtils.PhoneS("5", 1080, 1920, DPI.XXHDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS DPI440 = new AndroidPx2DpUtils.PhoneS("5", 1080, 1920, 440, ValuesType.W);

	private static final AndroidPx2DpUtils.PhoneS XXHDPI_1920x1080 = new AndroidPx2DpUtils.PhoneS("5", 1080, 1920, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_1800x1080 = new AndroidPx2DpUtils.PhoneS("5", 1080, 1800, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_1280x720 = new AndroidPx2DpUtils.PhoneS("4", 720, 1280, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2094x1080 = new AndroidPx2DpUtils.PhoneS("5", 1080, 2094, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2220x1080 = new AndroidPx2DpUtils.PhoneS("5", 1080, 2220, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2392x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2392, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2560x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2560, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2792x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2792, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXHDPI_2960x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2960, DPI.XXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXXHDPI = new AndroidPx2DpUtils.PhoneS("5", 1440, 2560, DPI.XXXHDPI.getValue(), ValuesType.DPI);
	private static final AndroidPx2DpUtils.PhoneS XXXHDPI_2392x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2392, DPI.XXXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXXHDPI_2560x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2560, DPI.XXXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXXHDPI_2792x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2792, DPI.XXXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS XXXHDPI_2960x1440 = new AndroidPx2DpUtils.PhoneS("5", 1440, 2960, DPI.XXXHDPI.getValue(),
			ValuesType.W);
	private static final AndroidPx2DpUtils.PhoneS[] phoneSArrays = { LDPI, MDPI, MDPI_480x320, HDPI, HDPI_800x480,
			HDPI_1848x1200, XHDPI, XHDPI_1280x720, XHDPI_960x640, XHDPI_1000x720, XXHDPI, DPI440, XXHDPI_1920x1080,
			XXHDPI_1280x720, XXHDPI_1800x1080, XXHDPI_2094x1080, XXHDPI_2220x1080, XXHDPI_2392x1440, XXHDPI_2560x1440,
			XXHDPI_2792x1440, XXHDPI_2960x1440, XXXHDPI, XXXHDPI_2392x1440, XXXHDPI_2560x1440, XXXHDPI_2792x1440,
			XXXHDPI_2960x1440 };
	private static final String WIDTH_NOTATION = "\r\n<!--宽度-->\r\n";
	private static final String HEIGHT_NOTATION = "\r\n<!--高度-->\r\n";
	private static final String TEXTSIZE_NOTATION = "\r\n<!--字体大小-->\r\n";
	/**
	 * 标注图的基准和对应的px
	 */
	private static final String[] usedDp = { "wbank_textSize_10px_basic_800x480", "wbank_textSize_15px_basic_800x480",
			"wbank_textSize_13px_basic_800x480", "wbank_textSize_18px_basic_800x480",
			"wbank_textSize_20px_basic_800x480", "wbank_textSize_22px_basic_800x480",
			"wbank_textSize_24px_basic_800x480", "wbank_textSize_27px_basic_800x480",
			"wbank_textSize_32px_basic_800x480",
			
			"wbank_textSize_24px_basic_1280x720","wbank_textSize_28px_basic_1280x720",
			
			"wbank_width_6px_basic_1280x720", "wbank_width_9px_basic_1280x720", "wbank_width_12px_basic_1280x720",
			"wbank_width_30px_basic_1280x720","wbank_width_50px_basic_1280x720", "wbank_width_70px_basic_1280x720",
			"wbank_width_36px_basic_1280x720", "wbank_width_173px_basic_1280x720", "wbank_width_200px_basic_1280x720",
			"wbank_width_238px_basic_1280x720", "wbank_width_528px_basic_1280x720", "wbank_width_122px_basic_1280x720",
		
			
			"wbank_width__116px_basic_800x480",
			
			"wbank_width_1px_basic_800x480", "wbank_width_2px_basic_800x480", "wbank_width_8px_basic_800x480",
			"wbank_width_10px_basic_800x480", "wbank_width_12px_basic_800x480", "wbank_width_14px_basic_800x480",
			"wbank_width_15px_basic_800x480", "wbank_width_16px_basic_800x480", "wbank_width_18px_basic_800x480",
			"wbank_width_20px_basic_800x480", "wbank_width_24px_basic_800x480", "wbank_width_25px_basic_800x480",
			"wbank_width_30px_basic_800x480", "wbank_width_36px_basic_800x480", "wbank_width_40px_basic_800x480",
			"wbank_width_50px_basic_800x480", "wbank_width_67.5px_basic_800x480", "wbank_width_90px_basic_800x480",
			"wbank_width_101px_basic_800x480", "wbank_width_116px_basic_800x480", "wbank_width_130px_basic_800x480",
			"wbank_width_136px_basic_800x480",
			"wbank_width_150px_basic_800x480", "wbank_width_156px_basic_800x480", "wbank_width_164px_basic_800x480",
			"wbank_width_310px_basic_800x480", "wbank_width_360px_basic_800x480",
			
			"wbank_height_14px_basic_1280x720","wbank_height_18px_basic_1280x720","wbank_height_22px_basic_1280x720",
			 "wbank_height_50px_basic_1280x720", "wbank_height_54px_basic_1280x720","wbank_height_126px_basic_1280x720",
			 "wbank_height_154px_basic_1280x720","wbank_height_292px_basic_1280x720",
			"wbank_height_402px_basic_1280x720", "wbank_height_100px_basic_1280x720", 
			"wbank_height__15px_basic_800x480",
			
			"wbank_height_1px_basic_800x480",
			"wbank_height_2px_basic_800x480", "wbank_height_3px_basic_800x480", "wbank_height_7px_basic_800x480",
			"wbank_height_8px_basic_800x480", "wbank_height_9px_basic_800x480", "wbank_height_10px_basic_800x480",
			"wbank_height_14px_basic_800x480", "wbank_height_15px_basic_800x480", "wbank_height_20px_basic_800x480",
			"wbank_height_24px_basic_800x480", "wbank_height_26px_basic_800x480", "wbank_height_30px_basic_800x480",
			"wbank_height_32px_basic_800x480", "wbank_height_36px_basic_800x480", "wbank_height_40px_basic_800x480",
			"wbank_height_43px_basic_800x480", "wbank_height_45px_basic_800x480", "wbank_height_50px_basic_800x480",
			"wbank_height_52px_basic_800x480", "wbank_height_56px_basic_800x480","wbank_height_68px_basic_800x480", 
			"wbank_height_75px_basic_800x480",
			"wbank_height_101px_basic_800x480", "wbank_height_120px_basic_800x480", "wbank_height_136px_basic_800x480",
			"wbank_height_154px_basic_800x480", "wbank_height_156px_basic_800x480", "wbank_height_164px_basic_800x480",
			"wbank_height_178px_basic_800x480", "wbank_height_183px_basic_800x480", "wbank_height_196px_basic_800x480",
			"wbank_height_226px_basic_800x480" };

	public static void main(String[] args) {
//		String a = "09";
//		String b = "1009";
//		String c = "0921";
//		System.out.println(c.indexOf(a));
		
//		testFreeswitch();
		creatPhoneValuesLibs();
//		System.out.println(isEmpty("    1"));
	}
	
	public static boolean isEmpty(String s){
        if(s == null || s.length() == 0 || s.trim().length() == 0){
            return true;
        }
        return false;
    }

	private static void creatPhoneValuesLibs() {
		try {
			for (int i = 0; i < phoneSArrays.length; i++) {
				createValueFileForPhoneS(phoneSArrays[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testFreeswitch() {
		// TODO Auto-generated method stub
		File file = new File(new File("").getAbsolutePath(),"jni");
		File soFile = new File(file.getAbsolutePath(),"libesljni.so");
		if (soFile.exists() && soFile.isFile()) {
			System.out.println(soFile.getAbsolutePath());
			System.load(soFile.getAbsolutePath());
		}
//		System.out.println(file.getAbsolutePath());
//		System.loadLibrary("");
	}

	private static void createValueFileForPhoneS(AndroidPx2DpUtils.PhoneS phoneS) throws IOException {
		File test = new File("");
		String parent = test.getAbsolutePath();
		// String folderName = "values-" + phoneS.dpiStr + (!phoneS.flag ? "-" +
		// phoneS.height + "x" + phoneS.width : "");
		String folderName = phoneS.valuesName;
		File parentFolder = new File(parent + "\\values\\" + folderName);
		System.out.println(parentFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		File stringXmlFile = new File(parentFolder, "String.xml");
		StringBuffer stringXmlText = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n<string name=\"current_dpi\">"
						+ folderName + "</string>\r\n" + "\r\n</resources>\r\n");
		createStringXML(stringXmlFile, stringXmlText);
		File dpXml = new File(parentFolder, "dimens.xml");
		StringBuffer dpText = new StringBuffer();
		dpText.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n");
		px2dpWrite2File(dpText, phoneS);
		px2spWrite2File(dpText, phoneS);
		dpText.append("\r\n</resources>\r\n");
		createDPXML(dpText, dpXml);
	}

	private static void createStringXML(File file, StringBuffer sb) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bytes = sb.toString().getBytes();
		fos.write(bytes, 0, bytes.length);
		fos.flush();
	}

	private static void createDPXML(StringBuffer sb, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bytes = sb.toString().getBytes();
		fos.write(bytes, 0, bytes.length);
		fos.flush();
	}

	private static void px2dpWrite2File(StringBuffer sb, AndroidPx2DpUtils.PhoneS phoneS) {
		for (int i = 0; i < usedDp.length; i++) {
			String s = usedDp[i];
			if (s.contains("wbank_width_")) {
				if (!sb.toString().contains("\r\n<!--宽度-->\r\n")) {
					sb.append("\r\n<!--宽度-->\r\n");
				}
				if (s.contains("wbank_width__")) {
					if (s.contains("px_basic_800x480")) {
						String px = s.substring(s.indexOf("wbank_width__") + "wbank_width__".length(),
								s.indexOf("px_basic_800x480"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_width__" + px + "px_basic_800x480\">" + "-"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getWidht() / 480.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					} else if (s.contains("px_basic_1280x720")) {
						String px = s.substring(s.indexOf("wbank_width_") + "wbank_width_".length(),
								s.indexOf("px_basic_1280x720"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_width_" + px + "px_basic_1280x720\">" + "-"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getWidht() / 720.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					}
				} else {
					if (s.contains("px_basic_800x480")) {
						String px = s.substring(s.indexOf("wbank_width_") + "wbank_width_".length(),
								s.indexOf("px_basic_800x480"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_width_" + px + "px_basic_800x480\">"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getWidht() / 480.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					} else if (s.contains("px_basic_1280x720")) {
						String px = s.substring(s.indexOf("wbank_width_") + "wbank_width_".length(),
								s.indexOf("px_basic_1280x720"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_width_" + px + "px_basic_1280x720\">"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getWidht() / 720.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					}
				}
			} else if (s.contains("wbank_height_")) {
				if (!sb.toString().contains("\r\n<!--高度-->\r\n")) {
					sb.append("\r\n<!--高度-->\r\n");
				}
				if (s.contains("wbank_height__")) {
					if (s.contains("px_basic_800x480")) {
						String px = s.substring(s.indexOf("wbank_height__") + "wbank_height__".length(),
								s.indexOf("px_basic_800x480"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_height__" + px + "px_basic_800x480\">" + "-" +

								Math.round(px2dp(phoneS.dp_px, phoneS.getHeight() / 800.0F * floatPx) * 100.0F) / 100.0F
								+ "dp</dimen>\r\n");
					} else if (s.contains("px_basic_1280x720")) {
						String px = s.substring(s.indexOf("wbank_height__") + "wbank_height__".length(),
								s.indexOf("px_basic_1280x720"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_height__" + px + "px_basic_1280x720\">" + "-" +

								Math.round(px2dp(phoneS.dp_px, phoneS.getHeight() / 1280.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					}
				} else {
					if (s.contains("px_basic_800x480")) {
						String px = s.substring(s.indexOf("wbank_height_") + "wbank_height_".length(),
								s.indexOf("px_basic_800x480"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_height_" + px + "px_basic_800x480\">"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getHeight() / 800.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					} else if (s.contains("px_basic_1280x720")) {
						String px = s.substring(s.indexOf("wbank_height_") + "wbank_height_".length(),
								s.indexOf("px_basic_1280x720"));
						float floatPx = Float.parseFloat(px);
						sb.append("<dimen name=\"wbank_height_" + px + "px_basic_1280x720\">"
								+ Math.round(px2dp(phoneS.dp_px, phoneS.getHeight() / 1280.0F * floatPx) * 100.0F)
										/ 100.0F
								+ "dp</dimen>\r\n");
					}
				}
			} else if (s.contains("wbank_textSize_")) {
				if (!sb.toString().contains("\r\n<!--字体大小-->\r\n")) {
					sb.append("\r\n<!--字体大小-->\r\n");
				}
				if (s.contains("px_basic_800x480")) {
					String px = s.substring(s.indexOf("wbank_textSize_") + "wbank_textSize_".length(),
							s.indexOf("px_basic_800x480"));
					float floatPx = Float.parseFloat(px);
					sb.append("<dimen name=\"wbank_textSize_" + px + "px_basic_800x480\">"
							+ Math.round(px2sp(phoneS.dp_px, phoneS.getWidht() / 480.0F * floatPx) * 100.0F) / 100.0F
							+ "sp</dimen>\r\n");
				} else if (s.contains("px_basic_1280x720")) {
					String px = s.substring(s.indexOf("wbank_textSize_") + "wbank_textSize_".length(),
							s.indexOf("px_basic_1280x720"));
					float floatPx = Float.parseFloat(px);
					sb.append("<dimen name=\"wbank_textSize_" + px + "px_basic_1280x720\">"
							+ Math.round(px2sp(phoneS.dp_px, phoneS.getWidht() / 720.0F * floatPx) * 100.0F) / 100.0F
							+ "sp</dimen>\r\n");
				}
			}
		}
	}

	private static void px2spWrite2File(StringBuffer sb, AndroidPx2DpUtils.PhoneS phoneS) {
	}

	public static float px2dp(float scale, float pxValue) {
		return pxValue / scale;
	}

	public static float px2sp(float scale, float pxValue) {
		return pxValue / scale;
	}

	public static class PhoneS {
		String id;
		int width;
		int height;
		float dp_px;
		String dpiStr;
		ValuesType type;
		String valuesName;

		/**
		 * 
		 * @param id
		 *            暂无定义
		 * @param width
		 *            像素宽度
		 * @param height
		 *            像素高度
		 * @param dpi
		 *            屏幕密度
		 * @param type
		 *            根据枚举值，设置valuesName
		 */
		public PhoneS(String id, int width, int height, int dpi, ValuesType type) {
			this.type = type;
			this.id = id;
			this.width = width;
			this.height = height;
			switch (dpi) {
			case 120:
				this.dp_px = 0.75F;
				setValuesName(type,"ldpi");
				break;
			case 160:
				this.dp_px = 1.0F;
				setValuesName(type,"mdpi");
				break;
			case 240:
				this.dp_px = 1.5F;
				setValuesName(type,"hdpi");
				break;
			case 320:
				this.dp_px = 2.0F;
				setValuesName(type,"xhdpi");
				break;
			case 440:
				this.dp_px = 2.75F;
				setValuesName(type,null);
				break;
			case 480:
				this.dp_px = 3.0F;
				setValuesName(type,"xxhdpi");
				break;
			case 640:
				this.dp_px = 4.0F;
				setValuesName(type,"xxxhdpi");
				break;
			default:
				this.dp_px = (int)((float)dpi/160f * 100f) / 100f;
				this.dpiStr = "0";
			}
		}
		
		/*
		 
public PhoneS(String id, int width, int height, int dpi, boolean flag) {
			this.flag = flag;
			this.id = id;
			this.width = width;
			this.height = height;
			switch (dpi) {
			case 120:
				this.dpi = 0.75F;
				if(flag){
					this.dpiStr = "ldpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			case 160:
				this.dpi = 1.0F;
				if(flag){
					this.dpiStr = "mdpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			case 240:
				this.dpi = 1.5F;
				if(flag){
					this.dpiStr = "hdpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			case 320:
				this.dpi = 2.0F;
				if(flag){
					this.dpiStr = "xhdpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			case 440:
				this.dpi = 2.75F;
				this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				break;
			case 480:
				this.dpi = 3.0F;
				if(flag){
					this.dpiStr = "xxhdpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			case 640:
				this.dpi = 4.0F;
				if(flag){
					this.dpiStr = "xxxhdpi";
				}else{
					this.dpiStr = "w"+(int)((float)width/this.dpi)+"dp";
				}
				break;
			default:
				this.dpi = 0.0F;
				this.dpiStr = "0";
			}
		}
 
		 */

		private void setValuesName(ValuesType type,String defaultName) {
			if (type == ValuesType.DPI && defaultName!=null) {
				this.dpiStr = defaultName;
				this.valuesName = "values-"+defaultName;
			} else if(type == ValuesType.W) {
				this.dpiStr = "w" + (int) ((float) width / this.dp_px) + "dp";
				this.valuesName = "values-"+"w" + (int) ((float) width / this.dp_px) + "dp-" + height+"x"+width;
			}
		}

		public String getId() {
			return this.id;
		}

		public float getWidht() {
			return this.width;
		}

		public float getHeight() {
			return this.height;
		}

		public float getDpi() {
			return this.dp_px;
		}
	}

	public static enum ValuesType {
		/**
		 * 最佳宽度dp适配
		 */
		W,
		/**
		 * 最小宽度dp适配
		 */
		SW,
		/**
		 * 标准适配
		 */
		DPI;
	}
	
	public static enum DPI{
		
		XXXHDPI(640),XXHDPI(480),XHDPI(320),HDPI(240),MDPI(160),LDPI(120);
		private int value;
		private DPI(int i){
			this.value = i;
		}
		
		public int getValue(){
			return value;
		}
	}

}
