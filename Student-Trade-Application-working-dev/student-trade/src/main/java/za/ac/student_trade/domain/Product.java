package za.ac.student_trade.domain;

import jakarta.persistence.*;
import lombok.Builder;

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
    private String productCategory;

    @Column(name = "availability")
    private boolean availabilityStatus;

    @Column(name = "product_image")
    private String productImageUrl;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Student seller;

    @OneToOne(mappedBy = "product")
    private Transaction transaction;

    public Product() {
    }

    protected Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.productDescription = builder.productDescription;
        this.condition = builder.condition;
        this.price = builder.price;
        this.productCategory = builder.productCategory;
        this.availabilityStatus = builder.availabilityStatus;
        this.productImageUrl = builder.productImageUrl;
        this.seller = builder.seller;
        this.transaction = builder.transaction;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getCondition() {
        return condition;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public Student getSeller() {
        return seller;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", productCategory=" + productCategory +
                ", availabilityStatus=" + availabilityStatus +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", seller=" + seller +
                ", transaction=" + transaction +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String productName;
        private String productDescription;
        private String condition;
        private Double price;
        private String productCategory;
        private boolean availabilityStatus;
        private String productImageUrl;
        private Student seller;
        private Transaction transaction;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setProductCategory(String productCategory) {
            this.productCategory = productCategory;
            return this;
        }

        public Builder setAvailabilityStatus(boolean availabilityStatus) {
            this.availabilityStatus = availabilityStatus;
            return this;
        }

        public Builder setProductImageUrl(String productImageUrl) {
            this.productImageUrl = productImageUrl;
            return this;
        }

        public Builder setSeller(Student seller) {
            this.seller = seller;
            return this;
        }

        public Builder setTransaction(Transaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.getProductId();
            this.productName = product.getProductName();
            this.productDescription = product.getProductDescription();
            this.condition = product.getCondition();
            this.price = product.getPrice();
            this.productCategory = product.getProductCategory();
            this.availabilityStatus = product.isAvailabilityStatus();
            this.productImageUrl = product.getProductImageUrl();
            this.seller = product.getSeller();
            this.transaction = product.getTransaction();
            return this;
        }

        public Builder builder(Product product) {
            this.productId = product.getProductId();
            this.productName = product.getProductName();
            this.productDescription = product.getProductDescription();
            this.condition = product.getCondition();
            this.price = product.getPrice();
            this.productCategory = product.getProductCategory();
            this.availabilityStatus = product.isAvailabilityStatus();
            this.productImageUrl = product.getProductImageUrl();
            this.seller = product.getSeller();
            this.transaction = product.getTransaction();
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}