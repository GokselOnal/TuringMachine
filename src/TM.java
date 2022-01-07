import java.util.ArrayList;
import java.util.HashMap;

public class TM {
    public String[] states;
    public String[] start_state;
    public String[] accept_state;
    public String[] reject_state;
    public String[] blank_symbol;
    public String[] tape_alph;
    public String[] input_alph;
    public String[] transitions;
    public Transitions transition_table;
    public String[] inputs;
    private StringParser parser;
    private ArrayList<String[]> parsed_text;

    public TM(StringParser sp){
        this.parser = sp;
        this.parsed_text =  this.parser.getStrings();
        this.states = parsed_text.get(0);
        this.tape_alph = parsed_text.get(1);
        this.input_alph = parsed_text.get(2);
        this.start_state = parsed_text.get(3);
        this.accept_state = parsed_text.get(4);
        this.reject_state = parsed_text.get(5);
        this.blank_symbol = parsed_text.get(6);
        this.transitions = parsed_text.get(7);
        this.inputs = parsed_text.get(8);
        this.transition_table = new Transitions(transitions);
        info();
    }

    private void makeTransition(){
        ArrayList<String[]> v = new ArrayList<String[]>();
        HashMap<String[], String[]> table = this.transition_table.getTable();

        for (String[] k : table.keySet()) {
            v.add(k);
        }

        //info
        System.out.println("\u03b4(Transition Table):");
        System.out.println("'state' 'variable' ['variable for pop', 'variable for push'] -> 'target state' (representation)");
        for(int i = 0; i < v.size(); i++){
            System.out.println("\t" + v.get(i)[0] + " " + v.get(i)[1] + " [" + table.get(v.get(i))[0] + ", " + table.get(v.get(i))[1] + "]  -> " + table.get(v.get(i))[2]);
        }
    }

    private void info(){
        System.out.println();
        System.out.println("~TURING MACHINE~");
        System.out.println();


        System.out.println("(Q,\u0393,\u2211,\u03b4,q0,F) Formal Definition: ");
        System.out.print("Q(States): ");
        for(String state : states)
            System.out.print(state + " ");
        System.out.println();


        System.out.print("\u2211(input alphabet): ");
        for(String symbol : input_alph)
            System.out.print(symbol + " ");
        System.out.println();


        System.out.print("\u0393(Tape alphabet): ");
        for(String symbol : tape_alph)
            System.out.print(symbol + " ");
        System.out.println();

        makeTransition();


        System.out.print("q_start(Start state)  : ");
        for(String start : start_state)
            System.out.print(start + " ");
        System.out.println();


        System.out.print("q_accept(Accept state): ");
        for(String acc : accept_state)
            System.out.print(acc + " ");
        System.out.println();

        System.out.print("q_reject(Reject state): ");
        for(String rej : reject_state)
            System.out.print(rej + " ");
        System.out.println();
    }

    public HashMap<String[], String[]> getTransition(){
        return this.transition_table.getTable();
    }
}
