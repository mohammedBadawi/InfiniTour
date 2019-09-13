package cat.code.com.infinitour.Classes;

import java.util.ArrayList;

public class Tourist {
    private String name;
    private String email;
    private String password;
    private ArrayList<String> spoken_languges;

    public Tourist(String name, String email, String password, ArrayList<String> spoken_languges) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.spoken_languges = spoken_languges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getSpoken_languges() {
        return spoken_languges;
    }

    public void setSpoken_languges(ArrayList<String> spoken_languges) {
        this.spoken_languges = spoken_languges;
    }
}
