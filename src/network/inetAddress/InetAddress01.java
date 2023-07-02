package network.inetAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddress01 {
    public static void main(String[] args) throws IOException {
        //#1. InetAddress 객체 생성
//@1-1. 원격지 IP
        InetAddress ia1 = InetAddress.getByName("www.google.com");
        InetAddress ia2 = InetAddress.getByAddress(new byte[] {(byte)172,(byte)217,(byte)161,(byte)132});
        InetAddress ia3 = InetAddress.getByAddress("www.google.com", new byte[] {(byte)172,(byte)217,(byte)161,(byte)132}); System.out.println(ia1);
        System.out.println(ia2);
        System.out.println(ia3);
//@1-2. 로컬/루프백 IP
        InetAddress ia4 = InetAddress.getLocalHost();
        InetAddress ia5 = InetAddress.getLoopbackAddress(); //localhost/127.0.0.1 System.out.println(ia4);
        System.out.println(ia5);
//@1-3. 하나의 호스트가 여러 개의 IP를 가지고 있는 경우
        InetAddress[] ia6 = InetAddress.getAllByName("www.naver.com"); System.out.println(Arrays.toString(ia6));
        System.out.println();



        //#2. InetAddress 메서드
//@2-1. 호스트와 IP 알아내기
        byte[] address = ia1.getAddress();
        System.out.println(Arrays.toString(address));
        System.out.println(ia1.getHostAddress());
        System.out.println(ia1.getHostName());
//@2-2. 저장 주소의 특징 알아내기
        System.out.println(ia1.isReachable(1000));
        System.out.println(ia1.isLoopbackAddress());
        System.out.println(ia1.isMulticastAddress());
        System.out.println(InetAddress.getByAddress(new byte[] {127,0,0,1}).isLoopbackAddress()); System.out.println(InetAddress.getByAddress(new byte[] {(byte)225,(byte)225,(byte)225,(byte)225}).isMulticastAddress());



    }
}
