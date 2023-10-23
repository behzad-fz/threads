package org.behzadfz.advanced;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomVersionValidator {
    public static void validate(String version) throws Exception {
        Field classMemberField = App.class.getDeclaredField("versions");
        if (classMemberField.isAnnotationPresent(CustomVersionAnnotation.class)) {
            CustomVersionAnnotation annotation = classMemberField.getAnnotation(CustomVersionAnnotation.class);
            String[] validVersions = annotation.validVersions();
            if (!Arrays.asList(validVersions).contains(version)) {
                throw new Exception("Invalid version: " + version);
            }
        }
    }
}
