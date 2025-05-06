package com.gradebook.system.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Teacher;
import com.gradebook.system.repository.TeacherRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TeacherRepository teachRepository;

    // Manually define the constructor for dependency injection
    public CustomUserDetailsService(TeacherRepository teacherRepository) {
        this.teachRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teachRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Assuming the user has roles, you can dynamically fetch them
        return org.springframework.security.core.userdetails.User.builder()
                .username(teacher.getEmail())
                .password(teacher.getPassword())
                .build();
    }
}
