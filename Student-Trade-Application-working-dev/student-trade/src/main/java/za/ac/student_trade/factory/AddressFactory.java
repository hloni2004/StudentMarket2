package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Address;
import za.ac.student_trade.util.Helper;

import java.util.Random;

public class AddressFactory {
    public static Address createAddress(String streetNumber, String streetName, String suburb, String city, String province,
                                        int postalCode){

        if(Helper.isNullOrEmpty(streetNumber) || Helper.isNullOrEmpty(streetName) || Helper.isNullOrEmpty(suburb)
                || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(province) || Helper.postalCodeValid(postalCode)){
            return new Address.Builder()
                    .setStreetNumber(streetNumber)
                    .setStreetName(streetName)
                    .setSuburb(suburb)
                    .setCity(city)
                    .setProvince(province)
                    .setPostalCode(postalCode)
                    .build();
        }
        return null;
    }
}
