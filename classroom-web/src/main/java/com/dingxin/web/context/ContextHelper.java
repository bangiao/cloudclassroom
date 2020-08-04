package com.dingxin.web.context;

import com.dingxin.common.utils.BeanUtils;

/**
 * 这个放到拦截器里去
 *
 * @author changxin.yuan
 * @date 2020/8/4 10:35
 */
public class ContextHelper {

    /**
     * 组装context
     */
    public void assemble(){
        //init
        CcrContext context = CcrContext.getContext();
        context.setCurriculumStrategySupplier((userInfo)->{
            //这里判断权限返回不同的 ICurriculumStrategy  从BeanUtils里获取
            return BeanUtils.getBean("xxx");
        });
    }

}
