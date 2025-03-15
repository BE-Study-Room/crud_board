package study.crudboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.crudboard.entity.*;
import study.crudboard.service.*;


@SpringBootApplication
public class CrudBoardApplication {

    public static void main(String[] args) {
		final ArrayList<Article> articles = new ArrayList<>();
		int articleCount = 0; // 멤버 변수로 생성
		String UserName;

		ConfigurableApplicationContext context = SpringApplication.run(CrudBoardApplication.class, args);
		ArticleService articleService = context.getBean(ArticleService.class);

		App app = new App();
		String username = app.start(); // app 메서드를 실행하여 로그인한 username 저장

		if (username == null) {
			System.out.println("로그인 실패 프로그램 종료");
			return;
		}
		UserName = username;

		System.out.println("======= 게시판 =======");
		Scanner sc = new Scanner(System.in);
		try{
		while (true) {
			System.out.print("write / list [string] / detail [num] / delete [num] / update [num] / exit : ");
			String commend = sc.nextLine();

			if (commend.equals("exit")) {
				break;
			} else if (commend.equals("write")) {
				int id = articleCount + 1;
				articleCount = id;

				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				int hit = 0;
				String nowDate = Util.getNowDateStr();
				Article article = new Article(id, title, body, hit, nowDate, UserName); // 게시글 생성
				articleService.create(article);

				System.out.println(id + "번째 글이 생성되었습니다.");

			} else if (commend.startsWith("list")) {
				articleService.read(commend);

			} else if (commend.startsWith("detail ")) {


			} else if (commend.startsWith("delete ")) {
				articleService.delete(commend);
				System.out.println("게시물이 삭제되었습니다.");

			} else if (commend.startsWith("update ")) {
				String[] commendBits = commend.split(" ");
				int id = Integer.parseInt(commendBits[1]);

				System.out.print("수정 제목: ");
				String title = sc.nextLine();
				System.out.print("수정 내용: ");
				String body = sc.nextLine();
				Article newarticle = new Article(id, title, body, 0, null, UserName);
				articleService.update(newarticle);

				System.out.println("수정이 완료되었습니다.");

			} else {
				System.out.println("잘못된 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("======게시판 종료======");
	} catch (Exception e) {
			System.out.println("오류:"+e);
		}}}

//	private void detail (String commend) {
//		String[] detailNum = commend.split(" ");
//		int id = Integer.parseInt(detailNum[1]); // 조회하고자 하는 게시글 번호 저장
//
//		Article foundArticle = null; // 찾으려는 게시글을 저장할 객체
//		// foundArticle를 통해 객체 참조
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			if (article.id == id) { // 번호와 일치하면 저장
//				foundArticle = article;
//				break;
//			}
//		}
//		if (foundArticle == null) {
//			System.out.println(id + "번 게시물은 존재하지 않습니다.");
//			return;
//		}
//
//		foundArticle.uphit();
//		System.out.println("==== Detail ====");
//		System.out.println("번호: " + foundArticle.id);
//		System.out.println("제목: " + foundArticle.title);
//		System.out.println("내용: " + foundArticle.body);
//		System.out.println("조회: " + foundArticle.hit);
//		System.out.println("날짜: " + foundArticle.nowDate);
//		System.out.println("작성자: " + foundArticle.author);
//		System.out.println("================");
//	}

//	private void delete (String commend){
//		String[] deleteNum = commend.split(" ");
//		int id = Integer.parseInt(deleteNum[1]);
//		int foundIndex = -1;
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			if (article.id == id) {
//				foundIndex = i;
//				break;
//			}
//		}
//		if (foundIndex == -1) {
//			System.out.println(id + "번째 게시물이 존재하지 않습니다.");
//			return;
//		}
//		articles.remove(foundIndex);
//		System.out.println(id + "번째 게시물이 삭제되었습니다.");
//	}

//	private void update(String commend, Scanner sc){
//		String[] commendBits = commend.split(" ");
//		int id = Integer.parseInt(commendBits[1]);
//		Article foundArticle = null;
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			if (article.id == id) {
//				foundArticle = article;
//				break;
//			}
//		}
//		if (foundArticle == null) {
//			System.out.println("없는 게시물 입니다.");
//			return;
//		}
//		System.out.print("수정 제목: ");
//		String title = sc.nextLine();
//		System.out.print("수정 내용: ");
//		String body = sc.nextLine();
//
//		foundArticle.title = title;
//		foundArticle.body = body;
//		System.out.println(foundArticle.id + "번 게시물 수정 완료");
//	}



