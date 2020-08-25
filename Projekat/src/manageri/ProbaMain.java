package manageri;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;*/

public class ProbaMain {

	public static void main(String[] args) {
		ReceptManager rm = ReceptManager.getInstance();
		rm.pregledaoRecept(rm.getRecept(0), null);
		rm.sacuvajRecepte();
		rm.sacuvajTabelu();
	}

}