package com.xibu.tickets.dao;


import com.xibu.tickets.domain.Users;

import java.util.List;
import java.util.Map;

/**
* 用户的持久层接口
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午9:00:27
*/
public interface IUsersDao {
	
	/**
	 * 用户注册
	 * @param users
	 * @return
	 */
	int register(Users users) ;
	
	/**
	 * 用户登录
	 * @param account	可以是电话/邮箱/身份证
	 * @param pwd
	 * @return
	 */
	Users login(Map<String,Object> map ) ;

	/**
	 * 用户激活
	 * @param identify
	 * @return
	 */
	int active(String identify);

	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 */
	Users findByEmail(String email);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int update(Users user);
	/**
	 * 根据分页查询的用户总数
	 * @return
	 */
	public int lhytotal();
	/**
	 * 分页查询用户信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Users> lhyfindByPage(Map<String,Object> map );
	/**
	 * 条件查询用户信息
	 * @param uname
	 * @param utel
	 * @param status
	 * @return
	 */
	public List<Users> lhyfindByCondition(Map<String,Object> map );
	/**
	 * 根据条件查询的用户总数
	 * @param uname
	 * @param utel
	 * @param status
	 * @return
	 */
	public int lhytotals(Map<String,Object> map );
	/**
	 * 修改用户状态   0冻结  1可用
	 * @param uid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map );
	
	public Users lhyfindByUid(String uid);

}
