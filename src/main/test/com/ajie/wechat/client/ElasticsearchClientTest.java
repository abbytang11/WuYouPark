package com.ajie.wechat.client;

import com.ajie.wechat.entity.elasticsearch.GeoPoint;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElasticsearchClientTest {
    private ElasticsearchClient client = new ElasticsearchClient();

    @Test
    public void testIndexing() throws Exception {
        client.init();
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setId(1L);
        geoPoint.setName("桐梓林");
        geoPoint.setLocation("40.715, -74.011");
        client.indexing(geoPoint.getId(),geoPoint);
    }

    @Test
    public void testDelete() throws Exception {

    }
}