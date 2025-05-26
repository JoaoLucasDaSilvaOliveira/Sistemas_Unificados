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
    };
    public abstract String getRegex();

}
