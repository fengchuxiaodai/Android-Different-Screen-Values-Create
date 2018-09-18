package com.djy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.djy.AndroidPx2DpUtils.DPI;
import com.djy.AndroidPx2DpUtils.ValuesType;
import com.djy.android.BasePx;
import com.djy.android.ValuesFolder;
import com.djy.android.print.Print2File;

public class AndroidPx2DpSpUtils {/*

	private static String[] basicScreenAndPx = {};

	*//**
	 * 文件名开头
	 *//*
	private static final String FOLDER_NAME_START = "values-";
	*//**
	 * dimen文件名结尾
	 *//*
	private static final String DP_FILE_NAME = "dimens.xml";
	*//**
	 * string 文件名结尾
	 *//*
	private static final String STRING_FILE_NAME = "string.xml";
	private static final String replaceXmlValue = "replaceXmlValue";
	*//**
	 * 文档的开头和结尾
	 *//*
	private static final String XML_TAG = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n"
			+ replaceXmlValue + "\r\n</resources>\r\n";
	*//**
	 * string 标签的 替换 内容
	 *//*
	private static final String replaceStringValue = "replaceSringValue";
	*//**
	 * string 标签的开头和结尾
	 *//*
	private static final String STRING_TAG = "<string name=\"current_dpi\">" + replaceStringValue + "</string>\r\n";

	private static final String replaceBasicScreenAndPx = "replaceBasicScreenAndPx";
	private static final String replaceCurrentDp = "replaceCurrentDp";

	*//**
	 * dimen中 宽度标签的开头 基准 值 实际值 结尾
	 * 例子<dimen name="basic_800x480_240dpi_sw320dp_1px">0.67dp</dimen>
	 *//*
	private static final String WIDTH_TAG = "<dimen name=\"basic_" + replaceBasicScreenAndPx + "\">" + replaceCurrentDp
			+ "dp</dimen>\r\n";
	*//**
	 * dimen中 高度标签的开头 基准 值 实际值 结尾
	 *//*
	private static final String HEIGHT_TAG = "<dimen name=\"basic_" + replaceBasicScreenAndPx + "\">" + replaceCurrentDp
			+ "dp</dimen>\r\n";
	*//**
	 * dimen中 字体大小标签的开头 基准 值 实际值 结尾
	 *//*
	private static final String TEXTSIZE_TAG = "<dimen name=\"basic_" + replaceBasicScreenAndPx + "\">"
			+ replaceCurrentDp + "sp</dimen>\r\n";

	private final static String[] usedDp = { "wbank_basic_800x480_240dpi_sw320dp_textSize_13px",
			"wbank_basic_800x480_240dpi_sw320dp_textSize_10px", "wbank_basic_800x480_240dpi_sw320dp_textSize_15px",
			"wbank_basic_800x480_240dpi_sw320dp_textSize_18px", "wbank_basic_800x480_240dpi_sw320dp_textSize_20px",
			"wbank_basic_800x480_240dpi_sw320dp_textSize_22px", "wbank_basic_800x480_240dpi_sw320dp_textSize_24px",
			"wbank_basic_800x480_240dpi_sw320dp_textSize_27px", "wbank_basic_800x480_240dpi_sw320dp_textSize_32px",

			"wbank_basic_1280x720_320dpi_sw360dp_textSize_24px", "wbank_basic_1280x720_320dpi_sw360dp_textSize_28px",

			"wbank_basic_1280x720_320dpi_sw360dp_width_6px", "wbank_basic_1280x720_320dpi_sw360dp_width_9px",
			"wbank_basic_1280x720_320dpi_sw360dp_width_12px", "wbank_basic_1280x720_320dpi_sw360dp_width_30px",
			"wbank_basic_1280x720_320dpi_sw360dp_width_50px", "wbank_basic_1280x720_320dpi_sw360dp_width_70px",
			"wbank_basic_1280x720_320dpi_sw360dp_width_36px", "wbank_basic_1280x720_320dpi_sw360dp_width_122px",
			"wbank_basic_1280x720_320dpi_sw360dp_width_173px", "wbank_basic_1280x720_320dpi_sw360dp_width_200px",
			"wbank_basic_1280x720_320dpi_sw360dp_width_238px", "wbank_basic_1280x720_320dpi_sw360dp_width_528px",

			"wbank_basic_800x480_240dpi_sw320dp_width__116px",

			"wbank_basic_800x480_240dpi_sw320dp_width_1px", "wbank_basic_800x480_240dpi_sw320dp_width_2px",
			"wbank_basic_800x480_240dpi_sw320dp_width_8px", "wbank_basic_800x480_240dpi_sw320dp_width_10px",
			"wbank_basic_800x480_240dpi_sw320dp_width_12px", "wbank_basic_800x480_240dpi_sw320dp_width_14px",
			"wbank_basic_800x480_240dpi_sw320dp_width_15px", "wbank_basic_800x480_240dpi_sw320dp_width_16px",
			"wbank_basic_800x480_240dpi_sw320dp_width_18px", "wbank_basic_800x480_240dpi_sw320dp_width_20px",
			"wbank_basic_800x480_240dpi_sw320dp_width_24px", "wbank_basic_800x480_240dpi_sw320dp_width_25px",
			"wbank_basic_800x480_240dpi_sw320dp_width_30px", "wbank_basic_800x480_240dpi_sw320dp_width_36px",
			"wbank_basic_800x480_240dpi_sw320dp_width_40px", "wbank_basic_800x480_240dpi_sw320dp_width_50px",
			"wbank_basic_800x480_240dpi_sw320dp_width_67.5px", "wbank_basic_800x480_240dpi_sw320dp_width_90px",
			"wbank_basic_800x480_240dpi_sw320dp_width_101px", "wbank_basic_800x480_240dpi_sw320dp_width_116px",
			"wbank_basic_800x480_240dpi_sw320dp_width_130px", "wbank_basic_800x480_240dpi_sw320dp_width_136px",
			"wbank_basic_800x480_240dpi_sw320dp_width_150px", "wbank_basic_800x480_240dpi_sw320dp_width_156px",
			"wbank_basic_800x480_240dpi_sw320dp_width_164px", "wbank_basic_800x480_240dpi_sw320dp_width_310px",
			"wbank_basic_800x480_240dpi_sw320dp_width_360px",

			"wbank_basic_1280x720_320dpi_sw360dp_height_14px", "wbank_basic_1280x720_320dpi_sw360dp_height_18px",
			"wbank_basic_1280x720_320dpi_sw360dp_height_22px", "wbank_basic_1280x720_320dpi_sw360dp_height_50px",
			"wbank_basic_1280x720_320dpi_sw360dp_height_54px", "wbank_basic_1280x720_320dpi_sw360dp_height_100px",
			"wbank_basic_1280x720_320dpi_sw360dp_height_126px", "wbank_basic_1280x720_320dpi_sw360dp_height_154px",
			"wbank_basic_1280x720_320dpi_sw360dp_height_292px", "wbank_basic_1280x720_320dpi_sw360dp_height_402px",

			"wbank_basic_800x480_240dpi_sw320dp_height__15px",

			"wbank_basic_800x480_240dpi_sw320dp_height_1px", "wbank_basic_800x480_240dpi_sw320dp_height_2px",
			"wbank_basic_800x480_240dpi_sw320dp_height_3px", "wbank_basic_800x480_240dpi_sw320dp_height_7px",
			"wbank_basic_800x480_240dpi_sw320dp_height_8px", "wbank_basic_800x480_240dpi_sw320dp_height_9px",
			"wbank_basic_800x480_240dpi_sw320dp_height_10px", "wbank_basic_800x480_240dpi_sw320dp_height_14px",
			"wbank_basic_800x480_240dpi_sw320dp_height_15px", "wbank_basic_800x480_240dpi_sw320dp_height_20px",
			"wbank_basic_800x480_240dpi_sw320dp_height_24px", "wbank_basic_800x480_240dpi_sw320dp_height_26px",
			"wbank_basic_800x480_240dpi_sw320dp_height_30px", "wbank_basic_800x480_240dpi_sw320dp_height_32px",
			"wbank_basic_800x480_240dpi_sw320dp_height_36px", "wbank_basic_800x480_240dpi_sw320dp_height_40px",
			"wbank_basic_800x480_240dpi_sw320dp_height_43px", "wbank_basic_800x480_240dpi_sw320dp_height_45px",
			"wbank_basic_800x480_240dpi_sw320dp_height_50px", "wbank_basic_800x480_240dpi_sw320dp_height_52px",
			"wbank_basic_800x480_240dpi_sw320dp_height_56px", "wbank_basic_800x480_240dpi_sw320dp_height_68px",
			"wbank_basic_800x480_240dpi_sw320dp_height_75px", "wbank_basic_800x480_240dpi_sw320dp_height_101px",
			"wbank_basic_800x480_240dpi_sw320dp_height_120px", "wbank_basic_800x480_240dpi_sw320dp_height_136px",
			"wbank_basic_800x480_240dpi_sw320dp_height_154px", "wbank_basic_800x480_240dpi_sw320dp_height_156px",
			"wbank_basic_800x480_240dpi_sw320dp_height_164px", "wbank_basic_800x480_240dpi_sw320dp_height_178px",
			"wbank_basic_800x480_240dpi_sw320dp_height_183px", "wbank_basic_800x480_240dpi_sw320dp_height_196px",
			"wbank_basic_800x480_240dpi_sw320dp_height_226px" };

	public static void main(String[] args) {

	}

	private static void makeValues(Device... devices) {
		if (devices == null) {
			return;

		}
		if (devices.length == 1) {
			createStringFile(devices[0]);
			createDimenFile(devices[0]);
			return;
		}
		for (Device device : devices) {
			createStringFile(device);
			createDimenFile(device);
		}
	}

	private static File getParentFile(Device device) {
		File test = new File("");
		String parent = test.getAbsolutePath();
		// String folderName = "values-" + phoneS.dpiStr + (!phoneS.flag ? "-" +
		// phoneS.height + "x" + phoneS.width : "");
		String folderName = device.getValuesFolderName();
		File parentFolder = new File(parent + "\\values\\" + folderName);
		System.out.println(parentFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		return parentFolder;
	}

	private static void createStringFile(Device device) {
		// TODO Auto-generated method stub

		String str = XML_TAG.replace(replaceXmlValue,
				STRING_TAG.replace(replaceStringValue, device.getValuesFolderName()));
		try {
			createStringXML(new File(getParentFile(device), STRING_FILE_NAME), new StringBuffer(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createDimenFile(Device device) {
		// TODO Auto-generated method stub
		String dpStr = "\n<!--字体大小-->\n" + getTextSizeTag(device) + "\n<!--宽度-->\n" + getWidthTag(device)
				+ "\n<!--高度-->\n" + getHeightTag(device);
		String str = XML_TAG.replace(replaceXmlValue, dpStr);
		try {
			createStringXML(new File(getParentFile(device), DP_FILE_NAME), new StringBuffer(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private static String getWidthTag(Device device) {
		String str = "";
		if (device.getWidthBasePxMap() == null || device.getWidthBasePxMap().size() == 0) {
			return str;
		}
		Set<Float> keySet = device.getWidthBasePxMap().keySet();
		for (Float f : keySet) {
			str += WIDTH_TAG.replace(replaceBasicScreenAndPx, device.getBasicScreenAndPx(f)).replace(replaceCurrentDp,
					device.getPx2DpOrSp(f) + "");
		}
		return str;
	}

	private static String getHeightTag(Device device) {
		String str = "";
		if (device.getHeightBasePxMap() == null || device.getHeightBasePxMap().size() == 0) {
			return str;
		}
		Set<Float> keySet = device.getHeightBasePxMap().keySet();
		for (Float f : keySet) {
			str += HEIGHT_TAG.replace(replaceBasicScreenAndPx, device.getBasicScreenAndPx(f)).replace(replaceCurrentDp,
					device.getPx2DpOrSp(f) + "");
		}
		return str;

	}

	private static String getTextSizeTag(Device device) {
		String str = "";
		if (device.getTextsizeBasePxMap() == null || device.getTextsizeBasePxMap().size() == 0) {
			return str;
		}
		Set<Float> keySet = device.getTextsizeBasePxMap().keySet();
		for (Float f : keySet) {
			str += TEXTSIZE_TAG.replace(replaceBasicScreenAndPx, device.getBasicScreenAndPx(f))
					.replace(replaceCurrentDp, device.getPx2DpOrSp(f) + "");
		}
		return str;
	}
*/
	

	

//	private static final ValuesFolder LDPI = new ValuesFolder(320,240,  DPI.LDPI.getValue());
//	private static final ValuesFolder MDPI = new ValuesFolder(480, 320, DPI.MDPI.getValue());
//	private static final ValuesFolder MDPI_480x320 = new ValuesFolder( 480,320, DPI.MDPI.getValue());

	private static final ValuesFolder HDPI = new ValuesFolder( 800, 480, DPI.HDPI.getValue());
	private static final ValuesFolder HDPI_800x480 = new ValuesFolder( 800, 480, DPI.HDPI.getValue());
	private static final ValuesFolder HDPI_1848x1200 = new ValuesFolder(1848, 1200,  DPI.HDPI.getValue());
	
	private static final ValuesFolder XHDPI = new ValuesFolder(1280, 720, DPI.XHDPI.getValue());
	private static final ValuesFolder XHDPI_960x640 = new ValuesFolder(960,640,  DPI.XHDPI.getValue());
	private static final ValuesFolder XHDPI_1000x720 = new ValuesFolder(1000,720,  DPI.XHDPI.getValue());
	private static final ValuesFolder XHDPI_1280x720 = new ValuesFolder(1280, 720,  DPI.XHDPI.getValue());
	
	
	private static final ValuesFolder DPI440 = new ValuesFolder(1920,  1080, 440);

	private static final ValuesFolder XXHDPI = new ValuesFolder(1920, 1080, DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_1280x720 = new ValuesFolder(1280,720,  DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_1800x1080 = new ValuesFolder(1800, 1080,  DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_1920x1080 = new ValuesFolder(1920, 1080,  DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2094x1080 = new ValuesFolder(2094,1080,  DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2220x1080 = new ValuesFolder(2220, 1080,  DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2392x1440 = new ValuesFolder(2392, 1440, DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2560x1440 = new ValuesFolder( 2560,1440, DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2792x1440 = new ValuesFolder( 2792,1440, DPI.XXHDPI.getValue());
	private static final ValuesFolder XXHDPI_2960x1440 = new ValuesFolder(2960, 1440,  DPI.XXHDPI.getValue());
	
	private static final ValuesFolder XXXHDPI = new ValuesFolder(2560, 1440, DPI.XXXHDPI.getValue());
	private static final ValuesFolder XXXHDPI_2392x1440 = new ValuesFolder(2392,  1440, DPI.XXXHDPI.getValue());
	private static final ValuesFolder XXXHDPI_2560x1440 = new ValuesFolder(2560, 1440,  DPI.XXXHDPI.getValue());
	private static final ValuesFolder XXXHDPI_2792x1440 = new ValuesFolder(2792,1440,  DPI.XXXHDPI.getValue());
	private static final ValuesFolder XXXHDPI_2960x1440 = new ValuesFolder(2960, 1440,  DPI.XXXHDPI.getValue());
	
	
	private static final ValuesFolder[] phoneSArrays = { /*LDPI, MDPI, MDPI_480x320,*/ HDPI, HDPI_800x480,
			HDPI_1848x1200, XHDPI, XHDPI_1280x720, XHDPI_960x640, XHDPI_1000x720, XXHDPI, DPI440, XXHDPI_1920x1080,
			XXHDPI_1280x720, XXHDPI_1800x1080, XXHDPI_2094x1080, XXHDPI_2220x1080, XXHDPI_2392x1440, XXHDPI_2560x1440,
			XXHDPI_2792x1440, XXHDPI_2960x1440, XXXHDPI, XXXHDPI_2392x1440, XXXHDPI_2560x1440, XXXHDPI_2792x1440,
			XXXHDPI_2960x1440 };
	
	public static void main(String[] args) {
		for(ValuesFolder  valuesFolder: phoneSArrays){
			valuesFolder.addAllBasePx(BasePx.usedDp);
			Print2File.createDimenFile(valuesFolder);
			Print2File.createStringFile(valuesFolder);
		}
	}
}
