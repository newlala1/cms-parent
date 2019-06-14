package org.xh.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName WebApplication
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:08
 * @ModifyDate 2019/6/14 9:08
 * @Version 1.0
 */

@EnableCaching
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class,args);
    }
}
