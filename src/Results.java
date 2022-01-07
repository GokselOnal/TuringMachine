import java.util.*;

public class Results {

    public TM automata;
    public HashMap<String[], String[]> t_table;
    public ArrayList<String[]> input_results;
    public String blank_symbol;
    public String accept;
    public String reject;
    public String start;


    public Results(TM tm, String[] inputs){
        this.t_table =  tm.transition_table.getTable();
        this.automata = tm;
        this.input_results = new ArrayList<>();
        String current_state = this.automata.start_state[0];
        this.start = this.automata.start_state[0];
        this.accept = this.automata.accept_state[0];
        this.reject = this.automata.reject_state[0];
        this.blank_symbol = this.automata.blank_symbol[0];

        //info for inputs
        given_inputs();
        System.out.println();

        System.out.println("~~~~~~~~~~~cs410~~~~~~~~~~~");


        for(int i = 0; i < inputs.length; i++) {
            int head_idx = 1;
            ArrayList<String> evaluate = new ArrayList<String>();
            evaluate.add(current_state);


            for(int j = 0; j < inputs[i].length(); j++){
                evaluate.add(Character.toString(inputs[i].charAt(j)));
            }
            evaluate.add(blank_symbol);

            String new_string = "";
            String direction = "";
            String next_state = "";
            ArrayList<String> route = new ArrayList<>();
            route.add(this.start); // start state is added
            boolean is_reject = false;


            long startTime = System.nanoTime();
            while(!next_state.equals(this.accept) && !is_reject){
                for(String[] k : this.t_table.keySet()) {
                    if(k[0].equals(evaluate.get(head_idx - 1)) && k[1].equals(evaluate.get(head_idx))) {
                        new_string = this.t_table.get(k)[0];
                        direction = this.t_table.get(k)[1];
                        next_state = this.t_table.get(k)[2];
                        evaluate.set(head_idx, new_string);
                        route.add(next_state);
                        if(next_state.equals(this.reject)){
                            is_reject = true;
                            break;
                        }
                        if(direction.equals("R")){
                            replace_arr(evaluate, head_idx - 1, head_idx);
                            evaluate.set(head_idx, next_state);
                            head_idx++;
                            break;
                        }else{
                            if(head_idx != 1) {
                                replace_arr(evaluate, head_idx - 1, head_idx - 2);
                                evaluate.set(head_idx - 2, next_state);
                                head_idx--;
                                break;
                            }
                            else if(head_idx < 2){
                                //it can not go left
                                evaluate.set(head_idx, next_state); // only update state
                            }
                        }
                    }
                }
                // if it does not find a transition from given items
                if(route.size() == 1){
                    if(this.accept.equals(this.start) && inputs[i].length() == 0){
                        break;
                    }else{
                        route.add(this.reject);
                        break;
                    }
                }
                long endTime = System.nanoTime();
                double duration = (double)(endTime - startTime) /1000000;
                //if it does not reach any result either accept or reject within 2.5 second
                if(duration > 2500){
                    route.add(this.reject);
                    break;
                }
            }


            String result = "";
            System.out.println();
            for(String str : route){
                System.out.print(str + " ");
                result += str + " ";
            }
            String[] last_result = result.split(" ");
            input_results.add(last_result);

            System.out.println("(route taken)");
            if(route.get(route.size() -1).equals(accept)){
                System.out.println("Accepted");
            }
            else if(route.get(route.size() -1).equals(reject)){
                System.out.println("Rejected");
            }
        }
    }

    public void given_inputs(){
        System.out.println();
        System.out.print("Given inputs: ");
        System.out.print(this.automata.inputs[0]);
        for(int i = 1; i < this.automata.inputs.length; i++)
            System.out.print(", " + this.automata.inputs[i]);
        System.out.println();
    }

    public void replace_arr(ArrayList<String> arr, int indx1, int indx2){
        String temp = arr.get(indx1);
        arr.set(indx1, arr.get(indx2));
        arr.set(indx2, temp);
    }

    public ArrayList<String[]> get_results(){return this.input_results;}
}
