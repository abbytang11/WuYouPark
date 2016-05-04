package com.ajie.wechat.client;

import com.ajie.wechat.entity.elasticsearch.ElasticsearchEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

@Component
public class ElasticsearchClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchClient.class);
    private ElasticsearchConfiguration config;
    private WebResource wr;

    @PostConstruct
    public void init() {
        JerseyClient jerseyClient = new JerseyClient(ElasticsearchConfiguration.HOST);
        wr = jerseyClient.buildWebResource();
    }

    public <T extends ElasticsearchEndpoint> void indexing(long id, T t){
        ClientResponse response = wr.path(t.getIndex())
                .path(t.getType())
                .path(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .put(ClientResponse.class,t);
        ElasticsearchResponseProcessor.indexing(response);
    }

    public <T extends ElasticsearchEndpoint> void delete(long id,T t){
        ClientResponse response = wr.path(t.getIndex())
                .path(t.getType())
                .path(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .delete(ClientResponse.class);
        ElasticsearchResponseProcessor.delete(response);
    }

}
