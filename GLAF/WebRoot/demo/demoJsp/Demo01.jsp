﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/sys/sysTld/struts-html.tld" %>
<%@ taglib prefix="bean" uri="/sys/sysTld/struts-bean.tld" %>
<%@ taglib uri="/sys/sysTld/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/sys/sysTld/struts-logic.tld" prefix="logic"%>
<html>
  <head>
    <title>iscSample</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/sys/sysCss/baseStyle.css">  
  </head><script type="text/javascript" src="<%=request.getContextPath()%>/sys/sysJs/frameWorkUtility.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/sys/sysJs/calendar/WdatePicker.js"></script>
  
  <body> 

  	<center><h2><br></h2><h2>日历控件 画面锁定 上传下载 Demo</h2></center>

	<html:form method="POST" action="baseSample.do">

<hr/>	
	日历控件demo<br/>
	默认样式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<html:text property="s_Date" styleId="allDate" /><br/> 
 	限制日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<html:text property="s_Date" styleId="beforeToday" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起止日期"2009-04-11"到"2009-04-13"<br/> 
 	日期格式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<html:text property="s_Date" styleId="afterToday" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"yyyy年MM月dd日"的日期格式 <br/>  
    </html:form>
<hr/>
  锁定窗口demo <br/> <input type="button" value="锁定" onclick=lockWindows("系统提示","锁定中。。。","256721");><br/>
 <br/>
<hr/>
  上传附件demo <br/>
 <a id="MSDSHref" style="background:#EAEAEA" href="#">上传附件(已上传<label id="MSDSCount"></label>个)</a> 上传文件路径为： <label id="path"></label><br/> 

<script type="text/javascript" language="Javascript">
	
	showCalendar("allDate");
	showCalendar("beforeToday",null,"2012-03-13","2012-03-16");
	showCalendar("afterToday","yyyy年MM月dd日");
	//showCalendar("afterToday","yyyy年MM月dd日");
	
	document.all.MSDSHref.onclick=function(){
               openUploadWithCallBack(function(back){
               alert("test");
               document.all.path.innerHTML=back[1]+"&nbsp;&nbsp;&nbsp;<a id=\"down\" href=\"../../Download?fileId="+back[0]+"\">下载</a>";        
               document.getElementById('MSDSCount').innerHTML='1';
            },'127.0.0.1','DemoPage','yeah01');
    };	

</script>

</body>
<jsp:include page="/sys/sysJsp/common/showAllTypeMessage.jsp" flush="true"/>
</html>
