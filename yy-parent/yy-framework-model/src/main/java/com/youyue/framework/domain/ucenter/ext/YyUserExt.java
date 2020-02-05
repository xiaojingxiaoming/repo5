package com.youyue.framework.domain.ucenter.ext;

import com.youyue.framework.domain.ucenter.YyMenu;
import com.youyue.framework.domain.ucenter.YyUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class YyUserExt extends YyUser {

    //权限信息
    private List<YyMenu> permissions;

    //企业信息
    private String companyId;
}
