package mp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mp.ShortfallException;
import mp.util.JdbcUtil;
import mp.vo.BuyDto;
import mp.vo.PayDto;
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

	@Override
	public void addpay(String paytype) throws SQLException {
		
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO PAY (pay_id,pay_date,pay_time,pay_type,final_amount) "; 
			sql+="VALUES (seq_pay.nextval,SYSDATE,TO_CHAR(SYSDATE, 'HH24:MI:SS'),?,0)";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setString(1, paytype);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			int count=pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		
	}

	@Override
	public int addbuy(BuyDto dto) throws SQLException, ShortfallException {
		BuyDto bdto=null;
		int subprice=0;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO BUY (buy_num,pay_id,product_id,product_price,buy_quantity,buy_price) "; 
			sql+="VALUES (seq_buy.nextval,?,?,?,?,?)";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1,dto.getPay_no());
			pstmt.setInt(2,dto.getProduct_id());
			pstmt.setInt(3,dto.getProduct_price());
			pstmt.setInt(4,dto.getBuy_quentity());
			pstmt.setInt(5,dto.getProduct_price()*dto.getBuy_quentity());
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			int count = pstmt.executeUpdate();
			subprice=dto.getProduct_price()*dto.getBuy_quentity();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return subprice;
	}

	@Override
	public ProductDto findByNo(int id) throws SQLException {
		ProductDto dto = null;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM PRODUCT WHERE product_id = ? ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1, id);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회결과가 있다
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int	product_price = rs.getInt("product_price");
				dto = new ProductDto(product_id, product_name, product_price, 0); // while문이 돌때마다 dto 생성
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

	@Override
	public void updatepay(PayDto dto) throws SQLException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "UPDATE PAY SET final_amount=? ";
            sql += "WHERE pay_id = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1, dto.getTotal_price());
            pstmt.setInt(2, dto.getPay_no());
           
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
		
	}

	@Override
	public int lastpay() throws SQLException {
		int payid = -1;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT max(pay_id)  FROM pay ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회결과가 있다
				payid=rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return payid;
	}

	@Override
	public void updatestock(BuyDto dto) throws SQLException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "UPDATE STOCK SET PRODUCT_QUANTITY=PRODUCT_QUANTITY-? ";
            sql += "WHERE product_id = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1, dto.getBuy_quentity());
            pstmt.setInt(2, dto.getProduct_id());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
	}

}
