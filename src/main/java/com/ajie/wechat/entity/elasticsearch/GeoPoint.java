package com.ajie.wechat.entity.elasticsearch;

public class GeoPoint implements ElasticsearchEndpoint{
    private long id;
    private String name;
    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getIndex() {
        return "wuyou";
    }

    @Override
    public String getType() {
        return "geoPoint";
    }
}
