import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;

public class Metodi {
    public static String dammiStringa(Integer intero) {
        if (intero < 0) throw new IllegalArgumentException("Valore negativo");
        StringBuilder stringa = new StringBuilder();
        for(int i = 0; i < 16 - intero.toString().length(); i++, stringa.append("0"));
        stringa.append(intero);
        return stringa.toString();
    }

    public static byte[] dammiChiave(Integer intero) {
        return dammiStringa(intero).getBytes();
    }

    public static void bruteForce(int nThread, File input, String indizio) throws Exception{
        Instant start = Instant.now();
        byte[] letturaOriginale = leggiFile(input);
        if(nThread <= 0) throw new IllegalArgumentException("Numero thread non valido!");
        if(input == null) throw new IllegalArgumentException("File == null!");
        BForcer[] bForcers = new BForcer[nThread];
        int passo = Integer.MAX_VALUE / nThread;
        int resto = Integer.MAX_VALUE % nThread;
        int conto = 0;
        for (int i = 0; i < nThread; i++) {
            if (i == nThread-1) bForcers[i] = new BForcer(letturaOriginale, conto, conto + passo + resto, i, indizio.getBytes(), bForcers);
            else {bForcers[i] = new BForcer(letturaOriginale, conto, conto + passo, i, indizio.getBytes(), bForcers);
            conto += passo;}
            bForcers[i].start();
        }
        for (int i = 0; i < nThread; i++) bForcers[i].join();
        Instant end = Instant.now();
        System.out.println("Tempo impiegato: " + Duration.between(start, end).toMinutes() + " minuti");
    }

    public static boolean contenutoValido(byte[] daVerificare, byte[] obiettivo) {
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

    public static byte[] leggiFile(File input) throws IOException {
        FileInputStream fIS = new FileInputStream(input);
        byte[] ret = new byte[(int) input.length()];
        fIS.read(ret);
        fIS.close();
        return ret;
    }

    public static void scriviFile(String pathNome, byte[] outputArray) throws Exception {
        File file = new File(pathNome);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(outputArray);
        outputStream.close();
    }

    public static byte[] decifra(byte[] chiave, byte[] input) {
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
