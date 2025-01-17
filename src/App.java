import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creazione di una SIM
        System.out.println("Inserisci il numero della SIM:");
        String numeroSIM = scanner.nextLine();
        System.out.println("Inserisci l'operatore della SIM:");
        String operatoreSIM = scanner.nextLine();
        System.out.println("Inserisci il credito iniziale della SIM:");
        double creditoSIM = scanner.nextDouble();
        System.out.println("Inserisci il costo per minuto di chiamata:");
        double costoPerMinuto = scanner.nextDouble();
        System.out.println("Inserisci il costo per SMS:");
        double costoSMS = scanner.nextDouble();

        Sim sim = new Sim(numeroSIM, operatoreSIM, creditoSIM, costoPerMinuto, costoSMS);

        // Creazione di uno smartphone con la SIM
        System.out.println("Inserisci il modello dello smartphone:");
        scanner.nextLine(); // Consumare la nuova riga
        String modelloSmartphone = scanner.nextLine();
        System.out.println("Inserisci la marca dello smartphone:");
        String marcaSmartphone = scanner.nextLine();

        Smartphone smartphone = new Smartphone(modelloSmartphone, marcaSmartphone, sim);

        boolean continua = true;

        while (continua) {
            System.out.println("\nScegli un'azione:");
            System.out.println("1. Aggiungi un contatto");
            System.out.println("2. Visualizza contatti");
            System.out.println("3. Effettua una chiamata");
            System.out.println("4. Invia un SMS");
            System.out.println("5. Visualizza registro chiamate");
            System.out.println("6. Verifica credito");
            System.out.println("7. Ricarica SIM");
            System.out.println("8. Cambia SIM");
            System.out.println("9. Modifica un contatto");
            System.out.println("10. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consumare la nuova riga

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il nome del contatto:");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        System.out.println("Errore: Il nome non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci il numero del contatto:");
                    String numero = scanner.nextLine();
                    if (numero.isEmpty()) {
                        System.out.println("Errore: Il numero non può essere vuoto.");
                        break;
                    }
                    smartphone.aggiungiContatto(nome, numero);
                    System.out.println("Contatto aggiunto con successo su " + modelloSmartphone + "nella sim " + operatoreSIM + numeroSIM + "!");
                    break;
                case 2:
                    System.out.println("Contatti:");
                    if (smartphone.visualizzaContatti().isEmpty()) {
                        System.out.println("Nessun contatto disponibile.");
                    } else {
                        for (Contatto contatto : smartphone.visualizzaContatti()) {
                            System.out.println(contatto.getNome() + ": " + contatto.getNumero());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Inserisci il numero da chiamare:");
                    String numeroChiamata = scanner.nextLine();
                    if (numeroChiamata.isEmpty()) {
                        System.out.println("Errore: Il numero non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci la durata della chiamata (in minuti):");
                    int durata;
                    try {
                        durata = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: La durata deve essere un numero intero.");
                        break;
                    }
                    smartphone.effettuaChiamata(numeroChiamata, durata);
                    break;
                case 4:
                    System.out.println("Inserisci il numero a cui inviare l'SMS:");
                    String numeroSMS = scanner.nextLine();
                    if (numeroSMS.isEmpty()) {
                        System.out.println("Errore: Il numero non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci il messaggio:");
                    String messaggio = scanner.nextLine();
                    if (messaggio.isEmpty()) {
                        System.out.println("Errore: Il messaggio non può essere vuoto.");
                        break;
                    }
                    smartphone.scriviSMS(numeroSMS, messaggio);
                    break;
                case 5:
                    System.out.println("Registro chiamate:");
                    if (smartphone.visualizzaRegistroChiamate().isEmpty()) {
                        System.out.println("Nessuna chiamata effettuata.");
                    } else {
                        for (Chiamata chiamata : smartphone.visualizzaRegistroChiamate()) {
                            System.out.println("Numero chiamato: " + chiamata.getNumeroChiamato() + ", Durata: " + chiamata.getDurata() + " minuti");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Credito residuo: " + sim.verificaCredito() + " euro");
                    break;
                case 7:
                    System.out.println("Inserisci l'importo della ricarica:");
                    double importo;
                    System.out.println("Credito residuo: " + sim.verificaCredito() + " euro");
                    try {
                        importo = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: L'importo deve essere un numero.");
                        break;
                    }
                    sim.ricarica(importo);
                    System.out.println("Credito attuale: " + sim.verificaCredito() + " €");
                    break;
                case 8:
                    System.out.println("Inserisci il nuovo numero della SIM:");
                    String nuovoNumeroSIM = scanner.nextLine();
                    if (nuovoNumeroSIM.isEmpty()) {
                        System.out.println("Errore: Il numero non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo operatore della SIM:");
                    String nuovoOperatoreSIM = scanner.nextLine();
                    if (nuovoOperatoreSIM.isEmpty()) {
                        System.out.println("Errore: L'operatore non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo credito iniziale della SIM:");
                    double nuovoCreditoSIM;
                    try {
                        nuovoCreditoSIM = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: Il credito deve essere un numero.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo costo per minuto di chiamata:");
                    double nuovoCostoPerMinuto;
                    try {
                        nuovoCostoPerMinuto = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: Il costo per minuto deve essere un numero.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo costo per SMS:");
                    double nuovoCostoSMS;
                    try {
                        nuovoCostoSMS = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: Il costo per SMS deve essere un numero.");
                        break;
                    }

                    Sim nuovaSim = new Sim(nuovoNumeroSIM, nuovoOperatoreSIM, nuovoCreditoSIM, nuovoCostoPerMinuto, nuovoCostoSMS);
                    smartphone.cambiaSIM(nuovaSim);
                    System.out.println("Nuova SIM " + operatoreSIM + " installata. Numero: " + nuovaSim.getNumero());
                    break;
                    case 9:
                    if (smartphone.visualizzaContatti().isEmpty()) {
                        System.out.println("Errore: Non ci sono contatti da modificare.");
                        break;
                    }
                    System.out.println("Inserisci il nome del contatto da modificare:");
                    String nomeContatto = scanner.nextLine();
                    if (!smartphone.esisteContatto(nomeContatto)) {
                        System.out.println("Errore: Contatto non trovato.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo nome del contatto:");
                    String nuovoNome = scanner.nextLine();
                    if (nuovoNome.isEmpty()) {
                        System.out.println("Errore: Il nome non può essere vuoto.");
                        break;
                    }
                    System.out.println("Inserisci il nuovo numero del contatto:");
                    String nuovoNumero = scanner.nextLine();
                    if (nuovoNumero.isEmpty()) {
                        System.out.println("Errore: Il numero non può essere vuoto.");
                        break;
                    }
                    smartphone.modificaContatto(nomeContatto, nuovoNome, nuovoNumero);
                    System.out.println("Contatto modificato con successo!");
                    break;
                
                case 10:
                    continua = false;
                    System.out.println("Uscita in corso... Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }

        scanner.close();
    }
}
