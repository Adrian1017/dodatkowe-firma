import java.io.Serializable;

class Pracownik implements Comparable<Pracownik>, Serializable {	
		/**
	 * 
	 */
	    private static final long serialVersionUID = 6174125303978722932L;
		private String imie;
		private String nazwisko;
		private char plec;
		private int nrDzialu;
		private float placa;
		private int wiek;
		private int liczbaDzieci;
		private boolean stanCywilny;
	
		public Pracownik( String imie, String nazwisko, char plec, int nrDzialu, float placa, 
				int wiek, int liczbaDzieci, boolean stanCywilny) {
				this.imie = imie;
				this.nazwisko = nazwisko;
				this.plec = plec;
				this.nrDzialu = nrDzialu;
				this.placa = placa;
				this.wiek = wiek;
				this.liczbaDzieci = liczbaDzieci;
				this.stanCywilny = stanCywilny;
		}
	
		public Pracownik() {	
			}
		@Override 
		public int compareTo( Pracownik p) {
			return nazwisko.compareTo(p.getNazwisko());
		}
		
		//getery		
		public String getImie() {
		return this.imie;
		}
		public String getNazwisko() {
		return this.nazwisko;
		}
		public char getPlec() {
			return this.plec;
		}
		public int getNrDzialu() {
			return this.nrDzialu;
		}
		public float getPlaca() {
			return this.placa;
		}
		public int getWiek() {
			return this.wiek;
		}
		public int getLiczbaDzieci() {
			return this.liczbaDzieci;
		}
		public boolean getStanCywilny() {
			return this.stanCywilny;
		}
		
		//setery	
		public void SetNazwisko( String nazwisko) {
			this.nazwisko = nazwisko;
		}
		public void SetNrDzialu( int nrDzialu) {
			this.nrDzialu = nrDzialu;
		}
		public void  SetPlaca(float placa) {
			this.placa = placa;
		}
		public void SetWiek(int wiek ) {
			this.wiek = wiek;
		}
		public void SetLiczbaDzieci(int liczbaDzieci) {
			this.liczbaDzieci = liczbaDzieci;
		}
		public void SetStanCywilny( boolean stanCywilny) {
			this.stanCywilny = stanCywilny;
		}
		
		// metody
		public String pokazWszystkieDane() {		
			return "Imie: " + imie + " Nazwisko:" + nazwisko + " Plec:" + plec + " Numer dzialu:" + nrDzialu
			+ " Placa:" + placa + " Wiek:" + wiek + " Liczba dzieci:" + liczbaDzieci 
			+ " Stan cywilny:" + pokazStanCywilny();	
			}
		public String pokazOkrojoneDane() {
			return imie + " " + nazwisko + " " + placa ;	
		}
		
		public String pokazDaneDuzeLitery() {
			return imie.toUpperCase() + " " + nazwisko.toUpperCase();	
		}
		public boolean czyPensjaWyzszaOdPodanej( float pensja) {
			if( this.placa > pensja) {
				return true;
			}
			return false;
		}
		
		public int procentPodwyzki() {		
			int procent = 0;
			procent = procent + ( 2 * liczbaDzieci);
			if(stanCywilny && procent > 0) {
				procent+= 3;}
			return procent;	
			}
		
		public String pokazStanCywilny() {		
			if(stanCywilny) {
				if(plec == 'K') {
					return "zamezna";
				}
				else {
					return "zonaty";
				}
			}
				return "wolny";	
		}
		
	}