package model;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class Week {
    private boolean w1;
    private boolean w2;
    private boolean w3;
    private boolean w4;
    private boolean w5;
    private boolean w6;
    private boolean w7;

    public Week() {
    }

    public boolean getW1() {
        return w1;
    }

    public void setW1(boolean w1) {
        this.w1 = w1;
    }

    public boolean getW2() {
        return w2;
    }

    public void setW2(boolean w2) {
        this.w2 = w2;
    }

    public boolean getW3() {
        return w3;
    }

    public void setW3(boolean w3) {
        this.w3 = w3;
    }

    public boolean getW4() {
        return w4;
    }

    public void setW4(boolean w4) {
        this.w4 = w4;
    }

    public boolean getW5() {
        return w5;
    }

    public void setW5(boolean w5) {
        this.w5 = w5;
    }

    public boolean getW6() {
        return w6;
    }

    public void setW6(boolean w6) {
        this.w6 = w6;
    }

    public boolean getW7() {
        return w7;
    }

    public void setW7(boolean w7) {
        this.w7 = w7;
    }

    public void setWeek(ArrayList<Boolean> week){
        this.w1 = week.get(0);
        this.w2 = week.get(1);
        this.w3 = week.get(2);
        this.w4 = week.get(3);
        this.w5 = week.get(4);
        this.w6 = week.get(5);
        this.w7 = week.get(6);
    }
    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    @Override
    public String toString() {
        String textWeek = "";
        textWeek = textWeek.concat(String.valueOf(boolToInt(w1)))
                            .concat(String.valueOf(boolToInt(w2)))
                            .concat(String.valueOf(boolToInt(w3)))
                            .concat(String.valueOf(boolToInt(w4)))
                            .concat(String.valueOf(boolToInt(w5)))
                            .concat(String.valueOf(boolToInt(w6)))
                            .concat(String.valueOf(boolToInt(w7)));
        return textWeek;
    }
}
