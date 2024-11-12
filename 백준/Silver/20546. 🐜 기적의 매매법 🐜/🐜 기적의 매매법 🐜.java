import java.io.*;
import java.util.*;

public class Main {
    interface Strategy {
        void buy(StockAccount stockAccount, int today);

        void sell(StockAccount stockAccount, int today);
    }

    static class StockAccount {
        private int money; // 보유 자산
        private int stock; // 보유 주식
        private final int[] stockPrices;
        private final Strategy strategy;

        public StockAccount(int money, int stock, int[] stockPrices, Strategy strategy) {
            this.money = money;
            this.stock = stock;
            this.stockPrices = stockPrices;
            this.strategy = strategy;
        }

        public void buy(int day) {
            strategy.buy(this, day);
        }

        public void sell(int day) {
            strategy.sell(this, day);
        }

        public int getProfit(int day) {
            return money + stockPrices[day] * stock;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int money;
    static int M = 14;
    static int[] stockPrices;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        money = Integer.parseInt(st.nextToken());
        stockPrices = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            stockPrices[i] = Integer.parseInt(st.nextToken());
        }

        StockAccount junStockAccount = new StockAccount(money, 0, stockPrices, new Strategy() {
            @Override
            public void buy(StockAccount stockAccount, int today) {
                int todayPrice = stockAccount.stockPrices[today];
                int stock = stockAccount.money / todayPrice;
                stockAccount.money -= stock * todayPrice;
                stockAccount.stock += stock;
            }

            @Override
            public void sell(StockAccount stockAccount, int today) {
            }
        });

        StockAccount seoungStockAccount = new StockAccount(money, 0, stockPrices, new Strategy() {
            @Override
            public void buy(StockAccount stockAccount, int today) {
                if (today < 3) {
                    return;
                }

                for (int prev = today - 3; prev < today - 1; prev++) {
                    if (stockAccount.stockPrices[prev] <= stockAccount.stockPrices[prev + 1]) {
                        return;
                    }
                }

                int stock = stockAccount.money / stockAccount.stockPrices[today];
                stockAccount.money -= stock * stockAccount.stockPrices[today];
                stockAccount.stock += stock;
            }

            @Override
            public void sell(StockAccount stockAccount, int today) {
                if (today < 3) {
                    return;
                }

                for (int prev = today - 3; prev < today - 1; prev++) {
                    if (stockAccount.stockPrices[prev] >= stockAccount.stockPrices[prev + 1]) {
                        return;
                    }
                }
                stockAccount.money += stockAccount.stock * stockAccount.stockPrices[today];
                stockAccount.stock = 0;
            }
        });

        for (int day = 0; day < M; day++) {
            junStockAccount.buy(day);
            junStockAccount.sell(day);
            seoungStockAccount.buy(day);
            seoungStockAccount.sell(day);
        }

        int junStockAccountProfit = junStockAccount.getProfit(M - 1);
        int seoungStockAccountProfit = seoungStockAccount.getProfit(M - 1);

        if (junStockAccountProfit > seoungStockAccountProfit) {
            System.out.println("BNP");
        } else if (junStockAccountProfit < seoungStockAccountProfit) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}