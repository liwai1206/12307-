package com.xibu.tickets.service.impl;


import com.xibu.tickets.dao.IOperateDao;
import com.xibu.tickets.dao.IOrderItemDao;
import com.xibu.tickets.domain.*;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.service.IOrderItemService;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月9日 上午11:34:26
*/
@Service
@Transactional
public class OrderItemServiceImpl implements IOrderItemService {
	@Autowired
	private IOrderItemDao orderItemDao;
	@Autowired
	private IOperateDao operateDao ;

	/**
	 * 购票
	 */
	@Override
	public int adds( Map<String,Object> map ) {
		List<String> seatNumbers = orderItemDao.findSeatNumber(Integer.parseInt( map.get("oid")+"" )) ;
		int number = 0 ;
		boolean flag = false ;
		List<Integer> secord= new ArrayList<Integer>() ;
		
		if ( seatNumbers.isEmpty()) {
			// 如果之前没有座位号
			seatNumbers.add("SN1")  ;
			number = 1 ;
		} 
		
		// 循环添加，需要额外给一个座位号
		for (UserInfo userInfo : (List<UserInfo>)map.get("passengers")) {
			Map<String,Object> paramMap = new HashMap<>() ;
			paramMap.put("oid", map.get("oid")) ;
			Users user = (Users) map.get("user");
			paramMap.put("uid", user.getUid()) ;
			paramMap.put("cardNumber", userInfo.getCardNumber()) ;
			paramMap.put("name", userInfo.getName()) ;
			paramMap.put("price", userInfo.getPrice()) ;
			paramMap.put("ticketType", userInfo.getTicketType()) ;
			paramMap.put("siteType", userInfo.getSiteType()) ;

			seatNumbers = orderItemDao.findSeatNumber(Integer.parseInt( map.get("oid")+"" )) ;
			
			// 循环座位号，把空缺的座位号补齐
			for (int i = 0; i < seatNumbers.size() ; i++) {
				number = Integer.parseInt( seatNumbers.get(i).substring(2) );
				secord.add(number) ; 
			}
			
			for (int i = 0; i < secord.size() ; i++) {
				if ( !secord.contains( i + 1 )) {
					number = i + 1 ;
					flag = true ;
					break ;
				}  
			}
			
			if ( !flag ) {
				number =  secord.size() + 1 ;
			}
			
			String seat = "SN" + number ;

			paramMap.put("seat" , seat ) ;
			orderItemDao.adds( paramMap );
				
			// 如果订单添加一个，则相应的运营车次的座位就要减一
//			System.out.println( paramMap.get("siteType") );
			operateDao.subSeat( paramMap );
		}
		
		return 0;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean<MyOrders> findByPage(String page) {
		
		// 查询总记录
		int totalCount = orderItemDao.total();
		
		// 总页数
		int totalPage =  (int) Math.ceil( totalCount / 5.0 ) ; 

		Map<String,Object> map = new HashMap<>() ;
		map.put( "page" , ( Integer.parseInt( page ) - 1) * 5 ) ;
		map.put("rows" , 5 ) ;
		// 每一页的数据
		List<MyOrders> list = orderItemDao.findByPage( map );
		
		// 当前页码,就是形参page 
		// 每页显示的条数,指定为5条
		
		PageBean<MyOrders> pageBean = new PageBean<MyOrders>() ;
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setlist(list);
		pageBean.setRows(5);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	
	/**
	 * 退票
	 */
	@Override
	public int backTickets(String oid) {
		/*
		 * 退票需要进行的操作,使用事务,后两步需要后台管理员的操作
		 * 	1. 将退票信息添加到退票表中，相应的信息应该通过订单id即oid来获取
		 * 	2. 在订单表中删除这个订单信息
		 * 	3. 在运营表中给相应的座位数量加一
		 */
		Connection con = null;
		int result = 0 ; 
		//	1. 将退票信息添加到退票表中 
		OrderItem oItem = orderItemDao.findById( Integer.parseInt( oid ));
		
		RefundTicket rTicket = new RefundTicket() ;
		rTicket.setOid(Integer.parseInt( oid ));
		rTicket.setOperateid( oItem.getOperateid() );
		rTicket.setUid( oItem.getUid() );
		rTicket.setIdentify( oItem.getIdentify() );
		rTicket.setName( oItem.getName() );
		rTicket.setMoney( oItem.getMoney() );
		result = orderItemDao.add( rTicket );
		
		// 将相应订单的状态改为2，即待审核
		orderItemDao.updateState( oid );
		
		//2. 在订单表中删除这个订单信息
		//dao.delete( Integer.parseInt( oid ));
		
		// 3. 在运营表中给相应的座位数量加一
		//dao.addSeat( oItem.getOperateid(),oItem.getSeattype() ); 
		
		return result;
	}
	
	@Override
	public JsonObject lhyfindByPage(Map<String,Object> map) {
		return new JsonObject(orderItemDao.lhytotal(true), orderItemDao.lhyfindByPage( map ));
	}

	@Override
	public JsonObject lhyfindByCondition( Map<String,Object> map ) {
		return new JsonObject(orderItemDao.lhytotals( map ), orderItemDao.lhyfindByCondition(map));
	}

	@Override
	public OrderItem lhyfindByOid(String oid) {
		return orderItemDao.lhyfindByOid(oid);
	}

	@Override
	public List<Map<String, String>> lhysumByTid() {
		return orderItemDao.lhysumByTid();
	}

	@Override
	public List<Map<String, String>> lhysumByYear(String year) {
		if(StringUtil.checkNull(year)) {
			return null;
		}
		return orderItemDao.lhysumByYear(year);
	}

	@Override
	public List<Map<String, String>> lhysumByMonth(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("year")+"" , map.get("month")+"")) {
			return null;
		}
		String month = (String) map.get("month");
		String dyear = "" ;
		if ( month.length() < 2 ){
			dyear = map.get("year") + "-0" + map.get("month");
		}else {
			dyear = map.get("year") + "-" + map.get("month");
		}

		map.put("dyear",dyear) ;
		return orderItemDao.lhysumByMonth(map);
	}

	@Override
	public List<Map<String, String>> lhysumByDate(String date) {
		if(StringUtil.checkNull(date)) {
			return null;
		}
		return orderItemDao.lhysumByDate(date);
	}

	@Override
	public List<Map<String, Object>> exportExcelData() {
		return orderItemDao.exportExcelData();
	}

}
