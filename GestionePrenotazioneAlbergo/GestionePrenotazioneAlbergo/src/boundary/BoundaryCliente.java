package boundary;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import control.GestionePrenotazioneAlbergoController;
import exception.OperationException;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {		
	boolean exit = false;
		
		while(!exit) {
			System.out.println("Cliente");
			System.out.println("1. Cerca disponibilità");
			System.out.println("2. Esci");
			
			String op = scan.nextLine();

			if(op.equals("1")){
				ricercaDisponibilità();
				
			}else if(op.equals("2")){
				exit = true;
				
			}else{
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}
	}
	
	public static void ricercaDisponibilità()  {
		
		String citta=null;
		Date data_arrivo=null;
		Date data_partenza=null;
		int tipologia=0;
		boolean inputValido = false;
		ArrayList<String> ris=null;
		
		System.out.println("Inserisci città, data di arrivo, data di partenza e tipologia della camera");
		
		while (!inputValido) {
			try {
				System.out.println("Inserisci la città");
				citta = scan.nextLine();
				
				if(citta.length()<=50)
				inputValido = true;
				
				else System.out.println("Formato non valido, riprovare..");
				if (Pattern.matches("[a-zA-Z]+",citta))
					inputValido = true;
				
				else { System.out.println("Formato non valido, riprovare..");
					inputValido=false;
				}
			}catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}
		
		inputValido=false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci la data di arrivo (aaaa-MM-gg)");
				String dataTemp = scan.nextLine();
				data_arrivo=Date.valueOf(dataTemp);
				inputValido = true;
				
			}catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}	
		
		inputValido=false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci la data di partenza (aaaa-MM-gg)");
				String dataTemp = scan.nextLine();
				data_partenza=Date.valueOf(dataTemp);
				inputValido = true;
				
			} catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}
		
		inputValido=false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci la tipologia della camera");
				tipologia = Integer.parseInt(scan.nextLine());
				if(tipologia>=1 && tipologia<=3)
					inputValido = true;
				
				else System.out.println("Formato non valido");	
			} catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}
		
		try {
			ris=GestionePrenotazioneAlbergoController.ricercaDisponibilità(citta, data_arrivo,data_partenza,tipologia);
			if(ris.isEmpty()) {
			System.out.println("Non ci sono camere disponibili");
			
			}else {
				System.out.println("Risultati della ricerca\n");
				System.out.println("N camera\tTipologia\tPrezzo\t");
				for(int i=0;i<ris.size();i++) {
					if(i%3==0 && i!=0) {
						System.out.println("\n");
					}
					System.out.print(ris.get(i));
					System.out.print("\t\t");
				}
				
				System.out.println("\n");
		
				System.out.println("Digita 's' per inserire i dati e proseguire con la prenotazione..");
				String conferma = scan.nextLine();

				if (!conferma.equals("s") ) {
					System.out.println("Operazione annullata..");
					System.out.println();
					return;
				}
		
				inserisciDati(data_arrivo,data_partenza);
				}
		}
		catch (OperationException e) {
			System.out.println(e.getMessage());
		}
}
	
	public static void inserisciDati(Date data_arrivo, Date data_partenza)  {
		String nome=null;
		String cognome=null;
		String email=null;
		String telefono=null;
		String indirizzo=null;
	    int civico=0;
		String carta_dicredito=null;
		boolean inputValido = false;
		
		System.out.println("Inserisci nome, cognome, email, telefono, indirizzo e carta di credito");
		
		while (!inputValido) {
			try {
				System.out.println("Inserisci il nome");
				nome = scan.nextLine();
				
				if(nome.length()<=15)
					inputValido = true;
				
				else System.out.println("Formato non valido, riprovare..");
				
				if (Pattern.matches("[a-zA-Z]+",nome))
					inputValido = true;
				
				else System.out.println("Formato non valido, riprovare..");
			} catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}	
		
		inputValido=false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci il cognome");
				cognome = scan.nextLine();
				
				if(cognome.length()<=15)
					inputValido = true;
				
				else System.out.println("Formato non valido, riprovare..");
				
				if (Pattern.matches("[a-zA-Z]+",cognome))
					inputValido = true;
				else System.out.println("Formato non valido, riprovare..");
			} catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}	
		
		inputValido=false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci l'email");
				email = scan.nextLine();
				
				if (email.contains("@") && email.contains(".")) {
					inputValido = true;
					
				} else {
					System.out.println("Formato non valido, riprovare..");
				}
			} catch (IllegalArgumentException iE) {
				System.out.println("Formato non valido, riprovare..");
				System.out.println();
			}
		}	
		
		inputValido = false;
		while (!inputValido) {
			try {
				System.out.println("Inserisci il numero di telefono");
				telefono = scan.nextLine();
				Long.parseLong(telefono);

				if (telefono.length() == 10) {
					inputValido = true;
				} else {
					System.out.println("Formato non valido");
				}
				} catch (NumberFormatException e) {
					System.out.println("Formato non valido");
				}
		}
		
		inputValido=false;
		while(!inputValido) {
			try {
			System.out.println("Inserisci l'indirizzo");
			indirizzo = scan.nextLine();
			
			if(indirizzo.length()<=30)
				inputValido = true;
			else {
				System.out.println("Formato non valido, riprovare..");
			}
			
			System.out.println("Inserisci il civico");
			civico = Integer.parseInt(scan.nextLine());
			if(civico>=000 && civico<=999)
				inputValido = true;
			else {
				System.out.println("Formato non valido, riprovare..");
			}
			}catch (NumberFormatException nE) {
				System.out.println("Errore, inserire un numero valido");
				System.out.println();
			}
		}
		
		inputValido = false;
		while (!inputValido) {
			System.out.println("Inserire il numero di carta:");
			carta_dicredito= scan.nextLine();

			try {
				Long.parseLong(carta_dicredito);

				if (carta_dicredito.length() == 16) {
					inputValido = true;
				} else {
					System.out.println("Formato non valido, riprovare...");
				}
			} catch (NumberFormatException e) {
				System.out.println("Formato non valido, riprovare...");
			}
		}
		
		try {
			int dati=GestionePrenotazioneAlbergoController.inserisciDati(email,nome, cognome,  telefono, indirizzo, civico, carta_dicredito);
			
			if(dati==1) System.out.println("Cliente inserito!");
			
			else if(dati==-1) System.out.println("Il cliente è già presente nel database.");
		
			System.out.println("Digita 's' per proseguire con la prenotazione..");
			String conferma2 = scan.nextLine();

			if (!conferma2.equals("s") ) {
				System.out.println("Operazione annullata..");
				System.out.println();
				return;
			}
			
			prenotazione(data_arrivo,data_partenza,email);
		
		} catch (OperationException e) {
			System.out.println(e.getMessage());
		} 
	}

	public static void prenotazione(Date data_arrivo, Date data_partenza, String email) {
		int prenotazione=0;
		
		System.out.println("Quale camera intendi prenotare? ");
		int numcamera_scelta = scan.nextInt();
    
		try {
			prenotazione=GestionePrenotazioneAlbergoController.prenotazione(data_arrivo,data_partenza,email,numcamera_scelta);
			if(prenotazione==1) System.out.println("prenotazione effettuata"); 
		} catch (OperationException e) {
			System.out.println(e.getMessage());
		} 
	}
}