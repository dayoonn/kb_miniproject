package day_0803.dao;

import java.util.Scanner;


public class StoreUi {
	public static void main(String[] args) throws StoreException {
		new StoreUi().go(); // go가 non static이기때문에 new로 객체를 생성해야 함.
	}

	private StoreService stSvc; // 편의점 서비스
	private Scanner sc;

	private void init() {
		stSvc = new StoreServiceImpl();
		sc = new Scanner(System.in);
	}

	private void go() throws StoreException {
		init(); // 필요 변수 초기화
//		liststock_store(); // 게시물 목록 출력
		while (true) {
			mainMenu(); // 메인 메뉴 출력
		}
	}

	private void mainMenu() throws StoreException {
		System.out.println("메인 메뉴 : 1.재고 조회 | 2. 구매 | 3.구매 내역 조회 | 4.발주 | 5.종료 ");
		System.out.print("메뉴 선택 : ");
		int menu=Integer.parseInt(sc.nextLine());
//		if(menu==1) liststock_store(); //재고조회
//		else if(menu==2) order_store(); //구매하기
		if(menu==3) listproduct_store(); //구매내역조회
		else if(menu==4) updatestock_store(); //발주
		else if(menu==5) System.exit(0); //VM종료
	}
	
	
	
	private void listproduct_store() throws StoreException {
		//pay_id로 조회하여 PAY 테이블 전체 출력.
		System.out.println("PAY_ID를 입력하세요");
		String id = sc.nextLine();

		try {
			PayDto dto = stSvc.pread(id);
			System.out.println("상세보기");
			System.out.println("PAY_ID: " + dto.getPAY_ID());
			System.out.println("PAY_DATE: " + dto.getPAY_DATE());
			System.out.println("PAY_TIME: " + dto.getPAY_TIME());
			System.out.println("PAY_TYPE: " + dto.getPAY_TYPE());
			System.out.println("FINAL_AMOUNT: " + dto.getFINAL_AMOUNT());
		}catch(RecordNotFoundException e) {
			System.out.println("없는 게시물입니다");
		}

		
		//해당 pay_id로 조회하여 buy 리스트를 테이블에서 product_id,product_price, subprice 출력
		try {
			BuyDto ddto = stSvc.bread(id);
			System.out.println("상세보기");
			System.out.println("PAY_ID: " + ddto.getPRODUCT_ID());
			System.out.println("PAY_DATE: " + ddto.getPRODUCT_PRICE());
			System.out.println("PAY_TIME: " + ddto.getBUY_PRICE());
		}catch(RecordNotFoundException e) {
			System.out.println("없는 게시물입니다");
		}

	}
	//발주 while - product_id 입력받기
	/**
   -수량 입력받기
   - STOCK 업데이트 (재고 수량 +)
   - STOCK_ORDER 추가 (발주번호:SEQ.next,product_id:입력받은거,수량:입력받은 수량, 날짜)
   - 발주 종료시 버튼 선택 */
	private void updatestock_store() {
		//수량과 product_id 입력
		while(true) {
			System.out.println("발주할 PRODUCT_ID를 입력하세요");
		String id = sc.nextLine();
		System.out.println("발주할 수량을 입력하세요");
		int quan = Integer.parseInt(sc.nextLine());
		//stock 업데이트
		try {
			stSvc.Stock_update(id,quan);
		}catch (RecordNotFoundException e) {
			System.out.println("없는 게시물입니다");
		}
		//stock_order에 추가 
		stSvc.StockOrder_add(id, quan);
		System.out.println("발주 종료>>1");
		int finish = Integer.parseInt(sc.nextLine());
		if (finish == 1) break;
		}
		
		
		
	}

}
