package Model.ENUMS;

/**
 * Enumeração que representa os diferentes tipos de guias disponíveis no sistema.
 * Cada tipo de guia possui um nome associado que pode ser recuperado.
 */
public enum GuiaTypes {
    FGTS("Fgts"),
    DARF("Inss"),
    DAE("Simples Nacional");

    private final String name;

    /**
     * Construtor privado para inicializar o tipo de guia com seu nome.
     *
     * @param name O nome associado ao tipo de guia
     */
    private GuiaTypes(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome associado ao tipo de guia.
     *
     * @return O nome do tipo de guia
     */
    public String getName() {
        return name;
    }
}
