package com.djy.android.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.djy.android.ValuesFolder;


public class Print2File {
	private static File getParentFile(ValuesFolder folder) {
		File test = new File("");
		String parent = test.getAbsolutePath();
		String folderName = folder.getFolderName();
		File parentFolder = new File(parent + "\\values\\" + folderName);
		System.out.println(parentFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		return parentFolder;
	}

	public static void createStringFile(ValuesFolder folder) {
		// TODO Auto-generated method stub

		String str = FileValueReplace.XML_TAG.replace(FileValueReplace.replaceXmlValue,
				FileValueReplace.STRING_TAG.replace(FileValueReplace.replaceStringValue, folder.getFolderName()));
		try {
			createXML(new File(getParentFile(folder), FileName.STRING_FILE_NAME), new StringBuffer(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createDimenFile(ValuesFolder folder) {
		// TODO Auto-generated method stub
		String dpStr = "\n<!--字体大小-->\n" + folder.getTextSizeTag() + "\n<!--宽度-->\n" + folder.getWidthTag()
				+ "\n<!--高度-->\n" + folder.getHeightTag();
		String str = FileValueReplace.XML_TAG.replace(FileValueReplace.replaceXmlValue, dpStr);
		try {
			createXML(new File(getParentFile(folder), FileName.DP_FILE_NAME), new StringBuffer(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createXML(File file, StringBuffer sb) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bytes = sb.toString().getBytes();
		fos.write(bytes, 0, bytes.length);
		fos.flush();
	}
}
