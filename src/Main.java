
import java.util.ArrayList;

import java.io.File;

public class Main {

	public static void main(String[] args)  {
		DAOManage manage = new DAOManage(new PatientDAO(),new AdmissionDAO(),new Input(args[0]));
		manage.AddPatient();
		manage.RemovePatient();
		manage.CreateAdmission();
		manage.ExaminationAndOutput();
		manage.update();
		
	

		


	}

}
