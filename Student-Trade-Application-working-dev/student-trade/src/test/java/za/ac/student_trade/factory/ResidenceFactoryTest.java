package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Residence;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ResidenceFactoryTest {


        private static Residence residence = ResidenceFactory.createResidence(null, "New Market Junction", "367", 3, "C Block");
        private static Residence res = ResidenceFactory.createResidence(null, "New Market Junction", "", 3, "C Block");

        @Test
        void testCreateResidence() {
            assertNotNull(residence);
            System.out.println(residence.toString());
        }
        @Test

        void testThatFails(){
            assertNull(res);
            System.out.println("Res is null");


        }
}