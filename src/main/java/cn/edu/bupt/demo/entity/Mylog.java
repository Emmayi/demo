package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * @author zy
 * @date 2019/5/29 下午7:34
 */
public class Mylog implements Serializable{

    @Setter@Getter
    private Long id;//工号
    @Setter@Getter
    private String username;
    @Setter@Getter
    private String operation;
    @Setter@Getter
    private String ip;
    @Setter@Getter
    private Data createDate;

}
