package manageri;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;*/
import java.io.IOException;

public class ProbaMain {

	public static void main(String[] args) throws IOException {
		KorisnikManager km = KorisnikManager.getInstance();
		km.registracijaKorisnika("Laslo", "Baranji", "szblaci@gmail.com", "laki_sb", "987654321");
		km.sacuvajNaloge();
		km.sacuvajKorisnike();
	}

}