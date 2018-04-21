
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class AppView extends JFrame //implements ActionListener
{
	
	private JMenuBar menuBar;
	private JMenu menuAplikacja;
	private JMenuItem apZamknij;
	private JPanel danePacjenta, badanie,listaPacjentow;
	private JTextField tImie, tNazwisko, tPesel, tHDL, tLDL, tGlicerydy;
	private JLabel lImie, lNazwisko, lPesel, lPlec, lUbezpieczenie, lData, lHDL, lLDL, lGlicerydy;
	private ButtonGroup radioPanel;
	private JRadioButton kobieta, mezczyzna;
	private JComboBox<String> boxUbezpieczenie;
	private JButton bZapiszPacjenta, bAnulujPacjenta, bZapiszBadanie, bAnulujBadanie, bDodaj, bUsun;
	private JSpinner SDzien, SMiesiac,SRok;
	//private JTable tablicaPacjentow;
    private String[] tblHead = {"Imiê i nazwisko", "P³eæ", "PESEL", "Ubezpieczenie", "Badanie"};
    private DefaultTableModel dtm=new DefaultTableModel(tblHead, 0);
	private JScrollPane suwak;
	
	public AppView()
	{
		this.setTitle("Rejestracja wyników badañ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(840,400));
		this.setMinimumSize(new Dimension(840,400));
		this.setMaximumSize(new Dimension(840,400));
		
		//********pasek narzêdzi******
		
		menuBar = new JMenuBar(); //tworzenie paska menu
		menuAplikacja = new JMenu("Aplikacja");
		
		apZamknij = new JMenuItem("Zamknij");

		
		menuAplikacja.add(apZamknij);
		
		apZamknij.setAccelerator(KeyStroke.getKeyStroke("alt F4"));  //dodanie skrótu klawiszowego
		
		setJMenuBar(menuBar); //dodanie menue Bar
		menuBar.add(menuAplikacja);  //dodanie do paska opcji aplikacja
		
		//********kontener dane pacjenta******

		//u³o¿enie okna dane w oknie g³ównym
		danePacjenta = new JPanel();
		GridBagConstraints ulozenie = new GridBagConstraints();
		ulozenie.fill = GridBagConstraints.HORIZONTAL;
		
		danePacjenta.setBorder(BorderFactory.createTitledBorder("Dane dacjenta"));
		ulozenie.gridx = 0;
		ulozenie.gridy = 0;
		this.getContentPane().add(danePacjenta,ulozenie); //dodanie kontenera do okna g³ównego
		//this.pack();
		
		//u³o¿enie kompontntów
		GroupLayout layoutDaneP = new GroupLayout(danePacjenta);
		danePacjenta.setLayout(layoutDaneP);

		lImie = new JLabel("Imiê:");

		tImie=new JTextField(10);

		tImie.setEditable(false);
		
		lNazwisko = new JLabel("Nazwisko:");

		tNazwisko=new JTextField(10);

		tNazwisko.setEditable(false);
		
		lPesel = new JLabel("PESEL:");

		tPesel=new JTextField(10);

		tPesel.setEditable(false);
		
		lPlec = new JLabel("P³eæ:");

		//dodawanie wyboru
		
		radioPanel = new ButtonGroup();
		
		kobieta = new JRadioButton("Kobieta");
		kobieta.setActionCommand("K");
		//kobieta.setSelected(true);  //ustawienie wciœniêtego
        kobieta.setEnabled(false);
		
		mezczyzna = new JRadioButton("Mê¿czyzna");
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
		.addGroup(layoutDaneP.createParallelGroup(GroupLayout.Alignment.LEADING )
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
		this.getContentPane().add(badanie,ulozenie); //dodanie kontenera do okna g³ównego
		this.pack();
		
		GroupLayout layoutBadanie = new GroupLayout(badanie);
		badanie.setLayout(layoutBadanie);
		
		lData = new JLabel("Data:");
		
		SDzien = new JSpinner();
		SDzien.setValue(20);
		
		SMiesiac = new JSpinner();
		SMiesiac.setValue(12);
		
		SRok = new JSpinner();
		SRok.setValue(1996);
		
		
		lHDL = new JLabel("Poziom holesterolu HDL:");

		tHDL=new JTextField();

		lLDL = new JLabel("Poziom holesterolu LDL:");
		
		tLDL=new JTextField();

		lGlicerydy = new JLabel("Poziom trójglicerydów:");
		
		tGlicerydy=new JTextField();
		
		bZapiszBadanie = new JButton("Zapisz");
		bZapiszBadanie.setActionCommand("ZapiszBadanie");
		
		bAnulujBadanie = new JButton("Anuluj");
		bAnulujBadanie.setActionCommand("AnulujBadanie");

		//U³o¿enie komponentów
		
		layoutBadanie.setAutoCreateGaps(true);
		layoutBadanie.setHorizontalGroup(layoutBadanie.createSequentialGroup()
		.addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.LEADING )
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
		
		listaPacjentow.setPreferredSize(new Dimension(500,300));
		listaPacjentow.setMinimumSize(new Dimension(500,300));
		listaPacjentow.setMaximumSize(new Dimension(1000,900));
		
		this.getContentPane().add(listaPacjentow,ulozenie); //dodanie kontenera do okna g³ównego
		this.pack();	 
		
		GroupLayout layoutListaP = new GroupLayout(listaPacjentow);
		listaPacjentow.setLayout(layoutListaP);

		
		
		suwak = new JScrollPane();
		/*tablicaPacjentow = new JTable();
		tablicaPacjentow.setModel(new DefaultTableModel(new String[] {"Imiê i nazwisko", "P³eæ", "PESEL", "Ubezpieczenie", "Badanie"}, 25));
		tablicaPacjentow.setRowHeight(20);*/

		//tablicaPacjentow.setModel(dtm);
        JTable tablicaPacjentow = new JTable(dtm);
		suwak.setViewportView(tablicaPacjentow);
        /*String[] item={"A","B","C","D","E"};
        String[] item1={"1","2","3","4","5"};*/

        /*dtm.addRow(item);
        dtm.insertRow(1, item1);*/
		
		
		bDodaj = new JButton("Dodaj");

		bUsun = new JButton("Usuñ");

		
		//Ustawiamy tylko suwak!
		
		layoutListaP.setAutoCreateGaps(true);
		layoutListaP.setHorizontalGroup(layoutListaP.createSequentialGroup()
				.addGroup(layoutListaP.createParallelGroup(GroupLayout.Alignment.LEADING )  
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

	public void setController(ActionListener c)  //pozwala œledziæ zewnêtrznemu kontrolerowi zdarzenia generowane przez swoje kontrolki
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
	// this.SDzien.addAncestorListener(c);
	// this.SMiesiac.addComponentListener(c);
	// this.SRok.addComponentListener(c);
	 }

	//public void setValue(int value) //pozwala kontrolerowi wp³ywaæ na stan kontorlek
	 //{
	//	this.mEdit.setText(String.valueOf(value));  <- Przyk³ad
	// 	this.tablicaPacjentow.setToolTipText(String.valueOf(value)); <-coœ zle
	// }

    public void EnablePatientFields(){
	    tImie.setEditable(true);
	    tNazwisko.setEditable(true);
	    tPesel.setEditable(true);
	    kobieta.setEnabled(true);
	    mezczyzna.setEnabled(true);
	    boxUbezpieczenie.setEnabled(true);
	    bZapiszPacjenta.setEnabled(true);
	    bAnulujPacjenta.setEnabled(true);
    }

    public String getImie(){
	    return tImie.getText();
    }

    public String getNazwisko(){
	    return tNazwisko.getText();
    }

    public String getPesel(){
	    return tPesel.getText();
    }

    public String getUbezpieczenie(){
	    return (String)boxUbezpieczenie.getSelectedItem();
    }

    public char getPlec(){
	    return radioPanel.getSelection().getActionCommand().charAt(0);
    }

    public DefaultTableModel getDtm(){
	    return dtm;
    }
}