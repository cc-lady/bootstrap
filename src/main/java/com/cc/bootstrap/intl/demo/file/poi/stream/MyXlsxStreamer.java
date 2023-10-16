package com.cc.bootstrap.intl.demo.file.poi.stream;

/**
 * @Description: stream 流式读取
 * Xlsx-streamer 的实现原理和 SXSSF 相同，都是滑动窗口 —— 限定读入内存中的数据大小，将正在解析的数据读到内存缓冲区中，
 * 形成一个临时文件，以防止大量使用内存。缓冲区的内容会随着解析的过程不断变化，当流关闭后，临时文件也将被删除。由于内存缓冲区的存在，
 * 整个流不会被完整地读入内存，从而防止了内存溢出。与 SXSSF 一样，因为内存中仅加载入部分行，故牺牲了随机访问的能力，仅能通过遍历顺序访问整表，
 * 这是不可避免的局限。换言之，如果调用 StreamingSheet.getRow (int rownum) 方法，该方法会获取 sheet 的指定行，会抛出 “不支持该操作” 的异常。
 * Xlsx-streamer 最大的优势是兼容 UserModel，尤其适合那些熟悉 UserModel 又不想使用繁琐的 EventModel 的开发者。它和 SXSSF 一样，
 * 都通过实现 UserModel 接口的方式给出解决内存问题的方案，很好地填补了 SXSSF 不支持读取的空白，可以说它是 “读取版” 的 SXSSF。
 * @author: ChenChen
 * @date: 2023/9/5 8:35
 */

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;

public class MyXlsxStreamer {
    public static void main(String[] args) throws Exception {
        parseSku();
    }

    public static void parseSku() throws Exception {
        FileInputStream in = new FileInputStream("D:\\sunhaoyu8\\Documents\\Files\\excel.xlsx");
        Workbook wk = StreamingReader.builder()
                //缓存到内存中的行数，默认是10
                .rowCacheSize(100)
                //读取资源时，缓存到内存的字节大小，默认是1024
                .bufferSize(4096)
                //打开资源，必须，可以是InputStream或者是File
                .open(in);
        Sheet sheet = wk.getSheetAt(0);

        for (Row r : sheet) {
            System.out.print("第" + r.getRowNum() + "行：");
            for (Cell c : r) {
                if (c != null) {
                    System.out.print(c.getStringCellValue() + " ");
                }
            }
            System.out.println();
        }
    }
}
