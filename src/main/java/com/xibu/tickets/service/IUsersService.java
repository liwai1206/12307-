package com.xibu.tickets.service;

import com.xibu.tickets.domain.Users;
import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
* 用户的业务层接口
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午9:15:45
*/
public interface IUsersService {
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

	int update(Users user);
	
	/**
	 * 分页查询用户信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map );
	/**
	 * 条件查询用户信息
	 * @param uname
	 * @param utel
	 * @param status
	 * @return
	 */
	public JsonObject lhyfindByCondition(Map<String,Object> map );
	/**
	 * 修改用户状态   0冻结  1可用
	 * @param uid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map );
	
	public Users lhyfindByUid(String uid);
}