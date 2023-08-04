package day_0803.dao;

public class PayDto {

	String PAY_ID;
	String PAY_DATE;
	String PAY_TIME;
	String PAY_TYPE;
	String FINAL_AMOUNT;
	
	public PayDto() {
		// TODO Auto-generated constructor stub
	}

	public PayDto(String pAY_ID, String pAY_DATE, String pAY_TIME, String pAY_TYPE, String fINAL_AMOUNT) {
		super();
		PAY_ID = pAY_ID;
		PAY_DATE = pAY_DATE;
		PAY_TIME = pAY_TIME;
		PAY_TYPE = pAY_TYPE;
		FINAL_AMOUNT = fINAL_AMOUNT;
	}

	public String getPAY_ID() {
		return PAY_ID;
	}

	public void setPAY_ID(String pAY_ID) {
		PAY_ID = pAY_ID;
	}

	public String getPAY_DATE() {
		return PAY_DATE;
	}

	public void setPAY_DATE(String pAY_DATE) {
		PAY_DATE = pAY_DATE;
	}

	public String getPAY_TIME() {
		return PAY_TIME;
	}

	public void setPAY_TIME(String pAY_TIME) {
		PAY_TIME = pAY_TIME;
	}

	public String getPAY_TYPE() {
		return PAY_TYPE;
	}

	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}

	public String getFINAL_AMOUNT() {
		return FINAL_AMOUNT;
	}

	public void setFINAL_AMOUNT(String fINAL_AMOUNT) {
		FINAL_AMOUNT = fINAL_AMOUNT;
	}

	@Override
	public String toString() {
		return "PayDto [PAY_ID=" + PAY_ID + ", PAY_DATE=" + PAY_DATE + ", PAY_TIME=" + PAY_TIME + ", PAY_TYPE="
				+ PAY_TYPE + ", FINAL_AMOUNT=" + FINAL_AMOUNT + "]";
	}

}