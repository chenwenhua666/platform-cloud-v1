package com.plm.platform.monitor.admin.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 */
@Component
public class StartedUpRunner implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ConfigurableApplicationContext context;

    @Value("${spring.application.name:'platform-monitor-admin'}")
    private String applicationName;

    @Autowired
    public StartedUpRunner(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (context.isActive()) {
            log.info("  _   _   _   _   _   _   _   _");
            log.info(" / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\");
            log.info("( c | o | m | p | l | e | t | e )");
            log.info(" \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/");
            log.info("{} 启动完毕，时间：{}", applicationName, LocalDateTime.now());
        }
    }
}
