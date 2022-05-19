

public class Measurements extends Decorator{
	private Examination examination;
	private int cost = 5;
	private String name = "mesaurements";
	public Measurements(Examination examination) {
		this.examination=examination;
	}
	public Measurements() {}
	public int cost() {
		if(examination==null) {
			return cost;
		}
		else {
		return examination.cost()+cost;
	}
}
	public String print() {
		if(examination==null) {
			return name;
		}
		else {
			return name+" "+examination.print();
	}	
	
	}
	public void Addoperation(Examination a) {
		this.examination=a;
	}
}
