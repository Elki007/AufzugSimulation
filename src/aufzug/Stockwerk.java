package aufzug;
//as
import java.util.Vector;

public class Stockwerk {
	protected Vector<Person> leute = new Vector<Person>();
	
	public void add(Person noob) {
		leute.add(noob);
	}
	//
	public void geduldsenken(int frame) {
		for(int i=leute.size()-1; i>=0;i--) {
			leute.get(i).geduld -= frame;
			//test
			if (leute.get(i).geduld <frame) {
				leute.get(i).goHome=true;
			}
			if (leute.get(i).geduld <= 0) {
				leute.removeElement(leute.get(i));
			}
		}		
	}
}
