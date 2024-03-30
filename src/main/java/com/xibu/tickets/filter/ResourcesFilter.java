package com.xibu.tickets.filter;

import com.xibu.tickets.utils.SessionKeyConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 资源过滤器
 * @author 外哥
 * 创建时间: 2020年11月30日 下午9:31:15
 */
@SuppressWarnings("all")
@WebFilter("/*")
public class ResourcesFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request ;
		HttpServletResponse resp = (HttpServletResponse) response ;
		
		String uri = req.getRequestURI();
		if ( req.getSession().getAttribute( SessionKeyConstant.MEMBERINFOLOGIN ) == null && req.getSession().getAttribute( SessionKeyConstant.BACKMEMBERINFOLOGIN ) == null) {
			if ( uri.contains("Listener") ||uri.contains("Filter") ||uri.contains("ticketsInfo.html")
					||  uri.contains("Servlet") ||uri.contains("register.html") ||uri.contains("findPwd.html")
					||uri.contains("login.html") ||uri.contains("index.html") || uri.contains("css")
					|| uri.contains("js") || uri.contains("images") ) {

//				System.out.println("进入过滤器1111");
				chain.doFilter(request, response);
			}

//			System.out.println("不通过过滤器33333");
		}else {
			// 如果已经登录了，则可以访问所有资源
//			System.out.println("进入过滤器2222");
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}