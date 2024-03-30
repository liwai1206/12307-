package com.xibu.tickets.service.impl;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xibu.tickets.dao.ITrainTypeDao;
import com.xibu.tickets.domain.TrainType;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.ITrainTypeService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午8:01:04
*/
@Service
@Transactional
public class TrainTypeServiceImpl implements ITrainTypeService {

	@Autowired
	private ITrainTypeDao trainTypeDao;

	@Autowired
	private RedisTemplate redisTemplate ;

	/**
	 * 查询所有车票类型
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Override
	public List<TrainType> findAll() throws JsonParseException, JsonMappingException, IOException {
		List<TrainType> list = new ArrayList() ;

		// 首先查询redis中是否有存在 
		String ty = (String) redisTemplate.opsForValue().get("types");
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		if ( ty != null && !"".equals( ty ) ) {
			System.out.println("=======查询redis");
			// 如果存在，将json字符串转成对象数组 
			 TrainType[] trainTypes = objectMapper.readValue( ty , TrainType[].class );
			 // 将对象数组转成对象集合
			 list = Arrays.asList( trainTypes ) ;

			return list ;
		}
		System.out.println("=======查询数据库");
		// 查询数据库
		list = trainTypeDao.findAll() ;
		// 如果不存在，则查询数据库，并将查询到的结果存入redis中 
		String jackson = objectMapper.writeValueAsString(list) ;
		redisTemplate.boundValueOps("types").set(jackson); ;
		
		return list ;
	}
	
	@Override
	public int lhyadd(String tname) {
		if(StringUtil.checkNull(tname)) {
			return -1;
		}
		return trainTypeDao.lhyadd(tname);
	}

	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(trainTypeDao.lhytotal(), trainTypeDao.lhyfindByPage(map));
	}

	@Override
	public JsonObject lhyfinds() {
		return new JsonObject(trainTypeDao.lhytotal(), trainTypeDao.lhyfinds());
	}

}
