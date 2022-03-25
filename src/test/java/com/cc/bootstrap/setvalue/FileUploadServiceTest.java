package com.cc.bootstrap.setvalue;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.intl.demo.file.upload.FileUploadService;
import com.cc.bootstrap.intl.logic.FileInfoLogic;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * @Description: 解决单测时，@InjectMocks无法获取@Value问题
 * @author: ChenChen
 * @date: 2022/3/25 10:38
 */
public class FileUploadServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceTest.class);

    @InjectMocks
    private FileUploadService fileUploadService;
    @Mock
    private FileInfoLogic fileInfoLogic;

    @Mock
    HttpServletRequest request;

    @Before
    public void beforeEveryTest() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);// 类上有了@RunWith(MockitoJUnitRunner.class)，就不用写这个代码了

        // 解决单测时，无法获取@Value问题
        /** 由于Mockito不知道Spring的运行配置值，所以无法获取@Value值，要么使用
         * @RunWith(SpringRunner.class)
         * @SpringBootTest 搭配@MockBean来进行测试
         * 但是这样会运行整个spring容器，会慢一些。
         *
         * 要么自己为字段赋值，例如运用反射为字段赋值。
         */
        ReflectUtils.setField(fileUploadService, "fileUploadPath", "upload");
        // 这个类也不用自己写，spring-test 依赖中包含此类
        ReflectionTestUtils.setField(fileUploadService, "fileUploadPath", "upload");
    }


    @Test
    public void test_uploadFiles_success() throws IOException {
        // data
        String userId = "testUserId";
        MultipartFile[] files = new MultipartFile[1];
        files[0] = new MockMultipartFile("files", "测试文件上传-用户信息.xls",
                MediaType.MULTIPART_FORM_DATA, this.getClass().getResourceAsStream("/测试文件上传-用户信息.xls"));
        // stubs
        doNothing().when(fileInfoLogic).insertUploadFileInfo(any(), any(), any(), any(), any());
        // test
        ResponseResult responseResult = fileUploadService.uploadFiles(userId, files);
        // verify
        TestCase.assertTrue(responseResult.getSuccess());
    }
}
