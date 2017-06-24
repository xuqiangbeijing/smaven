package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class StringUtil {

	/**
	 * 
	 * 功能: 字符串工具
	 * 作者: LIUTIANFANG
	 * 日期: 2011-8-5 上午11:23:12
	 */

	public static final String EMPTY = "";

	/**
	 * 
	 * Title: 判空工具
	 * @param str 源字符串
	 * @return 空返回真，反之假
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 
	 * Title: 判非空工具
	 * @param str 源字符串
	 * @return 非空返回真，反之假
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 
	 * Title: 判空白工具
	 * @param str 源字符串
	 * @return 空返回真，反之假
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	/**
	 * Title: 判非空白工具
	 * @param str 源字符串
	 * @return 非空白返回真，反之真
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 
	 * Title:
	 * Description:
	 * @param fileFrom
	 * @param fileToPath
	 * @param fileToName
	 * @return
	 */
	public static void CopyFile(InputStream in, String fileToPath,
			String fileToName) throws IOException {
		BufferedInputStream inStream = null;

		FileOutputStream fileOutput = null;

		BufferedOutputStream outStream = null;

		String fileRootPath = fileToPath;

		File toPath = new File(fileRootPath + File.separator);
		if (!toPath.isDirectory()) {
			toPath.mkdirs();
		}

		try {
			inStream = new BufferedInputStream(in);

			fileOutput = new FileOutputStream(toPath + File.separator
					+ fileToName);
			outStream = new BufferedOutputStream(fileOutput);

			byte[] buffer = new byte[4096];
			int bytes_read = 0;

			while ((bytes_read = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytes_read);
			}
			outStream.flush();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException ex) {

				}
			}

			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException ex) {

				}
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Title: FormatFileSize
	 * </p>
	 * <p>
	 * Description: 格式化文件大小
	 * </p>
	 * 
	 * @param fileSize
	 * @return fileSizeString 格式化后大小
	 */
	public static String padZero(Integer value) {
		DecimalFormat df = new DecimalFormat("0000");
		return df.format(value);
	}

}
