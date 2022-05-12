package QueryBuilder.validator;

import ErrorHandler.VrstaGreske;
import QueryBuilder.compiler.SQL.Select;
import QueryBuilder.validator.Rules.*;
import gui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ValidatorCompImpl implements ValidatorComp {

    List<Pravilo> listaPravila = new ArrayList<>();
    List<VrstaGreske> listaGresaka = new ArrayList<>();

    public ValidatorCompImpl() {
        Pravilo selectRule = new SelectRule();
        Pravilo syntaxRule = new SyntaxRule();
        Pravilo havingRule = new HavingRule();
        Pravilo deklaracija = new DeklaracijaRule();
        Pravilo where = new WhereRule();
        Pravilo join = new JoinRule();
        listaPravila.add(deklaracija);
        listaPravila.add(selectRule);
        listaPravila.add(syntaxRule);
        listaPravila.add(havingRule);
        listaPravila.add(where);
        listaPravila.add(join);
    }

    @Override
    public String validate(String query) {


        listaGresaka.clear();

        for (Pravilo pravilo : listaPravila) {

            VrstaGreske vrstaGreske = pravilo.proveriPravilo(query);
            if(!vrstaGreske.equals(VrstaGreske.ISPRAVNOPRAVILO)){
                listaGresaka.add(vrstaGreske);
            }

        }

        if(listaGresaka.isEmpty()){
            return "True";
        }
        else {

            for(VrstaGreske vrstaGreske:listaGresaka){
                    MainFrame.getInstance().getAppCore().getErrHandler().generateError(vrstaGreske);
            }

            return "False";
        }
    }




}

