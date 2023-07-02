package udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientServerFile01 {
    //Nonconnected-DatagramSocket
    public static void main(String[] args) throws IOException {
        System.out.println("<<ClientA>> - File");
//#1. DatagramSocket 생성(binding 포함) + 소켓간 연결
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(10000);
            ds.connect(new InetSocketAddress("127.0.0.1", 20000));
        } catch (SocketException e) {
        }
//#2. 파일 로딩

        File file = new File("src/sec02_udpcommunication/files_clientA/ImageFileUsingUDP.jpg");
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e1) {
        }
//#3. 데이터그램패킷 전송
        DatagramPacket sendPacket = null;
//@3-1. 파일이름 전송
        String fileName = file.getName();
        sendPacket = new DatagramPacket(fileName.getBytes(), fileName.length());
        //@3-1. 파일이름 전송
        try {
            ds.send(sendPacket);
        } catch (IOException e) {
        }

        //@3-2. 파일전송 시작 사인 전송 (/start)
        String startSign = "/start";
        sendPacket = new DatagramPacket(startSign.getBytes(), startSign.length());
        try {
            ds.send(sendPacket);
        } catch (IOException e) {
        }

        //@3-3. 2048 사이즈로 나누어 실제 파일 데이터 전송
        int len;
        byte[] filedata = new byte[2048]; //최대는 65508byte이지만 안정적 네트워크 통신을 위해서 2048byte씩 잘라서 전송
        try {
            while ((len = bis.read(filedata)) != -1) { // bis is null
                sendPacket = new DatagramPacket(filedata, len);
                ds.send(sendPacket);
            }
        } catch (IOException e) {
        }

        //@3-4. 파일전송 종료 사인 전송
        String endSign = "/end";
        sendPacket = new DatagramPacket(endSign.getBytes(), endSign.length());
        try {
            ds.send(sendPacket);
        } catch (IOException e1) {
        }
//#4. 데이터그램 패킷 수신
        byte[] receivedData = new byte[65508];
        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
        try {
            ds.receive(receivedPacket);
        } catch (IOException e) {
        }
        System.out.println("수신데이터 : " + new String(receivedPacket.getData()).trim());
    }
}
