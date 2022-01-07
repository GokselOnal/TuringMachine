import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class WriteFile {
    public ArrayList<String[]> results;
    public String accept;
    public String reject;
    public WriteFile(ArrayList<String[]> results, String accept, String reject){
        this.results = results;
        this.accept = accept;
        this.reject = reject;

        try {
            String text = "";
            for(String[] str_arr: results) {
                for(String str: str_arr){
                    text += str;
                    text += " ";
                }
                text += "(route taken)";
                if(str_arr[str_arr.length - 1].equals(this.accept))
                    text += "\nAccepted\n";
                else if(str_arr[str_arr.length - 1].equals(this.reject))
                    text += "\nRejected\n";

                Path fileName = Path.of("C:\\Users\\MSI\\Desktop\\CS410\\Project3\\TuringMachine/output_file.txt");
                Files.writeString(fileName, text);
                String file_content = Files.readString(fileName);
            }
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}