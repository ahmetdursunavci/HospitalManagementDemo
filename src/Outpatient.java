

public class Outpatient implements Examination{
	private int outcost = 15;
	private String name = "Outpatient";
	private Examination examination;
	public int cost() {
		return outcost+examination.cost();
	}
	public String print() {
		return "         "+name+" "+examination.print();
	}
	public void Addoperation(Examination a) {
		this.examination=a;
	}
}
