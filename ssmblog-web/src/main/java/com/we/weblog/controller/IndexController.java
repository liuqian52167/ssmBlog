package com.we.weblog.controller;

import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.controller.core.BaseController;
import com.we.weblog.domain.Category;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.modal.YearBlog;
import com.we.weblog.domain.result.Result;
import com.we.weblog.service.CommentService;
import com.we.weblog.service.HomeService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     拦截器，资源路径配置
 * </pre>
 * 首页、归档、分类、评论
 *   前端页面显示的控制器
 * @date 2019-03-28 13:49
 * @author Clay9
 */
@Controller
public class IndexController extends BaseController {
    //
    private PostService postService;
    //
    private CommentService commentSerivce;
    //
    private static int  postId ;
    //
    private static String  tagName = null;
    //
    private TagService tagService;

    @Resource
    private HomeService homeService;

    @Autowired
    public IndexController(PostService postService, TagService tagService, CommentService commentSerivce){
        this.commentSerivce = commentSerivce;
        this.tagService = tagService;
        this.postService = postService;
        //初始化postID 为第一个 防止单独访问为0 什么都木有
//        postId = postService.getLastestBlogId();
        postId = 0;
    }
    /**
     * 首页视图
     * @return
     */
    @GetMapping("/")
    public String index(){
        return redirectTo("/index");

    }

    @GetMapping("/about")
    public String about(){
        return redirectTo("/about");
    }

    /**
     *  先处理好数据 要不然后让所有url 在
     * @param id
     * @return
     */
    @GetMapping("/post/{id}")
    public void post(@PathVariable String id) throws IOException {
        postId = Integer.parseInt(id);
        response.sendRedirect("/article");
    }


    /**
     *  获取标签信息
     * @return
     */
    @GetMapping("/get_pages_data")
    @ResponseBody
    UIModel getTags() {
        FormModel formModel = new FormModel();
        formModel.createFormItem("title").setHidden(false).setLabel("页面名称");
        formModel.createFormItem("slug").setHidden(false).setLabel("页面路径");
        formModel.createFormItem("month").setHidden(false).setLabel("发布时间");
        formModel.createFormItem("publish").setHidden(false).setLabel("发布状态");

//        List<Post> tempContexts = postService.getArticlePages();
        TableData tableData = new TableData() ;
        tableData.setPage(true);
        tableData.setPageSize(15);
//        tableData.setDataItems(tempContexts);
        tableData.setFormItems(formModel.getFormItems());

        return UIModel.success().tableData(tableData) ;
    }


    @GetMapping("/front/base/{page}")
    @ResponseBody
    public Map<String,Object> getIndexData(@PathVariable String page) throws Exception {
        Map<String,Object> maps = new HashMap<>(20);
        try {
            //旁边博客展示都需要
            findResourceByPageType(maps , page);
            //todo 单独提供一个接口
            homeService.getViewCommon(maps, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return maps;
    }




    /**
     * 前端数据处理的主要函数
     * @param maps
     * @param pageType
     * @throws Exception
     */
    private void findResourceByPageType(Map<String,Object> maps, String pageType) throws Exception {
        try {
            //todo 这一块儿 可以优化  有点难懂
            if (pageType.equals(Types.PAGE_CATEGORY)) {
                Result categoryResult = postService.queryByCategory();
                maps.put(Types.CATEGORIES, categoryResult.getData());
            } else if (pageType.contains(Types.PAGE_ARTICLE)) {
                int getId = Integer.parseInt(pageType.substring(7,pageType.length()));
                if (getId <= 0) {
                    throw new Exception("GET ARTICLE ID FAIL,CHECK");
                }
                Post currentContext = (Post) postService.findByPostId(getId).getData();
                //增加一次访问量
                postService.updatePostVisit(currentContext);
                Post preContext = (Post) postService.findPreviousPost(getId).getData();
                Post nextContext = (Post) postService.findNextPost(getId).getData();

                int uid = currentContext.getUid();
                //显示评论
                if (uid > 0) {
                    List<Comment> comments = (List<Comment>) commentSerivce.findCommentByUid(uid).getData();
                    maps.put(Types.COMMENTS,comments);
                }
                maps.put(Types.CURRENT_BLOG, currentContext);
                maps.put(Types.NEXT_BLOG, nextContext);
                maps.put(Types.PREVIOUS_BLOG, preContext);

            } else if (pageType.equals(Types.PAGE_ARCHIVE)) {
                List<YearBlog> yearBlogs = (List<YearBlog>) postService.findPostByYearAndMonth(1).getData();
                maps.put(Types.BLOGS_DATA,yearBlogs);

            } else if (pageType.equals(Types.PAGE_ABOUT)) {
                Post about = (Post) postService.findAuthor().getData();
                maps.put(Types.BLOG,about);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
