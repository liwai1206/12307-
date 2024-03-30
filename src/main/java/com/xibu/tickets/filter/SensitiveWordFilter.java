package com.xibu.tickets.filter;

import org.springframework.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


/**
 * 敏感词汇过滤器
 * @author 外哥
 * 创建时间: 2020年11月20日 下午8:21:24
 */
@SuppressWarnings("all")
@WebFilter("/*")
public class SensitiveWordFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		ServletRequest proxyRequest = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(),new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 得到方法名称
				String methodName = method.getName();
				// 判断是否是getParameter方法
				if ("getParameter".equals( methodName )) {
					// 是getParameter方法，对参数进行增强
					String params = (String) method.invoke(request, args);
					if ( params != null ) {
//						System.out.println("前台参数为：" + params);
						for (String str : list) {
							if ( params.contains(str) ) {
//								System.out.println("含有敏感词:" + str);
								params = params.replaceAll(str, "***") ;
							}
						}
					}
					return params ;
				}
				if ("getParameterMap".equals(methodName)) {
					// 是getParameterMap方法，对参数进行增强
					Map<String, String[]> params = (Map<String, String[]>) method.invoke(request, args);
					Map<String, String[]> params2 = new HashMap<String, String[]>() ;
					
					// 得到所有的键
					Set<String> keys = params.keySet();
					Iterator<String> iterator = keys.iterator();
					
					// 遍历map集合
					while ( iterator.hasNext() ) {
						String key = iterator.next();
						String[] values = params.get(key);
						
						if ( values != null ) {
//							System.out.println("前台参数为：" + key + ":" + values[0]);
							// 替换敏感字符
							for ( int i = 0 ; i < values.length ; i++ ) {
								for (String str : list) {
									if ( values[i].contains(str) ) {
//										System.out.println("含有敏感词:" + str);
										values[i] = values[i].replaceAll(str, "***") ;
									} 
								}
							}   
						} 
						params2.put(key, values) ;
					}
					return params2 ;
				}
				
				return method.invoke(request, args);
			}
		} ) ;

		chain.doFilter(proxyRequest, response);
	}

	public void destroy() {

	}

	// 定义一个敏感字符的集合
	private List<String> list = new ArrayList<String>() ;
	
	public void init(FilterConfig config) throws ServletException {
		try {
			// 获取文件的真实路径
//			String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/sensitiveword.txt");
			String realPath = ResourceUtils.getURL("classpath:").getPath() + "sensitiveword.txt" ;
			System.out.println( realPath );

			// 读取sensitiveword.txt文件，将每一行的内容添加到list集合中
			BufferedReader reader = new BufferedReader( new FileReader(realPath) ) ;
			
			String lineString = "" ;
			while( (lineString = reader.readLine()) != null ) {
				list.add(lineString) ;
			}
			
			// 关闭流
			reader.close(); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
















