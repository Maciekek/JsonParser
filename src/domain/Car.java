package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciek on 2015-03-08.
 */
public class Car {
    public String mark;
    public String model;
    public int yop;


    public Car() {
    }

    public Car(String mark, String model, int yop) {
        this.mark = mark;
        this.model = model;
        this.yop = yop;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYop() {
        return yop;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }


}
