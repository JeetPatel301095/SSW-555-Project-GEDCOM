class Indiparser
{
    public void parseindi(String[] a)
    {
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
            }
        }
    }
}