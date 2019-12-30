package cn.edu.bupt.demo.entity;

import lombok.Getter;
import lombok.Setter;
public class PipeGalleryArea {

    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private String number;
    @Getter@Setter
    private String name;
    @Getter@Setter
    private Integer length;
    @Getter@Setter
    private String pipe_belong;
    @Getter@Setter
    private String startpoint;
    @Getter@Setter
    private String endpoint;
    @Getter@Setter
    private String description;
    @Getter@Setter
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
        sb.append(",\"pipe_belong\":\"")
                .append(pipe_belong).append('\"');
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

}
