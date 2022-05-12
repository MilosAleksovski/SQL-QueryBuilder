package ErrorHandler;

import ErrorHandler.ErrorObserver.ErrPublisher;
import ErrorHandler.ErrorObserver.ErrSubscriber;
import app.ErrHandler;

import java.util.ArrayList;
import java.util.List;

public class ErrImpl implements ErrHandler ,ErrPublisher {

    List<ErrSubscriber> errorSubscribers;


    @Override
    public void generateError(VrstaGreske type) {
        if(type.equals(VrstaGreske.NEMAQUERY)) {
            notifyErrorSubs(new GenError(1, "Korisnik nije uneo Query"));
        }
        if(type.equals(VrstaGreske.SYNTAXERROR)) {
            notifyErrorSubs(new GenError(1, "Nepravilan unos funkcija - Sintaksna greska !"));
        }
        if(type.equals(VrstaGreske.PRAZANSELECT)) {
            notifyErrorSubs(new GenError(1, "Nepravilan unos Select funkcije - Ne postoje parametri!"));
        }
        if(type.equals(VrstaGreske.GROUPBYBEZSVIHPARAMETARA)) {
            notifyErrorSubs(new GenError(1, "Group by nema sve parametre iz selecta koji nisu agregacija!"));
        }
        if(type.equals(VrstaGreske.GROUPBY)) {
            notifyErrorSubs(new GenError(1, "Postoji agregacija, ne postoji groupBy"));
        }
        if(type.equals(VrstaGreske.HAVINGPARAMETRI)) {
            notifyErrorSubs(new GenError(1, "Postoji having koji nema sve parametre agregacije!"));
        }
        if(type.equals(VrstaGreske.AndOrdHavingBezHaving)) {
            notifyErrorSubs(new GenError(1, "Postoji And having ili Or Having ali ne postoji Having!"));
        }
        if(type.equals(VrstaGreske.INIT)) {
            notifyErrorSubs(new GenError(1, "Greska priliko deklaracije promenljive!"));
        }
        if(type.equals(VrstaGreske.ORANDWHERE)) {
            notifyErrorSubs(new GenError(1, "Postoji funkcija And Where ili Or Where - mora postojati i Where!!"));
        }
        if(type.equals(VrstaGreske.WHEREBETWEEN)) {
            notifyErrorSubs(new GenError(1, "Postoji funkcija Where i WhereBetween"));
        }
        if(type.equals(VrstaGreske.WHEREIN)) {
            notifyErrorSubs(new GenError(1, "Postoji funkcija WhereIn , ne postoji ParametarList ili obrnuto"));
        }
        if(type.equals(VrstaGreske.WHEREPARAMETRI)) {
            notifyErrorSubs(new GenError(1, "NETACAN BROJ PARAMETARA U WHERE FUNKCIJI!"));
        }
        if(type.equals(VrstaGreske.JOINON)) {
            notifyErrorSubs(new GenError(1, "Postoji funkcija Join , ne postoji On ili obrnuto"));
        }
    }


    @Override
    public void addErrorSub(ErrSubscriber errorSubscriber) {
        if(errorSubscriber == null)
            return;
        if(this.errorSubscribers ==null)
            this.errorSubscribers = new ArrayList<>();
        if(this.errorSubscribers.contains(errorSubscriber))
            return;
        this.errorSubscribers.add(errorSubscriber);
    }

    @Override
    public void removeErrorSub(ErrSubscriber errorSubscriber) {
        if(errorSubscriber == null || this.errorSubscribers == null || !this.errorSubscribers.contains(errorSubscriber))
            return;
        this.errorSubscribers.remove(errorSubscriber);
    }

    @Override
    public void notifyErrorSubs(GenError error) {
        if(error == null || this.errorSubscribers == null || this.errorSubscribers.isEmpty())
            return;
        for(ErrSubscriber listener : errorSubscribers){
            listener.updateErrorSubscribers(error);
        }
    }
}
