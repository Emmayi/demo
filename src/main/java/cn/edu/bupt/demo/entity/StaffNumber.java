package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class StaffNumber extends IdBased{

//    @ApiComment(value = "用户id", sample = "1")
    @Getter@Setter
    private Integer id;

//    @ApiComment(value = "用户性别", sample = "女")
    @Getter@Setter
    private String gender;

//    @ApiComment(value = "用户电话", sample = "18811653782")
    @Getter@Setter
    private String phone;

//    @ApiComment(value = "用户姓名", sample = "李华")
    @Getter@Setter
    private String name;

    @Getter@Setter
    private String email;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
