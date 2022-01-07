import java.util.ArrayList;
import java.util.Arrays;

public class StringParser {
    private int len_var_alphabet;
    private int len_tape_alphabet;
    private int len_states;
    public String states;
    public String start_state;
    public String accept_state;
    public String reject_state;
    public String blank_symbol;;
    public String tape_alphabet;
    public String input_alphabet;
    public String transition;
    public String inputs;

    public StringParser(String text){
        String[] tokens = text.split(";");

        for (int i = 0; i < 3; i++)
            if (i == 0)
                this.len_var_alphabet = Integer.parseInt(tokens[i]);
            else if (i == 1)
                this.len_tape_alphabet = Integer.parseInt(tokens[i]);
            else if (i == 2)
                this.len_states = Integer.parseInt(tokens[i]);


        for (int i = 3; i < tokens.length; i++) {
            if (i == 3)
                this.states = tokens[i];
            else if (i == 4)
                this.start_state = tokens[i];
            else if (i == 5)
                this.accept_state = tokens[i];
            else if (i == 6)
                this.reject_state = tokens[i];
            else if (i == 7)
                this.blank_symbol = tokens[i];
            else if (i == 8)
                this.tape_alphabet = tokens[i];
            else if (i == 9)
                this.input_alphabet = tokens[i];
        }


        this.transition = "";
        this.inputs = "";
        String[] arr_alph = this.input_alphabet.split(" ");
        ArrayList<String> list_alph = new ArrayList<>(Arrays.asList(arr_alph));
        for (int i = 10; i < tokens.length; i++) {
            if (tokens[i] != "" && !list_alph.contains(Character.toString(tokens[i].charAt(0)))) {
                this.transition += tokens[i] + "#";
            } else {
                this.inputs += tokens[i] + "#";
            }
        }
    }

    public ArrayList<String[]> getStrings(){
        ArrayList<String[]> list_strings = new ArrayList<String[]>();
        list_strings.add(this.states.split(" "));
        list_strings.add(this.tape_alphabet.split(" "));
        list_strings.add(this.input_alphabet.split(" "));
        list_strings.add(this.start_state.split(" "));
        list_strings.add(this.accept_state.split(" "));
        list_strings.add(this.reject_state.split(" "));
        list_strings.add(this.blank_symbol.split(" "));
        list_strings.add(this.transition.split("#"));
        list_strings.add(this.inputs.split("#"));
        return list_strings;
    }
}
