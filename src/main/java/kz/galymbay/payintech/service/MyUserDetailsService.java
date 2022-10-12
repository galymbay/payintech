package kz.galymbay.payintech.service;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.MyUserPrincipal;
import kz.galymbay.payintech.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private ClientRepository clientRepository;

    public MyUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Client client = clientRepository.findByPhoneNumber(phoneNumber);
        if (client == null) throw new UsernameNotFoundException(phoneNumber);

        return new MyUserPrincipal(client);
    }
}
