import Elements.Argument;
import Elements.Option;
import java.util.Arrays;
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
        for (String name : option.getNames()) {
            OPTIONS.put(name, option);
            System.out.println("Option: "+name);
        }
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
        System.out.println(Arrays.toString(args));
                
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
        return result;
    }
        
    private void processCommonArgument(String argument, ParsedCommandLine result) {
        System.out.println("Common argument: "+argument);
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

        System.out.println("Option name: "+optionName);
        System.out.println("Option value: "+optionValue);

        Option option = OPTIONS.get(optionName);
        if (option != null) {
            System.out.println("Long option: "+optionName);
            Argument optionArgument = option.getArgument();
            if (optionValue == null) {
                result.setOption(optionName, optionArgument.getDefaultValue());
            } else if (optionArgument.accept(optionValue)) {
                result.setOption(optionName, optionValue);
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
            System.out.println("Option : "+optionName);
            Argument optionArgument = option.getArgument();
            if (optionArgument != null) {
                argumentIndex++;
                String parameter = args[argumentIndex];
                System.out.println("Paramater: "+optionName+" : "+parameter);
                if (optionArgument.accept(parameter)) {
                    result.setOption(optionName, parameter);
                } else {
                    System.out.println("ERROR1: "+parameter);
                }
            }
        } else {
            System.out.println("ERROR0: "+ optionName);
        }
        return argumentIndex;
    }
}
