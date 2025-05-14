package org.apache.ignite.config;

import org.apache.ignite.util.GlobalUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static org.apache.ignite.global.GlobalConstants.HTTP_CODE;
import static org.apache.ignite.global.GlobalConstants.INTERVAL_COLON;

/**
 * 系统启动
 *
 * @author weiqiang
 * @date 2025/4/29 11:18
 */
@Slf4j
@Order(value = 1)
@Component
public class SysCoreStartConfig implements CommandLineRunner {
    /**
     * 应用名称
     */
    @Value("${spring.application.name:apache-ignite-demo}")
    public String applicationName;
    /**
     * 激活配置文件
     */
    @Value("${spring.profiles.active:test}")
    public String profileActiveName;
    /**
     * serverPort
     */
    @Value("${server.port:8080}")
    private String serverPort;
    /**
     * context-path
     */
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 开启swagger
     */
    @Value("${springdoc.api-docs.enable:true}")
    private boolean enableSwagger;

    /**
     * 标题
     */
    @Value("${springdoc.info.title:quick-frame}")
    private String apiTitle;

    /**
     * 版本号
     */
    @Value("${springdoc.info.version:1.0}")
    private String apiVersion;

    /**
     * 描述
     */
    @Value("${springdoc.info.description:}")
    private String apiDescription;

    /**
     * 作者
     */
    @Value("${springdoc.info.contact.name:}")
    private String apiContactName;
    /**
     * 联系地址
     */
    @Value("${springdoc.info.contact.url:}")
    private String apiContactUrl;
    /**
     * 邮箱
     */
    @Value("${springdoc.info.contact.email:}")
    private String apiContactEmail;


    /**
     * 启动时执行
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        String apiUrl = GlobalUtils.appendString(HTTP_CODE, GlobalUtils.getHostIp(), INTERVAL_COLON, serverPort, contextPath);
        log.info("页面链接:{}", apiUrl);
        if (enableSwagger) {
            log.info("接口文档:{}/{}", apiUrl, "doc.html");
            if (!GlobalUtils.isOsLinux()) {
                log.info("接口文档:http://localhost:{}{}/{}", serverPort, contextPath, "doc.html");
            }
        }
    }

    /**
     * 配置swagger
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String apiUrl = GlobalUtils.appendString(HTTP_CODE, GlobalUtils.getHostIp(), INTERVAL_COLON, serverPort, contextPath);
        final Contact contact = new Contact().url(apiContactUrl).email(apiContactEmail).name(apiContactName);
        return new OpenAPI().info(new Info()
                .title(apiTitle)
                .version(apiVersion)
                .description(apiDescription)
                .contact(contact)
                .termsOfService(apiUrl)
                .license(new License().name("Apache 2.0").url(apiUrl)));
    }

}
