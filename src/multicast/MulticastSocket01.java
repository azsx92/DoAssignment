package multicast;

import java.io.IOException;
import java.net.*;

public class MulticastSocket01 {
    public static void main(String[] args) {
//# 멀티캐스트 (224.0.0.0~239.255.255.255) : multicastSocket 클래스 //#1. MulticastSocket 객체 생성
        MulticastSocket mcs1 = null, mcs2 = null, mcs3 = null;
        try {
            mcs1 = new MulticastSocket(); //비어있는 포트로 자동 바인딩
            mcs2 = new MulticastSocket(10000);
            mcs3 = new MulticastSocket(new InetSocketAddress(InetAddress.getLocalHost(),10000)); //일반적으로 포트만 지정
        } catch (IOException e) { }
        System.out.println(mcs1.getLocalSocketAddress()); //0.0.0.0/0.0.0.0:55477
        System.out.println(mcs2.getLocalSocketAddress()); //0.0.0.0/0.0.0.0:10000
        System.out.println(mcs3.getLocalSocketAddress()); //local IP:10000 System.out.println();

//#2. MulticastSocket 주요 메서드
//@2-1. getTimeToLive(), setTimeToLive(); 0~255까지 가능 (이외 IllegalArgumentException 발생)
        try {
            System.out.println("TimeToLive: " + mcs1.getTimeToLive()); //1 mcs1.setTimeToLive(50);
            System.out.println("TimeToLive: " + mcs1.getTimeToLive()); //50
        } catch (IOException e1) {
        }
        System.out.println();

        //@2-2. joinGroup(InetAddress), leaveGroup(InetAddress), send(DatagramPacket), receive(DatagramPacket)
        try {
            mcs1.joinGroup(InetAddress.getByName("234.234.234.234"));
            mcs2.joinGroup(InetAddress.getByName("234.234.234.234"));
            mcs3.joinGroup(InetAddress.getByName("234.234.234.234"));
            //234.234.234.234 멀티캐스트 그룹에 가입
            byte[] sendData = "안녕하세요".getBytes(); DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, InetAddress.getByName("234.234.234.234"), 10000); mcs1.send(sendPacket);
            byte[] receivedData; DatagramPacket receivedPacket;
            receivedData = new byte[65508];
            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            mcs2.receive(receivedPacket);

            System.out.print("mcs2가 수신한 데이터 : " + new String(receivedPacket.getData()).trim());
            System.out.println(" 송신지 : "+receivedPacket.getSocketAddress());
            receivedData = new byte[65508];
            receivedPacket = new DatagramPacket(receivedData, receivedData.length); mcs3.receive(receivedPacket);
            System.out.print("mcs3가 수신한 데이터 : " + new String(receivedPacket.getData()).trim()); System.out.println(" 송신지 : "+receivedPacket.getSocketAddress());
        } catch (UnknownHostException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();
        }
    }
}