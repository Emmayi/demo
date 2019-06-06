package cn.edu.bupt.demo.dao.EmergencySupplies;

import cn.edu.bupt.demo.entity.EmergencySupplies;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zy
 * @date 2018/11/24 下午4:52
 */
@Mapper
public interface SuppliesRespository {

    @Select("select * from emergency_supplies limit #{index},#{pageSize}")
    List<EmergencySupplies> findAllByPage(@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select * from emergency_supplies where category = #{category} limit #{index},#{pageSize}")
    List<EmergencySupplies> findSuppliesByCategoryAndPage(@Param("category")String category,@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select * from emergency_supplies where supply_id = #{supply_id}")
    EmergencySupplies findEmergencySuppliesById(Integer supply_id);

    @Select("select count(*) from emergency_supplies")
    Integer AllSuppliesCount();

    @Select("select count(*) from emergency_supplies where category = #{category}")
    Integer SuppliesCountOfCategory(String category);

    @Insert("insert into emergency_supplies (name,category,quantity,model,purchase_date,manufacturer,manufacture_date,valid_until,use_description,performance_description,affiliation,location)" +
            " values (#{name},#{category},#{quantity},#{model},#{purchase_date},#{manufacturer},#{manufacture_date},#{valid_until},#{use_description},#{performance_description},#{affiliation},#{location})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(EmergencySupplies emergencySupplies);

    @Update("update emergency_supplies set name = #{name},category = #{category},quantity = #{quantity},model = #{model},purchase_date = #{purchase_date},manufacturer = #{manufacturer}, " +
            "manufacture_date = #{manufacture_date},valid_until = #{valid_until},use_description = #{use_description},performance_description = #{performance_description},affiliation = #{affiliation},location = #{location} where supply_id=#{supply_id}")
    void update(EmergencySupplies emergencySupplies);

    @Delete("delete from emergency_supplies where supply_id=#{supply_id}")
    void deleteById(Integer supply_id);

    @Select("select * from emergency_supplies where supply_id > 0")
    List<EmergencySupplies> findAllSupplies();

}
