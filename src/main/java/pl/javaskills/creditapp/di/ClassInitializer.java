package pl.javaskills.creditapp.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassInitializer {
    private static final Logger log = LoggerFactory.getLogger(ClassInitializer.class);

    private  Map<String, Object> instanceMap = new HashMap<>();

    public  Object createInstance(Class classInstance) throws Exception {
        return getInstance(classInstance);
    }

    private Object getInstance(Class classInstance) throws Exception {

        if(instanceMap.containsKey(classInstance.getSimpleName())){
            return instanceMap.get(classInstance.getSimpleName());
        }
        Object instance = classInstance.getConstructor().newInstance();

        for(Field field : classInstance.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Inject.class)){
                Object dependencyInstance = getInstance(field.getType());
                Field dependencyFiled = instance.getClass().getDeclaredField(field.getName());
                dependencyFiled.setAccessible(true);
                dependencyFiled.set(instance, dependencyInstance);
            }
        }
        instanceMap.put(instance.getClass().getSimpleName(), instance);
        return instance;
    }

    public void setInstance(Object instance){
        instanceMap.put(instance.getClass().getSimpleName(), instance);
    }
}
