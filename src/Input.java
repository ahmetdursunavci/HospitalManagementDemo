
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Input implements IInputfiles{
	private String filename ;
	public Input(String a) {
		this.filename=a;
	}

	public ArrayList<String> readFile() {
		ArrayList<String> data = new ArrayList<String>();
		File file = new File(filename);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				data.add(scan.nextLine());
				
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
		
		
	}

}
