package Model.ENUMS;

public enum StatusPagamento {
    PAGO ("Guia paga!"),
    PENDENTE("Guia aguardando emissão!"),
    AGUARDANDO_PAGAMENTO("Guia aguardando pagamento!"),
    VENCIDO("Guia vencida!");

    StatusPagamento (String txt){
        this.texto = txt;
    }

    private final String texto;

    public String toString(){
        return this.texto;
    }

}