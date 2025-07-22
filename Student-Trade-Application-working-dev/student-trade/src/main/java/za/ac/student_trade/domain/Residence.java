package za.ac.student_trade.domain;

import jakarta.persistence.*;

@Entity
@Table(name ="residence")
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long residenceId;

    @Column(name = "residence_name")
    protected String residenceName;
    @Column(name = "room_number")
    protected String roomNumber;
    @Column(name = "floor_number")
    protected int floorNumber;
    @Column(name = "building")
    protected String buildingName;

    protected Residence() {}

    protected Residence(Builder builder) {
        this.residenceId = builder.residenceId;
        this.residenceName = builder.residenceName;
        this.roomNumber = builder.roomNumber;
        this.floorNumber = builder.floorNumber;
        this.buildingName = builder.buildingName;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    @Override
    public String toString() {
        return "Residence{" +
                "residenceId=" + residenceId +
                ", residenceName='" + residenceName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", floorNumber=" + floorNumber +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }

    public static class Builder {
        private Long residenceId;
        private String residenceName;
        private String roomNumber;
        private int floorNumber;
        private String buildingName;

        public Builder setResidenceId(Long residenceId) {
            this.residenceId = residenceId;
            return this;
        }

        public Builder setResidenceName(String residenceName) {
            this.residenceName = residenceName;
            return this;
        }

        public Builder setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setFloorNumber(int floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public Builder setBuildingName(String buildingName) {
            this.buildingName = buildingName;
            return this;
        }

        public Builder builder(Residence residence) {
            this.residenceId = residence.getResidenceId();
            this.residenceName = residence.getResidenceName();
            this.roomNumber = residence.getRoomNumber();
            this.floorNumber = residence.getFloorNumber();
            this.buildingName = residence.getBuildingName();
            return this;
        }

        public Builder copy(Residence residence) {
            this.residenceId = residence.getResidenceId();
            this.residenceName = residence.getResidenceName();
            this.roomNumber = residence.getRoomNumber();
            this.floorNumber = residence.getFloorNumber();
            this.buildingName = residence.getBuildingName();
            return this;
        }

        public Residence build() {
            return new Residence(this);
        }
    }
}