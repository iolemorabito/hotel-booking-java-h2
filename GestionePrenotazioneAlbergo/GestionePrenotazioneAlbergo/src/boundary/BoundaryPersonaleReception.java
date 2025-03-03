package boundary;

import java.util.Scanner;

import control.GestionePrenotazioneAlbergoController;
import exception.OperationException;

public class BoundaryPersonaleReception {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {		
		boolean exit = false;
		
		while(!exit) {
			System.out.println("Personale della reception");
			System.out.println("1. Effettua check-in");
			System.out.println("2. Effettua check-out");
			System.out.println("3. Esci");
			
			String op = scan.nextLine();

			if(op.equals("1")) {
				
					checkIn();
				
			} else if(op.equals("3")){
				exit = true;
			}else{
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}	
		
		System.out.println("Ciao.");
		
	}
	
	public static void checkIn() {
		int id_prenotazione=0;
		int ok=0;
		GestionePrenotazioneAlbergoController gestioneP= GestionePrenotazioneAlbergoController.getInstance();
		boolean inputValido = false;
		
		while (!inputValido) {
			try {
				System.out.println("Inserisci l'id della prenotazione");
				id_prenotazione = Integer.parseInt(scan.nextLine());
				
				if(id_prenotazione>=10000 && id_prenotazione<=99999) {
				inputValido = true;
				}
				else System.out.println("Formato non valido, riprova");
				
			}catch (NumberFormatException nE) {
				System.out.println("Errore, l'id della prenotazione non è valido, riprova");
				System.out.println();
			}
			
			try { 
			ok=gestioneP.checkIn(id_prenotazione);
			
			if(ok==1) {
				System.out.println("Check-in effettuato.");
			}else	System.out.println("Check-in non effettuato.");
			
			}catch (OperationException oE) {
				System.out.println(oE.getMessage());
				System.out.println("Riprovare..\n");
			}
		}
	} 
}
