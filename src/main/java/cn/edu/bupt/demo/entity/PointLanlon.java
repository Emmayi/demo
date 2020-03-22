package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/12/30 上午11:09
 */
public class PointLanlon {

    @Setter
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String pointName;
    @Getter
    @Setter
    private String lanlon;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"pointName\":\"")
                .append(pointName).append('\"');
        sb.append(",\"lanlon\":\"")
                .append(lanlon).append('\"');
        sb.append('}');

        return sb.toString();
    }
}
