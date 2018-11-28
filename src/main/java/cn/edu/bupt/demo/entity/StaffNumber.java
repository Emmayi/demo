package cn.edu.bupt.demo.entity;

public class StaffNumber extends IdBased{

//    @ApiComment(value = "用户id", sample = "1")
    private Integer id;

//    @ApiComment(value = "用户性别", sample = "女")
    private String gender;

//    @ApiComment(value = "用户电话", sample = "18811653782")
    private String phone;

//    @ApiComment(value = "用户姓名", sample = "李华")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
