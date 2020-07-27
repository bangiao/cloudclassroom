package com.dingxin.web.service;
import com.dingxin.pojo.po.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 菜单管理 服务接口
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> like(Menu data);

}
