package spring3.upsert.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import spring.upsert.common.domain.TradeInfo;
import spring3.upsert.app.dao.TradeInfoDAO;
import spring3.upsert.app.dao.TradeInfoDAOImpl;

// https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
// https://mkyong.com/spring/spring-jdbctemplate-batchupdate-example/
// https://hub.docker.com/r/wnameless/oracle-xe-11g-r2

public class Spring3UpsertApp {

	private static Random random = new Random();

	public static void main(String[] args) throws Exception {

		// open/read the application context file
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context-all.xml");

		// initializeDB(ctx);

		TradeInfoDAOImpl tradeInfoDAO = ctx.getBean(TradeInfoDAOImpl.class);

		// Step 1 - Fetch from database current TradeInfo records
		List<TradeInfo> tradeInfos = fetchAllTradeInfos(tradeInfoDAO);

		int noOfTradesInDBNow = tradeInfos.size();

		// Step 1 - Insert into DB new TradeInfo objects every execution of this class
		initializeByCreatingDummyTrades(tradeInfoDAO);

		// Step 3 - Batch UPSERT
		batchUpsertForNewAndFetchedTrades(tradeInfos, tradeInfoDAO);

		tradeInfos = fetchAllTradeInfos(tradeInfoDAO);
		int noOfTradesInDBNew = tradeInfos.size();

		System.out.println("No of Trades created in this execution are " + (noOfTradesInDBNew - noOfTradesInDBNow));
	}

	private static void initializeDB(ClassPathXmlApplicationContext ctx) {

		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

		// jdbcTemplate.execute(" DROP TABLE trade_info ");

		String sql = "CREATE TABLE trade_info "
				+ "(id INTEGER not NULL, status VARCHAR(25), from_currency VARCHAR(255), "
				+ " to_currency VARCHAR(255), "
				+ " no_of_trades INTEGER,  unit_price decimal(6,2), total_price decimal(6,2), total_discount decimal(6,2) , PRIMARY KEY ( id ))";

		// jdbcTemplate.execute(sql);

		String sequenceSql = "CREATE SEQUENCE trade_info_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 "
				+ " START WITH 1 INCREMENT BY 1 CACHE 20 ";

		jdbcTemplate.execute(sequenceSql);
	}

	private static void initializeByCreatingDummyTrades(TradeInfoDAO tradeInfoDAO) throws Exception {

		TradeInfo tradeInfo;

		// creating 2 dummy TradeInfo objects
		for (int ctr = 1; ctr <= 2; ctr++) {

			tradeInfo = createNewTradeInfoObject();

			tradeInfoDAO.saveTradeInfo(tradeInfo);
		}

		System.out.println("Created 2 Trades ");

	}

	private static TradeInfo createNewTradeInfoObject() {
		TradeInfo tradeInfo;
		tradeInfo = new TradeInfo();

		tradeInfo.setFromCurrency("USD");
		tradeInfo.setToCurrency("JPY");

		tradeInfo.setNoOfTradeUnits(random.nextInt(20));
		tradeInfo.setUnitPrice(BigDecimal.ONE);

		tradeInfo.setTotalPrice(BigDecimal.ONE.multiply(new BigDecimal(tradeInfo.getNoOfTradeUnits())));
		return tradeInfo;
	}

	private static List<TradeInfo> fetchAllTradeInfos(TradeInfoDAO tradeInfoDAO) throws Exception {
		List<TradeInfo> tradeInfos = tradeInfoDAO.findAllTradeInfos();

		System.out.println("No of Trades in the DB in the beginning " + tradeInfos.size());

		return tradeInfos;
	}

	private static void batchUpsertForNewAndFetchedTrades(List<TradeInfo> tradeInfos, TradeInfoDAO tradeInfoDAO)
			throws Exception {

		List<TradeInfo> upsertTrades = new ArrayList<>();

		// for insert - create one new record
		TradeInfo newTradeInfo = createNewTradeInfoObject();
		upsertTrades.add(newTradeInfo);

		System.out.println("Created 1 Trade");

		// for update - fetch one random tradeInfo from the tradeInfos and update it
		int noOfTrades = tradeInfos.size();

		if (noOfTrades > 0) {

			int randomTradeIndex = random.nextInt(noOfTrades - 1);

			TradeInfo upsertTradeInfo = tradeInfos.get(randomTradeIndex);
			upsertTradeInfo.setNoOfTradeUnits(random.nextInt(20));

			upsertTrades.add(upsertTradeInfo);

			System.out.println("Updating 1 Trade");
		}

		tradeInfoDAO.batchUpdateTradeInfos(upsertTrades);

	}

}
