package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;

public class PipeGallery {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String number;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer length;
    @Getter
    @Setter
    private String unit;
    @Getter
    @Setter
    private String startpoint;
    @Getter
    @Setter
    private String endpoint;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String drawpoint;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"number\":\"")
                .append(number).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"length\":\"")
                .append(length).append('\"');
        sb.append(",\"unit\":\"")
                .append(unit).append('\"');
        sb.append(",\"startpoint\":\"")
                .append(startpoint).append('\"');
        sb.append(",\"endpoint\":\"")
                .append(endpoint).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"drawpoint\":\"")
                .append(drawpoint).append('\"');
        sb.append('}');

        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDrawpoint() {
        return drawpoint;
    }

    public void setDrawpoint(String drawpoint) {
        this.drawpoint = drawpoint;
    }
}
