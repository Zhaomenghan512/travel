package com.travel.admin.dto;

import com.travel.common.domain.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 赵梦涵
 * @datetime: 2020/6/18 17:53
 * @description: TODO
 */
@Data
public class LoginUser implements Serializable {
    private String name;
    private String avatar;
    private String nickName;
    private List<SysMenu> menuList = new ArrayList();
}
