package com.example.ewe;

public class ResponseEwe {
    private long id;
    private String name;
    private String airDate;
    private String episode;
    private String[] characters;
    private String url;
    private String created;
    private String password;

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String value) {
        this.airDate = value;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String value) {
        this.episode = value;
    }

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] value) {
        this.characters = value;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String value) {
        this.url = value;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String value) {
        this.created = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }
}