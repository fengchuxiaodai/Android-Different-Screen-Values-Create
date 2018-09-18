package com.djy.android.print;

public class FileValueReplace {
	public static final String replaceXmlValue = "replaceXmlValue";
	/**
	 * 文档的开头和结尾
	 */
	public static final String XML_TAG = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n"
			+ replaceXmlValue + "\r\n</resources>\r\n";
	/**
	 * string 标签的 替换 内容
	 */
	public static final String replaceStringValue = "replaceSringValue";
	/**
	 * string 标签的开头和结尾
	 */
	public static final String STRING_TAG = "<string name=\"current_dpi\">" + replaceStringValue + "</string>\r\n";

	public static final String replaceBasicScreenAndPx = "replaceBasicScreenAndPx";
	public static final String replaceCurrentDp = "replaceCurrentDp";

	/**
	 * dimen中 dp标签的开头 基准 值 实际值 结尾
	 * 例子<dimen name="basic_800x480_240dpi_sw320dp_1px">0.67dp</dimen>
	 */
//	public static final String DP_TAG = "<dimen name=\"basic_" + replaceBasicScreenAndPx + "\">" + replaceCurrentDp
//			+ "dp</dimen>\r\n";
	public static final String DP_TAG = "<dimen name=\"" + replaceBasicScreenAndPx + "\">" + replaceCurrentDp
			+ "dp</dimen>\r\n";
	
	/**
	 * dimen中 字体大小标签的开头 基准 值 实际值 结尾
	 */
//	public static final String SP_TAG = "<dimen name=\"basic_" + replaceBasicScreenAndPx + "\">"
//			+ replaceCurrentDp + "sp</dimen>\r\n";
	public static final String SP_TAG = "<dimen name=\"" + replaceBasicScreenAndPx + "\">"
			+ replaceCurrentDp + "sp</dimen>\r\n";

}
