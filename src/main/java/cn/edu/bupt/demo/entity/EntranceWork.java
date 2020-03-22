package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

public class EntranceWork extends IdBased {

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String duration;
    @Getter
    @Setter
    private Long date;
    @Getter
    @Setter
    private Integer work_number;
    @Getter
    @Setter
    private String activity_range;
    @Getter
    @Setter
    private String evaluation;
    @Getter
    @Setter
    private String contact;
    @Getter
    @Setter
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
        sb.append(",\"contact\":\"")
                .append(contact).append('\"');
        sb.append(",\"contact_number\":\"")
                .append(contact_number).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
