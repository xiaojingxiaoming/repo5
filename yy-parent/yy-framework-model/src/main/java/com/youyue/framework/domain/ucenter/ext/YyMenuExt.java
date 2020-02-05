package com.youyue.framework.domain.ucenter.ext;

import com.youyue.framework.domain.course.ext.CategoryNode;
import com.youyue.framework.domain.ucenter.YyMenu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class YyMenuExt extends YyMenu {

    List<CategoryNode> children;
}
