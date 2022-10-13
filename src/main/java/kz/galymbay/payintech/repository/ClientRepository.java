package kz.galymbay.payintech.repository;

import kz.galymbay.payintech.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhoneNumber(String phoneNumber);

    @Override
    Optional<Client> findById(Long id);
}
