package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.ArrayList;
import java.util.List;

public class HavingRule extends  Pravilo {
    @Override
    public VrstaGreske proveriPravilo(String pravilo) {


        if(pravilo.contains("Having")  && (pravilo.contains("Avg") || pravilo.contains("Min") || pravilo.contains("Max") || pravilo.contains("Count"))) {

            List<String> parametriagrBezSelecta = new ArrayList<>();

            List<String> selectParametri = getSplitString().vratiParametreUnutarZagrade(pravilo,"Select");
            List<String> min = getSplitString().vratiParametreUnutarZagrade(pravilo,"Avg");
            List<String> max = getSplitString().vratiParametreUnutarZagrade(pravilo,"Min");
            List<String> avg = getSplitString().vratiParametreUnutarZagrade(pravilo,"Max");
            List<String> count = getSplitString().vratiParametreUnutarZagrade(pravilo,"Count");
            List<String> having = getSplitString().vratiParametreUnutarZagrade(pravilo,"Having");
            List<String> orHaving = getSplitString().vratiParametreUnutarZagrade(pravilo,"OrHaving");
            List<String> andHaving = getSplitString().vratiParametreUnutarZagrade(pravilo,"AndHaving");


            if(having.isEmpty() && (!orHaving.isEmpty() || !andHaving.isEmpty())){
                return VrstaGreske.AndOrdHavingBezHaving;
            }


            for(String string: min){
                if(!selectParametri.contains(string)){
                    parametriagrBezSelecta.add(string);
                }
            }
            for(String string: max){
                if(!selectParametri.contains(string)){
                    parametriagrBezSelecta.add(string);
                }
            }
            for(String string: count){
                if(!selectParametri.contains(string)){
                    parametriagrBezSelecta.add(string);
                }
            }
            for(String string: avg){
                if(!selectParametri.contains(string)){
                    parametriagrBezSelecta.add(string);
                }
            }



            for(String string: parametriagrBezSelecta){
                String s = string.substring(1,string.length() - 1);

                String h = having.toString();
                System.out.println(h);
                if(!h.contains(s)){

                    return VrstaGreske.HAVINGPARAMETRI;
                }
            }


        }

        return VrstaGreske.ISPRAVNOPRAVILO;
    }
}
