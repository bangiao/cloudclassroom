package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.MajorMapper;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Major;
import com.dingxin.pojo.request.StudentStudyStudentListRequest;
import com.dingxin.web.service.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 专业管理 服务接口实现类
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

    @Autowired
    private MajorMapper majorMapper;


    @Override
    public List<Major> like(Major data) {
        LambdaQueryWrapper<Major> query = Wrappers.<Major>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Major::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getMajorCode()),
                    Major::getMajorCode,
                    data.getMajorCode())
                .like(
                    Objects.nonNull(data.getMajorName()),
                    Major::getMajorName,
                    data.getMajorName())
                .like(
                    Objects.nonNull(data.getCollegeCode()),
                    Major::getCollegeCode,
                    data.getCollegeCode())
                .like(
                    Objects.nonNull(data.getCollegeName()),
                    Major::getCollegeName,
                    data.getCollegeName())
;
        return majorMapper.selectList(query);


    }

    @Override
    public IPage queryPageList(StudentStudyStudentListRequest query) {
        //查询列表数据
        Page<Major> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Major> wrapper = Wrappers.lambdaQuery();
        wrapper.like(Major::getMajorName,query.getQueryStr());
        IPage pageList = this.page(page,wrapper);
        return pageList;
    }
}
