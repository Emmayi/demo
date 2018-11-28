package cn.edu.bupt.demo.entity;

/**
 * @author zy
 * @date 2018/11/26 下午4:40
 */
public class EmergencySupplies extends IdBased{

    private Integer supply_id;
    private String name;
    private String category;
    private Integer quantity;
    private String model;
    private Long purchase_date;
    private String manufacturer;
    private Long manufacture_date;
    private Long valid_until;
    private String use_description;
    private String performance_description;
    private String affiliation;
    private String location;

    public Integer getSupply_id() {
        return supply_id;
    }

    public void setSupply_id(Integer supply_id) {
        this.supply_id = supply_id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Long purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(Long manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    public Long getValid_until() {
        return valid_until;
    }

    public void setValid_until(Long valid_until) {
        this.valid_until = valid_until;
    }

    public String getUse_description() {
        return use_description;
    }

    public void setUse_description(String use_description) {
        this.use_description = use_description;
    }

    public String getPerformance_description() {
        return performance_description;
    }

    public void setPerformance_description(String performance_description) {
        this.performance_description = performance_description;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
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
        sb.append("\"supply_id\":")
                .append(supply_id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"category\":\"")
                .append(category).append('\"');
        sb.append(",\"quantity\":\"")
                .append(quantity).append('\"');
        sb.append(",\"model\":\"")
                .append(model).append('\"');
        sb.append(",\"purchase_date\":\"")
                .append(purchase_date).append('\"');
        sb.append(",\"manufacturer\":\"")
                .append(manufacturer).append('\"');
        sb.append(",\"manufacture_date\":\"")
                .append(manufacture_date).append('\"');
        sb.append(",\"valid_until\":\"")
                .append(valid_until).append('\"');
        sb.append(",\"use_description\":\"")
                .append(use_description).append('\"');
        sb.append(",\"performance_description\":\"")
                .append(performance_description).append('\"');
        sb.append(",\"affiliation\":\"")
                .append(affiliation).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append('}');
        return sb.toString();

    }

}
