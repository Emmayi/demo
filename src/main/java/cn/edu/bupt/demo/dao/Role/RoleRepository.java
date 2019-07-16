package cn.edu.bupt.demo.dao.Role;

import cn.edu.bupt.demo.annotation.PermissionAop;
import cn.edu.bupt.demo.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * Created by zyf on 2018/11/29.
 */

@Mapper
public interface RoleRepository {

    @Select("select  id  as id,name as name,description as description from role")
    @ResultType(Role.class)
    @PermissionAop("test")
    List<Role> findAll();

    @Select("select id  as id,name as name,description as description from role where id in (select role_id from role_user_relation where user_id = #{user_id})")
    List<Role> findAllByUserId(int user_id);

    @Select("select id  as id,name as name,description as description from role where id in (select role_id from role_user_relation where user_id = #{user_id}) and id > 3")
    List<Role> findExtraByUserId(int user_id);

    @Select("select id  as id,name as name,description as description from role where id not in (select role_id from role_user_relation where user_id = #{user_id}) and id > 3")
    List<Role> findNotOwnedExtraByUserId(int user_id);

    @Select("select name as name from role where id in (select role_id from role_user_relation where user_id = #{user_id})")
    Set<String> findRolesNameByUserId(int user_id);

    @Select("select description as description from role where id in (select role_id from role_user_relation where user_id = #{user_id})")
    Set<String> findDescriptionByUserId(int user_id);

    @Select("select  id  as id,name as name,description as description from role where id=#{id}")
    Role findById(Integer id);

    @Insert("insert into role (name,description) values (#{name},#{description}) ")
    @Options(useGeneratedKeys = true,keyProperty="id")
    Integer saveRole(Role role);

    @Delete("delete from role where id = #{id}")
    void deleteById(Integer id);

    @Update("update role set name = #{name},description = #{description} where id=#{id}")
    void update(Role role);

    @Delete("delete from role_user_relation where role_id = #{role_id}")
    void deleteRoleUserRelationByRoleId(Integer role_id);

    @Delete("delete from role_user_relation where user_id = #{user_id}")
    void deleteRoleUserRelationByUserId(Integer user_id);

    @Delete("delete from role_permission_relation where role_id = #{role_id}")
    void deleteRolePermissionRelation(Integer role_id);

    @Insert("insert into role_user_relation (role_id,user_id) values (#{role_id},#{user_id}) ")
    void saveRoleUserRelation(@Param("role_id") Integer role_id, @Param("user_id") Integer user_id);

    @Insert("insert into role_user_relation (role_id,user_id) values (14,#{user_id}) ")
    void saveUserToRole(@Param("user_id") Integer user_id);

    @Delete("delete from role_user_relation where role_id = #{role_id} and user_id = #{user_id}")
    void deleteRoleUserRelation(@Param("role_id") Integer role_id, @Param("user_id") Integer user_id);

    @Select("SELECT p.id,p.name,p.description FROM permission p,role_permission_relation rp,role r WHERE p.id = #{permission_id} AND p.id = rp.permission_id AND rp.role_id=r.id ")
    List<Role> findRoleByPermissionId(Integer permission_id);
}
