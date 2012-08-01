<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="baseSrc.common.upload.*"%>
<%
  String cip = request.getParameter("cip");
  String cid = request.getParameter("cid");
  String uid = request.getParameter("uid");
  request.getSession().setAttribute("cip",cip);
  request.getSession().setAttribute("cid",cid);
  request.getSession().setAttribute("uid",uid);
  
  double fileSizeMax = (int)(((double)(BackGroundService.UPLOAD_FILE_SIZE_MAX / 1024 / 1024.0)) * 100) / 100.0;
 
	String type = request.getParameter("type");
	if (type == null) {
	  type = "";
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="./css/fileUpload.css" type="text/css" rel="stylesheet"/>
<link href="./css/site.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="./js/prototype.js"></script>
<script type="text/javascript" src="./js/AjaxWrapper.js"></script>
<script type="text/javascript" src="../../js/verify.js"></script>
<script type="text/javascript">
<!--
 function checkFileUploadForm(form) {
	if (form.file.value == '') {
		alert('文件不能为空.');
		form.file.focus();
		return false;
	}
	var type = '<%= type %>';
	if (type.length > 0 && !IsUploadFileType(form.file.value, type)) {
	  alert('文件上传类型不正确.允许上传的类型有：' + type);
		form.file.focus();
		return false;
	}
	document.getElementById("cancelUploadButton").disabled=false;
	startProgress();
	return true;
 }
//-->
</script>
<title>文件上传</title>

</head>
<body>
<div id="controlPanel">
  <div id="readme">上传说明:&nbsp;&nbsp;最大上传量:<%= fileSizeMax %>M，单个文件最大长度:<%= fileSizeMax %>M</div>
  <div id="uploadFileUrl"></div>
  <form id="fileUploadForm" name="fileUploadForm" action="./BackGroundService.action" enctype="multipart/form-data" method="post" onsubmit="return checkFileUploadForm(this);">
   
    <div id="upload-box">
		<div><input name="file" type="file" id="file" size="40" />
		</div>
		</div>
    <br />
    <input name="uploadButton" type="submit" class="button" id="uploadButton" value="开始上传"/>
    <input name="cancelUploadButton" type="button" class="button" id="cancelUploadButton" value="关闭窗口" onclick="window.close();" />
    <br />
  </form>
  <div id="progressBar">
    <div id="theMeter">
      <div id="progressBarText"></div>
      <div id="totalProgressBarBox">
        <div id="totalProgressBarBoxContent"></div>
      </div>
    </div>
    <div id="progressStatusText"></div>
  </div>
</div>
<script type="text/javascript" src="./js/upload.js"></script>
</body>
</html>