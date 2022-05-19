
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class AdmissionDAO implements IDAO{
	private String filename = "admission.txt";
	private ArrayList<String> admissionarr= this.read();
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
			e.printStackTrace();
		}
		return data;
	}
	public int add(String b,int a,String c) {
		if(c.equals("CreateAdmission")) {
			boolean control = true;
			for(int i = 0 ; i < admissionarr.size() ; i++) {
				String[] array = admissionarr.get(i).split("\t");
				if(array[0].length()<3) {
					if(a<Integer.valueOf(array[0])) {
						admissionarr.add(i,b);
						control = false;
					}
				}
			}
			if(control) {
				admissionarr.add(b);
			}
		}
		else {
			for(int i = 0 ; i < admissionarr.size() ; i++) {
				String[] array = admissionarr.get(i).split("\t");
				if(array[0].length()<5) {
				if(Integer.valueOf(array[0])==a) {
					for(int j = i+1 ; j<admissionarr.size() ; j++) {
						String[] array2= admissionarr.get(j).split("\t");
						if(array2[0].length()>5) {
							continue;
						}
						else {
							admissionarr.add(j,b);
							return 0;
						}
					}
						admissionarr.add(b);
						return 0;
					
				}
			}
		}
	}
		return 0;
	}
	public void remove(ArrayList<String> arr1,Output c) {
		for(int i = 0 ; i < arr1.size() ; i++) {
			String[] a = arr1.get(i).split(" ");
			if (a[0].equals("RemovePatient")) {
				for(int j = 0 ; j < admissionarr.size() ; j++) {
					String[] b = admissionarr.get(j).split("\t");
					if(b[1].equals(a[1])) {
						admissionarr.remove(admissionarr.get(j));
						for(int k = j ; k<admissionarr.size();) {
							String[] d = admissionarr.get(k).split("\t");
							if(d[0].length()>1) {
								admissionarr.remove(k);
							}
							else {
								break;
							}
						}
					}
				}
				}
		}
	}
	public ArrayList<String> getArray() {
		return admissionarr;
	}
	public ArrayList<String> ListPatient() {
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0 ; i < admissionarr.size() ; i++ ) {
			String[] a = admissionarr.get(i).split(" ");
			if(array.size()==0) {
				array.add(admissionarr.get(i));
			}
			else {
				array=SortMethod(array,a[1]);
			}
			
		}
		return array;
	}
	private ArrayList<String> SortMethod(ArrayList<String> a,String b) {
		for(int i = 0 ; i < a.size() ; i++) {
			String[] c = a.get(i).split(" ");
			if(b.charAt(arrayforsort[0])<c[1].charAt(arrayforsort[0])) {
				a.add(i,b);
				return a;
			}
			else if(b.charAt(0)==c[1].charAt(0)) {
				if(b.charAt(1)<c[1].charAt(1)) {
					a.add(i,b);
					return a;
				}
			}
		}
		a.add(a.size(),b);
		return a;
		
	}
    public void writing(){
	    
    File file = new File("admission.txt");
    try(BufferedWriter a = new BufferedWriter(new FileWriter(file))){
    	for(int i = 0 ; i<this.admissionarr.size() ; i++) {
    		a.write(admissionarr.get(i));
    		a.newLine();
    		}
    	a.close();
  
    } catch (IOException e) {
        System.out.println("Unable to read file " +file.toString());
        
    }

}
}
