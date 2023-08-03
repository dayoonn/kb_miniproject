package mp.dao;

import java.sql.SQLException;
import java.util.List;

import mp.ShortfallException;
import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;

public interface StoreDao {

	//재고 조회
	public List<ProductDto> productlist() throws SQLException;
	//결제내역 등록
	public void addpay(String paytype) throws SQLException;
	//구매내역 등록
	public int addbuy(BuyDto bdto) throws SQLException,ShortfallException;
	//물건 검색
	public ProductDto findByNo(int id) throws SQLException;
	//최종 결제 금액 업데이트
	public void updatepay(PayDto pay_dto)throws SQLException;
	//pay_id조회
	public int lastpay()throws SQLException;
	//수량 조정
	public void updatestock(BuyDto bdto)throws SQLException;

}
