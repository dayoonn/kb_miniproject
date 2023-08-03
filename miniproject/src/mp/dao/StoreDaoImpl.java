package mp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mp.util.JdbcUtil;
import mp.vo.ProductDto;

public class StoreDaoImpl implements StoreDao {

	@Override
	public List<ProductDto> productlist() throws SQLException {
		List<ProductDto> result = new ArrayList<ProductDto>();
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT p.product_id ,p.product_name,p.product_price,s.product_quantity FROM product p, stock s where p.product_id=s.product_id  "; //최신글 우선

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
		
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // 조회결과가 있다
				int product_id=rs.getInt("product_id");
				String product_name=rs.getString("product_name");
				int product_price=rs.getInt("product_price");
				int product_quantity=rs.getInt("product_quantity");
				ProductDto dto = new ProductDto(product_id,product_name,product_price,product_quantity); //while문이 돌때마다 dto 생성
				result.add(dto); //List에 추가
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

}
