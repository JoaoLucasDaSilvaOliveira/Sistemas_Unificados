package Control;

import DAO.GuiaDAO;
import Model.ENUMS.GuiaTypes;
import Model.EXEPTIONS.NoSuchReference;
import Model.Guia;
import java.time.YearMonth;
import java.util.ArrayList;

public class GuiaController implements OperacoesGuias <Guia>{
    @Override
    public Guia create(YearMonth competenciaDesejada, GuiaTypes tipo) throws Exception{
        if (competenciaDesejada == null) throw new NullPointerException("A data não pode ser nula");
        GuiaDAO g = new GuiaDAO();
        //retornar null caso não tenha encontrado a competência desejada
        ArrayList<Guia> guias  = (ArrayList<Guia>) g.searchByValue (competenciaDesejada);
        if (guias == null) throw new NoSuchReference("A competência "+ competenciaDesejada.toString() + " não foi encontrada");
        for (Guia gu : guias){
            if (gu.getTipo().equals(tipo)){
                return gu;
            }
        }
        throw new Exception("Não ha guias de "+tipo.getName()+" no mês de "+competenciaDesejada);
    }
}
