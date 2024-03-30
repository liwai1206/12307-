package com.xibu.tickets.service;

import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:20:54
*/
public interface ISiteService {
	/**
	 * 添加站点
	 * @param map
	 * @return
	 */
	public int lhyadd( Map<String,Object> map);

	/**
	 * 修改站点名
	 * @param map
	 * @return
	 */
	public int lhyupdateSname(Map<String,Object> map);

	/**
	 * 修改站点状态
	 * @param map
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map);

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map);
	/**
	 * 查询所有可用站点
	 * @return
	 */
	public JsonObject lhyfinds();
}
