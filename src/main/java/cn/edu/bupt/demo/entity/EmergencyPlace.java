package cn.edu.bupt.demo.entity;

public class EmergencyPlace {
    private Integer place_id;
    private String name;


    private String category;
    private String location;
    private Integer area;
    private Integer capacity;
    private String introduction;
    private String affiliation;
    private String principal;
    private String telephone;
    private String cellphone;

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"place_id\":")
                .append(place_id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"category\":\"")
                .append(category).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append(",\"area\":\"")
                .append(area).append('\"');
        sb.append(",\"capacity\":\"")
                .append(capacity).append('\"');
        sb.append(",\"introduction\":\"")
                .append(introduction).append('\"');
        sb.append(",\"affiliation\":\"")
                .append(affiliation).append('\"');
        sb.append(",\"principal\":\"")
                .append(principal).append('\"');
        sb.append(",\"telephone\":\"")
                .append(telephone).append('\"');
        sb.append(",\"cellphone\":\"")
                .append(cellphone).append('\"');
        sb.append('}');
        return sb.toString();

    }
}
