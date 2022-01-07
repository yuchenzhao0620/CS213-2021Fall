import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalTest {

    @Test
    void tuitionDue() {
        Profile profile1 = new Profile();
        profile1.setName("Yuchen Zhao");
        profile1.setMajor(Major.CS);
        Profile profile2 = new Profile();
        profile2.setName("Yuchen Zhao");
        profile2.setMajor(Major.CS);
        International is1 = new International(profile1);
        International is2 = new International(profile2);
        is1.setStudyAboard(true);
        is2.setStudyAboard(false);
        is1.setCredit(15);//international student must be equal or greater 12 credits.
        is2.setCredit(15);//international student must be equal or greater 12 credits.
        is1.tuitionDue();
        System.out.println(is1.getTuition());
        is2.tuitionDue();
        System.out.println(is2.getTuition());
    }
}