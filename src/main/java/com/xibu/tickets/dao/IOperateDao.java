package com.xibu.tickets.dao;


import com.xibu.tickets.domain.Operate;
import com.xibu.tickets.domain.TrainNumber;

import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:30:03
*/
public interface IOperateDao {
	
	/**
	 * 根据条件查询总记录数
	 * @param tid
	 * @return
	 */
	Integer total(Map<String,Object> map) ;
	
	/**
	 * 根据条件分页查询数据
	 * @param tid
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Operate> findByType(Map<String,Object> map) ;

	/**
	 * 查询当前座位的价格
	 * @param oid
	 * @param userInfo
	 * @return
	 */
	double totalPrice(Map<String,Object> map);

	/**
	 * 查询余票
	 * @param oid
	 * @param siteType
	 * @return
	 */
	int findRemain(Map<String,Object> map);

	/**
	 * 减座位数
	 * @param map
	 */
	void subSeat(Map<String,Object> map);


	/**
	 * 添加运营信息
	 * @param tid
	 * @param rworemain
	 * @param yworemain
	 * @param rzuoremain
	 * @param yzuoremain
	 * @param zhanremain
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public int lhyadd(Operate operate);
	/**
	 * 修改座位数   ----- 根据订单修改->下单相应类型座位数-1  退单响应类型座位数+1
	 * @param tid
	 * @param type
	 * @param reamin
	 * @return
	 */
	public int lhyupdateRemain(Operate operate);
	/**
	 * 修改运营状态  0--误点  1--正常
	 * @param tid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map);
	/**
	 * 总记录数
	 * @param flag
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Operate> lhyfindByPage(Map<String,Object> map);
	/**
	 * 条件查询，根据列车号、发车日期和状态
	 * @param tid
	 * @param date
	 * @param status
	 * @return
	 */
	public int lhytotals(Map<String,Object> map);
	/**
	 * 根据条件查询的总记录数
	 * @param tid
	 * @param date
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Operate> lhyfindByCondition(Map<String,Object> map);
	/**
	 * 根据编号查询
	 * @param oid
	 * @return
	 */
	public Operate lhyfindByOid(String oid);
	/**
	 * 修改
	 * @param oid
	 * @param rworemain
	 * @param yworemain
	 * @param rzuoremain
	 * @param yzuoremain
	 * @param zhanremain
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public int lhyupdate( Map<String,Object> map );

	TrainNumber findByTid(Integer tid);
}
