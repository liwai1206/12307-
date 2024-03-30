package com.xibu.tickets.service.impl;

import com.xibu.tickets.dao.ITrainNumberDao;
import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.ITrainNumberService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:32:51
*/
@Service
@Transactional
public class TrainNumberServiceImpl implements ITrainNumberService {

	@Autowired
	private ITrainNumberDao trainNumberDao ;

	@Override
	public int lhyadd(Map<String,Object> map ) {
		if(StringUtil.checkNull( map )) {
			return -1;
		}
		return trainNumberDao.lhyadd( map );
	}

	@Override
	public int lhyupdate(Map<String,Object> map) {
		if(StringUtil.checkNull( map )) {
			return -1;
		}
		return trainNumberDao.lhyupdate( map );
	}

	@Override
	public int lhyupdateStatus( Map<String,Object> map ) {
		if(StringUtil.checkNull(map)) {
			return -1;
		}
		return trainNumberDao.lhyupdateStatus(map);
	}

	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map  ) {
		return new JsonObject(trainNumberDao.lhytotal(), trainNumberDao.lhyfindByPage( map ));
	}

	@Override
	public JsonObject lhyfindByCondition( Map<String,Object> map) {
		return new JsonObject(trainNumberDao.lhytotals(map), trainNumberDao.lhyfindByCondition(map));
	}

	@Override
	public TrainNumber lhyfindByTid(String tid) {
		if(StringUtil.checkNull(tid)) {
			return null;
		}
		return trainNumberDao.lhyfindByTid(tid);
	}
}
