package service;

import java.sql.SQLException;

import model.Conta;
import repository.ContaCorrenteDAO;
import model.ContaCorrente;

public class ContaCorrenteService implements ContaService{

	public ContaCorrenteService() {
	}

	@Override
	public ContaCorrente deposito(ContaCorrente conta , double valorDeposito) throws SQLException {
		ContaCorrente cc = new ContaCorrente();
		ContaCorrenteDAO ccd = new ContaCorrenteDAO();
		
		cc = ccd.visualizarEspecifico(conta.getAgencia(), conta.getConta());
		
		if(cc.getAgencia() == conta.getAgencia() && cc.getConta() == conta.getConta()) {
			if(conta.getTipo() == "Corrente") {
				conta.setSaldo(cc.getSaldo() + valorDeposito);	
				cc = ccd.depositoDAO(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getTipo());
			}
			else {
				System.out.println("Tipo de conta não é valido");
			}	
		}
		else {
			System.out.println("Conta não encontrada");
			cc = conta;
		}
		
		return cc;
	}

	@Override
	public ContaCorrente saque(ContaCorrente conta , double valorSaque) throws SQLException {
		ContaCorrente cc = new ContaCorrente();
		ContaCorrenteDAO ccd = new ContaCorrenteDAO();
		
		cc = ccd.visualizarEspecifico(conta.getAgencia(), conta.getConta());
		
		if(cc.getAgencia() == conta.getAgencia() && cc.getConta() == conta.getConta()) {
			if(conta.getTipo() == "Corrente") {
				conta.setSaldo(cc.getSaldo() - valorSaque);	
				cc = ccd.saqueDAO(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getTipo());
			}
			else {
				System.out.println("Tipo de conta não é valido");
			}	
		}
		else {
			System.out.println("Conta não encontrada");
			cc = conta;
		}
		
		return cc;
	}	
	
	@Override
	public ContaCorrente transferencia(ContaCorrente contaOrigem, ContaCorrente contaDestino, double valor) throws SQLException{
		ContaCorrente cc1 = new ContaCorrente();
		ContaCorrente cc2 = new ContaCorrente();
		ContaCorrenteDAO ccd = new ContaCorrenteDAO();

		cc1 = ccd.visualizarEspecifico(contaOrigem.getAgencia(), contaOrigem.getConta());
		cc2 = ccd.visualizarEspecifico(contaDestino.getAgencia(), contaDestino.getConta());
		
		if(cc1.getAgencia() == contaOrigem.getAgencia() && cc1.getConta() == contaOrigem.getConta()) {
			if(cc2.getAgencia() == contaDestino.getAgencia() && cc2.getConta() == contaDestino.getConta()) {
				if(cc1.getSaldo() < valor) {
					System.out.println("FALHA: Saldo insuficiente!");
				}
				else {
					cc1.setSaldo(cc1.getSaldo() - valor);
					cc2.setSaldo(cc2.getSaldo() + valor);
					
					cc1 = ccd.transferenciaDAO(cc1.getAgencia(), cc1.getConta(), cc2.getAgencia(), cc2.getConta(), cc1.getSaldo(), cc2.getSaldo(), cc1.getTipo());
				}
			}
			else{
				System.out.println("FALHA: Conta Destino não localizada");
			}
		}
		else {
			System.out.println("FALHA: Conta Origem não localizada");
		}
		
		return cc1;
	}
	
	@Override
	public double consultaSaldo(int agencia, int conta) throws SQLException{
		ContaCorrente cc1 = new ContaCorrente();
		ContaCorrenteDAO ccd = new ContaCorrenteDAO();
		
		cc1 = ccd.visualizarEspecifico(agencia, conta);
		
		return cc1.getSaldo();
	}
	
}
