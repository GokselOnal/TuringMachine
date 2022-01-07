
public class main {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        String text = rf.getText();
        StringParser sp = new StringParser(text);
        TM turing = new TM(sp);
        Results result = new Results(turing, turing.inputs);
        WriteFile wf = new WriteFile(result.get_results(), result.accept, result.reject);
    }
}
