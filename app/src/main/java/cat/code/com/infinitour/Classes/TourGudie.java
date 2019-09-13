package cat.code.com.infinitour.Classes;

public class TourGudie {
    private String name;
    private String age;
    private String rate;
    private String photo_url;
    private String email;

    public TourGudie(String age, String email, String name, String photo_url, String rate) {
        this.name = name;
        this.age = age;
        this.rate = rate;
        this.photo_url = photo_url;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
