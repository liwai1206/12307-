package com.xibu.tickets.dao;


import com.xibu.tickets.domain.Site;

import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:38:16
*/
public interface ISiteDao {
	/**
	 * 添加站点
	 * @param sname
	 * @param status
	 * @return
	 */
	public int lhyadd(Map<String,Object> map );
	/**
	 * 修改站点名
	 * @param sname
	 * @return
	 */
	public int lhyupdateSname(Map<String,Object> map );
	/**
	 * 修改站点状态
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map );
	/**
	 * 查询记录总数
	 * @param flag true总记录  false可用总记录
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Site> lhyfindByPage(Map<String,Object> map );
	/**
	 * 查询所有可用站点
	 * @return
	 */
	public List<Site> lhyfinds();
}
