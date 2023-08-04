package mp.vo;

public class BuyDto {

	int buy_no;
	int pay_no;
	int product_id;
	int product_price;
	int buy_quantity;
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

	public int getBuy_quantity() {
		return buy_quantity;
	}

	public void setBuy_quantity(int buy_quantity) {
		this.buy_quantity = buy_quantity;
	}

	public int getSubprice() {
		return subprice;
	}

	public void setSubprice(int subprice) {
		this.subprice = subprice;
	}

	public BuyDto(int buy_no, int pay_no, int product_id, int product_price, int buy_quantity, int subprice) {
		super();
		this.buy_no = buy_no;
		this.pay_no = pay_no;
		this.product_id = product_id;
		this.product_price = product_price;
		this.buy_quantity = buy_quantity;
		this.subprice = subprice;
	}

	@Override
	public String toString() {
		return "buy_no=" + buy_no + ", pay_no=" + pay_no + ", product_id=" + product_id + ", product_price="
				+ product_price + ", buy_quantity=" + buy_quantity + ", subprice=" + subprice ;
	}
}