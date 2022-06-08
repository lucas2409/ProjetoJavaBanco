package view;
import java.sql.SQLException;
import java.util.*;

import model.ContaCorrente;
import model.ContaPoupanca;
import repository.ContaCorrenteDAO;
import service.ContaCorrenteService;
import util.Conexao;


public class Index {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ContaCorrente cc = new ContaCorrente(1, 4, 100.00, "Corrente");
		ContaCorrente cc2 = new ContaCorrente(1, 3, 100.00, "Corrente");
		ContaCorrente retornoContaCorrente = new ContaCorrente();
		ContaCorrenteService ccs = new ContaCorrenteService();
		ContaCorrenteDAO cd = new ContaCorrenteDAO();
		
		//Criando uma nova conta
		//cd.criar(cc);
		
		//testando deposito conta corrente
		//retornoContaCorrente = ccs.deposito(cc, 1000.00);
		//System.out.println("Novo Saldo: " + retornoContaCorrente.getSaldo());
		
		//testando saque conta corrente
		//retornoContaCorrente = ccs.saque(cc2, 4800.00);
		//System.out.println("Novo Saldo: " + retornoContaCorrente.getSaldo());
		
		//testando transferência conta corrente
		retornoContaCorrente = ccs.transferencia(cc, cc2, 200.00);
		System.out.println("Novo saldo conta origem: " + retornoContaCorrente.getSaldo());
		System.out.println("Novo Saldo conta destino: " + ccs.consultaSaldo(cc2.getAgencia(), cc2.getConta()));
	}

}
