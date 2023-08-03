package mp;

import java.sql.SQLException;
import java.util.List;

import mp.dao.StoreDao;
import mp.dao.StoreDaoImpl;
import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;

public class StoreServiceImpl implements StoreService {

	private StoreDao storeDao=new StoreDaoImpl();
	
	@Override
	public List<ProductDto> productlist() throws StoreException {
		List<ProductDto> list=null;
		
			try {
				list = storeDao.productlist();
			} catch (SQLException e) {
				throw new StoreException(e.getMessage());
			}
		
		return list;
	}

	@Override
	public boolean orderproduct(List<BuyDto> list,String paytype) throws StoreException, ShortfallException {
		try {
			storeDao.addpay(paytype);
			int pay_id=storeDao.lastpay();
			int totalprice=0;
			for (BuyDto dto : list) {
				ProductDto prodDto=storeDao.findByNo(dto.getProduct_id());
				if(prodDto==null) 
						throw new ShortfallException();
				BuyDto bdto=new BuyDto(0, pay_id, dto.getProduct_id(), prodDto.getProduct_price(), dto.getBuy_quentity(), 0);
				totalprice+=storeDao.addbuy(bdto);
				storeDao.updatestock(bdto);
			}
			
			PayDto pay_dto=new PayDto(pay_id,null, null, null, totalprice);
			storeDao.updatepay(pay_dto);
		} catch (SQLException e) {
			throw new StoreException(e.getMessage());
		}
		
		return false;
	}

	



}
