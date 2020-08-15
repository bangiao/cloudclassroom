package com.dingxin.common.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author changxin.yuan
 * @date 2020/7/18 17:51
 */
public class ExcelUtils {

    /**
     * 导出为xlsx格式的excel文件(数据)
     */
    public static <T extends BaseRowModel> void exportXlsx(
            HttpServletResponse res, @Nonnull String filenameWithoutExtension, Class<T> clazz, List<T> data) throws IOException {
        setDownloadHeader(res, filenameWithoutExtension + ".xlsx");
        ExcelWriter writer = new ExcelWriter(res.getOutputStream(), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setSheetName("sheet1");
        writer.write(data, sheet);
        writer.finish();
    }

    /**
     * 导出模板
     *
     * @param res
     * @param filenameWithoutExtension
     * @param titles
     * @throws IOException
     */
    public static void exportXlsx(
            HttpServletResponse res, @Nonnull String filenameWithoutExtension, List<List<String>> titles) throws IOException {
        setDownloadHeader(res, filenameWithoutExtension + ".xlsx");
        ExcelWriter writer = new ExcelWriter(res.getOutputStream(), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");
        Table table = new Table(1);
        table.setHead(titles);
        List<List<String>> dataList = Lists.newArrayList();
        writer.write0(dataList, sheet, table);
        writer.finish();
    }

    /**
     * 设置Content-Type为文件流
     *
     * @param filenameWithExtension 支持中文和特殊 字符
     */
    private static void setDownloadHeader(HttpServletResponse res, String filenameWithExtension) {
        res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        try {
            res.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(filenameWithExtension, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
