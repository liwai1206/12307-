package com.xibu.tickets.controller;

import com.alibaba.fastjson.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@RequestMapping("/QRCode")
@Controller
public class QRCode {

	 @RequestMapping("/generateQRCode")
	 public void generateQRCode(HttpServletRequest req , HttpServletResponse resp) throws WriterException, IOException, JSONException {
	     // 获取支付方式的参数
		 String payMethods = req.getParameter("paymethods");
		 
		 // 生成一个二维码

		// 定义一个json格式的字符串,实验fastjson
		// 1. 创建一个JSONObject对象
		JSONObject jsonObject = new JSONObject() ;

		// 2. 存入值
		jsonObject.put("conpany","http://127.0.0.1:8080/tickets") ;
		jsonObject.put("author","李外") ;
		jsonObject.put("address","南华大学") ;
		jsonObject.put("paymethods", payMethods) ;

		// 3.将jsonobject对象转换成json格式的字符串
		String content = jsonObject.toString();

		// 定义二维码的宽高
		int width = 200 ;
		int height = 200 ;

		// 创建map集合
		Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType, Object>() ;
		hints.put(EncodeHintType.CHARACTER_SET,"UTF-8") ;

		// 创建一个矩阵对象
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints) ;

		// 生成路径
	   // String pathName = "D:\\" ;
		// 文件名称
		//String fileName = "QRCode.jpg" ;
		// 将图片保存到系统文件
		//Path path = FileSystems.getDefault().getPath( pathName,fileName) ;

	   // MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path) ;

		// 将图片渲染到界面
		MatrixToImageWriter.writeToStream(bitMatrix, "jpg", resp.getOutputStream());

	}
}