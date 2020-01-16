package spring3.upsert.app.service;

import java.util.List;

import spring.upsert.common.domain.TradeInfo;

public interface TradeInfoService {

	int[][] batchUpdateTradeInfos(List<TradeInfo> tradeInfos) throws Exception;

	TradeInfo createTrade(TradeInfo tradeInfo) throws Exception;

	TradeInfo updateTrade(TradeInfo tradeInfo) throws Exception;

	TradeInfo upsert(TradeInfo tradeInfo) throws Exception;

}
