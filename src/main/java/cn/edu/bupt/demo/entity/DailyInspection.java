package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/4/26 上午10:18
 */
public class DailyInspection extends IdBased {

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Long time;
    @Getter
    @Setter
    private String inspection_person;
    @Getter
    @Setter
    private String path;
    @Getter
    @Setter
    private String work;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"time\":\"")
                .append(time).append('\"');
        sb.append(",\"inspection_person\":\"")
                .append(inspection_person).append('\"');
        sb.append(",\"path\":\"")
                .append(path).append('\"');
        sb.append(",\"work\":\"")
                .append(work).append('\"');
        sb.append('}');

        return sb.toString();
    }

}
