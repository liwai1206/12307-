package com.xibu.tickets.service;

import com.xibu.tickets.domain.MyRefund;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:02:21
*/
public interface IRefundTicketsService {

	PageBean<MyRefund> findByPage(String page);


	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map);

	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByCondition(Map<String,Object> map);

	/**
	 * 修改状态 1-通过 2-失败
	 * @param map
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map);

}
