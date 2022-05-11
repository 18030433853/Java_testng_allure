import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;



public class wew {

    //传输参数类型map
    public static String post(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        List<NameValuePair> formparams = setHttpParams(paramMap);
        UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPost.setEntity(param);
        HttpResponse response = httpClient.execute(httpPost);
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;

    }

    //传输参数类型json字符串
    public static String post(String url, String data) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(data, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;
    }

    private static String getHttpEntityContent(HttpResponse response) throws IOException{
        HttpEntity  entity = response.getEntity();
        if(entity != null){
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line = br.readLine();
            StringBuilder  sb = new StringBuilder();
            while(line !=null){
                sb.append(line+"\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }


    private static List<NameValuePair> setHttpParams(Map<String,String> paramMap){
        List<NameValuePair> formparams =  new ArrayList<NameValuePair>();
        Set<Map.Entry<String,String>> set = paramMap.entrySet();
        for(Map.Entry<String, String> entry:set){
            formparams.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        return formparams;
    }

}
