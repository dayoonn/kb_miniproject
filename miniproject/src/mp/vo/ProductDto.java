package mp.vo;

public class ProductDto {
	int product_id;
	String product_name;
	int product_price;
	int count;
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDto(int product_id, String product_name, int product_price, int count) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.count = count;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", count=" + count ;
	}
	
}