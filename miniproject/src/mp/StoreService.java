package mp;

import java.util.List;

import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;
import mp.vo.StockOrderDto;

public interface StoreService {
	// 재고 조회
	public List<ProductDto> productlist() throws StoreException;

	// 상품 구매
	public int orderproduct(List<BuyDto> list) throws RecordNotFoundException, ShortfallException, StoreException;

	// 결제 내역 추가
	public void paystart(String paytype) throws StoreException;

	// 결제 내역 업데이트
	public int payend(int total) throws StoreException;

	// 구매내역 조회
	public List<BuyDto> Buy_list(int no) throws StoreException, RecordNotFoundException;

	// 결제내역 조회
	public PayDto searchPay(int no) throws StoreException;

	// stock 수량 업데이트
	public List<StockOrderDto> orderstock(List<ProductDto> list) throws StoreException, RecordNotFoundException;

}