package com.xibu.tickets.controller;


import com.xibu.tickets.domain.Users;
import com.xibu.tickets.dto.JsonObject;
import com.xibu.tickets.dto.ResultObject;
import com.xibu.tickets.service.IUsersService;
import com.xibu.tickets.utils.MailUtils;
import com.xibu.tickets.utils.RequestParamUtil;
import com.xibu.tickets.utils.SessionKeyConstant;
import com.xibu.tickets.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/UsersServlet")
@RestController
public class UsersController {

	@Autowired
	private IUsersService usersService ;

	/**
	 * 修改用户信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/update")
	public ResultObject update(Users user , HttpSession session ){
		System.out.println( user );
		int result = usersService.update(user) ;

		if ( result > 0 ) {
			// 更新登录用户的信息
			Users loginUser = (Users) session.getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );
			loginUser.setUname( user.getUname() );
			loginUser.setUtel( user.getUtel() );
			loginUser.setUemail( user.getUemail() );
			loginUser.setUidentify( user.getUidentify() );
			// 存储
			session.setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, loginUser);
			return new ResultObject( 200,"成功" );
		}

		return new ResultObject( 500,"失败" );
	}
	
	/**
	 * 查询登录的用户信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/showUser")
	public ResultObject showUser(HttpSession session ){
		Users loginUser = (Users)session.getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		return new ResultObject( 200, loginUser );
	}
	
	 
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/register")
	public ResultObject register(Users user){
		// 添加用户，此时用户的status为0，当点击激活后改为1
		int result = usersService.register(user) ;
		
		if ( result < 0 ) {
			// req.getRequestDispatcher( "/WEB-INF/pages/error.html").forward(req, resp);
			return new ResultObject( 500 , "失败 ") ;
		}
		// 根据邮箱地址发送邮箱
		boolean flag = MailUtils.sendMail(user.getUemail(), "<a href='http://localhost:8080/tickets/UsersServlet/activeUser?identify="+ user.getUidentify() +"'>点击激活账号</a>", "12307铁路网") ;
		return new ResultObject( 200 , "成功 ") ;
	}
	
	/**
	 * 激活账户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/activeUser")
	public ResultObject activeUser(String identify , HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		if ( "".equals(identify) || identify == null ) {
			// req.getRequestDispatcher( "/WEB-INF/pages/error.html").forward(req, resp);
			return new ResultObject( 500 , "失败 ") ;
		}
		
		// 激活身份证号为identify的用户，将用户的状态修改为1
		int result = usersService.active( identify ) ;
		
		if ( result < 0 ) {
			return new ResultObject( 500 , "失败 ") ;
		} 
		
		// 跳转到激活界面
//		req.getRequestDispatcher( "/tickets/index.html").forward(req, resp);
		resp.sendRedirect("/tickets/login.html");
		return new ResultObject( 200 , "成功 ") ;
	}
	
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login")
	public ResultObject login(@RequestParam Map<String,Object> map , HttpSession session ){

		System.out.println("敏感词过滤后：" + map );

		String CheckCode = (String) map.get("CheckCode");

		// 首先判断验证码是否正确
		String code = (String)session.getAttribute(SessionKeyConstant.VERIFICATIONCODE );
		
		if ( StringUtil.checkNull( CheckCode ) || !CheckCode.equalsIgnoreCase( code )) {
			return new ResultObject(500, "验证码错误");
		}
		
		// 判断用户是否已经登录
		Object obj = session.getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );
		if ( obj != null ) {
			return new ResultObject( 500, "用户已经登录");
		}
		// 登录
		Users loginUser = usersService.login( map );
		if ( loginUser == null  ) {
			return new ResultObject( 500,"失败");
		}
		
		// 将用户存入session
		session.setAttribute( SessionKeyConstant.MEMBERINFOLOGIN , loginUser );

		return new ResultObject(  200,"成功");
	}
	 
	
	/**
	 * 退出登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/reback")
	public ResultObject reback(HttpSession session) {
		 
		Object obj = session.getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		
		if ( obj == null ) {
			return new ResultObject( 500,"请先登录");
		}
		
		session.removeAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		return new ResultObject(  200,"退出成功");
	}
	
	
	/**
	 * 通过邮箱查找用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findByEmail")
	public ResultObject findByEmail(String email){
		// 判空
		if ( email == null || "".equals( email ) ) {
			return new ResultObject(  500,"邮箱地址为空");
		}
		// 通过邮箱查询用户
		Users users = usersService.findByEmail( email ) ;
		
		if ( users == null ) {
			return new ResultObject( 500,"用户不存在");
		}
		
		// 发送邮箱
		MailUtils.sendMail( email , "你的密码为：" + users.getUpassword() + ",请妥善保管", "12307密码找回") ;

		return new ResultObject(  200,"成功找回");
	}


	@RequestMapping("/lhyfindByUid")
	public ResultObject lhyfindByUid(String uid){
		Users user = usersService.lhyfindByUid(uid);
		if(user != null) {
			return new ResultObject(  200, user);
		}
		return new ResultObject( 500, "失败");
	}

	@RequestMapping("/lhyupdateStatus")
	public ResultObject lhyupdateStatus(@RequestParam Map<String,Object> map) {
		int rs = usersService.lhyupdateStatus(map);
		if(rs > 0) {
			return new ResultObject(  200, "成功");
		}
		return new ResultObject(  500, "失败");
	}

	@RequestMapping("/lhyfindByCondition")
	public JsonObject lhyfindByCondition(@RequestParam Map<String,Object> map){
		JsonObject obj = usersService.lhyfindByCondition(RequestParamUtil.findByPageUtil(map));
		if(obj != null) {
			return obj;
		}
		return null ;
	}

	@RequestMapping("/lhyfindByPage")
	public JsonObject lhyfindByPage(@RequestParam Map<String,Object> map) {
		JsonObject obj = usersService.lhyfindByPage(RequestParamUtil.findByPageUtil(map));
		if(obj != null) {
			return obj ;
		}
		return null ;
	}
}






























