package cn.edu.bupt.demo.dao.Role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by zyf on 2018/11/29.
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    public static final Integer DEFAULT_ROLES_NUM = 3;

    @Autowired
    private RoleRepository roleRepository;

/*    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllRolesByUserId(int user_id) {
        return roleRepository.findAllByUserId(user_id);
    }

    @Override
    public List<Role> findExtraRolesByUserId(int user_id) {
        return roleRepository.findExtraByUserId(user_id);
    }

    @Override
    public List<Role> findNotOwnedExtraRolesByUserId(int user_id) {
        return roleRepository.findNotOwnedExtraByUserId(user_id);
    }*/

    @Override
    public Set<String> findRolesNameByUserId(int user_id) {
        return roleRepository.findRolesNameByUserId(user_id);
    }

 /*   @Override
    public Set<String> findDescriptionByUserId(int user_id) {
        return roleRepository.findDescriptionByUserId(user_id);
    }

    @Override
    public Integer saveRole(Role role) {
        return roleRepository.saveRole(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        if(id != null && id > DEFAULT_ROLES_NUM) {
            roleRepository.deleteRolePermissionRelation(id);
            roleRepository.deleteRoleUserRelationByRoleId(id);
            roleRepository.deleteById(id);
        }else{
            throw new DataValidationException("Role Id should be specified and default roles can't be deleted!");
        }
    }

    @Override
    public void deleteRoleUserRelationByUserId(Integer user_id) {
        roleRepository.deleteRoleUserRelationByUserId(user_id);
    }

    @Override
    public void updateRole(Role role) {
        if((Integer)role.getId()==null){
            throw new DataValidationException("Role Id should be specified!");
        }
        roleRepository.update(role);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public void saveRoleUserRelation(Integer role_id, Integer user_id) {
        if(role_id != null && role_id > DEFAULT_ROLES_NUM) {
            roleRepository.saveRoleUserRelation(role_id,user_id);
        }else {
            throw new DataValidationException("You can't assign a default role to user!");
        }
    }

    @Override
    public void saveUserToRole(Integer user_id) {
        if(user_id != null && user_id > DEFAULT_ROLES_NUM) {
            roleRepository.saveUserToRole(user_id);
        }else {
            throw new DataValidationException("You can't assign a default role to user!");
        }
    }

    @Override
    public void deleteRoleUserRelation(Integer role_id, Integer user_id) {
        if(role_id != null && role_id > DEFAULT_ROLES_NUM) {
            roleRepository.deleteRoleUserRelation(role_id,user_id);
        }else {
            throw new DataValidationException("You can't delete a default role from user!");
        }
    }

    @Override
    public List<Role> findRoleByPermissionId(Integer permission_id) {
        log.trace("Executing findRoleByPermissionId [{}]", permission_id);
        return roleRepository.findRoleByPermissionId(permission_id);
    }*/
}
