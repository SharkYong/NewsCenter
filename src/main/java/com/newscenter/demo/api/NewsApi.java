package com.newscenter.demo.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://v.juhe.cn",value = "index")
public interface NewsApi {
    @RequestMapping(value = "/toutiao/index", method = RequestMethod.GET)
    public String getdis(@RequestParam("key") String key,
                         @RequestParam("type") String type);
}
