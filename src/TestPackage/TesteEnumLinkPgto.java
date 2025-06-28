package TestPackage;

import Model.ENUMS.LinkPagamento;

public class TesteEnumLinkPgto {
    public static void main(String[] args) {
        LinkPagamento l = LinkPagamento.QRCODE;
        LinkPagamento l2 = LinkPagamento.COD_BARRAS;
        System.out.println(l.showLink());
        System.out.println(l2.showLink());
    }
}
