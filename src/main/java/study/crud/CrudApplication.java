package study.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import study.crud.entity.Board;
import study.crud.service.BoardService;

import java.util.Scanner;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CrudApplication {
    public static void main(String[] args) {
        //ApplicationContext ac = SpringApplication.run(CrudApplication.class, args);
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        BoardService boardService = ac.getBean(BoardService.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try{
                System.out.println("1.등록 2.전체조회 3.선택조회 4.수정 5. 삭제 0.종료");
                String choice=scanner.next();
                if(choice.equals("1")){
                    System.out.print("등록할 제목 : ");
                    String title= scanner.next();
                    System.out.print("등록할 내용 : ");
                    String content= scanner.next();
                    System.out.print("등록할 작성자 이름 : ");
                    String writer= scanner.next();
                    Board newBoard = new Board(title, content, writer);
                    boardService.create(newBoard);
                    System.out.println("생성 완료");
                }else if(choice.equals("2")){
                    System.out.println("전체 조회");
                    System.out.println(boardService.findAll());
                } else if(choice.equals("3")){
                    System.out.print("선택 조회");
                    System.out.print("제목 이름 : ");
                    String writer= scanner.next();
                    System.out.println(boardService.findByTitle(writer));
                } else if(choice.equals("4")){
                    System.out.println("수정");
                    System.out.print("수정할 글: ");
                    String findBoardTitle= scanner.next();
                    System.out.print("제목: ");
                    String title = scanner.next();
                    System.out.print("내용: ");
                    String content = scanner.next();
                    System.out.print("작성자 이름: ");
                    String writer = scanner.next();
                    Board boardParam = new Board(title, content, writer);
                    boardService.update(boardParam);
                } else if(choice.equals("5")){
                    System.out.println("삭제");
                    System.out.print("제목 이름 : ");
                    String title = scanner.next();
                    Board findBoard = boardService.findByTitle(title);
                    boardService.delete(findBoard);
                    System.out.println("삭제 완료");
                }
                else if(choice.equals("0")){
                    System.out.println("종료");
                    break;
                }
            }catch (Exception e) {
                System.out.println("오류:"+e);
            }
        }
    }
}



