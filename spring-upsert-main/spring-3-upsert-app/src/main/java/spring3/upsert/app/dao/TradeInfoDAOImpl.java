package spring3.upsert.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.upsert.common.domain.TradeInfo;

@Repository
public class TradeInfoDAOImpl implements TradeInfoDAO {

	private static final String TRADE_INFO_INSERT_STR = " INSERT INTO trade_info (from_currency, to_currency, no_of_trades, unit_price, "
			+ "total_price, total_discount) VALUES ( ?, ?, ?, ?, ?, ?) ";

	private static final String TRADE_INFO_DELETE_ALL_SQL_STR = "DELETE FROM trade_info ";

	private static final String TRADE_INFO_FETCH_ALL_SQL_STR = "SELECT id, status, from_currency, to_currency, no_of_trades, unit_price, "
			+ " total_price, total_discount FROM trade_info ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[][] batchUpdateTradeInfos(List<TradeInfo> tradeInfos) throws Exception {

		int[][] updateCounts = jdbcTemplate.batchUpdate(TRADE_INFO_INSERT_STR, tradeInfos, tradeInfos.size(),

				new ParameterizedPreparedStatementSetter<TradeInfo>() {

					public void setValues(PreparedStatement ps, TradeInfo tradeInfo) throws SQLException {
						ps.setString(1, tradeInfo.getFromCurrency());
						ps.setString(2, tradeInfo.getToCurrency());
						ps.setInt(3, tradeInfo.getNoOfTradeUnits());
						ps.setBigDecimal(4, tradeInfo.getUnitPrice());
						ps.setBigDecimal(5, tradeInfo.getTotalPrice());
						ps.setBigDecimal(6, tradeInfo.getTotalDiscount());
					}
				});

		System.out.println(updateCounts);
		return updateCounts;
	}

	@Override
	public TradeInfo saveTradeInfo(TradeInfo tradeInfo) throws Exception {

		List<TradeInfo> tradeInfos = new ArrayList<>();

		batchUpdateTradeInfos(tradeInfos);

		return tradeInfo;
	}

	@Override
	public TradeInfo findTradeInfoById(Long tradeInfoId) throws Exception {
		return null;
	}

	@Override
	public List<TradeInfo> findAllTradeInfos() throws Exception {

		List<TradeInfo> tradeInfos = jdbcTemplate.query(TRADE_INFO_FETCH_ALL_SQL_STR, new TradeInfoRowMapper());

		return tradeInfos;
	}

	@Override
	public TradeInfo updateTradeInfo(TradeInfo tradeInfo) throws Exception {
		return null;
	}

	@Override
	public TradeInfo upSertradeInfo(TradeInfo tradeInfo) throws Exception {
		return null;
	}

	@Override
	public void deleteTradeInfo(Long tradeInfoId) throws Exception {

	}

	@Override
	public int deleteAllTrades() throws Exception {

		int rowsDeleted = jdbcTemplate.update(TRADE_INFO_DELETE_ALL_SQL_STR);

		return rowsDeleted;
	}

}
