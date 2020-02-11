package com.youyue.manage_cms.controller;

import com.youyue.api.cms.CmsConfigControllerApi;
import com.youyue.framework.domain.cms.CmsConfig;
import com.youyue.manage_cms.dao.CmsConfigRepository;
import com.youyue.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi{

    @Autowired
    PageService pageService;
    @Override
    @GetMapping("/getModel/{id}")
    public CmsConfig getModel(@PathVariable("id") String id) {
        return pageService.getConfigById(id);
    }
}
