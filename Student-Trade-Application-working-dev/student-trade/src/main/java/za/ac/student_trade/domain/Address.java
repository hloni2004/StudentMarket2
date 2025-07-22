package za.ac.student_trade.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long addressId;

    @Column(name = "street_number")
    protected String streetNumber;
    @Column(name = "street_name")
    protected String streetName;
    @Column(name = "suburb")
    protected String suburb;
    @Column(name = "city")
    protected String city;
    @Column(name = "province")
    protected String province;
    @Column(name = "postal_code")
    protected int postalCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residence_id")
    protected Residence residence;

    protected Address() {}

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetNumber = builder.streetNumber;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.residence = builder.residence;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public Residence getAddress() {
        return residence;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                ", address=" + residence +
                '}';
    }

    public static class Builder {
        private Long addressId;
        private String streetNumber;
        private String streetName;
        private String suburb;
        private String city;
        private String province;
        private int postalCode;
        private Residence residence;

        public Builder setAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setResidence(Residence residence) {
            this.residence = residence;
            return this;
        }

        public Builder builder(Address address) {
            this.addressId = address.getAddressId();
            this.streetNumber = address.getStreetNumber();
            this.streetName = address.getStreetName();
            this.suburb = address.getSuburb();
            this.city = address.getCity();
            this.province = address.getProvince();
            this.postalCode = address.getPostalCode();
            this.residence = residence;
            return this;
        }

        public Builder copy(Address address) {
            this.addressId = address.getAddressId();
            this.streetNumber = address.getStreetNumber();
            this.streetName = address.getStreetName();
            this.suburb = address.getSuburb();
            this.city = address.getCity();
            this.province = address.getProvince();
            this.postalCode = address.getPostalCode();
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}