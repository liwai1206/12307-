package com.xibu.tickets.controller;


import com.xibu.tickets.domain.TrainNumber;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.ITrainNumberService;
import com.xibu.tickets.utils.RequestParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/TrainNumberServlet/*")
@RestController
public class TrainNumberController {

	@Autowired
	private ITrainNumberService trainNumberService ;

	@RequestMapping("/lhyupdate")
	public ResultObject lhyupdate(@RequestParam Map<String,Object> map ) {
		int rs = trainNumberService.lhyupdate( map );
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
		
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus ( @RequestParam Map<String,Object> map ) {
		int rs = trainNumberService.lhyupdateStatus( map );
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map ) {
		JsonObject obj = trainNumberService.lhyfindByCondition( RequestParamUtil.findByPageUtil(map));
		if(obj != null) {
			return obj ;
		}
		return null;
	}

	@RequestMapping("/lhyfindByTid")
	public ResultObject lhyfindByTid(String tid ) {
		TrainNumber trainNumber = trainNumberService.lhyfindByTid(tid);
		if(trainNumber != null) {
			return new ResultObject(  200, trainNumber);
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage(@RequestParam Map<String,Object> map ) {
		JsonObject obj = trainNumberService.lhyfindByPage( RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj ;
		}
		return null;
	}

	@RequestMapping("/lhyadd")
	public ResultObject lhyadd( @RequestParam Map<String,Object> map  ) {
		int rs = trainNumberService.lhyadd( map );
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}
}