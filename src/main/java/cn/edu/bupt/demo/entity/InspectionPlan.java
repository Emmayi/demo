package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zy
 * @date 2019/4/19 上午9:35
 */
public class InspectionPlan {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String inspection_person;

    @Getter @Setter
    private Long create_date;

    @Getter @Setter
    private Long inspection_date;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"inspection_person\":\"")
                .append(inspection_person).append('\"');
        sb.append(",\"create_date\":\"")
                .append(create_date).append('\"');
        sb.append(",\"inspection_date\":\"")
                .append(inspection_date).append('\"');
        sb.append('}');

        return sb.toString();
    }
}
