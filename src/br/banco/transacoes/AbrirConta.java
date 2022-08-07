package br.banco.transacoes;

import br.banco.Banco;
import br.exception.AbrirContaException;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AbrirConta extends JDialog {

	private JPanel panel;
	private JPanel panelBorda;

	private JPanel panelTitulo;
	private JLabel labelTitulo;
	
	private JPanel panelConta;
	private JLabel labelConta;
	private ButtonGroup grupoConta;
	private JRadioButton ContaPoupanca;
	private JRadioButton ContaCorrente;

	private JPanel panelBotao;
	private JButton AbrirConta;

	public AbrirConta(JFrame frame) {
		super(frame, true);
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Banco Java");
		this.setSize(400, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		Font fontTitulo = new Font("Arial", Font.BOLD, 25);
		Font fontTexto = new Font("Arial", Font.BOLD, 15);
		Font fontCampo = new Font("Arial", Font.PLAIN, 15);
		Font fontBotao = new Font("Arial", Font.BOLD, 15);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(panel);

		panelBorda = new JPanel();
		panelBorda.setLayout(new BoxLayout(panelBorda, BoxLayout.Y_AXIS));
		panelBorda.setBorder(new LineBorder(Color.black));
		panel.add(panelBorda);

		panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelTitulo);

		labelTitulo = new JLabel("Abrir Conta");
		labelTitulo.setFont(fontTitulo);
		panelTitulo.add(labelTitulo);

		panelConta = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelConta);

		labelConta = new JLabel("Tipo:");
		labelConta.setFont(fontTexto);
		panelConta.add(labelConta);

		ContaPoupanca = new JRadioButton("Conta Poupança");
		ContaPoupanca.setSelected(true);
		ContaPoupanca.setFont(fontCampo);
		panelConta.add(ContaPoupanca);

		ContaCorrente = new JRadioButton("Conta Corrente");
		ContaCorrente.setFont(fontCampo);
		panelConta.add(ContaCorrente);

		grupoConta = new ButtonGroup();
		grupoConta.add(ContaPoupanca);
		grupoConta.add(ContaCorrente);

		panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBorda.add(panelBotao);

		AbrirConta = new JButton("Abrir Conta");
		AbrirConta.setFont(fontBotao);
		AbrirConta.addActionListener((ActionEvent) -> {
			this.abrirConta();
		});
		panelBotao.add(AbrirConta);
	}

	private void abrirConta() {
		try {
			if (ContaPoupanca.isSelected()) {
				Banco.abrirContaPoupanca();
			} else {
				Banco.abrirContaCorrente();
			}

			String info = "Conta aberta com sucesso!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		} catch (AbrirContaException e) {
			String info = "Erro ao abrir conta!\nVocê não pode ter 2 contas poupanças ou correntes, somente uma de cada!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		}
	}

}
