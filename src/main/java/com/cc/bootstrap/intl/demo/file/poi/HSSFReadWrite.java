package com.cc.bootstrap.intl.demo.file.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Description: poi工具类 - HSSFReadWrite
 * @author: ChenChen
 * @date: 2022/4/19 10:10
 */
public final class HSSFReadWrite {
    private static Logger LOGGER = LoggerFactory.getLogger(HSSFReadWrite.class);


    public HSSFReadWrite() {
    }

    /**
     * creates an {@link HSSFWorkbook} with the specified OS filename.
     */
    public static HSSFWorkbook readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return new HSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

    /**
     * @Description 获取cell值
     * @param cell
     * @param fieldType 对应映射实体数据字段的类型
     * @author ChenChen
     * @return java.lang.String
     * @date 2022/4/19 10:23
     */
    public static Object getCellValue(HSSFCell cell, Class fieldType) {
        Object value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case FORMULA:
                    value = cell.getCellFormula();
                    break;

                case STRING:
                    value = cell.getStringCellValue();
                    break;

                case NUMERIC:
                    value = getNumericCellValueByFieldType(cell, fieldType);
                    break;

                case BLANK:
                    value = "<BLANK>";
                    break;

                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;

                case ERROR:
                    value = cell.getErrorCellValue();
                    break;

                default:
                    value = cell.getStringCellValue();
            }
            LOGGER.info("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
        }

        return value;
    }

    /**
     * @Description 根据字段类型获取cell数值
     * @param cell
     * @param fieldType
     * @author ChenChen
     * @return java.lang.Object
     * @date 2022/4/19 14:32
     */
    private static Object getNumericCellValueByFieldType(HSSFCell cell, Class fieldType) {
        Object value = null;
        if (Integer.class == fieldType || int.class == fieldType) {
            value = new BigDecimal(String.valueOf(cell.getNumericCellValue())).intValue();
        } else if (Float.class == fieldType || float.class == fieldType) {
            value = new BigDecimal(String.valueOf(cell.getNumericCellValue())).floatValue();
        } else {
            value = new BigDecimal(String.valueOf(cell.getNumericCellValue())).doubleValue();
        }
        return value;
    }
}

