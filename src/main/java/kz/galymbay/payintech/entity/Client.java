package kz.galymbay.payintech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_firstname", nullable = false)
    private String firstName;

    @Column(name = "client_lastname", nullable = false)
    private String lastName;

    @Column(name = "client_patronymic")
    private String patronymic;

    @Column(name = "client_iin", nullable = false, unique = true)
    private String iin;

    @Column(name = "client_phone", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "client_password", nullable = false)
    private String password;

    @Column(name = "client_address", nullable = false)
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Loan> loans = new HashSet<>();

    public Client(String firstName, String lastName, String patronymic, String iin, String phoneNumber, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.iin = iin;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }
}
