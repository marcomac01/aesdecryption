import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import crypto.CryptoUtils;

public class BForcer extends Thread{
    private int inizio, fine, id;
    File input;
    String indizio;
    BForcer[] bForces;

    public BForcer(File input, int inizio, int fine, int id, String indizio, BForcer[] bForces) {
        super();
        this.input = input;
        this.inizio = inizio;
        this.fine = fine;
        this.id = id;
        this.indizio = indizio;
        this.bForces = bForces;
    }

    @Override
    public void run() {

        System.out.println("comincio thread "+ id + " da "+inizio+" a "+fine);
        for (int i = inizio; i < fine; i++) {
            String chiave = Metodi.dammiStringa(i);
            try {
                String outputName = "forced" + id + ".possible";
                CryptoUtils.decrypt(chiave, input, new File(outputName));
                BufferedReader br = new BufferedReader(new FileReader(outputName));
                String s = "";
                while (s != null) {
                    s = br.readLine();
                    if(s.contains(indizio)) {
                        System.out.println(" la chiave corretta è" + chiave + " il file decifrato è " + outputName);
                        interrompiTutti();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    private void interrompiTutti() {
        for(BForcer bf : bForces) if(bf != this) bf.interrupt();
        this.interrupt();
    }
}
