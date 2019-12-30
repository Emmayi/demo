package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

/**
 * @author zy
 * @date 2018/10/25 上午10:18
 */
public class InspectionReport extends IdBased {


    private Integer id;
    @Getter@Setter
    private String duty_person;
    @Getter@Setter
    private String inspection_person;
    @Getter@Setter
    private Long create_date;
    @Getter@Setter
    private Long calendar_date;
    @Getter@Setter
    private Integer state;
    @Getter@Setter
    private String summary;
    @Getter@Setter
    private String abnormal;
    @Getter@Setter
    private String maintenance;
    @Getter@Setter
    private String image;
    @Getter@Setter
    private String video;
    @Getter@Setter
    private Integer plan_id;
    @Getter@Setter
    private String maintenanceDescription;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"plan_id\":")
                .append(plan_id);
        sb.append(",\"duty_person\":\"")
                .append(duty_person).append('\"');
        sb.append(",\"inspection_person\":\"")
                .append(inspection_person).append('\"');
        sb.append(",\"create_date\":\"")
                .append(create_date).append('\"');
        sb.append(",\"calendar_date\":\"")
                .append(calendar_date).append('\"');
        sb.append(",\"state\":")
                .append(state);
        sb.append(",\"summary\":\"")
                .append(summary).append('\"');
        sb.append(",\"abnormal\":")
                .append(abnormal);
        sb.append(",\"maintenance\":\"")
                .append(maintenance).append('\"');
        sb.append(",\"image\":\"")
                .append(image).append('\"');
        sb.append(",\"video\":\"")
                .append(video).append('\"');
        sb.append(",\"maintenanceDescription\":\"")
                .append(maintenanceDescription).append('\"');
        sb.append('}');

        return sb.toString();
    }
}
