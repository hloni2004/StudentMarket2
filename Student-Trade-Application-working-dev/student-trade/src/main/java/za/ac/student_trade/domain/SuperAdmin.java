package za.ac.student_trade.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "super_administrator")
public class SuperAdmin {

    @Id
    @Column(name = "super_admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long superAdminId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    protected SuperAdmin() {
    }

    private SuperAdmin(Builder builder) {
        this.superAdminId = builder.superAdminId;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
    }

    public Long getSuperAdminId() {
        return superAdminId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SuperAdmin{" +
                "superAdminId=" + superAdminId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        private Long superAdminId;
        private String username;
        private String email;
        private String password;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder copy(SuperAdmin superAdmin) {
            this.superAdminId = superAdmin.superAdminId;
            this.username = superAdmin.username;
            this.email = superAdmin.email;
            this.password = superAdmin.password;
            return this;
        }

        public SuperAdmin build() {
            return new SuperAdmin(this);
        }
    }
}