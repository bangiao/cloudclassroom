package com.dingxin.sdk.vod.service;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.dto.VodSearchDTO;
import com.dingxin.sdk.vod.modal.VideoMetaData;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.SearchMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.SearchMediaResponse;
import com.tencentcloudapi.vod.v20180717.models.SortBy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author changxin.yuan
 * @date 2020/8/10 22:13
 */
@Slf4j
@Component
public class VodSearchService extends BaseVodService {


    public void serachVod(VodSearchDTO dto){
        //默认查视频
        serachVod(dto,"Video");
    }


    public void serachVod(VodSearchDTO dto,String... cates){
        SearchMediaRequest request = new SearchMediaRequest();
        request.setOffset(dto.offset().longValue());
        request.setLimit(dto.getPageSize().longValue());
        request.setCategories(cates);
        SortBy sortBy = new SortBy();
        sortBy.setField("CreateTime");
        sortBy.setOrder("Desc");
        request.setSort(sortBy);
        String text = dto.getText();
        if(StringUtils.isNotEmpty(text)){
            request.setText(text);
        }
        SearchMediaResponse response = request(request);
        dto.setTotal(response.getTotalCount());
        List<VideoMetaData> list = Stream.of(response.getMediaInfoSet())
                .map(VideoMetaData::of).collect(Collectors.toList());
        dto.setData(list);
    }

    private SearchMediaResponse request(SearchMediaRequest request){
        try{
            VodClient client = initClient();
            SearchMediaResponse resp = client.SearchMedia(request);
            return resp;
        } catch (TencentCloudSDKException e) {
            log.error("vod-search-fail",e);
            throw new BusinessException(ExceptionEnum.VOD_SEARCH_ERROR);
        }
    }



}
