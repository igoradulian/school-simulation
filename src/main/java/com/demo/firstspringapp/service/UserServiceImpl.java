package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.UserDTO;
import com.demo.firstspringapp.repository.RoleRepository;
import com.demo.firstspringapp.repository.UserRepository;
import com.demo.firstspringapp.security.UserPrincipal;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private RoleService roleService;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, @Lazy BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userIdentifier) {
        Optional<User> user = userRepository.findUserByEmail(userIdentifier);
        if(user.isEmpty())
            //TODO add loging here
            throw new UsernameNotFoundException("Invalid login or password");

        //List<Role> list = roleService.getRolesByUser(user.getId());

        /*return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),mapRolesToAuthorities(list));*/
        return new UserPrincipal(user.get(), roleService.getRolesByUser(user.get().getId()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<? extends GrantedAuthority> mapRoles = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRoles;
    }

    @Override
    @Transactional
    public void saveUser(UserDTO userdto) throws UserExistException {

        if(userRepository.findUserByEmail(userdto.getEmail()).isPresent())
        {
            throw new UserExistException("User exist");
        }else {

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            User user = modelMapper.map(userdto, User.class);
            user.setRoles(Arrays.asList(roleService.findRoleByRoleName(userdto.getRole())));
            user.setPassword(encoder.encode(userdto.getPassword()));
            user.setEnabled("Y");

            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) throws UserExistException {

        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if(optionalUser.isPresent())
                return userRepository.findUserByEmail(email).get();

        else
            throw new UserExistException("User does not exist");
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
