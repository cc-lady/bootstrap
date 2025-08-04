package com.cc.bootstrap.intl.demo.encryptor;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 加密解密示例
 * @author: ChenChen
 * @date: 2025-08-01 17:05
 */
@Slf4j
@Controller//不用RestController因为可能会跳转页面
@RequestMapping(value="/api")
public class EncryptorDemo {

    @Qualifier("smEncryptor")
    @Autowired
    private StringEncryptor stringEncryptor;

    @GetMapping(value = "/encryptor")
    public ResponseResult encryptor() {
        String pwd = System.getProperty("jaspypt.encryptor.password");

        String jiami = stringEncryptor.encrypt("aiml@123");
        String jiemi = stringEncryptor.decrypt(jiami);
        log.info("jiami = {}, jiemi = {}", jiami, jiemi);
        return ResponseResult.success();
    }
}
