package com.servicefeign.server.fallback;

import com.servicefeign.server.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * Email 1655379381@qq.com
 *
 * @author DaiJin
 * Created Time 2019/7/18.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, 服务器升级维护中,"+name;
    }
}
