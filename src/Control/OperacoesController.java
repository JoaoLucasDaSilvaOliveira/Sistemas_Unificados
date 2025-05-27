package Control;

public interface OperacoesController <T>{
    public abstract T validate (T object) throws Exception;
}
