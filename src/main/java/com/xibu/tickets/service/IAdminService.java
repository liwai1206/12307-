package com.xibu.tickets.service;
import com.xibu.tickets.domain.Admin;
import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:18:25
*/
public interface IAdminService {
	/**
	 * 由超级管理员添加普通管理员
	 * @param map
	 * @return
	 */
	public int lhyadd(Map<String,Object> map);

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map);
	/**
	 * 根据编号查询
	 * @param aid
	 * @return
	 */
	public Admin lhyfindByAid(String aid);

	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByCondition(Map<String,Object> map);
	/**
	 * 升级管理员权限
	 * @param aid
	 * @return
	 */
	public int lhyupdateStatus(String aid);

	/**
	 * 根据管理员类型排序
	 * @param map
	 * @return
	 */
	public JsonObject lhysortByStatus(Map<String,Object> map);

	/**
	 * 管理员登录
	 * @param map
	 * @return
	 */
	public Admin login(Map<String,Object> map);

}
