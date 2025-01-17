public class Sim {
    private String numero;
    private String operatore;
    private double credito;
    private double costoPerMinuto;
    private double costoSMS;

    // Costruttore
    public Sim(String numero, String operatore, double credito, double costoPerMinuto, double costoSMS) {
        this.numero = numero;
        this.operatore = operatore;
        this.credito = credito;
        this.costoPerMinuto = costoPerMinuto;
        this.costoSMS = costoSMS;
    }

    // Metodo per verificare il credito
    public double verificaCredito() {
        return credito;
    }

    // Metodo per ricaricare la SIM
    public void ricarica(double importo) {
        credito += importo;
    }

    // Metodo per scalare il credito
    public void scalaCredito(double costo) {
        if (credito >= costo) {
            credito -= costo;
        } else {
            System.out.println("Credito insufficiente.");
        }
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public String getOperatore() {
        return operatore;
    }

    public double getCredito() {
        return credito;
    }

    public double getCostoPerMinuto() {
        return costoPerMinuto;
    }

    public double getCostoSMS() {
        return costoSMS;
    }
}
