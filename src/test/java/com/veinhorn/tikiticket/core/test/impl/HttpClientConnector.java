package com.veinhorn.tikiticket.core.test.impl;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.context.ContextHolder;
import com.veinhorn.tikiticket.core.util.Pair;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 17.12.16.
 * It's a simple implementation of IConnector powered by Apache Http Client
 * Every connector instance should include context holder
 */
public class HttpClientConnector implements IConnector {
    private CloseableHttpClient httpClient;
    private ContextHolder contextHolder;

    public HttpClientConnector(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
        contextHolder = new ContextHolder();
    }

    @Override
    public ContextHolder getContextHolder() {
        return contextHolder;
    }

    @Override
    public ICredentials getCredentials() {
        return new PropertyCredentials();
    }

    @Override
    public ResponseContext doGet(String url) throws IOException {
        final HttpGet httpGet = new HttpGet(url);
        return new ResponseContext() {
            @Override public String getHtml() throws IOException {
                return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
            }

            @Override public List<Pair> getHeaders() {
                return null;
            }
        };
    }

    @Override
    public ResponseContext doPost(String url, List<Pair> pairs) throws IOException {
        final HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        if (pairs != null) {
            for (Pair pair : pairs) {
                nvps.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        }
        final CloseableHttpResponse response = httpClient.execute(httpPost);

        return new ResponseContext() {
            @Override public String getHtml() throws IOException {
                return EntityUtils.toString(response.getEntity());
            }

            // TODO: Maybe some error
            @Override public List<Pair> getHeaders() {
                Header[] headers = response.getAllHeaders();
                if (headers.length > 0) {
                    List<Pair> pairs = new ArrayList<>();
                    for (Header header : response.getAllHeaders()) {
                        pairs.add(new Pair(header.getName(), header.getValue()));
                    }
                    return pairs;
                }
                return null;
            }
        };
    }
}
