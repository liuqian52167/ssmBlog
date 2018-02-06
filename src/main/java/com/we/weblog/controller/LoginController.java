package com.we.weblog.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.service.LogService;
import com.we.weblog.service.UserService;
import com.we.weblog.tool.IpTool;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@Controller
public class LoginController extends BaseController{


    private   UserService userService;
    private   LogService  logService;



    @Autowired
     LoginController(UserService userService,LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    /**
     * 登录 （注解跳过权限验证）
     */
  //  @Login(action = Action.Skip)
    @PostMapping("/admin")
    public String doLogin(HttpServletRequest request) throws Exception {
        /**
         * 生产环境需要过滤sql注入
         */
        // id 先写到1
        WafRequestWrapper req = new WafRequestWrapper(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean result = userService.checkLogin(username, password);
        if (result) {
            //创建日志
            Log loginLog =new Log(LogActions.LOGIN,username, IpTool.getIpAddress(request),1);
            if(logService.addLog(loginLog)<0)
                throw  new Exception("loginLog add error");

            //这里创建session 防止重复登录
            SSOHelper.setCookie(request, response, SSOToken.create().setIp(request).setId(1000).setIssuer(username), false);
            return redirectTo("/admin/index.html");
        }else {
            return   redirectTo("/login1.html");
        }
    }





    @GetMapping("/logout")
    public String logout() {
        SSOHelper.clearLogin(request, response);
        logService.addLog(new Log(LogActions.LOGOUT,null,IpTool.getIpAddress(request),1));

        return redirectTo("/index.html");

    }





     /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/login")
    public String login() {
        // 设置登录 COOKIE
        SSOToken st = SSOHelper.getSSOToken(request);
        if(st != null) {

            return redirectTo("/admin/index.html");
        }
        //在这里处理拦截请求
        /*
         * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
         * HttpUtil.decodeURL(xx) 解码后重定向
         */
        return redirectTo("/login1.html");
    }



}
