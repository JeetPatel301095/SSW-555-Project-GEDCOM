import junit.framework.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Sprint03Test extends Sprint3{



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
}