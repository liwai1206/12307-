package com.xibu.tickets.dao;


import com.xibu.tickets.domain.MyOrders;
import com.xibu.tickets.domain.OrderItem;
import com.xibu.tickets.domain.RefundTicket;

import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月9日 上午11:36:12
*/
public interface IOrderItemDao {
	
	/**
	 * 根据运营查询该运营车次的最后一个座位号
	 * @return
	 */
	public List<String> findSeatNumber(int oid);

	/**
	 * 添加订单
	 * @param passengers
	 * @param oid
	 * @param uid
	 * @param seat
	 */
	public void adds(Map<String,Object> map );

	public int total();

	public List<MyOrders> findByPage(Map<String,Object> map );

	public int add(RefundTicket rTicket);

	public OrderItem findById(int oid );

	public void delete(int oid);

	public void addSeat(Map<String,Object> map );

	public void updateState(String oid);
 
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderItem> lhyfindByPage(Map<String,Object> map );
	/**
	 * 根据分页查询的总记录数
	 * @param flag
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 条件查询
	 * @param tname 列车名
	 * @param uname 用户名称
	 * @param buytime 购买时间
	 * @param name 乘车人姓名
	 * @param status 状态 0-未发车  1-已发车
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderItem> lhyfindByCondition(Map<String,Object> map );
	/**
	 * 根据条件查询的总记录数
	 * @param tname
	 * @param uname
	 * @param buytime
	 * @param name
	 * @param status
	 * @return
	 */
	public int lhytotals(Map<String,Object> map );
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
	 * 根据月份统计各列车营业额
	 * @param month
	 * @return
	 */
	public List<Map<String, String>> lhysumByMonth(Map<String,Object> map );
	/**
	 * 根据日期统计各列车营业额
	 * @param date
	 * @return
	 */
	public List<Map<String, String>> lhysumByDate(String date);

	List<Map<String, Object>> exportExcelData();
}
