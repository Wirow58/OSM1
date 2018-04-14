import javax.swing.SwingUtilities;

public class Model
{

	public static void main (String[] args)
	{		
		{
			 Runnable thread=new Runnable()
			 {
				 @Override
				 public void run()
				 {
				 AppView view = new AppView();
				 AppController ctrl = new AppController(view);
				 view.setController(ctrl);
				 view.setVisible(true);
				 }
			 };
			 SwingUtilities.invokeLater(thread);   //wersja z wyk³adu
		}
	}
}