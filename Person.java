import java.util.*;

import static java.util.Calendar.*;


class Person
{
    protected String ID ="";
    protected String FirstName="";
    protected String LastName="";
    protected String Sex="";
    protected Date BirthDate;
    protected boolean Dead=false;
    protected Date DeathDate;
    protected int Age=0;
    protected String fams = "NA";
    protected String famc = "NA";
    protected Person Child;
    protected Person Spouse;

    /*
        Declaring Setters
    */

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public void setId(String id)
    {
        this.ID=id;
//        System.out.println(ID+" P");
//        System.out.println("ID");
    }

    public void setFirstName(String fname)
    {
        this.FirstName=fname;
//        System.out.println("FNAME");
//        System.out.println(fname+" R");
    }

    public void setLastName(String lname)
    {
        this.LastName=lname;
//        System.out.println(lname);
    }

    public void setSex(String sex)
    {
        this.Sex=sex;
//        System.out.println(Sex+" l");
    }

    public void setBirthDate(String day,String Month, String Year)
    {
//        System.out.println("IN B");
        int d = Integer.parseInt(day);
        int m = changeMonthFormatToInt(Month);
        int y = Integer.parseInt(Year);
        Calendar c = Calendar.getInstance();
        c.set(y,m,d); 
        this.BirthDate=c.getTime();
//        System.out.println(BirthDate+" E");
    }

    public void setDeathDate(String day, String Month, String Year)
    {
        int d = Integer.parseInt(day);
        int m = changeMonthFormatToInt(Month);
        int y = Integer.parseInt(Year);
        Calendar c = Calendar.getInstance();
        c.set(y,m,d);
        this.DeathDate=c.getTime();
//        System.out.println(DeathDate+" D");
    }

    public void setDead(boolean dead)
    {
        this.Dead=dead;
    }

    public void setFamc(String famc)
    {
        this.famc = famc;
    }

    public void setFams(String fams)
    {
        this.fams = fams;
    }


    public void setAge() {
       if(this.getDead()){
           Calendar a = getCalendar(getBirthDate());
           Calendar b = getCalendar(getDeathDate());
           int diff = b.get(YEAR) - a.get(YEAR);
           if (a.get(MONTH) > b.get(MONTH) ||
                   (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
               diff--;
           }
           this.Age = diff;
       }else{
           Date today = new Date();
           Calendar a = getCalendar(getBirthDate());
           Calendar b = getCalendar(today);
           int diff = b.get(YEAR) - a.get(YEAR);
           this.Age = diff;
       }
    }


    /*
        Declared Setters
    */
    /*
        Function to Change from month to int
    */
    public static int changeMonthFormatToInt(String month) 
    {

        int monthNo = 0;
//        System.out.println(month);
        switch(month)
        {
            case "JAN": monthNo=0; break;
            case "FEB": monthNo=1; break;
            case "MAR": monthNo=2; break;
            case "APR": monthNo=3; break;
            case "MAY": monthNo=4; break;
            case "JUN": monthNo=5; break;
            case "JUL": monthNo=6; break;
            case "AUG": monthNo=7; break;
            case "SEP": monthNo=8; break;
            case "OCT": monthNo=9; break;
            case "NOV": monthNo=10; break;
            case "DEC": monthNo=11; break;

        }
//        System.out.println(monthNo);
		return monthNo;
    }
    
    /*
        Declaring Getters
    */
    public String getFamc() {
        return famc;
    }
    public String getId()
    {
        return this.ID;
    }
    public String getFirstName()
    {
        return this.FirstName;
    }
    public String getLastName()
    {
        return this.LastName;
    }
    public String getFullName() { return (this.FirstName+this.LastName);}
    public String getSex()
    {
        return this.Sex;
    }
    public Date getBirthDate()
    {
        return this.BirthDate;
    }
    public boolean getDead()
    {
        return this.Dead;
    }
    public Date getDeathDate()
    {
        return this.DeathDate;
    }
    public int getAge() {
        return Age;
    }
    public String getFams() {
        return fams;
    }

    /*
        Declared Getters
    */
    
}