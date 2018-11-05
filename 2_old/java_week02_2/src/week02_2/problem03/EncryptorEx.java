package week02_2.problem03;

import java.util.HashMap;

public class EncryptorEx extends Encryptor {
	private HashMap<Character, Character> encTable;

	public EncryptorEx(String infile, String outfile) {
		super(infile, outfile);
		encTable = new HashMap<>();
		initEncryptionTable();
	}

	private void initEncryptionTable() {
		encTable.put('a', '4');
		encTable.put('b', '8');
		encTable.put('c', '(');
		encTable.put('e', '3');
		encTable.put('g', '9');
		encTable.put('i', '1');
		encTable.put('l', '|');
		encTable.put('o', '0');
		encTable.put('s', '5');
		encTable.put('t', '7');
		encTable.put('z', '2');
	}

	protected void encryptContent() {
		for (int i = 0; i < sbContent.length(); i++) {  // ���� ���̸�ŭ �ݺ�
			char c = Character.toLowerCase(sbContent.charAt(i));	// ���� �ε����� char ������
			if (encTable.containsKey(c))	// ���� char�� ���̺� key�� �����ϸ�
				sbContent.setCharAt(i, encTable.get(c));	// value ��ü��
		}
	}
}
