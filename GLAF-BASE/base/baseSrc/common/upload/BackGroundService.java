//================================================================================================
//项目名称 ：    基盘
//功    能 ：    文件上传
//文件名称 ：    BackGroundService.java                                   
//描    述 ：    文件上传后台服务程序
//================================================================================================
//修改履历                                                                
//年 月 日		区分			所 属/担 当           内 容									标识        
//----------   ----   -------------------- ---------------                          ------        
//2009/04/28   	编写   		Intasect/李闻海     新規作成                                                                            
//================================================================================================

package baseSrc.common.upload;


import baseSrc.common.Constants;
import baseSrc.common.DbAccess;
import baseSrc.orm.Attachment;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BackGroundService extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 6162190133320085279L;
	//文件上传路径
	public static final String UPLOAD_DIR = System.getProperty("WEBPATH") + Constants.UPLOAD_DIR;
    //上传结果页面
	public static final String DEFAULT_UPLOAD_FAILURE_URL = "/sys/sysJsp/common/upload/result.jsp";

	private static Random randGen = null;
	
	private static Object initLock = new Object();
	
	private static char[] numbersAndLetters = null;
	//文件上传最大值
	public static final long UPLOAD_FILE_SIZE_MAX = Constants.UPLOAD_FILE_SIZE_MAX;
    //资源文件对象
	public static MessageResources   resources;
	
	public BackGroundService() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 从文件路径中取出文件名

	 */
	private String takeOutFileName(String filePath) {
		int pos = filePath.lastIndexOf(File.separator);
		if (pos > 0) {
			return filePath.substring(pos + 1);
		} else {
			return filePath;
		}
	}

	/**
	 * 从request中取出FileUploadStatus Bean
	 */
	public static FileUploadStatus getStatusBean(HttpServletRequest request) {
		BeanControler beanCtrl = BeanControler.getInstance();
		return beanCtrl.getUploadStatus(request.getRemoteAddr());
	}

	/**
	 * 把FileUploadStatus Bean保存到类控制器BeanControler
	 */
	public static void saveStatusBean(HttpServletRequest request,
			FileUploadStatus statusBean) {
		statusBean.setUploadAddr(request.getRemoteAddr());
		BeanControler beanCtrl = BeanControler.getInstance();
		beanCtrl.setUploadStatus(statusBean);
	}

	/**
	 * 删除已经上传的文件

	 */
	private void deleteUploadedFile(HttpServletRequest request) {
		FileUploadStatus satusBean = getStatusBean(request);
		for (int i = 0; i < satusBean.getUploadFileUrlList().size(); i++) {
			File uploadedFile = new File(UPLOAD_DIR + File.separator
					+ satusBean.getUploadFileUrlList().get(i));
			uploadedFile.delete();
		}
		satusBean.getUploadFileUrlList().clear();
		satusBean.setStatus(resources.getMessage("commonUpload.deleteFile"));
		saveStatusBean(request, satusBean);
	}

	/**
	 * 上传过程中出错处理

	 */
	private void uploadExceptionHandle(HttpServletRequest request, String errMsg)
			throws ServletException, IOException {
		// 首先删除已经上传的文件
		deleteUploadedFile(request);
		FileUploadStatus satusBean = getStatusBean(request);
		satusBean.setStatus(errMsg);
		saveStatusBean(request, satusBean);
	}

	/**
	 * 初始化文件上传状态Bean
	 */
	private FileUploadStatus initStatusBean(HttpServletRequest request) {
		resources   =  ((MessageResources)   getServletContext().getAttribute(Globals.MESSAGES_KEY));
		FileUploadStatus satusBean = new FileUploadStatus();
		satusBean.setStatus(resources.getMessage("commonUpload.readytoProcess"));
		satusBean.setUploadTotalSize(request.getContentLength());
		satusBean.setProcessStartTime(System.currentTimeMillis());
		satusBean.setBaseDir(Constants.UPLOAD_DIR);
		return satusBean;
	}

	/**
	 * 处理文件上传
	 */
	private void processFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String cip = (String)request.getSession().getAttribute("cip");
		String cid = (String)request.getSession().getAttribute("cid");
		String uid = (String)request.getSession().getAttribute("uid");
		if(cip==null){
			cip="";
		}
		if(cid==null){
			cid="";
		}
		if(uid==null){
			uid="";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(204800);
		File tempDir = new File(UPLOAD_DIR + "/temp");
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// 设置临时文件存储位置
		factory.setRepository(tempDir);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setFileSizeMax(UPLOAD_FILE_SIZE_MAX);
		// 设置整个request的最大值		
		//设置编码方式
		upload.setHeaderEncoding("UTF-8");
		
		upload.setSizeMax(UPLOAD_FILE_SIZE_MAX);
		upload.setProgressListener(new FileUploadListener(request));
		
		// 保存初始化后的FileUploadStatus Bean
		saveStatusBean(request, initStatusBean(request));
		//上传文件名
		String fileName = "";
		// 文件储存目录
		String uploadDir = "";
		String forwardURL = "";
		try {
			List items = upload.parseRequest(request);
			// 获得返回url
			for (int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);
				if (item.isFormField()) {
					forwardURL = item.getString();
					break;
				}
			}
			
			// 处理文件上传
			for (int i = 0; i < items.size(); i++) {
				if (i == 0) {
					// 生成两层随机目录
					uploadDir = createDir(UPLOAD_DIR);
				}
				FileItem item = (FileItem) items.get(i);

				// 取消上传
				if (getStatusBean(request).getCancel()) {
					deleteUploadedFile(request);
					break;
				}

				// 保存文件
				else if (!item.isFormField() && item.getName().length() > 0) {
//					logger.info("222222222222  request char Encoding = "+request.getCharacterEncoding());
					
					String itemName =  item.getName();

					String fileEncodeString = itemName;
					fileName = getFileName(takeOutFileName(fileEncodeString));
					
					File uploadedFile = new File(
							new File(UPLOAD_DIR, uploadDir), fileName);
					item.write(uploadedFile);
					// 更新上传文件列表
					FileUploadStatus satusBean = getStatusBean(request);
					satusBean.getUploadFileUrlList().add(
							new File(uploadDir, fileName).toString());
					saveStatusBean(request, satusBean);
					Thread.sleep(500);
				}
			}
			
			ServletContext servletContext = this.getServletContext();
			WebApplicationContext context=
				WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			
			Attachment attachment = new Attachment();
			attachment.setName(fileName);
			attachment.setUrl(uploadDir+"\\"+fileName);
			attachment.setCdatetime(new Date());
			attachment.setCiP(cip);
			attachment.setCpID(cid);
			attachment.setCuID(uid);
			
			
			DbAccess dbAccess=(DbAccess)context.getBean("sysDbAccess");
			dbAccess.saveOrUpdate(attachment);
			request.setAttribute("fileId", attachment.getId().toString());
		} catch (FileUploadException e) {
			uploadExceptionHandle(request, resources.getMessage("commonUpload.uploadError")+":" + e.getMessage());
		} catch (Exception e) {
			uploadExceptionHandle(request, resources.getMessage("commonUpload.saveError")+":" + e.getMessage());
			e.printStackTrace();
		}
		if (forwardURL.length() == 0) {
			forwardURL = DEFAULT_UPLOAD_FAILURE_URL;
		}
		request.getRequestDispatcher(forwardURL).forward(request, response);
	}

	/**
	 * 回应上传状态查询

	 */
	private void responseStatusQuery(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		FileUploadStatus satusBean = getStatusBean(request);
		response.getWriter().write(satusBean.toJSon());
	}

	/**
	 * 处理取消文件上传
	 */
	private void processCancelFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FileUploadStatus satusBean = getStatusBean(request);
		satusBean.setCancel(true);
		saveStatusBean(request, satusBean);
		responseStatusQuery(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			processFileUpload(request, response);
		} else {
			request.setCharacterEncoding("UTF-8");
			if (request.getParameter("uploadStatus") != null) {
				responseStatusQuery(request, response);
			}
			if (request.getParameter("cancelUpload") != null) {
				processCancelFileUpload(request, response);
			}

		}
	}

	// 取新文件名
	private String getFileName(String fileName) {
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		int random = (int) (Math.random() * 1000);
		String newFileName = getPrefixName(fileName) + "_" + random
				+ getFileExtension(fileName);
		return newFileName;
	}

	// 取文件名(不含后缀名)
	private String getPrefixName(String fileName) {
		if (fileName == null) {
			return null;
		}
		String s = fileName;
		int pos = fileName.lastIndexOf("/");
		if (pos != -1) {
			s = fileName.substring(pos + 1);
		} else {
			pos = fileName.lastIndexOf("\\");
			s = fileName.substring(pos + 1);
		}
		pos = s.lastIndexOf(".");
		if (pos != -1) {
			s = s.substring(0, pos);
		}
		return s;
	}

	// 取文件后缀名
	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		String ext = "";
		int pos = fileName.lastIndexOf(".");
		if (pos != -1) {
			ext = fileName.substring(pos);
		}
		return ext;
	}

	/**
	 * 生成随机目录
	 * 
	 * @param root
	 * @return
	 */
	public static String createDir(String root) {
		String path = randomNumString(4);
		String slash = File.separator;
		String first = path.substring(0, 2);
		String second = path.substring(2, 4);
		File dir = new File(root + slash + first);
		if (!dir.isDirectory())
			dir.mkdir();
		dir = new File(root + slash + first + slash + second);
		if (!dir.isDirectory())
			dir.mkdir();
		return first + slash + second + slash;
	}
	
	/**
	 * 生成随机字符
	 * @param length长度
	 * @return String 随机字符
	 * 
	 */
	public static final String randomNumString(int length) {
		if (length < 1) {
			return null;
		}
		// 初始化随机数字生成器
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// 初始化数字字母数
					numbersAndLetters = ("0123456789").toCharArray();
				}
			}
		}
		// 创建字符缓存数组装入字母和数
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
		}
		return new String(randBuffer);
	}

}
