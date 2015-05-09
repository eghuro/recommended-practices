package elements;

import java.util.HashSet;
import java.util.Set;

import visitors.Visitor;

public class Option implements Visitable {
	
	/** Option short prefix **/
	public static final String SHORT_PREFIX = "-";
	
	/** Option long prefix **/
	public static final String LONG_PREFIX = "--";	
	
	/** Option name **/
	private String name = "";
	
	/** Option synonyms **/
    private Set<String> synonyms = new HashSet<>();
    
    /** Option description **/
    private String description = "";
    
    /** Is option required? **/
    private boolean required = false;
    
    /** Is option long? **/
    private boolean longOption = false;
    
    /** Option argument object **/
    private Argument argument = null;

    /**
     * Constructor for Option
     * @param name option name
     * @throws IllegalArgumentException
     */
    public Option(String name) throws IllegalArgumentException {
    	setName(name);
    }
    
    /**
     * Constructor for Option
     * @param name option name
     * @param description option description
     * @throws IllegalArgumentException
     */
    public Option(String name, String description) throws IllegalArgumentException {
    	setName(name);
    	setDescription(description);
    }
    
    /**
     * Constructor for Option
     * @param name option name
     * @param description option description
     * @param required is option required
     * @throws IllegalArgumentException
     */
    public Option(String name, String description, boolean required) throws IllegalArgumentException {
    	setName(name);
    	setDescription(description);
    	setRequired(required);
    }
    
    /**
     * Constructor for Option
     * @param name option name
     * @param description option description
     * @param required is option required
     * @param argument option Argument(object)
     * @throws IllegalArgumentException
     */
    public Option(String name, String description, boolean required, Argument argument) throws IllegalArgumentException {
    	setName(name);
    	setDescription(description);
    	setRequired(required);
    	setArgument(argument);
    }
    
    /**
     * Set option name
     * @param name
     * @throws IllegalArgumentException
     */
    private void setName(String name) throws IllegalArgumentException {
    	nameValidation(name);    	
    	this.name = name;
    }
    
    /**
     * Get option name
     * @return option name
     */
    public String getName() {
    	return this.name;
    }
    
    /**
     * Get option name with prefix
     * @return option name with prefix
     */
    public String getNameWithPrefix() {
    	if (isLong()) {    		
    		return LONG_PREFIX + this.name;
    	}
    	else {
    		return SHORT_PREFIX + this.name;
    	}    	
    }
    
    /**
     * Set option name synonym
     * @param name option name
     * @throws IllegalArgumentException
     */
    public void addSynonym(String name) throws IllegalArgumentException {
    	nameValidation(name);
    	synonyms.add(name);
    }

    /**
     * Set option name synonyms
     * @param nameSynonyms set of name synonyms
     * @throws IllegalArgumentException
     */
    public void addSynonyms(Set<String> nameSynonyms) throws IllegalArgumentException {
    	for (String otherName: nameSynonyms) {
    		addSynonym(otherName);
    	}
    }
    
    /**
     * Validate option name
     * @param name option name
     * @throws IllegalArgumentException
     */
    private void nameValidation(String name) throws IllegalArgumentException {
    	if (name == null || name.length() == 0) {
    		throw new IllegalArgumentException("Option name can't be empty.");
    	}
    	
    	if (!name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")) {
    		throw new IllegalArgumentException("Invalid option name - accepts letters, numbers, spaces and underscores.");
    	}    	
    }
    
    /**
     * Get option names
     * @return set of names for this option
     */
    public Set<String> getNames() {
        return this.synonyms;
    }
    
    /**
     * Get option names with prefix
     * @return set of names for this option with prefix
     */
    public Set<String> getNamesWithPrefix() {
    	Set<String> namesWithPrefix = new HashSet<>();    	
    	
    	for (String argumentSynonym: this.synonyms) {
    		String synonymWithPrefix = SHORT_PREFIX + argumentSynonym;
        	
    		if (this.isLong()) {    		
        		synonymWithPrefix = LONG_PREFIX + argumentSynonym;
        	}
    		
    		namesWithPrefix.add(synonymWithPrefix);
    	}
    	
        return namesWithPrefix;
    }
    
    /**
     * Set option description
     * @param description
     */
    public void setDescription(String description) {
    	this.description = description;
    }    

    /**
     * Get description of the option
     * @return description of the option
     */
    public String getDesription() {
        return this.description;
    }
    
    /**
     * Option will be required
     */
    public void setRequired() {
    	setRequired(true);
    }
    
    /**
     * Set required option
     * @param required will be option required?
     */
    public void setRequired(boolean required) {
    	this.required = required;
    }    
    
    /**
     * Whether is option required
     * @return is option required?
     */
    public boolean isRequired() {
        return this.required;
    }
    
    /**
     * Option will be long
     */
    public void setLong() {
    	setLong(true);
    }
    
    /**
     * Set long option
     * @param longOption will be option long?
     */
    public void setLong(boolean longOption) {
    	this.longOption = longOption;
    } 
    
    /**
     * Whether is option long
     * @return is this option long?
     */
    public boolean isLong() {
        return this.longOption;
    }
    
    /**
     * Set option Argument(object)
     * @param argument option argument
     * @throws IllegalArgumentException
     */
    public void setArgument(Argument argument) throws IllegalArgumentException {
    	if (argument == null) {
    		throw new IllegalArgumentException("Option argument can't be null object.");
    	}
    	this.argument = argument;
    }

    /**
     * Get Argument(object) for this option
     * @return Argument for this option
     */
    public Argument getArgument() {
        return this.argument;
    }
    
    /**
     * Whether has option argument
     * @return has this option argument?
     */
    public boolean hasArgument() {
    	return (this.argument != null);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
     }

}