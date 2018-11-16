package cn.edu.bupt.demo.entity;

/**
 * @author zy
 * @date 2018/10/25 上午10:18
 */
public class InspectionReport extends IdBased {

    private Integer id;
    private String duty_person;
    private String inspection_person;
    private Long create_date;
    private Long calendar_date;
    private String state;
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

    public String getInspection_person() {
        return inspection_person;
    }

    public void setInspection_person(String inspection_person) {
        this.inspection_person = inspection_person;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }

    public Long getCalendar_date() {
        return calendar_date;
    }

    public void setCalendar_date(Long calendar_date) {
        this.calendar_date = calendar_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
                .append(inspection_person).append('\"');
        sb.append(",\"create_date\":\"")
                .append(create_date).append('\"');
        sb.append(",\"calendar_date\":\"")
                .append(calendar_date).append('\"');
        sb.append(",\"state\":\"")
                .append(state).append('\"');
        sb.append(",\"abnormal\":\"")
                .append(abnormal).append('\"');
        sb.append(",\"maintenance\":\"")
                .append(maintenance).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
