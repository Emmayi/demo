package cn.edu.bupt.demo.entity;

/**
 * @author zy
 * @date 2018/10/25 上午10:18
 */
public class InspectionReport extends IdBased {

    private Integer id;
    private String duty_person;
    private String inspeciton_person;
    private Long date;
    private String condition;
    private String summary;
    private String abnormal;
    private String maintenance;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuty_person() {
        return duty_person;
    }

    public void setDuty_person(String duty_person) {
        this.duty_person = duty_person;
    }

    public String getInspeciton_person() {
        return inspeciton_person;
    }

    public void setInspeciton_person(String inspeciton_person) {
        this.inspeciton_person = inspeciton_person;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(String abnormal) {
        this.abnormal = abnormal;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"duty_person\":")
                .append(duty_person);
        sb.append(",\"inspeciton_person\":\"")
                .append(inspeciton_person).append('\"');
        sb.append(",\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"condition\":\"")
                .append(condition).append('\"');
        sb.append(",\"abnormal\":\"")
                .append(abnormal).append('\"');
        sb.append(",\"maintenance\":\"")
                .append(maintenance).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
