class BForcer extends Thread{
    private final int inizio, fine, id;
    private BForcer[] bForces;
    private byte[]  outputArray;
    private final byte[] inputArray, indizioArray;

    public BForcer(byte[] inputArray, int inizio, int fine, int id, byte[] indizioArray, BForcer[] bForces) {
        super();
        this.inputArray = inputArray;
        this.inizio = inizio;
        this.fine = fine;
        this.id = id;
        this.indizioArray = indizioArray;
        this.bForces = bForces;
    }

    @Override
    public void run() {
        System.out.println("Il thread "+ id + " comincia da "+inizio+" a "+fine);
        for (int i = inizio; i < fine && !this.isInterrupted(); i++) {
            try {
                byte[] chiave = Metodi.dammiChiave(i);
                outputArray = Metodi.decifra(chiave, inputArray);
                if (Metodi.contenutoValido(outputArray, indizioArray)) {
                    Metodi.scriviFile("Decifrato.dec", outputArray);
                    System.out.println("La chiave trovata sembra essere "+ Metodi.dammiStringa(i));
                    System.out.println("Il file in output ha nome Decifrato.dec");
                    interrompiTuttiNotThis();
                    break;
                }
            } catch (Exception e) {
            }
        }
    }

    private void interrompiTuttiNotThis() {
        for(int i = 0; i < bForces.length; i++) if(i != this.id) bForces[i].interrupt();
    }
}
