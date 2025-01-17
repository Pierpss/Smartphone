import java.util.ArrayList;
import java.util.List;

public class Smartphone {
    private String modello;
    private String marca;
    private Sim sim;
    private List<Contatto> contatti;
    private List<Chiamata> registroChiamate;

    // Costruttore
    public Smartphone(String modello, String marca, Sim sim) {
        this.modello = modello;
        this.marca = marca;
        this.sim = sim;
        this.contatti = new ArrayList<>();
        this.registroChiamate = new ArrayList<>();
    }

    // Metodo per aggiungere un contatto
    public void aggiungiContatto(String nome, String numero) {
        contatti.add(new Contatto(nome, numero));
    }

    // Metodo per verificare se un contatto esiste
    public boolean esisteContatto(String nome) {
        for (Contatto contatto : contatti) {
            if (contatto.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    // Metodo per modificare un contatto
    public void modificaContatto(String nomeContatto, String nuovoNome, String nuovoNumero) {
        for (Contatto contatto : contatti) {
            if (contatto.getNome().equals(nomeContatto)) {
                contatto.setNome(nuovoNome);
                contatto.setNumero(nuovoNumero);
                return;
            }
        }
        System.out.println("Contatto non trovato.");
    }

    // Metodo per visualizzare i contatti
    public List<Contatto> visualizzaContatti() {
        return contatti;
    }

    // Metodo per effettuare una chiamata
    public void effettuaChiamata(String numero, int durata) {
        double costoChiamata = durata * sim.getCostoPerMinuto();
        if (sim.verificaCredito() >= costoChiamata) {
            sim.scalaCredito(costoChiamata);
            registroChiamate.add(new Chiamata(numero, durata));
            for (Contatto contatto : contatti) {
                if (contatto.getNumero().equals(numero)) {
                    System.out.println("Chiamata effettuata a: " + contatto.getNome());
                    return;
                }
            }
            System.out.println("Chiamata effettuata al numero: " + numero);
        } else {
            System.out.println("Credito insufficiente per effettuare la chiamata.");
        }
    }

    // Metodo per inviare un SMS
    public void inviaSMS(String numero) {
        if (sim.verificaCredito() >= sim.getCostoSMS()) {
            sim.scalaCredito(sim.getCostoSMS());
            for (Contatto contatto : contatti) {
                if (contatto.getNumero().equals(numero)) {
                    return;
                }
            }
        } else {
            System.out.println("Credito insufficiente per inviare l'SMS.");
        }
    }

    // Metodo per scrivere un SMS
    public void scriviSMS(String numero, String messaggio) {
        if (sim.verificaCredito() >= sim.getCostoSMS()) {
            sim.scalaCredito(sim.getCostoSMS());
            for (Contatto contatto : contatti) {
                if (contatto.getNumero().equals(numero)) {
                    System.out.println("SMS inviato con successo a: " + contatto.getNome() + " - " + messaggio);
                    return;
                }
            }
        } else {
            System.out.println("Credito insufficiente per inviare l'SMS.");
        }
    }

    // Metodo per visualizzare il registro delle chiamate
    public List<Chiamata> visualizzaRegistroChiamate() {
        return registroChiamate;
    }

    // Metodo per cambiare la SIM
    public void cambiaSIM(Sim nuovaSim) {
        this.sim = nuovaSim;
    }
}
