package com.dingxin.sdk.vod.service;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author changxin.yuan
 * @date 2020/8/11 22:49
 */
@Slf4j
@Component
public class VodControlService extends BaseVodService {


    public DeleteMediaResponse delMedia(String fileId){
        try{
            VodClient client = initClient();
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(fileId);
            DeleteMediaResponse resp = client.DeleteMedia(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            log.error("删除资源失败",e);
            throw new BusinessException(ExceptionEnum.VOD_DELETE_ERROR);
        }
    }


}
