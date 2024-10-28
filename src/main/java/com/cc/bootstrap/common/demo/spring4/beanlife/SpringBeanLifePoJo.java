package com.cc.bootstrap.common.demo.spring4.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: spring容器中bean的生命周期
 * @author: ChenChen
 * @date: 2024-02-26 15:47
 */
public class SpringBeanLifePoJo implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        BeanPostProcessor, InitializingBean, DisposableBean {

    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    private String initialColor;
    private Tool tool;

    public SpringBeanLifePoJo() {
        System.out.println("SpringBeanLifePoJo实例化-无参构造");
    }

    public SpringBeanLifePoJo(Tool tool) {
        this.tool = tool;
        System.out.println("SpringBeanLifePoJo实例化-有参Tool构造");
    }


    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("BeanNameAware.setBeanName(name)");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        SpringBeanLifePoJo springBeanLifePoJo = (SpringBeanLifePoJo) beanFactory.getBean(beanName);
        System.out.println("BeanFactoryAware.setBeanFactory(beanFactory)");
        System.out.println("BeanFactoryAware.setBeanFactory(beanFactory)this:"
                + this.toString() + ", springBeanLifePoJo:" + springBeanLifePoJo);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContextAware.setApplicationContext(applicationContext)");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.initialColor = "yellow";
        System.out.println("InitializingBean.afterPropertiesSet");

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)" + beanName);// + "bean:" + bean
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessAfterInitialization(bean, beanName)" + beanName);// + "bean:" + bean
        return bean;
    }


    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public String getInitialColor() {
        return initialColor;
    }

    public void setInitialColor(String initialColor) {
        this.initialColor = initialColor;
    }

    @Override
    public void destroy() throws Exception {
        this.applicationContext = null;
        this.beanFactory = null;
        System.out.println("DisposableBean.destroy()");
    }
}

//"D:\Program Files (x86)\Java\jdk1.8.0_45\bin\java.exe" -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true "-javaagent:D:\software\Idea\IntelliJ IDEA 2021.3.1\lib\idea_rt.jar=64839:D:\software\Idea\IntelliJ IDEA 2021.3.1\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\charsets.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\deploy.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\access-bridge-32.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\cldrdata.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\dnsns.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\jaccess.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\jfxrt.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\localedata.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\nashorn.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\sunec.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\sunjce_provider.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\sunmscapi.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\sunpkcs11.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\zipfs.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\javaws.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\jce.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\jfr.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\jfxswt.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\jsse.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\management-agent.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\plugin.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\resources.jar;D:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\rt.jar;D:\cc_study\idea_workspace\springbootDemo\target\classes;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-starter-web\2.3.2.RELEASE\spring-boot-starter-web-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-starter\2.3.2.RELEASE\spring-boot-starter-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot\2.3.2.RELEASE\spring-boot-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-autoconfigure\2.3.2.RELEASE\spring-boot-autoconfigure-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-starter-logging\2.3.2.RELEASE\spring-boot-starter-logging-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\software_data\mavenRepo\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;D:\software_data\mavenRepo\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;D:\software_data\mavenRepo\org\apache\logging\log4j\log4j-to-slf4j\2.13.3\log4j-to-slf4j-2.13.3.jar;D:\software_data\mavenRepo\org\apache\logging\log4j\log4j-api\2.13.3\log4j-api-2.13.3.jar;D:\software_data\mavenRepo\org\slf4j\jul-to-slf4j\1.7.30\jul-to-slf4j-1.7.30.jar;D:\software_data\mavenRepo\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;D:\software_data\mavenRepo\org\springframework\spring-core\5.2.8.RELEASE\spring-core-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-jcl\5.2.8.RELEASE\spring-jcl-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\yaml\snakeyaml\1.26\snakeyaml-1.26.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-starter-json\2.3.2.RELEASE\spring-boot-starter-json-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\core\jackson-databind\2.11.1\jackson-databind-2.11.1.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\core\jackson-annotations\2.11.1\jackson-annotations-2.11.1.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\core\jackson-core\2.11.1\jackson-core-2.11.1.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.11.1\jackson-datatype-jdk8-2.11.1.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.11.1\jackson-datatype-jsr310-2.11.1.jar;D:\software_data\mavenRepo\com\fasterxml\jackson\module\jackson-module-parameter-names\2.11.1\jackson-module-parameter-names-2.11.1.jar;D:\software_data\mavenRepo\org\springframework\boot\spring-boot-starter-tomcat\2.3.2.RELEASE\spring-boot-starter-tomcat-2.3.2.RELEASE.jar;D:\software_data\mavenRepo\org\apache\tomcat\embed\tomcat-embed-core\9.0.37\tomcat-embed-core-9.0.37.jar;D:\software_data\mavenRepo\org\glassfish\jakarta.el\3.0.3\jakarta.el-3.0.3.jar;D:\software_data\mavenRepo\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.37\tomcat-embed-websocket-9.0.37.jar;D:\software_data\mavenRepo\org\springframework\spring-web\5.2.8.RELEASE\spring-web-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-beans\5.2.8.RELEASE\spring-beans-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-webmvc\5.2.8.RELEASE\spring-webmvc-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-aop\5.2.8.RELEASE\spring-aop-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-context\5.2.8.RELEASE\spring-context-5.2.8.RELEASE.jar;D:\software_data\mavenRepo\org\springframework\spring-expression\5.2.8.RELEASE\spring-expression-5.2.8.RELEASE.jar" org.example.DemoApplication
//
//        .   ____          _            __ _ _
//        /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//        ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
//        \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//        '  |____| .__|_| |_|_| |_\__, | / / / /
//        =========|_|==============|___/=/_/_/_/
//        :: Spring Boot ::        (v2.3.2.RELEASE)
//
//        2024-02-26 17:09:27.330  INFO 6712 --- [           main] org.example.DemoApplication              : Starting DemoApplication on CC-PC with PID 6712 (D:\cc_study\idea_workspace\springbootDemo\target\classes started by chenc in D:\cc_study\idea_workspace\springbootDemo)
//        2024-02-26 17:09:27.330  INFO 6712 --- [           main] org.example.DemoApplication              : No active profile set, falling back to default profiles: default
//        2024-02-26 17:09:27.795  INFO 6712 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'beanConfig' of type [org.example.spring4.BeanConfig$$EnhancerBySpringCGLIB$$3d06a090] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
//        Tool实例化-有参构造
//        2024-02-26 17:09:27.805  INFO 6712 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'tool' of type [org.example.spring4.Tool] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
//        SpringBeanLifePoJo实例化-有参Tool构造
//        BeanNameAware.setBeanName(name)
//        BeanFactoryAware.setBeanFactory(beanFactory)
//        BeanFactoryAware.setBeanFactory(beanFactory)this:org.example.spring4.SpringBeanLifePoJo@52a3ff, springBeanLifePoJo:org.example.spring4.SpringBeanLifePoJo@52a3ff
//        ApplicationContextAware.setApplicationContext(applicationContext)
//        InitializingBean.afterPropertiesSet
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration$EmbeddedTomcat
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration$EmbeddedTomcat
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)tomcatServletWebServerFactory
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)websocketServletWebServerCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)websocketServletWebServerCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.context.properties.BoundConfigurationProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.context.properties.BoundConfigurationProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)server-org.springframework.boot.autoconfigure.web.ServerProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)server-org.springframework.boot.autoconfigure.web.ServerProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)servletWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)servletWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)tomcatServletWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)tomcatServletWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)tomcatWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)tomcatWebServerFactoryCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)localeCharsetMappingsCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)localeCharsetMappingsCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)dispatcherServlet
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)dispatcherServlet
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)multipartConfigElement
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)multipartConfigElement
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)dispatcherServletRegistration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)dispatcherServletRegistration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)errorPageCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)errorPageCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)tomcatServletWebServerFactory
//        2024-02-26 17:09:27.995  INFO 6712 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
//        2024-02-26 17:09:28.010  INFO 6712 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
//        2024-02-26 17:09:28.010  INFO 6712 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.37]
//        2024-02-26 17:09:28.105  INFO 6712 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
//        2024-02-26 17:09:28.105  INFO 6712 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 740 ms
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)requestContextFilter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)requestContextFilter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)formContentFilter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)formContentFilter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)characterEncodingFilter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)characterEncodingFilter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)demoApplication
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)demoApplication
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.AutoConfigurationPackages
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.AutoConfigurationPackages
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)taskExecutorBuilder
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)taskExecutorBuilder
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)error
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)error
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)beanNameViewResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)beanNameViewResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)conventionErrorViewResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)conventionErrorViewResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)errorAttributes
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)errorAttributes
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)basicErrorController
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)basicErrorController
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcContentNegotiationManager
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcContentNegotiationManager
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcConversionService
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcConversionService
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcValidator
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcValidator
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)stringHttpMessageConverter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)stringHttpMessageConverter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)standardJacksonObjectMapperBuilderCustomizer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)standardJacksonObjectMapperBuilderCustomizer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$ParameterNamesModuleConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$ParameterNamesModuleConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)parameterNamesModule
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)parameterNamesModule
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)jsonComponentModule
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)jsonComponentModule
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)jacksonObjectMapperBuilder
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)jacksonObjectMapperBuilder
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)jacksonObjectMapper
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)jacksonObjectMapper
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mappingJackson2HttpMessageConverter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mappingJackson2HttpMessageConverter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)messageConverters
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)messageConverters
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)applicationTaskExecutor
//        2024-02-26 17:09:28.245  INFO 6712 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)applicationTaskExecutor
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)requestMappingHandlerAdapter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)requestMappingHandlerAdapter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcResourceUrlProvider
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcResourceUrlProvider
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)requestMappingHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)requestMappingHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)welcomePageHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)welcomePageHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcPathMatcher
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcPathMatcher
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcUrlPathHelper
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcUrlPathHelper
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)viewControllerHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)viewControllerHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)beanNameHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)beanNameHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)routerFunctionMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)routerFunctionMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)resourceHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)resourceHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)defaultServletHandlerMapping
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)defaultServletHandlerMapping
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)handlerFunctionAdapter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)handlerFunctionAdapter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcUriComponentsContributor
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcUriComponentsContributor
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)httpRequestHandlerAdapter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)httpRequestHandlerAdapter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)simpleControllerHandlerAdapter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)simpleControllerHandlerAdapter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)handlerExceptionResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)handlerExceptionResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mvcViewResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mvcViewResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)defaultViewResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)defaultViewResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)viewResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)viewResolver
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)objectNamingStrategy
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)objectNamingStrategy
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mbeanServer
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mbeanServer
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)mbeanExporter
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)mbeanExporter
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)springApplicationAdminRegistrar
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)springApplicationAdminRegistrar
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.aop.AopAutoConfiguration$ClassProxyingConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.aop.AopAutoConfiguration$ClassProxyingConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)applicationAvailability
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)applicationAvailability
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)lifecycleProcessor
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)lifecycleProcessor
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)taskSchedulerBuilder
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)taskSchedulerBuilder
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
//        BeanPostProcessor.postProcessBeforeInitialization(bean, beanName)multipartResolver
//        BeanPostProcessor.postProcessAfterInitialization(bean, beanName)multipartResolver
//        2024-02-26 17:09:28.395  INFO 6712 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
//        2024-02-26 17:09:28.408  INFO 6712 --- [           main] org.example.DemoApplication              : Started DemoApplication in 1.367 seconds (JVM running for 3.858)
//        2024-02-26 17:09:32.725  INFO 6712 --- [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
//        DisposableBean.destroy()
//
//        Process finished with exit code 130

