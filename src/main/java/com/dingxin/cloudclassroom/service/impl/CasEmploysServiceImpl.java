package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.CasEmploys;
import com.dingxin.cloudclassroom.mapper.CasEmploysMapper;
import com.dingxin.cloudclassroom.service.ICasEmploysService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务接口实现类
 */
@Service
@Transactional
public class CasEmploysServiceImpl extends ServiceImpl<CasEmploysMapper, CasEmploys> implements ICasEmploysService {

}
