package com.xibu.tickets.dao;


import com.xibu.tickets.domain.MyRefund;
import com.xibu.tickets.domain.RefundTicket;

import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:04:19
*/
public interface IRefundTicketsDao {

	int total();

	List<MyRefund> findByPage(Map<String,Object> map );
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<RefundTicket> lhyfindByPage(Map<String,Object> map);
	/**
	 * 根据分页查询的总记录数
	 * @param flag
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 条件查询
	 * @param tname
	 * @param uname
	 * @param name
	 * @param refundtime
	 * @param status 0-待审核 1-通过 2-失败
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<RefundTicket> lhyfindByCondition(Map<String,Object> map);
	/**
	 * 根据条件查询的总记录数
	 * @param tname
	 * @param uname
	 * @param name
	 * @param refundtime
	 * @param status
	 * @return
	 */
	public int lhytotals(Map<String,Object> map);
	/**
	 * 修改状态 1-通过 2-失败
	 * @param rid
	 * @param status
	 * @return
	 */
	// public int lhyupdateStatus(Map<String,Object> map);
	int lhyupdateStatu01( Map<String,Object> map );
	int lhyupdateStatu02( String rid  );
	int lhyupdateStatu03( String rid  );

	/**
	 * 通过退单id查询订单id
	 * @param rid
	 * @return
	 */
	int findOid(String rid);

	/**
	 * 更改这个订单的状态为1
	 * @param oid
	 * @param status
	 * @return
	 */
	int updateStatus(Map<String,Object> map);

	void updateStatusRefundStatus(Map<String, Object> map);
}
