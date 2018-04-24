
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class AppView extends JFrame //implements ActionListener
{

    private JMenuBar menuBar;
    private JMenu menuAplikacja;
    private JMenuItem apZamknij;
    private JPanel danePacjenta, badanie, listaPacjentow;


    private JTextField tImie, tNazwisko, tPesel, tHDL, tLDL, tGlicerydy;
    private JLabel lImie, lNazwisko, lPesel, lPlec, lUbezpieczenie, lData, lHDL, lLDL, lGlicerydy;
    private ButtonGroup radioPanel;
    private JRadioButton kobieta, mezczyzna;
    private JComboBox<String> boxUbezpieczenie;
    private JButton bZapiszPacjenta, bAnulujPacjenta, bZapiszBadanie, bAnulujBadanie, bDodaj, bUsun;
    private JSpinner SDzien, SMiesiac, SRok;
    //private JTable tablicaPacjentow;
    private String[] tblHead = {"Imię i nazwisko", "Płeć", "PESEL", "Ubezpieczenie", "Badanie"};
    private MyTableModel dtm = new MyTableModel(tblHead, 0) {
        Class[] types = {
                String.class, String.class, String.class, String.class, Boolean.class
        };

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
    };
    /*private DefaultTableModel dtm=new DefaultTableModel(tblHead, 0){
    	Class[] types = {
    	String.class, String.class, String.class, String.class, Boolean.class
		};
    	@Override
    	public Class getColumnClass(int columnIndex){
    		return types[columnIndex];
		}
	};*/
    private JScrollPane suwak;
    private JTable tablicaPacjentow = new JTable(dtm);

    public AppView() {
        this.setTitle("Rejestracja wyników badań");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(840, 400));
        this.setMinimumSize(new Dimension(840, 400));
        this.setMaximumSize(new Dimension(840, 400));

        //********pasek narzędzi******

        menuBar = new JMenuBar(); //tworzenie paska menu
        menuAplikacja = new JMenu("Aplikacja");

        apZamknij = new JMenuItem("Zamknij");


        menuAplikacja.add(apZamknij);

        apZamknij.setAccelerator(KeyStroke.getKeyStroke("alt F4"));  //dodanie skrótu klawiszowego

        setJMenuBar(menuBar); //dodanie menue Bar
        menuBar.add(menuAplikacja);  //dodanie do paska opcji aplikacja

        //********kontener dane pacjenta******

        //ułożenie okna dane w oknie głównym
        danePacjenta = new JPanel();
        GridBagConstraints ulozenie = new GridBagConstraints();
        ulozenie.fill = GridBagConstraints.HORIZONTAL;

        danePacjenta.setBorder(BorderFactory.createTitledBorder("Dane dacjenta"));
        ulozenie.gridx = 0;
        ulozenie.gridy = 0;
        this.getContentPane().add(danePacjenta, ulozenie); //dodanie kontenera do okna głównego
        //this.pack();

        //ułożenie kompontntów
        GroupLayout layoutDaneP = new GroupLayout(danePacjenta);
        danePacjenta.setLayout(layoutDaneP);

        lImie = new JLabel("Imię:");

        tImie = new JTextField(10);

        tImie.setEditable(false);

        lNazwisko = new JLabel("Nazwisko:");

        tNazwisko = new JTextField(10);

        tNazwisko.setEditable(false);

        lPesel = new JLabel("PESEL:");

        tPesel = new JTextField(10);

        tPesel.setEditable(false);

        lPlec = new JLabel("Płeć:");

        //dodawanie wyboru

        radioPanel = new ButtonGroup();

        kobieta = new JRadioButton("Kobieta");
        kobieta.setActionCommand("K");
        //kobieta.setSelected(true);  //ustawienie wciśniętego
        kobieta.setEnabled(false);

        mezczyzna = new JRadioButton("Mężczyzna");
        mezczyzna.setActionCommand("M");
        mezczyzna.setEnabled(false);

        radioPanel.add(kobieta);
        radioPanel.add(mezczyzna);

        //

        lUbezpieczenie = new JLabel("Ubezpieczenie:");

        //lista rozwijana
        boxUbezpieczenie = new JComboBox();
        boxUbezpieczenie.addItem("NFZ");
        boxUbezpieczenie.addItem("prywatne");
        boxUbezpieczenie.addItem("brak");

        boxUbezpieczenie.setEnabled(false);

        //przyciski zapisz i anuluj

        bZapiszPacjenta = new JButton("Zapisz");
        bZapiszPacjenta.setActionCommand("ZapiszPacjenta");
        bZapiszPacjenta.setEnabled(false);
        bAnulujPacjenta = new JButton("Anuluj");
        bAnulujPacjenta.setActionCommand("AnulujPacjenta");
        bAnulujPacjenta.setEnabled(false);


        layoutDaneP.setAutoCreateGaps(true);
        layoutDaneP.setHorizontalGroup(layoutDaneP.createSequentialGroup()
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lImie)
                        .addComponent(lNazwisko)
                        .addComponent(lPesel)
                        .addComponent(lPlec)
                        .addComponent(lUbezpieczenie)
                        .addGroup(layoutDaneP.createSequentialGroup()
                                .addComponent(bZapiszPacjenta)
                                .addComponent(bAnulujPacjenta)))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tImie)
                        .addComponent(tNazwisko)
                        .addComponent(tPesel)
                        .addGroup(layoutDaneP.createSequentialGroup()
                                .addComponent(kobieta)
                                .addComponent(mezczyzna))
                        .addComponent(boxUbezpieczenie)));

        layoutDaneP.setVerticalGroup(layoutDaneP.createSequentialGroup()
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lImie)
                        .addComponent(tImie))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lNazwisko)
                        .addComponent(tNazwisko))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lPesel)
                        .addComponent(tPesel))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lPlec)
                        .addComponent(kobieta)
                        .addComponent(mezczyzna))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lUbezpieczenie)
                        .addComponent(boxUbezpieczenie))
                .addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bZapiszPacjenta)
                        .addComponent(bAnulujPacjenta)));

        //****** panel badanie ******

        badanie = new JPanel();
        badanie.setBorder(BorderFactory.createTitledBorder("Badanie"));

        ulozenie.gridx = 0;
        ulozenie.gridy = 1;
        this.getContentPane().add(badanie, ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutBadanie = new GroupLayout(badanie);
        badanie.setLayout(layoutBadanie);

        lData = new JLabel("Data:");

        SpinnerNumberModel d_numberSpinnerModel = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerNumberModel m_numberSpinnerModel = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerNumberModel r_numberSpinnerModel = new SpinnerNumberModel(2000, 1, 3000, 1);

        SDzien = new JSpinner(d_numberSpinnerModel);
        //SDzien.setValue(1);
        SDzien.setEnabled(false);
        SMiesiac = new JSpinner(m_numberSpinnerModel);
        //SMiesiac.setValue(1);
        SMiesiac.setEnabled(false);
        SRok = new JSpinner(r_numberSpinnerModel);
        //SRok.setValue(2000);
        SRok.setEnabled(false);

        lHDL = new JLabel("Poziom holesterolu HDL:");

        tHDL = new JTextField();
        tHDL.setEditable(false);

        lLDL = new JLabel("Poziom holesterolu LDL:");

        tLDL = new JTextField();
        tLDL.setEditable(false);

        lGlicerydy = new JLabel("Poziom trójglicerydów:");

        tGlicerydy = new JTextField();
        tGlicerydy.setEditable(false);

        bZapiszBadanie = new JButton("Zapisz");
        bZapiszBadanie.setActionCommand("ZapiszBadanie");
        bZapiszBadanie.setEnabled(false);

        bAnulujBadanie = new JButton("Anuluj");
        bAnulujBadanie.setActionCommand("AnulujBadanie");
        bAnulujBadanie.setEnabled(false);

        //Ułożenie komponentów

        layoutBadanie.setAutoCreateGaps(true);
        layoutBadanie.setHorizontalGroup(layoutBadanie.createSequentialGroup()
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lData)
                        .addComponent(lHDL)
                        .addComponent(lLDL)
                        .addComponent(lGlicerydy)
                        .addGroup(layoutBadanie.createSequentialGroup()
                                .addComponent(bZapiszBadanie)
                                .addComponent(bAnulujBadanie)))
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutBadanie.createSequentialGroup()
                                .addComponent(SDzien)
                                .addComponent(SMiesiac)
                                .addComponent(SRok))
                        .addComponent(tHDL)
                        .addComponent(tLDL)
                        .addComponent(tGlicerydy)));


        layoutBadanie.setVerticalGroup(layoutBadanie.createSequentialGroup()
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lData)
                        .addComponent(SDzien)
                        .addComponent(SMiesiac)
                        .addComponent(SRok))
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lHDL)
                        .addComponent(tHDL))
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lLDL)
                        .addComponent(tLDL))
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lGlicerydy)
                        .addComponent(tGlicerydy))
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bZapiszBadanie)
                        .addComponent(bAnulujBadanie)));


        //******** Lista pacjentów **********

        listaPacjentow = new JPanel();
        listaPacjentow.setBorder(BorderFactory.createTitledBorder("Lista Pacjentów"));
        ulozenie.gridx = 1;
        ulozenie.gridy = 0;
        ulozenie.gridheight = 2;
        ulozenie.fill = GridBagConstraints.VERTICAL;

        listaPacjentow.setPreferredSize(new Dimension(500, 300));
        listaPacjentow.setMinimumSize(new Dimension(500, 300));
        listaPacjentow.setMaximumSize(new Dimension(1000, 900));

        this.getContentPane().add(listaPacjentow, ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutListaP = new GroupLayout(listaPacjentow);
        listaPacjentow.setLayout(layoutListaP);


        suwak = new JScrollPane();
		/*tablicaPacjentow = new JTable();
		tablicaPacjentow.setModel(new DefaultTableModel(new String[] {"Imię i nazwisko", "Płeć", "PESEL", "Ubezpieczenie", "Badanie"}, 25));
		tablicaPacjentow.setRowHeight(20);*/

        //tablicaPacjentow.setModel(dtm);


        suwak.setViewportView(tablicaPacjentow);
        /*String[] item={"A","B","C","D","E"};
        String[] item1={"1","2","3","4","5"};*/
        //tablicaPacjentow.setCellSelectionEnabled(false);
        //tablicaPacjentow.setRowSelectionAllowed(true);
		/*tablicaPacjentow.setEnabled(true);
        tablicaPacjentow.setUpdateSelectionOnSort(false);*/
        /*dtm.addRow(item);
        dtm.insertRow(1, item1);*/


        bDodaj = new JButton("Dodaj");

        bUsun = new JButton("Usuń");
        bUsun.setEnabled(false);

        //Ustawiamy tylko suwak!

        layoutListaP.setAutoCreateGaps(true);
        layoutListaP.setHorizontalGroup(layoutListaP.createSequentialGroup()
                .addGroup(layoutListaP.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(suwak)
                        .addGroup(layoutListaP.createSequentialGroup()
                                .addComponent(bDodaj)
                                .addComponent(bUsun))));


        layoutListaP.setVerticalGroup(layoutListaP.createSequentialGroup()
                .addGroup(layoutListaP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(suwak))
                .addGroup(layoutListaP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bDodaj)
                        .addComponent(bUsun)));

    }

    public void setController(ActionListener c)  //pozwala śledzić zewnętrznemu kontrolerowi zdarzenia generowane przez swoje kontrolki
    {
        this.bZapiszPacjenta.addActionListener(c);
        this.bAnulujPacjenta.addActionListener(c);
        this.bZapiszBadanie.addActionListener(c);
        this.bAnulujBadanie.addActionListener(c);
        this.bDodaj.addActionListener(c);
        this.bUsun.addActionListener(c);
        this.boxUbezpieczenie.addActionListener(c);
        this.kobieta.addActionListener(c);
        this.mezczyzna.addActionListener(c);
        this.apZamknij.addActionListener(c);


    }



    public void setTableController(ListSelectionListener lsl) {
        this.tablicaPacjentow.getSelectionModel().addListSelectionListener(lsl);
    }

    public void EnableDeleteButton() {
        bUsun.setEnabled(true);
    }

    public void DisableDeleteButton() {
        bUsun.setEnabled(false);
    }

    public void EnablePatientFields() {

        tImie.setEditable(true);
        tNazwisko.setEditable(true);
        tPesel.setEditable(true);
        kobieta.setEnabled(true);
        mezczyzna.setEnabled(true);
        boxUbezpieczenie.setEnabled(true);
        bZapiszPacjenta.setEnabled(true);
        bAnulujPacjenta.setEnabled(true);
    }

    public void DisablePatientFields() {

        tImie.setEditable(false);
        tNazwisko.setEditable(false);
        tPesel.setEditable(false);
        kobieta.setEnabled(false);
        mezczyzna.setEnabled(false);
        boxUbezpieczenie.setEnabled(false);
        bZapiszPacjenta.setEnabled(false);
        bAnulujPacjenta.setEnabled(false);
    }

    public void EnableExaminationFields() {
        SDzien.setEnabled(true);
        SMiesiac.setEnabled(true);
        SRok.setEnabled(true);
        tHDL.setEditable(true);
        tLDL.setEditable(true);
        tGlicerydy.setEditable(true);
        bZapiszBadanie.setEnabled(true);
        bAnulujBadanie.setEnabled(true);
    }

    public void DisableExaminationFields() {
        SDzien.setEnabled(false);
        SMiesiac.setEnabled(false);
        SRok.setEnabled(false);
        tHDL.setEditable(false);
        tLDL.setEditable(false);
        tGlicerydy.setEditable(false);
        bZapiszBadanie.setEnabled(false);
        bAnulujBadanie.setEnabled(false);
    }

    public void ClearPatientFields() {
        tImie.setText(null);
        tNazwisko.setText(null);
        tPesel.setText(null);
        radioPanel.clearSelection();
        boxUbezpieczenie.setSelectedIndex(0);

    }

    public void ClearExaminationFields() {
        SDzien.setValue(20);
        SMiesiac.setValue(12);
        SRok.setValue(1996);
        tHDL.setText(null);
        tLDL.setText(null);
        tGlicerydy.setText(null);
    }

    public String getImie() {
        return tImie.getText();
    }

    public String getNazwisko() {
        return tNazwisko.getText();
    }

    public String getPesel() {
        return tPesel.getText();
    }

    public String getUbezpieczenie() {
        return (String) boxUbezpieczenie.getSelectedItem();
    }

    public char getPlec() {
        return radioPanel.getSelection().getActionCommand().charAt(0);
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public JTable getTable() {
        return tablicaPacjentow;
    }

    public int getDzien() {
        return (int) SDzien.getValue();
    }

    public int getMiesiac() {
        return (int) SMiesiac.getValue();
    }

    public int getRok() {
        return (int) SRok.getValue();
    }

    public String getHDL() {
        return tHDL.getText();
    }

    public String getLDL() {
        return tLDL.getText();
    }

    public String getGlicerydy() {
        return tGlicerydy.getText();
    }

    public JTextField gettImie() {
        return tImie;
    }

    public JTextField gettNazwisko() {
        return tNazwisko;
    }

    public JTextField gettPesel() {
        return tPesel;
    }

    public JTextField gettHDL() {
        return tHDL;
    }

    public JTextField gettLDL() {
        return tLDL;
    }

    public JTextField gettGlicerydy() {
        return tGlicerydy;
    }

    public JRadioButton getKobieta() {
        return kobieta;
    }

    public boolean radioTest() {
        return radioPanel.getSelection() == null;
    }


    public JRadioButton getMezczyzna() {
        return mezczyzna;
    }

    public JComboBox<String> getBoxUbezpieczenie() {
        return boxUbezpieczenie;
    }

    public JSpinner getSDzien() {
        return SDzien;
    }

    public JSpinner getSMiesiac() {
        return SMiesiac;
    }

    public JSpinner getSRok() {
        return SRok;
    }


}