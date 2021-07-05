package com.poo.visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.poo.visao.componentes.IInternalFrame;
import com.poo.visao.componentes.MenuBarItem;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xx;
	private int yy;
	private List<Component> lstMenuButos = new ArrayList<Component>();

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/imagens/hospital-24.png")));
		getContentPane().setPreferredSize(new Dimension(1000, 595));

		desktop = new javax.swing.JDesktopPane();
		desktop.setBounds(153, 23, 847, 572);
		desktop.setAlignmentY(0.0f);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("");
		setName("�Sistema de Atendimento\r\n" + "Hospitalar"); // NOI18N
		setUndecorated(true);
		setResizable(false);
		setSize(new java.awt.Dimension(0, 0));
		setBackground(new java.awt.Color(237, 242, 249));

		desktop.setBackground(new java.awt.Color(237, 242, 249));
		desktop.setForeground(new java.awt.Color(237, 242, 249));
		desktop.setPreferredSize(new java.awt.Dimension(824, 600));

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/hospital.png")));

		javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
		desktopLayout.setHorizontalGroup(
				desktopLayout.createParallelGroup(Alignment.LEADING).addGap(0, 847, Short.MAX_VALUE));
		desktopLayout
				.setVerticalGroup(desktopLayout.createParallelGroup(Alignment.LEADING).addGap(0, 572, Short.MAX_VALUE));
		desktopLayout.setAutoCreateGaps(false);
		desktopLayout.setAutoCreateContainerGaps(false);
		desktop.setLayout(desktopLayout);
		getContentPane().setLayout(null);

		JButton btnFechar = new JButton("X");
		btnFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColorEntered(btnFechar);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFechar.setBackground(new java.awt.Color(3, 133, 186));
			}
		});
		btnFechar.setFocusPainted(false);
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.setBorderPainted(false);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		painelMenu = new JMenuBar();
		painelMenu.setBounds(0, 23, 154, 572);
		painelMenu.setBackground(new java.awt.Color(4, 158, 218));
		painelMenu.setForeground(new java.awt.Color(105, 196, 218));

		JLabel logoLabel = new JLabel("");
		logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/hospital.png")));

		MenuBarItem btnGerenciarPaciente = criaBotaoMenui("Gerenciar Paciente", new GerenciarPaciente());
		MenuBarItem btnGerarAtendimento = criaBotaoMenui("Gerar Atendimento", new GeraAtendimento());

		MenuBarItem btnGerarConsulta = criaBotaoMenui("Gerar Consulta", new GeraConsulta());

		MenuBarItem btnInternacao = criaBotaoMenui("Interna\u00E7\u00E3o", new InternacoesView());

		MenuBarItem btnFinalizarAtendimento = criaBotaoMenui("Finalizar Atendimento", new FinalizarAtendimentoView());

		MenuBarItem btnAguardandoLeito = criaBotaoMenui("Aguardando Leito", new FilaLeitosView());

		MenuBarItem btnInternados = criaBotaoMenui("Internados", new PacientesInternadosView());

		MenuBarItem btnLeitosVagos = criaBotaoMenui("Leitos Vagos", new LeitosVagosView());

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColorEntered(btnSair);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setBackground(new java.awt.Color(3, 133, 186));
			}
		});
		btnSair.setAlignmentY(Component.TOP_ALIGNMENT);
		btnSair.setFocusPainted(false);
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setBorderPainted(false);
		btnSair.setForeground(Color.WHITE);
		btnSair.setPreferredSize(new Dimension(154, 47));
		btnSair.setBorder(null);
		btnSair.setBackground(new java.awt.Color(3, 133, 186));

		javax.swing.GroupLayout painelMenuLayout = new javax.swing.GroupLayout(painelMenu);
		painelMenuLayout.setHorizontalGroup(painelMenuLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(painelMenuLayout.createSequentialGroup().addGroup(painelMenuLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(logoLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarAtendimento, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarConsulta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInternacao, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFinalizarAtendimento, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAguardandoLeito, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInternados, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLeitosVagos, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSair, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnGerenciarPaciente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		painelMenuLayout.setVerticalGroup(painelMenuLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(painelMenuLayout.createSequentialGroup()
						.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnGerenciarPaciente, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnGerarAtendimento, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnGerarConsulta, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnInternacao, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnFinalizarAtendimento, GroupLayout.PREFERRED_SIZE, 41,
								GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnAguardandoLeito, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnInternados, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(btnLeitosVagos, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)));
		painelMenu.setLayout(painelMenuLayout);
		getContentPane().add(painelMenu);

		// -------------------------- EVENTS ------------------------------

		btnFechar.setForeground(Color.WHITE);
		btnFechar.setBorder(null);
		btnFechar.setBounds(950, 0, 50, 23);
		getContentPane().add(btnFechar);
		btnFechar.setBackground(new java.awt.Color(3, 133, 186));
		getContentPane().add(desktop);

		JPanel panelNome = new JPanel();
		panelNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelMousePressed(e);
			}
		});
		panelNome.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				panelMouseDragged(e);
			}
		});
		FlowLayout flowLayout = (FlowLayout) panelNome.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelNome.setBounds(0, 0, 1000, 23);
		getContentPane().add(panelNome);
		panelNome.setBackground(new java.awt.Color(3, 133, 186));

		JLabel labelNome = new JLabel("Sistema de Atendimento Hospitalar");
		labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelNome.setToolTipText("");
		labelNome.setForeground(Color.WHITE);
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNome.setBackground(Color.WHITE);
		panelNome.add(labelNome);

		pack();
		setLocationRelativeTo(null);

		// btnColorFocus(btnGerenciarPaciente);
	}

	protected MenuBarItem criaBotaoMenui(String label, IInternalFrame classItFrame) {
		MenuBarItem btn = new MenuBarItem(label);
		lstMenuButos.add(btn);

		btn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				for (Component jButton : lstMenuButos) {
					jButton.setForeground(Color.WHITE);
					jButton.setBackground(new java.awt.Color(4, 158, 218));

				}
				btn.setBackground(new java.awt.Color(237, 242, 249));
				btn.setForeground(Color.BLACK);
				repaint();
			}
		});

		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				classItFrame.dadosDefault();
				setPanelPrincipal(classItFrame);
			}
		});

		return btn;
	}

	private void setPanelPrincipal(IInternalFrame itFrame) {
		desktop.removeAll();
		itFrame.limpar();
		((BasicInternalFrameUI) itFrame.getUI()).setNorthPane(null);

		desktop.add(itFrame);

		try {
			itFrame.setMaximum(true);
		} catch (Exception ex) {
			Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}

		itFrame.setVisible(true);
		itFrame.setSize(desktop.getSize());
	}

	private void panelMouseDragged(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		setLocation(x - xx, y - yy);
	}

	private void panelMousePressed(MouseEvent e) {
		xx = e.getX();
		yy = e.getY();
	}

	private void btnColorEntered(JButton button) {
		if (!button.getBackground().equals(new java.awt.Color(237, 242, 249))) {
			button.setBackground(new java.awt.Color(105, 196, 218));
			button.repaint();
		}
	}

	private javax.swing.JDesktopPane desktop;
	private JMenuBar painelMenu;
}
