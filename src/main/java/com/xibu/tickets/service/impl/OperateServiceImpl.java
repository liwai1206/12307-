package com.xibu.tickets.service.impl;


import com.xibu.tickets.dao.IOperateDao;
import com.xibu.tickets.domain.Operate;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.domain.UserInfo;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.IOperateService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:24:37
*/
@Service
@Transactional
public class OperateServiceImpl implements IOperateService {
	@Autowired
	private IOperateDao operateDao ;

	/**
	 * 分页查询
	 */
	@Override
	public PageBean<Operate> findByType(Map<String,Object> map ) {
		PageBean<Operate> bean = new PageBean<Operate>() ;
		bean.setRows( 5 ); 
		bean.setCurrentPage( Integer.parseInt( map.get( "page")+"" ) );

		SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		// 开始日期
		String startdate = sdf01.format(new Date() ).split(" ")[0] ;

		// 结束日期的月份
		int endMonth = ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  % 12 )  ;
		String endmouth = "";
		if ( endMonth < 10 ) {
			endmouth = "0" + endMonth ;
		}else {
			endmouth = endMonth + "" ;
		}
		// 结束日期的年份
		int endYear =Integer.parseInt( startdate.split("-")[0] ) + ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  / 12 ) ;
		// 结束日期的年天数
		int endDay = Integer.parseInt( startdate.split("-")[2] ) ;
		String endday = "" ;
		if ( endDay < 10 ) {
			endday = "0" + endDay ;
		}else {
			endday = endDay + "" ;
		}
		String enddate = endYear+"-"+endmouth+"-"+endday ;
		map.put("startdate" , startdate ) ;
		map.put("enddate" , enddate ) ;

		// 总记录数
		int totalCount = 0 ;
		totalCount = operateDao.total(map) ;
		bean.setTotalCount(totalCount);
		
		// 总页数
		int totalPage = (int) Math.ceil( totalCount / 5.0 ) ;
		bean.setTotalPage(totalPage);
		
		// 每一页的数据
		List<Operate> list = operateDao.findByType( map );
		bean.setlist(list); 
		
		return bean;
	}

	/**
	 * 计算总价
	 */
	@Override
	public double totalPrice(Map<String,Object> map) {
		
		double totalprice = 0.0d ;
		
		if (  map.isEmpty() || StringUtil.checkNull( map.get( "oid" )+"" ) ) {
			return 0.0d ;
		}

		List<UserInfo> users = (List<UserInfo>) map.get("array");

		for (UserInfo userInfo : users) {
			if ( StringUtil.checkNull( userInfo.getSiteType() , userInfo.getTicketType() ) ) {
				continue ;
			}
			map.put("ticketType",userInfo.getTicketType() ) ;
			map.put("siteType",userInfo.getSiteType() ) ;
			totalprice += operateDao.totalPrice( map );
		}

		return totalprice;
	}

	/**
	 * 查询余票
	 */
	@Override
	public int findRemain(Map<String,Object> map) {
		int remainNumber = operateDao.findRemain( map );
		return remainNumber ;
	}
	
	@Override
	public int lhyadd( Operate operate ) {
		if(StringUtil.checkNull(operate.getTid()+"" , operate.getRworemain()+"", operate.getYworemain()+"", operate.getRzuoremain()+"", operate.getYzuoremain()+"", operate.getZhanremain()+"", operate.getDate(), operate.getStarttime(), operate.getEndtime())) {
			return -1;
		}
		return operateDao.lhyadd( operate );
	}

	@Override
	public int lhyupdateRemain(Operate operate) {
		if(StringUtil.checkNull(operate.getTid()+"" )) {
			return -1;
		}
		return operateDao.lhyupdateRemain(operate);
	}

	@Override
	public int lhyupdateStatus(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("oid")+"", map.get("status")+"")) {
			return -1;
		}
		return operateDao.lhyupdateStatus(map);
	}


	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		if(StringUtil.checkNull( map.get("page")+"", map.get("rows") + "")) {
			return null;
		}
		return new JsonObject(operateDao.lhytotal(true), operateDao.lhyfindByPage(map));
	}


	@Override
	public JsonObject lhyfindByCondition( Map<String,Object> map ) {
		return new JsonObject(operateDao.lhytotals(map), operateDao.lhyfindByCondition(map));
	}

	@Override
	public Operate lhyfindByOid(String oid) {
		if(StringUtil.checkNull(oid)) {
			return null;
		}
		return operateDao.lhyfindByOid(oid);
	}

	@Override
	public int lhyupdate( Map<String,Object> map ) {
		if(StringUtil.checkNull( map.get("oid")+"", String.valueOf( map.get("rworemain") ), String.valueOf( map.get( "yworemain") ), String.valueOf( map.get( "rzuoremain")) , String.valueOf( map.get( "yzuoremain") ), String.valueOf( map.get( "zhanremain" )), map.get( "date" )+"",  map.get( "starttime")+"" ,  map.get( "endtime") +"") ) {
			return -1;
		}
		return operateDao.lhyupdate( map );
	}

    @Override
    public TrainNumber findByTid(Integer tid) {

		return operateDao.findByTid( tid );
    }

}














