package problem02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

public class TcpServer extends Thread {
	public static final int SVR_PORT = 0;
	public static final int VOID = 1;
	public static final String[] SA_CAPTION = { "Server Port", "" };

	private ServerSocket svrSckt;
	private Socket sckt;
	private final int svrPort;
	private DefaultListModel<String> dlmRef;
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private boolean bConnected = false;

	public TcpServer(int svrPort, DefaultListModel<String> d) {
		this.svrPort = svrPort;
		dlmRef = d;
		try {
			svrSckt = new ServerSocket(svrPort);
			sckt = svrSckt.accept();
			br = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(sckt.getOutputStream()));
			bConnected = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMsg(String sMsgOut) {
		try {
			bw.write(sMsgOut);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		dlmRef.addElement(sMsgOut);
	}

	public void run() {
		String sMsgIn = "";
		while (bConnected) {
			try {
				sMsgIn = br.readLine();
			} catch (IOException e) {
				//e.printStackTrace();
				bConnected = false;
				break;
			}
			dlmRef.addElement("Client : " + sMsgIn);
		}
	}
}
