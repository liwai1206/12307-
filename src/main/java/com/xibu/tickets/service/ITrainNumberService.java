package com.xibu.tickets.service;

import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.dto.JsonObject;

import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:21:35
*/
public interface ITrainNumberService {
	/**
	 *
	 * @param tname 列车名
	 * @param typeid 车次类型编号  --tid
	 * @param startsite 开始站点编号  --sid
	 * @param endsite 到达站点编号 --sid
	 * @param duration 时长
	 * @param rwo 软卧数量
	 * @param rwoprice 软卧价格
	 * @param rzuo 软座数量
	 * @param rzuoprice 软座价格
	 * @param ywo 硬卧数量
	 * @param ywoprice 硬卧价格
	 * @param yzuo 硬座数量
	 * @param yzuoprice 硬座价格
	 * @param zhan 站票数量
	 * @param zhanprice 站票价格
	 * @param wostudentprice 卧铺学生票价格
	 * @param zuostudentprice 坐铺学生票价格
	 * @param status 车次状态  0--运转  1--停业
	 * @return
	 */
	public int lhyadd( Map<String,Object> map );
	/**
	 * 修改列车信息
	 * @param tname
	 * @param typeid
	 * @param startsite
	 * @param endsite
	 * @param duration
	 * @param rwo
	 * @param rwoprice
	 * @param rzuo
	 * @param rzuoprice
	 * @param ywo
	 * @param ywoprice
	 * @param yzuo
	 * @param yzuoprice
	 * @param zhan
	 * @param zhanprice
	 * @param wostudentprice
	 * @param zuostudentprice
	 * @return
	 */
	public int lhyupdate(Map<String,Object> map );
	/**
	 * 修改列车状态
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(Map<String,Object> map );
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(Map<String,Object> map );
	/**
	 * 条件查询
	 * @param tname
	 * @param typeid
	 * @param startsite
	 * @param endsite
	 * @return
	 */
	public JsonObject lhyfindByCondition(Map<String,Object> map );
	/**
	 * 根据编号查询
	 * @param tid
	 * @return
	 */
	public TrainNumber lhyfindByTid(String tid);

}
