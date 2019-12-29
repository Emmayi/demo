package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/11/18 下午9:43
 */
public class User {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String addtional_info;
    @Getter @Setter
    private String authority;
    @Getter @Setter
    private Integer customer_id;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Integer tenant_id;
    @Getter @Setter
    private String phone;
    @Getter @Setter
    private String wechat;
    @Getter @Setter
    private Integer base_role_id;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"addtional_info\":\"")
                .append(addtional_info).append('\"');
        sb.append(",\"authority\":\"")
                .append(authority).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append("\"customer_id\":")
                .append(customer_id);
        sb.append("\"tenant_id\":")
                .append(tenant_id);
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"wechat\":\"")
                .append(wechat).append('\"');
        sb.append("\"base_role_id\":")
                .append(base_role_id);
        sb.append('}');
        return sb.toString();
    }

}
