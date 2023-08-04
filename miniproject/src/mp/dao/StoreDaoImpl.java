package mp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mp.ShortfallException;
import mp.util.JdbcUtil;
import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;
import mp.vo.StockOrderDto;

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
			String sql = "SELECT p.product_id ,p.product_name,p.product_price,s.product_quantity FROM product p, stock s where p.product_id=s.product_id  "; // 최신글
																																								// 우선

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정

			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // 조회결과가 있다
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int product_price = rs.getInt("product_price");
				int product_quantity = rs.getInt("product_quantity");
				ProductDto dto = new ProductDto(product_id, product_name, product_price, product_quantity); // while문이
																											// 돌때마다 dto
																											// 생성
				result.add(dto); // List에 추가
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
			sql += "VALUES (seq_pay.nextval,SYSDATE,TO_CHAR(SYSDATE, 'HH24:MI:SS'),?,0)";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setString(1, paytype);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			int count = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public int addbuy(BuyDto dto) throws SQLException, ShortfallException {
		BuyDto bdto = null;
		int subprice = 0;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO BUY (buy_num,pay_id,product_id,product_price,buy_quantity,buy_price) ";
			sql += "VALUES (seq_buy.nextval,?,?,?,?,?)";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1, dto.getPay_no());
			pstmt.setInt(2, dto.getProduct_id());
			pstmt.setInt(3, dto.getProduct_price());
			pstmt.setInt(4, dto.getBuy_quantity());
			pstmt.setInt(5, dto.getProduct_price() * dto.getBuy_quantity());
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			int count = pstmt.executeUpdate();
			subprice = dto.getProduct_price() * dto.getBuy_quantity();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return subprice;
	}

	@Override
	public ProductDto findById(int id) throws SQLException {
		ProductDto dto = null;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT p.product_id,p.product_name,p.product_price,s.product_quantity FROM PRODUCT p,STOCK s ";
			sql += "WHERE s.product_id=p.product_id AND p.product_id = ? ";

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
				int product_price = rs.getInt("product_price");
				int product_quantity = rs.getInt("product_quantity");

				dto = new ProductDto(product_id, product_name, product_price, product_quantity); // while문이 돌때마다 dto 생성
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
		// DBMS연결
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
				payid = rs.getInt(1);
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
		// DBMS연결
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
			pstmt.setInt(1, dto.getBuy_quantity());
			pstmt.setInt(2, dto.getProduct_id());
			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void deletepay(PayDto dto) throws SQLException {
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "delete pay where pay_id=? ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, dto.getPay_no());

			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public List<BuyDto> findById_payid(int no) throws SQLException {
		List<BuyDto> list = new ArrayList<BuyDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3.SQL 작성
			String sql = "SELECT * FROM BUY where PAY_ID = ?";// 마지막 한칸 띄기
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, no);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() : int
			// select전송 : executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int BUY_NUM = rs.getInt("BUY_NUM");
				int PAY_ID = rs.getInt("PAY_ID");
				int PRODUCT_ID = rs.getInt("PRODUCT_ID");
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				int BUY_QUANTITY = rs.getInt("BUY_QUANTITY");
				int BUY_PRICE = rs.getInt("BUY_PRICE");
				BuyDto dto = new BuyDto(BUY_NUM, PAY_ID, PRODUCT_ID, PRODUCT_PRICE, BUY_QUANTITY, BUY_PRICE);
				list.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return list;
	}

	@Override
	public PayDto find_pay(int no) throws SQLException {
		PayDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3.SQL 작성
			String sql = "SELECT * FROM PAY where PAY_ID = ?";// 마지막 한칸 띄기
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, no);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() : int
			// select전송 : executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int PAY_ID = rs.getInt("PAY_ID");
				Date PAY_DATE = rs.getDate("PAY_DATE");
				String PAY_TIME = rs.getString("PAY_TIME");
				String PAY_TYPE = rs.getString("PAY_TYPE");
				int FINAL_AMOUNT = rs.getInt("FINAL_AMOUNT");

				dto = new PayDto(PAY_ID, PAY_DATE, PAY_TIME, PAY_TYPE, FINAL_AMOUNT);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return dto;
	}

	@Override
	public void addOrderStock(ProductDto pdto) throws SQLException {
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO store_order (oeder_seq,product_id,order_quantity,order_date) ";
			sql += "VALUES (ORDER_SEQ.NEXTVAL,?,?,SYSDATE)";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1, pdto.getProduct_id());
			pstmt.setInt(2, pdto.getCount());
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			int count = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void updatestock(ProductDto dto) throws SQLException {
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "UPDATE STOCK SET PRODUCT_QUANTITY=PRODUCT_QUANTITY+? ";
			sql += "WHERE product_id = ?";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, dto.getCount());
			pstmt.setInt(2, dto.getProduct_id());
			// 6. SQL 전송, 결과수신
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public int last_orderseq() throws SQLException {
		int orderseq = -1;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT max(OEDER_SEQ)  FROM STORE_ORDER ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정

			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회결과가 있다
				orderseq = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return orderseq;
	}

	@Override
	public List<StockOrderDto> stockorderlist() throws SQLException {
		List<StockOrderDto> list = new ArrayList<StockOrderDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3.SQL 작성
			String sql = "SELECT * FROM STORE_ORDER ORDER BY OEDER_SEQ ";// 마지막 한칸 띄기
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정

			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() : int
			// select전송 : executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int OEDER_SEQ = rs.getInt("OEDER_SEQ");
				int PRODUCT_ID = rs.getInt("PRODUCT_ID");
				int ORDER_QUANTITY = rs.getInt("ORDER_QUANTITY");
				Date ORDER_DATE = rs.getDate("ORDER_DATE");

				StockOrderDto dto = new StockOrderDto(OEDER_SEQ, PRODUCT_ID, ORDER_QUANTITY, ORDER_DATE);
				list.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return list;
	}

}