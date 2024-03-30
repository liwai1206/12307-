package com.xibu.tickets.dao;


import com.xibu.tickets.domain.Admin;

import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月16日 上午10:38:21
*/
public interface IAdminDao {

	/**
	 * 管理员登录
	 * @param map
	 * @return
	 */
    Admin login(Map<String,Object> map) ;

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
	public List<Admin> lhyfindByPage(Map<String,Object> map);

	/**
	 * 根据分页查询的总记录数
	 * @return
	 */
	public int lhytotal();
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
	public List<Admin> lhyfindByCondition(Map<String,Object> map);

	/**
	 * 根据条件查询的总数
	 * @param map
	 * @return
	 */
	public int lhytotals(Map<String,Object> map);
	/**
	 * 升级管理员权限
	 * @param aid
	 * @return
	 */
	public int lhyupdateStatus(String aid);
	/**
	 * 根据管理员类型排序
	 * @return
	 */
	public List<Admin> lhysortByStatus(Map<String,Object> map);

}
