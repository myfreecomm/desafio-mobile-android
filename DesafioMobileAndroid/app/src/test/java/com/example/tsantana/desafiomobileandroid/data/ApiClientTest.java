package com.example.tsantana.desafiomobileandroid.data;




import org.junit.Test;

import com.example.tsantana.desafiomobileandroid.data.ApiClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static junit.framework.Assert.*;


/**
 * Created by tsantana on 06/12/2017.
 */

// voltar nisso depois
public class ApiClientTest  {
    private MockWebServer mServer;
    private ApiClient apiClient;

    @Test
    public void testGetJson() throws Exception{
        mServer = new MockWebServer();
        mServer.enqueue(new MockResponse().setResponseCode(200).setBody("total:2323"));

        mServer.start();
        apiClient = new ApiClient(mServer.url("/").toString());

        String out = apiClient.getJson();
        assertNotNull(out);

        assertEquals("total:2323\n",out);

        RecordedRequest request = mServer.takeRequest();
        assertEquals("/", request.getPath());
        assertEquals("GET", request.getMethod());

        apiClient = new ApiClient(null);
        assertNotNull(apiClient.getJson());

        mServer.shutdown();

    }
}
