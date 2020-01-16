package spring3.upsert.app.dao;

import java.util.List;

import spring.upsert.common.domain.TradeInfo;

public interface TradeInfoDAO {

	int[][] batchUpdateTradeInfos(List<TradeInfo> tradeInfos) throws Exception;

	TradeInfo saveTradeInfo(TradeInfo tradeInfo) throws Exception;

	TradeInfo findTradeInfoById(Long tradeInfoId) throws Exception;

	List<TradeInfo> findAllTradeInfos() throws Exception;

	TradeInfo updateTradeInfo(TradeInfo tradeInfo) throws Exception;

	TradeInfo upSertradeInfo(TradeInfo tradeInfo) throws Exception;

	void deleteTradeInfo(Long tradeInfoId) throws Exception;

	int deleteAllTrades() throws Exception;

}
