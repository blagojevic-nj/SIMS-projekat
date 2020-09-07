package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import manageri.ReceptManager;
import model.Recenzija;
import model.Recept;
import model.RegistrovaniKorisnik;
import model.Sastojak;
import model.TipNaloga;
import model.TipOcene;
import model.UredjajUReceptu;
import net.miginfocom.swing.MigLayout;

public class VelikiPrikazRecepta extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String font = "Tahoma";

	private Image img;
	private int brojacZvezdi = 0;
	private int privremenaOcena = 0;
	private int brojacRecenzija;
	private JLabel zvezde;
	private JButton follow;
	private JButton unfollow, sacuvaj, zaboravi;
	private JPanel recenzije;

	public VelikiPrikazRecepta() {

	}

	public VelikiPrikazRecepta(MainWindow mW, JPanel prethodni, Recept r) {
		this.img = new ImageIcon("data/ikonice/back2.jpg").getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(0, 0, 1040, 650);
		setLayout(null);

		ImageIcon back1 = new ImageIcon("data/ikonice/back1.png");
		Image b1 = back1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		JButton nazad = new JButton(new ImageIcon(b1));
		nazad.setBounds(20, 0, 50, 50);
		nazad.setBorderPainted(false);
		nazad.setFocusPainted(false);
		nazad.setContentAreaFilled(false);
		add(nazad);
		nazad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mW.postaviDesniPanel(prethodni);
			}
		});

		JLabel naslov = new JLabel(r.getNaziv());
		naslov.setFont(new Font(font, 1, 20));
		naslov.setBounds(450, 10, 300, 30);
		naslov.setForeground(Color.white);
		add(naslov);

		setBorder(BorderFactory.createRaisedBevelBorder());

		ImageIcon slika = new ImageIcon(ReceptManager.getPutanjaDoSlike(r.getId()));

		JLabel slikaLabela = new JLabel(
				new ImageIcon(slika.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		slikaLabela.setBounds(20, 50, 300, 300);
		add(slikaLabela);

		JLabel clock = new JLabel(new ImageIcon("data/ikonice/clock.png"));
		clock.setBounds(20, 365, 30, 30);
		add(clock);
		JLabel vreme = new JLabel(r.getVremePripreme() + " minuta");
		vreme.setFont(new Font(font, 0, 15));
		vreme.setBounds(55, 365, 100, 30);
		vreme.setForeground(Color.white);
		add(vreme);

		JLabel tezina = new JLabel(new ImageIcon("data/ikonice/tezina/" + r.getTezina().toString() + ".png"));
		tezina.setBounds(140, 347, 86, 86);
		add(tezina);

		JLabel ocena = new JLabel("Ocena: " + r.getOcena());
		ocena.setBounds(240, 365, 100, 30);
		ocena.setFont(new Font(font, 0, 15));
		ocena.setForeground(Color.white);
		add(ocena);

		JLabel datum = new JLabel(r.getDatum().toString());
		datum.setFont(new Font(font, 0, 15));
		datum.setBounds(330, 365, 80, 30);
		datum.setForeground(Color.white);
		add(datum);

		JLabel pregledi = new JLabel(new ImageIcon("data/ikonice/eye.png"));
		pregledi.setBounds(420, 364, 30, 30);
		add(pregledi);

		JLabel brojPregleda = new JLabel(r.getPregleda() + "");
		brojPregleda.setFont(new Font(font, 0, 15));
		brojPregleda.setForeground(Color.white);
		brojPregleda.setBounds(460, 365, 80, 30);
		add(brojPregleda);

		JSeparator sep = new JSeparator();
		sep.setBounds(520, 50, 7, 570);
		sep.setOrientation(1);
		add(sep);
		sep.setForeground(Color.black);

		int brojac = 0;
		for (Integer i : r.getKategorije()) {
			JLabel logoKat = new JLabel(new ImageIcon("data/ikonice/category.png"));
			logoKat.setBounds(330, 50 + brojac * 30, 30, 30);
			JLabel kategorija = new JLabel(MainWindow.katM.getKategorija(i).getNaziv().toUpperCase());
			kategorija.setFont(new Font(font, 1, 15));
			kategorija.setForeground(Color.white);
			kategorija.setBounds(360, 50 + brojac * 30, 180, 30);
			add(kategorija);
			add(logoKat);
			brojac++;
		}

		JButton youtube = new JButton("Video", new ImageIcon("data/ikonice/youtube.png"));
		youtube.setForeground(Color.white);
		youtube.setBorderPainted(false);
		youtube.setFocusPainted(false);
		youtube.setContentAreaFilled(false);
		youtube.setBounds(290, 290, 150, 30);

		youtube.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Desktop d = Desktop.getDesktop();
				try {
					if(!r.getYoutubeLink().equals(""))
						d.browse(new URI(r.getYoutubeLink()));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});

		add(youtube);

		JSeparator sep1 = new JSeparator();
		sep1.setBounds(40, 430, 440, 7);
		add(sep1);
		sep1.setForeground(Color.black);

		JLabel sastojci = new JLabel("Potrebno je: ");
		sastojci.setFont(new Font(font, 1, 15));
		sastojci.setBounds(540, 50, 100, 20);
		sastojci.setForeground(Color.white);
		add(sastojci);

		JPanel listaSastojaka = new JPanel(new MigLayout());
		listaSastojaka.setOpaque(false);
		listaSastojaka.setBackground(Color.DARK_GRAY);

		for (Sastojak s : r.getSastojci()) {
			JLabel naziv = new JLabel("- " + s.getNazivProizvoda());
			naziv.setForeground(Color.white);
			listaSastojaka.add(naziv);
			JLabel kolicina = new JLabel(" | " + s.getKolicina() + " " + s.getMernaJedinica());
			kolicina.setForeground(Color.white);
			listaSastojaka.add(kolicina);
			JLabel tip = new JLabel(" | " + s.getTip().toString());
			tip.setForeground(Color.white);
			listaSastojaka.add(tip, "wrap");
		}

		JScrollPane sastojciPane = new JScrollPane(listaSastojaka);
		sastojciPane.setOpaque(false);
		sastojciPane.getViewport().setOpaque(false);
		sastojciPane.getVerticalScrollBar().setUnitIncrement(20);
		//sastojciPane.getVerticalScrollBar().setVisible(false);
		sastojciPane.setBorder(BorderFactory.createRaisedBevelBorder());
		//sastojciPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		sastojciPane.setBounds(540, 80, 230, 150);
		add(sastojciPane);

		JLabel uredjaji = new JLabel("Uredjaji: ");
		uredjaji.setFont(new Font(font, 1, 15));
		uredjaji.setForeground(Color.white);
		uredjaji.setBounds(790, 50, 100, 20);
		add(uredjaji);

		JPanel listaUredjaja = new JPanel(new MigLayout());
		listaUredjaja.setOpaque(false);
		listaUredjaja.setBackground(Color.DARK_GRAY);

		for (UredjajUReceptu u : r.getUredjaji()) {
			JLabel stavka = new JLabel("-" + u.getUredjaj().getNaziv());
			stavka.setForeground(Color.white);
			listaUredjaja.add(stavka);
			JLabel tip = new JLabel(" | " + ((u.isPozeljan()) ? "NEOPHODAN" : "NIJE NEOPHODAN"));
			tip.setForeground(Color.white);
			listaUredjaja.add(tip, "wrap");
		}

		JScrollPane uredjajiPane = new JScrollPane(listaUredjaja);
		uredjajiPane.setOpaque(false);
		uredjajiPane.getViewport().setOpaque(false);
		uredjajiPane.getVerticalScrollBar().setUnitIncrement(20);
		//uredjajiPane.getVerticalScrollBar().setVisible(false);
		uredjajiPane.setBorder(BorderFactory.createRaisedBevelBorder());
		//uredjajiPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		uredjajiPane.setBounds(790, 80, 230, 150);
		add(uredjajiPane);

		JSeparator sep2 = new JSeparator();
		sep2.setBounds(560, 240, 440, 7);
		add(sep2);
		sep2.setForeground(Color.black);

		JTextArea opis = new JTextArea(r.getOpis());
		opis.setOpaque(false);
		opis.setBackground(Color.DARK_GRAY);
		opis.setForeground(Color.white);
		opis.setEditable(false);
		opis.setFont(new Font(font, 1, 12));

		JScrollPane opisPane = new JScrollPane(opis);
		opisPane.setOpaque(false);
		opisPane.getViewport().setOpaque(false);
		opisPane.getVerticalScrollBar().setUnitIncrement(20);
		//opisPane.getVerticalScrollBar().setVisible(false);
		opisPane.setBorder(BorderFactory.createRaisedBevelBorder());
		//opisPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		opisPane.setBounds(20, 440, 480, 100);
		add(opisPane);

		JSeparator sep3 = new JSeparator();
		sep3.setBounds(40, 550, 440, 7);
		add(sep3);
		sep3.setForeground(Color.black);

		JLabel koraciLabela = new JLabel("Postupak pripreme");
		koraciLabela.setForeground(Color.white);
		koraciLabela.setFont(new Font(font, 1, 15));
		koraciLabela.setBounds(720, 250, 200, 30);
		add(koraciLabela);

		JTextArea koraci = new JTextArea(r.getKoraci());
		koraci.setOpaque(false);
		koraci.setBackground(Color.DARK_GRAY);
		koraci.setForeground(Color.white);
		koraci.setEditable(false);

		JScrollPane koraciPane = new JScrollPane(koraci);
		koraciPane.setOpaque(false);
		koraciPane.getViewport().setOpaque(false);
		koraciPane.getVerticalScrollBar().setUnitIncrement(20);
		//koraciPane.getVerticalScrollBar().setVisible(false);
		koraciPane.setBorder(BorderFactory.createRaisedBevelBorder());
		//koraciPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		koraciPane.setBounds(540, 280, 480, 150);
		add(koraciPane);

		//JLabel korisnik = new JLabel(new ImageIcon("data/ikonice/user.png"));
		ImageIcon korisnikIcon = new ImageIcon("data/ikonice/noUser.png");
		Image ki1 = korisnikIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		JLabel korisnik = new JLabel(new ImageIcon(ki1));

		korisnik.setBounds(20, 570, 50, 50);
		add(korisnik);

		korisnik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mW.postaviDesniPanel(new PregledKorisnikaPanel(mW, VelikiPrikazRecepta.this,
						(RegistrovaniKorisnik) MainWindow.km.getKorisnik(r.getAutor())));
			}
		});

		JLabel autor = new JLabel(r.getAutor());
		autor.setForeground(Color.white);
		autor.setFont(new Font(font, 1, 15));
		autor.setBounds(80, 583, 100, 50);
		add(autor);
		
		ImageIcon zaprati1 = new ImageIcon("data/ikonice/follow.png");
		Image z1 = zaprati1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		follow = new JButton(new ImageIcon(z1));
		follow.setBounds(400, 570, 50, 50);
		follow.setToolTipText("Zaprati!");
		follow.setBorderPainted(false);
		follow.setFocusPainted(false);
		follow.setContentAreaFilled(false);
		
		
		ImageIcon book1 = new ImageIcon("data/ikonice/bookmark.png");
		Image bk1 = book1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		sacuvaj = new JButton(new ImageIcon(bk1));
		sacuvaj.setBounds(460, 570, 50, 50);
		sacuvaj.setToolTipText("Sacuvaj!");
		sacuvaj.setBorderPainted(false);
		sacuvaj.setFocusPainted(false);
		sacuvaj.setContentAreaFilled(false);

		ImageIcon unbook1 = new ImageIcon("data/ikonice/unbookmark.png");
		Image ubk1 = unbook1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	
		zaboravi = new JButton(new ImageIcon(ubk1));
		zaboravi.setBounds(460, 570, 50, 50);
		zaboravi.setToolTipText("Obrisi sacuvano!");
		zaboravi.setBorderPainted(false);
		zaboravi.setFocusPainted(false);
		zaboravi.setContentAreaFilled(false);

		ImageIcon otprati1 = new ImageIcon("data/ikonice/unfollow.png");
		Image o1 = otprati1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		unfollow = new JButton(new ImageIcon(o1));
		unfollow.setBounds(400, 570, 50, 50);
		unfollow.setToolTipText("Otprati!");
		unfollow.setBorderPainted(false);
		unfollow.setFocusPainted(false);
		unfollow.setContentAreaFilled(false);
		
		
		JLabel komentari = new JLabel("Komentari: ");
		komentari.setForeground(Color.white);
		komentari.setFont(new Font(font, 1, 15));
		komentari.setBounds(750, 430, 150, 25);
		add(komentari);

		JTextArea mojKom = new JTextArea("Unesite komentar:");
		mojKom.setForeground(Color.white);
		mojKom.setBackground(Color.DARK_GRAY);
		JScrollPane mojKomPane = new JScrollPane(mojKom);
		mojKomPane.setBounds(540, 450, 450, 30);

		JButton objavi = new JButton(new ImageIcon("data/ikonice/comment.png"));
		objavi.setBounds(990, 450, 30, 30);
		objavi.setBorderPainted(false);
		objavi.setFocusPainted(false);
		objavi.setContentAreaFilled(false);
		objavi.setToolTipText("Postavi komentar");

		mojKom.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (mojKom.getText().equals("Unesite komentar:")) {
					mojKom.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (mojKom.getText().isEmpty()) {
					mojKom.setText("Unesite komentar:");
				}
			}
		});

		recenzije = new JPanel(null);
		recenzije.setBackground(Color.DARK_GRAY);
		brojacRecenzija = 0;
		for (Recenzija rec : r.getRecenzije()) {
			JTextArea recenzija = new JTextArea(rec.getKomentar());
			recenzija.setBackground(Color.DARK_GRAY);
			recenzija.setForeground(Color.white);
			recenzija.setEditable(false);
			JScrollPane recenzijaPane = new JScrollPane(recenzija);
			TitledBorder tB = BorderFactory
					.createTitledBorder(rec.getIdKorisnika() + " " + ReceptManager.dateTimeToString(rec.getDatum()));
			recenzijaPane.setBorder(tB);
			recenzijaPane.setBounds(10, 10 + brojacRecenzija * 70, 440, 70);
			recenzije.add(recenzijaPane);
			brojacRecenzija++;
		}

		JScrollPane recenzijePane = new JScrollPane();
		recenzije.setPreferredSize(new Dimension(460, brojacRecenzija * 70 + 20));
		recenzijePane.getViewport().add(recenzije);
		recenzijePane.setBackground(Color.DARK_GRAY);
		recenzijePane.setBounds(540, 480, 480, 150);

		objavi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mojKom.getText().equals("Unesite komentar:") || mojKom.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(mW, "Prvo unesite komentar a zatim ga objavite!");
				} else {
					Recenzija rec = new Recenzija(mojKom.getText().trim(), LocalDateTime.now(),
							MainWindow.trenutniNalog.getKorisnickoIme());
					r.addRecenzija(rec);
					MainWindow.rM.promenjen(r);
					MainWindow.rM.sacuvajRecepte();
					JOptionPane.showMessageDialog(mW, "Uspesno ste dodali recenziju!");
					mojKom.setText("Unesite komentar:");

					JTextArea recenzija = new JTextArea(rec.getKomentar());
					recenzija.setBackground(Color.DARK_GRAY);
					recenzija.setForeground(Color.white);
					recenzija.setEditable(false);
					JScrollPane recenzijaPane = new JScrollPane(recenzija);
					TitledBorder tB = BorderFactory.createTitledBorder(
							rec.getIdKorisnika() + " " + ReceptManager.dateTimeToString(rec.getDatum()));
					recenzijaPane.setBorder(tB);
					recenzijaPane.setBounds(10, 10 + brojacRecenzija * 70, 440, 70);
					recenzije.add(recenzijaPane);
					brojacRecenzija++;
					recenzijePane.getViewport().remove(recenzije);
					recenzije.setPreferredSize(new Dimension(460, brojacRecenzija * 70 + 20));
					recenzijePane.getViewport().add(recenzije);
					revalidate();
					repaint();
				}
			}

		});

		add(recenzijePane);
		if (MainWindow.trenutniNalog != null) {
			if (MainWindow.trenutniNalog.getTip() == TipNaloga.REG_KORISNIK
					&& !MainWindow.trenutniNalog.getKorisnickoIme().equals(r.getAutor())) {
				RegistrovaniKorisnik rk = (RegistrovaniKorisnik) MainWindow.km
						.getKorisnik(MainWindow.trenutniNalog.getKorisnickoIme());
				if (rk.getPraceni() == null) {
					add(follow);
				} else {
					if (rk.getPraceni().contains(r.getAutor())) {
						add(unfollow);

					} else {
						add(follow);
					}
				}
				
				if(rk.getSacuvaniRecepti().contains(r.getId()))
					add(zaboravi);
				else
					add(sacuvaj);

				follow.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						RegistrovaniKorisnik autor = (RegistrovaniKorisnik) MainWindow.km.getKorisnik(r.getAutor());
						rk.zaprati(autor);
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.promenjen(r.getAutor());
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
						RegistrovaniKorisnik autor = (RegistrovaniKorisnik) MainWindow.km.getKorisnik(r.getAutor());
						rk.otprati(autor);
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.promenjen(r.getAutor());
						MainWindow.km.sacuvajKorisnike();
						remove(unfollow);
						add(follow);
						revalidate();
						repaint();
					}
				});

				sacuvaj.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						rk.addSacuvaniRecept(r.getId());
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.sacuvajKorisnike();
						JOptionPane.showMessageDialog(mW, "Sacuvali ste recept. Mozete ga pogledati kasnije.");
						
						add(zaboravi);
						remove(sacuvaj);
						revalidate();
						repaint();
					}

				});
				
				zaboravi.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						rk.deleteSacuvaniRecept(r.getId());
						MainWindow.km.promenjen(rk.getKorisnickoIme());
						MainWindow.km.sacuvajKorisnike();
						JOptionPane.showMessageDialog(mW, "Izbacili ste recept iz vasih sacuvanih recepata!");
						remove(zaboravi);
						add(sacuvaj);
						revalidate();
						repaint();
					}
					
				});

				add(mojKomPane);
				add(objavi);

				MainWindow.rM.pregledaoRecept(r, rk);

				if (r.getOcene() != null && r.getOcene().containsKey(MainWindow.trenutniNalog.getIdKorisnika())) {
					switch (r.getOcene().get(MainWindow.trenutniNalog.getIdKorisnika())) {
					case CETIRI:
						brojacZvezdi = 4;
						privremenaOcena = 4;
						break;
					case DVA:
						brojacZvezdi = 2;
						privremenaOcena = 2;
						break;
					case JEDAN:
						brojacZvezdi = 1;
						privremenaOcena = 1;
						break;
					case PET:
						brojacZvezdi = 5;
						privremenaOcena = 5;
						break;
					case TRI:
						brojacZvezdi = 3;
						privremenaOcena = 3;
						break;
					default:
						break;
					}
				}

				zvezde = new JLabel(new ImageIcon("data/ikonice/stars/" + privremenaOcena + ".png"));
				zvezde.setBounds(330, 330, 93, 20);
				add(zvezde);

				zvezde.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						brojacZvezdi = privremenaOcena;
						zvezde.setIcon(new ImageIcon("data/ikonice/stars/" + privremenaOcena + ".png"));
						if (r.getOcene() == null) {
							r.setOcene(new HashMap<Integer, TipOcene>());
						}
						if (brojacZvezdi == 1)
							r.getOcene().put(MainWindow.trenutniNalog.getIdKorisnika(), TipOcene.JEDAN);
						else if (brojacZvezdi == 2)
							r.getOcene().put(MainWindow.trenutniNalog.getIdKorisnika(), TipOcene.DVA);
						else if (brojacZvezdi == 3)
							r.getOcene().put(MainWindow.trenutniNalog.getIdKorisnika(), TipOcene.TRI);
						else if (brojacZvezdi == 4)
							r.getOcene().put(MainWindow.trenutniNalog.getIdKorisnika(), TipOcene.CETIRI);
						else if (brojacZvezdi == 5)
							r.getOcene().put(MainWindow.trenutniNalog.getIdKorisnika(), TipOcene.PET);

						RegistrovaniKorisnik autor = (RegistrovaniKorisnik) MainWindow.km.getKorisnik(r.getAutor());
						MainWindow.rM.prosekOcenaRecepta(r);
						MainWindow.km.prosekOcenaKorisnik(autor);
						MainWindow.km.promenjen(r.getAutor());
						MainWindow.rM.promenjen(r);
						MainWindow.rM.sacuvajRecepte();
						MainWindow.km.sacuvajKorisnike();

						zvezde.revalidate();
						zvezde.repaint();
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {

					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						privremenaOcena = brojacZvezdi;
						zvezde.setIcon(new ImageIcon("data/ikonice/stars/" + privremenaOcena + ".png"));
						zvezde.revalidate();
						zvezde.repaint();

					}

					@Override
					public void mousePressed(MouseEvent arg0) {

					}

					@Override
					public void mouseReleased(MouseEvent arg0) {

					}

				});

				zvezde.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent arg0) {

					}

					@Override
					public void mouseMoved(MouseEvent e) {
						int x = e.getX();
						if (x <= 3)
							privremenaOcena = 0;
						else if (x > 3 && x < 20)
							privremenaOcena = 1;
						else if (21 <= x && x <= 38)
							privremenaOcena = 2;
						else if (39 <= x && x <= 56)
							privremenaOcena = 3;
						else if (57 <= x && x <= 70)
							privremenaOcena = 4;
						else
							privremenaOcena = 5;

						zvezde.setIcon(new ImageIcon("data/ikonice/stars/" + privremenaOcena + ".png"));
						repaint();
					}
				});
			} else if (MainWindow.trenutniNalog.getTip() == TipNaloga.REG_KORISNIK) {
				add(mojKomPane);
				add(objavi);
			}
		} else {
			MainWindow.rM.pregledaoRecept(r, null);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
