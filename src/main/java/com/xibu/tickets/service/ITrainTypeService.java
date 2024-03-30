package com.xibu.tickets.service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xibu.tickets.domain.TrainType;
import com.xibu.tickets.dto.JsonObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午8:00:49
*/
public interface ITrainTypeService {

	List<TrainType> findAll() throws JsonParseException, JsonMappingException, IOException;
	
	
	/**
	 * 添加类型
	 * @param tname
	 * @return
	 */
	public int lhyadd(String tname);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map);
	/**
	 * 查询所有列车类型
	 * @return
	 */
	public JsonObject lhyfinds();
}
