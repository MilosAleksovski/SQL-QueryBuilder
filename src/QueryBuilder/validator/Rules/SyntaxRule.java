package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.List;


public class SyntaxRule extends Pravilo {
    @Override
    public VrstaGreske proveriPravilo(String pravilo) {


        List<String> listaFunkcija = getSplitString().vratiNaziveFunkcija(pravilo);

        int flag = 0;
        int flag2 = 0;



        for(int i = 1; i < listaFunkcija.size(); i++){

            String funkcija = listaFunkcija.get(i);

            for(Syntax c : Syntax.values()){

                String str = String.valueOf(c);

                if(str.equals(funkcija)){
                     flag = 1;
                }

            }

            if(flag == 0){
                flag2 = 1;
                break;
            }
            flag = 0;

        }



        if(listaFunkcija.size() == 1){
            return VrstaGreske.ISPRAVNOPRAVILO;
        }

        if(flag2 == 0) {
            return VrstaGreske.ISPRAVNOPRAVILO;
        }
        else {
            return VrstaGreske.SYNTAXERROR;
        }
    }
}
