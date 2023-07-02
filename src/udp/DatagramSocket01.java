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

//@2-2. 원격지 주소 정보 (connect()된 경우에만 연결된 원격지 주소 정보)
        System.out.println("원격지 주소 정보: " + ds4.getInetAddress() + ":" + ds4.getPort());
        try {
            ds4.connect(new InetSocketAddress("localhost", 10003));
        } catch (SocketException e2) {
        }
        System.out.println("원격지 주소 정보: " + ds4.getInetAddress() + ":" + ds4.getPort());
        ds4.disconnect();
        System.out.println();
//@2-3. send(), connect(), disconnect();
//@2-3-0. 전송패킷 2개 (수신지 주소가 없는 패킷 + 수신지 주소가 있는 패킷)
        byte[] data1 = "수신지 주소가 없는 데이터그램 패킷".getBytes();
        byte[] data2 = "수신지 주소가 있는 데이터그램 패킷".getBytes();
        DatagramPacket dp1 = new DatagramPacket(data1, data1.length);
        DatagramPacket dp2 = new DatagramPacket(data2, data2.length, new InetSocketAddress("localhost", 10002));

        try {
//@2-3-1. 수신지 주소가 없는 패킷 전송 = connect() + send() + disconnect();
//ds1.send(dp1); //불가능: 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 없음 (NullPointerException)
// ds2.send(dp1); //불가능: 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 없음 (NullPointerException)
// ds3.send(dp1); //불가능: 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 없음 (NullPointerException)
            ds1.connect(new InetSocketAddress("localhost", 10002));
            ds2.connect(new InetSocketAddress("localhost", 10002));
            ds3.connect(new InetSocketAddress("localhost", 10002));
            ds1.send(dp1); //가능 : 소켓이 connect된 곳 있음 + 패킷에 수신지 주소 없음
            ds2.send(dp1); //가능 : 소켓이 connect된 곳 있음 + 패킷에 수신지 주소 없음
            ds3.send(dp1); //가능 : 소켓이 connect된 곳 있음 + 패킷에 수신지 주소 없음
            ds1.disconnect();
            ds2.disconnect();
            ds3.disconnect();
//@2-3-2. 수신지 주소가 있는 패킷 전송 = send();
            ds1.send(dp2); //가능 : 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 있음
            ds2.send(dp2); //가능 : 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 있음
            ds3.send(dp2); //가능 : 소켓이 connect된 곳 없음 + 패킷에 수신지 주소 있음
//@2-3-2. connect 된 소켓이용 + 수신지 주소가 있는 패킷 전송 : 반드시 주소 일치;
            ds3.connect(new InetSocketAddress("localhost", 10002));
            ds3.send(dp2); //소켓이 connect()된 경우 패킷 연결된 주소와 패킷의 수신지가 같아야 함( IllegalArgumentException ) ds3.disconnect();
        } catch (IOException e2) {
        }

        //@2-4. receive() : 데이터수신
        byte[] receivedData = new byte[65508];
        DatagramPacket dp = new DatagramPacket(receivedData, receivedData.length);
        try {
            for (int i = 0; i < 7; i++) {
                ds4.receive(dp);
                System.out.println("송신자 정보 " + dp.getAddress() + ":" + dp.getPort() + " -> " + new String(dp.getData()).trim());
            }
        } catch (IOException e2) {
        }
        System.out.println();
//@2-5. 송신 및 수신 버퍼 크기
        try {
            System.out.println("송신버퍼크기" + ds1.getSendBufferSize() + ", 수신버퍼크기" + ds1.getReceiveBufferSize());
        } catch (SocketException e) {
        }
    }
}
