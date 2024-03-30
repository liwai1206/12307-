package com.xibu.tickets.controller;


import com.xibu.tickets.domain.MyRefund;
import com.xibu.tickets.domain.PageBean;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.IRefundTicketsService;
import com.xibu.tickets.utils.RequestParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/RefundTicketsServlet")
@RestController
public class RefundTicketsController {

	@Autowired
	private IRefundTicketsService refundTicketsService ;

	@RequestMapping("/findByPage")
	public ResultObject findByPage(String page ) {
		 PageBean<MyRefund> pageBean = refundTicketsService.findByPage( page );
		 
		 if ( pageBean == null  ) {
			return new  ResultObject( 500, pageBean);
		}

		return new  ResultObject(200,pageBean);
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus(@RequestParam Map<String,Object> map ) {
		int rs = refundTicketsService.lhyupdateStatus( map );
		if(rs > 0) {
			return new  ResultObject( 200, "成功");
		}
		return new  ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map ) {
		System.out.println( map );
		JsonObject obj = refundTicketsService.lhyfindByCondition(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage(  @RequestParam Map<String,Object> map ) {
		JsonObject obj = refundTicketsService.lhyfindByPage(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null;
	}
}