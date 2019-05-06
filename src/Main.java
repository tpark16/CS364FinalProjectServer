import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.math.BigInteger;

public class Main {

    private static String getQuote(){
        Random rquote = new Random();
        ArrayList<String> quotes = new ArrayList<String>();

        quotes.add("A bird in the hand is safer than one overhead.");
        quotes.add("A clean desk is a sign of a sick mind.");
        quotes.add("A computer makes as many mistakes in one second as three people working for thirty years" +
                "straight.");
        quotes.add("A conference is simply an admission that you want somebody else to join you in your troubles.");
        quotes.add("A dog is a dog except when he is facing you. Then he is Mr. Dog.");
        quotes.add("A great deal of money is never enough once you have it.");
        quotes.add("A major failure will not occur until after the unit has passed final inspection.");
        quotes.add("A meeting is an event at which the minutes are kept and the hours are lost.");
        quotes.add("42");
        quotes.add("A misplaced decimal point will always end up where it will do the greatest damage.");
        quotes.add("A perfectly calm day will turn gusty the instant you drop a $20 bill.");
        quotes.add("A stockbroker is someone who invests your money until it is all gone.");
        quotes.add("A synonym is a word you use when you can't spell the other one.\n");
        quotes.add("A waist is a terrible thing to mind.");

        int number = rquote.nextInt(quotes.size() - 1);

        return quotes.get(number);
    }

    public static void main(String[] args) {

        ServerSocket sock;
        Socket client;
        BufferedReader from;
        PrintWriter to;

        try {
            sock = new ServerSocket(12345);
            System.out.println("Waiting for connection...");
            client= sock.accept();
            System.out.println("Connected to " +
                    client.getInetAddress());
            from = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()
                    )
            );

            to = new PrintWriter(client.getOutputStream(),
                    true);

            Random rnd = new Random();


            while (true) {
                BigInteger a = new BigInteger(13, 3, rnd);
                BigInteger b = new BigInteger(13, 3, rnd);
                BigInteger first = a.multiply(b);

                BigInteger c = new BigInteger(13, 3, rnd);
                BigInteger d = new BigInteger(13, 3, rnd);
                BigInteger second = c.multiply(d);

                System.out.println(from.readLine());

                to.println(first);
                to.println(second);

                System.out.println("Sending " + first + ", " + second + " to client");
                int firstFactor = Integer.parseInt(from.readLine());
                int secondFactor = Integer.parseInt(from.readLine());

                System.out.println("received factors " + firstFactor + ", " + secondFactor);
                System.out.println("verifying factors");

                if (first.intValue()%firstFactor == 0 && second.intValue()%secondFactor == 0) {
                    to.println("correct");
                    System.out.println("Sending \"correct\"");
                    String quote = getQuote();
                    System.out.println("sending quote: \"" + quote + "\"");
                    to.println(quote);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
