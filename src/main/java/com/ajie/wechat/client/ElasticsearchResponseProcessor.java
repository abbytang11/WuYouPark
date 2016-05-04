package com.ajie.wechat.client;

import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ElasticsearchResponseProcessor {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchResponseProcessor.class);
	private ElasticsearchResponseProcessor(){
		//hide the constructor
	}
	public static void indexing(ClientResponse response) {
		int status = response.getStatus();
		if (status < 200 || status >= 300) {
			String msg = response.getEntity(String.class);
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	public static void delete(ClientResponse response) {
		int status = response.getStatus();
		if (status < 200 || status >= 300) {
			String msg = response.getEntity(String.class);
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}

}
