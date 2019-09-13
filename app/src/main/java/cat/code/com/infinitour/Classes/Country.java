package cat.code.com.infinitour.Classes;

import java.util.ArrayList;

public class Country {
    private ArrayList<String> cities;

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public Country(ArrayList<String> cities) {
        this.cities = cities;
    }
}
