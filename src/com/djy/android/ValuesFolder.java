package com.djy.android;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.djy.android.print.FileValueReplace;

public class ValuesFolder {
	private float baseHeight;// 基准分辨率高度
	private float currentHeight;// 当前分辨率高度
	private float baseWidth;// 基准分辨率宽度
	private float currentWidth;// 当前分辨率宽度
	private float currentDpi;// 当前设备像素密度
	private float baseDpi;
	private float baseSWWidthDp;// 标准设备宽度最大dp
	private float currentSWWidthDP;// 当前设备最大宽度dp
	private float baseSWHeightDp;// 标准设备高度最大dp
	private float currentSWHeightDp;// 当前设备高度对打dp
	private final float basiDpi = 160f;// 密度基数
	private float flag;// flag = px / dp = px值/dp值
	private Map<String, String> widthBasePxMap;
	private Map<String, String> heightBasePxMap;
	private Map<String, String> textsizeBasePxMap;

	public ValuesFolder(int height, int width, int dpi) {
		super();
		if (dpi == 120 || (height == 320 && width == 240)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 320f / 0.75f;
			this.baseHeight = 320;
			this.baseSWWidthDp = 240f / 0.75f;
			this.baseWidth = 240;
			this.baseDpi = 120;
			this.flag = baseDpi / basiDpi;
		}else if (dpi == 160 || (height == 480 && width == 320)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 480f / 1f;
			this.baseHeight = 480;
			this.baseSWWidthDp = 320f / 1f;
			this.baseWidth = 320;
			this.baseDpi = 160;
			this.flag = baseDpi / basiDpi;
		}else  if (dpi == 240 || (height == 800 && width == 480)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 800f / 1.5f;
			this.baseHeight = 800;
			this.baseSWWidthDp = 480f / 1.5f;
			this.baseWidth = 480;
			this.baseDpi = 240;
			this.flag = baseDpi / basiDpi;
		}else   if (dpi == 320 || (height == 1280 && width == 720)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 1280f / 2f;
			this.baseHeight = 1280;
			this.baseSWWidthDp = 720f / 2f;
			this.baseWidth = 720;
			this.baseDpi = 320;
			this.flag = baseDpi / basiDpi;
		}else   if (dpi == 480 || (height == 1920 && width == 1080)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 1920f / 3f;
			this.baseHeight = 1920;
			this.baseSWWidthDp = 1080f / 3f;
			this.baseWidth = 1080;
			this.baseDpi = 480;
			this.flag = baseDpi / basiDpi;
		}else if (dpi == 640 || (height == 2560 && width == 1440)) {//如果分辨率或者dpi有匹配标准的，就按照标准取基准值；
			this.baseSWHeightDp = 2560f / 4f;
			this.baseHeight = 2560;
			this.baseSWWidthDp = 1440f / 4f;
			this.baseWidth = 1440;
			this.baseDpi = 640;
			this.flag = baseDpi / basiDpi;
		}
		this.currentHeight = height;
		this.currentWidth = width;
		this.currentDpi = dpi;
		if(this.flag < 1){this.flag = dpi / basiDpi;}
		this.currentSWWidthDP = currentWidth / (dpi / basiDpi);
		this.currentSWHeightDp = currentHeight / (dpi / basiDpi);
		this.widthBasePxMap = new HashMap();
		this.heightBasePxMap = new HashMap();
		this.textsizeBasePxMap = new HashMap();
	}

	public void addAllBasePx(String[] basePx) {
		for (int i = 0; i < basePx.length; i++) {
			if (basePx[i].contains("height")) {
				addHeightBasePx(basePx[i]);
			} else if (basePx[i].contains("width")) {
				addWidthBasePx(basePx[i]);
			} else if (basePx[i].contains("textSize")) {
				addTextsizeBasePx(basePx[i]);
			}
		}
	}

	/**
	 * "wbank_basic_800x480_240dpi_sw320dp_width_14px",
	 * "wbank_width_1px_basic_800x480"
	 * @param basePx
	 */
	public void addHeightBasePx(String basePx) {
		String[] split = basePx.split("_");
		int[] baseScreen={};
//		int baseDpi;
//		int baseSWWidthDP;
		float px = 0 ;
		for (String str : split) {
			if (str.contains("x") &&!str.contains("px")) {
				baseScreen = new int[] { Integer.parseInt(str.split("x")[0]), Integer.parseInt(str.split("x")[1]) };
				continue;
			}
			if (str.contains("dpi")) {
//				baseDpi = Integer.parseInt(str.substring(0, str.indexOf("dpi")));
				continue;
			}
			if (str.contains("sw")) {
//				baseSWWidthDP = Integer.parseInt(str.substring(str.indexOf("sw") + "sw".length(), str.indexOf("dp")));
				continue;
			}
			if (str.contains("px")) {
				px = Float.parseFloat(str.substring(0,str.indexOf("px")));
				continue;
			}
		}
		String currentDp = "";
		if (this.baseDpi > 1f && currentDpi > 1) {//标准dpi的屏幕
			if (this.baseDpi == currentDpi) {//标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[0]*currentHeight/flag);
			}else{//非标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[0]*currentHeight/flag/this.baseSWHeightDp*this.currentSWHeightDp);
			}
		}else if(this.baseDpi<1 && currentDpi>1){
			currentDp = MathUtils.floatAfterPoint2(px/flag);
		}
		if (currentDp!=null&&currentDp.length()!=0 && !currentDp.equals("0")) {
			if (basePx.contains("__")) {
				currentDp = "-"+currentDp;
			}
			heightBasePxMap.put(basePx, currentDp);
		}
	}

	/**
	 * "wbank_basic_1280x720_320dpi_sw360dp_width_50px",
	 * "wbank_width_1px_basic_800x480"
	 * @param basePx
	 */
	public void addWidthBasePx(String basePx) {
	
		String[] split = basePx.split("_");
		int[] baseScreen={};
//		int baseDpi;
//		int baseSWWidthDP;
		float px = 0 ;
		for (String str : split) {
			if (str.contains("x") &&!str.contains("px")) {
				baseScreen = new int[] { Integer.parseInt(str.split("x")[0]), Integer.parseInt(str.split("x")[1]) };
				continue;
			}
			if (str.contains("dpi")) {
//				baseDpi = Integer.parseInt(str.substring(0, str.indexOf("dpi")));
				continue;
			}
			if (str.contains("sw")) {
//				baseSWWidthDP = Integer.parseInt(str.substring(str.indexOf("sw") + "sw".length(), str.indexOf("dp")));
				continue;
			}
			if (str.contains("px")) {
				px = Float.parseFloat(str.substring(0,str.indexOf("px")));
				continue;
			}
		}
		String currentDp = "";
		if (this.baseDpi > 1f && currentDpi > 1) {//标准dpi的屏幕
			if (this.baseDpi == currentDpi) {//标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[1]*currentWidth/flag);
			}else{//非标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[1]*currentWidth/flag/this.baseSWWidthDp*this.currentSWWidthDP);
			}
		}else if(this.baseDpi<1 && currentDpi>1){
			currentDp = MathUtils.floatAfterPoint2(px/flag);
		}
		if (currentDp!=null&&currentDp.length()!=0 && !currentDp.equals("0")) {
			if (basePx.contains("__")) {
				currentDp = "-"+currentDp;
			}
			widthBasePxMap.put(basePx, currentDp);
		}
	}

	/**
	 * 
	 * 
	 * "wbank_basic_800x480_240dpi_sw320dp_textSize_24px",
	 * "wbank_width_1px_basic_800x480"
	 * @param basePx
	 */
	public void addTextsizeBasePx(String basePx) {
		
		String[] split = basePx.split("_");
		int[] baseScreen={};
//		int baseDpi;
//		int baseSWWidthDP;
		float px = 0 ;
		for (String str : split) {
			if (str.contains("x") &&!str.contains("px")&&!str.contains("text")) {
				baseScreen = new int[] { Integer.parseInt(str.split("x")[0]), Integer.parseInt(str.split("x")[1]) };
				continue;
			}
			if (str.contains("dpi")) {
//				baseDpi = Integer.parseInt(str.substring(0, str.indexOf("dpi")));
				continue;
			}
			if (str.contains("sw")) {
//				baseSWWidthDP = Integer.parseInt(str.substring(str.indexOf("sw") + "sw".length(), str.indexOf("dp")));
				continue;
			}
			if (str.contains("px")) {
				px = Float.parseFloat(str.substring(0,str.indexOf("px")));
				continue;
			}
		}
		String currentDp = "";
		if (this.baseDpi > 1f && currentDpi > 1) {//标准dpi的屏幕
			if (this.baseDpi == currentDpi) {//标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[1]*currentWidth/flag);//字体按照标准取值
			}else{//非标准dpi的屏幕
				currentDp = MathUtils.floatAfterPoint2(px/baseScreen[1]*currentWidth/flag/this.baseSWWidthDp*this.currentSWWidthDP);
			}
		}else if(this.baseDpi<1 && currentDpi>1){
			currentDp = MathUtils.floatAfterPoint2(px/flag);
		}
		if (currentDp!=null&&currentDp.length()!=0 && !currentDp.equals("0")) {
			if (basePx.contains("__")) {
				currentDp = "-"+currentDp;
			}
			textsizeBasePxMap.put(basePx, currentDp);
		}
		
	}
	public String getFolderName() {
		return "values-sw" + (int)currentSWWidthDP + "dp-"
				+ MathUtils.floatAfterPoint2(currentHeight) + "x" + MathUtils.floatAfterPoint2(currentWidth);
	}

	public static void main(String[] args) {
		System.out.println(new ValuesFolder(800, 480, 240).getFolderName());
	}

	@Override
	public String toString() {
		return "ValuesFolder [baseHeight=" + baseHeight + ", currentHeight=" + currentHeight + ", baseWidth="
				+ baseWidth + ", currentWidth=" + currentWidth + ", currentDpi=" + currentDpi + ", baseDpi=" + baseDpi
				+ ", baseSWWidthDp=" + baseSWWidthDp + ", currentSWWidthDP=" + currentSWWidthDP + ", baseSWHeightDp="
				+ baseSWHeightDp + ", currentSWHeightDp=" + currentSWHeightDp + ", basiDpi=" + basiDpi + ", flag="
				+ flag + ", widthBasePxMap=" + widthBasePxMap + ", heightBasePxMap=" + heightBasePxMap
				+ ", textsizeBasePxMap=" + textsizeBasePxMap + "]";
	}

	public static class MathUtils {
		public static String floatAfterPoint2(float f) {
			float newF = (int) ((f + 0.005) * 100) / 100f;
			String str = newF + "";
			if (str.contains(".")) {
				String strNew = str.substring(str.indexOf(".") + 1, str.length());
				if (strNew.replaceAll("0", "").length() == 0) {
					return str.substring(0, str.indexOf("."));
				} else {
					return str;
				}
			} else {
				return str;
			}

		}
	}

	public String getTextSizeTag() {
		// TODO Auto-generated method stub
		String str = "";
		if (textsizeBasePxMap.size() > 0) {
			Set<String> keySet = textsizeBasePxMap.keySet();
			for(String baseStr:keySet){
				str += FileValueReplace.DP_TAG.replace(FileValueReplace.replaceBasicScreenAndPx, baseStr).replace(FileValueReplace.replaceCurrentDp, textsizeBasePxMap.get(baseStr));
			}
		}
		return str;
	}

	public String getWidthTag() {
		// TODO Auto-generated method stub
		String str = "";
		if (widthBasePxMap.size() > 0) {
			Set<String> keySet = widthBasePxMap.keySet();
			for(String baseStr:keySet){
				str += FileValueReplace.DP_TAG.replace(FileValueReplace.replaceBasicScreenAndPx, baseStr).replace(FileValueReplace.replaceCurrentDp, widthBasePxMap.get(baseStr));
			}
		}
		return str;
	}

	public String getHeightTag() {
		// TODO Auto-generated method stub
		String str = "";
		if (heightBasePxMap.size() > 0) {
			Set<String> keySet = heightBasePxMap.keySet();
			for(String baseStr:keySet){
				str += FileValueReplace.DP_TAG.replace(FileValueReplace.replaceBasicScreenAndPx, baseStr).replace(FileValueReplace.replaceCurrentDp, heightBasePxMap.get(baseStr));
			}
		}
		return str;
	}
}
