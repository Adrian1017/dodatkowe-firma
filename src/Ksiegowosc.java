import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Ksiegowosc{
	
	private FunkcjeGlowne funkcjeGlowne; // klasa wewnetrzna 
	private FunkcjeDodatkowe funkcjeDodatkowe;
	private FunkcjePlikow funkcjePlikow;
	private FunkcjeEdytowania funkcjeEdytowania;
	private ObslugaPliku plik; 
	private List<Pracownik> listaPracownikow;
	private final int REZYGNUJ = 997;
	private String nazwaBazyDanych = "bazaDanych.dat";
	private String nazwaPlikuTekstowego = "Lista_pracownikow";	
	private final int MAX_ILOSC_PRACOWNIKOW = 100;
	
	public Ksiegowosc() {
	
	    funkcjeGlowne = new FunkcjeGlowne();
		funkcjeDodatkowe = new FunkcjeDodatkowe();
		funkcjePlikow = new FunkcjePlikow();
		funkcjeEdytowania = new FunkcjeEdytowania();
		plik = new ObslugaPliku(); 
		listaPracownikow = new ArrayList<>();
	}
	
	public void dodajPracownikaTest( Pracownik pracownik) {
		
		listaPracownikow.add(pracownik);
		
	}
	
     public void start() {
    	 
    	if(!plik.czyIstnieje("firma.info")) {
    		System.out.println("Brak pliku konfiguracyjnego!");
    		System.exit(1);	
    	}
    	else {
    		// pobranie nazyw bazy danych z pliku konfig. i importowanie bazy do Listy: listaPracownikow
    		this.nazwaBazyDanych = plik.ladujePlikInfo();
    		System.out.println("Laduje baze danych z pliku:" + nazwaBazyDanych);
    	    this.listaPracownikow = plik.importZBazyDanychDoListy(nazwaBazyDanych);
    		this.menuGlowne();}
    	   
    	 
     }
	
	private void menuGlowne() {
		// menuGlowne uzywa bezposrednio listy: listaPracownikow i po wybraniu opcji Koniec zapisuje do bazy danych
		// wywolanie menu dodatkowego lub plikow zapisuje listaPracownikow do pliku tekstowego
	
		boolean powtorz = true;
	
		while(powtorz) {
			
			int wybor = funkcjeGlowne.menuGlowne.zwrocWyborLiczby();
			
		switch(wybor) {
		
		case 1:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.pokazWszystkichPracownikow();
            this.czekajNaKlawisz();
			break;
			
		case 2:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.dodajNowegoPracownika();
			break;
		case 3:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			this.czekajNaKlawisz();
			break;
			
		case 4:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
		    funkcjeGlowne.usuwaniePracownika();
			this.czekajNaKlawisz();
			break;	
		case 5:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.edycjaDanych();
			break;
			
		case 6:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.dodatkoweFunkcje();
			break;
		case 7:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.funkcjeDlaPlikowTekstowych();
			break;
			
		case 8:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.informacjeOProgramie();
			break;	
		case 9:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.nowaNazwaPlikuDanych();
			break;
			
		case 10:
			funkcjeGlowne.menuGlowne.pokazPole(wybor -1);
			funkcjeGlowne.koniec();
			powtorz = false;
			break;	
			
		case 997:
			// wybranie samej opcji <enter>  bez numeru z menu
			break;	
			
		}
		
		
	    
		}
	}
	
	private void menuDodatkoweFunkcje(){
		boolean powtorz = true;
	     // to menu dziala na pliku tekstowym listy pracownikow
		
		while(powtorz) {
		int wybor = funkcjeDodatkowe.menuDodatkoweFunkcje.zwrocWyborLitery();
		
		switch(wybor) {
		
		
		case 1:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
	        funkcjeDodatkowe.pracownicyZPensjaNieMniejsza(nazwaPlikuTekstowego);
			 this.czekajNaKlawisz();
			break;
			
		case 2:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.sredniaPlacWDziale(nazwaPlikuTekstowego);
		    this.czekajNaKlawisz();
			break;
			
		case 3:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.najwiekszePensjeWsrodKobietMezczyzn(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 4:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.sredniaIloscKobietMezczyznWPoszczegolnychDzialach(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;	
		case 5:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
		    funkcjeDodatkowe.stosunekSredniejPlacyKobietDoSredniejPlacyMezczyzn(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 6:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.zwiekszaniePensjiO10Procent(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();			
			break;
		case 7:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.zwiekszaniePensjiOPodanaKwote(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 8:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.sortowanieWgNazwiska(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;	
		case 9:
			funkcjeDodatkowe.menuDodatkoweFunkcje.pokazPole(wybor -1);
			funkcjeDodatkowe.sortowanieWgPensji(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 10:
            // kopiowanie pliku txt do listyPracownikow, nie jest w zadaniu wymagane, ale inaczej podwyzki przepadna...
			listaPracownikow = plik.importZPlikuTekstowego(nazwaPlikuTekstowego);
		    powtorz = false;
			break;	
		}
		
		
		
		}	
		
		
		
	}
	
	private void menuFunkcjeDlaPlikow(){
		
		boolean powtorz = true;
		while(powtorz) {
		int wybor = funkcjePlikow.menuFunkcjePlikow.zwrocWyborLitery();
		// to menu dziala na pliku tekstowym listy pracownikow
		switch(wybor) {
		
			
			
		case 1:
			funkcjePlikow.menuFunkcjePlikow.pokazPole(wybor -1);
		    funkcjePlikow.pokazNajdluzszeNazwisko(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 2:
			funkcjePlikow.menuFunkcjePlikow.pokazPole(wybor -1);
			funkcjePlikow.pokazSredniWiekZDziecmi(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
		case 3:
			funkcjePlikow.menuFunkcjePlikow.pokazPole(wybor -1);
			funkcjePlikow.kodowanieNazwiska(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;
			
		case 4:
			funkcjePlikow.menuFunkcjePlikow.pokazPole(wybor -1);
			funkcjePlikow.tabelaHTML(nazwaPlikuTekstowego);
			this.czekajNaKlawisz();
			break;	
			
		case 5:
			// kopiowanie pliku txt do listyPracownikow, nie jest w zadaniu wymagane, ale inaczej kodowanie nazwiska przepadnie...
			listaPracownikow = plik.importZPlikuTekstowego(nazwaPlikuTekstowego);
		    powtorz = false;
			break;	
		}
		
		
		
		}	
		
		
		
	}
	
	private void menuEdytowaniaPracownika( int index){
		boolean powtorz = true;
		while(powtorz) {
			System.out.println(listaPracownikow.get(index).pokazDaneDuzeLitery());
			
		int wybor = funkcjeEdytowania.menuEdytowaniaPracownika.zwrocWyborLitery();     
		
		switch(wybor) {
		
		
		case 1:
			funkcjeEdytowania.edytujNrDzialu(listaPracownikow.get(index));
			break;
			
		case 2:
			funkcjeEdytowania.edytujPlace(listaPracownikow.get(index));
			break;
			
		case 3:
			funkcjeEdytowania.edytujWiek(listaPracownikow.get(index));
			break;
			
		case 4:
			funkcjeEdytowania.edytujLiczbeDzieci(listaPracownikow.get(index));
			break;	
			
		case 5:
			funkcjeEdytowania.edytujStanCywilny(listaPracownikow.get(index));
			break;
			
		case 6:
			if( listaPracownikow.get(index).getPlec() == 'M') {
				System.out.println("Tylko nazwiska kobiet moga byc edytowane!");		
			}
			else
			{ 
				funkcjeEdytowania.edytujNazwisko(listaPracownikow.get(index));}
			break;
		
		case 7:
			powtorz = false;
			break;
		}
		
		
		
		}	
		
		
	
	
	
	
		
	}
	
	
	private void czekajNaKlawisz() {
		Scanner sc = new Scanner(System.in);
		System.out.println("------------> Nacisnij  klawisz <enter> zeby kontynuowac <-------------");
		while(!sc.hasNextLine()) {
			
		}
	}
	
	
	
	
class FunkcjeGlowne{
		
		public Menu menuGlowne;
		
		public  FunkcjeGlowne() {
			
		menuGlowne = new Menu();	
		menuGlowne.dodajPole("Pokaz wszystkich pracownikow");
		menuGlowne.dodajPole("Dodaj nowego pracownika");
		menuGlowne.dodajPole("Eksport do pliku tekstowego");
		menuGlowne.dodajPole("Usuwanie pracownika");
		menuGlowne.dodajPole("Edycja danych");
		menuGlowne.dodajPole("Dodatkowe funkcje");
		menuGlowne.dodajPole("Funkcje dla plikow tekstowych");
		menuGlowne.dodajPole("Informacje o programie");
		menuGlowne.dodajPole("Nowa nazwa pliku danych");
		menuGlowne.dodajPole("Koniec");
		}
		
		
		public void pokazWszystkichPracownikow() {
			
			listaPracownikow.stream().forEach( p -> System.out.println( p.pokazOkrojoneDane()));		
		}
		public void dodajNowegoPracownika() {
			if( listaPracownikow.size() < MAX_ILOSC_PRACOWNIKOW) {
				listaPracownikow.add(funkcjeEdytowania.nowyPracownik());}  
			else
			{
				System.out.println("Maksymalna ilosc pracownikow to:" + MAX_ILOSC_PRACOWNIKOW);
			}
		}
		public void eksportDoPlikuTekstowego() {
			System.out.println("Zapisuje baze danych do pliku tekstowego: " + nazwaPlikuTekstowego);
			plik.eksportDoPlikuTekstowego(nazwaPlikuTekstowego, listaPracownikow);
			
		}
		public void usuwaniePracownika() {
			
			Menu menuUsunPracownika = new Menu();
			for( Pracownik pracownik : listaPracownikow) {
				menuUsunPracownika.dodajPole(pracownik.pokazOkrojoneDane());}
			int indeks = menuUsunPracownika.zwrocWyborLiczby();
			if(indeks != REZYGNUJ) {
			listaPracownikow.remove(indeks -1 );
			System.out.println("Pracownik zostal usuniety");
			}
		}
		
		public void edycjaDanych() {
			
			Menu menuPracownikDoEdycji = new Menu();
			for( Pracownik pracownik : listaPracownikow) {
				menuPracownikDoEdycji.dodajPole(pracownik.pokazOkrojoneDane());}
			int indeks = menuPracownikDoEdycji.zwrocWyborLiczby();
			if(indeks != REZYGNUJ) {
				 menuEdytowaniaPracownika( indeks -1);}	
		}
		public void dodatkoweFunkcje() {	
			  // poniewaz menu dodatkow pracuje na pliku tekstowym 
			  plik.eksportDoPlikuTekstowego(nazwaPlikuTekstowego, listaPracownikow);
			  menuDodatkoweFunkcje();
		}
		
		public void funkcjeDlaPlikowTekstowych() {
			// poniewaz menu plikow pracuje na pliku tekstowym 
			  plik.eksportDoPlikuTekstowego(nazwaPlikuTekstowego, listaPracownikow);
			  menuFunkcjeDlaPlikow();
		}
		public void informacjeOProgramie() {	
			System.out.println("Program \"Firma\" wersja 0.1");
		}
		public void nowaNazwaPlikuDanych() {	
			System.out.println("Podaj nowa nazwe dla bazy danych:");
			String string = Klawiatura.wczytajTekst();
			nazwaBazyDanych = string +  ".dat";
			plik.zapiszPlikInfo(nazwaBazyDanych);
		
		}
		public void koniec() {
			System.out.println("Zapisuje liste pracownikow do bazy danych:" + nazwaBazyDanych);
			plik.eksportDoBazyDanych(nazwaBazyDanych, listaPracownikow);
		
		}
	
	}

	
	
	
	
	
	
	
}
