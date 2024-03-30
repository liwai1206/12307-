package com.xibu.tickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 李外
 * @ProjectName: tickets
 * @PackageName: com.xibu.tickets.controller
 * @Description:
 * @email : liwai_2012220663@163.com
 * @date 2022/5/21 18:09
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "login" ;
    }

}
