package spring.upsert.common.domain;

import java.math.BigDecimal;

public class TradeInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String fromCurrency;
	private String toCurrency;

	private Integer noOfTradeUnits;

	private BigDecimal unitPrice;
	private BigDecimal tradeAmount;
	private BigDecimal totalPrice;
	private BigDecimal totalDiscount;

	public TradeInfo() {
		super();
	}

	public TradeInfo(String fromCurrency, String toCurrency, Integer noOfTradeUnits, BigDecimal unitPrice,
			BigDecimal tradeAmount, BigDecimal totalPrice, BigDecimal totalDiscount) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.noOfTradeUnits = noOfTradeUnits;
		this.unitPrice = unitPrice;
		this.tradeAmount = tradeAmount;
		this.totalPrice = totalPrice;
		this.totalDiscount = totalDiscount;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Integer getNoOfTradeUnits() {
		return noOfTradeUnits;
	}

	public void setNoOfTradeUnits(Integer noOfTradeUnits) {
		this.noOfTradeUnits = noOfTradeUnits;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	@Override
	public String toString() {
		return "TradeInfo [fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + ", noOfTradeUnits="
				+ noOfTradeUnits + ", unitPrice=" + unitPrice + ", tradeAmount=" + tradeAmount + ", totalPrice="
				+ totalPrice + ", totalDiscount=" + totalDiscount + ", toString()=" + super.toString() + "]";
	}

}
