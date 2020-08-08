package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.po.Student;
import com.dingxin.dao.mapper.StudentMapper;
import com.dingxin.pojo.request.StudentStudyStudentListRequest;
import com.dingxin.web.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 学生信息表 服务接口实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> like(Student data) {
        LambdaQueryWrapper<Student> query = Wrappers.<Student>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Student::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getXsbh()),
                    Student::getXsbh,
                    data.getXsbh())
                .like(
                    Objects.nonNull(data.getXh()),
                    Student::getXh,
                    data.getXh())
                .like(
                    Objects.nonNull(data.getXm()),
                    Student::getXm,
                    data.getXm())
                .like(
                    Objects.nonNull(data.getXbmc()),
                    Student::getXbmc,
                    data.getXbmc())
                .like(
                    Objects.nonNull(data.getXslbdm()),
                    Student::getXslbdm,
                    data.getXslbdm())
                .like(
                    Objects.nonNull(data.getXslbmc()),
                    Student::getXslbmc,
                    data.getXslbmc())
                .like(
                    Objects.nonNull(data.getSydm()),
                    Student::getSydm,
                    data.getSydm())
                .like(
                    Objects.nonNull(data.getSymc()),
                    Student::getSymc,
                    data.getSymc())
                .like(
                    Objects.nonNull(data.getYxdm()),
                    Student::getYxdm,
                    data.getYxdm())
                .like(
                    Objects.nonNull(data.getYxmc()),
                    Student::getYxmc,
                    data.getYxmc())
                .like(
                    Objects.nonNull(data.getZydldm()),
                    Student::getZydldm,
                    data.getZydldm())
                .like(
                    Objects.nonNull(data.getZydlmc()),
                    Student::getZydlmc,
                    data.getZydlmc())
                .like(
                    Objects.nonNull(data.getZydm()),
                    Student::getZydm,
                    data.getZydm())
                .like(
                    Objects.nonNull(data.getZymc()),
                    Student::getZymc,
                    data.getZymc())
                .like(
                    Objects.nonNull(data.getBjdm()),
                    Student::getBjdm,
                    data.getBjdm())
                .like(
                    Objects.nonNull(data.getBjmc()),
                    Student::getBjmc,
                    data.getBjmc())
                .like(
                    Objects.nonNull(data.getFdyzgh()),
                    Student::getFdyzgh,
                    data.getFdyzgh())
                .like(
                    Objects.nonNull(data.getFdyxm()),
                    Student::getFdyxm,
                    data.getFdyxm())
                .like(
                    Objects.nonNull(data.getSjh()),
                    Student::getSjh,
                    data.getSjh())
                .like(
                    Objects.nonNull(data.getDzxx()),
                    Student::getDzxx,
                    data.getDzxx())
                .like(
                    Objects.nonNull(data.getLxdh()),
                    Student::getLxdh,
                    data.getLxdh())
                .like(
                    Objects.nonNull(data.getYjbyrq()),
                    Student::getYjbyrq,
                    data.getYjbyrq())
                .like(
                    Objects.nonNull(data.getRxny()),
                    Student::getRxny,
                    data.getRxny())
                .like(
                    Objects.nonNull(data.getSjbyrq()),
                    Student::getSjbyrq,
                    data.getSjbyrq())
                .like(
                    Objects.nonNull(data.getTbrq()),
                    Student::getTbrq,
                    data.getTbrq())
                .like(
                    Objects.nonNull(data.getTblx()),
                    Student::getTblx,
                    data.getTblx())
                .like(
                    Objects.nonNull(data.getCzzxm()),
                    Student::getCzzxm,
                    data.getCzzxm())
                .like(
                    Objects.nonNull(data.getXznj()),
                    Student::getXznj,
                    data.getXznj())
                .like(
                    Objects.nonNull(data.getDsxm()),
                    Student::getDsxm,
                    data.getDsxm())
                .like(
                    Objects.nonNull(data.getDszgh()),
                    Student::getDszgh,
                    data.getDszgh())
                .like(
                    Objects.nonNull(data.getHkszd()),
                    Student::getHkszd,
                    data.getHkszd())
                .like(
                    Objects.nonNull(data.getZp()),
                    Student::getZp,
                    data.getZp())
                .like(
                    Objects.nonNull(data.getXjztdm()),
                    Student::getXjztdm,
                    data.getXjztdm())
                .like(
                    Objects.nonNull(data.getXslx()),
                    Student::getXslx,
                    data.getXslx())
;
        return studentMapper.selectList(query);


    }

    @Override
    public IPage queryPageList(StudentStudyStudentListRequest query) {
        //查询列表数据
        Page<Student> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Student> wrapper = Wrappers.lambdaQuery();
        String queryStr = query.getQueryStr();
        if(StringUtils.isNotEmpty(queryStr)){
            wrapper.and(Wrapper -> Wrapper.like(Student::getXm,queryStr));
        }
        IPage pageList = this.page(page,wrapper);
        return pageList;
    }
}
