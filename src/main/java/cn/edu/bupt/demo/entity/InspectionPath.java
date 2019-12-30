package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/5/29 上午10:38
 */
public class InspectionPath {

    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private String number;
    @Getter@Setter
    private String area_belong;
    @Getter@Setter
    private String pipe_belong;
    @Getter@Setter
    private String startpoint;
    @Getter@Setter
    private String endpoint;
    @Getter@Setter
    private Long create_date;
    @Getter@Setter
    private String description;
    @Getter@Setter
    private String name;
    @Getter@Setter
    private String drawpoint;
    @Getter@Setter
    private Integer status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"status\":")
                .append(status);
        sb.append(",\"number\":\"")
                .append(number).append('\"');
        sb.append(",\"area_belong\":\"")
                .append(area_belong).append('\"');
        sb.append(",\"pipe_belong\":\"")
                .append(pipe_belong).append('\"');
        sb.append(",\"startpoint\":\"")
                .append(startpoint).append('\"');
        sb.append(",\"endpoint\":\"")
                .append(endpoint).append('\"');
        sb.append(",\"create_date\":\"")
                .append(create_date).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"drawpoint\":\"")
                .append(drawpoint).append('\"');
        sb.append('}');

        return sb.toString();
    }

}
