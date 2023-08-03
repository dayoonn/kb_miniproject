package day_0803.dao;

import java.util.List;

public class StoreServiceImpl implements StoreService {

	@Override
	public List<PayDto> plist() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<BuyDto> blist() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<BuyDto> Buy_list() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<PayDto> Pay_list() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<StockDto> Stock_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayDto pread(String no) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	public BuyDto bread(String no) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PayDto dto) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean Stock_update(String id, int qUAN) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
	public void StockOrder_update(StockOrderDto m) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return ;
	}
	public boolean update(BuyDto dto) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int no) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
	public void StockOrder_delete(int id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int StockOrder_count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean add(PayDto dto) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean StockOrder_add(String id, int quan) {
		// TODO Auto-generated method stub
		return false;
	}
}
