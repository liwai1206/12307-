package com.xibu.tickets.service;


import com.xibu.tickets.domain.Operate;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:24:25
*/
public interface IOperateService {

	/**
	 * 根据车次类型分页查询
	 * @param map
	 * @return
	 */
	PageBean<?> findByType(Map<String,Object> map);

	/**
	 * 计算总价
	 * @param map
	 * @return
	 */
	double totalPrice(Map<String,Object> map);

	/**
	 * 查询余票
	 * @param map
	 * @return
	 */
	int findRemain(Map<String,Object> map);


	/**
	 * 添加运营信息
	 * @param operate
	 * @return
	 */
	public int lhyadd(Operate operate);

	/**
	 * 修改座位数   ----- 根据订单修改->下单相应类型座位数-1  退单响应类型座位数+1
	 * @param operate
	 * @return
	 */
	public int lhyupdateRemain(Operate operate);

	/**
	 * 修改运营状态  0--误点  1--正常
	 * @param map
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map);

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map);

	/**
	 * 条件查询，根据列车号、发车日期和状态
	 * @param map
	 * @return
	 */
	public JsonObject lhyfindByCondition( Map<String,Object> map );
	/**
	 * 根据编号查询
	 * @param oid
	 * @return
	 */
	public Operate lhyfindByOid(String oid);

	/**
	 * 修改
	 * @param map
	 * @return
	 */
	public int lhyupdate(  Map<String,Object> map );

    TrainNumber findByTid(Integer tid);
}
