package site.thewhale.volunteer.objects;

import java.io.Serializable;

public class Movies implements Serializable  {
    //attributes
    private String name;
    private String plot;
    private String year;
    private String duration;
    private int img;
    private String rate;
    private String imdb;

    //Constructor
    public Movies(String name, int img, String plot, String year, String duration, String rate, String imdb) {
        this.name = name;
        this.plot = plot;
        this.year = year;
        this.duration = duration;
        this.img = img;
        this.rate = rate;
        this.imdb = imdb;
    }

    //Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
