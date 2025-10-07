package za.ac.student_trade.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Helper {

    public static String generateId(){return UUID.randomUUID().toString();}

    //Todo: Validate Email

    public static boolean isValidEmail(String email){
        return  EmailValidator.getInstance().isValid(email);
    }

    //Todo: Validate Price > 0
    //Todo: firstName, lastName, email, and password is not Null

    public static boolean isNullOrEmpty(String s){
        if(s.isEmpty() || s == null){
            return true;
        }
        return false;
    }
    //Todo: validate postal code > 999 && postal code < 10000
    public static boolean postalCodeValid(int postalCode){
        if(postalCode > 999 || postalCode < 1000){
            return true;
        }
        return false;
    }

    //for images
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }



    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

}
