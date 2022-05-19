

public class Test extends Decorator{
	private Examination examination;
	private int cost = 7;
	private String name = "tests";
	public Test(Examination examination) {
		this.examination=examination;
	}
	public Test() {}
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
