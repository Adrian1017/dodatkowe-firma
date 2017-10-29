import java.util.Scanner;

public final class  Klawiatura {
	
static public int  wczytajInt() {	
		Scanner sc = new Scanner(System.in);
		boolean dalej = true;
		int liczba = 0;
		do {
			if(sc.hasNextInt()) {			
				liczba = sc.nextInt();
				dalej = false;}	
			else
			{
				System.out.println("Blad.Podaj liczbe!");
				sc.nextLine();}  		
		   }
		while(dalej);		
	   //sc.close();
		return liczba;		
	}	

static public float  wczytajFloat() {	
		Scanner sc = new Scanner(System.in);
		boolean dalej = true;
		float liczba = 0;
		do {
			if(sc.hasNextFloat()) {			
				liczba =  sc.nextFloat();
				dalej = false;}	
			else
			{
		      System.out.println("Blad.Podaj liczbe!");
		      sc.nextLine();}		
		   }
		while(dalej);		
		// sc.close();
		return liczba;	
	}

static public String  wczytajTekst() {
		Scanner sc = new Scanner(System.in);
		boolean dalej = false;
		String tekst = "";
		do {  
			if(sc.hasNext()) {	
				tekst = sc.nextLine();
				if(  tekst.matches("\\A\\D*\\Z") == true ) {
					dalej = false;}
				else {
					System.out.println("Tylko litery!");}
				}
		}
		while(dalej);
		// sc.close();
		return tekst;
	}

}