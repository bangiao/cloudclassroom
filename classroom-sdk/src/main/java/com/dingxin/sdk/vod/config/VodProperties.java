package com.dingxin.sdk.vod.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author changxin.yuan
 * @date 2020/7/30 14:52
 */
@Data
@ConfigurationProperties(prefix = "sdk.vod")
public class VodProperties {

    private String secretId;

    private String secretKey;

}
