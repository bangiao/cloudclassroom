package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.CasDepts;
import com.dingxin.cloudclassroom.mapper.CasDeptsMapper;
import com.dingxin.cloudclassroom.service.ICasDeptsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务接口实现类
 */
@Service
@Transactional
public class CasDeptsServiceImpl extends ServiceImpl<CasDeptsMapper, CasDepts> implements ICasDeptsService {

}
