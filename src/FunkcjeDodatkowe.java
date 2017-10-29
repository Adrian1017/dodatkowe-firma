import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FunkcjeDodatkowe {
    
	public  Menu menuDodatkoweFunkcje;
	
	public FunkcjeDodatkowe() {
		
		menuDodatkoweFunkcje = new Menu();
		menuDodatkoweFunkcje.dodajPole("Pracownicy z pensja nie mniejsza niz...");
		menuDodatkoweFunkcje.dodajPole("Srednia placa w dziale");
		menuDodatkoweFunkcje.dodajPole("Najwieksze pensje wsrod kobiet i mezczyzn");
		menuDodatkoweFunkcje.dodajPole("Srednia ilosc kobiet i mezczyzn w poszczegolnych dzialach");
		menuDodatkoweFunkcje.dodajPole("Stosunek sredniej placy kobiet do sredniej placy mezczyzn");
		menuDodatkoweFunkcje.dodajPole("Zwiekszanie pensji o 10%");
		menuDodatkoweFunkcje.dodajPole("Zwiekszanie pensji o podana kwote");
		menuDodatkoweFunkcje.dodajPole("Sortowanie pracownikow wg. nazwiska");
		menuDodatkoweFunkcje.dodajPole("Sortowanie pracownikow wg. pensji");
		menuDodatkoweFunkcje.dodajPole("Powrot do glownego menu");
	}
	
	public void pracownicyZPensjaNieMniejsza( String nazwa ) {
		    ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
			float sumaDoPorownania;
			boolean dalej = true;
			dalej = true;
			System.out.println("Podaj place do porownania:");
			do {
				sumaDoPorownania = Klawiatura.wczytajFloat();
					if(  sumaDoPorownania >= 0) {
						dalej = false;}
					else {
						System.out.println("Placa nie moze byc ujemna!");}							
			}
				while(dalej);
				for( Pracownik pracownik : listaPracownikow) {
					if( pracownik.czyPensjaWyzszaOdPodanej(sumaDoPorownania)){
						System.out.println(pracownik.pokazOkrojoneDane());
					}
				}
		
	}
		
	  public float sredniaPlacWDziale( String nazwa) {
		  
			boolean dalej = true;
			int dzial = 0;
			System.out.println("Podaj numer dzialu( 0-100):");
			dalej = true;
			do {
				dzial = Klawiatura.wczytajInt();
				if(  dzial >= 0 && dzial <= 100) {
					dalej = false;}
				else {
						System.out.println("Numer dzialu 0-100!");
					}										
			}
			while(dalej);
			
           return this.policzsredniaPlacWDziale(nazwa, dzial);
			
	
	  }
	
		private float policzsredniaPlacWDziale( String nazwa, int dzial) {
			
			 ObslugaPliku plik = new ObslugaPliku();
			 List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
				
			    float sumaPensji = 0;
			    int indeks = 0;
			    for( Pracownik pracownik : listaPracownikow) {
			    	if(pracownik.getNrDzialu() == dzial) {
						indeks++;
						sumaPensji+= pracownik.getPlaca();
					}			
				}
				
				if(sumaPensji > 0) {
					System.out.println(sumaPensji / indeks + "PLN");
				}
				else {
					System.out.println("Srednia w tym dziale nie mozna policzyc");
					return -1;
				}
				return sumaPensji / indeks;
		}
			
	public void najwiekszePensjeWsrodKobietMezczyzn(String nazwa) {
			ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
			Pracownik kobietaMaxPensja = null;
			
			int index = 0;
			for( int i = 0; i < listaPracownikow.size(); i++) {
				
				if( listaPracownikow.get(i).getPlec() == 'K'){				
					kobietaMaxPensja = listaPracownikow.get(i);
					index = i+1;
					break;
				}
			}
			
			for( int i = index; i < listaPracownikow.size(); i++) {
				
				if(listaPracownikow.get(i).getPlec() == 'K') {
					if( listaPracownikow.get(i).czyPensjaWyzszaOdPodanej(kobietaMaxPensja.getPlaca())){
						kobietaMaxPensja = listaPracownikow.get(i);
					}
				}
			}
			System.out.println(kobietaMaxPensja.pokazOkrojoneDane());
			
			Pracownik mezczyznaMaxPensja = null;
		    index = 0;
			for( int i = 0; i < listaPracownikow.size(); i++) {
				
				if( listaPracownikow.get(i).getPlec() == 'M'){				
					mezczyznaMaxPensja = listaPracownikow.get(i);
					index = i+1;
					break;
				}
			}
			
			for( int i = index; i < listaPracownikow.size(); i++) {
				
				if(listaPracownikow.get(i).getPlec() == 'M') { 
					if(listaPracownikow.get(i).czyPensjaWyzszaOdPodanej(mezczyznaMaxPensja.getPlaca())){
					mezczyznaMaxPensja = listaPracownikow.get(i);
					}
				}
			}
			System.out.println(mezczyznaMaxPensja.pokazOkrojoneDane());
			
		}
	public void sredniaIloscKobietMezczyznWPoszczegolnychDzialach(String nazwa) {
			
			ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
			Set <Integer> listaDzialow = new HashSet<>();
        
			for( Pracownik pracownik : listaPracownikow) {
				listaDzialow.add( pracownik.getNrDzialu());		
			}
		
			listaDzialow.stream().forEach( d -> { System.out.print("Dzial " + d + " srednia plac:");
			this.policzsredniaPlacWDziale(nazwa, d);   this.liczenieSredniejPlciWDziale(nazwa, d);} );
		
			}
		
		
	private void liczenieSredniejPlciWDziale(String nazwa, int dzial) {
			ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
		    
			 int iloscKobiet = 0;
			 int iloscMezczyzn = 0;
				for( Pracownik pracownik : listaPracownikow) {
					if(pracownik.getNrDzialu() == dzial) {
						if(pracownik.getPlec() == 'K') {
							iloscKobiet++;
						}
						if(pracownik.getPlec() == 'M') {
							iloscMezczyzn++;
						}
					}			
				}
				
				if(iloscKobiet != iloscMezczyzn) {
					if(iloscKobiet > iloscMezczyzn) {
						System.out.println("W tym dziale pracuje wiecej kobiet");
					}
					else{
						System.out.println("W tym dziale pracuje wiecej mezczyzn");}
				}else {			
					System.out.println("W tym dziale pracuje tyle samo kobiet i mezczyzn");}
			
		}
		
		public void  stosunekSredniejPlacyKobietDoSredniejPlacyMezczyzn(String nazwa) {
			
			System.out.println("Srednia zarobkow kobiet do mezczyzn to:" +  (Math.round( (this.liczenieSredniejPlacKobiet(nazwa)/ this.liczenieSredniejPlacMezczyzn(nazwa)) +100.00) / 100.00) );
		}
		
		public void zwiekszaniePensjiO10Procent(String nazwa) {
			ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
			for( Pracownik pracownik : listaPracownikow) {
				pracownik.SetPlaca( pracownik.getPlaca() + ( ( pracownik.procentPodwyzki() + 10) * (pracownik.getPlaca()/100)) );
				}
			plik.eksportDoPlikuTekstowego(nazwa, listaPracownikow);
			listaPracownikow.stream().forEach( p -> System.out.println( p.pokazOkrojoneDane()));
		}
		
		
		public void  zwiekszaniePensjiOPodanaKwote(String nazwa) {
			ObslugaPliku plik = new ObslugaPliku();
		    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
		
				boolean dalej = true;
				float podwyzka = 0;
				float sumaPodwyzek = 0;
				float sumaPodwyzekKobiet = 0;
				float sumaPodwyzekMezczyzn = 0; 		
				System.out.println("Podaj podwyzke");
				dalej = true;
				do {
					 podwyzka = Klawiatura.wczytajFloat();
						if(  podwyzka >= 0) {
							dalej = false;}
						else {
							System.out.println("Podwyzka nie moze byc ujemna!");
						}		
				
				}
				while(dalej);

			for( Pracownik pracownik : listaPracownikow) {
				pracownik.SetPlaca( pracownik.getPlaca() + podwyzka);
				sumaPodwyzek+= podwyzka;
				if(pracownik.getPlec() == 'K') {
					sumaPodwyzekKobiet+= podwyzka;
				}
				else {
					sumaPodwyzekMezczyzn+=podwyzka;}	
				}
			plik.eksportDoPlikuTekstowego(nazwa, listaPracownikow);
			listaPracownikow.stream().forEach( p -> System.out.println( p.pokazOkrojoneDane()));
			System.out.println("Suma wszystki podwyzek:" + sumaPodwyzek +"\nSuma podwyzek kobiet:" + sumaPodwyzekKobiet + "\nSuma podwyzek mezczyzn:" + sumaPodwyzekMezczyzn);
		}
		
		
	public void  sortowanieWgNazwiska(String nazwa) {
		ObslugaPliku plik = new ObslugaPliku();
	    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
	       String string;
			boolean dalej = true;
			System.out.println("Sortowanie (r)osnaco  czy (m)alejaco?");
			dalej = true;
			do {	
					string =  Klawiatura.wczytajTekst().toUpperCase();
					if(  string.charAt(0) == 'R' || string.charAt(0) == 'M') {
						dalej = false;		
					}
					else {
						System.out.println("Tylko litery 't' lub 'n'");
					}				
			}
			while(dalej);
			
	
	    
		if( string.charAt(0) == 'R')
		{   
			listaPracownikow.sort(Comparator.comparing(Pracownik::getNazwisko));
			listaPracownikow.stream().forEach( p -> System.out.println(p.pokazOkrojoneDane()));
		}
		else {
			listaPracownikow.sort(Comparator.comparing(Pracownik::getNazwisko).reversed());
			listaPracownikow.stream().forEach( p -> System.out.println(p.pokazOkrojoneDane()));
		}
	}
	
	public void sortowanieWgPensji(String nazwa) {
		
		ObslugaPliku plik = new ObslugaPliku();
		List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
		String string;
		boolean dalej = true;
		System.out.println("Sortowanie (r)osnaco  czy (m)alejaco?");
		dalej = true;
		do {
			
			string =  Klawiatura.wczytajTekst().toUpperCase();
			if(  string.charAt(0) == 'R' || string.charAt(0) == 'M') {
				dalej = false;		
			}
			else {
				System.out.println("Tylko litery 't' lub 'n'");
			}				
		}
		while(dalej);
			
	
	    
		if( string.charAt(0) == 'R')
			{   
				listaPracownikow.sort(Comparator.comparing( Pracownik::getPlaca));
				listaPracownikow.stream().forEach( p -> System.out.println(p.pokazOkrojoneDane()));
			}
			else {
				listaPracownikow.sort(Comparator.comparing(Pracownik::getPlaca).reversed());
				listaPracownikow.stream().forEach( p -> System.out.println(p.pokazOkrojoneDane()));
			}
		
		
	}
		
	private float liczenieSredniejPlacKobiet(String nazwa) {
		ObslugaPliku plik = new ObslugaPliku();
		List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
		float sumaPensji = 0;
		int indeks = 0;
		for( Pracownik pracownik : listaPracownikow) {
			if(pracownik.getPlec() == 'K') {
				sumaPensji+= pracownik.getPlaca();
				indeks++;}
		}		
		return sumaPensji/indeks;			
	}
	
	private float liczenieSredniejPlacMezczyzn(String nazwa) {
		ObslugaPliku plik = new ObslugaPliku();
		List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
		float sumaPensji = 0;
		int indeks = 0;
		for( Pracownik pracownik : listaPracownikow) {
			if(pracownik.getPlec() == 'M') {
				sumaPensji+= pracownik.getPlaca();
				indeks++;}
		}		
		return sumaPensji/indeks;	
	}
				
}
