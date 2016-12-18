package com.veinhorn.tikiticket.core.impl;

import com.veinhorn.tikiticket.core.Connector;
import com.veinhorn.tikiticket.core.Pair;
import com.veinhorn.tikiticket.core.RequestContext;
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
 */
public class CustomConnector implements Connector {
    private CloseableHttpClient httpClient;

    public CustomConnector(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override public RequestContext doGet(String url) throws IOException {
        final HttpGet httpGet = new HttpGet(url);
        return new RequestContext() {
            @Override public String getHtml() throws IOException {
                return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
            }

            @Override public List<Pair> getHeaders() {
                return null;
            }
        };
    }

    @Override public RequestContext doPost(String url, List<Pair> pairs) throws IOException {
        final HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        if (pairs != null) {
            for (Pair pair : pairs) {
                nvps.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        }
        final CloseableHttpResponse response = httpClient.execute(httpPost);

        return new RequestContext() {
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
