package mp.vo;

import java.util.Date;

public class StockOrderDto {

	int ORDER_SEQ;
	int PRODUCT_ID;
	int ORDER_QUANTITY;
	Date ORDER_DATE;

	public StockOrderDto() {
		// TODO Auto-generated constructor stub
	}

	
	


	public StockOrderDto(int oRDER_SEQ, int pRODUCT_ID, int oRDER_QUANTITY, Date oRDER_DATE) {
		super();
		ORDER_SEQ = oRDER_SEQ;
		PRODUCT_ID = pRODUCT_ID;
		ORDER_QUANTITY = oRDER_QUANTITY;
		ORDER_DATE = oRDER_DATE;
	}





	public int getORDER_SEQ() {
		return ORDER_SEQ;
	}





	public void setORDER_SEQ(int oRDER_SEQ) {
		ORDER_SEQ = oRDER_SEQ;
	}





	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}





	public void setPRODUCT_ID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}





	public int getORDER_QUANTITY() {
		return ORDER_QUANTITY;
	}





	public void setORDER_QUANTITY(int oRDER_QUANTITY) {
		ORDER_QUANTITY = oRDER_QUANTITY;
	}





	public Date getORDER_DATE() {
		return ORDER_DATE;
	}





	public void setORDER_DATE(Date oRDER_DATE) {
		ORDER_DATE = oRDER_DATE;
	}





	@Override
	public String toString() {
		return "ORDER_SEQ=" + ORDER_SEQ + ", PRODUCT_ID=" + PRODUCT_ID + ", ORDER_QUANTITY="
				+ ORDER_QUANTITY + ", ORDER_DATE=" + ORDER_DATE ;
	}
	
}