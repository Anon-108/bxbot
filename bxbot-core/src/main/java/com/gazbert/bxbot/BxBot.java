/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Gareth Jon Lynch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gazbert.bxbot;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.trade.AccountInformation;
import com.gazbert.bxbot.core.engine.TradingEngine;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import org.knowm.xchange.Exchange;
//import org.knowm.xchange.ExchangeFactory;
//import org.knowm.xchange.ExchangeSpecification;
//import org.knowm.xchange.binance.BinanceExchange;
//import org.knowm.xchange.currency.Currency;
//import org.knowm.xchange.dto.account.AccountInfo;
//import org.knowm.xchange.dto.account.Balance;
//import org.knowm.xchange.dto.account.Wallet;
////import org.knowm.xchange.examples.bitfinex.BitfinexDemoUtils;
//import org.knowm.xchange.service.account.AccountService;
//import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.ta4j.core.*;
//import org.ta4j.core.analysis.criteria.ReturnOverMaxDrawdownCriterion;
//import org.ta4j.core.analysis.criteria.VersusBuyAndHoldCriterion;
//import org.ta4j.core.analysis.criteria.WinningPositionsRatioCriterion;
//import org.ta4j.core.analysis.criteria.pnl.GrossReturnCriterion;
//import org.ta4j.core.indicators.SMAIndicator;
//import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
//import org.ta4j.core.num.Num;
//import org.ta4j.core.rules.CrossedDownIndicatorRule;
//import org.ta4j.core.rules.CrossedUpIndicatorRule;
//import org.ta4j.core.rules.StopGainRule;
//import org.ta4j.core.rules.StopLossRule;
//import ta4jexamples.loaders.CsvTradesLoader;

import java.io.IOException;

/**
 * BX-bot app.
 *
 * @author gazbert
 */
@SpringBootApplication
@SecurityScheme(
    name = "Authorization",
    scheme = "Bearer",
    type = SecuritySchemeType.APIKEY,
    in = SecuritySchemeIn.HEADER)
public class BxBot implements CommandLineRunner {

  private final TradingEngine tradingEngine;

  /**
   * 市场服务
   */
//  static MarketDataService marketService;

  @Autowired
  public BxBot(TradingEngine tradingEngine) {
    this.tradingEngine = tradingEngine;
  }

  public static void main(String[] args) {
    SpringApplication.run(BxBot.class);
  }

  @Override
  public void run(String... strings) throws IOException, InterruptedException {
    // TODO 请求代理接口  仅在本地测试开启
    System.setProperty("proxyType", "4");
    System.setProperty("proxyPort", Integer.toString(10809));
    System.setProperty("proxyHost", "127.0.0.1");
    System.setProperty("proxySet", "true");

//    tradingEngine.start(); //TODO 原生方法
    tradingEngine.startTrade();

//    Exchange bfx = BitfinexDemoUtils.createExchange();
//
//    BitfinexTradeServiceRaw tradeService = (BitfinexTradeServiceRaw) bfx.getTradeService();
//
//    Date tenDaysAgo =
//            Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
//    BitfinexFundingTradeResponse[] fundingTradeResponses =
//            tradeService.getBitfinexFundingHistory("USD", tenDaysAgo, 2000);
//    testBitfinex();
//    test2();
//    testBinance();
//    testBinanceSDK();


  }
  public void  testBinanceSDK(){
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient = SyncRequestClient.create("apiKey",
            "secretKey",
            options);
    System.out.println("========================================================");
    AccountInformation balance = syncRequestClient.getAccountInformation();
    Gson gson = new Gson();
    String json = gson.toJson(balance);

    System.out.println(json);

  }

  public void  testBitfinex() throws IOException, InterruptedException {
//    bfxSpec.setApiKey("1ObRGaVgwfcaYwebE0ave1ACiFzQs51evimKHCjEBj3");
//    bfxSpec.setSecretKey("WLYzLXkgbmr3RnKx2OxHqVUVWyyKM9kK6IlrEDflEh8");

//    Exchange bfx = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
//    ExchangeSpecification bfxSpec = bfx.getDefaultExchangeSpecification();
//    bfx.applySpecification(bfxSpec);


//    Exchange bfx = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
//    boolean flag = true;
//    while (flag) {
//      MarketDataService marketDataService = bfx.getMarketDataService();
//      Ticker ticker = marketDataService.getTicker(CurrencyPair.EOS_USD);
//      Calendar calendar = Calendar.getInstance();
//      System.out.println("===当前信息("+calendar.getTime()+")===："+ticker.toString());
//      Thread.sleep(10000);
//    }



  }

//  public void  testBinance() throws IOException, InterruptedException {
//    /*公告交换器*/
////    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class);
////
////    BinanceMarketDataService service = (BinanceMarketDataService) exchange.getMarketDataService();
////    Ticker ticker1 = service.getTicker(CurrencyPair.EOS_USDT);
////    //获取15分钟
////
////    List<BinanceKline> klines15m = service.klines(CurrencyPair.EOS_USDT, KlineInterval.m15, 5,null,null);
////
////    System.out.println(ticker1);
////    klines15m.forEach(item -> {
////      System.out.println(item);
////    });
//
//    /*私有交换器*/
//    ExchangeSpecification dExSp = new BinanceExchange().getDefaultExchangeSpecification();
//    Exchange binance = ExchangeFactory.INSTANCE.createExchange(dExSp);
////    TradeService tradeService = binance.getTradeService();
//    AccountService accountService = binance.getAccountService();
//    AccountInfo accountInfo = accountService.getAccountInfo();
//    String username = accountInfo.getUsername();
////    Map<String, Wallet> wallet = accountInfo.getWallets();
//    Wallet wallet = accountInfo.getWallet();
//    Balance balanceBNB = wallet.getBalance(Currency.BNB);
//    Balance balanceVTHO = wallet.getBalance(Currency.VTHO);
//    Balance balanceIOTA = wallet.getBalance(Currency.IOTA);
//    Balance balanceUSDT = wallet.getBalance(Currency.USDT);
//
//    System.out.println("balanceBNB"+balanceBNB);
//    System.out.println("balanceVTHO:"+balanceVTHO);
//    System.out.println("balanceIOTA:"+balanceIOTA);
//    System.out.println("balanceUSDT:"+balanceUSDT);
//
//    System.out.println("币安userName："+username);
//    System.out.println("wallet："+ wallet);
//
//  }
//  public static void testTa4j() {
//
//    // Getting a bar series (from any provider: CSV, web service, etc.)
//    // 获取条形系列（来自任何提供者：CSV、Web 服务等）
//    BarSeries series = CsvTradesLoader.loadBitstampSeries();
//
//    // Getting the close price of the bars
//    // 获取柱的收盘价
//    Num firstClosePrice = series.getBar(0).getClosePrice();
//    System.out.println("First close price 第一收盘价: " + firstClosePrice.doubleValue());
//    // Or within an indicator:
//    // 或在指标内：
//    ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
//    // Here is the same close price:
//    // 这是相同的收盘价：
//    System.out.println(firstClosePrice.isEqual(closePrice.getValue(0))); // equal to firstClosePrice 等于 firstClosePrice
//
//    // Getting the simple moving average (SMA) of the close price over the last 5 bars
//    // 获取最近 5 根柱线收盘价的简单移动平均线 (SMA)
//    SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
//    // Here is the 5-bars-SMA value at the 42nd index
//    // 这是第 42 个指数的 5 根 SMA 值
//    System.out.println("5-bars-SMA value at the 42nd index 第 42 个指数的 5 条 SMA 值: " + shortSma.getValue(42).doubleValue());
//
//    // Getting a longer SMA (e.g. over the 30 last bars)
//    // 获得更长的 SMA（例如，超过 30 个最后一根柱线）
//    SMAIndicator longSma = new SMAIndicator(closePrice, 30);
//
//    // Ok, now let's building our trading rules!
//    // 好的，现在让我们建立我们的交易规则！
//
//    // Buying rules
//    // 购买规则
//    // We want to buy:
//    // 我们要购买：
//    // - if the 5-bars SMA crosses over 30-bars SMA
//    // - 如果 5 根 SMA 跨越 30 根 SMA
//    // - or if the price goes below a defined price (e.g $800.00)
//    // - 或者如果价格低于定义的价格（例如 $800.00）
//    Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma)
//            .or(new CrossedDownIndicatorRule(closePrice, 800));
//
//    // Selling rules
//    // 销售规则
//    // We want to sell:
//    // 我们要出售：
//        /*
//        - if the 5-bars SMA crosses under 30-bars SMA
//         - or if the price loses more than 3%
//         - or if the price earns more than 2%
//         - 如果 5 根 SMA 下穿 30 根 SMA
//          - 或者如果价格下跌超过 3%
//          - 或者如果价格收益超过 2%
//         */
//
//    Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma)
//            .or(new StopLossRule(closePrice, series.numOf(3))).or(new StopGainRule(closePrice, series.numOf(2)));
//
//    // Running our juicy trading strategy...
//    // 运行我们多汁的交易策略...
//    BarSeriesManager seriesManager = new BarSeriesManager(series);
//    TradingRecord tradingRecord = seriesManager.run(new BaseStrategy(buyingRule, sellingRule));
//    System.out.println("Number of positions for our strategy 我们战略的倉位数量: " + tradingRecord.getPositionCount());
//
//    // Analysis
//    // 分析
//
//    // Getting the winning positions ratio
//    // 获取胜率
//    AnalysisCriterion winningPositionsRatio = new WinningPositionsRatioCriterion();
//    System.out.println("Winning positions ratio 胜率: " + winningPositionsRatio.calculate(series, tradingRecord));
//    // Getting a risk-reward ratio
//    // 获取风险回报率
//    AnalysisCriterion romad = new ReturnOverMaxDrawdownCriterion();
//    System.out.println("Return over Max Drawdown 返回最大回撤: " + romad.calculate(series, tradingRecord));
//
//    // Total return of our strategy vs total return of a buy-and-hold strategy
//    // 我们策略的总回报与买入并持有策略的总回报
//    AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new GrossReturnCriterion());
//    System.out.println("Our return vs buy-and-hold return 我们的回报与买入并持有回报: " + vsBuyAndHold.calculate(series, tradingRecord));
//
//    // Your turn!
//    // 到你了！
//  }
}
