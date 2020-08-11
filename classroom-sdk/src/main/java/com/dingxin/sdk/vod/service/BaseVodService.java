package com.dingxin.sdk.vod.service;

import com.dingxin.sdk.vod.config.VodProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author changxin.yuan
 * @date 2020/8/11 23:22
 */
public class BaseVodService {

    @Autowired
    private VodProperties vodProperties;


    public VodClient initClient(){
        Credential cred = new Credential(vodProperties.getSecretId(),vodProperties.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(vodProperties.getEndpoint());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        return new VodClient(cred, vodProperties.getRegion(), clientProfile);
    }

}
