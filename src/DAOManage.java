
import java.util.ArrayList;
public class DAOManage {
	private String name;
	private String surname;
	private int id;
	private String adress;
	private String pnumber;
	private IDAO daoobject1;
	private IDAO daoobject2;
	private IInputfiles inputobject;
	private Output output=new Output();
	public DAOManage(IDAO daoobject1,IDAO daoobject2, IInputfiles inputobject) {
		this.daoobject1 = daoobject1;
		this.daoobject2 = daoobject2;
		this.inputobject = inputobject;
	}
	
	public void AddPatient() {
		ArrayList<String> inputarraylist = inputobject.readFile();
		for (int i = 0 ; i < inputarraylist.size() ; i++) {
			String[] inputarray = inputarraylist.get(i).split(" ");
			if(inputarray[0].equals("AddPatient")) {
				this.id=Integer.valueOf(inputarray[1]);
				this.name=inputarray[2];
				this.surname=inputarray[3];
				this.pnumber=inputarray[4];
				this.adress=inputarray[5]+" "+inputarray[6]+" "+inputarray[7]+" "+inputarray[8];
				String info =id+"\t"+name+" "+surname+"\t"+pnumber+"\t"+"Address: "+adress;
				daoobject1.add(info,id,"patiendao");
				output.add("Patient"+" "+inputarray[1]+" "+name+" "+"Added");
				
			}
		}
		
	}
	public void RemovePatient() {
		daoobject1.remove(inputobject.readFile(),output);
		daoobject2.remove(inputobject.readFile(),output);
	}
	public ArrayList<String> print() {
		return daoobject2.getArray();
	
	}
	public void CreateAdmission() {
		ArrayList<String> inputarraylist = inputobject.readFile();
		for(int i = 0 ; i < inputarraylist.size() ; i++) {
			String[] inputarray = inputarraylist.get(i).split(" ");
			if (inputarray[0].equals("CreateAdmission")) {
				output.add("Admission"+" "+inputarray[1]+" "+"created");
				int order = Integer.valueOf(inputarray[1]);
				String admission=inputarray[1]+"\t"+inputarray[2];
				daoobject2.add(admission,order,"CreateAdmission");
			}
		}
	}
	public void ExaminationAndOutput() {
		ArrayList<String> inputarraylist = inputobject.readFile();
		for(int i = 0; i < inputarraylist.size() ; i++) {
			String[] inputarray = inputarraylist.get(i).split(" ");
			if (inputarray[0].equals("AddExamination")) {
				String a = inputarray[2]+"\t";
				for(int j = 3 ; j<inputarray.length ; j++) {
					a+=inputarray[j]+" ";
				}
				daoobject2.add(a, Integer.valueOf(inputarray[1]), "AddExamination");
				output.add(inputarray[2]+" "+"examination added to admission "+inputarray[1]);
				}
			else if(inputarray[0].equals("TotalCost")) {
				output.add("TotalCost for admission "+inputarray[1]);
				int cost = 0;
				ArrayList<String> array = daoobject2.getArray();
				for(int j = 0 ; j<array.size() ; j++) {
					String[] array1 = array.get(j).split("\t");
					if(inputarray[1].equals(array1[0])) {
						for(int k = j+1 ; k<array.size() ; k++) {
							String[] array3 = array.get(k).split("\t");
							String[] array4 = array3[1].split(" ");
							if(array3[0].length()>5) {
							if(array3[0].equals("Inpatient")) {
								Examination a = new Inpatient();
								if(array4.length==3) {
									if(array4[0].equals("doctorvisit")) {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("tests")) {
												a.Addoperation(new DoctorVisit(new Imaging(new Test())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new DoctorVisit(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("tests")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new DoctorVisit(new Test(new Imaging())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new DoctorVisit(new Test(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new DoctorVisit(new Measurements(new Imaging())));
											}
											else if(array4[2].equals("tests")) {
												a.Addoperation(new DoctorVisit(new Measurements(new Test())));
											}
										}
									}
									else if(array4[0].equals("tests")) {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Test(new Imaging(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Test(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Test(new DoctorVisit(new Imaging())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Test(new DoctorVisit(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Test(new Measurements(new Imaging())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Test(new Measurements(new DoctorVisit())));
											}
										}
									}
									else if(array4[0].equals("imaging")) {
										if(array4[1].equals("tests")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Imaging(new Test(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Imaging(new Test(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("tests")) {
												a.Addoperation(new Imaging(new DoctorVisit(new Test())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Imaging(new DoctorVisit(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("tests")) {
												a.Addoperation(new Imaging(new Measurements(new Test())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Imaging(new Measurements(new DoctorVisit())));
											}
										}
									}
									else {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Measurements(new Imaging(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Measurements(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Measurements(new DoctorVisit(new Imaging())));
											}
											else if(array4[2].equals("tests")) {
												a.Addoperation(new Measurements(new DoctorVisit(new Test())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Measurements(new Test(new Imaging())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Measurements(new Test(new DoctorVisit())));
											}
										}
									}
									
								}
								else if(array4.length==2) {
									if(array4[0].equals("doctorvisit")) {
										if(array4[1].equals("imaging")) {
											a.Addoperation(new DoctorVisit(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("tests")) {
											a.Addoperation(new DoctorVisit(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new DoctorVisit(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										
									}
									else if(array4[0].equals("imaging")) {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Imaging(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("tests")) {
											a.Addoperation(new Imaging(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Imaging(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
									}
									else if(array4[0].equals("tests")) {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Test(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("imaging")) {
											a.Addoperation(new Test(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Test(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
									}
									else {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Measurements(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("imaging")) {
											a.Addoperation(new Measurements(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Measurements(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										
									}
								}
								else if(array4.length==1) {
									if(array4[0].equals("tests")) {
										a.Addoperation(new Test()); 
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
										
									}
									else if(array4[0].equals("doctorvisit")) {
										a.Addoperation(new DoctorVisit());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
									else if(array4[0].equals("imaging")) {
										a.Addoperation(new Imaging());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
									else {
										a.Addoperation(new Measurements());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
								}
								else {
									cost+=a.cost();
									String total = a.print()+" "+a.cost()+"$";
									output.add(total);
									
								}
							}
							else {
								Examination a = new Outpatient();
								if(array4.length==3) {
									if(array4[0].equals("doctorvisit")) {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("tests")) {
												a.Addoperation(new DoctorVisit(new Imaging(new Test())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new DoctorVisit(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("tests")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new DoctorVisit(new Test(new Imaging())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new DoctorVisit(new Test(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new DoctorVisit(new Measurements(new Imaging())));
											}
											else if(array4[2].equals("tests")) {
												a.Addoperation(new DoctorVisit(new Measurements(new Test())));
											}
										}
									}
									else if(array4[0].equals("tests")) {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Test(new Imaging(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Test(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Test(new DoctorVisit(new Imaging())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Test(new DoctorVisit(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Test(new Measurements(new Imaging())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Test(new Measurements(new DoctorVisit())));
											}
										}
									}
									else if(array4[0].equals("imaging")) {
										if(array4[1].equals("tests")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Imaging(new Test(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Imaging(new Test(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("tests")) {
												a.Addoperation(new Imaging(new DoctorVisit(new Test())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Imaging(new DoctorVisit(new Measurements())));
											}
										}
										else {
											if(array4[2].equals("tests")) {
												a.Addoperation(new Imaging(new Measurements(new Test())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Imaging(new Measurements(new DoctorVisit())));
											}
										}
									}
									else {
										if(array4[1].equals("imaging")) {
											if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Measurements(new Imaging(new DoctorVisit())));
											}
											else if(array4[2].equals("measurements")) {
												a.Addoperation(new Measurements(new Imaging(new Measurements())));
											}
										}
										else if(array4[1].equals("doctorvisit")) {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Measurements(new DoctorVisit(new Imaging())));
											}
											else if(array4[2].equals("tests")) {
												a.Addoperation(new Measurements(new DoctorVisit(new Test())));
											}
										}
										else {
											if(array4[2].equals("imaging")) {
												a.Addoperation(new Measurements(new Test(new Imaging())));
											}
											else if(array4[2].equals("doctorvisit")) {
												a.Addoperation(new Measurements(new Test(new DoctorVisit())));
											}
										}
									}
									
								}
								else if(array4.length==2) {
									if(array4[0].equals("doctorvisit")) {
										if(array4[1].equals("imaging")) {
											a.Addoperation(new DoctorVisit(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("tests")) {
											a.Addoperation(new DoctorVisit(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new DoctorVisit(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										
									}
									else if(array4[0].equals("imaging")) {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Imaging(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("tests")) {
											a.Addoperation(new Imaging(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Imaging(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
									}
									else if(array4[0].equals("tests")) {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Test(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("imaging")) {
											a.Addoperation(new Test(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Test(new Measurements()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
									}
									else {
										if(array4[1].equals("doctorvisit")) {
											a.Addoperation(new Measurements(new DoctorVisit()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else if(array4[1].equals("imaging")) {
											a.Addoperation(new Measurements(new Imaging()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										else {
											a.Addoperation(new Measurements(new Test()));
											cost+=a.cost();
											String total = a.print()+" "+a.cost()+"$";
											output.add(total);
										}
										
									}
								}
								else if(array4.length==1) {
									if(array4[0].equals("tests")) {
										a.Addoperation(new Test()); 
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
										
									}
									else if(array4[0].equals("doctorvisit")) {
										a.Addoperation(new DoctorVisit());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
									else if(array4[0].equals("imaging")) {
										a.Addoperation(new Imaging());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
									else {
										a.Addoperation(new Measurements());
										cost+=a.cost();
										String total = a.print()+" "+a.cost()+"$";
										output.add(total);
									}
								}
								else {
									cost+=a.cost();
									String total = a.print()+" "+a.cost()+"$";
									output.add(total);
									
								}
							}
						}
							else {
								break;
							}
					}
				}
			}
			output.add("         "+"Total: "+cost+"$");
		}
			else if(inputarray[0].equals("ListPatients")) {
				ArrayList<String> list = daoobject1.ListPatient();
				output.add("Patient List:");
				for(int j = 0 ; j< list.size() ; j++) {
					output.add(list.get(j).replace("\t"," "));
				}
			}
		}
		
	}
	public void update() {
		output.writing();
		daoobject1.writing();
		daoobject2.writing();
	}
}

