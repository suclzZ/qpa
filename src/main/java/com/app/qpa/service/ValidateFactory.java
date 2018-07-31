package com.app.qpa.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 检验工厂稽核类、组、项
 *
 */
@Component
public class ValidateFactory implements ApplicationContextAware{
    private Map<String, Validator> validatorMap;
    private List<Validator> validators;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        findValidators(applicationContext);
    }

    private void findValidators(ApplicationContext applicationContext) {
        Map<String, Validator> validatorMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Validator.class, true, false);
        this.validatorMap = validatorMap;
        if(validators!=null){
            validators = new ArrayList<>(validatorMap.values());
        }
    }

    public Map<String, Validator> getValidatorMap() {
        return validatorMap;
    }

    public List<Validator> getValidators() {
        return validators;
    }
}
