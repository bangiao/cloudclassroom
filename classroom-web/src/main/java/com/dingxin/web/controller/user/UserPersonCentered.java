package com.dingxin.web.controller.user;

import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.enums.RoleEnum;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.PersonInfoRequest;
import com.dingxin.pojo.request.WidRequest;
import com.dingxin.pojo.vo.ProjectManagementVo;
import com.dingxin.web.service.IClassEvaluateService;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.dingxin.web.service.ITeachersService;
import com.dingxin.web.shiro.ShiroUtils;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ya.nie
 * @date 2020/8/16 18:37
 * @Description
 */
@UserTag
@RestController
@RequestMapping("/User/Person")
@Api(tags = "个人中心接口")
public class UserPersonCentered {

    @Autowired
    private ITeachersService teachersService;
    @Autowired
    private IStduentClassSeeRecordService stduentClassSeeRecordService;
    /**
     * pc个人信息
     */
    @PostMapping("/personCenter")
    @ApiOperation(value = "个人信息")
    public BaseResult personCenter(){
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        Object o = null;
        String type = null;
        RoleEnum userType = ShiroUtils.getUserType();
        switch (userType){
            case TEACHER:
                o = teachersService.techerInformation(ShiroUtils.getUserId());
                type="teacher";
                break;
            case NORMAL_USER:
                o= stduentClassSeeRecordService.getOneSelf(new WidRequest(ShiroUtils.getUserId()));
                type="user";
                break;
        };
        objectObjectHashMap.put("introduction",teachersService.queryInfo());
        objectObjectHashMap.put("data",o);
        objectObjectHashMap.put("type",type);
        return BaseResult.success(objectObjectHashMap);
    }

    /**
     * pc个人信息
     */
    @PostMapping("/updatePerson")
    @ApiOperation(value = "个人信息")
    public BaseResult updatePerson(@RequestBody PersonInfoRequest personInfoRequest){
        return BaseResult.success(teachersService.UpdatePerson(personInfoRequest));
    }

}
