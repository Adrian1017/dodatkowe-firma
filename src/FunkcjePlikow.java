import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

class FunkcjePlikow {
	
	public Menu menuFunkcjePlikow;	
	
	public FunkcjePlikow() {		
		menuFunkcjePlikow = new Menu();
		menuFunkcjePlikow.dodajPole("Pracownik o najdluzszym nazwisku");
		menuFunkcjePlikow.dodajPole("Srednia wieku osob posiadajacych dzieci");
		menuFunkcjePlikow.dodajPole("Zakodowanie nazwiska osob o najnizszej pensji");
		menuFunkcjePlikow.dodajPole("Utworzenie pliku html");
		menuFunkcjePlikow.dodajPole("Powrot do glownego menu");		
	}
	
	public void pokazNajdluzszeNazwisko( String nazwa) {
		
		ObslugaPliku plik = new ObslugaPliku();
	    List<Pracownik> listaNajdluzszeNazwisko = plik.importZPlikuTekstowego(nazwa);
	    
		Pracownik maxNazwisko = listaNajdluzszeNazwisko.get(0);			
		for(int i = 1; i < listaNajdluzszeNazwisko.size(); i++) {
			
			if(listaNajdluzszeNazwisko.get(i).getNazwisko().length() > maxNazwisko.getNazwisko().length()) {
				maxNazwisko = listaNajdluzszeNazwisko.get(i);	
			}
		}
		System.out.println(maxNazwisko.getNazwisko());
		
	}
	
	public void pokazSredniWiekZDziecmi( String nazwa) {
		
		ObslugaPliku plik = new ObslugaPliku();
	    List<Pracownik> listaSredniWiekDzieci = plik.importZPlikuTekstowego(nazwa);
	    
	    int indeks = 0;
		float sumaWieku = 0;
		for(Pracownik pracownik : listaSredniWiekDzieci) {
			if(pracownik.getLiczbaDzieci() > 0) {
				sumaWieku+=pracownik.getWiek();
				indeks++;
			}
		}
		if(indeks > 0) {
			System.out.println(( Math.round( ( sumaWieku/indeks)*100.00)/ 100.00));}
		else {
			System.out.println("Brak pracownikow z dziecmi");
		}
		
	}
	
	private String kodowanie( String nazwisko) {
		
		String zakodowaneNazwisko;
		String gwiazdki ="";
		
		for( int i = 0; i < nazwisko.length()-2; i++) {
			
		gwiazdki = gwiazdki + "*";
		}
		
		zakodowaneNazwisko = nazwisko.charAt(0) + gwiazdki + nazwisko.charAt(nazwisko.length()-1);	
		
		return zakodowaneNazwisko;
		
	}
	public void kodowanieNazwiska( String nazwa) {
		
		ObslugaPliku plik = new ObslugaPliku();
	    List<Pracownik> listaPracownikow = plik.importZPlikuTekstowego(nazwa);
	    float sumaPlac = 0;
	    for(Pracownik pracownik : listaPracownikow) {
	    	sumaPlac+= pracownik.getPlaca();
	    }
	    float sredniaPlac = sumaPlac/listaPracownikow.size();
	    
	    List<Pracownik> listaPracownikoPonizejSredniej = new ArrayList<>();
		for(Pracownik pracownik :listaPracownikow) {
			if(pracownik.getPlaca() < sredniaPlac) {
				listaPracownikoPonizejSredniej.add(pracownik);
			}
		}
	    
	    
	    
      for( Pracownik miniPlaca: listaPracownikoPonizejSredniej) { 	
    	  	  
    	  Pracownik zakodowanyPracownik = new Pracownik(miniPlaca.getImie(), this.kodowanie(miniPlaca.getNazwisko()), miniPlaca.getPlec(),
    				miniPlaca.getNrDzialu(),miniPlaca.getPlaca(),  miniPlaca.getWiek(), miniPlaca.getLiczbaDzieci(), miniPlaca.getStanCywilny());	
    	  System.out.println(zakodowanyPracownik.getImie() + " " + zakodowanyPracownik.getNazwisko());
       	  listaPracownikow.remove(miniPlaca);	
    	  listaPracownikow.add(zakodowanyPracownik);
    	  
      }
      plik.eksportDoPlikuTekstowego(nazwa, listaPracownikow);
	}
	
	public void tabelaHTML(String nazwa) {
		
		ObslugaPliku plik = new ObslugaPliku();
		List<Pracownik> listaDoHtml = plik.importZPlikuTekstowego(nazwa);
	
		String aktualnyKatalog = System.getProperty("user.dir");
		
		String htmlStart = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"    <head>" + "<meta charset=\"utf-8\">\r\n" + 
				"        <title>Lista pracownikow</title>" +
				" </head>\r\n" + 
				"    <body>\r\n" + " </body>\r\n" + 
				"</html>";
		String koniecLini ="<br />";
		String fontStart ="<font size=\"4\">";
		String fontEnd ="</font>";
		String htmlEnd =" </body>\r\n" + 
				"</html>";
		Path nazwaPliku = Paths.get(aktualnyKatalog, "Lista_pracownikow" + ".html");
		try(BufferedWriter bw = 
				Files.newBufferedWriter(nazwaPliku, StandardOpenOption.CREATE)){
			
				bw.write(htmlStart);
				bw.write(fontStart); 
				for( Pracownik pracownik : listaDoHtml) {
					bw.write("Nazwisko:");
					bw.write(pracownik.getNazwisko() + " ");
					bw.write(koniecLini);
					bw.write("Imie:");
					bw.write(pracownik.getImie() + " ");
					bw.write(koniecLini);
					bw.write("Plec:");
					bw.write(pracownik.getPlec() + " ");
					bw.write(koniecLini);
					bw.write("Numer dzialu:");
					bw.write(pracownik.getNrDzialu() + " ");
					bw.write(koniecLini);
					bw.write("Placa:");
					bw.write( Float.toString(pracownik.getPlaca()) + " ");
					bw.write("PLN");
					bw.write(koniecLini);
					bw.write("Wiek:");
					bw.write(pracownik.getWiek() + " ");
					bw.write(koniecLini);
					bw.write("Liczba dzieci:");
					bw.write(pracownik.getLiczbaDzieci() + " ");
					bw.write(koniecLini);
					bw.write("Stan cywilny:");
					bw.write(pracownik.pokazStanCywilny());
					bw.write(koniecLini); 
					bw.write("--------------------------------------------");
					bw.write(koniecLini); 
				}
				bw.write(fontEnd);
				bw.write(htmlEnd);
				bw.close();
				System.out.println("Plik 'Lista_pracownikow.html' zostal utworzony!");  
		}catch(IOException ex) {
			ex.printStackTrace();
		}		
	}
	
}