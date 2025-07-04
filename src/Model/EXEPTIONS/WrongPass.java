package Model.EXEPTIONS;

public class WrongPass extends RuntimeException {
    public WrongPass(String message) {
        super(message);
    }
}
