package kz.galymbay.payintech.service;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.Role;
import kz.galymbay.payintech.repository.ClientRepository;
import kz.galymbay.payintech.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Client registration(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Role role_client = roleRepository.findByRoleName("ROLE_CLIENT");
        if (role_client != null)
            client.getRoles().add(role_client);
        return clientRepository.save(client);
    }
}
