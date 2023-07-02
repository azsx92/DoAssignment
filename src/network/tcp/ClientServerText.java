package network.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServerText {
    // CientSide
    public static void main(String[] args) {
        System.out.println("<<Client>>");
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getByName("localhost"), 10000); //원격지 연결정보(IP, Port) 포함 소켓 생성

            System.out.println("Server에 접속완료");
            System.out.println("접속 server 주소:" + socket.getInetAddress() + ":" + socket.getPort());

            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            dos.writeUTF("안녕하세요");
            dos.flush();
            String str = dis.readUTF();

            System.out.println("server: " + str);

        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}
