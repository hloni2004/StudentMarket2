package za.ac.student_trade.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long studentId;

    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @Column(name = "email",nullable = false)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    protected Address address;

    @OneToMany(mappedBy = "seller")
    private List<Product> productForSale;

    @OneToMany(mappedBy = "buyer")
    private List<Transaction> purchases;


    protected Student() {}

    private Student(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.address = builder.address;
        this.productForSale = builder.productForSale;
        this.purchases = builder.purchases;

    }

    public Long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public List<Product> getProductForSale() {
        return productForSale;
    }

    public List<Transaction> getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", productForSale=" + productForSale +
                ", purchases=" + purchases +
                '}';
    }

    public static class Builder {
        private Long studentId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Address address;
        private List<Product> productForSale;
        private List<Transaction> purchases;

        public Builder setStudentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
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

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setProductForSale(List<Product> productForSale) {
            this.productForSale = productForSale;
            return this;
        }

        public Builder setPurchases(List<Transaction> purchases) {
            this.purchases = purchases;
            return this;
        }

        public Builder builder(Student student) {
            this.studentId = student.getStudentId();
            this.firstName = student.getFirstName();
            this.lastName = student.getLastName();
            this.email = student.getEmail();
            this.password = student.getPassword();
            this.address = student.getAddress();
            this.productForSale = student.getProductForSale();
            this.purchases = student.getPurchases();
            return this;
        }

        public Builder copy(Student student) {
            this.studentId = student.studentId;
            this.firstName = student.firstName;
            this.lastName = student.lastName;
            this.email = student.email;
            this.password = student.password;
            this.address = student.address;
            this.productForSale = student.productForSale;
            this.purchases = student.purchases;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
