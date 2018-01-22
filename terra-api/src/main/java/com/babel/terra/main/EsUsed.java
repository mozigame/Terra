package com.babel.terra.main;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * User: joey
 * Date: 2018/1/19
 * Time: 18:13
 */
public class EsUsed {

    public static void main(String[] args) {
        TransportClient client = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "my-es-dogs").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.213"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.223"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.220"), 9300));
            System.out.println(client.nodeName());
            GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
            System.out.println(response.getFields());
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

// on shutdown
    }
}
