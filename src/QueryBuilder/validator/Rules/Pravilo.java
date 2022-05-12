package QueryBuilder.validator.Rules;

import ErrorHandler.VrstaGreske;

public abstract class Pravilo {

    private SplitString splitString = new SplitString();

    public abstract VrstaGreske proveriPravilo(String pravilo);

    public SplitString getSplitString() {
        return splitString;
    }
}
