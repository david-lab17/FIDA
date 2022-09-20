//package com.deltacode.kcb.helper;
//
//import com.deltacode.kcb.entity.Privileges;
//import com.deltacode.kcb.entity.Role;
//import com.deltacode.kcb.entity.UserApp;
//import com.deltacode.kcb.repository.PrivilegesRepository;
//import com.deltacode.kcb.repository.RoleRepository;
//import com.deltacode.kcb.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//    boolean alreadySetup = false;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PrivilegesRepository privilegesRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (alreadySetup)
//            return;
//        Privileges readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privileges writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        List<Privileges> adminPrivileges = List.of(readPrivilege, writePrivilege);
//
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", List.of(readPrivilege));
//
//        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
//
//        UserApp user = new UserApp();
//        user.setUsername("admin");
//        user.setPassword("admin");
//        user.setEmail("admin@gmail.com");
//        user.setMiddleName("admin");
//        user.setLastName("admin");
//        user.setFirstName("admin");
//        user.setPassword(passwordEncoder.encode("password"));
//        user.setRoles(List.of(adminRole.get()));
//        userRepository.save(user);  //save user
//        alreadySetup = true;
//    }
//
//
//
//    @Transactional
//    private void createRoleIfNotFound(String name, Collection<Privileges> privileges) {
//
//        Optional<Role> role = roleRepository.findByName(name);
//        if (role.isEmpty()) {
//            Role newRole = new Role();
//            newRole.setName(name);
//            newRole.setPrivileges(privileges);
//            roleRepository.save(newRole);
//        }
//
//
//    }
//@Transactional
//    private Privileges createPrivilegeIfNotFound(String name) {
//
//            Optional<Privileges> privilege = Optional.ofNullable(privilegesRepository.findByName(name));
//            if (privilege.isEmpty()) {
//                Privileges newPrivilege = new Privileges();
//                newPrivilege.setName(name);
//                privilegesRepository.save(newPrivilege);
//                return newPrivilege;
//            }
//            return privilege.get();
//    }
//
//
//}
