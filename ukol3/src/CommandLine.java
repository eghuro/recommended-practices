import Elements.Argument;
import Elements.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandLine {
    private final Map<String, Option> OPTIONS;
                
    public CommandLine() {
        this.OPTIONS = new HashMap<>();
    }
	
    /**
     * Zaregistrovanie vytvorenej vlastnosti.
     *
     * @param  option	option vytvorena vlasnost
     * @see Option
     */	
    public void registerOption(Option option) {
        option.getNames().stream().forEach((name) -> {
            OPTIONS.put(name, option);
        });
    }
	
    /**
     * Vrati popis pouzitia (Helper) vlastnosti a argumentov.
     *
     * @return text pouzitia
     */	
    public String getUsage() {
        return null;
    }
        
    private enum State { 
        OPTION, ARGUMENT
    }
	
    /**
     * Parsuje volby a argumenty prikazoveho riadka.
     * 
     * @param args volby z prikazoveho riadka
     * @return vrati parsovany prikazovy riadok
     * @see ParsedCommandLine
     */
    public ParsedCommandLine parse(String[] args) {
        ParsedCommandLine result = new ParsedCommandLine();
        State state = State.OPTION;            
        for(int argumentIndex = 0; argumentIndex < args.length;) {
            String argument = args[argumentIndex];
                    
            if (state.equals(State.ARGUMENT)) {
                processCommonArgument(argument, result);
            } else {
                if (argument.matches("-[a-zA-Z0-9]{1}")) {
                    argumentIndex = processShortOption(argument, result, args, argumentIndex);
                } else if (argument.equals("--")) {
                    state = State.ARGUMENT;
                } else if (!argument.startsWith("-")) {
                    processCommonArgument(argument, result);
                    state = State.ARGUMENT;
                } else {
                    processLongOption(argument, result);
                }
            }
            argumentIndex++;
        }
        OPTIONS.keySet().stream().filter((optionName) -> (!result.hasOption(optionName))).map((optionName) -> OPTIONS.get(optionName)).forEach((option) -> {
            result.setOption(option, option.getArgument().getDefaultValue());
        });
        return result;
    }
        
    private void processCommonArgument(String argument, ParsedCommandLine result) {
        result.setCommonArgument(argument);
    }
        
    private void processLongOption(String argument, ParsedCommandLine result) {
        String regex = "--([a-zA-Z0-9]+)(=([a-zA-Z0-9]+))?";
        Pattern longArgument = Pattern.compile(regex);
        Matcher matcher = longArgument.matcher(argument);
        
        boolean found = false;
        while(matcher.find()) {
            parseLongOption(matcher, result);
            found = true;
        }
        if(!found) {
            System.out.println("ERROR");
        }
    }
        
    private void parseLongOption(Matcher matcher, ParsedCommandLine result) {
        String optionName = matcher.group(1);
        String optionValue = matcher.group(3);

        Option option = OPTIONS.get(optionName);
        if (option != null) {
            Argument optionArgument = option.getArgument();
            if (optionValue == null) {
                result.setOption(option, optionArgument.getDefaultValue());
            } else if (optionArgument.accept(optionValue)) {
                result.setOption(option, optionValue);
            } else {
                System.out.println("ERROR");
            }
        } else {
            System.out.println("ERROR");
        }
    }
        
    private int processShortOption(String argument, ParsedCommandLine result, String[] args, int argumentIndex) {
       String optionName = argument.substring(1);
        Option option = OPTIONS.get(optionName);
        if (option != null) {
            Argument optionArgument = option.getArgument();
            if (optionArgument != null) {
                argumentIndex++;
                String parameter = args[argumentIndex];
                if (optionArgument.accept(parameter)) {
                    result.setOption(option, parameter);
                } else {
                    System.out.println("ERROR1: "+parameter);
                }
            } else {
                result.setOption(option, "");
            }
        } else {
            System.out.println("ERROR0: "+ optionName);
        }
        return argumentIndex;
    }
}
