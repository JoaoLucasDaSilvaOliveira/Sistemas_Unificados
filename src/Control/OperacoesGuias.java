package Control;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public interface OperacoesGuias<T>{
    public T create (YearMonth competenciaDesejada) throws Exception;
}
