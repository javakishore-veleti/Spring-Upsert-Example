package spring3.upsert.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.upsert.common.domain.TradeInfo;

@Repository
public class TradeInfoDAOImpl implements TradeInfoDAO {

	// https://stackoverflow.com/questions/17097818/basic-merge-into-same-table
	/*
	 * "This is a good simple example because, if you're only using one table, you have to use the select 1 from dual and in the ON, ".....
	 */
	private static final String TRADE_INFO_INSERT_STR = " MERGE INTO trade_info TRADEINFO_TBL USING "
			+ "  	(SELECT 1 FROM DUAL) E  ON (TRADEINFO_TBL.id = ? )  " + " 	WHEN MATCHED THEN  "
			+ " 		UPDATE SET " + "				TRADEINFO_TBL.from_currency = ? , "
			+ "				TRADEINFO_TBL.to_currency = ?, " + "				TRADEINFO_TBL.no_of_trades = ?  "
			+ " 	WHEN NOT MATCHED THEN  "
			+ " 		INSERT  (TRADEINFO_TBL.id, TRADEINFO_TBL.from_currency, TRADEINFO_TBL.to_currency, TRADEINFO_TBL.no_of_trades, TRADEINFO_TBL.unit_price, "
			+ "				TRADEINFO_TBL.total_price, TRADEINFO_TBL.total_discount ) "
			+ " 		VALUES (trade_info_seq.NEXTVAL, ?, ?, ?, ?, ?, ?) ";

	private static final String TRADE_INFO_DELETE_ALL_SQL_STR = "DELETE FROM trade_info ";

	private static final String TRADE_INFO_FETCH_ALL_SQL_STR = "SELECT id,  from_currency, to_currency, no_of_trades, unit_price, "
			+ " total_price, total_discount FROM trade_info ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[][] batchUpdateTradeInfos(List<TradeInfo> tradeInfos) throws Exception {

		int[][] updateCounts = jdbcTemplate.batchUpdate(TRADE_INFO_INSERT_STR, tradeInfos, tradeInfos.size(),

				new ParameterizedPreparedStatementSetter<TradeInfo>() {

					public void setValues(PreparedStatement ps, TradeInfo tradeInfo) throws SQLException {

						if (tradeInfo.getId() == null || tradeInfo.getId() < 1) {

							// ps.setNull(1, Types.BIGINT);
							ps.setLong(1, -100L);

						} else {

							ps.setLong(1, tradeInfo.getId());
						}

						ps.setString(2, tradeInfo.getFromCurrency());
						ps.setString(3, tradeInfo.getToCurrency());
						ps.setInt(4, tradeInfo.getNoOfTradeUnits());

						ps.setString(5, tradeInfo.getFromCurrency());
						ps.setString(6, tradeInfo.getToCurrency());
						ps.setInt(7, tradeInfo.getNoOfTradeUnits());
						ps.setBigDecimal(8, tradeInfo.getUnitPrice());
						ps.setBigDecimal(9, tradeInfo.getTotalPrice());
						ps.setBigDecimal(10, tradeInfo.getTotalDiscount());
					}
				});

		return updateCounts;
	}

	@Override
	public TradeInfo saveTradeInfo(TradeInfo tradeInfo) throws Exception {

		List<TradeInfo> tradeInfos = new ArrayList<>();
		tradeInfos.add(tradeInfo);

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
