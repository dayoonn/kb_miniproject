package mp;

import java.sql.SQLException;
import java.util.List;

import mp.dao.StoreDao;
import mp.dao.StoreDaoImpl;
import mp.vo.ProductDto;

public class StoreServiceImpl implements StoreService {

	private StoreDao storeDao=new StoreDaoImpl();
	
	@Override
	public List<ProductDto> productlist() throws StoreException {
		List<ProductDto> list=null;
		
			try {
				list = storeDao.productlist();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return list;
	}

}
