class FunkcjeEdytowania {

	private String imie;
	private String nazwisko;
	private char plec;
	private int nrDzialu;
	private float placa;
	private int wiek;
	private int liczbaDzieci;
	private boolean stanCywilny;	
	public Menu menuEdytowaniaPracownika;

	public FunkcjeEdytowania() {	
	menuEdytowaniaPracownika = new Menu();		
	menuEdytowaniaPracownika.dodajPole("Zmien numer dzialu");
	menuEdytowaniaPracownika.dodajPole("Zmien place");
	menuEdytowaniaPracownika.dodajPole("Zmien wiek");
	menuEdytowaniaPracownika.dodajPole("Zmien liczbe dzieci");
	menuEdytowaniaPracownika.dodajPole("Zmien stan cywilny");
	menuEdytowaniaPracownika.dodajPole("Zmien nazwisko (opcja dostepna tylko dla pracowniczek)");
	menuEdytowaniaPracownika.dodajPole("Powrot do glownego menu");		
	}
	
	public Pracownik  nowyPracownik() {
	    
		Pracownik pracownik = new Pracownik();
		this.edytujImie(pracownik);
		this.edytujNazwisko(pracownik);
		this.edytujPlec(pracownik);
		this.edytujNrDzialu(pracownik);
		this.edytujPlace(pracownik);
		this.edytujWiek(pracownik);
		this.edytujLiczbeDzieci(pracownik);
		this.edytujStanCywilny(pracownik);
		
		return new Pracownik(imie,nazwisko, plec, nrDzialu, placa, 
				wiek, liczbaDzieci, stanCywilny );
	   
	}
	
	public void edytujImie( Pracownik pracownik) {
		
		System.out.println("Podaj imie:");
		imie = Klawiatura.wczytajTekst();
		imie = imie.toLowerCase();
		imie = imie.trim();
		imie = imie.substring(0, 1).toUpperCase() + imie.substring(1);
	
	}
	
	public void edytujNazwisko(Pracownik pracownik ) {

		System.out.println("Podaj nazwisko:");
		nazwisko = Klawiatura.wczytajTekst();
		nazwisko = nazwisko.toLowerCase();
		nazwisko = nazwisko.trim();
		nazwisko = nazwisko.substring(0, 1).toUpperCase() + nazwisko.substring(1);
					
	   pracownik.SetNazwisko(nazwisko);
	}
	
	
	
	
	public void edytujPlec( Pracownik pracownik) {
		
		System.out.println("Podaj plec 'K' - kobieta, 'M' - mezczyzna:");
		boolean dalej = true;
		do {
			    String string = Klawiatura.wczytajTekst();
				string = string.toUpperCase();
				plec = string.charAt(0);
				if(  plec == 'K' || plec == 'M') {
					dalej = false;}
				else {
					System.out.println("Tylko litery 'K' lub 'M'");
				}		
		}while(dalej);
		
	}
	
	public void edytujNrDzialu( Pracownik pracownik) {
		boolean dalej = true;
		System.out.println("Podaj numer dzialu( 0-100):");
		dalej = true;
		do {		
				nrDzialu =  Klawiatura.wczytajInt();
				if(  nrDzialu >= 0 && nrDzialu <= 100) {
					dalej = false;}
				else {
					System.out.println("Numer dzialu 0-100!");
				}						
		}
		while(dalej);
		
		pracownik.SetNrDzialu(nrDzialu);
		
	}
	public void edytujPlace( Pracownik pracownik) {
		
		boolean dalej = true;
		System.out.println("Podaj place pracownika:");
		dalej = true;
		do {	
				placa =  Klawiatura.wczytajFloat();
				if(  placa >= 0) {
					dalej = false;}
				else {
					System.out.println("Placa nie moze byc ujemna!");
				}				
		}
		while(dalej);

		pracownik.SetPlaca(placa);

	}
	
	public void edytujWiek( Pracownik pracownik) {
		boolean dalej = true;
		System.out.println("Podaj wiek( 18-70):");
		dalej = true;
		do {		
				wiek =  Klawiatura.wczytajInt();
				if(  wiek >= 18 && wiek <= 70) {
					dalej = false;}
				else {
					System.out.println("Wiek 18-70!");
				}					
		}
		while(dalej);

		pracownik.SetWiek(wiek);
	
	}
	
	public void edytujLiczbeDzieci( Pracownik pracownik) {
		boolean dalej = true;
		System.out.println("Podaj liczbe dzieci:");
		dalej = true;
		do {	
				liczbaDzieci =  Klawiatura.wczytajInt();
				if(  liczbaDzieci >= 0 && liczbaDzieci <= 10) {
					dalej = false;}
				else {
					System.out.println("Liczba dzieci miedzy 0-10 ;)!");
				}				
		}
		while(dalej);
		
		pracownik.SetLiczbaDzieci(liczbaDzieci);
		
	}
	
	public void edytujStanCywilny( Pracownik pracownik) {
		boolean dalej = true;
		System.out.println("Pracownik stanu wolnego  't' - tak, 'n' - nie:");
		dalej = true;
		do {	
				String string = Klawiatura.wczytajTekst();
				string = string.toUpperCase();
				if(  string.charAt(0) == 'T' || string.charAt(0) == 'N') {
					dalej = false;
				if(string.charAt(0) == 'T') {
					stanCywilny = false;
				}
				else {
					stanCywilny = true;
				}
				}
				else {
					System.out.println(" Tylko 't' - tak, 'n' - nie !");
				}
		}
		while(dalej);
		
		pracownik.SetStanCywilny(dalej);
			
	}
	
}

