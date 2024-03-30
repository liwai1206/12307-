package com.xibu.tickets.controller;


import com.xibu.tickets.utils.DownloadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("all")
@RequestMapping("/DownLoadServlet")
@Controller
public class DownLoadServlet{
	/**
	 * 下载
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		获取请求参数，文件名称
		String filename = req.getParameter("filename") ;
		
		/*
		 * 使用字节输入流加载文件进内存
		 * 	1.找到文件服务器路径
		 * 	2，用字节流关联
		 */
		ServletContext context = req.getServletContext() ;

		// context.getRealPath(): 只能获取jsp的项目路径
//		String realPath = context.getRealPath("/classes/static/files/" + filename ) ;
		String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files/" + filename;
		System.out.println( realPath );
//		使用字节流关联
		FileInputStream fileInputStream = new FileInputStream(realPath) ;
		

		//获取MIME文件类型
		String mimeType = context.getMimeType(filename) ;

		//设置response的响应头,设置文件的类型
		resp.setHeader("content-type", mimeType ); 
		
//		解决中文文件名问题
//		1.获取user-agent请求头
//		2.使用工具类方法编码文件名
		String agent = req.getHeader("user-agent") ; 
		filename = DownloadUtils.getFileName(agent, filename) ;
		
//						设置资源的打开方式			附件			文件名		
		resp.setHeader("content-disposition", "attachment;filename=" + filename );
		
//		将输入流的数据写出到输出流中
		ServletOutputStream fOutputStream = resp.getOutputStream() ;
		byte[] buff = new byte[ 1024 ] ;
		int len = 0 ;
		
		while ( ( len = fileInputStream.read(buff) ) != -1 ) {
			fOutputStream.write( buff , 0 , len );
		}
		 
		fileInputStream.close();
	}
}