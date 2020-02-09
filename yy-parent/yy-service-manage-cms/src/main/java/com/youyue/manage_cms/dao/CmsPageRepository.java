package com.youyue.manage_cms.dao;

import com.youyue.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 页面查询dao
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String>{

    //根据页面名称查询
    CmsPage findByPageName(String pageName);

    //现在dao中我们应该写一个保存cmsPage的方法  save已经有了

    //根据页面名称  站点id 页面webpath查询
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);
}
