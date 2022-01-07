import java.util.HashMap;

public class Transitions {
    public HashMap<String[], String[]> table;

    public Transitions(String[] transitions){
        this.table = new HashMap();
        addItem(transitions);
    }


    public void addItem(String[] transitions){
        for(String rules : transitions){
            String[] item = rules.split(" ");
            String[] Key = { item[0], item[1]};
            String[] Value = {item[2], item[3], item[4]};
            this.table.put(Key,Value);
        }
    }

    public HashMap<String[], String[]> getTable(){
        return this.table;
    }
}
