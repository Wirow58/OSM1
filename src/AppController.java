
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AppController implements ActionListener, ListSelectionListener
{

	private AppView mView = null;
	List<Patient> patientList = new ArrayList<Patient>();
	public AppController(AppView view)
	{
		this.mView=view;  //Pamiêta klase odpowiedzialn¹ za gui
	}
	private boolean isAdd=false;
	
	@Override
	public void actionPerformed(ActionEvent e)  
	{
		String action = e.getActionCommand();
		//System.out.println(action);
		switch(action){
			case "ZapiszPacjenta":
			    if (isAdd=true) {
			        isAdd=false;
                    System.out.println("Pacjent");
                    patientList.add(new Patient(mView.getImie(), mView.getNazwisko(), mView.getPesel(), mView.getUbezpieczenie(), mView.getPlec()));
                    mView.getDtm().addRow(patientList.get(patientList.size() - 1).getPatientAsVector());
                    mView.ClearPatientFields();
                } else {
                    int selected = mView.getTable().getSelectedRow();
                    patientList.remove(selected);
                    mView.getDtm().removeRow(selected);
                    patientList.add(selected, new Patient(mView.getImie(), mView.getNazwisko(), mView.getPesel(), mView.getUbezpieczenie(), mView.getPlec()));
                    mView.getDtm().insertRow(selected, patientList.get(patientList.size() - 1).getPatientAsVector());
                }
				break;
			case "ZapiszBadanie":
				System.out.println("Badanie");
				int selected = mView.getTable().getSelectedRow();
				System.out.println(selected);
				patientList.get(selected).setDzien_badania(mView.getDzien());
				patientList.get(selected).setMiesiac_badania(mView.getMiesiac());
				patientList.get(selected).setRok_badania(mView.getRok());
				patientList.get(selected).setHDL(Integer.parseInt(mView.getHDL()));
                patientList.get(selected).setLDL(Integer.parseInt(mView.getLDL()));
                patientList.get(selected).setGlicerydy(Integer.parseInt(mView.getGlicerydy()));
                patientList.get(selected).setCzy_badany(true);
                mView.getDtm().setValueAt(patientList.get(selected).getCzy_badany(), selected, 4);
                mView.ClearExaminationFields();
				break;
			case "AnulujPacjenta":
				System.out.println("AnulujPacjenta");
				break;
			case "AnulujBadanie":
				System.out.println("AnulujBadanie");
				break;
			case "Dodaj":
			    isAdd=true;
				System.out.println("Dodaj");
                mView.EnablePatientFields();
                mView.ClearPatientFields();
				break;
			case "Usuñ":
				System.out.println("Usuñ");
				break;
            case "Zamknij":
                mView.dispose();
                break;

		}

	}

    @Override
    public void valueChanged(ListSelectionEvent event) {
	    //System.out.println("costam");
        if (mView.getTable().getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println(mView.getTable().getValueAt(mView.getTable().getSelectedRow(), 0).toString());
        }
    }

}
