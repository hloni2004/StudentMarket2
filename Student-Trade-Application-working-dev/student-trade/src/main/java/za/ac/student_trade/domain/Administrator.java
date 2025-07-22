package za.ac.student_trade.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator")
public class Administrator {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
