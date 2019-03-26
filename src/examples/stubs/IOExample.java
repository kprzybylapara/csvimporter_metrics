package examples.stubs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of IO automatic stubs.
 * 
 * This example shows how jtest does white-box testing of code using I/O. Jtest
 * automatically generates stubs for the I/O calls to cover the code and
 * simulate IO exceptions that may happen.
 */
public class IOExample {

	public IOExample(InputStream input, int num_accounts) throws IOException {
		_dis = new DataInputStream(input);
		_numAccounts = num_accounts;
	}

	public int checkForLowBalances() throws IOException {
		int num_low = 0;
		for (int i = 0; i < _numAccounts; i++) {
			Account account = new Account(_dis);
			if (account.getBalance() < 100 && !account.getName().equals("BOSS")) {
				num_low++;
				addToLowBalancesList(account);
			}
		}
		return num_low;
	}

	private void addToLowBalancesList(Account account) {
		_lowBalances.add(account);
	}

	private static class Account {
		Account(DataInputStream dis) throws IOException {
			_name = dis.readUTF();
			_balance = dis.readInt();
		}

		String getName() {
			return _name;
		}

		int getBalance() {
			return _balance;
		}

		private String _name;
		private int _balance;
	}

	private DataInputStream _dis;
	private int _numAccounts;
	private List _lowBalances = new ArrayList();
}