package cn.edu.bupt.demo.entity;

import java.util.Date;

public class EntranceWork extends IdBased{

    private Integer id;
    private String duration;
    private Long date;
    private Integer work_number;
    private String activity_range;
    private String evaluation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getWork_number() {
        return work_number;
    }

    public void setWork_number(Integer work_number) {
        this.work_number = work_number;
    }

    public String getActivity_range() {
        return activity_range;
    }

    public void setActivity_range(String activity_range) {
        this.activity_range = activity_range;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

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
