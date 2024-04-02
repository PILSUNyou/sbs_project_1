package org.example.controller;

import org.example.container.Container;
import org.example.dto.Article;
import org.example.dto.Member;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private Sesstion sesstion;

    private MemberService memberService;

    public MemberController() {
        sc = Container.getScanner();
        sesstion = Container.getSesstion();
        memberService = Container.memberService;
    }

    public void doAction(String cmd, String actionMethodName) {
        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.printf("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void doJoin() {
        String loginId = null;

        while (true){
            System.out.printf("사용할 ID를 입력하세요 : ");
            loginId = sc.nextLine();

            if (isJoinableLoginId(loginId) == false){
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }
            break;
        }

        String loginPw= null;
        String loginPwConfirm = null;

        while (true){
            System.out.print("패스워드를 입력하세요 : ");
            loginPw = sc.nextLine();
            System.out.print("패스워드 확인 : ");
            loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("패스워드가 일치하지 않습니다. 다시 작성해 주세요.");
                continue;
            }
            break;
        }


        System.out.printf("이름을 입력하세요 : ");
        String name = sc.nextLine();

        memberService.join(loginId, loginPw, name);

        System.out.printf("[%s]회원가입이 완료되었습니다 ! 환영합니다. :))\n", name);
    }
    public void doLogin(){
        System.out.printf("ID : ");
        String loginId = sc.nextLine();
        System.out.printf("PassWord : ");
        String loginPw = sc.nextLine();

        // 입력받은 아이디에 해당하는 회원이 존재하는지 확인
        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null){
            System.out.println("해당 회원은 존재하지 않습니다.");
            return;
        }
        if (member.loginPw.equals(loginPw) == false){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        sesstion.setLoginedMember(member);
        Member loginedMember = sesstion.getLoginedMember();

        System.out.printf("로그인 성공! %s님 환영합니다.\n",loginedMember.name);
    }

    private void doLogout() {
        sesstion.setLoginedMember(null);
        System.out.printf("로그아웃 되었습니다.\n");
    }
    private boolean isJoinableLoginId(String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);

        if(member == null){
            return true;
        }
        return false;
    }

}
