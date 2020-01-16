package spring3.upsert.app;

import java.math.BigDecimal;
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

		// Step 1 - Insert into DB new TradeInfo objects every execution of this class
		initializeByCreatingDummyTrades(tradeInfoDAO);

		// Step 2 - Fetch from database current TradeInfo records
		List<TradeInfo> tradeInfos = fetchAllTradeInfos(tradeInfoDAO);

		System.out.println(tradeInfos);

		// Step 3 - Batch UPSERT
		batchUpsertForNewAndFetchedTrades(tradeInfos, tradeInfoDAO);
	}

	private static void initializeDB(ClassPathXmlApplicationContext ctx) {

		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

		// jdbcTemplate.execute(" DROP TABLE trade_info ");

		String sql = "CREATE TABLE trade_info "
				+ "(id INTEGER not NULL, status VARCHAR(25), from_currency VARCHAR(255), "
				+ " to_currency VARCHAR(255), "
				+ " no_of_trades INTEGER,  unit_price decimal(6,2), total_price decimal(6,2), total_discount decimal(6,2) , PRIMARY KEY ( id ))";

		jdbcTemplate.execute(sql);
	}

	private static void initializeByCreatingDummyTrades(TradeInfoDAO tradeInfoDAO) throws Exception {

		TradeInfo tradeInfo;

		// creating 2 dummy TradeInfo objects
		for (int ctr = 1; ctr <= 2; ctr++) {

			tradeInfo = new TradeInfo();

			tradeInfo.setFromCurrency("USD");
			tradeInfo.setToCurrency("JPY");

			tradeInfo.setNoOfTradeUnits(random.nextInt(20));
			tradeInfo.setUnitPrice(BigDecimal.ONE);

			tradeInfo.setTotalPrice(BigDecimal.ONE.multiply(new BigDecimal(tradeInfo.getNoOfTradeUnits())));
			tradeInfoDAO.saveTradeInfo(tradeInfo);
		}
	}

	private static List<TradeInfo> fetchAllTradeInfos(TradeInfoDAO tradeInfoDAO) throws Exception {
		return tradeInfoDAO.findAllTradeInfos();
	}

	private static void batchUpsertForNewAndFetchedTrades(List<TradeInfo> tradeInfos, TradeInfoDAO tradeInfoDAO) {

	}

}
