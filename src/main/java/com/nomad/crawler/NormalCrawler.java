package com.nomad.crawler;

import com.nomad.util.FileStoreUtil;
import com.nomad.util.PropertyUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//无需验证 普通页面爬虫
public class NormalCrawler {

    //无代理
    public static void crawler() {
        Properties properties = PropertyUtil.getProperties();

        Connection connection = Jsoup.connect(properties.getProperty("url"));
        connection.header("User-Agent", properties.getProperty("User-Agent"));
        try {
            Connection.Response response = connection.method(Connection.Method.GET).timeout(50000).execute();
            System.out.println("========打印响应体==========");
            System.out.println(response.body());

            //把响应体存到文件
            //把时间戳当文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            FileStoreUtil.fileStore(sdf.format(date) + ".html", response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //有代理
    public static void crawlerWithProxy() {
        Properties properties = PropertyUtil.getProperties();

        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080));

        try {
            URL url = new URL(properties.getProperty("url"));
            URLConnection conn = url.openConnection(proxy);
            //获取连接
            conn.connect();

            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            //Document doc = Jsoup.parse(sb.toString());
            System.out.println("========打印响应体==========");
            System.out.println(sb.toString());

            //把响应体存到文件
            //把时间戳当文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            FileStoreUtil.fileStore(sdf.format(date) + ".html", sb.toString());

            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
