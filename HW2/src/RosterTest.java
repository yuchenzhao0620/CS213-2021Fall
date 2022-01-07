import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RosterTest {

    @Test
    void add() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        Profile profile3 = new Profile();
        Profile profile4 = new Profile();
        Profile profile5 = new Profile();
        profile1.setName("Yuchen Zhao");
        profile1.setMajor(Major.CS);
        profile2.setName("Yuchen Zhao");
        profile2.setMajor(Major.EE);
        profile3.setName("Yuchen Zhao");
        profile3.setMajor(Major.IT);
        profile4.setName("Yuchen Zhao");
        profile4.setMajor(Major.BA);
        profile5.setName("Yuchen Zhao");
        profile5.setMajor(Major.ME);
        Resident rs = new Resident(profile1);
        NonResident ns = new NonResident(profile2);
        TriState ts = new TriState(profile3);
        International is1 = new International(profile4);
        International is2 = new International(profile5);
        Roster roster = new Roster();
        System.out.println(roster.add(rs));
        System.out.println(roster.add(rs));
        System.out.println(roster.add(ns));
        System.out.println(roster.add(ns));
        System.out.println(roster.add(ts));
        System.out.println(roster.add(ts));
        System.out.println(roster.add(is1));
        System.out.println(roster.add(is1));
        System.out.println(roster.add(is2));
        System.out.println(roster.add(is2));
    }

    @Test
    void remove() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        Profile profile3 = new Profile();
        Profile profile4 = new Profile();
        Profile profile5 = new Profile();
        profile1.setName("Yuchen Zhao");
        profile1.setMajor(Major.CS);
        profile2.setName("Yuchen Zhao");
        profile2.setMajor(Major.EE);
        profile3.setName("Yuchen Zhao");
        profile3.setMajor(Major.IT);
        profile4.setName("Yuchen Zhao");
        profile4.setMajor(Major.BA);
        profile5.setName("Yuchen Zhao");
        profile5.setMajor(Major.ME);
        Resident rs = new Resident(profile1);
        NonResident ns = new NonResident(profile2);
        TriState ts = new TriState(profile3);
        International is1 = new International(profile4);
        International is2 = new International(profile5);
        Roster roster = new Roster();
        roster.add(rs);// add first
        roster.add(ns);// add first
        roster.add(ts);// add first
        roster.add(is1);// add first
        roster.add(is2);// add first
        System.out.println(roster.remove(rs));
        System.out.println(roster.remove(rs));
        System.out.println(roster.remove(ns));
        System.out.println(roster.remove(ns));
        System.out.println(roster.remove(ts));
        System.out.println(roster.remove(ts));
        System.out.println(roster.remove(is1));
        System.out.println(roster.remove(is2));
    }
}