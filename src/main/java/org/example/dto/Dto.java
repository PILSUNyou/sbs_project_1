package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

import java.util.Map;

@Getter
@Setter
public class Dto {
    public int id; // DTO
    public String regDate;

    public  Dto(){
        this(0);
    }
    public Dto(int id){
        this(id, Util.getNowDateStr());
    }
    public Dto(int id, String regData){
        this.id =id;
        this.regDate = regData;
    }
    public Dto(Map<String, Object> row){
        this((int)row.get("id"), (String) row.get("regDate"));
    }
}
