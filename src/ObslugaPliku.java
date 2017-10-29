import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

class ObslugaPliku {
	public void eksportDoPlikuTekstowego(String nazwa, List<Pracownik> listaPracownikow) {
				
		String aktualnyKatalog = System.getProperty("user.dir");
		Path nazwaPliku = Paths.get(aktualnyKatalog, nazwa + ".txt");
		try(BufferedWriter bw = 
				Files.newBufferedWriter(nazwaPliku, StandardOpenOption.CREATE)){
			for( Pracownik pracownik : listaPracownikow) {
				bw.write(pracownik.getNazwisko() + " ");
				bw.write(pracownik.getImie() + " ");
				bw.write(pracownik.getPlec() + " ");
				bw.write(pracownik.getNrDzialu() + " ");
				bw.write( Float.toString(pracownik.getPlaca()) + " ");
				bw.write(pracownik.getWiek() + " ");
				bw.write(pracownik.getLiczbaDzieci() + " ");
				bw.write(Boolean.toString(pracownik.getStanCywilny()) +"\n");
			}
			bw.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
		  
		  public  List<Pracownik> importZPlikuTekstowego( String nazwa) {			
			  List<Pracownik> listaPracownikow = new ArrayList<>();
			  String wiersz;
			  String aktualnyKatalog = System.getProperty("user.dir");
			  Path nazwaPliku = Paths.get(aktualnyKatalog, nazwa +".txt");
			  try(BufferedReader br = 
						Files.newBufferedReader(nazwaPliku)){			    
						while( (wiersz = br.readLine()) != null) {					
							  String [] doPodzialu = wiersz.split(" ");		
							  if(doPodzialu.length == 8) { 
								  Pracownik pracownik = new Pracownik( doPodzialu[1], doPodzialu[0], 
										  doPodzialu[2].charAt(0),
										  Integer.parseInt(doPodzialu[3]),  Float.parseFloat(doPodzialu[4]), 
										  Integer.parseInt(doPodzialu[5]),  Integer.parseInt(doPodzialu[6]), 
										  Boolean.parseBoolean(doPodzialu[7]));
								  listaPracownikow.add(pracownik);		
							  }
					    }
					    br.close();
			  }catch(IOException ex) {
					ex.printStackTrace();
				}
			  
			  return listaPracownikow;
		  }
		 		
		
	public  void eksportDoBazyDanych(String nazwaPelnaPliku, List<Pracownik> listaPracownikowDoWyslania ) {

			String aktualnyKatalog = System.getProperty("user.dir");
			Path nazwaPliku = Paths.get(aktualnyKatalog, nazwaPelnaPliku);
		
			try{  
				FileOutputStream saveFile=new FileOutputStream( nazwaPliku.toString() );
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(listaPracownikowDoWyslania);		
				save.close(); 
				saveFile.close();
			}
			catch(IOException exc){
				exc.printStackTrace(); 
			}	
	}
	
	
	public   List<Pracownik> importZBazyDanychDoListy( String nazwaPelnaPliku)  {	
		
			List<Pracownik> listaPracownikow = new ArrayList<>();	
			String aktualnyKatalog = System.getProperty("user.dir");
			Path nazwaPliku = Paths.get(aktualnyKatalog, nazwaPelnaPliku);	
			try{  
				FileInputStream loadFile=new FileInputStream( nazwaPliku.toString() );
				ObjectInputStream load = new ObjectInputStream(loadFile);
				listaPracownikow = (List<Pracownik>) load.readObject();	
				load.close(); 
				loadFile.close();
			}
			catch( Exception exc){
				exc.printStackTrace(); 
			}	
			return listaPracownikow;	
		}
	
	public boolean czyIstnieje( String pelnaNazwaPliku) {
			String aktualnyKatalog = System.getProperty("user.dir");
			Path nazwaPliku = Paths.get(aktualnyKatalog, pelnaNazwaPliku);
			 File file = new File(nazwaPliku.toString());
			return  file.exists();
		}
		
	public  void zapiszPlikInfo(String nazwaBazy) {
			String aktualnyKatalog = System.getProperty("user.dir");
			Path nazwaPliku = Paths.get(aktualnyKatalog, "firma.info");
			try(BufferedWriter bw = 
					Files.newBufferedWriter(nazwaPliku, StandardOpenOption.CREATE)){
				bw.write(nazwaBazy + "\n");
				bw.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}		
	}
		
	public String ladujePlikInfo() {
			
			String aktualnaNazwaPliku = null;
			String aktualnyKatalog = System.getProperty("user.dir");
			Path nazwaPliku = Paths.get(aktualnyKatalog, "firma.info");
			try(BufferedReader br = 
					Files.newBufferedReader(nazwaPliku)){			    
				String wiersz;
				if( (wiersz = br.readLine()) != null  ) {	
					aktualnaNazwaPliku = wiersz;
				}
				br.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}		  
			return aktualnaNazwaPliku;
		}
	}