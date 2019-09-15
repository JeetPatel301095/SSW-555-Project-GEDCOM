import java.util.ArrayList;
class Family
{
    protected String id;
    protected Person dad;
    protected Person mom;
    protected String marriageDate;
    protected String divorcedDate;
    protected ArrayList<String> children;

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
    public void setMarriageDate(String date)
    {
        this.marriageDate = date;
    }
    public void setDivorceDate(String date)
    {
        this.divorcedDate = date;
    }
    public void addChild(String child)
    {
        children.add(child);
    }

    // Getters
    public String getId()
    {
        return this.id;
    }
    public String getHusbandId()
    {
        return this.dad.getId();
    }
    public String getHusbandFullName()
    {
        return this.dad.getFirstName() + this.dad.getLastName();
    }
    public String getWifeId()
    {
        return this.mom.getId();
    }
    public String getWifeFullName()
    {
        return this.mom.getFirstName() + this.mom.getLastName();
    }
    public String getMarriageDate()
    {
        return this.marriageDate;
    }
    public String getDivorceDate()
    {
        return this.divorcedDate;
    }
    public ArrayList<String> getChildrenIds()
    {
        return this.children;
    }

}