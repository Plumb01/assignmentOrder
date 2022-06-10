package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.models.MyUserDetails;
import com.itc25.assignmentorder.models.User;
import com.itc25.assignmentorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //nyari berdasarkan ID
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username tidak ditemukan"));
        //mengubah dari user -> UserDetails
        return new MyUserDetails(user);
    }
}
