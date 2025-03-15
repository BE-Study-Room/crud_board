package study.crudboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.ArrayList;
import java.util.Scanner;
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

		LoginService loginService = new LoginService();
		String username = loginService.start();

		if (username == null) {
			System.out.println("로그인 실패 프로그램 종료");
			return;
		}
		UserName = username;

		System.out.println("======= 게시판 =======");
		Scanner sc = new Scanner(System.in);
		try{
		while (true) {
			System.out.print("write / list [num] / delete [num] / update [num] / exit : ");
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
				String[] commendBits = commend.split(" ");
				int id = 0;

				if (commendBits.length > 1) {
					try {
						id = Integer.parseInt(commendBits[1]);
					} catch (NumberFormatException e) {
						System.out.println("숫자와 함께 입력해주세요.");
						continue;
					}
				}
				articleService.read(id);

			} else if (commend.startsWith("delete ")) {
				String[] commendBits = commend.split(" ");
				int id = Integer.parseInt(commendBits[1]);

				articleService.delete(id);
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
		}
	}
}



