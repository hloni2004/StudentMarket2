package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.util.Helper;

import java.util.Random;

public class ResidenceFactory {

    public static Residence createResidence(Long residenceId,String residenceName, String roomNumber, int floorNumber, String buildingName) {
        if
        (!Helper.ValidString(residenceName) || !Helper.ValidString(roomNumber) || !Helper.ValidString(buildingName)) {
            return null;
        }
        if
        (!Helper.ValidInteger(floorNumber)) {
            return null;
        }
        if
        (!Helper.validLong(residenceId)) {
            return null;
        }


        residenceId = new Random().nextLong();
        return new Residence.Builder()
                .setResidenceId(residenceId)
                .setResidenceName(residenceName)
                .setRoomNumber(roomNumber)
                .setFloorNumber(floorNumber)
                .setBuildingName(buildingName)
                .build();
    }
}

