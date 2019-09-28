import junit.framework.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Sprint1Test extends Sprint1{
	
	public void testUS01() {
		Sprint1 sprint1 = new Sprint1();
		Person p1 = new Person();
		p1.setBirthDate("1", "JAN", "2021");
		Person p2 = new Person();
		p2.setDeathDate("1", "JAN", "2021");
		Family f1 = new Family();
		f1.setMarriageDate("1", "JAN", "2021");
		Family f2 = new Family();
		f2.setDivorceDate("1", "JAN", "2021");
		
		ArrayList<Person> person = new ArrayList<Person>();
		ArrayList<Family> fam = new ArrayList<Family>();
		assertEquals(0, sprint1.US01(person, fam));
		person.add(p1);
		assertEquals(1, sprint1.US01(person, fam));
		person.set(0, p2);
		assertEquals(2, sprint1.US01(person, fam));
		person.remove(0);
		fam.add(f1);
		assertEquals(3, sprint1.US01(person, fam));
		fam.set(0, f2);
		assertEquals(4, sprint1.US01(person, fam));
		person.add(p1);
		assertEquals(5, sprint1.US01(person, fam));
		
	}
	
	public void testUS02() {
		Sprint1 sprint1 = new Sprint1();
		Person p1 = new Person();
		p1.setId("p1");
		p1.setBirthDate("1", "JAN", "2018");
		Person p2 = new Person();
		p2.setId("p2");
		Family f1 = new Family();
		f1.setDad(p1);
		f1.setMom(p2);
		f1.setMarriageDate("1", "JAN", "2017");
		ArrayList<Person> person = new ArrayList<Person>();
		ArrayList<Family> fam = new ArrayList<Family>();
		assertEquals(0, sprint1.US02(person, fam));
		person.add(p1);
		person.add(p2);
		fam.add(f1);
		assertEquals(1, sprint1.US02(person, fam));
		p2.setBirthDate("1", "JAN", "2018");
		assertEquals(3, sprint1.US02(person, fam));
		p1.setBirthDate("1", "JAN", "2016");
		assertEquals(2, sprint1.US02(person, fam));
	}

	public void testUS04(){
	    Sprint1 sprint1 = new Sprint1();
	    Family f1 = new Family();
	    Person p1 = new Person();
	    Person p2 = new Person();
	    p1.setId("P01");
	    p2.setId("P02");
	    f1.setId("F01");
	    f1.setDad(p1);
	    f1.setMom(p2);
	    f1.setMarriageDate("1", "JAN", "2000");
	    f1.setDivorced(true);
	    f1.setDivorceDate("1", "JAN", "1995");
	    ArrayList<Family> fam = new ArrayList<>();
	    fam.add(f1);
	    assertEquals(false, sprint1.US04(fam));
	    f1.setDivorceDate("1", "JAN", "2001");
	    assertEquals(true, sprint1.US04(fam));

    }

    public void testUS05(){
        Sprint1 sprint1 = new Sprint1();
        Family f1 = new Family();
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setId("P01");
        p1.setDead(true);
        p1.setBirthDate("1", "JAN", "1975");
        p1.setDeathDate("1", "JAN", "2000");

        p2.setId("P02");
        p2.setBirthDate("1", "FEB", "1975");
        p2.setDead(false);
        ArrayList<Person> ppl = new ArrayList<>();
        ppl.add(p1);
        ppl.add(p2);

        f1.setDad(p1);
        f1.setMom(p2);
        f1.setId("F1");
        f1.setMarriageDate("1", "JAN", "2001");
        ArrayList<Family> fam = new ArrayList<>();
        fam.add(f1);

        assertEquals(false, sprint1.US05(ppl, fam));
        p1.setDeathDate("1", "JAN", "2002");
        assertEquals(true, sprint1.US05(ppl, fam));
    }

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