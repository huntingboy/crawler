package com.nomad.crawler;

import com.nomad.util.FileStoreUtil;
import com.nomad.util.PropertyUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//登录界面爬虫
public class LoginCrawler {

    public static void login() {
        //第一次请求
        try {
            /*ClassLoader classLoader = LoginCrawler.class.getClassLoader();
            URL url = classLoader.getResource("site.properties");
            File file = new File(url.getFile());
            InputStream inputStream = new FileInputStream(file);*/

            /*ClassLoader classLoader = LoginCrawler.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("site.properties");
            Properties properties = new Properties();
            properties.load(inputStream);*/

            Properties properties = PropertyUtil.getProperties();

            Connection connection = Jsoup.connect(properties.getProperty("url"));
            connection.header("User-Agent", properties.getProperty("User-Agent"));
            Connection.Response response = connection.execute();
            Document document = Jsoup.parse(response.body());
            //form表单
            Elements elements = document.select(properties.getProperty("formId"));
            //post数据
            System.out.println("==========POST请求数据============");
            Map<String, String> datas = new HashMap<>();
            for (Element e :
                    elements.get(0).getAllElements()) {
                if (e.attr("name").equals(properties.getProperty("formUsername"))) {
                    e.attr("value", properties.getProperty("username"));
                }
                if (e.attr("name").equals(properties.getProperty("formPassword"))) {
                    e.attr("value", properties.getProperty("password"));
                }

                //没有name属性的去掉
                if (e.attr("name").length() > 0) {
                    datas.put(e.attr("name"), e.attr("value"));
                    System.out.println(e.attr("name") + ": " + e.attr("value"));
                }
            }


            //有了第一次连接返回的cookie等数据，就可以开始第二次请求
            Connection connection1 = Jsoup.connect(properties.getProperty("url"));
            connection1.header("User-Agent", properties.getProperty("User-Agent"));
            Connection.Response login = connection1.ignoreContentType(true).method(Connection.Method.POST)
                    .data(datas).cookies(response.cookies()).execute();

            System.out.println("===========打印响应体===============");
            System.out.println(login.body());

            //把响应体存到文件
            //把时间戳当文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            FileStoreUtil.fileStore(sdf.format(date) + ".html", login.body());

            System.out.println("===========打印响应Cookies==========");
            Map<String, String> cookies = login.cookies();
            for (String s :
                    cookies.keySet()) {
                System.out.println(s + ": " + cookies.get(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
