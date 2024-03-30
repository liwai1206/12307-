package com.xibu.tickets.controller;


import com.xibu.tickets.domain.Operate;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.IOperateService;
import com.xibu.tickets.utils.RequestParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/OperateServlet")
@RestController
public class OperateController{

	@Autowired
	private IOperateService operateService ;
	/**
	 * 通过类型查询运营信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findByType")
	public ResultObject findByType(@RequestParam Map<String,Object> map) {
		Object page = map.get("page");
		if (  page == null ) {
			page = 1 + "" ;
			map.put("page",page) ;
		}
		map.put("rows",5) ;
		PageBean bean = operateService.findByType(RequestParamUtil.findByPageUtil( map )) ;
		return new ResultObject( 200 , bean );
		
	}
	
	/**
	 * 查询是否有余票
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findRemain")
	public ResultObject findRemain( @RequestParam Map<String,Object> map ){
		int remainNumber = operateService.findRemain( map );
		
		if ( remainNumber < 1 ) {
			return new ResultObject(  200, "没有余票");
		}

		return new ResultObject(  200,"成功");
	}

	@RequestMapping("/lhyupdate")
	public ResultObject lhyupdate(  @RequestParam Map<String,Object> map ) {
		int rs = operateService.lhyupdate( map );
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByOid")
	public ResultObject lhyfindByOid( String oid ){
		Operate obj = operateService.lhyfindByOid(oid);
		if(obj != null) {
			return new ResultObject( 200, obj);
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map  ){
		JsonObject obj = operateService.lhyfindByCondition(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage( @RequestParam Map<String,Object> map  ){
		JsonObject obj = operateService.lhyfindByPage(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus( @RequestParam Map<String ,Object> map ){
		int rs = operateService.lhyupdateStatus(map);
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyupdateRemain")
	public ResultObject lhyupdateRemain(Operate operate ) {
		int rs = operateService.lhyupdateRemain(operate);
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyadd")
	public ResultObject lhyadd( Operate operate) {
		int rs = operateService.lhyadd( operate );
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
	}

//	trainnumber
	@RequestMapping("/findByTid")
	public ResultObject findByTid( Integer tid ) {
		TrainNumber rs = operateService.findByTid( tid );
		if(rs != null) {
			return new ResultObject( 200, "成功", rs );
		}
		return new ResultObject( 500, "失败");
	}
	
}























