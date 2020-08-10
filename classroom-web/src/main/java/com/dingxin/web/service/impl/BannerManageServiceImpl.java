package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.BannerManageMapper;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.pojo.request.BannerRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IBannerManageService;
import com.dingxin.web.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *  首页banner服务接口实现类
 */
@Service
public class BannerManageServiceImpl extends ServiceImpl<BannerManageMapper, BannerManage> implements IBannerManageService {

    @Autowired
    private BannerManageMapper bannerManageMapper;


    @Override
    public List<BannerManage> like(BannerManage data) {
        LambdaQueryWrapper<BannerManage> query = Wrappers.<BannerManage>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    BannerManage::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getTitle()),
                    BannerManage::getTitle,
                    data.getTitle())
                .like(
                    Objects.nonNull(data.getContent()),
                    BannerManage::getContent,
                    data.getContent())
                .like(
                    Objects.nonNull(data.getFileName()),
                    BannerManage::getFileName,
                    data.getFileName())
                .like(
                    Objects.nonNull(data.getFileUrl()),
                    BannerManage::getFileUrl,
                    data.getFileUrl())
                .like(
                    Objects.nonNull(data.getUseType()),
                    BannerManage::getUseType,
                    data.getUseType())
                .like(
                    Objects.nonNull(data.getCreatePerson()),
                    BannerManage::getCreatePerson,
                    data.getCreatePerson())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    BannerManage::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    BannerManage::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    BannerManage::getDelFlag,
                    data.getDelFlag())
                .like(
                    Objects.nonNull(data.getDisable()),
                    BannerManage::getDisable,
                    data.getDisable())
;
        return bannerManageMapper.selectList(query);


    }

    @Override
    public IPage queryPageList(BaseQuery<BannerManage> query) {
        Page<BannerManage> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<BannerManage> qw = Wrappers.lambdaQuery();
        qw.eq(BannerManage::getDelFlag, CommonConstant.DEL_FLAG);
        qw.orderByAsc(BannerManage::getDisable);
        IPage pageList = this.page(page,qw);
        return pageList;
    }

    @Override
    public boolean deleteBannerManage(IdRequest id) {
        LambdaUpdateWrapper<BannerManage> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(BannerManage::getId,id.getId());
        wrapper.set(BannerManage::getDelFlag, CommonConstant.DEL_FLAG_TRUE);
        boolean retFlag = this.update(wrapper);
        return retFlag;
    }

    @Override
    public boolean enableStatus(BannerRequest bannerRequest) {
        Integer disable = bannerRequest.getDisable();
        //如果是禁用状态的话  就直接改状态
        if(disable == CommonConstant.DISABLE_TRUE){
            LambdaUpdateWrapper<BannerManage> wrapper = Wrappers.lambdaUpdate();
            wrapper.eq(BannerManage::getId,bannerRequest.getId());
            wrapper.set(BannerManage::getDisable,bannerRequest.getDisable());
            return this.update(wrapper);
        } else {
            LambdaQueryWrapper<BannerManage> query = Wrappers.lambdaQuery();
            query.eq(BannerManage::getDisable,CommonConstant.DISABLE_FALSE);
            query.eq(BannerManage::getDelFlag,CommonConstant.DEL_FLAG);
            int count = this.count(query);
            if(count >= 3){
                throw  new BusinessException(ExceptionEnum.BANNER_MSG);
            }else {
                LambdaUpdateWrapper<BannerManage> wrapper = Wrappers.lambdaUpdate();
                wrapper.eq(BannerManage::getId,bannerRequest.getId());
                wrapper.set(BannerManage::getDisable,bannerRequest.getDisable());
                return this.update(wrapper);
            }
        }
    }

    @Override
    public boolean saveBannerManage(BannerManage bannerManage) {
        List<BannerManage> list = this.list();
        Optional<BannerManage> optional = list.stream().max(Comparator.comparingInt(BannerManage::getSortId));
        if(optional.isPresent()){
            BannerManage manage = optional.get();
            bannerManage.setSortId(manage.getSortId()+1);
        }else {
            bannerManage.setSortId(1);
        }
        bannerManage.setCreatePerson(ShiroUtils.getUserName());
        return this.save(bannerManage);
    }

    @Override
    public boolean updateBannerManage(BannerManage bannerManage) {
        String fileUrl = bannerManage.getFileUrl();
        LambdaUpdateWrapper<BannerManage> qw = Wrappers.lambdaUpdate();
        if(StringUtils.isEmpty(fileUrl)){
            qw.set(BannerManage::getFileUrl,null)
                    .set(BannerManage::getFileName,null)
                    .set(BannerManage::getTitle,bannerManage.getTitle())
                    .set(BannerManage::getContent,bannerManage.getContent())
                    .set(BannerManage::getUseType,bannerManage.getUseType());
            qw.eq(BannerManage::getId,bannerManage.getId());
            return this.update(qw);
        }else {
            return this.saveOrUpdate(bannerManage);
        }
    }
}
