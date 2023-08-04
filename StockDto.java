package day_0803.dao;

public class StockDto {

	String PRODUCT_ID;
	int PRODUCT_QUANTITY;

	public StockDto() {
		// TODO Auto-generated constructor stub
	}

	public StockDto(String pRODUCT_ID, int pRODUCT_QUANTITY) {
		super();
		PRODUCT_ID = pRODUCT_ID;
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
	}

	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public int getPRODUCT_QUANTITY() {
		return PRODUCT_QUANTITY;
	}

	public void setPRODUCT_QUANTITY(int pRODUCT_QUANTITY) {
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
	}

	@Override
	public String toString() {
		return "StockDto [PRODUCT_ID=" + PRODUCT_ID + ", PRODUCT_QUANTITY=" + PRODUCT_QUANTITY + "]";
	}
	
}