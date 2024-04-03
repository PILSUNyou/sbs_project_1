package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.MemberController;
import org.example.db.DBConnection;
import org.example.service.ExportService;

import java.awt.event.ContainerListener;
import java.util.Scanner;

public class App {
    public App() {
        DBConnection.DB_NAME = "sbs_proj";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

        // 현재 게시판을 1번 게시판으로 선택
        Container.getSesstion().setCurrentBoard(Container.articleService.getBoard(2));
    }
    public void start() {
        System.out.println("== 프로그램 시작 ==");
        System.out.println("= 명령어 모음 =");
        System.out.println("1. 회원가입 : member join");
        System.out.println("2. 로그인/회원가입 : member login/logout");
        System.out.println("3. 현재 게시판 확인 : article currentBoard");
        System.out.println("4. 게시판 변경 : article changeBoard");
        System.out.println("5. 게시물 리스트 : article list");
        System.out.println("6. 게시물 상세 확인 : article detail");
        System.out.println("7. 게시물 작성(로그인 후 사용가능) : article write");
        System.out.println("8. 게시물 수정/삭제(로그인 후 사용가능) : article modify/delete");

        MemberController memberController = new MemberController();
        Controller articleController = new ArticleController();
        ExportController exportController = new ExportController();

        // 게시물 프로그램 실행
        while (true) {
            System.out.print("명령어 입력 : ");
            String cmd = Container.getScanner().nextLine();
            cmd = cmd.trim();
            // cmd에 명령어를 입력하여 게시물 관리
            if (cmd.length()==0){
                System.out.print("명령어를 입력하세요.");
                continue;
            }
            // exit를 입력하면 게시물 관리를 끝내고 실행 종료
            if (cmd.equals("exit")){
                break;
            }
            String[] cmdBits = cmd.split(" ");

            if(cmdBits.length == 1){
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }
                String controllerName = cmdBits[0];
                String actionMethodName = cmdBits[1];
                Controller controller = null;

            if (controllerName.equals("article")){
                controller = articleController;
            }
            else if(controllerName.equals("member")){
                controller = memberController;
            }
            else if(controllerName.equals("export")){
                controller = exportController;
            }
            else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }

            String actionName = controllerName + "/" + actionMethodName;
            switch (actionName){
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if(Container.getSesstion().isLogined() == false){
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            switch (actionName){
                case "member/login":
                case "member/join":
                    if(Container.getSesstion().isLogined()){
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }
            controller.doAction(cmd, actionMethodName);

        }
        Container.getDBConnection().close();
        Container.getScanner().close();

        System.out.println("== 프로그램 끝 ==");
    }
}
