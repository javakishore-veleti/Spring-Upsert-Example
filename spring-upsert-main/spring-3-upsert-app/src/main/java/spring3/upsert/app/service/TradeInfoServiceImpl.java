package spring3.upsert.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.upsert.common.domain.TradeInfo;
import spring3.upsert.app.dao.TradeInfoDAO;

@Component
public class TradeInfoServiceImpl implements TradeInfoService {

	@Autowired
	private TradeInfoDAO tradeInfoDAO;

	@Override
	public int[][] batchUpdateTradeInfos(List<TradeInfo> tradeInfos) throws Exception {
		return this.tradeInfoDAO.batchUpdateTradeInfos(tradeInfos);
	}

	@Override
	public TradeInfo createTrade(TradeInfo tradeInfo) throws Exception {
		return this.createTrade(tradeInfo);
	}

	@Override
	public TradeInfo updateTrade(TradeInfo tradeInfo) throws Exception {
		return this.updateTrade(tradeInfo);
	}

	@Override
	public TradeInfo upsert(TradeInfo tradeInfo) throws Exception {
		return this.upsert(tradeInfo);
	}

}
