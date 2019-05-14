package cn.edu.bupt.demo.entity;

public class EmergencyTeam {
    private Integer team_id;
    private String name;
    private String category;
    private Integer level;
    private String specialty;
    private String introduction;
    private String affiliation;
    private String principal;
    private String phone;
    private String location;

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"team_id\":")
                .append(team_id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"category\":\"")
                .append(category).append('\"');
        sb.append(",\"level\":\"")
                .append(level).append('\"');
        sb.append(",\"specialty\":\"")
                .append(specialty).append('\"');
        sb.append(",\"introduction\":\"")
                .append(introduction).append('\"');
        sb.append(",\"affiliation\":\"")
                .append(affiliation).append('\"');
        sb.append(",\"principal\":\"")
                .append(principal).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append('}');
        return sb.toString();

    }
}
