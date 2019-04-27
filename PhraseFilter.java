
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {

    private String where;
    private String phrase;
    public PhraseFilter(String wh, String ph)
    {
        where=wh;
        phrase=ph;
    }
    public boolean satisfies(QuakeEntry qe)
    {
        if(where.equals("start"))
        {
            String title = qe.getInfo();
            if(title.startsWith(phrase))
            {
                return true;
            }
        }
        if(where.equals("end"))
        {
           String title = qe.getInfo();
            if(title.endsWith(phrase))
            {
                return true;
            }  
        }
        if(where.equals("any"))
        {
            String title = qe.getInfo();
            if(title.indexOf(phrase)!=-1)
            {
                return true;
            }
        }
        return false;
    }
    public String getName()
    {
        return "Phrase Filter";
    }
}
