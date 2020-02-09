package com.youyue.manage_cms.service;

import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.request.QueryPageRequest;
import com.youyue.framework.domain.cms.response.CmsPageResult;
import com.youyue.framework.model.response.CommonCode;
import com.youyue.framework.model.response.QueryResponseResult;
import com.youyue.framework.model.response.QueryResult;
import com.youyue.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     *
     * @param page  页码  从1开始记录
     * @param size 每页记录数
     * @param queryPageRequest  查询条件
     * @return
     */
    //页面查询方法
    public QueryResponseResult findList( int page,int size, QueryPageRequest queryPageRequest) {
       if (queryPageRequest==null){
           queryPageRequest=new QueryPageRequest();
       }
        //自定义条件查询
        //自定义匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //构建条件值对象
        CmsPage cmsPage=new CmsPage();
        //设置条件值  站点id
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){//条件值不为空 才设置到条件中
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //设置模板id
        if(StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){//条件值不为空 才设置到条件值对象中
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){//条件值不为空 才设置到条件值对象中
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //定义条件对象
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //分页参数
        if(page<=0){
            page=1;
        }
        page=page-1;
        if(size<=0){
            size=10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult queryResult=new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult=new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    //新增页面
    public CmsPageResult add(CmsPage cmsPage){
       //校验页面名称  站点id 页面webpath的唯一性
        //根据页面名称  站点id 页面webpath去cmsPage集合中查询 查到说明已经存在 查不到在继续添加

        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (cmsPage1==null){//没有查询到 可以完成添加
            //调用dao的新增页面
            cmsPage.setPageId(null);//这样设置 MongoDB肯定会帮我们创建主键
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }
        //新增失败
        return new CmsPageResult(CommonCode.FAIL,null);
    }
}
