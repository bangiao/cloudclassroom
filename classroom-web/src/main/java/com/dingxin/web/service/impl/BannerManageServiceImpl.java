package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.dao.mapper.BannerManageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IBannerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

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
                    Objects.nonNull(data.getIsValid()),
                    BannerManage::getIsValid,
                    data.getIsValid())
;
        return bannerManageMapper.selectList(query);


    }

    @Override
    public IPage queryPageList(BaseQuery<BannerManage> query) {
        Page<BannerManage> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<BannerManage> qw = Wrappers.lambdaQuery();
        qw.eq(BannerManage::getDelFlag, CommonConstant.DEL_FLAG);
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
}
