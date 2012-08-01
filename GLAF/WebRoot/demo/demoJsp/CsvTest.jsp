<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/sys/sysTld/struts-html.tld" %>
<%@ taglib prefix="bean" uri="/sys/sysTld/struts-bean.tld" %>
<%@ taglib uri="/sys/sysTld/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/sys/sysTld/struts-logic.tld" prefix="logic"%>

<html>
  <head>
    <title>我的测试浏览</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/baseStyle.css">  
  </head><script type="text/javascript" src="<%=request.getContextPath()%>//sys/sysJs/frameWorkUtility.js"></script>
  <body>
    This is my JSP page. <br>
    <html:form action="csvTest.do" method="post">
    	<input type="hidden" value="csvDo" name="actionMethodId"/>
    	<div>
    		<table>
			<tr>
			<td>变量1<br></td>
			<td><html:text maxlength="9" property="xmA"/></td>
			<td>变量2<br></td>
			<td><html:text maxlength="9" property="xmB"/></td>
		   </tr>
		</table>
    	</div>
    	<input type="button" value="save" name="save" onclick="doSave()"/>
    </html:form>
<script type="text/javascript" language="Javascript">
	function doSave(){
	    objFrm = document.forms[0];
	    //clearErrorColor();
	    objFrm.actionMethodId.value = "csvDo";
		submitForm(objFrm,"<bean:message key='baseSample.doubleSubmit'/>");
		}
</script>

</body>
<jsp:include page="/sys/sysJsp/common/showAllTypeMessage.jsp" flush="true"/>
</html>
