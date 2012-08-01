package com.glaf.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glaf.base.modules.sys.model.SysUser;

public class AuthorizeFilter implements Filter{
	private Log logger = LogFactory.getLog(AuthorizeFilter.class);
	private String url="";
	private String require="";
	private String errorUrl="";
	private String loginUser="";
	
	/**
	 * ��ʼ��Filter
	 * @param config FilterConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig config) throws ServletException {
		this.url = config.getInitParameter("login_url");
		this.require = config.getInitParameter("require");
		this.errorUrl = config.getInitParameter("error_url");
		this.loginUser = config.getInitParameter("login_user");
		logger.info("url:"+url);
		logger.info("require:"+require);
		logger.info("errorUrl:"+errorUrl);
		logger.info("loginUser:"+loginUser);
	}
	
	/**
	 * ע��
	 */
	public void destroy() {
	}
	/**
	 * �Ƿ���Ե�ǰҳ��
	 * 
	 * @param uri
	 * @return
	 */
	private boolean ignoreUrl(String uri) {
		boolean ret = false;
		String[] ignoreUrls = url.split(",");
		for (int i = 0; i < ignoreUrls.length; i++) {
			if (ignoreUrls[i].trim().equals(uri)) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	/**
	 * ����
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//��Ҫ��֤
		if("true".equals(require)){
			//����û��Ƿ��Ѿ���¼
			SysUser bean = (SysUser) req.getSession().getAttribute(loginUser);
			String uri = req.getRequestURI();
			logger.info(uri);
			
			//�û�û�е�¼�ҵ�ǰҳ���ǵ�¼ҳ��
			logger.info("ignoreUrl:" + ignoreUrl(uri));
			if (bean==null && !ignoreUrl(uri)) {//��ʾ��½ҳ
				res.sendRedirect(errorUrl);
				return;
			}
		}
		chain.doFilter(req, response);
	}
}