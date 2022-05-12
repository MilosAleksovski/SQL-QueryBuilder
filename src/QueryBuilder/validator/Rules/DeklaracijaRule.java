package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.ArrayList;
import java.util.List;

public class DeklaracijaRule extends Pravilo {
    @Override
    public VrstaGreske proveriPravilo(String pravilo) {

        List<String> listaFunkcija = getSplitString().vratiNaziveFunkcija(pravilo);

        System.out.println("|" + listaFunkcija.get(0));


        String split[] = listaFunkcija.get(0).split("\\s+");

        System.out.println(split.length);

        if(split.length != 5){
            return VrstaGreske.INIT;
        }

        if(!split[0].equals("var")){
            return VrstaGreske.INIT;
        }
        if(!split[2].equals("=")){
            return VrstaGreske.INIT;
        }
        if(!split[3].equals("new")){
            return VrstaGreske.INIT;
        }


        return VrstaGreske.ISPRAVNOPRAVILO;
    }
}
