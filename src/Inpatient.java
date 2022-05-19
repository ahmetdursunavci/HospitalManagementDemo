

public class Inpatient implements Examination {
	private int incost = 10 ;
	private String name = "Inpatient";
	private Examination examination;
	public int cost() {
		return incost+examination.cost();
	}
	public String print() {
		return "        "+" "+name+" "+examination.print();
	}
	public void Addoperation(Examination a) {
		this.examination=a;
	}
		
	
}
