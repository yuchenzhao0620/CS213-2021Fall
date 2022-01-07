import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date date1 = new Date("11/1/1979");
        System.out.println(date1.isValid());
        Date date2 = new Date("2/29/2021");
        System.out.println(date2.isValid());
        Date date3 = new Date("13/1/2021");
        System.out.println(date3.isValid());
        Date date4 = new Date("4/31/2021");
        System.out.println(date4.isValid());
        Date date5 = new Date("5/32/2021");
        System.out.println(date5.isValid());
        Date date6 = new Date("2/28/2021");
        System.out.println(date6.isValid());
        Date date7 = new Date("11/1/2021");
        System.out.println(date7.isValid());
        Date date8 = new Date("2/29/2021");
        System.out.println(date8.isValid());
        Date date9 = new Date("5/20/2021");
        System.out.println(date9.isValid());
        Date date10 = new Date("2/29/2020");
        System.out.println(date10.isValid());
        Date date11 = new Date("10/10/2021");
        System.out.println(date11.isValid());
        Date date12 = new Date("10/1/2021");
        System.out.println(date12.isValid());
    }
}