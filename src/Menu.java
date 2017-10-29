import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Menu {
	
	List<String> tekstMenu;
	// ILOSC_WIERSZY_EKRANU jest potrzebna do dzielenia wiekszej ilosci danych na porcje
	// pasujace do podanej wielkosci ekranu konsoli
	private int ILOSC_WIERSZY_EKRANU = 10;
	
	public Menu() {
		tekstMenu = new ArrayList<>();
	}
	
	public void dodajPole(String pole) {	
		tekstMenu.add(pole);
	}
	
	public int zwrocWyborLiczby()
	{
		return zwrocWybor(true);
	}
	
	public int zwrocWyborLitery()
	{
		return zwrocWybor(false);
	}
	public void pokazPole( int index) {
		if(index >= 0 && index < tekstMenu.size()) {
		System.out.println(tekstMenu.get(index).toUpperCase() + ":"); }	
	}
	
	private int zwrocWybor( boolean czyLiczby ) {
		int iloscWierszyEkranu = this.ILOSC_WIERSZY_EKRANU;	
		boolean gotowe = false;
		String liczba;
		int wynik = 0;
		Scanner sc = new Scanner(System.in);
		do{
			int index = 0;
		   // czesc sterujaca przewijaniem
			for( String pole : tekstMenu) {	
				index++;
				if(index > iloscWierszyEkranu )  {
					this.czekajNaEnter();
					iloscWierszyEkranu += ILOSC_WIERSZY_EKRANU;
				}
				if(czyLiczby) {
					System.out.println(index + ")" + " " + pole );}
				else {
					System.out.println((char)(96+index) + ")" + " " + pole );}
			}
		
			// czesc obslugujaca wybor uzytkownika i zwracajaca wynik 
			System.out.println("Wybierz opcje i nacisnij <enter> lub nacisnij tylko <enter> zeby zrezygnowac:");
			
			liczba = sc.nextLine();
			try {
				if(liczba.isEmpty()) {
					gotowe = true;
					wynik = 997;
				}
				else {
					if(czyLiczby) {
						wynik = Integer.parseInt(liczba);	
						if(wynik <= 0 || wynik > tekstMenu.size()) {
							throw new IllegalArgumentException();
						}
						gotowe = true;}
					else {
						wynik = (int) liczba.charAt(0);
						if( wynik < 97 || wynik > 122) {
							throw new IllegalArgumentException();
						}
						wynik-=96;
						gotowe = true;
					}
				}
			} catch( Exception ex) {
				System.out.println("To nie byla wlasciwa opcja!");
				System.out.println("Sprobuj jeszcze raz:");
    	
			}
		}
		while( gotowe == false); 
		// sc.close(); 
	
	
		return wynik;
		}
	
	private void czekajNaEnter() {
		Scanner sc = new Scanner(System.in);
		System.out.println("------------> Nacisnij  klawisz <enter> zeby zobaczyc dalej <-------------");
		while(!sc.hasNextLine()) {
		}
	}
	
	}
