package com.dingxin.web.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.vo.TreeVo;

import java.util.List;

/**
 *  服务接口
 */
public interface ICasDeptsService extends IService<CasDepts> {

    List<CasDepts> like(CasDepts data);

    BaseResult<Page<CasDepts>> queryPageList();
    /**
     *  根据人员部门编号获取机构数
     * @param departmentCodes 人员所属部门编号集合
     * @return
     */
    List<TreeVo> queryTree(List<String> departmentCodes);

    /**
     *  获取结构tree
     * @param depts 部门集合
     * @return
     */
    List<TreeVo> queryTreeByDepts(List<CasDepts> depts);

    /**
     * 将CasDepts 转换成treevo
     * @param depts
     * @return
     */
    List<TreeVo> transformation(List<CasDepts> depts);

}
