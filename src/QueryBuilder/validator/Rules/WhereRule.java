package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

import java.util.List;

public class WhereRule extends Pravilo {
    @Override
    public VrstaGreske proveriPravilo(String pravilo) {

        List<String> listaNazivaFunkcija = getSplitString().vratiNaziveFunkcija(pravilo);
        List<String> parametriWhere = getSplitString().vratiParametreUnutarZagrade(pravilo, "Where");
        List<String> parametriAndWhre = getSplitString().vratiParametreUnutarZagrade(pravilo, "AndWhere");
        List<String> parametriOrWhere = getSplitString().vratiParametreUnutarZagrade(pravilo, "OrWhere");
        List<String> parametriWhereBetween = getSplitString().vratiParametreUnutarZagrade(pravilo, "WhereBetween");


        int where = 0;
        int whereAnd = 0;
        int whereOr = 0;
        int whereBetween = 0;
        int whereIn = 0;
        int parametarList = 0;
        for(String nazivFunkcije: listaNazivaFunkcija) {

            if(nazivFunkcije.equals("Where"))
                where = 1;
            if(nazivFunkcije.equals("AndWhere"))
                whereAnd = 1;
            if(nazivFunkcije.equals("OrWhere"))
                whereOr = 1;
            if(nazivFunkcije.equals("WhereBetween"))
                whereBetween = 1;
            if(nazivFunkcije.equals("WhereIn"))
                whereIn = 1;
            if(nazivFunkcije.equals("ParametarList"))
                parametarList = 1;
        }

        if(where == 0 && (whereAnd == 1 || whereOr == 1)){
            return VrstaGreske.ORANDWHERE;
        }
        if(where == 1 && whereBetween == 1){
            return VrstaGreske.WHEREBETWEEN;
        }

        if((whereIn == 1 && parametarList == 0) ||(whereIn == 0 && parametarList == 1) || (whereIn == 1 && where == 1)){
            return VrstaGreske.WHEREIN;
        }



        if(!(parametriWhere.size() == 3) && (where == 1)){
            return VrstaGreske.WHEREPARAMETRI;
        }
        if(!(parametriAndWhre.size() == 3) && (whereAnd == 1)){
            return VrstaGreske.WHEREPARAMETRI;
        }
        if(!(parametriOrWhere.size() == 3) && (whereOr == 1)){
            return VrstaGreske.WHEREPARAMETRI;
        }

        if(!(parametriWhereBetween.size() == 3) && (whereBetween == 1)){
            return VrstaGreske.WHEREPARAMETRI;
        }

        return VrstaGreske.ISPRAVNOPRAVILO;
    }
}
