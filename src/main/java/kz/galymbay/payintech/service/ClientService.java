package kz.galymbay.payintech.service;

import kz.galymbay.payintech.entity.AuthenticationDto;
import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.Role;
import kz.galymbay.payintech.exception.ServiceFaultException;
import kz.galymbay.payintech.repository.ClientRepository;
import kz.galymbay.payintech.repository.RoleRepository;
import kz.galymbay.payintech.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registration(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Role role_client = roleRepository.findByRoleName("ROLE_CLIENT");
        if (role_client == null)
            throw new ServiceFaultException("Client role not found", HttpStatus.BAD_REQUEST);
        client.getRoles().add(role_client);
        clientRepository.save(client);
        String token = jwtUtil.generateToken(client.getPhoneNumber());
        return token;
    }

    public String login(AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDto.getPhoneNumber(), authenticationDto.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Invalid user phoneNumber/password");
        }
        return jwtUtil.generateToken(authenticationDto.getPhoneNumber());
    }
}
