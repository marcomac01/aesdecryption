import java.io.File;

public class Test {
    public static void main(String[] args) throws Exception {
        Metodi.bruteForce(4, new File("document2022.encrypted"), "SISOP-corsoB");
        //Metodi.bruteForce(4,new File("document.encrypted"), "uffi");
        //document.decryptedOK
        /*byte[] chiave = Metodi.dammiChiave(1301770212);
        byte[] indizio = "SISOP-corsoB".getBytes();
        byte[] input = Metodi.leggiFile(new File("document2022.encrypted"));

        try {

            byte[] arr = Metodi.decifra("0000001301770212".getBytes(), input);
            File fine = new File("fine.dec");
            FileOutputStream outputStream = new FileOutputStream(fine);
            outputStream.write(arr);
            outputStream.close();
        } catch (Exception e) {

        }

*/
    }
}
