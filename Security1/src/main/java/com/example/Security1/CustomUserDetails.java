//package com.example.Security1;
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final UserInfo userInfo;
//
//    public CustomUserDetails(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
////    private final UserInfo userInfo;
////    private final PasswordEncoder passwordEncoder;
////
////    public CustomUserDetails(UserInfo userInfo, PasswordEncoder passwordEncoder) {
////        this.userInfo = userInfo;
////        this.passwordEncoder = passwordEncoder;
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return userInfo.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//       return userInfo.getPassword();
//        //return passwordEncoder.encode(userInfo.getPassword());
//    }
//
//    @Override
//    public String getUsername() {
//        return userInfo.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return userInfo.isEnabled();
//    }
//}
