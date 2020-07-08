package com.example.bingogameeng;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Elements {
    private long id;
    private String eleName;

    public Elements() {}

    public Elements(String eleName) {
        super();
        this.eleName = eleName;
    }

    public Elements(long id, String eleName) {
        super();
        this.id = id;
        this.eleName = eleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    @Override
    public String toString() {
        return "Elements [id=" + id + ", firstName=" + eleName + "]";
    }


}
