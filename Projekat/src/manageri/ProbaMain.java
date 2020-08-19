package manageri;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;*/
import java.io.IOException;

public class ProbaMain {

	public static void main(String[] args) throws IOException {
		/*UredjajManager pm = UredjajManager.getInstance();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("d:\\Cetvrti semestar\\Specifikacija i modeliranje\\uredjaji.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String linija = "";
		while ((linija = br.readLine()) != null) {
			pm.novUredjaj(linija.trim());
		}
		br.close();
		pm.sacuvajUredjaje();
		System.out.println(pm.getNaziviUredjaja());*/
		
		ReceptManager km = ReceptManager.getInstance();
		km.sacuvajTabelu();
	}

}