package com.xibu.tickets.controller;


import com.xibu.tickets.domain.*;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.IOperateService;
import com.xibu.tickets.service.IOrderItemService;
import com.xibu.tickets.utils.DBToExcel;
import com.xibu.tickets.utils.RequestParamUtil;
import com.xibu.tickets.utils.SessionKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.*;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
@RequestMapping("/OrderItemServlet")
@RestController
public class OrderItemController{

	@Autowired
	private IOrderItemService orderItemService ;

	@Autowired
	private IOperateService operateService ;

	/**
	 * 将数据导出excel表
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/ExportExcel")
	public ResultObject ExportExcel(){
		String filePath = "G:\\Users\\IDEAAdministrator02\\tickets\\excels\\" ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmss") ;
		filePath += sdf.format( new Date() ) ;
		filePath += UUID.randomUUID() ;

//		System.out.println( filePath );

//		String sql = "select tn.tname as '车次名称', SUM(ods.money) as '营业总额' from orderitems ods, operate op ,train_number tn where ods.operateid=op.oid and op.tid=tn.tid group by ods.operateid" ;
		List<Map<String, Object>> list = orderItemService.exportExcelData();
		int result = DBToExcel.exportExcel(list, filePath , "营业额", 0);
		
		if ( result > 0 ) {
			return new ResultObject( 200, "成功");
		}

		return new ResultObject( 500, "失败");
	}
	
	
	/**
	 * 将乘客信息存入session域中
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/savePassengersToSession")
	public ResultObject savePassengersToSession(String passengers , String oid , HttpSession session){
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> users = JSON.parseArray(passengers, UserInfo.class);

		// 将信息存入session中
		session.setAttribute(SessionKeyConstant.PASSENGERS	, users);
		session.setAttribute(SessionKeyConstant.OID, oid);

		return new ResultObject( 200 , "成功");
	}

	@RequestMapping("/findPassengersFromSession")
	public ResultObject findPassengersFromSession( HttpSession session ){
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> passengers = (List<UserInfo>) session.getAttribute(SessionKeyConstant.PASSENGERS) ;
		return new ResultObject(  200, passengers);
	}
	
	
	/**
	 * 计算总价
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/calTotalPrice")
	public ResultObject calTotalPrice(@RequestParam Map<String,Object> map ){
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> users = JSON.parseArray(map.get("array")+"", UserInfo.class);
		map.put("array" , users ) ;
		map.put("oid" , map.get("oid") ) ;
		double totalPrice = operateService.totalPrice( map );
		return new ResultObject(200, totalPrice);
	}
	
	/**
	 * 添加订单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/addOrderItem")
	public void addOrderItem(HttpSession session ){
		 List<UserInfo> passengers = (List<UserInfo>) session.getAttribute( SessionKeyConstant.PASSENGERS ) ;
//		System.out.println( passengers );
		 String oid = (String) session.getAttribute( SessionKeyConstant.OID );
//		System.out.println( oid );
		 Users user = (Users) session.getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );

		 Map<String,Object> map = new HashMap<>() ;
		 map.put("passengers" , passengers) ;
		 map.put("oid" , oid) ;
		 map.put("user" , user) ;

		 int result = orderItemService.adds(map) ;
	}
	
	/**
	 * 分页查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findByPage")
	public ResultObject findByPage(String page){
		 PageBean<MyOrders> pageBean = orderItemService.findByPage( page );
		 if ( pageBean == null  ) {
			return new ResultObject( 500, pageBean);
		}

		return new ResultObject(  200,pageBean);
	}
	
	/**
	 * 退票
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/backTickets")
	public ResultObject backTickets( String oid ){
		 // 获取订单id
		 int i = orderItemService.backTickets( oid );
		 
		 if ( i > 0 ) {
			 return new ResultObject(  200,"成功");
		}

		return new ResultObject( 500,"失败");
	}

	@RequestMapping("/lhysumByDate")
	public ResultObject lhysumByDate( String date  ){

		List<Map<String, String>> list = orderItemService.lhysumByDate(date);
		if(list != null && list.size()!=0) {
			return new ResultObject(200, list);
		}
		return new ResultObject( 500, "该时间内没有数据！");
	}

	@RequestMapping("/lhysumByMonth")
	public ResultObject lhysumByMonth( @RequestParam Map<String,Object> map) {
		System.out.println( "controller:" + map );
		List<Map<String, String>> list = orderItemService.lhysumByMonth(map);
		if(list != null && list.size()!=0) {
			return new ResultObject(200, list);
		}
		return new ResultObject( 500, "该时间内没有数据！");
	}

	@RequestMapping("/lhysumByYear")
	public ResultObject lhysumByYear(String year ) {
		List<Map<String, String>> list = orderItemService.lhysumByYear(year);
		if(list != null && list.size()!=0) {
			return new ResultObject(200, list);
		}
		return new ResultObject( 500, "该时间内没有数据！");
	}

	@RequestMapping("/lhysumByTid")
	public ResultObject lhysumByTid(){
		List<Map<String, String>> list = orderItemService.lhysumByTid();
		if(list != null && list.size()!=0) {
			return new ResultObject(200, list);
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByOid")
	public ResultObject lhyfindByOid( String oid ) {
		OrderItem item = orderItemService.lhyfindByOid(oid);
		if(item != null) {
			return new ResultObject( 200, item);
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map ) {
		JsonObject obj = orderItemService.lhyfindByCondition(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage( @RequestParam Map<String,Object> map  ) {
		JsonObject obj = orderItemService.lhyfindByPage(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}
	
}