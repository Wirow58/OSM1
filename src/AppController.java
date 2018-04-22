
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppController implements ActionListener
{

	private AppView mView = null;
	List<Patient> patientList = new ArrayList<Patient>();
	public AppController(AppView view)
	{
		this.mView=view;  //Pamiêta klase odpowiedzialn¹ za gui
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)  
	{
		String action = e.getActionCommand();
		switch(action){
			case "ZapiszPacjenta":
				System.out.println("Pacjent");
               patientList.add(new Patient(mView.getImie(), mView.getNazwisko(), mView.getPesel(), mView.getUbezpieczenie(), mView.getPlec()));
                mView.getDtm().addRow(patientList.get(patientList.size()-1).getPatientAsArray());
				break;
			case "ZapiszBadanie":
				System.out.println("Badanie");
				break;
			case "AnulujPacjenta":
				System.out.println("AnulujPacjenta");
				break;
			case "AnulujBadanie":
				System.out.println("AnulujBadanie");
				break;
			case "Dodaj":
				System.out.println("Dodaj");
                mView.EnablePatientFields();
				break;
			case "Usuñ":
				System.out.println("Usuñ");
				break;
            case "Zamknij":
                mView.dispose();
                break;

		}
		/*if (e.getActionCommand().equals("Zapisz"))
			{
		System.out.println("Dupppa");
			}
		else if (e.getActionCommand().equals("Anuluj"))
			{
		
			}
		else if (e.getActionCommand().equals("Dodaj"))
			{
		
			}
		else if (e.getActionCommand().equals("Usun"))
			{
		
			}
		else if (e.getActionCommand().equals("Zamknij"))
			{
			mView.dispose();
			}*/
		
	}

}
