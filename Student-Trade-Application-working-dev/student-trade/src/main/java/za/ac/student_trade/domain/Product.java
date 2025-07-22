package za.ac.student_trade.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_condition")
    private String condition;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private int productCategory;

    @Column(name = "availability")
    private boolean availabilityStatus;

    @Column(name = "product_image")
    private String productImageUrl;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Student seller;

    @OneToOne(mappedBy = "product")
    private Transaction transaction;
}