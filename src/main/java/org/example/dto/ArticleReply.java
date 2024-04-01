package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReply extends Dto{
    private int articleId;
    private int memberId;
    private String body;

    public ArticleReply(int id, String regDate){
        super(id,regDate);
    }
}