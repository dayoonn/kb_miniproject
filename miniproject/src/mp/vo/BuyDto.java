package mp.vo;

public class BuyDto {

	int buy_no;
	int pay_no;
	int product_id;
	int product_price;
	int buy_quentity;
	int subprice;
	
	public BuyDto() {
		// TODO Auto-generated constructor stub
	}

	public int getBuy_no() {
		return buy_no;
	}

	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}

	public int getPay_no() {
		return pay_no;
	}

	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getBuy_quentity() {
		return buy_quentity;
	}

	public void setBuy_quentity(int buy_quentity) {
		this.buy_quentity = buy_quentity;
	}

	public int getSubprice() {
		return subprice;
	}

	public void setSubprice(int subprice) {
		this.subprice = subprice;
	}

	public BuyDto(int buy_no, int pay_no, int product_id, int product_price, int buy_quentity, int subprice) {
		super();
		this.buy_no = buy_no;
		this.pay_no = pay_no;
		this.product_id = product_id;
		this.product_price = product_price;
		this.buy_quentity = buy_quentity;
		this.subprice = subprice;
	}

	@Override
	public String toString() {
		return "buy_no=" + buy_no + ", pay_no=" + pay_no + ", product_id=" + product_id + ", product_price="
				+ product_price + ", buy_quentity=" + buy_quentity + ", subprice=" + subprice ;
	}
}
