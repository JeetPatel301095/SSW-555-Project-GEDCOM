import junit.framework.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Sprint04Test extends Person {
	
	@Test
	public void testUS32() {
		Sprint4 s4 = new Sprint4();
		ArrayList<Person> indi = new ArrayList<>();
        ArrayList<Family> fams = new ArrayList<>();
        Family f = new Family();
        Person c1 = new Person();
        c1.setId("c1");
        Person c2 = new Person();
        c2.setId("c2");
        int d = Integer.parseInt("1");
        int m = changeMonthFormatToInt("JAN");
        int y = Integer.parseInt("2010");
        Calendar c = Calendar.getInstance();
        c.set(y,m,d); 
        c1.BirthDate=c.getTime();
        c2.BirthDate=c.getTime();
        c1.setAge();
        c2.setAge();
        f.addChild("c1");
        f.addChild("c2");
        indi.add(c1);
        indi.add(c2);
        fams.add(f);
        assertEquals(1, s4.US32(indi, fams));
	}
	
	@Test
	public void testUS33() {
		Sprint4 s4 = new Sprint4();
		ArrayList<Person> indi = new ArrayList<>();
        ArrayList<Family> fams = new ArrayList<>();
        Person h = new Person();
        h.setDead(true);
        Person w = new Person();
        w.setDead(true);
        Person c = new Person();
        c.setId("c1");
        c.setBirthDate("1", "1", "2010");
        Family f = new Family();
        f.setDad(h);
        f.setMom(w);
        f.addChild("c1");
        
        indi.add(h);
        indi.add(w);
        indi.add(c);
        fams.add(f);
        
        assertEquals(1, s4.US33(indi, fams));
        
        Person c2 = new Person();
        c2.setId("c2");
        c2.setBirthDate("1", "1", "2010");
        f.addChild("c2");
        indi.add(c2);
        
        assertEquals(2, s4.US33(indi, fams));
	}


    @Test
    public void testUS34() {
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Person> individs = new ArrayList<>();
        ArrayList<Family> fams = new ArrayList<>();
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setId("p01");
        p2.setId("p02");
        p1.setBirthDate("1", "MAR", "1920");
        p2.setBirthDate("1", "JAN", "1950");
        Family fam1 = new Family();
        fam1.setId("f01");
        fam1.setDad(p1);
        fam1.setMom(p2);
        fam1.setMarriageDate("1", "SEP", "1980");
        individs.add(p1);
        individs.add(p2);
        fams.add(fam1);
        sprint4.US34(individs, fams);

    }

    @Test
    public void testUS35() {
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Person> individs = new ArrayList<>();
        Person p1 = new Person();
        p1.setId("p01");
        p1.setFirstName("chris");
        p1.setLastName("rudel");
        p1.setBirthDate("6", "OCT", "2019");
        individs.add(p1);
        sprint4.US35(individs);
    }

    @Test
    public void testUS36() {
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Person> indi = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setId("Sp4TI1");
        p1.setFirstName("ABC");
        p1.setLastName("DEF");
        p1.setDead(true);
        p1.setDeathDate("25", "OCT", "2019");
        indi.add(p1);
        assertEquals(1, sprint4.US36(indi).size());
    }

    @Test
    public void testUS37() {
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Person> indi = new ArrayList<Person>();
        ArrayList<Family> fam = new ArrayList<Family>();
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();
        p1.setId("SP4TI1");
        p2.setId("SP4TI2");
        p3.setId("SP4TI3");
        p4.setId("SP4TI4");

        p1.setFams("SP4TF1");
        p1.setFirstName("ABC");
        p2.setFirstName("DEF");
        p3.setFirstName("GHI");
        p4.setFirstName("JFK");

        p1.setLastName("XYZ");
        p2.setLastName("XYZ");
        p3.setLastName("XYZ");
        p4.setLastName("XYZ");

        p1.setDead(true);
        p3.setDead(true);

        p1.setDeathDate("20", "OCT", "2019");
        p3.setDeathDate("20", "JUN", "2019");

        indi.add(p1);
        indi.add(p2);
        indi.add(p3);
        indi.add(p4);

        Family f1 = new Family();
        f1.setId("SP4TF1");
        f1.setDad(p1);
        f1.setMom(p2);
        f1.addChild(p3.getId());
        f1.addChild(p4.getId());

        fam.add(f1);

        assertEquals(2, sprint4.US37(indi, fam).size());
    }

    @Test
    public void testUS38(){
	    //Warning: this test will fail after 11.25.2019!!!!!!!!!!!!
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Person> individs = new ArrayList<>();
        Person p1 = new Person();
        p1.setId("p01");
        p1.setFirstName("wxs");
        p1.setLastName("Qwd");
        p1.setBirthDate("25", "NOV", "1999");
        individs.add(p1);
        assertEquals(1,sprint4.US38(individs).size());
    }

    @Test
    public void testUS39(){
        //Warning: this test will fail after 11.25.2019!!!!!!!!!!!!
        Sprint4 sprint4 = new Sprint4();
        ArrayList<Family> fam = new ArrayList<>();
        Family family = new Family();
        family.setMarriageDate("25", "NOV", "2019");
        fam.add(family);
        ArrayList<Person> individs = new ArrayList<>();
        Person p1 = new Person();
        p1.setId("p01");
        p1.setFirstName("wxs");
        p1.setLastName("Qwd");
        p1.setBirthDate("25", "NOV", "2019");
        assertEquals(1,sprint4.US39(fam,individs).size());

    }
}
