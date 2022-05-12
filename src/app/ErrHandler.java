package app;


import ErrorHandler.ErrorObserver.ErrPublisher;
import ErrorHandler.VrstaGreske;

public interface ErrHandler extends ErrPublisher {
    void generateError(VrstaGreske type);
}
