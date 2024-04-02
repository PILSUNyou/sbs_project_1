package org.example.container;

import org.example.controller.Sesstion;
import org.example.dao.ArticleDao;
import org.example.dao.MemberDao;
import org.example.db.DBConnection;
import org.example.service.ArticleService;
import org.example.service.ExportService;
import org.example.service.MemberService;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static DBConnection dbConnection;
    public static ArticleDao articleDao;
    public static MemberDao memberDao;
    public static ArticleService articleService;
    public static MemberService memberService;
    public static ExportService exportService;
    public static Sesstion sesstion;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
        articleService = new ArticleService();
        memberService = new MemberService();
        exportService = new ExportService();
    }


    public static DBConnection getDBConnection() {
        if ( dbConnection == null ) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }

    public static Scanner getScanner(){
        if(sc == null){
            sc = new Scanner(System.in);
        }
        return sc;
    }

    public static Sesstion getSesstion() {
        if (sesstion == null) {
            sesstion = new Sesstion();
        }

        return sesstion;
    }
}
