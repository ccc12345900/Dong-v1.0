package com.dong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/websocket/{name}")
    public String webSocket(@PathVariable String name, HttpSession session, Model model){
        try{
            if(session.getAttribute("employee")!=null) {
                logger.info("跳转到websocket的页面上");
                model.addAttribute("username", name);
                return "test";
            }else if(session.getAttribute("employer")!=null)
            {
                logger.info("跳转到websocket的页面上");
                model.addAttribute("username", name);
                return "test2";
            }
            return "error";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }

}
