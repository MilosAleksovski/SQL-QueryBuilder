package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.List;

public class JoinRule extends Pravilo {
    @Override
    public VrstaGreske proveriPravilo(String pravilo) {
        List<String> imenaFunkcija = getSplitString().vratiNaziveFunkcija(pravilo);
        int join = 0;
        int on = 0;

        for(String string : imenaFunkcija){
            if(string.equals("Join")){
                join = 1;
            }
            if(string.equals("On")){
                on = 1;
            }
        }


        if((join == 1 && on == 0) || (join == 0 && on == 1)){
            return VrstaGreske.JOINON;
        }


        return VrstaGreske.ISPRAVNOPRAVILO;
    }
}
