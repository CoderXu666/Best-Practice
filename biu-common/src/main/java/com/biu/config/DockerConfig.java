package com.biu.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-28  13:17
 * @Description: Docker配置类
 * @Version: 1.0
 */
@Configuration
public class DockerConfig {

    @Bean
    public DockerClient dockerClient() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://101.42.13.186:2375")
                .build();
        return DockerClientBuilder.getInstance(config).build();
    }
}