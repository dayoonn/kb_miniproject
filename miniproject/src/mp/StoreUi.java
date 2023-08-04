package mp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mp.vo.BuyDto;
import mp.vo.PayDto;
import mp.vo.ProductDto;
import mp.vo.StockOrderDto;

public class StoreUi {

	public static void main(String[] args) {
		new StoreUi().go(); // go가 non static이기때문에 new로 객체를 생성해야 함.
	}

	private void go() {
		init(); // 필요 변수 초기화
		liststock_store(); // 물품 목록 출력
		while (true) {
			mainMenu(); // 메인 메뉴 출력
		}
	}

	private StoreService stSvc; // 편의점 서비스
	private Scanner sc;

	private void init() {
		stSvc = new StoreServiceImpl();
		sc = new Scanner(System.in);
	}

	private void mainMenu() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("메인 메뉴 : 1.재고 조회 | 2. 구매 | 3.구매 내역 조회 | 4.발주 | 5.종료 ");
		System.out.print("메뉴 선택 : ");
		int menu = Integer.parseInt(sc.nextLine());
		if (menu == 1)
			liststock_store(); // 재고조회
		else if (menu == 2)
			order_store(); // 구매하기
		else if (menu == 3)
			listproduct_store(); // 구매내역조회
		else if (menu == 4)
			updatestock_store(); // 발주
		else if (menu == 5)
			System.exit(0); // VM종료
		else {
			System.out.println(" 메뉴를 다시 선택해 주세요. ");
		}
	}

	private void updatestock_store() {
		System.out.println("----------------------------[발주]------------------------------");
		System.out.print("발주할 물품의 종류 수를 입력하세요 >> ");
		int k = Integer.parseInt(sc.nextLine());

		List<ProductDto> list = new ArrayList<ProductDto>();
		List<StockOrderDto> stlist = new ArrayList<StockOrderDto>();

		try {
			for (int i = 0; i < k; i++) {
				System.out.print("발주하려는 물품 id를 입력하세요>> ");
				int id = Integer.parseInt(sc.nextLine());
				System.out.print("수량를 입력하세요>> ");
				int quan = Integer.parseInt(sc.nextLine());

				ProductDto pdto = new ProductDto(id, null, 0, quan);
				list.add(pdto);

			}

			stlist = stSvc.orderstock(list);
			System.out.println("**** 발주 완료 되었습니다. ****");
			System.out.println("----------------------------[전체 발주 내역]------------------------------");
			System.out.println("번호 -- 물품ID -- 수량 -- 발주 날짜 ");
			for (StockOrderDto sdto : stlist) {
				System.out.println(sdto.getORDER_SEQ() + "    	 " + sdto.getPRODUCT_ID() + "  	"
						+ sdto.getORDER_QUANTITY() + "    " + sdto.getORDER_DATE());
			}

		} catch (StoreException e) {
			System.out.println("*** 시스템에 오류가 발생했습니다 ***");
		} catch (RecordNotFoundException e) {
			System.out.println("*** 없는 물품ID 입니다. 처음부터 다시 주문해주세요. ***");
		}

	}

	private void listproduct_store() {
		System.out.println("--------------------------[구매 내역 조회]----------------------------");
		System.out.print("Pay id(영수증 id)를 입력하세요 >> ");
		int id = Integer.parseInt(sc.nextLine());
		List<BuyDto> list = new ArrayList<BuyDto>();

		try {
			list = stSvc.Buy_list(id);
			PayDto paydto = stSvc.searchPay(id);

			System.out.println("----------------------------[구매 내역]------------------------------");
			System.out.println("PAY ID : " + paydto.getPay_no() + "		" + "결제방법 : " + paydto.getPay_type());
			System.out.println("결제 시간 : " + paydto.getPay_date() + " " + paydto.getPay_time());
			System.out.println("물품ID --- 가격 ---- 구매 수량 ---- 총 가격 ");
			for (BuyDto dto : list) {
				System.out.println(dto.getProduct_id() + " 	 " + dto.getProduct_price() + "원	     "
						+ dto.getBuy_quantity() + "개      	 " + dto.getSubprice() + "원");
			}
			System.out.println("총 결제 금액 : " + paydto.getTotal_price());

		} catch (StoreException e) {
			System.out.println("*** 시스템에 오류가 발생했습니다 ***");
		} catch (RecordNotFoundException e) {
			System.out.println("*** 없는 결제 결제ID 입니다. ***");
		}

	}

	private void order_store() {
		System.out.println("----------------------------[결제]------------------------------");
		System.out.print("구매할 물품의 종류 수를 입력하세요 >> ");
		int k = Integer.parseInt(sc.nextLine());
		System.out.print("결제 방식을 입력하세요 (현금 / 카드) >> ");
		String paytype = sc.nextLine();
		int total = 0;
		List<BuyDto> list = new ArrayList<BuyDto>();
		try {
			stSvc.paystart(paytype);
			for (int i = 0; i < k; i++) {
				System.out.print("구매할 물품의 id를 입력하세요 >> ");
				int id = Integer.parseInt(sc.nextLine());
				System.out.print("[ID :" + id + "]" + " 물품의 수량을 입력하세요 >> ");
				int quentity = Integer.parseInt(sc.nextLine());
				BuyDto dto = new BuyDto(0, 0, id, 0, quentity, 0);
				list.add(dto);

			}
			total += stSvc.orderproduct(list);
			int pay_id = stSvc.payend(total);

			list = stSvc.Buy_list(pay_id);
			PayDto paydto = stSvc.searchPay(pay_id);

			System.out.println("**** 결제 되었습니다.****");

			System.out.println("----------------------------[구매 내역]------------------------------");
			System.out.println("PAY ID : " + paydto.getPay_no() + "		" + "결제방법 : " + paydto.getPay_type());
			System.out.println("결제 시간 : " + paydto.getPay_date() + " " + paydto.getPay_time());
			System.out.println("물품ID ---- 가격 ---- 구매 수량 ---- 총 가격 ");
			for (BuyDto dto : list) {
				System.out.println(dto.getProduct_id() + " 	 " + dto.getProduct_price() + "원	 "
						+ dto.getBuy_quantity() + "개	 " + dto.getSubprice() + "원");
			}
			System.out.println("총 결제 금액 : " + paydto.getTotal_price());

		} catch (StoreException e) {
			System.out.println("*** 시스템에 오류가 발생했습니다 ***");
		} catch (RecordNotFoundException e) {
			System.out.println("*** 없는 상품 입니다. 결제를 취소합니다. ***");
		} catch (ShortfallException e) {
			System.out.println("*** 수량이 부족합니다 결제를 취소합니다. ***");
		}

	}

	private void liststock_store() { // 재고 조회하기
		System.out.println("----------------------------[재고 목록]------------------------------");
		System.out.println("물품ID -- 물품 이름 --- 가격 --- 남은 수량 ");
		List<ProductDto> list;
		try {
			list = stSvc.productlist();

			for (ProductDto dto : list) { // 정상적일 경우 for문 실행
				System.out.printf("%-8d", dto.getProduct_id());
				System.out.printf("%-8s", dto.getProduct_name());
				System.out.printf("%8d원", dto.getProduct_price());
				System.out.printf("%8d개\n", dto.getCount());

			}
		} catch (StoreException e) {
			System.out.println("*** 시스템에 오류가 발생했습니다 ***");
		}
	}
}