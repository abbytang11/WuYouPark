package com.ajie.wechat.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.client.apache4.config.ApacheHttpClient4Config;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

class JerseyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyClient.class);
    
    private String host;
    private Client client;

    public JerseyClient(String host) {
        this.host = host;
        this.client = buildClient();
    }

    public WebResource buildWebResource(){
        return this.client.resource(this.host);
    }

    private static Client buildClient() {
        ObjectMapper mapper = new ObjectMapper();
        return buildClient(mapper);
    }

    private static Client buildClient(ObjectMapper mapper) {
        ClientConfig cc = new DefaultApacheHttpClient4Config();
        cc.getSingletons().add(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));

        cc.getProperties().put(ApacheHttpClient4Config.PROPERTY_CONNECTION_MANAGER, createConnectionManager());

        Client c = Client.create(cc);
        
        if (LOGGER.isDebugEnabled()) {
            c.addFilter(new LoggingFilter(System.out)); // NOSONAR, have to use System.out for debugging purpose
        }
        
        return c;
    }

    private static HttpClientConnectionManager createConnectionManager() {
        PoolingHttpClientConnectionManager m = new PoolingHttpClientConnectionManager(1, TimeUnit.HOURS);
        m.setDefaultMaxPerRoute(1024);
        m.setMaxTotal(1024);

        return m;
    }
}