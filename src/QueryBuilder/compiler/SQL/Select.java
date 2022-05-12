package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class Select {

    private String UIFrom;
    private List<String> selectParametri;
    private String from = "";
    private String parametar;
    private int flag = 0;

    public Select() {
        selectParametri = new ArrayList<>();
    }



    public void napraviFrom(String string){

            UIFrom = string;

            for(int i = 0; i < UIFrom.length(); i ++){

                if(UIFrom.charAt(i) == '"' && flag == 1) {
                    flag = 0;
                    break;
                }

                if(flag == 1){
                    from += String.valueOf(UIFrom.charAt(i));

                }


                if(UIFrom.charAt(i) == '"')
                    flag = 1;


            }


    }
    public void napraviSelect(String string){

        String UISelekt = string;

        parametar = "";




        for(int i = 0; i < UISelekt.length(); i ++){

            if(UISelekt.charAt(i) == '"' && flag == 1) {
                flag = 0;
                selectParametri.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UISelekt.charAt(i));

            }


            if(UISelekt.charAt(i) == '"')
                flag = 1;


        }

        for(String s:selectParametri){
            System.out.println(s);
        }



    }



    public String vratiSaZvezdicom(){

        return "SELECT * FROM" + " " + from;
    }
    public String vratiSaSelectBezAgregacije(){

        String s = "";

        for(int i = 0; i < selectParametri.toArray().length; i ++){
            s += selectParametri.toArray()[i].toString();
            if(i == selectParametri.size() - 1) break;
            s += ",";
        }

        System.out.println(s);

        if(s.equals("")){
            return "*";
        }

        return s ;
    }


    public String vratiSelectParametre(){

        String s = "";

        for(int i = 0; i < selectParametri.toArray().length; i ++){
            s += selectParametri.toArray()[i].toString();
            if(i == selectParametri.size() - 1) break;
            s += ",";
        }

        System.out.println(s);

        return s;
    }







    public List<String> getSelectParametri() {
        return selectParametri;
    }

    public String getFrom() {
        return from;
    }
}
