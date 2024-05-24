package com.mirea.kt.ribo.datastorageapplication;
public class Doctor {
    private String name;
    private String spec;
    private boolean study;

    public Doctor(String name, String spec, boolean study) {
        this.name = name;
        this.spec = spec;
        this.study = study;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public boolean isStudy() {
        return study;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }
}
