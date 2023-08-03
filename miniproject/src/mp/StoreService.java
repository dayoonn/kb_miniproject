package mp;

import java.util.List;

import mp.vo.ProductDto;
import mp.vo.StoreDto;

public interface StoreService {
	//재고 조회
	public List<ProductDto> productlist() throws StoreException;
}
