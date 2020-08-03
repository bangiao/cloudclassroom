package com.dingxin.sdk.vod.config;

import com.qcloud.vod.VodUploadClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changxin.yuan
 * @date 2020/7/30 14:53
 */
@Configuration
@EnableConfigurationProperties(value = {VodProperties.class})
@ConditionalOnProperty(prefix = "dsk.vod", name = "enabled", matchIfMissing = true)
public class VodConfig {


    @Bean
    public VodUploadClient vodUploadClient(VodProperties vodProperties){
        return new VodUploadClient(vodProperties.getSecretId(),vodProperties.getSecretKey());
    }


}
