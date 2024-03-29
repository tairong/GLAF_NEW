package com.glaf.base.modules.sys;

/**
 * <p>Title: Global.java</p>
 * <p>Description: 公用变量类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * @version 1.0
 */

public class SysConstants {
	//用户session名称
	public static String LOGIN = "LOGIN_USER";
	public static String MENU = "LOGIN_MENU";
		
	public static int SORT_PREVIOUS = 0;//前移
	public static int SORT_FORWARD = 1;//后移
	
	public static int TREE_ROOT=1;//目录根节点
	public static String TREE_BASE="01";//基础数据结构树编号
	public static String TREE_APP="02";//模块结构树编号
	public static String TREE_DICTORY="011";//数据字典结构树编号
	public static String TREE_DEPT="012";//部门结构树编号
	
	//是否失效
	public static int BLOCKED_0 = 0;  //否
	public static int BLOCKED_1 = 1;  //是
	
	//部门状态
	public static Integer DEPT_STATUS_0 = Integer.valueOf(0);	//有效
	public static Integer DEPT_STATUS_1 = Integer.valueOf(1);	//失效

}