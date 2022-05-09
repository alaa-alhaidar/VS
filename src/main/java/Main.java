import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        connectToSocket connectToSocket = new connectToSocket();
        System.out.println(connectToSocket.getSelectionKey());
    }
}
