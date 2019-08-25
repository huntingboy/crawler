# crawler
## 爬虫demo

### 登录爬虫
1. 分析页面结构(f12 form标签 输入框信息)
2. 设置post数据（比如user-agent, cookie, data:username/password）
3. 发起第二次请求，打印/存储response

### 普通页面爬虫(代理版和非代理版)
1. 设置header:User-Agent
2. 发起method:get请求，打印/存储response

---
> **requirement**
>> Jsoup, JUnit4

> **notes**
>> *.class.getResource("").getPath(); //  */crawler/target/classes/com/nomad/util/  
>> *.class.getResource("/").getPath(); // */crawler/target/test-classes/     
>> *.class.getClassLoader().getResource("").getPath(); //  */crawler/target/test-classes/  
>> *.class.getClassLoader().getResource("/").getPath(); // error  
