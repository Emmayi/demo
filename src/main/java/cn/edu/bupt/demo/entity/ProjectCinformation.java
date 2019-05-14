package cn.edu.bupt.demo.entity;

public class ProjectCinformation {
    private Integer project_id;
    private String location;
    private Integer area;
    private String drawpoint;
    private String mainequipments;
    private String proequipments;
    private String department;
    private String mainusage;

    public String getMainusage() {
        return mainusage;
    }

    public void setMainusage(String mainusage) {
        this.mainusage = mainusage;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDrawpoint() {
        return drawpoint;
    }

    public void setDrawpoint(String drawpoint) {
        this.drawpoint = drawpoint;
    }

    public String getMainequipments() {
        return mainequipments;
    }

    public void setMainequipments(String mainequipments) {
        this.mainequipments = mainequipments;
    }

    public String getProequipments() {
        return proequipments;
    }

    public void setProequipments(String proequipments) {
        this.proequipments = proequipments;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"project_id\":")
                .append(project_id);
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append(",\"area\":\"")
                .append(area).append('\"');
        sb.append(",\"drawpoint\":\"")
                .append(drawpoint).append('\"');
        sb.append(",\"mainequipments\":\"")
                .append(mainequipments).append('\"');
        sb.append(",\"proequipments\":\"")
                .append(proequipments).append('\"');
        sb.append(",\"department\":\"")
                .append(department).append('\"');
        sb.append(",\"mainusage\":\"")
                .append(mainusage).append('\"');
        sb.append('}');
        return sb.toString();

    }
}
