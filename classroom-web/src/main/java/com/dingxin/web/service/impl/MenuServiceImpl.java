package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.MenuMapper;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 菜单管理 服务接口实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> like(Menu data) {
        LambdaQueryWrapper<Menu> query = Wrappers.<Menu>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        Menu::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getParentId()),
                        Menu::getParentId,
                        data.getParentId())
                .like(
                        Objects.nonNull(data.getName()),
                        Menu::getName,
                        data.getName())
                .like(
                        Objects.nonNull(data.getUrl()),
                        Menu::getUrl,
                        data.getUrl())
                .like(
                        Objects.nonNull(data.getPerms()),
                        Menu::getPerms,
                        data.getPerms())
                .like(
                        Objects.nonNull(data.getType()),
                        Menu::getType,
                        data.getType())
                .like(
                        Objects.nonNull(data.getIcon()),
                        Menu::getIcon,
                        data.getIcon())
                .like(
                        Objects.nonNull(data.getOrderNum()),
                        Menu::getOrderNum,
                        data.getOrderNum());
        return menuMapper.selectList(query);


    }

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<Menu> queryPage(CommQueryListRequest query) {
        LambdaQueryWrapper<Menu> qw = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(query.getQueryStr())) {
            qw.select(Menu::getName, Menu::getOrderNum, Menu::getCheck, Menu::getIcon, Menu::getId
                    , Menu::getParentId, Menu::getType, Menu::getUrl).eq(Menu::getName, query.getQueryStr())
                    .eq(Menu::getDelFlag, CommonConstant.DEL_FLAG);
        }
        Page<Menu> page = new Page(query.getCurrentPage(), query.getPageSize());
        return page(page, qw);
    }

    /**
     * 获取单个菜单
     *
     * @param idRequest
     * @return
     */
    @Override
    public Menu getOneSelf(IdRequest idRequest) {
        LambdaQueryWrapper<Menu> qw = Wrappers.lambdaQuery();
        qw.eq(Menu::getId, idRequest.getId()).eq(Menu::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * 保存或者修改
     *
     * @param convent
     * @return
     */
    @Override
    public boolean saveSelf(Menu convent) {
        LambdaQueryWrapper<Menu> qw = Wrappers.lambdaQuery();
        qw.eq(Menu::getName, convent.getName()).eq(Menu::getDelFlag, CommonConstant.DEL_FLAG)
                .eq(Menu::getUrl, convent.getUrl());
        int count = count(qw);
        if (count > 1) {
            throw new BusinessException(ExceptionEnum.DUPLICATE_DATA);
        }
        return saveOrUpdate(convent);

    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(IdRequest id) {
        LambdaUpdateWrapper<Menu> qw = Wrappers.lambdaUpdate();
        qw.set(Menu::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), Menu::getId, id.getId());
        return update(qw);
    }

    /**
     * 批量删除菜单
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<Menu> qw = Wrappers.lambdaUpdate();
        qw.set(Menu::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(Menu::getId, list);
        return update(qw);
    }

}
