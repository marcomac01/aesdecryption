import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws Exception{
        //Metodi.bruteForce(12, new File("document2022.encrypted"), "SISOP-corsoB");
        //document.decryptedOK
        File f = new File("document.decryptedOK");
        FileInputStream inputStream = new FileInputStream(f);
        byte[] inputBytes = new byte[(int) f.length()];
        inputStream.read(inputBytes);
        System.out.println(Arrays.toString(inputBytes));
        String s = "SISOP-corsoB";
        byte[] sb = s.getBytes(StandardCharsets.UTF_8);

    }
}
