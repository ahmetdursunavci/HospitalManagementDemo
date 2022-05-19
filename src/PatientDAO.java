
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class PatientDAO implements IDAO{
	private String filename = "patient.txt";
	private ArrayList<String> patientarr= this.read();
	private int[] arrayforsort= {0};
	public ArrayList<String> read() {
		ArrayList<String> data = new ArrayList<String>();
		File file = new File(filename);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String d = scan.nextLine();
				data.add(d);
				
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public void remove(ArrayList<String> array,Output a) {
		for (int i = 0 ; i < array.size() ; i++) {
			String[] array2 = array.get(i).split(" ");
			if(array2[0].equals("RemovePatient")) {
				for(int j = 0 ; j < patientarr.size() ; j++) {
					String[] array3 = patientarr.get(j).split("\t");
					if(array2[1].equals(array3[0])) {
						String[] names = array3[1].split(" ");
						a.add("Patient"+" "+array3[0]+" "+names[0]+" "+"Removed");
						patientarr.remove(patientarr.get(j));
					}
				}
			}
		}
	}
	public int add(String a,int b,String c) {
		boolean control = true;
		for(int j = 0 ; j < patientarr.size() ; j++) {
			String[] array3 = patientarr.get(j).split("\t");
			if(b<Integer.valueOf(array3[0])) {
				patientarr.add(j,a);
				control=false;
				break;
		}	
	}
		if(control) {
			patientarr.add(a);
			}
		return 0;
	}
	

	public ArrayList<String> getArray() {
		return patientarr;
	}
	public ArrayList<String> ListPatient() {
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0 ; i < patientarr.size() ; i++ ) {
			String[] a = patientarr.get(i).split("\t");
			if(array.size()==0) {
				array.add(patientarr.get(i));
			}
			else {
				boolean control = true;
				for (int j = 0 ; j < array.size(); j++) {
					String[] b = array.get(j).split("\t");
					if(SortMethod(b[1],a[1])) {
						array.add(j,patientarr.get(i));
						control = false;
						break;
						
					}
				}
				if(control) {
					array.add(patientarr.get(i));
				}
			}
			
		}
		return array;
	}
	private boolean SortMethod(String a,String b) {
			if(b.charAt(arrayforsort[0])<a.charAt(arrayforsort[0])) {
				arrayforsort[0]=0;
				return true;
			}
			else if(b.charAt(arrayforsort[0])==a.charAt(arrayforsort[0])) {
				arrayforsort[0]=arrayforsort[0]+1;
				return SortMethod(a,b);
			}
	arrayforsort[0]=0;
	return false;

		
	}
    public void writing(){
	    
    File file = new File("patient.txt");
    try(BufferedWriter a = new BufferedWriter(new FileWriter(file))){
    	for(int i = 0 ; i<this.patientarr.size() ; i++) {
    		a.write(patientarr.get(i));
    		a.newLine();
    		}
    	a.close();
  
    } catch (IOException e) {
        System.out.println("Unable to read file " +file.toString());
        
    }

}
}
