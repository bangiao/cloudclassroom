package com.dingxin.web.service;
import com.dingxin.pojo.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 角色 服务接口
 */
public interface IRoleService extends IService<Role> {

    List<Role> like(Role data);

}
