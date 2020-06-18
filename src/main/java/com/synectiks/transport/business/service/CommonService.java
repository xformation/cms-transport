package com.synectiks.transport.business.service;

import java.util.List;

import com.synectiks.transport.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommonService {

    private final static Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    RestTemplate restTemplate;

    @Nullable
    public<T> List<T> getList(String url) {
        List<T> temp = this.restTemplate.getForObject(url, List.class);
        return temp;
    }

    @Nullable
    public<T> T getObject(String url, Class<T> cls) {
        T temp = this.restTemplate.getForObject(url, cls);
        return temp;
    }

    public<T> T postObject(String url, Object obj, Class<T> cls) {
        T temp = this.restTemplate.postForObject(url, obj, cls);
        return temp;
    }
}
