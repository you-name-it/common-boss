package test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 〈〉
 *
 * @author HuangQiuRong
 * @create 2019/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class testController {

    /*public static void main(String[] args) throws Exception {

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        HttpResult httpResult = httpClientUtil.doGet("https://toobigdata.com/douyin/user/83668?utm_source=de");

        String html = httpResult.getData();
        Document doc = Jsoup.parse(html);
        Element rows = doc.select("img.round-avatar[src]").first();

        String text = rows.attr("src");

        //哈哈
        System.out.println(text);

    }*/


    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }


}
