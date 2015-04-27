import Elements.Argument;
import Elements.Option;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        StringBuilder usage = new StringBuilder();
        
        usage.append("Usage:\n");
        
        Set<Option> options = new HashSet<>();
        options.addAll(OPTIONS.values());
        options.stream().map((option) -> {
            if(!option.isRequired()) {
                usage.append("[");
            }
            return option;
        }).map((option) -> {
            option.getNames().stream().forEach((optionString) -> {
                usage.append("-").append(optionString).append(" | ");
            });
            return option;
        }).map((option) -> {
            usage.delete(usage.length()-3, usage.length());
            if(!option.isRequired()) {
                usage.append("]");
            }
            return option;
        }).forEach((option) -> {
            usage.append("\t").append(option.getDesription()).append("\n");
        });
        return usage.toString();
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
        
        HashSet<Option> options = new HashSet<>();
        options.addAll(OPTIONS.values());
        options.stream().filter((option) -> (option.isRequired())).filter((option) -> (!result.hasOption(option.toString()))).forEach((Option option) -> {
            result.setError("Required option not present: "+option.toString() + ((option.getDesription().length() > 0) ?" ("+option.getDesription()+")":null));
        });
        options.stream().filter((option) -> (!result.hasOption(option.toString()))).forEach((option) -> {
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
