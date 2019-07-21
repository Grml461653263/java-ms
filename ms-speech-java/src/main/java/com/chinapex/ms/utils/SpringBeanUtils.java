package com.chinapex.ms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Leiyuhau
 * 2019.1.7
 */
@Component
@Lazy(false)
public class SpringBeanUtils  implements ApplicationContextAware {
    private  static ApplicationContext applicationContext;

    //获取 applicationContext
    public  static  ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    //通过名字获取Bean
    public  static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class 获取Bean 对象
    public  static  <T> T getBean(Class<T> tClass){
        return getApplicationContext().getBean(tClass);
    }
    //通过name 和class 获取Bean 对象
    public  static  <T> T getBean(String name,Class<T> tClass){
        return getApplicationContext().getBean(name,tClass);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if(SpringBeanUtils.applicationContext==null){
            SpringBeanUtils.applicationContext = applicationContext;
        }
    }
}
