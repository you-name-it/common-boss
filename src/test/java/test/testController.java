package test;

import com.common.utils.HttpClientUtil;
import com.common.utils.HttpResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 〈〉
 *
 * @author HuangQiuRong
 * @create 2019/3/26
 */
public class testController {

    public static void main(String[] args) throws Exception {

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        HttpResult httpResult = httpClientUtil.doGet("https://toobigdata.com/douyin/user/83668?utm_source=de");

        String html = httpResult.getData();
        Document doc = Jsoup.parse(html);
        Element rows = doc.select("img.round-avatar[src]").first();

        String text = rows.attr("src");

        System.out.println(text);

    }

}
