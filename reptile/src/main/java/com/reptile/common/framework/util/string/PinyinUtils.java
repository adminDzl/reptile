package com.reptile.common.framework.util.string;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.util.StringUtils;

/**
 * 汉语拼音 帮助类
 */
public class PinyinUtils {
	
	/**
	 * 获取汉字串拼音, 用 - 来分隔每个汉字的拼音 
	 * 例如，输入：篮球世界
	 * 输出：lan-qiu-shi-jie
	 * @param strCN
	 * @return
	 */
	public static String getHanyuPinyin(String strCN) {
		try {
			return getHanyuPinyin(strCN, true);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取汉字串拼音
	 * 
	 * @param strCN 输入的字符串
	 * @param needSplit 是否需要用 - 来分隔每个汉字的拼音、
	 * @return 汉字串拼音
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getHanyuPinyin(String strCN, boolean needSplit) 
			throws BadHanyuPinyinOutputFormatCombination {
		
		if (null == strCN) {
			return null;
		}
		StringBuilder spell = new StringBuilder();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
				if (null != spellArray) {
					if(needSplit) {
						spell.append("-").append(spellArray[0]).append("-");
					} else {
						spell.append(spellArray[0]);
					}
				} else {
					spell.append(charOfCN[i]);
				}
			} else {
				spell.append(charOfCN[i]);
			}
		}
		
		if(needSplit) {
			String ss = spell.toString();
			ss = StringUtils.replace(ss, "--", "-");
			ss = StringUtils.replace(ss, "--", "-");
			
			int len = ss.length();
			int beginIndex = ss.startsWith("-") ? 1 : 0;
			int endIndex = ss.endsWith("-") ? len - 1 : len;
			
			return ss.substring(beginIndex, endIndex);
		} else {
			String tmp = spell.toString();
			tmp = StringUtils.replace(tmp, "--", "-");
			tmp = StringUtils.replace(tmp, "--", "-");
			return tmp;
		}
	}

	/**
	 * 获取汉字串 拼音首字母
	 * 
	 * @param strCN
	 * @return 拼音首字母
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstHanyuPinyin(String strCN)
			throws BadHanyuPinyinOutputFormatCombination {
		if (null == strCN) {
			return null;
		}
		StringBuilder firstSpell = new StringBuilder();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(
						charOfCN[i], format);
				// 取拼音首字母
				if (null != spellArray) {
					firstSpell.append(spellArray[0].charAt(0));
				} else {
					firstSpell.append(charOfCN[i]);
				}
			} else {
				firstSpell.append(charOfCN[i]);
			}
		}
		return firstSpell.toString();
	}

	/**
	 * 获取 汉字串拼音首字母 & 汉字串拼音
	 * 
	 * @param strCN
	 * @return 下标0：汉字串拼音首字母；下标1：汉字串拼音
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String[] getFirstAndHanyuPinyin(String strCN)
			throws BadHanyuPinyinOutputFormatCombination {
		if (null == strCN) {
			return null;
		}
		StringBuilder firstSpell = new StringBuilder();
		StringBuilder spell = new StringBuilder();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(
						charOfCN[i], defaultFormat);
				if (null != spellArray) {
					firstSpell.append(spellArray[0].charAt(0));
					spell.append(spellArray[0]);
				} else {
					firstSpell.append(charOfCN[i]);
					spell.append(charOfCN[i]);
				}
			} else {
				firstSpell.append(charOfCN[i]);
				spell.append(charOfCN[i]);
			}
		}
		return new String[] { firstSpell.toString(), spell.toString() };
	}
		
}