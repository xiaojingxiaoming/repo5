package com.youyue.manage_cms.dao;

import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;

/**
 * @Author  tianjing
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;
   //添加
    @Test
    public void testAdd(){
        CmsPage cmsPage=new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams=new ArrayList<CmsPageParam>();
        CmsPageParam cmsPageParam=new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }
    //删除
    @Test
    public void testDelete(){
        cmsPageRepository.deleteById("");
    }
   //查询
    @Test
    public void testFindAll(){
        List<CmsPage> list = cmsPageRepository.findAll();
        System.out.println(list);
    }
    //分页查询
    @Test
    public void testFindPage(){
        //分页参数
        int page=0;
        int size=10;
        Pageable pageable= PageRequest.of(page, size);
        Page<CmsPage> pages = cmsPageRepository.findAll(pageable);
        System.out.println(pages);
    }

    @Test
    public void testFindPage2(){
        //分页参数
        int page=0;//从0页开始
        int size=5;//每页显示5条
        Pageable pageable= PageRequest.of(page, size);
        CmsPage cmsPage=new CmsPage();
        //设置站点
       // cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        //设置模板
       // cmsPage.setTemplateId("5a925be7b00ffc4b3c1578b5");
        //设置页面别名
        cmsPage.setPageAliase("轮播");
        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
        .withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //定义Example
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);//构建条件  站点id 模板 别名
        Page<CmsPage> pages = cmsPageRepository.findAll(example,pageable);
        System.out.println(pages.getContent());
    }
    //修改

    /**
     * findById
     *
     * findByOne
     *
     * 延迟加载懒加载
     */
    @Test
    public void testUpdate(){
        //查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5a92141cb00ffc5a448ff1a0");
       if(optional.isPresent()){//表示判断内容是否为空
           CmsPage cmsPage = optional.get();  //不为空走if
           //设置修改的值
           cmsPage.setPageAliase("test01");
           //完成修改
           cmsPageRepository.save(cmsPage);
       }
    }
    //根据名称查询
    @Test
    public void testFindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("10101.html");
        System.out.println(cmsPage);
    }
}
