package mp.dao;

import java.sql.SQLException;
import java.util.List;

import mp.ShortfallException;
import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;
import mp.vo.StockOrderDto;

public interface StoreDao {

	// 재고 조회
	public List<ProductDto> productlist() throws SQLException;
	// 결제내역 등록
	public void addpay(String paytype) throws SQLException;
	// 구매내역 등록
	public int addbuy(BuyDto bdto) throws SQLException, ShortfallException;
	// 물건 검색
	public ProductDto findById(int id) throws SQLException;
	// 최종 결제 금액 업데이트
	public void updatepay(PayDto pay_dto) throws SQLException;
	// 최근 pay_id조회
	public int lastpay() throws SQLException;
	// 물품 수량 조정
	public void updatestock(BuyDto bdto) throws SQLException;
	// 결제 내역 삭제
	public void deletepay(PayDto pay_dto) throws SQLException;
	// pay_id로 구매내역 list 조회
	public List<BuyDto> findById_payid(int no) throws SQLException;
	// pay번호로 pay 레코드 조회
	public PayDto find_pay(int no) throws SQLException;
	// 발주 내역 등록
	public void addOrderStock(ProductDto pdto) throws SQLException;
	// 물품 수량 조정
	public void updatestock(ProductDto pdto) throws SQLException;
	// 최근 발주 내역 번호 조회
	public int last_orderseq() throws SQLException;
	// 발주 list 조회
	public List<StockOrderDto> stockorderlist() throws SQLException;
}