package service;

import java.sql.SQLException;

import model.Conta;
import model.ContaCorrente;

public interface ContaService {
	
	public ContaCorrente deposito(ContaCorrente conta, double valorDeposito) throws SQLException;
	public ContaCorrente saque(ContaCorrente conta, double valorSaque) throws SQLException;
	public ContaCorrente transferencia(ContaCorrente contaOrigem, ContaCorrente contaDestino, double valor)  throws SQLException;
	public double consultaSaldo(int agencia, int conta)  throws SQLException;

}
