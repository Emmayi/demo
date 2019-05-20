package cn.edu.bupt.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/5/8 上午11:25
 */
@Data
public class InspectionEquip {

    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private String name;
    @Getter@Setter
    private String content;
    @Getter@Setter
    private String period;
    @Getter@Setter
    private Long last_time;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"period\":\"")
                .append(period).append('\"');
        sb.append(",\"last_time\":\"")
                .append(last_time).append('\"');
        sb.append('}');

        return sb.toString();
    }

}
