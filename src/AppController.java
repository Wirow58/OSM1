import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppController implements ActionListener, ListSelectionListener {

    private AppView mView = null;

    List<Patient> patientList = new ArrayList<Patient>();

    public AppController(AppView view) {
        this.mView = view;  //Pamięta klase odpowiedzialną za gui
    }

    private boolean isAdd = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        int selected = 0;
        switch (action) {
            case "ZapiszPacjenta":
                if (ArgumentsControlLib.isPatientOK(mView)) {
                    if (isAdd == true) {
                        if (ArgumentsControlLib.checkPesel(mView.getPesel(), patientList)) {
                            System.out.println(isAdd);
                            System.out.println("Pacjent");
                            patientList.add(new Patient(mView.getImie(), mView.getNazwisko(), mView.getPesel(), mView.getUbezpieczenie(), mView.getPlec()));
                            mView.getDtm().addRow(patientList.get(patientList.size() - 1).getPatientAsVector());
                            mView.ClearPatientFields();
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Pacjent z podanym numerem PESEL już został wprowadzony do systemu!",
                                    "Błąd!",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        System.out.println(isAdd);
                        selected = mView.getTable().getSelectedRow();
                        boolean isExaminated = patientList.get(selected).getCzy_badany();
                        patientList.remove(selected);
                        mView.getDtm().removeRow(selected);
                        patientList.add(selected, new Patient(mView.getImie(), mView.getNazwisko(), mView.getPesel(), mView.getUbezpieczenie(), mView.getPlec(), isExaminated));
                        mView.getDtm().insertRow(selected, patientList.get(selected).getPatientAsVector());
                        mView.ClearPatientFields();
                        mView.DisablePatientFields();
                        mView.DisableExaminationFields();
                        mView.DisableDeleteButton();
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Błąd wprowadzonych danych!\nZwróć uwagę na:\n-Czy w imieniu i nazwisku nie ma cyfr\n-Czy numer pesel jest poprawny (11 cyfr)\n-Czy płeć pacjenta została wybrana",
                            "Błąd!",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "ZapiszBadanie":
                if (ArgumentsControlLib.isExaminationOK(mView)) {
                    System.out.println("Badanie");
                    ArgumentsControlLib.examinationRange(mView);
                    selected = mView.getTable().getSelectedRow();
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
                    mView.getTable().clearSelection();
                    mView.DisableExaminationFields();
                    mView.DisablePatientFields();
                    mView.ClearPatientFields();
                    mView.DisableDeleteButton();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Wprowadzone wyniki badań nie są wartościami liczbowymi!",
                            "Błąd!",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "AnulujPacjenta":
                System.out.println("AnulujPacjenta");
                mView.ClearPatientFields();
                break;
            case "AnulujBadanie":
                System.out.println("AnulujBadanie");
                mView.ClearPatientFields();
                break;
            case "Dodaj":
                isAdd = true;
                System.out.println("Dodaj");
                mView.EnablePatientFields();
                mView.ClearPatientFields();
                mView.getTable().clearSelection();
                mView.DisableDeleteButton();
                break;
            case "Usuń":
                System.out.println("Usuń");
                selected = mView.getTable().getSelectedRow();
                patientList.remove(selected);
                mView.getDtm().removeRow(selected);
                mView.ClearPatientFields();
                mView.ClearExaminationFields();
                mView.DisablePatientFields();
                mView.DisableDeleteButton();
                mView.DisableExaminationFields();
                break;
            case "Zamknij":
                mView.dispose();
                break;

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        isAdd = false;
        mView.EnablePatientFields();
        mView.EnableExaminationFields();
        mView.EnableDeleteButton();
        if (mView.getTable().getSelectedRow() > -1) {
            System.out.println("costam");
            int selected = mView.getTable().getSelectedRow();
            isAdd = false;
            mView.gettImie().setText(patientList.get(selected).getImie());
            mView.gettNazwisko().setText(patientList.get(selected).getNazwisko());
            if (patientList.get(selected).getPlec() == 'K') {
                mView.getKobieta().setSelected(true);
                mView.getMezczyzna().setSelected(false);
            } else {
                mView.getKobieta().setSelected(false);
                mView.getMezczyzna().setSelected(true);
            }
            mView.gettPesel().setText(patientList.get(selected).getPesel());
            switch (patientList.get(selected).getUbezpieczenie()) {
                case "NFZ":
                    mView.getBoxUbezpieczenie().setSelectedIndex(0);
                    break;
                case "prywatne":
                    mView.getBoxUbezpieczenie().setSelectedIndex(1);
                    break;
                case "brak":
                    mView.getBoxUbezpieczenie().setSelectedIndex(2);
                    break;
            }
            mView.getSDzien().setValue(patientList.get(selected).getDzien_badania());
            mView.getSMiesiac().setValue(patientList.get(selected).getMiesiac_badania());
            mView.getSRok().setValue(patientList.get(selected).getRok_badania());
            mView.gettLDL().setText(Integer.toString(patientList.get(selected).getLDL()));
            mView.gettHDL().setText(Integer.toString(patientList.get(selected).getHDL()));
            mView.gettGlicerydy().setText(Integer.toString(patientList.get(selected).getGlicerydy()));
        }

    }

}
