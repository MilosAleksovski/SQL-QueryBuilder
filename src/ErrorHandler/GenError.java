package ErrorHandler;

public class GenError {
    int type;
    String message;

    public GenError(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
}
