package com.glaf.base.modules.sys.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glaf.base.dao.AbstractSpringDao;
import com.glaf.base.modules.sys.SysConstants;
import com.glaf.base.modules.sys.model.SysApplication;
import com.glaf.base.modules.sys.model.SysTree;
import com.glaf.base.modules.sys.model.SysUser;
import com.glaf.base.modules.utils.ContextUtil;
import com.glaf.base.utils.PageResult;

public class SysApplicationService {
	private Log logger = LogFactory.getLog(SysApplicationService.class);
	private SysTreeService sysTreeService;
	private AbstractSpringDao abstractDao;

	public void setAbstractDao(AbstractSpringDao abstractDao) {
		this.abstractDao = abstractDao;
		logger.info("setAbstractDao");
	}

	public void setSysTreeService(SysTreeService sysTreeService) {
		this.sysTreeService = sysTreeService;
		logger.info("setSysTreeService");
	}

	/**
	 * 保存
	 * 
	 * @param bean
	 *            SysApplication
	 * @return boolean
	 */
	public boolean create(SysApplication bean) {
		boolean ret = false;
		if (abstractDao.create(bean)) {// 插入记录成功
			bean.setSort((int) bean.getId());// 设置排序号为刚插入的id值
			abstractDao.update(bean);
			ret = true;
		}
		return ret;
	}

	/**
	 * 更新
	 * 
	 * @param bean
	 *            SysApplication
	 * @return boolean
	 */
	public boolean update(SysApplication bean) {
		return abstractDao.update(bean);
	}

	/**
	 * 删除
	 * 
	 * @param bean
	 *            SysApplication
	 * @return boolean
	 */
	public boolean delete(SysApplication bean) {
		return sysTreeService.delete(bean.getId());
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            int
	 * @return boolean
	 */
	public boolean delete(long id) {
		SysApplication bean = findById(id);
		if (bean != null) {
			return delete(bean);
		} else {
			return false;
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteAll(long[] id) {
		List list = new ArrayList();
		for (int i = 0; i < id.length; i++) {
			SysApplication bean = findById(id[i]);
			if (bean != null)
				list.add(bean);
		}
		return abstractDao.deleteAll(list);
	}

	/**
	 * 获取对象
	 * 
	 * @param id
	 * @return
	 */
	public SysApplication findById(long id) {
		return (SysApplication) abstractDao.find(SysApplication.class,
				new Long(id));
	}

	/**
	 * 按名称查找对象
	 * 
	 * @param name
	 *            String
	 * @return SysApplication
	 */
	public SysApplication findByName(String name) {
		SysApplication bean = null;
		Object[] values = new Object[] { name };
		String query = "from SysApplication a where a.name=? order by a.id desc";
		List list = abstractDao.getList(query, values, null);
		if (list != null && list.size() > 0) {// 有记录
			bean = (SysApplication) list.get(0);
		}
		return bean;
	}

	/**
	 * 获取分页列表
	 * 
	 * @param parent
	 *            int
	 * @param pageNo
	 *            int
	 * @param pageSize
	 *            int
	 * @return
	 */
	public PageResult getApplicationList(int parent, int pageNo, int pageSize) {
		// 计算总数
		Object[] values = new Object[] { new Long(parent) };
		String query = "select count(*) from SysApplication a where a.node.parent=?";
		logger.info(query);
		int count = ((Long) abstractDao.getList(query, values, null).iterator()
				.next()).intValue();
		if (count == 0) {// 结果集为空
			PageResult pager = new PageResult();
			pager.setPageSize(pageSize);
			return pager;
		}
		// 查询列表
		query = "from SysApplication a where a.node.parent=? order by a.sort desc";
		return abstractDao.getList(query, values, pageNo, pageSize, count);
	}

	/**
	 * 获取列表
	 * 
	 * @param parent
	 *            int
	 * @return List
	 */
	public List getApplicationList(int parent) {
		Object[] values = new Object[] { new Long(parent) };
		String query = "from SysApplication a where a.node.parent=? order by a.sort desc";
		return abstractDao.getList(query, values, null);
	}

	/**
	 * 获取全部列表
	 * 
	 * @return List
	 */
	public List getApplicationList() {
		String query = "from SysApplication a order by a.sort desc";
		return abstractDao.getList(query, null, null);
	}

	/**
	 * 排序
	 * 
	 * @param bean
	 *            SysApplication
	 * @param operate
	 *            int 操作
	 */
	public void sort(long parent, SysApplication bean, int operate) {
		if (bean == null)
			return;
		if (operate == SysConstants.SORT_PREVIOUS) {// 前移
			sortByPrevious(parent, bean);
		} else if (operate == SysConstants.SORT_FORWARD) {// 后移
			sortByForward(parent, bean);
		}
	}

	/**
	 * 向前移动排序
	 * 
	 * @param bean
	 */
	private void sortByPrevious(long parent, SysApplication bean) {
		Object[] values = new Object[] { new Long(parent),
				new Integer(bean.getSort()) };
		// 查找前一个对象
		String query = "from SysApplication a where a.node.parent=? and a.sort>? order by a.sort asc";
		List list = abstractDao.getList(query, values, null);
		if (list != null && list.size() > 0) {// 有记录
			SysApplication temp = (SysApplication) list.get(0);
			int i = bean.getSort();
			bean.setSort(temp.getSort());
			abstractDao.update(bean);// 更新bean
			SysTree node = bean.getNode();
			node.setSort(bean.getSort());
			abstractDao.update(node);

			temp.setSort(i);
			abstractDao.update(temp);// 更新temp
			node = temp.getNode();
			node.setSort(temp.getSort());
			abstractDao.update(node);
		}
	}

	/**
	 * 向后移动排序
	 * 
	 * @param bean
	 */
	private void sortByForward(long parent, SysApplication bean) {
		Object[] values = new Object[] { new Long(parent),
				new Integer(bean.getSort()) };
		// 查找后一个对象
		String query = "from SysApplication a where a.node.parent=? and a.sort<? order by a.sort desc";
		List list = abstractDao.getList(query, values, null);
		if (list != null && list.size() > 0) {// 有记录
			SysApplication temp = (SysApplication) list.get(0);
			int i = bean.getSort();
			bean.setSort(temp.getSort());
			abstractDao.update(bean);// 更新bean
			SysTree node = bean.getNode();
			node.setSort(bean.getSort());
			abstractDao.update(node);

			temp.setSort(i);
			abstractDao.update(temp);// 更新temp
			node = temp.getNode();
			node.setSort(temp.getSort());
			abstractDao.update(node);
		}
	}

	/**
	 * 获取用户能访问到的模块列表
	 * 
	 * @param userId
	 *            int
	 * @param parent
	 *            int
	 * @return List
	 */
	public List getAccessAppList(long parent, SysUser user) {
		logger.info("parent:" + parent);
		long parentAppId = parent;
		SysApplication parentApp = findById(parent);
		if (parentApp != null) {
			parentAppId = parentApp.getNode().getId();
		}

		logger.info("parent node:" + parentAppId);

		StringBuffer sb = new StringBuffer();
		Iterator iter = user.getApps().iterator();
		while (iter.hasNext()) {
			SysApplication bean = (SysApplication) iter.next();
			sb.append(bean.getId()).append(",");
		}
		sb.append(0);

		// 计算总数
		Object[] values = new Object[] { new Long(parentAppId) };
		String query = "select a from SysApplication a "
				+ "where a.node.parent=? and " + "a.id in (" + sb.toString()
				+ ") " + "order by a.sort desc";
		return abstractDao.getList(query, values, null);
	}

	/**
	 * 获取菜单
	 * 
	 * @param parent
	 * @param userId
	 * @return
	 */
	public String getMenu(long parent, SysUser user) {
		StringBuffer menu = new StringBuffer("");
		List list = getAccessAppList(parent, user);
		if (list == null || list.isEmpty()) {
			if ("root".equals(user.getAccount())) {
				list = getApplicationList((int) parent);
			}
		}
		if (list != null && list.size() > 0) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				SysApplication bean = (SysApplication) iter.next();
				menu.append("<li>");
				menu.append("<a href=\"javascript:jump('");
				//System.out.println("ContextUtil.getContextPath():"+ContextUtil.getContextPath());
				if (ContextUtil.getContextPath() != null) {
					menu.append(ContextUtil.getContextPath());
				}
				menu.append(bean.getUrl());
				menu.append("',");
				menu.append(bean.getShowMenu());
				menu.append(");\">");
				menu.append(bean.getName()).append("</a>\n");

				List sonNode = getAccessAppList(bean.getId(), user);
				if (sonNode == null || sonNode.isEmpty()) {
					if ("root".equals(user.getAccount())) {
						sonNode = getApplicationList((int) bean.getId());
					}
				}
				if (sonNode != null && sonNode.size() > 0) {// 有子菜单
					menu.append("<ul>");
					menu.append(getMenu(bean.getId(), user));
					menu.append("</ul>");
				}
				menu.append("</li>").append("<li></li>\n");
			}
		}
		return menu.toString();
	}
}
