

public class DoctorVisit extends Decorator{
	private Examination examination;
	private int cost = 15;
	private String name = "doctorvisit";
	public DoctorVisit(Examination a) {
		examination = a;
	}
	public DoctorVisit() {}
	public String print() {
		if(examination==null) {
			return name;
		}
		else {
			return name+" "+examination.print();
	}
	}

	public int cost() {
		if(examination==null) {
			return cost;
		}
		else {
		return examination.cost()+cost;
	}
}
	public void Addoperation(Examination a) {
		this.examination=a;
	}
}
