package mp;

import java.util.List;
import java.util.Scanner;

import mp.dao.RecordNotFoundException;
import mp.vo.BoardDto;

public class StoreUi {

	public static void main(String[] args) {
		new StoreUi().go(); // go가 non static이기때문에 new로 객체를 생성해야 함.
	}

	private StoreService stSvc; // 편의점 서비스
	private Scanner sc;

	private void init() {
		stSvc = new StoreServiceImpl();
		sc = new Scanner(System.in);
	}

	private void go() {
		init(); // 필요 변수 초기화
		liststock_store(); // 게시물 목록 출력
		while (true) {
			mainMenu(); // 메인 메뉴 출력
		}
	}

	private void mainMenu() {
		System.out.println("메인 메뉴 : 1.재고 조회 | 2. 구매 | 3.구매 내역 조회 | 4.발주 | 5.종료 ");
		System.out.print("메뉴 선택 : ");
		int menu=Integer.parseInt(sc.nextLine());
		if(menu==1) liststock_store(); //재고조회
//		else if(menu==2) order_store(); //구매하기
//		else if(menu==3) listproduct_store(); //구매내역조회
//		else if(menu==4) updatestock_store(); //발주
		else if(menu==5) System.exit(0); //VM종료
	}

	private void liststock_store() {
		// TODO Auto-generated method stub
		
	}
}


