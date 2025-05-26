package Model;

public enum ValidsFormats {
    CNPJ {
        @Override
        public String getRegex() {
            return "XX.XXX.XXX/0001-XX";
        }
    };
    public abstract String getRegex();

}
