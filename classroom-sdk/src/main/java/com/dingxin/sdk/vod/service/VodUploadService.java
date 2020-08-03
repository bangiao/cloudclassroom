package com.dingxin.sdk.vod.service;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.sdk.exception.VodUploadException;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * vod服务端本地上传服务
 *
 * @author changxin.yuan
 * @date 2020/7/30 14:57
 * @see {https://cloud.tencent.com/document/product/266/10276}
 */
@Slf4j
@Service
public class VodUploadService {

    @Autowired
    private VodUploadClient vodUploadClient;

    /**
     * 本地上传
     *
     * @param filePath 文件路径
     */
    public void localUpload(String filePath) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload error", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传携带封面
     *
     * @param filePath
     * @param cover
     */
    public void localUploadWithCover(String filePath, String cover) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        request.setCoverFilePath(cover);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 上传执行指定任务流
     *
     * @param filePath
     * @param procedureName
     * @see {https://cloud.tencent.com/document/product/266/33819}
     */
    public void localUploadProcedure(String filePath, String procedureName) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        request.setProcedure(procedureName);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传指定子应用id
     *
     * @param filePath
     * @param subAppId
     * @see {https://cloud.tencent.com/document/product/266/14574}
     */
    public void localUploadSubId(String filePath, Long subAppId) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        request.setSubAppId(subAppId);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传指定存储域
     *
     * @param filePath
     * @param storageRegion
     * @see {https://cloud.tencent.com/document/product/266/14059}
     */
    public void localUploadStorageRegion(String filePath, String storageRegion) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        request.setStorageRegion(storageRegion);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传指定分片数,针对大文件
     *
     * @param filePath
     * @param concurrentUploadNumber
     */
    public void localUploadCum(String filePath, Integer concurrentUploadNumber) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        request.setConcurrentUploadNumber(concurrentUploadNumber);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传使用临时证书
     *
     * @param filePath
     * @param tmpSecretId
     * @param tmpSecretKey
     * @param token
     */
    public void localUploadTmp(String filePath, String tmpSecretId, String tmpSecretKey, String token) {
        VodUploadClient client = new VodUploadClient(tmpSecretId, tmpSecretKey, token);
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        try {
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传，通过代理
     *
     * @param filePath
     * @param proxy
     * @param port
     */
    public void localUploadProxy(String filePath, String proxy, Integer port) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setProxyHost(proxy);
        httpProfile.setProxyPort(port);
        vodUploadClient.setHttpProfile(httpProfile);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

    /**
     * 本地上传 自适应
     *
     * @param filePath
     */
    public void localUploadSelfAdap(String filePath) {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(filePath);
        try {
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw new VodUploadException(ExceptionEnum.VOD_UPLOAD_ERROR);
        }
    }

}
