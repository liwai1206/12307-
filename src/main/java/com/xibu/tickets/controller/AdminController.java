package com.xibu.tickets.controller;


import com.xibu.tickets.domain.Admin;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.IAdminService;
import com.xibu.tickets.utils.RequestParamUtil;
import com.xibu.tickets.utils.SessionKeyConstant;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/AdminServlet")
@RestController
public class AdminController {

	@Autowired
	private IAdminService adminService ;
	/**
	 * 管理员登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login")
	public ResultObject login(@RequestParam Map<String,Object> map , HttpSession session) {

		String CheckCode = map.get( "CheckCode" )+"" ;

		// 首先判断验证码是否正确
		String code = (String) session.getAttribute(SessionKeyConstant.VERIFICATIONCODE );
		if ( StringUtil.checkNull( CheckCode  ) || !CheckCode.equalsIgnoreCase( code )) {
			return new  ResultObject( 500, "验证码错误");
		}
		 
		Admin admin = adminService.login( map );
		if ( admin == null  ) {
			return new  ResultObject( 500,"失败");
		}
		
		// 登陆成功，存入session
		session.setAttribute(SessionKeyConstant.BACKMEMBERINFOLOGIN, admin);

		return new  ResultObject( 200,"成功");
	}

	@RequestMapping("/lhysortByStatus")
	public JsonObject lhysortByStatus(@RequestParam Map<String,Object> map){
		JsonObject obj =  adminService.lhysortByStatus(RequestParamUtil.findByPageUtil(map));
		if(obj != null) {
			return new JsonObject( 200 , obj);
		}
		return new JsonObject( 500, "失败");
	}

	@RequestMapping("/lhygetAdmin")
	public ResultObject lhygetAdmin( HttpSession session ){
		Object obj = session.getAttribute(SessionKeyConstant.BACKMEMBERINFOLOGIN);
		if(obj != null) {
			return new ResultObject(200, (Admin)obj);
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus(String aid){
		int rs = adminService.lhyupdateStatus(aid);
		if(rs > 0) {
			return new ResultObject(  200, rs);
		}
		return new ResultObject( 500, "失败");
	}



	@RequestMapping("/lhyadd")
	public ResultObject lhyadd(@RequestParam Map<String,Object> map){
		int rs = adminService.lhyadd(map);
		if(rs > 0) {
			return new ResultObject( 200, "成功");
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map) {
		JsonObject obj = adminService.lhyfindByCondition( RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return  obj;
		}
		return null;
	}

	@RequestMapping("/lhyfindByAid")
	public ResultObject lhyfindByAid(String aid) {
		Admin admin = adminService.lhyfindByAid(aid);
		if(admin != null) {
			return new ResultObject(  200, admin);
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage( @RequestParam Map<String,Object> map ){
		JsonObject obj =  adminService.lhyfindByPage( RequestParamUtil.findByPageUtil( map ));
		if(obj != null) {
			return obj ;
		}
		return null ;
	}
}