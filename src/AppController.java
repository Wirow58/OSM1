
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener
{

	private AppView mView = null;
	
	public AppController(AppView view)
	{
		this.mView=view;  //Pamiêta klase odpowiedzialn¹ za gui
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)  
	{
				

		if (e.getActionCommand().equals("Zapisz"))
			{
		
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
			}
		
	}

}
