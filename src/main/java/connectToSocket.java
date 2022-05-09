import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class connectToSocket {



    public Selector getSelector() throws IOException {
        Selector selector = Selector.open();

        return selector;
    }

    public SelectionKey getSelectionKey() throws IOException {
        ServerSocketChannel servSock = ServerSocketChannel.open();
        servSock.configureBlocking(false);
        servSock.socket().bind(new InetSocketAddress(6332));
       SelectionKey selectionKey=  servSock.register(getSelector() , SelectionKey.OP_ACCEPT);

        return selectionKey;
    }

    public void eventListen(SelectionKey selectionKey, Selector selector) throws IOException {
        while (true) {
            if (selector.select()==0){
                continue;
            }
            Set<SelectionKey> selectedKeys=selector.selectedKeys();
            Iterator<SelectionKey> iter= selectedKeys.iterator();
            while (iter.hasNext()){
                SelectionKey key=iter.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocket= (ServerSocketChannel) key.channel();
                    SocketChannel clientSocket=serverSocket.accept();
                    clientSocket.configureBlocking(false);
                    clientSocket.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ);


                }
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel socketChannel= (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();


                }

                if(selectionKey.isConnectable()){

                }
                if(selectionKey.isWritable()){

                }


                iter.remove();
            }




        }


    }
}
