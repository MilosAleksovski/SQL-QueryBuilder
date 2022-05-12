package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class Having {

    private List<String> having = new ArrayList<>();
    private List<String> andHaving = new ArrayList<>();
    private List<String> orHaving = new ArrayList<>();

    private List<String> tip;

    public void napraviHaving(String s) {
        System.out.println("CAOOOO");
        System.out.println(s + "Uzeti splitovani string");

        String UIAvg=s;

        int flag = 0;
        int flag2=0;
        String parametar = "";

        if(s.contains("OrHaving"))
            tip=orHaving;
        else if(s.contains("AndHaving"))
            tip=andHaving;
        else if(s.contains("Having"))
            tip=having;

        for(int i = 0; i < UIAvg.length(); i++){

            if(UIAvg.charAt(i) == '"' && flag == 1 ) {
                flag = 0;
                tip.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));
            }
            if(UIAvg.charAt(i) == '"')
                flag = 1;

        }

        String split[]=s.split("[,]");
        String poslednjiParametar=split[2];
        String poslednjiParametar2="";

        for(int i=0;i<poslednjiParametar.length();i++)
        {
            if(poslednjiParametar.charAt(i)!=')')
                poslednjiParametar2+=poslednjiParametar.charAt(i);
        }

        tip.add(poslednjiParametar2);

        System.out.println(tip+"KAKO SAD IZGLEDA TIP");

    }

    public String vratiHaving() {

        if(!having.isEmpty() && andHaving.isEmpty() && orHaving.isEmpty()){
            return " Having " + having.get(0)+ " "+having.get(1)+" "+having.get(2);
        }
        if(!having.isEmpty() && !andHaving.isEmpty() && orHaving.isEmpty()){
            return " Having " + having.get(0)+ " "+having.get(1)+" "+having.get(2)+" AND "+andHaving.get(0)+" "+andHaving.get(1)+" "+andHaving.get(2)+" ";
        }
        if(!having.isEmpty() && andHaving.isEmpty() && !orHaving.isEmpty()){
            return " Having " + having.get(0)+ " "+having.get(1)+" "+having.get(2)+" OR "+orHaving.get(0)+" "+orHaving.get(1)+" "+orHaving.get(2)+" ";
        }

        return "";
    }
}
