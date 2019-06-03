package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class EntranceWork extends IdBased{

    @Setter@Getter
    private Integer id;
    @Setter@Getter
    private String duration;
    @Setter@Getter
    private Long date;
    @Setter@Getter
    private Integer work_number;
    @Setter@Getter
    private String activity_range;
    @Setter@Getter
    private String evaluation;
    @Setter@Getter
    private String contact;
    @Setter@Getter
    private String contact_number;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"work_number\":")
                .append(work_number);
        sb.append(",\"duration\":\"")
                .append(duration).append('\"');
        sb.append(",\"activity_range\":\"")
                .append(activity_range).append('\"');
        sb.append(",\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"evaluation\":\"")
                .append(evaluation).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
