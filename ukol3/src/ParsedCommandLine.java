import elements.Option;

import java.util.HashMap;
import java.util.LinkedList;

public class ParsedCommandLine {
    private final HashMap<Option, String> OPTIONS;
    private final LinkedList<String> COMMONS;
    private final StringBuilder ERRORS;

    public ParsedCommandLine() {
        this.OPTIONS = new HashMap<>();
        this.COMMONS = new LinkedList<>();
        this.ERRORS = new StringBuilder();
    }

    /**
    * Zisti, ze ci bolo v prikazovom riadku dana vlastnost.
    *
    * @param optionName nazov vlastnosti
    * @return info, ze ci sa nachadza vlasnost v prikazovom riadku
    */
    public boolean hasOption(String optionName) {
        return  (OPTIONS.keySet().stream()
                .anyMatch((option) -> (option.getNames().contains(optionName))));
    }

    /**
    * Vrati hodnotu argumentu pre vlastnost.
    *
    * @param optionName nazov vlastnosti
    * @return hodnota argumentu pre danu vlastnost z prikazoveho riadku
    */
    public String getOptionValue(String optionName) {
        for (Option option : OPTIONS.keySet()) {
            if(option.getNames().contains(optionName)) {
                return OPTIONS.get(option);
            }
        }
        return null;
    }

    /**
    * Vrati uspech, ked sa podarilo v poriadku rozparsovat prikazovy riadok.
    *
    * @return  uspech parsovania
    */
    public boolean success() {
        return ERRORS.length()==0;
    }

    /**
    * Vrati zoznam chyb, ked sa nepodarilo v poriadku
    * rozparsovat prikazovy riadok.
    *
    * @return  zoznam chyb
    */
    public String getErrors() {
        return ERRORS.toString();
    }

    /**
    * Vrati bezne argumenty uvedene na konci prikazoveho riadka,
    * ktore neboli priradene k ziadnej vlastnosti.
    *
    * @return  zoznam argumentov
    */
    public String[] getCommonArguments() {
        return COMMONS.toArray(new String[0]);
    }

    public void setCommonArgument(String argument) {
        COMMONS.addLast(argument);
    }

    public void setOption(Option option, String value) {
        OPTIONS.put(option, value);
    }

    public void setError(String error) {
        ERRORS.append(error).append("\n");
    }
}
