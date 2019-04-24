package com.example.demo.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 
 * @描述: 对象操作
 * @作者: benny.he
 * @版本: 1.0 .
 */

public class StrUtils extends StringUtils {

	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 将驼峰式命名的字符串转换为_的形式 如userName 转换为 user_name
	 *
	 * @param name
	 * @return
	 */
	public static String underscoreName(String name) {
		if (StrUtils.isBlank(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(name.substring(0, 1).toLowerCase());
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = s.toLowerCase();
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			} else {
				result.append(s);
			}
		}
		return result.toString();

	}

	/**
	 * ********************************************************************
	 * 驼峰式命名转换
	 * ********************************************************************
	 */
	private static final char SEPARATOR = '_';

	/**
	 * 驼峰式字符转换为下杠形式 ISOCertifiedStaff -> iso_certified_staff CertifiedStaff ->
	 * certified_staff UserID -> user_id
	 *
	 * @param s
	 *            驼峰式字符串
	 * @return 带下杠的字符串
	 */
	public static String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 下杠式字符转换为驼峰形式 iso_certified_staff -> isoCertifiedStaff certified_staff ->
	 * certifiedStaff user_id -> userId
	 *
	 * @param s
	 *            带下杠的字符串
	 * @return 驼峰式字符串
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 下杠式字符转换为驼峰形式，但首字符大写 iso_certified_staff -> IsoCertifiedStaff
	 * certified_staff -> CertifiedStaff user_id -> UserId
	 *
	 * @param s
	 *            带下杠的字符串
	 * @return 驼峰式字符串
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/*********************************************************
	 * 字符串首字母处理
	 ********************************************************/
	/**
	 * 字符串首字母小写
	 *
	 * @param s
	 * @return
	 */
	public static String toLowerFirst(String s) {
		char[] chars = new char[1];
		chars[0] = s.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'A' && chars[0] <= 'Z') {// 当为字母时，则转换为小写
			return s.replaceFirst(temp, temp.toLowerCase());
		}
		return s;
	}

	/**
	 * 字符串首字母大写
	 *
	 * @param s
	 * @return
	 */
	public static String toUpperFirst(String s) {
		char[] chars = new char[1];
		chars[0] = s.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'a' && chars[0] <= 'z') {// 当为字母时，则转换为小写
			s.replaceFirst(temp, temp.toUpperCase());
		}
		return s;
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 替换掉HTML标签
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceHtml(String html) {
		if (org.apache.commons.lang3.StringUtils.isBlank(html)) {
			return "";
		}

		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 *
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果。例如：row.user.id
	 * 返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 *
	 * @param objectString
	 *            对象串
	 * @return
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = org.apache.commons.lang3.StringUtils.split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}
}
