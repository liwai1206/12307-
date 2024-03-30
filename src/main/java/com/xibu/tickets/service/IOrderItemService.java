package com.xibu.tickets.service;


import com.xibu.tickets.domain.MyOrders;
import com.xibu.tickets.domain.OrderItem;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.dto.JsonObject;

import java.util.List;
import java.util.Map;

/**
* 
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月3日 下午8:25:50
*/
public interface IOrderItemService {
	/**
	 * 添加订单
	 * @param map
	 * @return
	 */
	public int adds(Map<String,Object> map);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public PageBean<MyOrders> findByPage(String page);

	/**
	 * 退票
	 * @param oid
	 * @return
	 */
	public int backTickets(String oid);

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
	 * 根据编号查询
	 * @param oid
	 * @return
	 */
	public OrderItem lhyfindByOid(String oid);
	/**
	 * 报表统计 根据列车查询总营业额
	 * @return
	 */
	public List<Map<String, String>> lhysumByTid();
	/**
	 * 根据年统计各列车营业额
	 * @param year
	 * @return
	 */
	public List<Map<String, String>> lhysumByYear(String year);

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> lhysumByMonth(Map<String,Object> map);
	/**
	 * 根据日期统计各列车营业额
	 * @param date
	 * @return
	 */
	public List<Map<String, String>> lhysumByDate(String date);

	List<Map<String, Object>> exportExcelData();
}
