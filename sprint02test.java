import junit.framework.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Sprint02Test extends Sprint2{

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