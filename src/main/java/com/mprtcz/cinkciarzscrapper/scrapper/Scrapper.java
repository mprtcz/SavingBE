package com.mprtcz.cinkciarzscrapper.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Scrapper {

  private static final String URL_1_UNIT = "https://conotoxia" +
      ".com/wa/pe/top4?subscriptionId=TOP4&unit=10";

  public static List<Rate> getRates() {
    return getTable(getCinkciarzRatesPage());
  }

  private static Document getCinkciarzRatesPage() {
    var url = URL_1_UNIT;
    try {
      return Jsoup.connect(url).ignoreContentType(true).get();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not connect to the website " + url);
    }
  }

  private static List<Rate> getTable(Document document) {
    Elements tableBody = document.getElementsByTag("tbody");
    List<Rate> rates = new ArrayList<>();
    for (Element row : tableBody.select("tr")) {
      Rate.RateBuilder rate = Rate.builder();
      Elements tds = row.select("td");

      addRateString(tds.get(0), rate);
      addSellPrice(tds.get(2), rate);
      addBuyPrice(tds.get(3), rate);

      rates.add(rate.build());
    }
    return List.copyOf(rates);
  }

  private static void addRateString(Element element, Rate.RateBuilder rate) {
    String rateId = element.getElementsByTag("a").attr("href");
    rate.rateId(rateId);
  }

  private static void addSellPrice(Element element, Rate.RateBuilder rate) {
    String sellPriceString = element.select("a > span > span").text();
    rate.sellPrice(Double.parseDouble(sellPriceString));
  }

  private static void addBuyPrice(Element element, Rate.RateBuilder rate) {
    String buyPriceString = element.select("a > span > span").text();
    rate.buyPrice(Double.parseDouble(buyPriceString));
  }
}
