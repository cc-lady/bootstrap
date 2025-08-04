package com.cc.bootstrap.demo;

import com.cc.bootstrap.intl.demo.encryptor.JasyptConfig;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @Description: Jasypt加密解密测试
 * @author: ChenChen
 * @date: 2025-08-04 10:02
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class JasyptConfigTest {

    @Test
    public void test_jasyptConfig() {
        JasyptConfig jasyptConfig = new JasyptConfig();
        StringEncryptor stringEncryptor = jasyptConfig.stringEncryptor();

        String passwordOrigin = "aiml@swagger";
        String result = stringEncryptor.encrypt(passwordOrigin);
        log.info("[{}]加密结果 [{}]", passwordOrigin, result);
        String password = stringEncryptor.decrypt(result);
        log.info("[{}]解密结果 [{}]", result, password);
        TestCase.assertEquals(passwordOrigin, password);
    }
}
