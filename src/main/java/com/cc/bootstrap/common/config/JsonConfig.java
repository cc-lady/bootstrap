package com.cc.bootstrap.common.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @ClassName: JsonConfig 
 * @Description: 配置解析json用 jackson - objectMapper
 * @author CC  
 * @date 2021年5月31日 上午11:26:19 
 * @version V1.0 
 */
@Configuration
public class JsonConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //可以设置SimpleModule 进行个性化设置
        SimpleModule module = new SimpleModule();
        //1.对解析值两端去空格
        module.addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
            private static final long serialVersionUID = 1L;

            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException,
                    JsonProcessingException {
                //为空串的字符串也解析为null
                if(StringUtils.isEmpty(jsonParser.getValueAsString())) {
                    return null;
                } else {
                    for (String arg : jsonParser.getValueAsString().split(" ")) {
                        String regex = "\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)";
                        if (Pattern.matches(regex,arg.trim().toLowerCase())){
                            return null;
                        }
                    }
                }

                String currentName = jsonParser.getCurrentName();
                //flag字段不去两端空格  add by cc 20200518
                if("flag".equals(currentName)){
                    return jsonParser.getValueAsString();
                }
                return jsonParser.getValueAsString().trim();
            }
        });
        //设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.registerModule(module);
        //objectMapper.setSerializationInclusion(Include.NON_NULL);//属性为null不参与序列化
        return objectMapper;
    }
}
