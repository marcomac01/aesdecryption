import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Key;

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
                byte[] chiave = dammiChiave(i);
                outputArray = decifra(chiave, inputArray);
                if (contenutoValido(outputArray, indizioArray)) {
                    scriviFile("Decifrato.dec", outputArray);
                    System.out.println("La chiave trovata sembra essere "+ dammiStringa(i));
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

    static String dammiStringa(Integer intero) {
        if (intero < 0) throw new IllegalArgumentException("Valore negativo");
        StringBuilder stringa = new StringBuilder();
        for(int i = 0; i < 16 - intero.toString().length(); i++, stringa.append("0"));
        stringa.append(intero);
        return stringa.toString();
    }
    static byte[] dammiChiave(Integer intero) {
        return dammiStringa(intero).getBytes();
    }
    static boolean contenutoValido(byte[] daVerificare, byte[] obiettivo) {
        if (daVerificare.length < obiettivo.length) throw new IllegalArgumentException("Arrays in input non validi o invertiti");
        int indiceObiettivo = 0;
        for (int i = 0; i < daVerificare.length; i++) {
            if (daVerificare[i] != obiettivo[indiceObiettivo]) {
                indiceObiettivo = 0;
            } else {
                indiceObiettivo++;
            }
            if (indiceObiettivo == obiettivo.length) return true;
        }
        return false;
    }

    static void scriviFile(String pathNome, byte[] outputArray) throws Exception {
        File file = new File(pathNome);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(outputArray);
        outputStream.close();
    }

    static byte[] decifra(byte[] chiave, byte[] input) {
        try {
            Key chiaveSegreta = new SecretKeySpec(chiave, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chiaveSegreta);
            return cipher.doFinal(input);
            } catch (Exception e) {
        }
        return null;
    }

}
