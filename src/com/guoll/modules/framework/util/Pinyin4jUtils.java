package com.guoll.modules.framework.util;

import net.sourceforge.pinyin4j.PinyinHelper;    
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;    
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;    
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;    
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;    
     
public final class Pinyin4jUtils {    
     
    private static final String EMPTY = "";    
     
    /** 大写输出 */    
    private static final HanyuPinyinOutputFormat OUTPUT_FORMAT = new HanyuPinyinOutputFormat();    
    static {    
        OUTPUT_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);    
        OUTPUT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);    
    }    
     
    /**  
     * 汉字转拼音的方法  
     *  
     * 如:	好丽友(浓香茄汁)33g
     * 		HAOLIYOU(NONGXIANGQIEZHI)33g  
     *  
     * @param chineseCharacters  
     * @return  
     * @throws BadHanyuPinyinOutputFormatCombination  
     */    
    @SuppressWarnings("deprecation")
	public static String formatToPinYin1(String chineseCharacters){
		if (null == chineseCharacters || EMPTY.equals(chineseCharacters.trim()))
			return chineseCharacters;
		
		String str = null;
		
		try {
			str = PinyinHelper.toHanyuPinyinString(chineseCharacters,
					OUTPUT_FORMAT, EMPTY);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return str;
    }    
     
    /**  
     * 汉字转拼音的方法  
     *  
     * 如：	好丽友(浓香茄汁)33g
     * 		HLY(NXQZ)33g  
     *  
     * @param chineseCharacters  
     * @return  
     * @throws BadHanyuPinyinOutputFormatCombination  
     */    
    public static String formatToPinYin2(String chineseCharacters)    
            throws BadHanyuPinyinOutputFormatCombination {    
		if (null == chineseCharacters || EMPTY.equals(chineseCharacters.trim()))
			return chineseCharacters;   
     
		char[] chars = chineseCharacters.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (c > 127) {
				sb.append(PinyinHelper.toHanyuPinyinStringArray(c,
						OUTPUT_FORMAT)[0].toCharArray()[0]);
			} else {
				sb.append(c);
			}
		}
        return sb.toString();    
    }    
     
}    