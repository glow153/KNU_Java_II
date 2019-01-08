package problem01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

public class UdpComm extends Thread {
	public static final int MYPORT = 0;
	public static final int OPPORT = 1;
	public static final int OPIP = 2;
	public static final int VOID = 3;
	public static final String[] SA_CAPTION = { "My Port", "Your Port", "Your IP", "" };
	private final int myPort, opPort;
	private InetAddress addrOpIP;
	private DatagramSocket dskt;
	private JTextArea jta;
	private byte[] bufSend = new byte[1024];
	private byte[] bufReceive = new byte[1024];

	public UdpComm(int myPort, int opPort, String opIP, JTextArea jta) {
		this.myPort = myPort;
		this.opPort = opPort;
		this.jta = jta;
		try {
			dskt = new DatagramSocket(myPort);
			addrOpIP = InetAddress.getByName(opIP);
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMsg(String sMsgOut) {
		bufSend = sMsgOut.getBytes();
		DatagramPacket dpkt = new DatagramPacket(bufSend, bufSend.length, addrOpIP, opPort);
		try {
			dskt.send(dpkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jta.append("나 : " + sMsgOut + "\r\n");
	}

	public void run() {
		while (true) {
			DatagramPacket dpktReceive = new DatagramPacket(bufReceive, bufReceive.length);
			try {
				dskt.receive(dpktReceive);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			String sMsgIn = new String(dpktReceive.getData(), 0, dpktReceive.getLength());
			jta.append("상대방 : " + sMsgIn + "\r\n");
		}
	}
}
