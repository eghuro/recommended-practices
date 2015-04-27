import Elements.Argument;
import Elements.Option;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandLine {
    private final Map<String, Option> OPTIONS_BY_NAME;
    private final HashSet<Option> OPTIONS;
                
    public CommandLine() {
        this.OPTIONS_BY_NAME = new HashMap<>();
        this.OPTIONS = new HashSet<>();
    }
	
    /**
     * Zaregistrovanie vytvorenej vlastnosti.
     *
     * @param  option	option vytvorena vlasnost
     * @see Option
     */	
    public void registerOption(Option option) {
        option.getNames().stream().forEach((name) -> {
            OPTIONS_BY_NAME.put(name, option);
        });
        OPTIONS.add(option);
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
        options.addAll(OPTIONS_BY_NAME.values());
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
    
    /**
     * Parsuje volby a argumenty prikazoveho riadka.
     * 
     * @param args volby z prikazoveho riadka
     * @return vrati parsovany prikazovy riadok
     * @see ParsedCommandLine
     */
    public ParsedCommandLine parse(String[] args) {
        ParsedCommandLine result = new ParsedCommandLine();
        processArguments(args, result);
        
        checkRequiredOptionsPresent(result);
        fillMissingOptionsWithDefaultValues(result);
        
        return result;
    }

    private enum State { 
        OPTION, ARGUMENT
    }
    
    private void checkRequiredOptionsPresent(ParsedCommandLine result) {
        OPTIONS.stream().filter((option) -> (option.isRequired())).filter((option) -> (!result.hasOption(option.toString()))).forEach((Option option) -> {
            requiredOptionNotPresentError(result, option);
        });
    }

    private void fillMissingOptionsWithDefaultValues(ParsedCommandLine result) {
        OPTIONS.stream().filter((option) -> (!result.hasOption(option.toString()))).forEach((option) -> {
            result.setOption(option, option.getArgument().getDefaultValue());
        });
    }

    private void processArguments(String[] args, ParsedCommandLine result) {
        State state = State.OPTION;
        for(int argumentIndex = 0; argumentIndex < args.length;) {
            String argument = args[argumentIndex];
                    
            if (state.equals(State.ARGUMENT)) {
                processCommonArgument(argument, result);
            } else {
                final String shortOptionRegex = "-[a-zA-Z0-9]{1}";
                if (argument.matches(shortOptionRegex)) {
                    argumentIndex = processShortOption(argument, result, args, argumentIndex);
                } else if (argument.equals("--")) {
                    state = State.ARGUMENT;
                } else if (!argument.startsWith("-")) {
                    processCommonArgument(argument, result);
                    state = State.ARGUMENT;
                } else {
                    parseLongOption(argument, result);
                }
            }
            argumentIndex++;
        }
    }
        
    private void processCommonArgument(String argument, ParsedCommandLine result) {
        result.setCommonArgument(argument);
    }
        
    private void parseLongOption(String argument, ParsedCommandLine result) {
        final String longOptionRegex = "--([a-zA-Z0-9]+)(=([a-zA-Z0-9]+))?";
        Pattern longArgument = Pattern.compile(longOptionRegex);
        Matcher matcher = longArgument.matcher(argument);
        
        boolean found = false;
        while(matcher.find()) {
            String optionName = matcher.group(1);
            String optionValue = matcher.group(3);
            
            processLongOption(optionName, optionValue, result);
            found = true;
        }
        if(!found) {
            unknownParameterError(result, argument);
        }
    }
        
    private void processLongOption(String optionName, String optionValue, ParsedCommandLine result) {
        Option option = OPTIONS_BY_NAME.get(optionName);
        if (option != null) {
            Argument optionArgument = option.getArgument();
            if (optionValue == null) {
                result.setOption(option, optionArgument.getDefaultValue());
            } else if (optionArgument.accept(optionValue)) {
                result.setOption(option, optionValue);
            } else {
                typeMismatchError(result,optionName,optionValue);
            }
        } else {
            unknownParameterError(result, optionName);
        }
    }
        
    private int processShortOption(String argument, ParsedCommandLine result, String[] args, int argumentIndex) {
       String optionName = argument.substring(1);
        Option option = OPTIONS_BY_NAME.get(optionName);
        if (option != null) {
            Argument optionArgument = option.getArgument();
            if (optionArgument != null) {
                argumentIndex++;
                String parameter = args[argumentIndex];
                if (optionArgument.accept(parameter)) {
                    result.setOption(option, parameter);
                } else {
                    typeMismatchError(result, optionName, parameter);
                }
            } else {
                result.setOption(option, "");
            }
        } else {
            unknownParameterError(result, optionName);
        }
        return argumentIndex;
    }
    
    private void typeMismatchError(ParsedCommandLine result, String optionName, String optionValue) {
        result.setError("Parameter type mismatch for "+optionName+" ("+optionValue+")");
    }
    
    private void unknownParameterError(ParsedCommandLine result, String optionName) {
        result.setError("Unknown parameter: "+optionName);
    }
    
    private void requiredOptionNotPresentError(ParsedCommandLine result, Option option) {
        result.setError("Required option not present: "+option.toString() + ((option.getDesription().length() > 0) ?" ("+option.getDesription()+")":null));
    }
}
