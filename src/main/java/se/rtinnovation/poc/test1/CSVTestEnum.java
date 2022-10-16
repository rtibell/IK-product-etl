package se.rtinnovation.poc.test1;

public enum CSVTestEnum {
    HANDLE("Handle"),
    TITLE("Title"),
    BODY("Body (HTML)");

    public final String label;

    private CSVTestEnum(String label) {
        this.label = label;
    }
}
