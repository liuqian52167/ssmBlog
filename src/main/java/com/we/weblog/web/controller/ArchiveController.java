package com.we.weblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 *     前台文章归档控制器
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/30 11:04
 */

@Controller
public class ArchiveController {
    /**
     * 首页视图
     * @return
     */
    @GetMapping("/article")
    public String article(){
        return "/article";
    }


    /**
     * 首页视图
     * @return
     */
    @GetMapping("/archive")
    public String archive(){
        return "/archive";
    }
}
