package cn.edu.bupt.demo.dao.Role;

import java.util.Set;

/**
 * Created by zyf on 2018/11/29.
 */
public interface RoleService {

/*    List<Role> findAllRoles();

    List<Role> findAllRolesByUserId(int user_id);

    List<Role> findExtraRolesByUserId(int user_id);

    List<Role> findNotOwnedExtraRolesByUserId(int user_id);*/

    Set<String> findRolesNameByUserId(int user_id);

/*    Set<String> findDescriptionByUserId(int user_id);

    Integer saveRole(Role role);

    void deleteRoleById(Integer id);

    void updateRole(Role role);

    Role findRoleById(Integer id);

    void saveRoleUserRelation(Integer role_id, Integer user_id);

    void saveUserToRole(Integer user_id);

    void deleteRoleUserRelation(Integer role_id, Integer user_id);

    void deleteRoleUserRelationByUserId(Integer user_id);

    List<Role> findRoleByPermissionId(Integer accessId);*/
}
