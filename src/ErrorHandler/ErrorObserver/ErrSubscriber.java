package ErrorHandler.ErrorObserver;

import ErrorHandler.GenError;

public interface ErrSubscriber {
    void updateErrorSubscribers(GenError error);
}
