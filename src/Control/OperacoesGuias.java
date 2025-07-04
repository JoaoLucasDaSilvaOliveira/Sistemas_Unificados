package Control;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.EXEPTIONS.NoSuchReference;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

/**
 * Interface responsável por definir operações de criação e gerenciamento de guias.
 *
 * @param <T> O tipo de guia que será manipulado pelas operações
 */
public interface OperacoesGuias<T> {
    /**
     * Cria e retorna uma guia baseada na competência e tipo especificados.
     *
     * @param competenciaDesejada A competência (mês e ano) desejada para a guia
     * @param tipo O tipo de guia a ser criada
     * @return A guia encontrada com a competência e tipo especificados
     * @throws NullPointerException Quando a data de competência é nula
     * @throws NoSuchReference Quando a competência especificada não é encontrada
     * @throws Exception Quando não há guias do tipo especificado para a competência
     */
    public T create (YearMonth competenciaDesejada, GuiaTypes tipo) throws Exception;
}
