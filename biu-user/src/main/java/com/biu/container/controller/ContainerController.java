package com.biu.container.controller;

import com.biu.enums.ResponseEnum;
import com.biu.response.R;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-28  13:36
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/container")
public class ContainerController {
    @Autowired
    private DockerClient dockerClient;

    @GetMapping("/test")
    public R searchContainers() {
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd().withShowAll(true);
        List<Container> containers = listContainersCmd.exec();
        return R.out(ResponseEnum.SUCCESS, containers);
    }
}
