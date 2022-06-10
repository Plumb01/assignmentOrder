package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.dtos.RegistrationDto;
import com.itc25.assignmentorder.execptions.EntityNotFoundExecption;
import com.itc25.assignmentorder.models.Member;
import com.itc25.assignmentorder.models.User;
import com.itc25.assignmentorder.repositories.MemberRepository;
import com.itc25.assignmentorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registration(String memberId, RegistrationDto registrant){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundExecption("id employee tidak ditemukan"));

        List<User> userListDummy = userRepository.findAll();

        for (User userDummy: userListDummy){
            if(userDummy.getId().equals(memberId)){
                throw new EntityNotFoundExecption("id employee telah memiliki user profile");
            }
        }

        User user = new User(registrant.getUsername(),registrant.getPassword());



        user.setMember(member);

        if(member.getId().indexOf("A") != -1){
            user.setRole("ADMIN");
        }else if(member.getId().indexOf("B") != -1) {
            user.setRole("STAFF_MEMBER");
        }else if(member.getId().indexOf("C") != -1) {
            user.setRole("CHAIRMAN");
        }else {
            throw new EntityNotFoundExecption("id employee tidak sesuai");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user.getPassword();

        return true;
    }
}
