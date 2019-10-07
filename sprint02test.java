import junit.framework.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Sprint02Test extends Sprint2{

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