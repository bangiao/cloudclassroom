package com.dingxin.web.context;

import com.dingxin.pojo.po.UserRole;
import com.dingxin.web.service.strategy.curriculum.ICurriculumStrategy;
import lombok.Data;

import java.util.Objects;

/**
 * @author changxin.yuan
 * @date 2020/8/4 10:08
 */
@Data
public class CcrContext {

    private static final ThreadLocal<CcrContext> holer = new ThreadLocal<>();

    /**
     * 课组策略-Supplier
     */
    private UnarySupplier<ICurriculumStrategy,UserRole> curriculumStrategySupplier;



    /**
     * 获取全局context
     * @return
     */
    public static CcrContext getContext(){
        CcrContext ccrContext = holer.get();
        if(Objects.isNull(ccrContext)){
            ccrContext = new CcrContext();
            holer.set(ccrContext);
        }
        return ccrContext;
    }


    public UserRole getUserRole(){
        //todo
        return new UserRole();
    }


    public ICurriculumStrategy getCurriculumStrategy(){
        UserRole userRole = getUserRole();
        return curriculumStrategySupplier.get(userRole);
    }


}
