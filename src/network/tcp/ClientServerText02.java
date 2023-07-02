package network.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServerText02 {
    // ServerSide
    public static void main(String[] args) throws IOException {
        System.out.print("<<Server>>");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10000);
        } catch (IOException e) {
            System.out.println("해당포트를 사용할 수 없습니다.");
            System.exit(0);
        }
        System.out.println(" - Client 접속 대기...");
        try {
            Socket socket = serverSocket.accept(); ////Client와 연결 수락과 함께 통신할 수 있는 Socket 객체 생성
            System.out.println("Client 연결 수락");
            System.out.println("접속 client 주소:" + socket.getInetAddress() + ":" + socket.getPort());


            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String str = dis.readUTF();
            System.out.println("client: " + str);
            dos.writeUTF("어서오세요!");
            dos.flush();

        } catch (IOException e) {
        }
    }
}
