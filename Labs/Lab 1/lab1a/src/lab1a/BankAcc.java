package lab1a;

public interface BankAcc {
	public String getName();
	public Integer getAccNum();
	public Float getBalance();
	public Float deposit(Float amount);
	public boolean withdraw(Float amount);
}
