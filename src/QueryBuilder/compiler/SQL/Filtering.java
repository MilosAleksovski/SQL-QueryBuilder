package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class Filtering {

    private List<String> where = new ArrayList<>();
    private List<String> orWhere = new ArrayList<>();
    private List<String> andWhere = new ArrayList<>();
    private List<String> whereBetween = new ArrayList<>();
    private List<String> WhereEndsWith = new ArrayList<>();
    private List<String> WhereStartsWith = new ArrayList<>();
    private List<String> WhereContains = new ArrayList<>();
    private List<String> trenutnaLista;
    private String whereIn = "";
    private String parametarWhereIn = "";

    private List<String> tip;



    public void napraviWhereIn(String string){
        String UIAvg = string;
        int flag = 0;
        String parametar = "";



        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
                flag = 0;
                whereIn = parametar;
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));

            }


            if(UIAvg.charAt(i) == '"')
                flag = 1;
        }
        System.out.println("Napravio Sam whereIn :" + whereIn);

    }

    public void napraviStringOperacije(String string){
        String UIAvg = string;
        int flag = 0;
        String parametar = "";

        if(string.contains("WhereEndsWith"))
            trenutnaLista = WhereEndsWith;
        if(string.contains("WhereStartsWith"))
            trenutnaLista = WhereStartsWith;
        if(string.contains("WhereContains"))
            trenutnaLista = WhereContains;



        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
                flag = 0;
                trenutnaLista.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));

            }


            if(UIAvg.charAt(i) == '"')
                flag = 1;
        }
        System.out.println("Napravio Sam whereIn :" + whereIn);

    }




    public void parametarList(String string){
        String UIAvg = string;
        int flag = 0;
        String parametar = "";



        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
                flag = 0;
                parametarWhereIn += "'";
                parametarWhereIn += parametar;
                parametarWhereIn += "'";
                if(i == UIAvg.length() - 3)
                break;
                parametarWhereIn += ",";
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));

            }


            if(UIAvg.charAt(i) == '"')
                flag = 1;
        }

    }


    public void napraviWhereBetween(String string){
        String UIAvg = string;
        int flag = 0;
        String parametar = "";



        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
                flag = 0;
                whereBetween.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));

            }


            if(UIAvg.charAt(i) == '"')
                flag = 1;
        }


        String split[] = string.split(",");
        whereBetween.add(split[1]);
        whereBetween.add(split[2].replace(")",""));



    }




    public void napraviWhereAndOr(String string){
        String UIAvg = string;
        int flag = 0;
        String parametar = "";

        if(string.contains("OrWhere")){
            tip = orWhere;
        }
        else if(string.contains("AndWhere")){
            tip = andWhere;
        }
        else if(string.contains("Where")){
            tip = where;
        }

        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
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

        if(tip.size() == 2) {
            String split[] = string.split(",");
            String broj = "";
            for (int i = 0; i < split[2].length() - 1; i++) {
                broj += split[2].charAt(i);
            }

            tip.add(broj);
        }

        if(tip.get(1).toString().equals("like")){
            tip.set(2, "'" + tip.get(2) + "'");
        }



    }

    public String vratiFilter(){

        System.out.println(whereIn.length() + "AAAAAAAAAAAA");


        if(!where.isEmpty() && orWhere.isEmpty() && andWhere.isEmpty()){
            return " Where " + where.get(0) + " " + where.get(1) + " " + where.get(2) + " ";
        }
        if(!where.isEmpty() && !orWhere.isEmpty() && andWhere.isEmpty()){
            return " Where " + where.get(0) + " " + where.get(1) + " " + where.get(2) + " " + "OR " + orWhere.get(0) + " " + orWhere.get(1) +" "+ orWhere.get(2)+ " ";
        }
        if(!where.isEmpty() && orWhere.isEmpty() && !andWhere.isEmpty()){
            return " Where " + where.get(0) + " " + where.get(1) + " " + where.get(2) + " " + "AND " + andWhere.get(0) + " " + andWhere.get(1) +" "+ andWhere.get(2)+ " ";
        }
        if(!where.isEmpty() && !orWhere.isEmpty() && !andWhere.isEmpty()){
            return " Where " + where.get(0) + " " + where.get(1) + " " + where.get(2) + " " + "AND " + andWhere.get(0) + " " + andWhere.get(1) +" "+ andWhere.get(2)+ " "+ "OR " + orWhere.get(0) + " " + orWhere.get(1) +" "+ orWhere.get(2)+ " ";
        }

        if(!whereBetween.isEmpty()){
            return " Where " + whereBetween.get(0) + " Between " + whereBetween.get(1) + " AND " + whereBetween.get(2)+ " ";
        }

        if(!whereIn.equals("")){
            return " Where " + whereIn + " IN("  + parametarWhereIn+ ") ";
        }
        if(!WhereEndsWith.isEmpty()){
            return " Where " +  WhereEndsWith.get(0) + " Like " +"'%"+WhereEndsWith.get(1)+"' ";
        }
        if(!WhereContains.isEmpty()){
            return " Where " +  WhereContains.get(0) + " Like " +"'%"+WhereContains.get(1)+"%' ";
        }
        if(!WhereStartsWith.isEmpty()){
            return " Where " +  WhereStartsWith.get(0) + " Like " +"'"+WhereStartsWith.get(1)+"%' ";
        }

        return  "";
    }
}
