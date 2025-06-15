package Control;

import Model.EXEPTIONS.NoSuchReference
import Model.Guia;
import java.time.YearMonth;

public class GuiaController implements OperacoesGuias <Guia>{
    @Override
    public Guia create(YearMonth competenciaDesejada) throws NoSuchReference{
        if (competenciaDesejada == null) throw new NullPointerException("A data não pode ser nula");
        GuiaDAO g = new GuiaDAO();
        //retornar null caso não tenha encontrado a competência desejada
        Guia guia = g.searchByValue (competenciaDesejada);
        if (guia == null) throw new NoSuchReference("A competência "+ competenciaDesejada.toString() + " não foi encontrada");
        return guia;
    }
}
