package com.youyue.manage_cms.dao;

import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
