package SupportClasses;

import java.util.ArrayList;

public class InfoList {
    private ArrayList<Integer> ids;
    private ArrayList<Integer> sizes;
    private ArrayList<Double> times;

    public InfoList(ArrayList<Integer> ids, ArrayList<Integer> sizes, ArrayList<Double> times) {
        this.ids = ids;
        this.sizes = sizes;
        this.times = times;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public ArrayList<Integer> getSizes() {
        return this.sizes;
    }

    public ArrayList<Double> getTimes() {
        return this.times;
    }
}
