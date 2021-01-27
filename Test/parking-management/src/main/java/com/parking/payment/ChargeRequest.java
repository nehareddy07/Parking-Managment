package com.parking.payment;

public class ChargeRequest {

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setStripeEmail(String stripeEmail) {
		this.stripeEmail = stripeEmail;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	public enum Currency {
		EUR, USD;
	}

	private String description;
	private int amount; // cents
	private Currency currency;
	private String stripeEmail;
	private String stripeToken;
	private Long entryId;
	private Long userId;

	public String getDescription() {
		return description;
	}

	public int getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getStripeEmail() {
		return stripeEmail;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}