import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Metodi {
    public static String dammiStringa(Integer intero) {
        if (intero < 0) throw new IllegalArgumentException("Valore negativo");
        StringBuilder stringa = new StringBuilder();
        for(int i = 0; i< 16 - intero.toString().length(); i++, stringa.append("0"));
        stringa.append(intero);
        return stringa.toString();
    }


    public static void bruteForce(int nThread, File input, String indizio) throws Exception{
        Instant start = Instant.now();
        if(nThread <= 0) throw new IllegalArgumentException("Numero thread non valido!");
        if(input == null) throw new IllegalArgumentException("File == null!");
        BForcer[] bForcers = new BForcer[nThread];
        int passo = Integer.MAX_VALUE / nThread;
        int resto = Integer.MAX_VALUE % nThread;
        int conto = 0;
        for (int i = 0; i < nThread; i++) {
            if (i == nThread-1) bForcers[i] = new BForcer(input, conto, conto + passo + resto, i, indizio, bForcers);
            else {bForcers[i] = new BForcer(input, conto, conto + passo, i, indizio, bForcers);
            conto += passo;}
            bForcers[i].start();
        }
        for (int i = 0; i < nThread; i++) bForcers[i].join();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMinutes());
    }
}
