package network.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocket01 {
    public static void main(String[] args) {
        //#1. ServerSocket 객체 생성
        ServerSocket serverSocket1 = null;
        ServerSocket serverSocket2 = null;
        try {
            serverSocket1 = new ServerSocket();
            serverSocket2 = new ServerSocket(20000);
        } catch (IOException e) {
        }
//#2. ServerSocket 메서드
        //@bind
        System.out.println(serverSocket1.isBound());//false
        System.out.println(serverSocket2.isBound());//true
        try {
            serverSocket1.bind(new InetSocketAddress("127.0.0.1", 10000));
        } catch (IOException e) {
        }
        System.out.println(serverSocket1.isBound());//true
        System.out.println(serverSocket2.isBound());//true
        System.out.println();


        //@ 사용중인 TCP 포트 확인하기 (cmd: netstat -a)
        for (int i = 0; i < 65536; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
            } catch (IOException e) {
                System.err.println(i + "번째 포트 사용중 ...");
            }
        }
        System.out.println();
//@ accept() / setSoTimeout() / getSoTimeout() //Client로 부터 TCP 접속 대기 (일반적으로 쓰레드 사용)
        try {
            serverSocket1.setSoTimeout(2000);
        } catch (
                SocketException e) {
            e.printStackTrace();
        }
        try {
            Socket socket = serverSocket1.accept();
        } catch (IOException e) {
            try {
                System.out.println(serverSocket1.getSoTimeout() + "ms 시간이 지나 listening을 종료");
            } catch (IOException e1) {
            }
        }
    }
}
