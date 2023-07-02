package udp;

import java.io.IOException;
import java.net.*;

public class DatagramSocket01 {
    public static void main(String[] args) throws IOException {
//#1. DatagramSocket 객체 생성
        DatagramSocket ds1 = null, ds2 = null, ds3 = null, ds4 = null;
        try {
//@1-1. port 미지정 / port만 지정
            ds1 = new DatagramSocket(); //현재 호스트의 비워져 있는 포트로 자동 바인딩
            ds2 = new DatagramSocket(10000); //10000 포트로 바인딩
//@1-2. 바인딩 정보(IP, Port) 포함
            ds3 = new DatagramSocket(10001, InetAddress.getByName("localhost"));
            ds4 = new DatagramSocket(new InetSocketAddress("localhost", 10002));
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
//#2. DatagramSocket 메서드
//@2-1. 소켓의 바인딩 주소 정보
        System.out.println("ds1 바인딩 정보 : " + ds1.getLocalAddress() + ":" + ds1.getLocalPort());
        System.out.println("ds2 바인딩 정보 : " + ds2.getLocalAddress() + ":" + ds2.getLocalPort());
        System.out.println("ds3 바인딩 정보 : " + ds3.getLocalAddress() + ":" + ds3.getLocalPort());
        System.out.println("ds4 바인딩 정보 : " + ds4.getLocalAddress() + ":" + ds4.getLocalPort());
        System.out.println();
    }
}
