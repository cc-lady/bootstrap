package com.cc.bootstrap.intl.demo.file.poi.load;

import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.common.util.StringUtils;
import com.cc.bootstrap.page.dao.UserMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: poi文件导出服务类
 * @author: ChenChen
 * @date: 2022/4/19 9:29
 */
@Component
public class FileLoadService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileLoadService.class);

    private static short COLUMN_WIDTH = (short) 5000;//列宽
    private static short ROW_HEIGHT_FIRST = (short) 400;//行高-第一行
    private static short ROW_HEIGHT_OTHER = (short) 320;//行高-第一行

    private static String[] HEAD_FIELDS = {"序号", "姓名", "手机号", "地址", "email", "用户权限", "备注"};
    private static String[] HEAD_FIELD_NAME = {"serialNo", "userName", "mobilePhone", "address", "email", "role", "note"};


    @Value("${fileupload.path}")
    private String fileUploadPath;

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description poi导出实体为文件
     * @param userId
     * @param response
     * @author ChenChen
     * @return void
     * @date 2022/4/19 14:59
     */
    public void load(String userId, HttpServletResponse response) throws IOException {
        // 目录获取失败，不解析
        String dirPath = fileUploadPath + File.separator + userId;
        if (!Paths.get(dirPath).toFile().isDirectory()) {
            throw new UnsupportedOperationException("获取解析文件目录失败，请先上传文件！目录 【" + dirPath + "】");
        }

        // 导出文件名
        String fileName = "load-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";

        // 获取导出数据
        User user = userMapper.selectById(userId);
        if(null == user) {
            LOGGER.error("查不到用户信息！userId 【{}】", userId);
            throw new UnsupportedOperationException("查不到用户编码对应的用户信息！");
        }

        // 将数据写入excel，并返回
        // 设置下载返回头
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("FileName", URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Access-Control-Expose-Headers", "FileName");
        response.setContentType("application/octet-stream");

        Workbook workbook = this.generateWorkBook(userId, user);
        workbook.write(response.getOutputStream());

        // 刷新返回流
        workbook.close();
        response.flushBuffer();
    }

    /**
     * @Description 根据数据生成excel
     * @param userId
     * @param user
     * @author ChenChen
     * @return org.apache.poi.ss.usermodel.Workbook
     * @date 2022/4/19 15:10
     */
    private Workbook generateWorkBook(String userId, User user) {
        // 生成表格
        Workbook workbook = new HSSFWorkbook();
        // 生成Sheet
        Sheet sheet = workbook.createSheet("用户信息");

        // 表头设置
        this.generateFirstRowContent(workbook, sheet, 0);
        // 表体设置
        this.generateBodyContent(workbook, sheet, user);

        LOGGER.info("userId [{}] 成功生成excel格式！");
        return workbook;
    }

    /**
     * @Description 表体设置
     * @param workbook
     * @param sheet
     * @param user
     * @author ChenChen
     * @return void
     * @date 2022/4/19 15:30
     */
    private void generateBodyContent(Workbook workbook, Sheet sheet, User user) {
        // 根据内容生成表体
        Row row = null;
        Cell cell = null;
        int index = 1;//记录行号

        // 请求体样式
        CellStyle cellStyle_body = this.generateBodyRowCellStyle(workbook, false);
        row = this.generateRow(index++, ROW_HEIGHT_OTHER, sheet);
        // 生成本行各单元格内容
        Method method = null;
        Object value = null;
        for(int i = 0; i < HEAD_FIELDS.length; i++) {
            cell = row.createCell(i);
            // 第一行数据为行号
            if(i == 0) {
                value = i + 1;
            } else {
                //反射获取User内容
                try {
                    method = User.class.getMethod("get" + StringUtils.upperFirstChar(HEAD_FIELD_NAME[i]));
                } catch (Exception e) {
                    LOGGER.error("get User Method set[{}] error!", StringUtils.upperFirstChar(HEAD_FIELD_NAME[i]), e);
                    continue;
                }
                try {
                    value = method.invoke(user);
                } catch (Exception e) {
                    LOGGER.error("get User [{}] error!", HEAD_FIELD_NAME[i], e);
                    continue;
                }
            }
            cell.setCellValue(value == null ? null : value.toString());
            cell.setCellStyle(cellStyle_body);
        }
    }

    /**
     * @Description 请求体CellStyle
     * @param workbook
     * @param isBold
     * @author ChenChen
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @date 2022/4/19 15:35
     */
    private CellStyle generateBodyRowCellStyle(Workbook workbook, boolean isBold) {
        // cell style
        CellStyle cellStyle = workbook.createCellStyle();
        // font
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());//黑色
        font.setBold(isBold);//加粗
        font.setFontHeightInPoints((short)10);//字体大小
        font.setFontName("宋体");
        // cell style 表头风格
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平中间对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直中间对齐
        // 带边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * @Description 设置第一行
     * @param workbook
     * @param sheet
     * @param rowNum
     * @author ChenChen
     * @return void
     * @date 2022/4/19 15:13
     */
    private void generateFirstRowContent(Workbook workbook, Sheet sheet, int rowNum) {
        // CellStyle
        CellStyle cellStyle = this.generateFirstRowCellStyle(workbook);
        // 每个cell
        Cell cell = null;
        // 表头属性
        Row row_first = this.generateRow(rowNum, ROW_HEIGHT_FIRST, sheet);//第一行
        for(int i = 0; i < HEAD_FIELDS.length; i ++) {
            // 设置表头属性
            cell = row_first.createCell(i);
            cell.setCellValue(HEAD_FIELDS[i]);
            cell.setCellStyle(cellStyle);
            // 顺便调整宽度
            sheet.setColumnWidth(i, COLUMN_WIDTH);
        }
    }

    /**
     * @Description 生成行
     * @param rowNum
     * @param rowHeightFirst
     * @param sheet
     * @author ChenChen
     * @return org.apache.poi.ss.usermodel.Row
     * @date 2022/4/19 15:29
     */
    private Row generateRow(int rowNum, short rowHeightFirst, Sheet sheet) {
        Row row = sheet.createRow(rowNum);
        row.setHeight(rowHeightFirst);
        return row;
    }

    /**
     * @Description 第一行样式
     * @param workbook
     * @author ChenChen
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @date 2022/4/19 15:23
     */
    private CellStyle generateFirstRowCellStyle(Workbook workbook) {
        // cell style
        CellStyle cellStyle = workbook.createCellStyle();
        // font
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());//黑色
        font.setBold(true);//加粗
        font.setFontHeightInPoints((short)12);//字体大小
        font.setFontName("宋体");
        // cell style 表头风格
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平中间对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直中间对齐
        // 带边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }
}
