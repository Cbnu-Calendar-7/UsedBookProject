package calendar7.usedbookproject.Service.APIBookSearch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NaverAPIBookSearchService
{
    private final NaverAPIConfig naverAPIConfig;

    public NaverAPIBookSearchService() {
        this.naverAPIConfig = new NaverAPIConfig();
    }

    public List<Book> search(String searchKeyword)
    {
        String text = null;

        text = URLEncoder.encode(searchKeyword, StandardCharsets.UTF_8);

        String apiURL = naverAPIConfig.requestURL + text;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", naverAPIConfig.clientId);
        requestHeaders.put("X-Naver-Client-Secret", naverAPIConfig.clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        // 받아온 결과를 객체로 만든다.
        JSONParser parser = new JSONParser();
        JSONObject obj = null;

        try
        {
            obj = (JSONObject)parser.parse(responseBody);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        JSONArray item = (JSONArray)obj.get("items");

        List<Book> list;
        list = new ArrayList<>();

        for (int i = 0; i < item.size(); i ++)
        {
            Book m = new Book();
            JSONObject tmp = (JSONObject)item.get(i);
            String title = (String)tmp.get("title");
            String image = (String)tmp.get("image");
            String link = (String)tmp.get("link");
            String author = (String)tmp.get("author");
            String publisher = (String)tmp.get("publisher");
            String pubdate = tmp.get("pubdate").toString();
            String price = tmp.get("price").toString();

            // 받아오는 책 제목에서 <b></b> 태그 붙은 것 제거.
            title = title.replace("<b>", "")
                    .replace("</b>", "");

            // 받아오는 출판사 이름에서 <b></b> 태그 붙은 것 제거.
            publisher = publisher.replace("<b>", "")
                    .replace("</b>", "");


            // 10자리 13자리 ISBN으로 분리
            String ISBN = tmp.get("isbn").toString();
            for(String isbn : ISBN.split(" "))
            {
                if(isbn.length() == 10) m.setIsbn10(isbn);
                else if(isbn.length() == 13) m.setIsbn13(isbn);
                else System.out.println("오류");
            }

            m.setTitle(title);
            m.setAuthor(author);
            m.setImage(image);
            m.setLink(link);
            m.setPublisher(publisher);
            m.setPubdate(pubdate);
            m.setPrice(price);
            list.add(m);
        }

//        for (Book movie : list)
//        {
//            System.out.println(movie.getTitle());
//            System.out.println(movie.getPubdate());
//        }
//
//        System.out.println(responseBody);

        return list;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders)
    {
        HttpURLConnection con = connect(apiUrl);
        try
        {
            con.setRequestMethod("GET");

            for(Map.Entry<String, String> header :requestHeaders.entrySet())
            {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if
            (responseCode == HttpURLConnection.HTTP_OK)
            {
                // 정상 호출
                return readBody(con.getInputStream());
            } else
            {
                // 에러 발생
                return readBody(con.getErrorStream());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally
        {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl)
    {
        try
        {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        }
        catch (IOException e)
        {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body)
    {
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader))
        {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null)
            {
                responseBody.append(line);
            }

            return responseBody.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
