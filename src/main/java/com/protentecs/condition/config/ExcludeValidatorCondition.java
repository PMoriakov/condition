package com.protentecs.condition.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Pavel Moriakov
 * @since 26/03/2023
 */
public class ExcludeValidatorCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        List list = context.getEnvironment().getProperty("exclude.validators", List.class);

        //Get name of the class where annotation was invoked
        try {
            Object source = metadata.getAnnotations().get(Validator.class.getName()).getSource();
            if(source == null){
                return false;
            } else {
                Field declaredField = source.getClass().getDeclaredField("className");
                declaredField.setAccessible(true);
                String[] sourceClassFullName = declaredField.get(source).toString().split("\\.");
                return !list.contains(sourceClassFullName[sourceClassFullName.length - 1]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
