package model;

public class Conta {
	private int numAgencia;
	private int numConta;
	private double saldo;
	private String tipoConta;
	
	public Conta() {
		
	}
	public Conta(int agencia, int conta, double saldo, String tipoConta) {
		this.numAgencia = agencia;
		this.numConta = conta;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}
	
	public void setAgencia(int agencia) {
		this.numAgencia = agencia;
	}
	
	public int getAgencia() {
		return this.numAgencia;
	}
	public void setConta(int conta) {
		this.numConta = conta;
	}
	
	public int getConta() {
		return this.numConta;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setTipo(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getTipo() {
		return this.tipoConta;
	}
	
	public boolean saque(double valorSaque) {
		if(valorSaque <= getSaldo()) {
			this.saldo = this.saldo - valorSaque;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean transfConta(Conta contaOrigem, Conta contaDestino, double valor) {
		if(contaOrigem.numConta == contaDestino.numConta) {
			return false;
		}
		else {
			if(contaOrigem.getSaldo() >= valor) {
				contaOrigem.setSaldo(contaOrigem.getSaldo() - valor );
				contaDestino.setSaldo(contaDestino.getSaldo() + valor);
				return true;
			}
			else {
				return false;
			}
		}
			
	}
}
