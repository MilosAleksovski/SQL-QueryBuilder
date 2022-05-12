package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.ArrayList;
import java.util.List;

public class SelectRule extends Pravilo{


    @Override
    public VrstaGreske proveriPravilo(String pravilo) {

        if(!pravilo.contains("Query")){
            return VrstaGreske.NEMAQUERY;
        }

        if(pravilo.contains("Select") &&  pravilo.contains("GroupBy")&& (pravilo.contains("Avg") || pravilo.contains("Min") || pravilo.contains("Max") || pravilo.contains("Count"))) {

            List<String> parametriBezAgr = new ArrayList<>();

            List<String> selectParametri = getSplitString().vratiParametreUnutarZagrade(pravilo,"Select");
            List<String> groupBy = getSplitString().vratiParametreUnutarZagrade(pravilo,"GroupBy");
            List<String> min = getSplitString().vratiParametreUnutarZagrade(pravilo,"Avg");
            List<String> max = getSplitString().vratiParametreUnutarZagrade(pravilo,"Min");
            List<String> avg = getSplitString().vratiParametreUnutarZagrade(pravilo,"Max");
            List<String> count = getSplitString().vratiParametreUnutarZagrade(pravilo,"Count");

            for(String string: selectParametri){
                if(!min.contains(string) && !max.contains(string) && !avg.contains(string) && !count.contains(string)){
                    parametriBezAgr.add(string);
                }
            }


            for(String string: parametriBezAgr){
                if(!groupBy.contains(string)){
                    return VrstaGreske.GROUPBYBEZSVIHPARAMETARA;
                }
            }


        }

        if(pravilo.contains("Select") &&  !pravilo.contains("GroupBy")&& (pravilo.contains("Avg") || pravilo.contains("Min") || pravilo.contains("Max") || pravilo.contains("Count"))) {
            return VrstaGreske.GROUPBY;
        }


        return VrstaGreske.ISPRAVNOPRAVILO;
    }
}
