package com.cc.bootstrap.intl.demo.file.poi.parse;

import com.cc.bootstrap.common.schema.User;
import com.cc.bootstrap.common.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: poi文件解析服务类
 * @author: ChenChen
 * @date: 2022/4/19 9:29
 */
@Component
public class FileParseService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileParseService.class);

    // 字段属性 - serialNo为占位，无意义
    private static String[] HEAD_FIELDS = {"serialNo", "userName", "mobilePhone", "address", "email", "role", "note"};
    private static Class[] FIELD_TYPES = {Integer.class, String.class, String.class, String.class,
            String.class, Integer.class, String.class};

    @Value("${fileupload.path}")
    private String fileUploadPath;

    /**
     * @Description poi解析文件为对应实体数据
     * @param userId
     * @author ChenChen
     * @return java.util.List<com.cc.bootstrap.common.schema.User>
     * @date 2022/4/19 9:35
     */
    public List<User> parse(String userId) throws IOException {
        // 目录获取失败，不解析
        String dirPath = fileUploadPath + File.separator + userId;
        if (!Paths.get(dirPath).toFile().isDirectory()) {
            throw new UnsupportedOperationException("获取解析文件目录失败，请先上传文件！目录 【" + dirPath + "】");
        }

        // 文件获取失败，不解析
        String[] fileNames = Paths.get(dirPath).toFile().list();
        if (null == fileNames || fileNames.length == 0) {
            throw new UnsupportedOperationException("目录下无待解析文件，请先上传文件！目录 【" + dirPath + "】");
        }

        List<User> userList = new ArrayList<>();
        User user = null;
        Method method = null;
        for (String fileName : fileNames) {
            try (HSSFWorkbook wb = HSSFReadWrite.readFile(dirPath + File.separator + fileName)) {
                LOGGER.info("Data dump:");

                for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                    HSSFSheet sheet = wb.getSheetAt(k);
                    int rows = sheet.getPhysicalNumberOfRows();
                    LOGGER.info("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows + " row(s).");
                    for (int r = 1; r < rows; r++) {//从第二行开始获取数据
                        HSSFRow row = sheet.getRow(r);
                        if (row == null) {
                            continue;
                        }

                        user = new User();
                        LOGGER.info("ROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
                        for (int c = 1; c < row.getLastCellNum(); c++) {
                            HSSFCell cell = row.getCell(c);
                            Object value = HSSFReadWrite.getCellValue(cell, FIELD_TYPES[c]);
                            LOGGER.info("set User [{}] = [{}]", HEAD_FIELDS[c], value);
                            try {
                                method = User.class.getMethod("set" +
                                        StringUtils.upperFirstChar(HEAD_FIELDS[c]), FIELD_TYPES[c]);
                            } catch (Exception e) {
                                LOGGER.error("get User Method set[{}] error!",
                                        StringUtils.upperFirstChar(HEAD_FIELDS[c]), e);
                                continue;
                            }
                            try {
                                method.invoke(user, value);
                            } catch (Exception e) {
                                LOGGER.error("set User [{}] = [{}] error!", HEAD_FIELDS[c], value, e);
                                continue;
                            }
                        }
                        userList.add(user);
                    }
                }
            }
        }


        return userList;
    }
}
