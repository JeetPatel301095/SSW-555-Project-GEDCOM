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

    @Test
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

    @Test
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

    @Test
    public void testUS25()
    {
        Sprint3 sprint3 = new Sprint3();
        Person husb = new Person();
        Person wife = new Person();
        Person child = new Person();
        ArrayList<Person> a = new ArrayList<Person>();
        ArrayList<Family> b = new ArrayList<Family>();

        husb.setId("Sp3I1");
        wife.setId("Sp3I2");
        child.setId("Sp3I3");

        husb.setFirstName("ABC");
        wife.setFirstName("DEF");
        child.setFirstName("ABC");

        husb.setLastName("XYZ");
        wife.setLastName("XYZ");
        child.setLastName("XYZ");

        Family re = new Family();
        re.setId("Sp3F1");
        re.setDad(husb);
        re.setMom(wife);
        re.addChild(child.getId());

        a.add(husb);
        a.add(wife);
        a.add(child);

        b.add(re);

        assertEquals(false, sprint3.US25(b,a));
    }

    @Test
    public void testUS29()
    {
        Sprint3 sprint3 = new Sprint3();
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        ArrayList<Person> a = new ArrayList<Person>();

        p1.setId("Sp3I1");
        p2.setId("Sp3I2");
        p3.setId("Sp3I3");
        p1.setFirstName("ABC");
        p2.setFirstName("DEF");
        p3.setFirstName("GHI");
        p1.setLastName("XYZ");
        p2.setLastName("XYZ");
        p3.setLastName("XYZ");

        p1.setDead(true);
        p2.setDead(true);
        p3.setDead(true);

        p1.setDeathDate("1","AUG","2000");
        p2.setDeathDate("1","OCT","2000");
        p3.setDeathDate("1","SEP","2000");

        a.add(p1);
        a.add(p2);
        a.add(p3);

        assertEquals(3, sprint3.US29(a).size());
    }

    @Test
    public void testUS30(){
        Sprint3 sprint3 = new Sprint3();
        Person husband1 = new Person();
        Person husband2 = new Person();
        Person wife1 = new Person();
        Person wife2 = new Person();
        Person single = new Person();

        single.setId("p00");
        single.setLastName("Zhang");
        single.setFirstName("Zh");
        husband1.setId("p01");
        husband2.setId("p02");
        wife1.setId("p04");
        wife2.setId("p05");

        husband1.setFirstName("John");
        husband1.setLastName("Smith");
        wife1.setFirstName("Isabelle");
        wife1.setLastName("Smith");
        husband1.setFams("p04");
        wife1.setFams("p01");

        husband2.setFirstName("John");
        husband2.setLastName("Smith");
        husband2.setFams("p05");
        wife2.setFirstName("Isabelle");
        wife2.setLastName("Smith");
        wife2.setFams("p02");

        ArrayList<Person> p = new ArrayList<>();
        p.add(husband1);
        p.add(husband2);
        p.add(wife1);
        p.add(wife2);
        p.add(single);
        assertEquals(4, sprint3.US30(p).size());

    }

    @Test
    public void testUS31(){
        Sprint3 sprint3 = new Sprint3();
        Person husband1 = new Person();
        Person husband2 = new Person();
        Person wife1 = new Person();
        Person wife2 = new Person();
        Person single = new Person();
        Person single2 = new Person();
        Person displayed = new Person();

        single.setId("p00");
        single.setLastName("Zhang");
        single.setFirstName("Zh");
        single.setBirthDate("30","01","2000");
        single.setAge();
        single2.setId("0001");
        single2.setDead(true);
        displayed.setId("p007");
        displayed.setBirthDate("01","01","1950");
        displayed.setAge();

        husband1.setId("p01");
        husband2.setId("p02");
        wife1.setId("p04");
        wife2.setId("p05");

        husband1.setFirstName("John");
        husband1.setLastName("Smith");
        wife1.setFirstName("Isabelle");
        wife1.setLastName("Smith");
        husband1.setFams("p04");
        wife1.setFams("p01");

        ArrayList<Person> p = new ArrayList<>();
        p.add(husband1);
        p.add(husband2);
        p.add(wife1);
        p.add(wife2);
        p.add(single);
        p.add(single2);
        p.add(displayed);
        assertEquals(1, sprint3.US31(p).size());
    }
}