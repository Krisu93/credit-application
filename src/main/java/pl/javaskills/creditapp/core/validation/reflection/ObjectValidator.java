package pl.javaskills.creditapp.core.validation.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.annotation.ValidationCollection;
import pl.javaskills.creditapp.core.annotation.ValidationObject;
import pl.javaskills.creditapp.core.annotation.ValidationOptional;
import pl.javaskills.creditapp.core.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ObjectValidator {
    private static final Logger log = LoggerFactory.getLogger(ObjectValidator.class);

    private final List<ClassAnnotationProcessor> classProcessor;
    private final List<FieldAnnotationProcessor> fieldProcessor;

    public ObjectValidator(List<ClassAnnotationProcessor> classProcessor, List<FieldAnnotationProcessor> fieldProcessor) {
        this.classProcessor = classProcessor;
        this.fieldProcessor = fieldProcessor;
    }

    public void validate(Object object) throws ValidationException, IllegalAccessException, NoSuchFieldException {
        Class aClass = object.getClass();
        Class superClass = aClass.getSuperclass();

        if(!"Object".equals(superClass.getSimpleName())){
            processClass(object, superClass);
            processField(object, superClass);
        }
        processClass(object, aClass);
        processField(object, aClass);
    }

    private void processClass(Object object, Class aClass) throws IllegalAccessException, ValidationException {
        log.debug("Starting validation of class {}.", aClass.getSimpleName());
        for(ClassAnnotationProcessor processor: classProcessor)
            processor.process(object, aClass);
    }

    private void processField(Object object, Class aClass) throws IllegalAccessException, ValidationException, NoSuchFieldException {
        for(Field field: aClass.getDeclaredFields()) {
            field.setAccessible(true);
            log.debug("Starting validation of field {} in class {}.", field.getName(), aClass.getSimpleName());
            for(FieldAnnotationProcessor processor: fieldProcessor) {
                processor.process(object, field);
            }
            Object obj = field.get(object);
            if(field.isAnnotationPresent(ValidationObject.class))
                validate(obj);
            else if (field.isAnnotationPresent(ValidationCollection.class)){
                Collection<Object> elements = (Collection<Object>) obj;
                for(Object element : elements){
                    validate(element);
                }
            } else if (field.isAnnotationPresent(ValidationOptional.class)){
                if(((Optional<Object>) obj).isPresent()){
                    Object elementFromOptional = ((Optional<Object>) obj).get();
                    validate(elementFromOptional);
                }
            }

        }
    }
}
