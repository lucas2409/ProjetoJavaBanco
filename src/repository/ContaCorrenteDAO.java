package repository;


import model.*;
import util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;

public class ContaCorrenteDAO {

	public ContaCorrente criar(ContaCorrente cc) throws SQLException {
		try {			
			ContaCorrente contaCriada = new ContaCorrente();
			contaCriada = visualizarEspecifico(cc.getAgencia(), cc.getConta());  
			if(contaCriada.getConta() == cc.getConta() && contaCriada.getAgencia() == cc.getAgencia()) {
				System.out.println("Conta já registrada!");
			}
			else {
				if(cc.getTipo() == "Corrente") {
					Connection conn = Conexao.getConnection();
					String insertSQL = "INSERT INTO conta(cont_NumAgencia, cont_NumConta, cont_Saldo, cont_TipoConta)VALUES("
							+cc.getAgencia()+","
							+cc.getConta()+","
							+cc.getSaldo()+",'"
							+cc.getTipo()+"');";
					try {
						Statement st = conn.createStatement();
						st.executeUpdate(insertSQL);	
						System.out.println("Conta cadastrada");
					} catch (SQLException e) {
						Logger.getLogger(ContaCorrenteDAO.class.getName()).log(Level.SEVERE, null, e);
						throw e;
					}
				}
				else {
					System.out.println("Tipo de conta inválido");;
				}	
			}
		} 
		catch (SQLException e) {
			throw e;
		}
		return cc;
	}
	
	public void visualizar() throws SQLException{
		Connection conn = Conexao.getConnection();
		String selectSQL = "SELECT * FROM conta WHERE cont_TipoConta = 'Corrente'";
		ResultSet r = null;
		try {
			Statement st = conn.createStatement();
			r = st.executeQuery(selectSQL);
			while(r.next()) {
				System.out.println("Agencia: " + r.getInt("cont_NumAgencia"));
				System.out.println("Conta: " + r.getInt("cont_NumConta"));
				System.out.println("Saldo: " + r.getFloat("cont_Saldo"));
				System.out.println("Tipo conta: " + r.getString("cont_TipoConta"));
			}
		} catch (SQLException e) {
			Logger.getLogger(ContaCorrenteDAO.class.getName()).log(Level.SEVERE, null, e);
			throw e;
		}
		r.close();
	}
	
	public ContaCorrente visualizarEspecifico(int agencia, int conta) throws SQLException{
		Connection conn = Conexao.getConnection();
		String selectSQL = "SELECT * FROM conta WHERE cont_TipoConta = 'Corrente'"
				+ " AND cont_NumConta = "+conta+""
				+ " AND cont_NumAgencia = "+agencia+"";
		ResultSet r = null;
		ContaCorrente c1 = new ContaCorrente();
		
		try {
			Statement st = conn.createStatement();
			r = st.executeQuery(selectSQL);
			while(r.next()) {
				c1.setAgencia(r.getInt("cont_NumAgencia"));
				c1.setConta(r.getInt("cont_NumConta"));
				c1.setSaldo(r.getFloat("cont_Saldo"));
				c1.setTipo(r.getString("cont_TipoConta"));
			}
			return c1;
		} catch (SQLException e) {
			Logger.getLogger(ContaCorrenteDAO.class.getName()).log(Level.SEVERE, null, e);
			throw e;
		}
	}	

	public ContaCorrente depositoDAO(int agencia, int conta, double saldo, String tipoConta) throws SQLException {
		ContaCorrente cc = new ContaCorrente();
		cc = visualizarEspecifico(agencia, conta);
		
		if(cc.getAgencia() == agencia && cc.getConta() == conta) {
			Connection conn = Conexao.getConnection();
			String updateSQL = "UPDATE conta SET cont_Saldo = " + saldo
					+" WHERE cont_NumAgencia = " + cc.getAgencia() 
					+ " AND cont_NumConta = " + cc.getConta() + 
					" AND cont_TipoConta = '"+tipoConta+"'";
			try{
				Statement st = conn.createStatement();
				st.executeUpdate(updateSQL);	
				System.out.println("Deposito efetuado");
				cc = visualizarEspecifico(agencia, conta);
			}
			catch(SQLException e) {
				System.out.println("Erro ao depositar");
			}
		}
		else {
			System.out.println("Conta não localizada");
		}
		return cc;
	}

	public ContaCorrente saqueDAO(int agencia, int conta, double saldo, String tipoConta) throws SQLException {
		ContaCorrente cc = new ContaCorrente();
		cc = visualizarEspecifico(agencia, conta);
		
		if(cc.getAgencia() == agencia && cc.getConta() == conta) {
			Connection conn = Conexao.getConnection();
			String updateSQL = "UPDATE conta SET cont_Saldo = " + saldo
					+" WHERE cont_NumAgencia = " + cc.getAgencia() 
					+ " AND cont_NumConta = " + cc.getConta() + 
					" AND cont_TipoConta = '"+tipoConta+"'";
			try{
				Statement st = conn.createStatement();
				st.executeUpdate(updateSQL);	
				System.out.println("Saque efetuado");
				cc = visualizarEspecifico(agencia, conta);
			}
			catch(SQLException e) {
				System.out.println("Erro ao sacar");
			}
		}
		else {
			System.out.println("Conta não localizada");
		}
		return cc;
	}

	public ContaCorrente transferenciaDAO(int agenciaOrigem, int contaOrigem, int agenciaDestino, int contaDestino
			, double saldoOrigem, double saldoDestino, String tipoConta) throws SQLException {
		
		ContaCorrente cc1 = new ContaCorrente();
		ContaCorrente cc2 = new ContaCorrente();
		cc1 = visualizarEspecifico(agenciaOrigem, contaOrigem);
		cc2 = visualizarEspecifico(agenciaDestino, contaDestino);
		
		
		if(cc1.getAgencia() == agenciaOrigem && cc1.getConta() == contaOrigem) {
			if(cc2.getAgencia() == agenciaDestino && cc2.getConta() == contaDestino) {
				Connection conn = Conexao.getConnection();
				String updateSQLcontaOrigem = "UPDATE conta SET cont_Saldo = " + saldoOrigem
						+" WHERE cont_NumAgencia = " + cc1.getAgencia() 
						+ " AND cont_NumConta = " + cc1.getConta() + 
						" AND cont_TipoConta = '"+tipoConta+"'";
				
				String updateSQLcontaDestino = "UPDATE conta SET cont_Saldo = " + saldoDestino
						+" WHERE cont_NumAgencia = " + cc2.getAgencia() 
						+ " AND cont_NumConta = " + cc2.getConta() + 
						" AND cont_TipoConta = '"+tipoConta+"'";
				
				try{
					Statement st = conn.createStatement();
					st.executeUpdate(updateSQLcontaOrigem);
					try {
						st.executeUpdate(updateSQLcontaDestino);
						System.out.println("Transferência efetuada");
						cc1 = visualizarEspecifico(agenciaOrigem, contaOrigem);
					}
					catch(SQLException e) {
						System.out.println("Erro ao transferir - checar conta destino");
					}
				}
				catch(SQLException e) {
					System.out.println("Erro ao transferir - checar conta origem");
				}	
			}
			else {
				System.out.println("Conta de destino não localizada");	
			}
		}
		else {
			System.out.println("Conta de origem não localizada");
		}
		return cc1;
	}
}
