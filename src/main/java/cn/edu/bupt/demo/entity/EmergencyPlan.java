package cn.edu.bupt.demo.entity;

/**
 * @author zy
 * @date 2018/11/24 下午9:59
 */
public class EmergencyPlan extends IdBased{

    private Integer emergency_id;
    private String name;
    private String category;
    private Integer level;
    private String associated_event_type;
    private String content;
    private String department;
    private Long release_date;
    private String release_number;
    private String issued;
    private String signer;
    private String file;

    public Integer getEmergency_id() {
        return emergency_id;
    }

    public void setEmergency_id(Integer emergency_id) {
        this.emergency_id = emergency_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAssociated_event_type() {
        return associated_event_type;
    }

    public void setAssociated_event_type(String associated_event_type) {
        this.associated_event_type = associated_event_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Long release_date) {
        this.release_date = release_date;
    }

    public String getRelease_number() {
        return release_number;
    }

    public void setRelease_number(String release_number) {
        this.release_number = release_number;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"emergency_id\":")
                .append(emergency_id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"category\":\"")
                .append(category).append('\"');
        sb.append(",\"level\":\"")
                .append(level).append('\"');
        sb.append(",\"associated_event_type\":\"")
                .append(associated_event_type).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"department\":\"")
                .append(department).append('\"');
        sb.append(",\"release_date\":\"")
                .append(release_date).append('\"');
        sb.append(",\"release_number\":\"")
                .append(release_number).append('\"');
        sb.append(",\"issued\":\"")
                .append(issued).append('\"');
        sb.append(",\"signer\":\"")
                .append(signer).append('\"');
        sb.append(",\"file\":\"")
                .append(file).append('\"');
        sb.append('}');
        return sb.toString();

    }
}
