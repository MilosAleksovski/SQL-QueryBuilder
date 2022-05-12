package QueryBuilder;

import ErrorHandler.VrstaGreske;
import QueryBuilder.compiler.CompilerComp;
import QueryBuilder.compiler.CompilerCompImpl;
import QueryBuilder.validator.ValidatorComp;
import QueryBuilder.validator.ValidatorCompImpl;
import gui.MainFrame;

public class QueryBuilderImpl implements QueryBuilder {

    private ValidatorComp validatorComp;
    private CompilerComp compilerComp;


    public QueryBuilderImpl(ValidatorComp validatorComp, CompilerComp compilerComp) {
        this.validatorComp = validatorComp;
        this.compilerComp = compilerComp;
    }



    @Override
    public String queryEngine(String query) {
        String s = validatorComp.validate(query);

        if(s == "True"){
            return compilerComp.compile(query);
        }
        else {
            return "False";
        }

    }
}
