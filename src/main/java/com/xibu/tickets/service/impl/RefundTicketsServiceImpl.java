package com.xibu.tickets.service.impl;


import com.xibu.tickets.dao.IRefundTicketsDao;
import com.xibu.tickets.domain.MyRefund;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.IRefundTicketsService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:02:51
*/
@Service
@Transactional
public class RefundTicketsServiceImpl implements IRefundTicketsService {

	@Autowired
	private IRefundTicketsDao refundTicketsDao;

	@Override
	public PageBean<MyRefund> findByPage(String page) {
		// 查询总记录
		int totalCount = refundTicketsDao.total();
		
		// 总页数
		int totalPage =  (int) Math.ceil( totalCount / 5.0 ) ;
		Map<String,Object> map = new HashMap<>() ;
		map.put("page" , Integer.parseInt(page) ) ;
		map.put("rows" , 5 ) ;

		// 每一页的数据
		List<MyRefund> list = refundTicketsDao.findByPage( map);
		
		// 当前页码,就是形参page 
		// 每页显示的条数,指定为5条
		PageBean<MyRefund> pageBean = new PageBean<MyRefund>() ;
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setlist(list);
		pageBean.setRows(5);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(refundTicketsDao.lhytotal(true), refundTicketsDao.lhyfindByPage( map ));
	}

	@Override
	public JsonObject lhyfindByCondition(Map<String,Object> map) {
		return new JsonObject(refundTicketsDao.lhytotals( map ), refundTicketsDao.lhyfindByCondition( map ));
	}

	@Override
	public int lhyupdateStatus(Map<String,Object> map) {
		if(StringUtil.checkNull( map.get( "rid")+"", map.get( "status")+"")) {
			return -1;
		}

		int result = -1 ;
		if ( "0".equals( map.get( "status")+"" )) {
			// 如果批准，则修改退票表的状态，删除订单表的相应订单。增加相应的座位
			result = refundTicketsDao.lhyupdateStatu01( map );
			result = refundTicketsDao.lhyupdateStatu02( map.get("rid")+"" );
			result = refundTicketsDao.lhyupdateStatu03( map.get("rid")+"" );
			return result ;
		}
		// 如果不批准，则删除修改订单表的订单状态
		// 首先根据rid查询oid
		int oid = refundTicketsDao.findOid( map.get( "rid")+"" );
		// 修改订单的状态
		map.put( "oid",oid ) ;
		refundTicketsDao.updateStatusRefundStatus(map) ;
		result = refundTicketsDao.updateStatus( map ) ;
		return result ;
	}
}
