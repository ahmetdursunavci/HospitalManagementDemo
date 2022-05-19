

public class Imaging extends Decorator{
	private Examination examination;
	private int cost=10;
	private String name = "imaging";
	public Imaging(Examination a) {
		this.examination=a;
	}
	public Imaging() {}
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


