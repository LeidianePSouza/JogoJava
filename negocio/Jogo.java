package negocio;

import apresentacao.*;
import java.util.Random;

public class Jogo {

	private Jogador jogador;
	private Tela tela;
	private Terminal terminal;
	private int numeroEscolhido;
	private boolean jogando;
	

	public int numeroEscolhido(){
		Random numeroEscolhido = new Random();
		int numeroAleatorio = numeroEscolhido.nextInt(100); 
		return numeroAleatorio;
	}
	
	public Jogo() {
		tela = new Tela();
		terminal = new Terminal();
		jogador = new Jogador();
		numeroEscolhido = numeroEscolhido();
		jogando = true;
	}

	public void inciarJogoTerminal() {
		jogador.setNome(terminal.entradaNome());
		terminal.mesagem("Seja bem-vindo(a): " + jogador.getNome());
	}

	public void inciarJogoGUI() {
		jogador.setNome(tela.entradaDados("Qual é o seu nome?"));
		tela.mesagem("Seja bem-vindo: " + jogador.getNome());
		jogadas();
	}

	public int solicitarNumero() {
		String numero = tela.entradaDados("Informe um número:");
		return Integer.parseInt(numero);
	}

	public void jogadas() {
		do {
			verificarAcerto();
		} while (jogando);

		fimDoJogo();
	}

	private void fimDoJogo() {

		String numeros = "";
		for (Integer numero : jogador.getListaNumeros()) {
			numeros += " / " + numero;
		}
		tela.mesagem("Números apostados: " + numeros);

	}

	public boolean verificarMenor(int numero) {
		if (numero < numeroEscolhido)
			return true;

		return false;
	}

	public void verificarAcerto() {
		int numero = solicitarNumero();
		jogador.addNumero(numero); 
		if (numero == numeroEscolhido) {
			tela.mesagem("Parabéns! Você acertou. \n Número de tentativas: " + jogador.getNumeroTentativa());
			jogando = false;
		} else {
			jogador.setNumeroTentativa();
			if (verificarMenor(numero)) { 
				tela.mesagem("Deu ruim,  você errou!\n Tente um número maior.");
			}else {
				tela.mesagem("Deu ruim,  você errou! \n Tente um número menor.");
			}
		}
	}
}
