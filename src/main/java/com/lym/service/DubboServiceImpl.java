package com.lym.service;


import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by liuyumeng on 2018/9/18.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class DubboServiceImpl implements DubboService{
    @Override
    public void sayHello(String word) {
        System.out.println(String.format("dubbo service say hello [%s]",word));
    }
}
