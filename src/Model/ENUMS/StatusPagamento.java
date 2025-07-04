package Model.ENUMS;

/**
 * Enum que representa os diferentes status de pagamento possíveis para uma guia.
 */
public enum StatusPagamento {
    PAGO ("Guia paga!"),
    PENDENTE("Guia aguardando emissão!"),
    AGUARDANDO_PAGAMENTO("Guia aguardando pagamento!"),
    VENCIDO("Guia vencida!");

    StatusPagamento (String txt){
        this.texto = txt;
    }

    /**
     * O texto descritivo associado ao status de pagamento.
     */
    private final String texto;

    /**
     * Retorna o texto descritivo do status de pagamento.
     *
     * @return O texto que descreve o status de pagamento
     */
    public String toString(){
        return this.texto;
    }

}