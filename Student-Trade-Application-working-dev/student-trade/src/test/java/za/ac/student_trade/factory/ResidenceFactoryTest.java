package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Residence;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ResidenceFactoryTest {



        private static Residence residence = ResidenceFactory.createResidence("PRESIDENT HOUSE", "22", 367, "block c", null);
        private static Residence res = ResidenceFactory.createResidence("NMJ", "420", 2, "block b", null);

        @Test
        void testCreateResidence() {
            assertNotNull(residence);
            System.out.println(residence.toString());
        }

//        @Test
//        void testThatFails(){
//            assertNull(res);
//            System.out.println("Res is null");
//        }
}