package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.util.Helper;

public class ResidenceFactory {

    public static Residence createResidence(String residenceName, String roomNumber, int floorNumber, String buildingName, Address address) {
        if
        (Helper.isNullOrEmpty(residenceName) || Helper.isNullOrEmpty(roomNumber) || Helper.isNullOrEmpty(buildingName)) {
            return null;
        }

        return new Residence.Builder()
                .setResidenceName(residenceName)
                .setRoomNumber(roomNumber)
                .setFloorNumber(floorNumber)
                .setBuildingName(buildingName)
                .setAddress(address)
                .build();
    }
}

