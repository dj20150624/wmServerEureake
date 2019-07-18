package com.serviceribbon.controller;
/**
 * Email 1655379381@qq.com
 *
 * @author DaiJin
 * Created Time 2019/7/18.
 */
import com.serviceribbon.server.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    /**
     * 一个服务注册中心，eureka server,端口为8761
     * service-hi工程跑了两个实例，端口分别为8762,8763，分别向服务注册中心注册
     * sercvice-ribbon端口为8764,向服务注册中心注册
     * 当sercvice-ribbon通过restTemplate调用service-hi的hi接口时，因为用ribbon进行了负载均衡，
     * 会轮流的调用service-hi：8762和8763 两个端口的hi接口；
     * ---------------------
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService( name );
    }
}
