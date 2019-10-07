import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Family
{
    protected String id;
    protected Person dad;
    protected Person mom;
    protected Date marriageDate;
    protected Date divorcedDate;
    protected ArrayList<String> children;
    protected boolean divorced=false;

    public Family()
    {
        children = new ArrayList<>();
    }
    // Setters
    public void setId(String id)
    {
        this.id = id;
    }
    public void setDad(Person dad)
    {
        this.dad = dad;
    }
    public void setMom(Person mom)
    {
        this.mom = mom;
    }
    public void setMarriageDate(String day, String Month, String Year)
    {
        int d = Integer.parseInt(day);
        int m = changeMonthFormatToInt(Month);
        int y = Integer.parseInt(Year);
        Calendar c = Calendar.getInstance();
        c.set(y,m,d);
        this.marriageDate = c.getTime();
    }
    public void setDivorceDate(String day, String Month, String Year)
    {
        int d = Integer.parseInt(day);
        int m = changeMonthFormatToInt(Month);
        int y = Integer.parseInt(Year);
        Calendar c = Calendar.getInstance();
        c.set(y,m,d);
        this.divorcedDate=c.getTime();
    }
    public void addChild(String child)
    {
        children.add(child);
    }
    public void setDivorced(boolean divorced) {
        this.divorced = divorced;
    }
    // Getters
    public boolean getDivorced() {
        return divorced;
    }
    public String getId()
    {
        return this.id;
    }
    public String getHusbandId()
    {
        if(this.dad == null){
            return "NA";
        }else{
            return this.dad.getId();
        }
    }
    public String getHusbandLastName(){
        if(this.dad == null){
            return "NA";
        }else{
            return this.dad.getLastName();
        }
    }
    public String getHusbandFullName()
    {
        if(this.dad == null){
            return "NA";
        }else{
            return this.dad.getFirstName() + this.dad.getLastName();
        }
    }
    public String getWifeId()
    {
        if(this.mom == null){
            return "NA";
        }else{
            return this.mom.getId();
        }
    }
    public String getWifeFullName()
    {
        if(this.mom == null){
            return "NA";
        }else{
            return this.mom.getFirstName() + this.mom.getLastName();
        }
    }
    public Date getMarriageDate()
    {
        return this.marriageDate;
    }
    public Date getDivorceDate()
    {
        return this.divorcedDate;
    }
    public ArrayList<String> getChildrenIds()
    {
        return this.children;
    }


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
}