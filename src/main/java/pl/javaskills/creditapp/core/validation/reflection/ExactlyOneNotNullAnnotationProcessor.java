package pl.javaskills.creditapp.core.validation.reflection;

import pl.javaskills.creditapp.core.annotation.ExactlyOneNotNull;
import pl.javaskills.creditapp.core.exception.ExactlyOneNotNullException;
import pl.javaskills.creditapp.core.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExactlyOneNotNullAnnotationProcessor implements ClassAnnotationProcessor {
    @Override
    public void process(Object object, Class aClass) throws IllegalAccessException, ValidationException {

        List<Field> annotationFiledList = new ArrayList<>();
        Set<String> annotationFiledNameList = new HashSet<>();
        if (aClass.isAnnotationPresent(ExactlyOneNotNull.class)) {
            ExactlyOneNotNull annotation = (ExactlyOneNotNull) aClass.getAnnotation(ExactlyOneNotNull.class);
            for (String paramField : annotation.value()) {
                for (Field field : aClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().equals(paramField)) {
                        annotationFiledList.add(field);
                    }
                }
            }

            int numOfNotNullFileds = 0;
            for (Field field : annotationFiledList) {
                if (field.get(object) != null) {
                    numOfNotNullFileds++;
                }
                annotationFiledNameList.add(field.getName());
            }
            if (numOfNotNullFileds != 1) {
                throw new ExactlyOneNotNullException(annotationFiledNameList, aClass.getSimpleName());
            }
        }
    }
}
