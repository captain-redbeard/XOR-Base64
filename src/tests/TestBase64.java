package tests;

import com.captainredbeard.xor.Base64;

/**
 * @author captain-redbeard
 * @version 1.00
 * @since 28/12/16
 */
public class TestBase64 {

    public static void main(String[] args) {
        String message = "Hi, my name is Slim Shady!" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789" +
                "`~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
        String encoded = new String(Base64.encode(message.getBytes()));
        String decoded = new String(Base64.decode(encoded.getBytes()));

        String f = new String(Base64.encode("f".getBytes()));
        String fo = new String(Base64.encode("fo".getBytes()));
        String foo = new String(Base64.encode("foo".getBytes()));
        String foob = new String(Base64.encode("foob".getBytes()));
        String fooba = new String(Base64.encode("fooba".getBytes()));
        String foobar = new String(Base64.encode("foobar".getBytes()));

        String df = new String(Base64.decode(f.getBytes()));
        String dfo = new String(Base64.decode(fo.getBytes()));
        String dfoo = new String(Base64.decode(foo.getBytes()));
        String dfoob = new String(Base64.decode(foob.getBytes()));
        String dfooba = new String(Base64.decode(fooba.getBytes()));
        String dfoobar = new String(Base64.decode(foobar.getBytes()));

        System.out.println("Raw: " + message);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
        System.out.println("Pass: " + message.equals(decoded));

        System.out.println();
        System.out.println("-- Standard Tests Encode --");
        System.out.println("f: \t\t" + f + "\t - " + (f.equals("Zg==")));
        System.out.println("fo: \t" + fo + "\t - " + (fo.equals("Zm8=")));
        System.out.println("foo: \t" + foo + "\t - " + (foo.equals("Zm9v")));
        System.out.println("foob: \t" + foob + " - " + (foob.equals("Zm9vYg==")));
        System.out.println("fooba: \t" + fooba + " - " + (fooba.equals("Zm9vYmE=")));
        System.out.println("foobar: " + foobar + " - " + (foobar.equals("Zm9vYmFy")));

        System.out.println();
        System.out.println("-- Standard Tests Decode --");
        System.out.println("f: \t\t" + df + "\t\t - " + (df.equals("f")));
        System.out.println("fo: \t" + dfo + "\t\t - " + (dfo.equals("fo")));
        System.out.println("foo: \t" + dfoo + "\t\t - " + (dfoo.equals("foo")));
        System.out.println("foob: \t" + dfoob + "\t - " + (dfoob.equals("foob")));
        System.out.println("fooba: \t" + dfooba + "\t - " + (dfooba.equals("fooba")));
        System.out.println("foobar: " + dfoobar + "\t - " + (dfoobar.equals("foobar")));
    }

}
