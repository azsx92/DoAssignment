package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class DatagramPacket01 {

    public static void main(String[] args) throws IOException {
//#0. 송신데이터: 최대바이트수 65508 byte (64Kbyte) : ipv4 (65536-20(IP header)-8(UDP header)=65505)
        byte[] buf = "UDP-데이터그램패킷".getBytes();
//#1. DatagramPacket 객체 생성
//@1-1. 수신지 주소 미포함 DatagramPacket
        DatagramPacket dp1 = new DatagramPacket(buf, buf.length); //UDP-데이터그램패킷
        DatagramPacket dp2 = new DatagramPacket(buf, 4, buf.length - 4); //데이터그램패킷 (단, getData()는 전체 포함)

//@1-2. 수신측 주소가 InetAddress와 port로 포함되어 있는 경우
        DatagramPacket dp3 = null, dp4 = null;
        try {
            dp3 = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 10000); //data=UDP-데이터그램패킷
            dp4 = new DatagramPacket(buf, 4, buf.length - 4, InetAddress.getByName("localhost"), 10000); //// data=데이터그램패킷

        } catch (UnknownHostException e) {
        }
//@1-3. 수신측 주소가 SocketAddress로 포함되어 있는 경우
        DatagramPacket dp5 = null, dp6 = null;
        dp5 = new DatagramPacket(buf, buf.length, new InetSocketAddress("localhost", 10000));
        dp6 = new DatagramPacket(buf, 4, buf.length - 4, new InetSocketAddress("localhost", 10000));

        //#2. DatagramPacket 주요 메서드
//@2-1. getAddress(), getPort(), getSocketAddress()
        System.out.println("원격지 IP : " + dp1.getAddress()); //null
        System.out.println("원격지 Port : " + dp1.getPort()); //-1
//System.out.println("원격지 SocketAddress : " + dp1.getSocketAddress()); //IllegalArgumentException 발생
        System.out.println("원격지 IP : " + dp3.getAddress()); //localhost/127.0.0.1
        System.out.println("원격지 Port : " + dp3.getPort()); //10000
        System.out.println("원격지 SocketAddress : " + dp3.getSocketAddress()); //localhost/127.0.0.1:10000 System.out.println();
//@2-2. getData(). setData(), getOffset(), getLength()
        System.out.println("포함데이터 : " + new String(dp1.getData())); //UDP-데이터그램패킷
        System.out.println("포함데이터 : " + new String(dp2.getData())); //UDP-데이터그램패킷 (len와 상관없이 항상 전체데이터 리턴) System.out.println("포함데이터 : "+new String(dp2.getData(),dp2.getOffset(),dp2.getLength())); //데이터그램패킷
        dp1.setData("안녕하세요".getBytes());
        System.out.println("포함데이터 : " + new String(dp1.getData())); //안녕하세요


    }
}