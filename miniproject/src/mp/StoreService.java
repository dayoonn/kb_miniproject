package mp;

import java.util.List;

import mp.vo.BuyDto;
import mp.vo.ProductDto;

public interface StoreService {
	//재고 조회
	public List<ProductDto> productlist() throws StoreException;
	
	//상품 구매
	public boolean orderproduct(List<BuyDto> list,String paytype) throws StoreException,ShortfallException;

}
