package com.cc.bootstrap.powermock;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.intl.demo.file.upload.FileUploadService;
import com.cc.bootstrap.intl.logic.FileInfoLogic;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.reflect.Whitebox.setInternalState;

/**
 * @Description: 使用powermock进行单元测试
 * @author: ChenChen
 * @date: 2022/3/25 10:38
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUploadService.class)
public class FileUploadServiceTest2 {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceTest2.class);


    @Test
    public void test_uploadFiles_success() throws IOException {
        // data
        String userId = "testUserId";
        MultipartFile[] files = new MultipartFile[1];
        files[0] = new MockMultipartFile("files", "测试文件上传-用户信息.xls",
                MediaType.MULTIPART_FORM_DATA, this.getClass().getResourceAsStream("/测试文件上传-用户信息.xls"));
        // stubs
        FileInfoLogic fileInfoLogic = PowerMockito.mock(FileInfoLogic.class);
        PowerMockito.doNothing().when(fileInfoLogic).insertUploadFileInfo(any(), any(), any(), any(), any());
        // test
        FileUploadService fileUploadService = new FileUploadService();
        // 使用powermock的设置方式，原理类似（反射设置字段值）
        setInternalState(fileUploadService, "fileUploadPath", "upload");
        setInternalState(fileUploadService, "fileInfoLogic", fileInfoLogic);
//        ReflectionTestUtils.setField(fileUploadService, "fileUploadPath", "upload");
//        ReflectionTestUtils.setField(fileUploadService, "fileInfoLogic", fileInfoLogic);
        ResponseResult responseResult = fileUploadService.uploadFiles(userId, files);
        // verify
        TestCase.assertTrue(responseResult.getSuccess());
    }
}
