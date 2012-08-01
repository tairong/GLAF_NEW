//================================================================================================
//项目名称 ：    基盘
//功    能 ：   基盘共通操作
//文件名称 ：    BaseUtility.java                                   
//描    述 ：    
//================================================================================================
//修改履历                                                                
//年 月 日		区分		所 属/担 当           		内 容									标识        
//----------   	----   	------------------- ---------------                          ------        
//2009/04/28   	编写   	Intasect/廖学志    	 新規作成                                                                            
//================================================================================================

package baseSrc.common;

import java.util.List;


public class BaseUtility {
	
	public static long getTestProcessDifination()
	{
		return 2031820;
	}

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isStringNull(String str){
		boolean ret = false;
		
		if(null == str || "".equals(str)){
			ret = true;
		}
		return ret;
	}
	
	/**
	 * 判断List是否为空
	 * @param list
	 * @return
	 */
	public static boolean isListNull(List<?> list){
		boolean ret = false;
		
		if(null == list || 0 == list.size()){
			ret = true;
		}
		return ret;
	}

	
}
