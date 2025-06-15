package Model.ENUMS;

import java.util.Random;
import java.util.UUID;

public enum LinkPagamento {
    QRCODE(),
    COD_BARRAS(1);

    private LinkPagamento (){
        codPgto = generateQRCode().toString();
    }
    private LinkPagamento (long codPagamento){
        codPgto = generateCodeBar().toString();
    }

    public String showLink (){
        return this.codPgto;
    }

    private final String codPgto;

    private UUID generateQRCode (){
        return UUID.randomUUID();
    }

    private Long generateCodeBar (){
        return new Random().nextLong(100000000000L, 100000000000000000L);
    }
}
