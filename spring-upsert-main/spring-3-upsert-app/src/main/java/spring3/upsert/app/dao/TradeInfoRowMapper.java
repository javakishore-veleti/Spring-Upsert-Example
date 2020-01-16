package spring3.upsert.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.upsert.common.domain.TradeInfo;

public class TradeInfoRowMapper implements RowMapper<TradeInfo> {

	@Override
	public TradeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		TradeInfo tradeInfo = new TradeInfo();

		tradeInfo.setId(rs.getLong("id"));
		tradeInfo.setCustomerId(rs.getLong("customer_id"));
		tradeInfo.setFromCurrency(rs.getString("from_currency"));
		tradeInfo.setToCurrency(rs.getString("to_currency"));
		tradeInfo.setStatus(rs.getString("status"));

		return tradeInfo;
	}

}
