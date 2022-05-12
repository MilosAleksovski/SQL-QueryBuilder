package ErrorHandler.ErrorObserver;

import ErrorHandler.GenError;

public interface ErrPublisher {
    void addErrorSub(ErrSubscriber errorSubscriber);
    void removeErrorSub(ErrSubscriber errorSubscriber);
    void notifyErrorSubs(GenError error);
}
