import junit.framework.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Sprint02Test extends Sprint2{
	
	@Test
	public void testUS09() {
		Sprint2 s2 = new Sprint2();
		Person husb = new Person();
		Person wife = new Person();
		Person child = new Person();
		Family fam = new Family();
		husb.setId("p1");
		wife.setId("p2");
		child.setId("p3");
		wife.setDead(true);
		wife.setDeathDate("1", "1", "2015");
		child.setBirthDate("2", "2", "2015");
		fam.setDad(husb);
		fam.setMom(wife);
		fam.addChild(child.getId());
		ArrayList<Person> person = new ArrayList<Person>();
		person.add(husb);
		person.add(wife);
		person.add(child);
		ArrayList<Family> family = new ArrayList<Family>();
		family.add(fam);
		assertEquals(1, s2.US09(person, family));
		husb.setDead(true);
		husb.setDeathDate("1", "1", "2014");
		assertEquals(3, s2.US09(person, family));
		wife.setDead(false);
		assertEquals(2, s2.US09(person, family));
	}
	
	@Test
	public void testUS10() {
		Sprint2 s2 = new Sprint2();
		Person husb = new Person();
		Person wife = new Person();
		Family fam = new Family();
		husb.setId("p1");
		wife.setId("p2");
		husb.setBirthDate("1", "1", "2000");
		fam.setDad(husb);
		fam.setMom(wife);
		fam.setMarriageDate("1", "1", "2010");
		fam.setId("f1");
		ArrayList<Person> person = new ArrayList<Person>();
		person.add(husb);
		person.add(wife);
		ArrayList<Family> family = new ArrayList<Family>();
		family.add(fam);
		assertEquals(1, s2.US10(person, family));
		wife.setBirthDate("1", "1", "2000");
		assertEquals(3, s2.US10(person, family));
		husb.setBirthDate("1", "1", "1990");
		assertEquals(2, s2.US10(person, family));
	}

    public void testUS11() {
        Sprint2 sprint2 = new Sprint2();
        Person husband1 = new Person();
        Person husband2 = new Person();
        Person wife1 = new Person();
        Person wife2 = new Person();
        Family fam1 = new Family();
        Family fam2 = new Family();
        husband1.setId("01");
        husband2.setId("02");
        wife1.setId("03");
        wife2.setId("04");
        fam1.setId("F01");
        fam2.setId("F02");
        fam1.setDad(husband1);
        fam1.setMom(wife1);
        fam2.setDad(husband2);
        fam2.setMom(wife2);
        ArrayList<Family> fams = new ArrayList<>();
        fams.add(fam1);
        fams.add(fam2);
        assertEquals(0, sprint2.US11(fams));

        fam2.setDad(husband1);
        assertEquals(1, sprint2.US11(fams));

        fam2.setDad(husband2);
        fam1.setMom(wife2);
        assertEquals(2, sprint2.US11(fams));

        fam1.setMom(wife1);

        Person wife3 = new Person();
        Family fam3 = new Family();
        wife3.setId("05");
        fam3.setId("F03");
        fam1.setDivorced(true);
        fam3.setMom(wife3);
        fam3.setDad(husband1);
        fams.add(fam3);
        assertEquals(0, sprint2.US11(fams)); //divorced, so husband has two wives
        fam1.setDivorced(false);
        assertEquals(1, sprint2.US11(fams));

    }

    public void testUS12(){
        Sprint2 sprint2 = new Sprint2();
        Person dad = new Person();
        Person mom = new Person();
        Person son = new Person();
        Person daughter = new Person();
        Family fam = new Family();
        dad.setId("01");
        dad.setBirthDate("1", "JAN", "1955");
        mom.setId("02");
        mom.setBirthDate("1", "JAN", "1960");
        son.setId("03");
        son.setBirthDate("1", "JAN", "2000");
        daughter.setId("04");
        daughter.setBirthDate("1", "JAN", "1970");
        fam.setDad(dad);
        fam.setMom(mom);
        fam.addChild(son.getId());
        fam.addChild((daughter.getId()));
        fam.setId("fam01");
        ArrayList<Person> ppl = new ArrayList<>();
        ArrayList<Family> fams = new ArrayList<>();
        ppl.add(dad);
        ppl.add(mom);
        ppl.add(son);
        ppl.add(daughter);
        fams.add(fam);

        assertEquals(0, sprint2.US12(ppl, fams)); //parents are young enough to have kids

        dad.setBirthDate("1", "JAN", "1915");
        assertEquals(1, sprint2.US12(ppl, fams)); // dad is 85 years older than son

        dad.setBirthDate("1", "JAN", "1955");
        mom.setBirthDate("1", "JAN", "1930");

        assertEquals(2, sprint2.US12(ppl, fams)); // mom is 70 years older than son
    }
    
    @Test
    public void testUS13(){
        Sprint2 sprint2 = new Sprint2();
        ArrayList<Person> a = new ArrayList<Person>();
        ArrayList<Family> b = new ArrayList<Family>();
        Person F = new Person();
        F.setId("PP001");
        F.setFirstName("Ned");
        F.setLastName("Stark");
        F.setBirthDate("1","JAN","1950");
        Person M = new Person();
        M.setId("PP006");
        M.setFirstName("Catelyn");
        M.setLastName("Stark");
        M.setBirthDate("1","JAN","1950");
        Person I2 = new Person();
        I2.setId("PP002");
        I2.setFirstName("Rob");
        I2.setLastName("Stark");
        I2.setBirthDate("3","OCT","2000");
        Person I3 = new Person();
        I3.setId("PP003");
        I3.setFirstName("Arya");
        I3.setLastName("Stark");
        I3.setBirthDate("3","APR","2000");
        Person I4 = new Person();
        I4.setId("PP004");
        I4.setFirstName("Sansa");
        I4.setLastName("Stark");
        I4.setBirthDate("2","JAN","2000");
        Person I1 = new Person();
        I1.setId("PP005");
        I1.setFirstName("Bran");
        I1.setLastName("Stark");
        I1.setBirthDate("1","JAN","2000");
        a.add(F);
        a.add(M);
        a.add(I1);
        a.add(I2);
        a.add(I3);
        a.add(I4);

        Family f1 = new Family();
        f1.setDad(F);
        f1.setMom(M);
        f1.addChild(I1.getId());
        f1.addChild(I2.getId());
        f1.addChild(I3.getId());
        f1.addChild(I4.getId());
        b.add(f1);
        assertEquals(false,sprint2.US13(b, a));
        a.remove(I3);
        Family f2 = new Family();
        f2.setDad(F);
        f2.setMom(M);
        f2.addChild(I1.getId());
        f2.addChild(I2.getId());
        f2.addChild(I4.getId());
        b.remove(f1);
        b.add(f2);
        assertEquals(true,sprint2.US13(b, a));
        
    }
    
    @Test
    public void testUS14(){
        Sprint2 sprint2 = new Sprint2();
        ArrayList<Person> a = new ArrayList<Person>();
        ArrayList<Family> b = new ArrayList<Family>();
        Person F = new Person();
        F.setId("PP001");
        F.setFirstName("Ned");
        F.setLastName("Stark");
        F.setBirthDate("1","JAN","1950");
        Person M = new Person();
        M.setId("PP006");
        M.setFirstName("Catelyn");
        M.setLastName("Stark");
        M.setBirthDate("1","JAN","1950");
        Person I2 = new Person();
        I2.setId("PP002");
        I2.setFirstName("Rob");
        I2.setLastName("Stark");
        I2.setBirthDate("1","JAN","2000");
        Person I3 = new Person();
        I3.setId("PP003");
        I3.setFirstName("Arya");
        I3.setLastName("Stark");
        I3.setBirthDate("1","JAN","2000");
        Person I4 = new Person();
        I4.setId("PP004");
        I4.setFirstName("Sansa");
        I4.setLastName("Stark");
        I4.setBirthDate("1","JAN","2000");
        Person I1 = new Person();
        I1.setId("PP005");
        I1.setFirstName("Bran");
        I1.setLastName("Stark");
        I1.setBirthDate("1","JAN","2000");
        Person I5 = new Person();
        I5.setId("PP007");
        I5.setFirstName("Jon");
        I5.setLastName("Snow");
        I5.setBirthDate("1","JAN","2000");
        Person I6 = new Person();
        I6.setId("PP008");
        I6.setFirstName("Jake");
        I6.setLastName("Stark");
        I6.setBirthDate("1","JAN","2000");
        
        a.add(F);
        a.add(M);
        a.add(I1);
        a.add(I2);
        a.add(I3);
        a.add(I4);
        a.add(I5);
        a.add(I6);
        Family f1 = new Family();
        f1.setDad(F);
        f1.setMom(M);
        f1.addChild(I1.getId());
        f1.addChild(I2.getId());
        f1.addChild(I3.getId());
        f1.addChild(I4.getId());
        f1.addChild(I5.getId());
        f1.addChild(I6.getId());
        b.add(f1);
        
        assertEquals(false,sprint2.US14(b, a));
        a.remove(I6);
        Family f2 = new Family();
        f2.setDad(F);
        f2.setMom(M);
        f2.addChild(I1.getId());
        f2.addChild(I2.getId());
        f2.addChild(I3.getId());
        f2.addChild(I4.getId());
        f2.addChild(I5.getId());
        b.remove(f1);
        b.add(f2);
        assertEquals(true,sprint2.US14(b, a));
    }
    public void testUS15() {
        Sprint2 sprint2 = new Sprint2();
        Person[] p = new Person[17];
        Family f = new Family();
        for(int i = 0;i < 17;i++){
            p[i] = new Person();
            p[i].setId(String.valueOf(i));
            f.addChild(String.valueOf(i));
        }
        ArrayList<Family> fam = new ArrayList<Family>();
        f.setId("FAM123");
        fam.add(f);
        assertEquals(false, sprint2.US15(fam));
    }

    public void testUS16(){
        Sprint2 sprint2 = new Sprint2();
        Person p1 = new Person();
        p1.setId("001");
        p1.setLastName("Boffa");
        Person p2 = new Person();
        p2.setLastName("Mike");
        p2.setId("002");
        Family f1 = new Family();
        f1.setMarriageDate("1", "JAN", "2021");
        f1.addChild(p1.getId());
        f1.addChild(p2.getId());
        f1.setId("F007");
        ArrayList<Person> person = new ArrayList<Person>();
        person.add(p1);
        person.add(p2);
        ArrayList<Family> fam = new ArrayList<Family>();
        fam.add(f1);
        assertEquals(false, sprint2.US16(fam, person));
    }
    
}