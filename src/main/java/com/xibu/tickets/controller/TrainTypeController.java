package com.xibu.tickets.controller;


import com.xibu.tickets.domain.TrainType;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.ITrainTypeService;
import com.xibu.tickets.utils.RequestParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/TrainTypeServlet")
@RestController
public class TrainTypeController {

	@Autowired
	private ITrainTypeService trainTypeService ;

	/**
	 * 查询所有车票类型
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findAll")
	public ResultObject findAll() throws IOException {
		List<TrainType> list = trainTypeService.findAll() ;
		return new ResultObject( 200, list);
	}

	@RequestMapping("/lhyfinds")
	public JsonObject lhyfinds() throws IOException {
		JsonObject obj = trainTypeService.lhyfinds();
		if(obj != null) {
			return  obj;
		}
		return null;

	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage(@RequestParam Map<String,Object> map) {
		JsonObject obj =  trainTypeService.lhyfindByPage(RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return  obj;
		}
		return null;
		
	}

	@RequestMapping("/lhyadd")
	public ResultObject lhyadd( String tname ) {
		int rs = trainTypeService.lhyadd(tname);
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
	}
}