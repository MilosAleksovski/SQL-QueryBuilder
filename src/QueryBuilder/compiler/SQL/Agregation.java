package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class Agregation {

    private String parametar;
    private Select select;
    private List<String> avgParametri;
    private List<String> minParametri;
    private List<String> maxParametri;
    private List<String> countParametri;
    private List<String> trenutnaLista;
    private String groupByParametri = " group by ";


    public Agregation(Select select) {
        this.select = select;
        avgParametri = new ArrayList<>();
        minParametri = new ArrayList<>();
        maxParametri = new ArrayList<>();
        countParametri = new ArrayList<>();
    }

    public void napraviAgr(String string, String tip){

        String UIAvg = string;
        int flag = 0;
        parametar = "";

        if(tip.equals("Avg"))
            trenutnaLista = avgParametri;
        if(tip.equals("Min"))
            trenutnaLista = minParametri;
        if(tip.equals("Max"))
            trenutnaLista = maxParametri;
        if(tip.equals("Count"))
            trenutnaLista = countParametri;



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

    }



    public void groupBy(String groupBy){

        int flag = 0;

        String parametarGb = groupBy;

        parametar = "";




        for(int i = 0; i < parametarGb.length(); i ++){

            if(parametarGb.charAt(i) == '"' && flag == 1) {
                flag = 0;
                this.groupByParametri += parametar;
                if(i == parametarGb.length() - 2)
                    break;
                this.groupByParametri += ",";
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(parametarGb.charAt(i));

            }


            if(parametarGb.charAt(i) == '"')
                flag = 1;


        }

        System.out.println("GROUP BY AGR " + this.groupByParametri);


    }



    public String vratiSaAvg(){


            String avg = "";
            String as = "";
            String s = this.select.vratiSelectParametre();
            String resenje = "";
            String kolonaSelect = "";




            String split[] = s.split(",");

            if(!select.getSelectParametri().isEmpty()) {


                for (int i = 0; i < trenutnaLista.toArray().length; i++) {

                    String string = trenutnaLista.toArray()[i].toString();

                    if (s.contains(string)) {
                        as = "as " + "\"" + string + "\"";
                    } else if (avgParametri.size() > 1) avg = "avg(" + string + ") ";
                    else if (minParametri.size() > 1) avg = "min(" + string + ") ";
                    else if (maxParametri.size() > 1) avg = "max(" + string + ") ";
                    else if (countParametri.size() > 1) avg = "count(" + string + ") ";

                }

                resenje = avg + as;

                kolonaSelect = "";

                for (String sa : split) {
                    if (!avg.contains(s) && !as.contains(s)) {
                        if(!as.contains(sa)) {
                            kolonaSelect += sa;
                            kolonaSelect += ",";
                        }
                    }
                }

                return kolonaSelect+resenje;
            }
            else {
                System.out.println("RESENJE " +"Avg(" + avgParametri.get(0) + ")" + " as " + avgParametri.get(0));
                return "Avg(" + avgParametri.get(0) + ")" + " as " + avgParametri.get(1);


            }







    }

    public String vratiRez(){

        if(avgParametri.isEmpty() && maxParametri.isEmpty() && minParametri.isEmpty() && countParametri.isEmpty()){
            if(select.getSelectParametri().isEmpty()){
                return "*";
            }
            else{
                return select.vratiSelectParametre();
            }
        }

        if(avgParametri.size() == 1){
            String avg = "Avg(" + avgParametri.get(0) +")" ;
            if(select.getSelectParametri().isEmpty()){
                return avg;
            }

        }
        if(minParametri.size() == 1){
            String avg = "Min(" + minParametri.get(0) +")" ;
            if(select.getSelectParametri().isEmpty()){
                return avg;
            }

        }
        if(maxParametri.size() == 1){
            String avg = "Max(" + maxParametri.get(0) +")" ;
            if(select.getSelectParametri().isEmpty()){
                return avg;
            }

        }
        if(countParametri.size() == 1){
            String avg = "Count(" + countParametri.get(0) +")" ;
            if(select.getSelectParametri().isEmpty()){
                return avg;
            }

        }
        if (avgParametri.size() > 1 || minParametri.size() >1 || maxParametri.size() > 1 || countParametri.size() >1){
            return vratiSaAvg();
        }



        return null;
    }



    public List<String> getAvgParametri() {
        return avgParametri;
    }

    public String getGroupByParametri() {
        if(!this.groupByParametri.equals(" group by "))
        return groupByParametri;
        else return "";
    }

}
