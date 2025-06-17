package Control;

import Model.ENUMS.GuiaTypes;
import Model.EXEPTIONS.NoSuchReference;
import Model.Guia;
import java.time.YearMonth;
import java.util.ArrayList;

public class GuiaController implements OperacoesGuias <Guia>{
    @Override
    public Guia create(YearMonth competenciaDesejada, GuiaTypes tipo) throws NoSuchReference{
        if (competenciaDesejada == null) throw new NullPointerException("A data não pode ser nula");
        GuiaDAO g = new GuiaDAO();
        //retornar null caso não tenha encontrado a competência desejada
        ArrayList<Guia> guias  = g.searchByValue (competenciaDesejada);
        if (guias == null) throw new NoSuchReference("A competência "+ competenciaDesejada.toString() + " não foi encontrada");
        for (Guia g : guias){
            if (g.getTipo().equals(tipo)){

            }
        }
    }
}
