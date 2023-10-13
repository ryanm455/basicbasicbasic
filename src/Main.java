// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private String ReadID() {
        System.out.print("The ID of the user: ");
        try (Scanner scan = new Scanner(System.in)) {
            return scan.nextLine();
        }
    }

    private URL ParseIdToURL(String id) throws MalformedURLException {
        return URI.create("https://www.ecs.soton.ac.uk/people/" + id).toURL();
    }

    private String FindContent(BufferedReader reader, String og) throws IOException {
        String read;
        while ((read = reader.readLine()) != null) if (read.contains(og)) {
            return read.substring(read.indexOf("content=\"") + 9, read.indexOf("\" />"));
        }
        throw new RuntimeException("User not found!");
    }

    public void Run() throws IOException {
        System.out.println("Welcome.\nJust enter the ID of the user to begin!\n");

        String id = this.ReadID();

        URL url = this.ParseIdToURL(id);

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String name = this.FindContent(reader, "og:title");

        System.out.printf("Name of person: %s", name);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.Run();
    }
}