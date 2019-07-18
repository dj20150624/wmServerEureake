package com.serviceribbon.server;
/**
 * Email 1655379381@qq.com
 *
 * @author DaiJin
 * Created Time 2019/7/18.
 */
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * 通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，
 * 在这里我们直接用的程序名替代了具体的url地址，
 * 在ribbon中它会根据服务名来选择具体的服务实例，
 * 根据服务实例在请求的时候会用具体的url替换掉服务名
 *
 * */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        // 负载均衡，访问了不同的端口的服务实例
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
    /**
     * 该注解对该方法创建了熔断器的功能，
     * 并指定了fallbackMethod熔断方法，
     * 熔断方法直接返回了一个字符串，
     * 字符串为"hi,"+name+",sorry,error!"
     * */
    public String hiError(String name){
        return "hi,"+name+",服务升级维护中";
    }
}
