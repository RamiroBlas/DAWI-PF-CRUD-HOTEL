package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.entity.User;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // consultar usuario por username
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // retornar un UserDetails que sera usado por Spring Security
        User user = optional.get();
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        userBuilder.password(user.getPassword()); // la contraeña esta hasheada en la BD
        userBuilder.roles(user.getRole());

        return userBuilder.build();
    }

}
