import java.util.*;

class Person
{
    protected String ID ="";
    protected String FirstName="";
    protected String LastName="";
    // protected char Sex='';
    protected Date BirthDate;
    protected boolean Dead=false;
    protected Date DeathDate;
    protected int Age=0;

    
    /*
        Setters Declared
    */

    // /*
    //     Declaring all the Getters
    // */
    // String getId();
    // String getFirstName();
    // String getLastName();
    // char getSex();
    // Date getBirthDate();
    // boolean getDead();
    // Date getDeathDate();
    // int getAge();
    
    // /*
    //     Getters Declared
    // */

    /*
        Declaring Setters
    */

    public void setId(String id)
    {
        this.ID=id;
        System.out.println(ID+" P");
        System.out.println("ID");
    }

    public void setFirstName(String fname)
    {
        System.out.println("FNAME");
        this.FirstName=fname;
        System.out.println(fname+" R");
    }

    public void setLastName(String lname)
    {
        System.out.println("LNAME");
        String lname2;
        lname2=lname.replaceAll("/","");
        this.LastName=lname2;
        System.out.println(lname);
    }
    
    // public void setSex(char sex)
    // {
    //     this.Sex=sex;
    // }

    public void setBirthDate(Date bdate)
    {
        this.BirthDate=bdate;
    }

    public void setDeathDate(Date ddate)
    {
        this.DeathDate=ddate;
    }

    public void setDead(boolean dead)
    {
        this.Dead=dead;
    }

    /*
        Declared Setters
    */
}