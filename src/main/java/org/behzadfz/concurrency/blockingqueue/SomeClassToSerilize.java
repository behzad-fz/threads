package org.behzadfz.concurrency.blockingqueue;

import java.io.Serializable;

public class SomeClassToSerilize implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public SomeClassToSerilize(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SomeClassToSerilize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
