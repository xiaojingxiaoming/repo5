package com.youyue.framework.domain.cms.request;

import lombok.Data;

/**
 * 这是一个封装请求参数的模型
 */
@Data
public class QueryPageRequest {
    //接收页面查询的查询条件
    //站点id
    private String siteId;

    //页面id
    private String pageId;

    //页面名称
    private String pageName;

    //别名
    private String pageAliase;

    //模板id
    private String templateId;
}
