package org.behzadfz.concurrency.blockingqueue;

import org.behzadfz.advanced.CustomVersionAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    @CustomVersionAnnotation(validVersions = {"1.0", "2.1"})
    private List versions = new ArrayList<>();

    @SuppressWarnings({"unchecked"}) // better way is to change List above to List<String> to not get the warning at the first place
    public void addVersion(String version) throws Exception {
        CustomVersionValidator.validate(version);

        versions.add(version);
    }

    public List getVersions() {
        return versions;
    }
}
