package com.deltacode.kcb.service;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.repository.RoleRepository;
import com.deltacode.kcb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public void save(Role client) {
        roleRepository.save(client);
    }

    public Role findById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    //delete role
    public void delete(long id) {
        roleRepository.delete(findById(id));
    }

    public void assignRole(Long userId, Long roleId){
        UserApp user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = (Set<Role>) user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public void unassignRole(Long userId, Long roleId){
        UserApp user = userRepository.findById(userId).orElse(null);
        Set<Role> userRoles = (Set<Role>) user.getRoles();
        userRoles.removeIf(x -> Objects.equals(x.getId(), roleId));
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(UserApp user){
        return (Set<Role>) user.getRoles();
    }


    public List<Role> getUserNotRoles(UserApp user){
        return roleRepository.getUserNotRoles(user.getId());
    }
}
