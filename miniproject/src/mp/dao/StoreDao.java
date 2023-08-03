package mp.dao;

import java.sql.SQLException;
import java.util.List;

import mp.vo.ProductDto;

public interface StoreDao {

	public List<ProductDto> productlist() throws SQLException;

}
