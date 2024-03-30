package com.xibu.tickets.service.impl;

import com.xibu.tickets.dao.IAdminDao;
import com.xibu.tickets.domain.Admin;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.IAdminService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:22:55
*/
@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao ;

	@Override
	public int lhyadd(Map<String,Object> map) {
		if(StringUtil.checkNull( map.get("aname")+"" ,map.get("apassword")+"" , map.get("aemail")+"",map.get("status")+"" )) {
			return -1;
		}
		return adminDao.lhyadd(map);
	}

	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(adminDao.lhytotal(), adminDao.lhyfindByPage(map));
	}

	@Override
	public Admin lhyfindByAid(String aid) {
		return adminDao.lhyfindByAid(aid);
	}

	@Override
	public JsonObject lhyfindByCondition(Map<String,Object> map) {
		return new JsonObject(adminDao.lhytotals(map), adminDao.lhyfindByCondition(map));
	}

	@Override
	public int lhyupdateStatus(String aid) {
		if(StringUtil.checkNull(aid)) {
			return -1;
		}
		return adminDao.lhyupdateStatus(aid);
	}

	@Override
	public JsonObject lhysortByStatus(Map<String,Object> map) {
		return new JsonObject(adminDao.lhytotal(), adminDao.lhysortByStatus(map));
	}

	/**
	 * 管理员登录
	 */
	@Override
	public Admin login(Map<String,Object> map) {
		return adminDao.login(map);
	}
}
