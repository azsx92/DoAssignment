package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientServerText02 {
    //Nonconnected-DatagramSocket
    public static void main(String[] args) {

        System.out.println("<<ClientA>> - Text");
        //#1. DatagramSocket 생성(binding 포함)
        // 10000(port)에 바인딩 된 DatagramSocket 객체 생성
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(20000);
        } catch (SocketException e) {
        }
        //#2. 데이터그램 패킷 수신
        byte[] receivedData = new byte[65508];
        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
        try {
            ds.receive(receivedPacket);
        } catch (IOException e) {
        }
        System.out.println("수신데이터 : " + new String(receivedPacket.getData()).trim());
//#3. 전송데이터 생성 + Datagrampacket 생성(수신지 주소 = 수신 패킷의 원격지 정보 추출)
        byte[] sendData = "반갑습니다.".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getSocketAddress());
// #4. 데이터그램 패킷 수신
        //원격지 정보 포함 DatagramPacket 생성
        //수신을 위한 빈 DatagramPacket 생성
        try {
            System.out.println("송신데이터 : " + new String(sendPacket.getData()).trim()); ds.send(sendPacket);
        } catch (IOException e) {}
    }
}
