package com.deltacode.kcb.service;

import com.deltacode.kcb.entity.Privilege;
import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.repository.PrivilegeRepository;
import com.deltacode.kcb.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service

public class PrivilegeService {
    public final PrivilegeRepository privilegeRepository;
    
    public final RoleRepository roleRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository, RoleRepository roleRepository) {
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
    }
    public List<Privilege> getPrivilege(){
        return privilegeRepository.findAll();
    }

    public void assignPrivilege(Long roleId, Long privilegeId){
        Role role = roleRepository.findById(roleId).orElse(null);
        Privilege privilege = privilegeRepository.findById(privilegeId).orElse(null);
        Set<Privilege> rolePrivileges = (Set<Privilege>) role.getPrivileges();
        rolePrivileges.add(privilege);
        role.setPrivileges(rolePrivileges);
        roleRepository.save(role);
    }
    public void unassignPrivilege(Long roleId, Long privilegeId){
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Privilege> rolePrivileges = (Set<Privilege>) role.getPrivileges();
        rolePrivileges.removeIf(x -> x.getId().equals(privilegeId));
        role.setPrivileges(rolePrivileges);
        roleRepository.save(role);
    }

    public void save(Privilege privilege) {
        privilegeRepository.save(privilege);
    }
    public Set<Privilege> getRolePrivileges(Role role){
        return (Set<Privilege>) role.getPrivileges();
    }
   public List<Privilege> getRoleNotPrivilege(Long roleId){
        return privilegeRepository.getRoleNotPrivilege(roleId);
    }
    public void delete(long id) {
        privilegeRepository.delete(findById(id));
    }

    public Privilege findById(long id) {
        return privilegeRepository.findById(id).orElse(null);
    }
}
