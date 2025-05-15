package org.apache.ignite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 系统初始化
 *
 * @author weiqiang
 * @date 2025/5/15 09:24
 */
@Component
public class SysInitConfig implements CommandLineRunner {

    /**
     * jdbcTemplate
     */
    @Autowired
    public JdbcTemplate jdbcTemplate;

    /**
     * 默认schema
     */
    @Value("${ignite.default.schema:data_public}")
    private String defaultSchema;

    /**
     * 初始化
     *
     * @param args 参数
     * @throws Exception 异常
     */
    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("create schema if not exists " + defaultSchema);
    }
}

