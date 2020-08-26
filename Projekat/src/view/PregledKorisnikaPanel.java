package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import model.Recept;
import model.RegistrovaniKorisnik;
import model.TipNaloga;
import net.miginfocom.swing.MigLayout;

public class PregledKorisnikaPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PregledKorisnikaPanel(MainWindow mw, JPanel prethodni, RegistrovaniKorisnik korisnik) {
		setBounds(0, 0, 1040, 650);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		JButton nazad = new JButton(new ImageIcon("data/ikonice/reduce.png"));
		nazad.setBounds(10, 0, 50, 50);
		nazad.setBorderPainted(false);
		nazad.setFocusPainted(false);
		nazad.setContentAreaFilled(false);
		add(nazad);
		nazad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mw.postaviDesniPanel(prethodni);
			}
		});

		JLabel lblKorisnickoIme = new JLabel(korisnik.getKorisnickoIme());
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblKorisnickoIme.setForeground(Color.WHITE);
		lblKorisnickoIme.setBounds(70, 10, 300, 30);
		add(lblKorisnickoIme);

		JSeparator sep = new JSeparator();
		sep.setBounds(0, 50, 1040, 5);
		add(sep);

		JLabel korisnikImg = new JLabel(new ImageIcon("data/ikonice/user.png"));
		korisnikImg.setBounds(20, 80, 100, 100);
		add(korisnikImg);

		JLabel imePrezime = new JLabel(korisnik.getIme() + " " + korisnik.getPrezime());
		imePrezime.setFont(new Font("Tahoma", Font.PLAIN, 35));
		imePrezime.setForeground(Color.WHITE);
		imePrezime.setBounds(30, 200, 400, 50);
		add(imePrezime);

		JButton follow = new JButton("Zaprati", new ImageIcon("data/ikonice/add-user.png"));
		follow.setBounds(150, 150, 150, 30);

		JButton unfollow = new JButton("Otprati", new ImageIcon("data/ikonice/unfollow.png"));
		unfollow.setBounds(150, 150, 150, 30);

		if (MainWindow.trenutniNalog != null) {
			if (MainWindow.trenutniNalog.getTip() == TipNaloga.REG_KORISNIK
					&& !MainWindow.trenutniNalog.getKorisnickoIme().equals(korisnik.getKorisnickoIme())) {
				RegistrovaniKorisnik rk = (RegistrovaniKorisnik) MainWindow.km.getKorisnik(MainWindow.trenutniNalog.getKorisnickoIme());
				if (rk.getPraceni() == null)
					add(follow);
				else {
					if (rk.getPraceni().contains(korisnik.getKorisnickoIme()))
						add(unfollow);
					else
						add(follow);
				}
				follow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						rk.addPracen(korisnik.getKorisnickoIme());
						korisnik.povecajBrojPratilaca();
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.promenjen(korisnik.getKorisnickoIme());
						MainWindow.km.sacuvajKorisnike();
						remove(follow);
						add(unfollow);
						revalidate();
						repaint();
					}
				});
				unfollow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						rk.deletePracen(korisnik.getKorisnickoIme());
						korisnik.smanjiBrojPratilaca();
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.promenjen(korisnik.getKorisnickoIme());
						MainWindow.km.sacuvajKorisnike();
						remove(unfollow);
						add(follow);
						revalidate();
						repaint();
					}
				});
			}
		}
		
		JLabel email = new JLabel(korisnik.getEmail());
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email.setForeground(Color.WHITE);
		email.setBounds(30, 250, 400, 50);
		add(email);
		
		JLabel lblBedz = new JLabel("Bedz:");
		lblBedz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBedz.setForeground(Color.WHITE);
		lblBedz.setBounds(30, 350, 200, 50);
		add(lblBedz);
		
		JLabel bedzVal = new JLabel(korisnik.getBedz()+"");
		bedzVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bedzVal.setForeground(Color.WHITE);
		bedzVal.setBounds(250, 350, 200, 50);
		add(bedzVal);
		
		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcena.setForeground(Color.WHITE);
		lblOcena.setBounds(30, 400, 200, 50);
		add(lblOcena);
		
		JLabel ocenaVal = new JLabel(korisnik.getProsecnaOcena()+"");
		ocenaVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		ocenaVal.setForeground(Color.WHITE);
		ocenaVal.setBounds(250, 400, 200, 50);
		add(ocenaVal);
		
		JLabel lblPratioci = new JLabel("Pratioci:");
		lblPratioci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPratioci.setForeground(Color.WHITE);
		lblPratioci.setBounds(30, 450, 200, 50);
		add(lblPratioci);
		
		JLabel pratiociVal = new JLabel(korisnik.getBrojPratilaca()+"");
		pratiociVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pratiociVal.setForeground(Color.WHITE);
		pratiociVal.setBounds(250, 450, 200, 50);
		add(pratiociVal);
		
		JLabel lblBrRecepata = new JLabel("Broj recepata:");
		lblBrRecepata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBrRecepata.setForeground(Color.WHITE);
		lblBrRecepata.setBounds(30, 500, 200, 50);
		add(lblBrRecepata);
		
		JLabel receptiVal = new JLabel(korisnik.getRecepti().size()+"");
		receptiVal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		receptiVal.setForeground(Color.WHITE);
		receptiVal.setBounds(250, 500, 200, 50);
		add(receptiVal);

		JPanel receptiPane = new JPanel(new MigLayout("wrap 1", "[][]10[]", "[]10[]"));
		receptiPane.setBackground(Color.DARK_GRAY);
		JScrollPane scrollRecepti = new JScrollPane(receptiPane);
		scrollRecepti.setBounds(500, 50, 540, 600);
		scrollRecepti.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollRecepti);
		for (Recept recept : MainWindow.rM.getRecepti(korisnik.getRecepti())) {
			MaliPrikazRecepta mpr = new MaliPrikazRecepta(recept, mw);
			receptiPane.add(mpr);
		}
	}
}
