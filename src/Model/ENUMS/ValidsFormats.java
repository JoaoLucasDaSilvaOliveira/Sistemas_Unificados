package Model.ENUMS;

public enum ValidsFormats {
    CNPJ {
        @Override
        public String getRegex() {
            return "XX.XXX.XXX/0001-XX";
        }
    },
    ENTERPRISE_NAME{
        @Override
        public String getRegex() {
            return "Nome da Empresa";
        }
    },
    USER_NAME{
        @Override
        public String getRegex() {
            return "Nome do Usuário";
        }
    },
    EMAIL{
        @Override
        public String getRegex() {
            return "seuemail@gmail.com (aceitos: hotmail, terra, outlook...)";
        }
    };
    public abstract String getRegex();

}
