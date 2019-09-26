import junit.framework.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Sprint1Test extends Sprint1{

    public void testUS07(){
        Sprint1 sprint1 = new Sprint1();
        Person p = new Person();
        p.setDead(true);
        p.setBirthDate("1", "JAN","1950");
        p.setDeathDate("1","JAN","2200");
        p.setId("I01");
        ArrayList<Person> list = new ArrayList<>();
        list.add(p);
        ArrayList<Person> list2 = new ArrayList<>();
        Person p2 = new Person();
        p2.setBirthDate("1","JAN","1800");
        p2.setId("I02");
        p2.setAge();
        list2.add(p2);
        assertEquals(false, sprint1.US07(list));
        assertEquals(false, sprint1.US07(list2));
    }

    public void testUS08(){
        Sprint1 sprint1 = new Sprint1();
        Person child = new Person();
        child.setId("N01");
        child.setBirthDate("1", "FEB","1999");
        ArrayList<Person> list = new ArrayList<>();
        list.add(child);
        Family fam = new Family();
        fam.setId("F01");
        fam.setMarriageDate("1","JAN","2000");
        fam.addChild("N01");
        ArrayList<Family> families = new ArrayList<>();
        families.add(fam);
        assertEquals(false, sprint1.US08(families, list));
        //test second function of US08
        Person child2 = new Person();
        child2.setId("N02");
        child2.setBirthDate("1", "FEB", "2007");
        ArrayList<Person> list2 = new ArrayList<>();
        list2.add(child2);
        Family fam2 = new Family();
        fam2.setId("F02");
        fam2.setDivorced(true);
        fam2.setMarriageDate("1", "FEB", "2000");
        fam2.setDivorceDate("1","FEB","2005");
        fam2.addChild("N02");
        ArrayList<Family> families2 = new ArrayList<>();
        families2.add(fam2);
        assertEquals(false, sprint1.US08(families2, list2));
    }
}