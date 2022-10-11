package com.deltacode.kcb.controller;

import com.deltacode.kcb.entity.Privilege;
import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.service.PrivilegeService;
import com.deltacode.kcb.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Privilege Management")
@RestController()

@RequestMapping(path = "/api/v1")
public class PrivilegeController {
    public final RoleService roleService;
    public  final PrivilegeService privilegeService;

    public PrivilegeController(RoleService roleService, PrivilegeService privilegeService) {
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @GetMapping("privileges/findById/{id}")
    @ResponseBody
    public Privilege findById(@PathVariable Long id) {
        return privilegeService.findById(id);
    }
    @PutMapping(value="/privileges/update")
    public String update(Privilege privilege) {
        privilegeService.save(privilege);
        return "redirect:/privileges";
    }
    // Get all privileges
    @GetMapping("/privileges")
    public List<Privilege> getAllPrivileges() {
        return privilegeService.getPrivilege();
    }
    //assign privilege to role
    @PostMapping("/privileges/assign/{roleId}/{privilegeId}")
    public String assignPrivilegeToRole(@PathVariable Long roleId,
                                        @PathVariable Long privilegeId){
        privilegeService.assignPrivilege(roleId, privilegeId);
        return "redirect:/role/Edit/"+roleId;
    }
    //unassign privilege to role
    @DeleteMapping("/privileges/unassign/{roleId}/{privilegeId}")
    public String unassignPrivilegeToRole(@PathVariable Long roleId,
                                          @PathVariable Long privilegeId){
        privilegeService.unassignPrivilege(roleId, privilegeId);
        return "redirect:/role/Edit/"+roleId;
    }
    //delete privilege
    @DeleteMapping("/privileges/delete/{id}")
    public String deletePrivilege(@PathVariable Long id){
        privilegeService.delete(id);
        return "redirect:/privileges";
    }
    //create privilege
    @PostMapping("/privileges/create")
    public String createPrivilege(Privilege privilege){
        privilegeService.save(privilege);
        return "redirect:/privileges";
    }
}
