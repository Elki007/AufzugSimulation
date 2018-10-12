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
			if (leute.get(i).status == Status.wait) {
				leute.get(i).geduld -= frame;
				//test
				if (leute.get(i).geduld <frame) {
					leute.get(i).status = Status.goHome;
				}
				if (leute.get(i).status == Status.home) {
					leute.removeElement(leute.get(i));
				}
			}
			if (leute.get(i).status == Status.steigAus || leute.get(i).status == Status.ausgestiegen) {
				leute.get(i).geduld -= 2*frame;
			}
		}		
	}
}
