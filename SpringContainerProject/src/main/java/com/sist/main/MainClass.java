package com.sist.main;

public class MainClass {
	public static void main(String[] args) {
		String path = "C:\\SpringDev\\springStudy\\SpringContainerProject\\src\\main\\java\\com\\sist\\main\\app.xml";
		ApplicationContext app = new ApplicationContext(path);
		Board board = (Board)app.getBean("board");
		System.out.println("baord="+board);
		board.board_list();
		Board board1 = (Board)app.getBean("board");
		System.out.println("baord1="+board1);
		board1.board_list();
		Board board2 = (Board)app.getBean("board");
		System.out.println("baord2="+board2);
		board2.board_list();
	}
}
