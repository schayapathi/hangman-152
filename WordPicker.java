import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordPicker{

    public static String chooseWord(){
        BufferedReader reader;
        int count = 0;
        int chosenLine = chooseLine(); 

        try{
            reader = new BufferedReader(new FileReader("words.txt"));
            String line = reader.readLine();
            while (line != null && count + 1 < chosenLine) {
                line = reader.readLine();
                count++;
            }
            while(line.length() <= 4){
                line = reader.readLine();
            }
            reader.close();
            return line;
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private static int chooseLine(){
        Random rand = new Random();
        return rand.nextInt(9885);
    }
}