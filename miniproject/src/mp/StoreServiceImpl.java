package mp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mp.dao.StoreDao;
import mp.dao.StoreDaoImpl;
import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;
import mp.vo.StockOrderDto;

public class StoreServiceImpl implements StoreService {

	private StoreDao storeDao = new StoreDaoImpl();

	@Override
	public List<ProductDto> productlist() throws StoreException {
		List<ProductDto> list = null;

		try {
			list = storeDao.productlist();
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}

		return list;
	}

	@Override
	public int orderproduct(List<BuyDto> list) throws StoreException, ShortfallException, RecordNotFoundException {
		int totalprice = 0;
		try {

			int pay_id = storeDao.lastpay();
			for (BuyDto dto : list) {
				ProductDto prodDto = storeDao.findById(dto.getProduct_id());
				if (prodDto == null) {
					paydelete();
					throw new RecordNotFoundException();
				} // 찾는 상품이 없을 시
				if (prodDto.getCount() < dto.getBuy_quantity()) { // 수량 부족 시

					paydelete();
					throw new ShortfallException();
				}

			} // 예외 확인 for

			for (BuyDto dto : list) {
				ProductDto prodDto = storeDao.findById(dto.getProduct_id());
				BuyDto bdto = new BuyDto(0, pay_id, dto.getProduct_id(), prodDto.getProduct_price(),
						dto.getBuy_quantity(), 0);
				totalprice += storeDao.addbuy(bdto);
				storeDao.updatestock(bdto);
			} // 구매내역 등록

		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}

		return totalprice; // 총 결제금액 반환
	}

	private void paydelete() throws StoreException {
		int pay_id;
		try {
			pay_id = storeDao.lastpay();
			PayDto pay_dto = new PayDto(pay_id, null, null, null, 0);
			storeDao.deletepay(pay_dto);
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}

	}

	@Override
	public void paystart(String paytype) throws StoreException {
		try {
			storeDao.addpay(paytype);
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}
	}

	@Override
	public int payend(int total) throws StoreException {
		int pay_id;
		try {
			pay_id = storeDao.lastpay();
			PayDto pay_dto = new PayDto(pay_id, null, null, null, total);
			storeDao.updatepay(pay_dto);
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}
		return pay_id;
	}

	@Override
	public List<BuyDto> Buy_list(int no) throws StoreException, RecordNotFoundException {
		List<BuyDto> list = new ArrayList<BuyDto>();

		try {
			list = storeDao.findById_payid(no);
			if (list.size() < 1)
				throw new RecordNotFoundException(); // 찾는 pay id가 없을 시
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}
		return list;
	}

	@Override
	public PayDto searchPay(int no) throws StoreException {
		PayDto dto = null;
		try {
			dto = storeDao.find_pay(no);
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}
		return dto;
	}

	@Override
	public List<StockOrderDto> orderstock(List<ProductDto> list) throws StoreException, RecordNotFoundException {
		List<StockOrderDto> stlist = new ArrayList<StockOrderDto>();
		try {
			for (ProductDto dto : list) {
				ProductDto prodDto = storeDao.findById(dto.getProduct_id());
				if (prodDto == null) {
					throw new RecordNotFoundException();
				} // 찾는 상품이 없을 시
			} // 예외 확인 for
			for (ProductDto dto : list) {

				storeDao.addOrderStock(dto);
				storeDao.updatestock(dto);
			}

			stlist = storeDao.stockorderlist();

		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}

		return stlist;
	}

}