package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zy
 * @date 2019/5/29 下午7:34
 */
public class SysLog implements Serializable {

    @Setter
    @Getter
    private Integer id;//日志记录序号

    @Setter
    @Getter
    private String user_id;
    @Setter
    @Getter
    private String user_name;
    @Setter
    @Getter
    private String action;
    @Setter
    @Getter
    private Timestamp create_Time;
    @Setter
    @Getter
    private String account;
    @Setter
    @Getter
    private String role;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"user_id\":\"")
                .append(user_id).append('\"');
        sb.append(",\"user_name\":\"")
                .append(user_name).append('\"');
        sb.append(",\"action\":\"")
                .append(action).append('\"');
        sb.append(",\"create_Time\":\"")
                .append(create_Time).append('\"');
        sb.append(",\"account\":\"")
                .append(account).append('\"');
        sb.append(",\"role\":\"")
                .append(role).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
