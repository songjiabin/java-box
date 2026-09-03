package com.x.y.demo;


import com.x.y.bean.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/t1")
@Controller
public class TemplatesTest {

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "Hello,Thymeleaf");
        return "test";
    }

}
