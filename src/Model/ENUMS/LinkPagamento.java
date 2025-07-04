package Model.ENUMS;

import java.util.Random;
import java.util.UUID;

/**
 * Enumera os tipos de links de pagamento disponíveis no sistema.
 * Fornece mecanismos para geração automática de códigos de pagamento.
 */
public enum LinkPagamento {
    /**
     * Tipo de pagamento que gera um QR Code único
     */
    QRCODE(),
    /**
     * Tipo de pagamento que gera um código de barras
     */
    COD_BARRAS(1);

    /**
     * Construtor para pagamento via QR Code.
     * Gera automaticamente um identificador único.
     */
    private LinkPagamento() {
        codPgto = generateQRCode().toString();
    }

    /**
     * Construtor para pagamento via código de barras.
     *
     * @param codPagamento código identificador do tipo de pagamento
     */
    private LinkPagamento(long codPagamento) {
        codPgto = generateCodeBar().toString();
    }

    /**
     * Obtém o link de pagamento gerado.
     *
     * @return o código de pagamento em formato string
     */
    public String showLink() {
        return this.codPgto;
    }

    /**
     * Armazena o código de pagamento gerado (QR Code ou código de barras)
     */
    private final String codPgto;

    /**
     * Gera um identificador único para QR Code.
     *
     * @return um UUID para ser usado como QR Code
     */
    private UUID generateQRCode() {
        return UUID.randomUUID();
    }

    /**
     * Gera um número aleatório para código de barras.
     *
     * @return um número longo para ser usado como código de barras
     */
    private Long generateCodeBar (){
        return new Random().nextLong(100000000000L, 100000000000000000L);
    }
}
