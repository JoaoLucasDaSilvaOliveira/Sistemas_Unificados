package Control;

import DAO.GuiaDAO;
import Model.ENUMS.GuiaTypes;
import Model.EXEPTIONS.NoSuchReference;
import Model.Guia;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador responsável por gerenciar operações relacionadas a guias.
 * Implementa a interface OperacoesGuias para guias.
 */
public class GuiaController implements OperacoesGuias <Guia>{
    /**
     * Cria e retorna uma guia baseada na competência e tipo especificados.
     *
     * @param competenciaDesejada A competência (mês e ano) desejada para a guia
     * @param tipo                O tipo de guia a ser criada
     * @return A guia encontrada com a competência e tipo especificados
     * @throws NullPointerException Quando a data de competência é nula
     * @throws NoSuchReference      Quando a competência especificada não é encontrada
     * @throws Exception            Quando não há guias do tipo especificado para a competência
     */
    @Override
    public Guia create(YearMonth competenciaDesejada, GuiaTypes tipo) throws Exception{
        if (competenciaDesejada == null) throw new NullPointerException("A data não pode ser nula");
        GuiaDAO g = new GuiaDAO();
        //retornar null caso não tenha encontrado a competência desejada
        List<Guia> guias  = g.searchByValue (competenciaDesejada);
        if (guias == null) throw new NoSuchReference("A competência "+ competenciaDesejada.toString() + " não foi encontrada");
        for (Guia gu : guias){
            if (gu.getTipo().equals(tipo)){
                return gu;
            }
        }
        throw new Exception("Não ha guias de "+tipo.getName()+" no mês de "+competenciaDesejada);
    }
}
