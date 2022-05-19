
import java.util.ArrayList;
import java.io.*;
public class Output {
	private ArrayList<String> arraylist=new ArrayList<String>();
	public void add(String a) {
		arraylist.add(a);
	}
    public void writing(){
	    
    File file = new File("Output.txt");
    try(BufferedWriter a = new BufferedWriter(new FileWriter(file))){
    	for(int i = 0 ; i<arraylist.size() ; i++) {
    		a.write(arraylist.get(i));
    		a.newLine();
    		}
    	a.close();
  
    } catch (IOException e) {
        System.out.println("Unable to read file " +file.toString());
        
    }

}
}
