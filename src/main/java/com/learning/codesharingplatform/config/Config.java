package com.learning.codesharingplatform.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class Config implements BeanPostProcessor {

    // Spring boot robi autoconfiguracje beanow - m.in. autoconfiguruje freemakera
    // dlatego zeby zmienic konfiguracje stosuje metode ponizej
    // metoda wyluskuje bean z konfiguracją freemarkera, zmienia format daty
    // i zwraca zaktualizowana konfiguracje

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof FreeMarkerConfigurer) {
            FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) bean;
            freeMarkerConfigurer.getConfiguration().setDateTimeFormat("yyyy/MM/dd HH:mm:ss");
        }
        return bean;
    }

}
