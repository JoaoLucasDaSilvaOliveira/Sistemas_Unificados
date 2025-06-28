package Model.ENUMS;

public enum GuiaTypes {
    FGTS("Fgts"),
    DARF("Inss"),
    DAE ("Simples Nacional");

    private final String name;

    private GuiaTypes (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
