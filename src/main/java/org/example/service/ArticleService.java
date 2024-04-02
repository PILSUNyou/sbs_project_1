package org.example.service;

import org.example.container.Container;
import org.example.dao.ArticleDao;
import org.example.dto.Article;
import org.example.dto.Board;


import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private ArticleDao articleDao;

    public ArticleService(){
            articleDao = Container.articleDao;
        }
        public List<Article> getForPrintArticles(String searchKeyword) {
        return articleDao.getForPrintArticles(searchKeyword);
    }
    public List<Article> getForPrintArticles() {
        return this.getForPrintArticles(null);
    }


    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }

    public int getNewId() {
        return articleDao.getNewId();
    }

    public int write(int memberId, int boardId ,String title, String body) {
        Article article = new Article(memberId, boardId, title, body);
        return articleDao.write(article);
    }

    public List<Article> getArticles() {
        return articleDao. getArticles();
    }

    public Board getBoard(int id){
        return articleDao.getBoard(id);
    }

    public Article getForPrintArticle(int id) {
        return articleDao.getForPrintArticle(id);
    }

    public Article getAritcle(int id) {
        return articleDao.getArticle(id);
    }

    public void modify(int id, String title, String body) {
        articleDao.modify(id, title, body);
    }

    public void delete(int id) {
       articleDao.delete(id);
    }
}
