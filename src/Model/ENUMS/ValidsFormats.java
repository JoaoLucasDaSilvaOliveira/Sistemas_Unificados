package Model.ENUMS;

/**
 * Enumeração que define os formatos válidos para diferentes tipos de dados no sistema.
 */
public enum ValidsFormats {
    /**
     * Formato válido para CNPJ.
     * Padrão esperado: XX.XXX.XXX/0001-XX
     */
    CNPJ {
        @Override
        public String getRegex() {
            return "XX.XXX.XXX/0001-XX";
        }
    },
    /**
     * Formato válido para nome de empresa.
     * Padrão esperado: Nome da Empresa
     */
    ENTERPRISE_NAME{
        @Override
        public String getRegex() {
            return "Nome da Empresa";
        }
    },
    /**
     * Formato válido para nome de usuário.
     * Padrão esperado: Nome do Usuário
     */
    USER_NAME{
        @Override
        public String getRegex() {
            return "Nome do Usuário";
        }
    },
    /**
     * Formato válido para endereço de e-mail.
     * Padrão esperado: seuemail@gmail.com (aceitos: hotmail, terra, outlook...)
     */
    EMAIL{
        @Override
        public String getRegex() {
            return "seuemail@gmail.com (aceitos: hotmail, terra, outlook...)";
        }
    };

    /**
     * Obtém o padrão regex associado ao formato.
     *
     * @return Uma String representando o padrão esperado para o formato.
     */
    public abstract String getRegex();

}
