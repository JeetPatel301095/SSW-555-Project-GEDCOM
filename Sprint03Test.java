import junit.framework.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Sprint03Test extends Sprint3{
	@Test
	public void testUS21() {
		Sprint3 sprint3 = new Sprint3();
		Person hus = new Person();
		Person wife = new Person();
		Family fam = new Family();
		hus.setId("p1");
		hus.setSex("F");
		wife.setId("p2");
		wife.setSex("M");
		fam.setId("f1");
		fam.setDad(hus);
		ArrayList<Person> p = new ArrayList<>();
		ArrayList<Family> f = new ArrayList<>();
		p.add(hus);
		p.add(wife);
		f.add(fam);
		assertEquals(1, sprint3.US21(p, f));
		fam.setMom(wife);
		assertEquals(3, sprint3.US21(p, f));
		p.remove(hus);
		assertEquals(2, sprint3.US21(p, f));
	}
	
	@Test
	public void testUS22() {
		Sprint3 sprint3 = new Sprint3();
		Person p1 = new Person();
		Person p2 = new Person();
		Family f1 = new Family();
		Family f2 = new Family();
		p1.setId("p1");
		p2.setId("p1");
		f1.setId("f1");
		f2.setId("f1");
		ArrayList<Person> p = new ArrayList<>();
		ArrayList<Family> f = new ArrayList<>();
		p.add(p1);
		p.add(p2);
		assertEquals(1, sprint3.US22(p, f));
		f.add(f1);
		f.add(f2);
		assertEquals(3, sprint3.US22(p, f));
		p.remove(p2);
		assertEquals(2, sprint3.US22(p, f));
	}
	
    public void testUS23(){
        Sprint3 sprint3 = new Sprint3();
        Person p01 = new Person();
        Person p02 = new Person();
        Person p03 = new Person();
        Person p04 = new Person();
        Person p01duplicate = new Person();
        ArrayList<Person> people = new ArrayList<>();
        p01.setId("p01");
        p01.setFirstName("John");
        p01.setLastName("Smith");
        p01.setBirthDate("1", "JAN", "2000");
        p02.setId("p02");
        p02.setFirstName("Mary");
        p02.setLastName("Smith");
        p02.setBirthDate("1", "FEB", "2000");
        p03.setId("po3");
        p03.setFirstName("Will");
        p03.setLastName("Smith");
        p03.setBirthDate("1", "MAR", "2000");
        p04.setId("p04");
        p04.setFirstName("Nancy");
        p04.setLastName("Smith");
        p04.setBirthDate("1", "MAY", "2000");
        p01duplicate.setId("p05");
        p01duplicate.setFirstName("John");
        p01duplicate.setLastName("Smith");
        p01duplicate.setBirthDate("1", "JAN", "2000");
        people.add(p01);
        people.add(p02);
        people.add(p03);
        people.add(p04);
        people.add(p01duplicate);

        assertEquals(false, sprint3.US23(people));

        p01duplicate.setFirstName("Chris");
        p01duplicate.setBirthDate("2", "JAN", "2000");

        assertEquals(true, sprint3.US23(people));
    }

    public void testUS24(){
        Sprint3 sprint3 = new Sprint3();
        Person husband1 = new Person();
        Person husband2 = new Person();
        Person husband3 = new Person();
        Person wife1 = new Person();
        Person wife2 = new Person();
        Person wife3 = new Person();
        Family fam1 = new Family();
        Family fam2 = new Family();
        Family fam3 = new Family();
        husband1.setId("p01");
        husband2.setId("p02");
        husband3.setId("p03");
        wife1.setId("p04");
        wife2.setId("p05");
        wife3.setId("p06");
        fam1.setId("f01");
        fam2.setId("f02");
        fam3.setId("f03");

        husband1.setFirstName("John");
        husband1.setLastName("Smith");
        wife1.setFirstName("Isabelle");
        wife1.setLastName("Smith");
        fam1.setDad(husband1);
        fam1.setMom(wife1);
        fam1.setMarriageDate("1", "JUL", "1990");

        husband2.setFirstName("John");
        husband2.setLastName("Smith");
        wife2.setFirstName("Isabelle");
        wife2.setLastName("Smith");
        fam2.setDad(husband2);
        fam2.setMom(wife2);
        fam2.setMarriageDate("1", "JUL", "1990");

        husband3.setFirstName("John");
        husband3.setLastName("Deer");
        wife3.setFirstName("Isabelle");
        wife3.setLastName("Deer");
        fam3.setDad(husband3);
        fam3.setMom(wife3);
        fam3.setMarriageDate("1", "JUL", "1990");

        ArrayList<Family> fams = new ArrayList<>();
        fams.add(fam1);
        fams.add(fam2);
        fams.add(fam3);
        assertEquals(false, sprint3.US24(fams));

        fam1.setMarriageDate("20", "MAY", "1995");
        assertEquals(true, sprint3.US24(fams));
    }
}