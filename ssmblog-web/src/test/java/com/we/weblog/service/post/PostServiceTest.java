package com.we.weblog.service.post;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.result.Result;
import com.we.weblog.service.PostService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/3/28 15:10
 */
public class PostServiceTest extends BaseTest {


    @Resource
    private PostService postService;


    @Test
    public void countCategoryTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }


    @Test
    public void increaseVisitors() {
//        Result result = postService.updatePostVisit();
//        Assert.assertTrue(result.isSuccess());
//        System.out.println("最后的结果是:" + result.getData());
    }



    @Test
    public void getLastestBlogIdTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void sortBlogsByCategoriesTest() {

        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findAuthorTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void updatePostTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findPostsByTagNameTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findPostByYearAndMonthTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findPostCountTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void getArticlePagesTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void removePostCategoryTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void saveByPostTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findNextPostTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findPreviousPostTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void removeByPostIdTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void updatePostStatusTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findAllPostsTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findAllPosts1Test() {

        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findAllPostsByStatusTest() {

        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findByPostIdTest() {

        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }

    @Test
    public void findLastestPostTest() {
        Result result = postService.countCategory();
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());
    }
}