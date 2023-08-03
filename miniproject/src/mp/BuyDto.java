package day_0803.dao;

public class BuyDto {

	int BUY_NUM;
	String PAY_ID;
	String PRODUCT_ID;
	int PRODUCT_PRICE;
	int BUY_QUANTITY;
	int BUY_PRICE;
	public BuyDto() {
		// TODO Auto-generated constructor stub
	}
	public BuyDto(int bUY_NUM, String pAY_ID, String pRODUCT_ID, int pRODUCT_PRICE, int bUY_QUANTITY, int bUY_PRICE) {
		super();
		BUY_NUM = bUY_NUM;
		PAY_ID = pAY_ID;
		PRODUCT_ID = pRODUCT_ID;
		PRODUCT_PRICE = pRODUCT_PRICE;
		BUY_QUANTITY = bUY_QUANTITY;
		BUY_PRICE = bUY_PRICE;
	}
	public int getBUY_NUM() {
		return BUY_NUM;
	}
	public void setBUY_NUM(int bUY_NUM) {
		BUY_NUM = bUY_NUM;
	}
	public String getPAY_ID() {
		return PAY_ID;
	}
	public void setPAY_ID(String pAY_ID) {
		PAY_ID = pAY_ID;
	}
	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	public int getPRODUCT_PRICE() {
		return PRODUCT_PRICE;
	}
	public void setPRODUCT_PRICE(int pRODUCT_PRICE) {
		PRODUCT_PRICE = pRODUCT_PRICE;
	}
	public int getBUY_QUANTITY() {
		return BUY_QUANTITY;
	}
	public void setBUY_QUANTITY(int bUY_QUANTITY) {
		BUY_QUANTITY = bUY_QUANTITY;
	}
	public int getBUY_PRICE() {
		return BUY_PRICE;
	}
	public void setBUY_PRICE(int bUY_PRICE) {
		BUY_PRICE = bUY_PRICE;
	}
	@Override
	public String toString() {
		return "BuyDto [BUY_NUM=" + BUY_NUM + ", PAY_ID=" + PAY_ID + ", PRODUCT_ID=" + PRODUCT_ID + ", PRODUCT_PRICE="
				+ PRODUCT_PRICE + ", BUY_QUANTITY=" + BUY_QUANTITY + ", BUY_PRICE=" + BUY_PRICE + "]";
	}
	

}