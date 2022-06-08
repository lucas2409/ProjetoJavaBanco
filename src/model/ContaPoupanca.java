package model;

public class ContaPoupanca extends Conta{

	private double rendimentos ;
	private boolean resgateValor;
	
	public ContaPoupanca(int agencia, int conta, double saldo, String tipoConta) {
		super(agencia, conta, saldo, tipoConta);
	}
	
	public void setRendimento(ContaPoupanca deposito) {
		this.rendimentos = (deposito.getSaldo() * 0.009) ;
		deposito.setSaldo(deposito.getSaldo() + this.rendimentos);
	}
	public double getRendimento() {
		return this.rendimentos;
	}
	
	public boolean resgate(ContaPoupanca contaOrigem, ContaCorrente contaDestino, double valor) {
		this.resgateValor = transfConta(contaOrigem, contaDestino, valor);
		return this.resgateValor;
	}
	
}
