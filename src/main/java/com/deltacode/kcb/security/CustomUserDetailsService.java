package com.deltacode.kcb.security;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; //repository for user

    public CustomUserDetailsService( UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    //implementation of UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserApp userApp = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return new User(userApp.getUsername(), userApp.getPassword(),mapRolesToAuthorities(userApp.getRoles()));
    }

   // map roles to authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(  role.getName()))
                .collect(Collectors.toList());
    }

//    private List<String> getPrivileges(Collection<Role> roles) {
//
//        List<String> privileges = new ArrayList<>();
//        List<Privileges> collection = new ArrayList<>();
//        for (Role role : roles) {
//            privileges.add(role.getName());
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privileges item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }

}
//@Override
//public Collection<? extends GrantedAuthority> getAuthorities() {
//    Set<Role> roles = user.getRoles();
//    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//    for (Role role : roles) {
//        authorities.add(new SimpleGrantedAuthority(role.getName()));
//    }
//
//    return authorities;
//}

// private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
//       return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//
//    }

//set up roles and privileges
//    private void setUpRolesAndPrivileges() {
//        //create roles
//        Role adminRole = new Role("ADMIN");
//        Role userRole = new Role("USER");
//        //create privileges
//        Privilege readPrivilege = new Privilege("READ_PRIVILEGE");
//        Privilege writePrivilege = new Privilege("WRITE_PRIVILEGE");
//        //add privileges to roles
//        adminRole.getPrivileges().add(readPrivilege);
//        adminRole.getPrivileges().add(writePrivilege);
//        userRole.getPrivileges().add(readPrivilege);
//        //save roles to db
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//        //save privileges to db
//        privilegeRepository.save(readPrivilege);
//        privilegeRepository.save(writePrivilege);
//    }