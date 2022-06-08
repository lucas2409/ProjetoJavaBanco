package model;


public class ContaCorrente extends Conta{
	
	private double novoSaldo = 0.0;
	private boolean taxaSaque = false;
	private double txSaque = 0.009;
	private boolean transferenciaRealizada;
	private String tipoConta;
	
	public ContaCorrente() {}
	
	public ContaCorrente(int agencia, int conta, double saldo, String tipoConta) {
		super(agencia, conta, saldo, tipoConta);
	}
	
	public boolean saqueContaCorrente(double valorSaque){
		this.novoSaldo = getSaldo();
		this.taxaSaque = saque(valorSaque);
		if(this.taxaSaque == true) {
			this.novoSaldo = getSaldo() - (this.novoSaldo * txSaque);
			setSaldo(novoSaldo); 
			return true;
		 }
		 else {
			return false;
		 }
	}
	
	public boolean transfContaCorrente(ContaCorrente contaOrigem, ContaCorrente contaDestino, double valor) {
		this.transferenciaRealizada = transfConta(contaOrigem, contaDestino, valor);
		return this.transferenciaRealizada;
	}
	
	public boolean transfContaPoupanca(ContaCorrente contaOrigem, ContaPoupanca contaDestino, double valor) {
		this.transferenciaRealizada = transfConta(contaOrigem, contaDestino, valor);
		return this.transferenciaRealizada;
	}
}
