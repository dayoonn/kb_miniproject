package mp;

import java.util.List;
import java.util.Scanner;

import mp.dao.RecordNotFoundException;
import mp.vo.BoardDto;

public class BoardUi {
	
	public static void main(String[] args) {
		new BoardUi().go(); //go가 non static이기때문에 new로 객체를 생성해야 함.
	}

	private void go() {
		init(); //필요 변수 초기화
		list(); //게시물 목록 출력
		while (true) {
			mainMenu(); //메인 메뉴 출력
		}
	}
	private void mainMenu() {
		System.out.println("메인 메뉴 : 1.등록 | 2.목록 | 3.상세 | 4.수정 | 5.삭제 | 6.종료 ");
		System.out.print("메뉴 선택 : ");
		int menu=Integer.parseInt(sc.nextLine());
		if(menu==1) addBoard();
		else if(menu==2) list();
		else if(menu==3) readBoard(); //상세보기
		else if(menu==4) updateBoard(); //수정
		else if(menu==5) deleteBoard(); //삭제
		else if(menu==7) countBoard();
		else if(menu==6) System.exit(0); //VM종료
	}
	private void countBoard() {
		System.out.print("총 게시물의 수 : ");
		try {
			int count=brdSvc.count();
			System.out.println(count);
		} catch (BoardException e) {
			System.out.println("--- 게시판 서버 오류 ---");
		}
	}

	private void deleteBoard() {
		System.out.print("삭제할 게시물 번호를 입력하세요 >> ");
		int no=Integer.parseInt(sc.nextLine());
		try {
			brdSvc.delete(no);
			System.out.println(no+"번 게시물 삭제 완료");
		} catch (BoardException e) {
			System.out.println("--- 게시판 서버 오류 ---");
		}catch (RecordNotFoundException e) {
			System.out.println("--- 존재하지 않는 게시물 입니다. ---");
		}
		
	}

	private void updateBoard() { //게시물 내용 수정하기
		System.out.print("수정할 게시물 번호를 입력하세요 >> ");
		int no=Integer.parseInt(sc.nextLine());
		System.out.print("수정 내용을 입력하세요 >> ");
		String content=sc.nextLine();
		try {
			brdSvc.update(new BoardDto(no,null,null,null,content));
		} catch (BoardException e) {
			System.out.println("--- 게시판 서버 오류 ---");
		} catch (RecordNotFoundException e) {
			System.out.println("--- 존재하지 않는 게시물 입니다. ---");
		}
		
	}

	private void readBoard() { //게시물 상세보기
		System.out.print("내용을 보고싶은 게시물 번호를 입력하세요 >> ");
		int no=Integer.parseInt(sc.nextLine());
		try {
			BoardDto dto = brdSvc.read(no);
			if(dto!=null) {
			System.out.println("** 상세보기 **");
			System.out.println("번호: "+dto.getNo());
			System.out.println("제목: "+dto.getTitle());
			System.out.println("작성자: "+dto.getWriter());
			System.out.println("작성일: "+dto.getRegdate());
			System.out.println("내용: "+dto.getContent());}
		} catch (BoardException e) {
			System.out.println("--- 게시판 서버 오류 ---");
		}catch (RecordNotFoundException e) {
			System.out.println("--- 존재하지 않는 게시물 입니다. ---");
		}
	}

	private void addBoard() {
		System.out.println("** 게시물 등록 **");
		System.out.print("제목을 입력하세요>> ");
		String title =sc.nextLine();
		System.out.print("작성자를 입력하세요>> ");
		String writer=sc.nextLine();
		System.out.print("내용을 입력하세요>> ");
		String content=sc.nextLine();
		BoardDto dto=new BoardDto(0,title,writer,null,content);
		try {
			brdSvc.add(dto);
		} catch (BoardException e) {
			System.out.println("게시물 등록 오류");
		}
	}
	private BoardService brdSvc; //게시판 서비스
	private Scanner sc;
	private void init() {
		brdSvc=new BoardServiceImpl();
		sc=new Scanner(System.in);
	}
	private void list() {
		System.out.println("[게시물 목록]");
		System.out.println("번호 ---- 작성자 ---- 제목 ---- 작성일 ----");
		List<BoardDto> list;
		try {
			list = brdSvc.list();
			for (BoardDto dto : list) { // 정상적일 경우 for문 실행
				System.out.println(dto.getNo() + "      " + dto.getWriter() + "      " + dto.getTitle() + "      "
						+ dto.getRegdate() + "      ");
			}
		} catch (BoardException e) {
			System.out.println("*** 서버에 오류가 발생했습니다 ***");
		}
	}

}
