package com.cc.bootstrap.common.demo.springbootstudy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: springboot源码学习1-读取文件的所有属性放入result
 * jar:file:/D:/software_data/mavenRepo/org/springframework/boot/spring-boot/2.3.2.RELEASE/spring-boot-2.3.2.RELEASE.jar!/META-INF/spring.factories
 * @author: ChenChen
 * @date: 2024-02-22 16:11
 */
@Slf4j
@Component
public class Demo1_Core {
    private static final Map<ClassLoader, MultiValueMap<String, String>> cache = new ConcurrentReferenceHashMap<>();
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";


    // loadSpringFactories
    private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
        MultiValueMap<String, String> result = cache.get(classLoader);
        if (result != null) {
            return result;
        }

        try {
            Enumeration<URL> urls = (classLoader != null ?
                    classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
                    ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
            //MultiValueMap<String, String> 第二个泛型是List的泛型
            result = new LinkedMultiValueMap<>();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                //Properties extends Hashtable<Object,Object>
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    String factoryTypeName = ((String) entry.getKey()).trim();
                    // StringUtils.commaDelimitedListToStringArray((String) entry.getValue()) 将"",""类似格式返回为String[]  org.springframework.util.StringUtils
                    for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                        result.add(factoryTypeName, factoryImplementationName.trim());
                    }
                }
            }
            cache.put(classLoader, result);
            return result;
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load factories from location [" +
                    FACTORIES_RESOURCE_LOCATION + "]", ex);
        }
    }

// jar:file:/D:/software_data/mavenRepo/org/springframework/boot/spring-boot/2.3.2.RELEASE/spring-boot-2.3.2.RELEASE.jar!/META-INF/spring.factories
//文件内容如下：
//# PropertySource Loaders
//    org.springframework.boot.env.PropertySourceLoader=\
//    org.springframework.boot.env.PropertiesPropertySourceLoader,\
//    org.springframework.boot.env.YamlPropertySourceLoader
//
//# Run Listeners
//    org.springframework.boot.SpringApplicationRunListener=\
//    org.springframework.boot.context.event.EventPublishingRunListener
//
//# Error Reporters
//    org.springframework.boot.SpringBootExceptionReporter=\
//    org.springframework.boot.diagnostics.FailureAnalyzers
//
//# Application Context Initializers
//    org.springframework.context.ApplicationContextInitializer=\
//    org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
//    org.springframework.boot.context.ContextIdApplicationContextInitializer,\
//    org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
//    org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer,\
//    org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer
//
//# Application Listeners
//    org.springframework.context.ApplicationListener=\
//    org.springframework.boot.ClearCachesApplicationListener,\
//    org.springframework.boot.builder.ParentContextCloserApplicationListener,\
//    org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor,\
//    org.springframework.boot.context.FileEncodingApplicationListener,\
//    org.springframework.boot.context.config.AnsiOutputApplicationListener,\
//    org.springframework.boot.context.config.ConfigFileApplicationListener,\
//    org.springframework.boot.context.config.DelegatingApplicationListener,\
//    org.springframework.boot.context.logging.ClasspathLoggingApplicationListener,\
//    org.springframework.boot.context.logging.LoggingApplicationListener,\
//    org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener
//
//# Environment Post Processors
//    org.springframework.boot.env.EnvironmentPostProcessor=\
//    org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor,\
//    org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor,\
//    org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor,\
//    org.springframework.boot.reactor.DebugAgentEnvironmentPostProcessor
//
//# Failure Analyzers
//    org.springframework.boot.diagnostics.FailureAnalyzer=\
//    org.springframework.boot.context.properties.NotConstructorBoundInjectionFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.BeanCurrentlyInCreationFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.BeanDefinitionOverrideFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.BeanNotOfRequiredTypeFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.BindFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.BindValidationFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.UnboundConfigurationPropertyFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.ConnectorStartFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.NoSuchMethodFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.NoUniqueBeanDefinitionFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.PortInUseFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.ValidationExceptionFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.InvalidConfigurationPropertyNameFailureAnalyzer,\
//    org.springframework.boot.diagnostics.analyzer.InvalidConfigurationPropertyValueFailureAnalyzer
//
//# FailureAnalysisReporters
//    org.springframework.boot.diagnostics.FailureAnalysisReporter=\
//    org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter


    // PropertiesPropertySourceLoader
    private static final String XML_FILE_EXTENSION = ".xml";
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, ?> loadProperties(Resource resource) throws IOException {
        String filename = resource.getFilename();
        if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
            return (Map) PropertiesLoaderUtils.loadProperties(resource);
        }
        return new HashMap<>();
    }

    //使用Set集合数据不可重复的特性进行去重操作。
    protected final <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public void stringFormat() {
        String message = "error!";
        String formatStr = String.format("The following classes could not be excluded because they are"
                + " not auto-configuration classes:%n%s", message);
        log.info("stringFormat formatStr = [{}]", formatStr);
    }

//    事件及事件监听相关的内容不在此过多展开。
//    spring.factories中自动配置监听器相关配置代码如下。
//    org.springframework.boot.autoconfigure.AutoConfigurationImportListener=\
//    org.springframework.boot.autoconfigure.condition.ConditionEvaluationReportAutoConfigurationImportListener

}
