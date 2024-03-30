package com.xibu.tickets.controller;


import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.ISiteService;
import com.xibu.tickets.utils.RequestParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/SiteServlet")
@RestController
public class SiteController {

	@Autowired
	private ISiteService siteService ;

	@RequestMapping("/lhyfinds")
	public JsonObject lhyfinds() {
		JsonObject obj = siteService.lhyfinds();
		if(obj != null) {
			return obj ;
		}
		return null;
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage(@RequestParam Map<String,Object> map ) {
		JsonObject obj = siteService.lhyfindByPage( RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj;
		}
		return null ;
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus(@RequestParam Map<String,Object> map ) {
		int rs = siteService.lhyupdateStatus( map );
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyupdateSname")
	public ResultObject lhyupdateSname( @RequestParam Map<String,Object> map  ) {
		int rs = siteService.lhyupdateSname( map );
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyadd")
	public ResultObject lhyadd(  @RequestParam Map<String,Object> map  ) {
		map.put("status" , 1 ) ;
		int rs = siteService.lhyadd( map );
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}
	
}