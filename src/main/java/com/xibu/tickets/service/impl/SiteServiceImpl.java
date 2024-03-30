package com.xibu.tickets.service.impl;

import com.xibu.tickets.dao.ISiteDao;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.ISiteService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:31:12
*/
@Service
@Transactional
public class SiteServiceImpl implements ISiteService {

	@Autowired
	private ISiteDao siteDao ;

	@Override
	public int lhyadd(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("sname")+"", map.get("status")+"")) {
			return -1;
		}
		return siteDao.lhyadd( map );
	}

	@Override
	public int lhyupdateSname(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("sname")+"", map.get("sid")+"")) {
			return -1;
		}
		return siteDao.lhyupdateSname(map);
	}

	@Override
	public int lhyupdateStatus(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("status")+"", map.get("sid")+"")) {
			return -1;
		}
		return siteDao.lhyupdateStatus(map);
	}

	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(siteDao.lhytotal(false), siteDao.lhyfindByPage( map ));
	}

	@Override
	public JsonObject lhyfinds() {
		return new JsonObject(siteDao.lhytotal(true), siteDao.lhyfinds());
	}
}
