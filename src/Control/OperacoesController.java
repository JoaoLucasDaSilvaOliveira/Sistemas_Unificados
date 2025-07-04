package Control;

/**
 * Interface genérica que define operações de validação e criação de objetos.
 *
 * @param <T> O tipo de objeto que será manipulado pelas operações
 */
public interface OperacoesController<T> {
    /**
     * Valida e cria um novo objeto do tipo especificado.
     *
     * @param object O objeto a ser validado e criado
     * @return O objeto criado após validação
     * @throws Exception Se ocorrer algum erro durante a validação ou criação
     */
    public  T validateAndCreate(T object) throws Exception;
}
