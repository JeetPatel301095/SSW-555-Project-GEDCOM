class Indiparser
{
    public Person parseindi(String[] a)
    {
        int f=0;
        Person A = new Person();
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==null)
                continue;
            // System.out.println(a[i]);
            String[] b = a[i].split("\\s+");
            if(b[0].equals("0"))
            {
                A.setId(b[1]);
            }
            if(b[0].equals("1"))
            {
                if(b[1].equals("NAME"))
                {
                    System.out.println("Here");
                    A.setFirstName(b[2]);
                    A.setLastName(b[3]);
                }
                else if(b[1].equals("SEX"))
                {
                    A.setSex(b[2]);
                }
                else if(b[1].equals("BIRT"))
                {
                    f=1;
                }
                else if(b[1].equals("DEAT"))
                {
                    f=2;
                    A.setDead(true);
                }
                else if(b[1].equals("FAMC"))
                {
                    A.setFamc(b[2]);
                }
                else if(b[1].equals("FAMS"))
                {
                    A.setFamc(b[2]);
                }
            }
            if(b[0].equals("2"))
            {
                if(f==1)
                {
                    System.out.println("Going to B");
                    A.setBirthDate(b[2],b[3],b[4]);
                    f=0;
                }
                if(f==2)
                {
                    A.setDeathDate(b[2],b[3],b[4]);
                    f=0;
                }
            }
        }
        return A;
    }
}