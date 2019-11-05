import junit.framework.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Sprint04Test {


    @Test
    public void testUS34(){
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
    public void testUS35(){
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
}
