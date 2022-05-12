package observer;


import observer.enums.NotificationCode;
import resource.implementation.InformationResource;


public class Notification {
    private NotificationCode code;
    private Object data;

    public Notification(NotificationCode notificationCode, InformationResource ir) {
        this.code = notificationCode;
        this.data = ir;
    }

    public NotificationCode getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
