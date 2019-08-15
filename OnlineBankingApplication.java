package session7;

class Account {
	static Account account;
	static int balance = 1000;

	private Account() {
	}

	public synchronized static Account getAccount() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}

	public synchronized static int getBal() {
		return balance;
	}

	public synchronized void withdraw(int bal) {
		if (balance >= bal) {
			balance = balance - bal;
		}
	}

	public synchronized void deposit(int bal) {
		if (bal > 0) {
			balance = balance + bal;
		}
	}

	public static Account getAccountUnsync() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}

	public static int getBalUnsync() {
		return balance;
	}

	public void withdrawUnsync(int bal) {
		if (balance >= bal) {
			balance = balance - bal;
		}
	}

	public void depositUnsync(int bal) {
		if (bal > 0) {
			balance = balance + bal;
		}
	}
}

class BankThreadSync extends Thread {

	@Override
	public void run() {

		for (int i = 0; i < 200; i++) {
			try {
				Account acc = Account.getAccount();
				acc.withdraw(150);
				if (Account.getBal() < 0) {
					System.out.println("Account overdrawn");
				}
				acc.deposit(200);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Account balance after this thread is: " + Account.getBal() + "\n");

	}
}

class BankThreadUnsync extends Thread {

	@Override
	public void run() {

		for (int i = 0; i < 200; i++) {
			try {
				Account acc = Account.getAccountUnsync();
				acc.withdrawUnsync(150);
				if (Account.getBalUnsync() < 0) {
					System.out.println("Account overdrawn");
				}
				acc.depositUnsync(200);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Account balance after this thread is: " + Account.getBalUnsync() + "\n");

	}
}

public class OnlineBankingApplication {

	public static void main(String[] args) {

		int balance = 1000;
		BankThreadSync ts1 = new BankThreadSync();
		BankThreadSync ts2 = new BankThreadSync();
		BankThreadSync ts3 = new BankThreadSync();
		BankThreadSync ts4 = new BankThreadSync();
		BankThreadSync ts5 = new BankThreadSync();
		BankThreadSync ts6 = new BankThreadSync();
		BankThreadSync ts7 = new BankThreadSync();
		BankThreadSync ts8 = new BankThreadSync();
		BankThreadUnsync ts9 = new BankThreadUnsync();
		BankThreadUnsync ts10 = new BankThreadUnsync();
		BankThreadUnsync ts11 = new BankThreadUnsync();
		BankThreadUnsync ts12 = new BankThreadUnsync();
		BankThreadUnsync ts13 = new BankThreadUnsync();
		BankThreadUnsync ts14 = new BankThreadUnsync();
		BankThreadUnsync ts15 = new BankThreadUnsync();
		BankThreadUnsync ts16 = new BankThreadUnsync();
		ts1.start();
		ts2.start();
		ts3.start();
		ts4.start();
		// ts5.start();
		// ts6.start();
		// ts7.start();
		// ts8.start();

		for (int i = 0; i < 800; i++) {
			balance = balance + 50;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The synced account balance is: " + Account.getBal() + "\n");
		System.out.println("The account balance should be: " + balance + "\n");

		ts9.start();
		ts10.start();
		ts11.start();
		ts12.start();
		// ts13.start();
		// ts14.start();
		// ts15.start();
		// ts16.start();

		for (int i = 0; i < 800; i++) {
			balance = balance + 50;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The unsynced account balance is: " + Account.getBal() + "\n");
		System.out.println("The account balance should be: " + balance + "\n");

	}

}
