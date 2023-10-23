import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Metodi {




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



    static byte[] leggiFile(File input) throws IOException {
        FileInputStream fIS = new FileInputStream(input);
        byte[] ret = new byte[(int) input.length()];
        fIS.read(ret);
        fIS.close();
        return ret;
    }





}
