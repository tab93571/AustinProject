package ptt;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PTTScraper {

    private static final String BASE_URL = "https://www.ptt.cc/bbs/SportsShop/index.html";
    private static final int NUM_PAGES = 10;
    private static final HashMap<String, Integer> PRODUCT_PRICES = new HashMap<>();

    private static final String[] requiredTitles = {"garmin"};

    private static final String[] inValidTitles = {"徵求","本文已被刪除"};

    static {
        PRODUCT_PRICES.put("955", 12000);
        PRODUCT_PRICES.put("945", 6500);
        PRODUCT_PRICES.put("935", 4500);
        PRODUCT_PRICES.put("255", 9000);
        PRODUCT_PRICES.put("245", 4200);
        PRODUCT_PRICES.put("235", 2000);
    }

    public static void main(String[] args) throws InterruptedException {


        while (true) {

            Connection conn = Jsoup.connect(BASE_URL).cookie("over18", "1");
            try {
                for (int i = 0; i < NUM_PAGES; i++) {
                    Document doc = conn.get();
                    Elements posts = doc.select("div.r-ent");
                    for (Element post : posts) {
                        String title = post.selectFirst("div.title").text().trim();
                        if (shouldSkip(title.toLowerCase())) {
                            continue;
                        }
                        String link = "https://www.ptt.cc" + post.selectFirst("div.title a").attr("href");
                        Connection linkConn = Jsoup.connect(link).cookie("over18", "1");
                        Document linkDoc = linkConn.get();
                        Element content = linkDoc.selectFirst("div[id^=main-content]");

                        String context = content.text();
                        context = context.replaceAll("\\s+", "");


                        String regex = "(?:商品價格|出售金額)：(?:[＄$])?((?:\\d{1,3}[,.]?)*\\d{1,}(?:\\.\\d{2})?)元?";

                        Pattern pattern = Pattern.compile(regex);

                        Matcher matcher = pattern.matcher(context);
                        if (matcher.find()) {
                            String matchedPrice = matcher.group(1).replaceAll("[,.]", "");

                            int price = Integer.parseInt(matchedPrice);


                            String productId = getProductID(title);
                            int maxPrice = PRODUCT_PRICES.get(productId);
                            if (price <= maxPrice) {
                                System.out.println("Title: " + title);
                                System.out.println("Link: " + link);
                                System.out.println("Price: " + price);
                            }
                        } else {
                            System.out.println("Title: " + title);
                            System.out.println("Link: " + link);
                            System.out.println("Price not found");
                        }
                    }

                    String prevPageUrl = "https://www.ptt.cc" + doc.selectFirst("a:containsOwn(‹ 上頁)").attr("href");
                    conn = Jsoup.connect(prevPageUrl).cookie("over18", "1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("done");

            Thread.sleep(1800000);

        }
    }

    private static boolean isInvalidTitle(String title) {
        for(String keyWord : inValidTitles) {
            if(title.contains(keyWord)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidTitle(String title) {
        for(String keyWord : requiredTitles) {
            if(!title.contains(keyWord)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isProductTitle(String title) {
        return PRODUCT_PRICES.keySet().stream().anyMatch(title::contains);
    }

    public static boolean shouldSkip(String title) {
        return isInvalidTitle(title) || !isProductTitle(title) || !isValidTitle(title);
    }

    public static String getProductID(String title) {
        for (String productId : PRODUCT_PRICES.keySet()) {
            if (title.contains(productId)) {
                return productId;
            }
        }
        return null;
    }
}
