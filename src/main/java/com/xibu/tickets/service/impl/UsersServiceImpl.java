package com.xibu.tickets.service.impl;


import com.xibu.tickets.dao.IUsersDao;
import com.xibu.tickets.domain.Users;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.IUsersService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
* 用户的业务层实现类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午9:16:29
*/
@Service
@Transactional
public class UsersServiceImpl implements IUsersService {
	@Autowired
	private IUsersDao usersDao ;

	/**
	 * 注册
	 */
	@Override
	public int register(Users users) {
		if ( StringUtil.checkNull( users.getUname(),users.getUpassword(),users.getUtel(),users.getUidentify(),users.getUemail() )) {
			// 信息为空
			return -1 ;
		} 
		return usersDao.register(users) ;
	}

	
	/**
	 * 登录
	 */
	@Override
	public Users login(Map<String,Object> map) {
		if ( StringUtil.checkNull( map.get("account")+"" , map.get("pwd")+""  )) {
			// 信息为空
			return null ;
		} 
		return usersDao.login( map);
	}


	/**
	 * 激活
	 */
	@Override
	public int active(String identify) {
		return usersDao.active( identify )  ;
	}

	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 */
	@Override
	public Users findByEmail(String email) {  
		return usersDao.findByEmail(email) ;
	}


	/**
	 * 修改用户信息
	 * @param user
	 */
	@Override
	public int update(Users user) {
		return usersDao.update( user );
	}
	
	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(usersDao.lhytotal(), usersDao.lhyfindByPage(map));
	}

	@Override
	public JsonObject lhyfindByCondition(Map<String,Object> map) {
		return new JsonObject(usersDao.lhytotals(map), usersDao.lhyfindByCondition(map));
	}

	@Override
	public int lhyupdateStatus(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("uid")+"", String.valueOf(map.get( "status")))) {
			System.out.println("修改用户状态：信息不完整");
			return -1;
		}
		return usersDao.lhyupdateStatus(map);
	}

	@Override
	public Users lhyfindByUid(String uid) {
		if(StringUtil.checkNull(uid)) {
			return null;
		}
		return usersDao.lhyfindByUid(uid);
	}


}
