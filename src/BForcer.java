import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import crypto.CryptoUtils;
import crypto.CryptoUtilsRework;

public class BForcer extends Thread{
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
        System.out.println("comincio thread "+ id + " da "+inizio+" a "+fine);
        for (int i = inizio; i < fine; i++) {
            try {
                byte[] chiave = Metodi.dammiChiave(i);
                CryptoUtilsRework.decrypt(chiave, inputArray, outputArray);
                if (Metodi.contenutoValido(outputArray, indizioArray)) {
                    File fine = new File("fine.dec");
                    FileOutputStream outputStream = new FileOutputStream(fine);
                    outputStream.write(outputArray);
                    outputStream.close();
                    System.out.println("la chiave trovata dal thread "+id+" è "+ Metodi.dammiStringa(i)+" e il file in output è fine.dec");
                    interrompiTutti();
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
