package com.nomad.crawler;

import com.nomad.util.FileStoreUtil;
import org.junit.Test;

public class LoginCrawerTest {

    @Test
    public void testLogin() {
        LoginCrawler.login();
        System.out.println("完成");
    }

    @Test
    public void testCrawler() {
        NormalCrawler.crawler();
        System.out.println("完成");
    }

    @Test
    public void testCrawlerWithProxy() {
        NormalCrawler.crawlerWithProxy();
        System.out.println("完成");
    }

    @Test
    public void testFileStore() {
        FileStoreUtil.fileStore("test.txt", "dfad");
    }
}
