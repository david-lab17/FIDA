package com.deltacode.kcb.controller;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.service.RoleService;
import com.deltacode.kcb.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@Api(value = "Role Management")
@RestController()

@RequestMapping(path = "/api/v1")
public class RoleController {
    private final RoleService roleService;

    private final UserService userService;

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/roles")
    public String getStates(Model model) {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roles", roleList);
        return roleList.toString();
    }
    @GetMapping("/user/Edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        UserApp user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/userEdit";
    }
    @PostMapping("/roles/addNew")
    public String addNew(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }
    @GetMapping("roles/findById")
    @ResponseBody
    public Role findById(long id) {
        return roleService.findById(id);
    }

    @PutMapping(value="/roles/update")
    public String update(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }
    @DeleteMapping(value="/roles/delete/{roleId}")
    public String delete(Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
    @PostMapping("/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable Long roleId){
        roleService.assignRole(userId, roleId);
        return "redirect:/user/Edit/"+userId;
    }
    @DeleteMapping("/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Long userId,
                               @PathVariable Long roleId){
        roleService.unassignRole(userId, roleId);
        return "redirect:/user/Edit/"+userId;
    }


}
