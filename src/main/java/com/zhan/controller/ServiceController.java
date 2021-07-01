package com.zhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取某服务所有的实例
     *
     * @return
     */
    @RequestMapping("services")
    public Object getServices() {

        List<ServiceInstance> instances = discoveryClient.getInstances("consul-producer");
        System.out.println(instances.toString());
        return instances;
    }

    /**
     * 轮训获取服务中的其中一个
     *
     * @return
     */
    @RequestMapping("discover")
    public String discover() {

        return loadBalancerClient.choose("consul-producer").toString();
    }
}