package mp.vo;

import java.util.Date;

public class PayDto {

	int pay_no;
	Date pay_date;
	String pay_time;
	String pay_type;
	int total_price;
	
	public PayDto() {
		// TODO Auto-generated constructor stub
	}

	public PayDto(int pay_no, Date pay_date, String pay_time, String pay_type, int total_price) {
		super();
		this.pay_no = pay_no;
		this.pay_date = pay_date;
		this.pay_time = pay_time;
		this.pay_type = pay_type;
		this.total_price = total_price;
	}

	public int getPay_no() {
		return pay_no;
	}

	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "pay_no=" + pay_no + ", pay_date=" + pay_date + ", pay_time=" + pay_time + ", pay_type="
				+ pay_type + ", total_price=" + total_price ;
	}
	
	
}
