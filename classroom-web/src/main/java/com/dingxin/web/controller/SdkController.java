package com.dingxin.web.controller;

import com.dingxin.common.annotation.ManTag;
import com.dingxin.sdk.other.Signature;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author changxin.yuan
 * @date 2020/7/31 18:07
 */
@Slf4j
@ManTag
@RestController
@Api(tags = "sdk接口")
@RequestMapping("/sdk")
public class SdkController {



    @PostMapping("/vodSignature")
    public String vodSignature(){
        Signature sign = new Signature();
        sign.setSecretId("AKIDc091WleNozqLmJ6tqWv3ycLn35rIjIHK");
        sign.setSecretKey("vQfdMnyLm372avnPyJTL5xciV4PL0ovm");
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        //一小时
        sign.setSignValidDuration(3600);
        String signature = "";
        try {
            signature = sign.getUploadSignature();
            log.info("signature :{}",signature);
        } catch (Exception e) {
            log.error("获取签名失败",e);
        }
        return signature;
    }


}
