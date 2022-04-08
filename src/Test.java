import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws Exception{
        Metodi.bruteForce(4, new File("document2022.encrypted"), "SISOP-corsoB");
        //document.decryptedOK
    }
}
