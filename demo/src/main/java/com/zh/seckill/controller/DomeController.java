package seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zh
 * @date 2022/4/11 14:22
 * @description:说明
 */
@Controller
@RequestMapping("/demo")
public class DomeController {

    @RequestMapping("/test")
    public String test01(Model model){
        model.addAttribute("name","测试01");
        return "hello";
    }
}
