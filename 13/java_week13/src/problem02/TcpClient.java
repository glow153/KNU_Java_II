package problem02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

public class TcpClient extends Thread {
	public static final int SVR_IP = 0;
	public static final int SVR_PORT = 1;
	public static final int VOID = 2;
	public static final String[] SA_CAPTION = { "Server IP", "Server Port", "" };

	private Socket sckt;
	private final String svrIP;
	private final int svrPort;
	private JTextArea jta;
	
	private BufferedReader br;
	private BufferedWriter bw;
	
	private boolean bConnected = false;

	public TcpClient(String svrIP, int svrPort, JTextArea jta) {
		this.svrIP = svrIP;
		this.svrPort = svrPort;
		this.jta = jta;
		try {
			sckt = new Socket(svrIP, svrPort);
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
		jta.append("³ª : " + sMsgOut + "\r\n");
		jta.setCaretPosition(jta.getText().length());
	}

	public void run() {
		String sMsgIn = "";
		while (bConnected) {
			try {
				sMsgIn = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bConnected = false;
				break;
			}
			jta.append(sMsgIn + "\r\n");
			jta.setCaretPosition(jta.getText().length());
		}
	}
}
