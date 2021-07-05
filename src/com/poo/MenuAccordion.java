package com.poo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MenuAccordion extends JFrame {

	private JPanel contentPane;
	private JCheckBox cbMenuUtama;
	private JButton btnPelanggan;
	private JButton btnBarang;
	private JCheckBox ccTransaksi;
	private JButton btnPesan;
	private JButton btnRetur;

	/**
	 * Create the frame.
	 */
	public MenuAccordion() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUtama = new JPanel();
		panelUtama.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelUtama.setBounds(0, 0, 179, 488);
		contentPane.add(panelUtama);
		panelUtama.setLayout(null);
		
		cbMenuUtama = new JCheckBox("Menu Utama");
		cbMenuUtama.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				if(cbMenuUtama.isSelected())
				{
					btnPelanggan.setVisible(true);
					btnBarang.setVisible(true);
				}
				else
				{
					btnPelanggan.setVisible(false);
					btnBarang.setVisible(false);
				}
			}
		});
		cbMenuUtama.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuUtama.png"));
		cbMenuUtama.setBorderPaintedFlat(true);
		cbMenuUtama.setBorderPainted(true);
		cbMenuUtama.setBounds(8, 8, 159, 24);
		panelUtama.add(cbMenuUtama);
		
		btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuPelanggan.png"));
		btnPelanggan.setBounds(8, 35, 159, 23);
		btnPelanggan.setVisible(false);
		panelUtama.add(btnPelanggan);
		
		btnBarang = new JButton("Barang");
		btnBarang.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuBarang.png"));
		btnBarang.setBounds(8, 59, 159, 23);
		btnBarang.setVisible(false);
		panelUtama.add(btnBarang);
		
		ccTransaksi = new JCheckBox("Transaksi");
		ccTransaksi.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				if(ccTransaksi.isSelected())
				{
					btnPesan.setVisible(true);
					btnRetur.setVisible(true);
				}
				else
				{
					btnPesan.setVisible(false);
					btnRetur.setVisible(false);
				}
			}
		});
		ccTransaksi.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuTransaksi.png"));
		ccTransaksi.setBorderPaintedFlat(true);
		ccTransaksi.setBorderPainted(true);
		ccTransaksi.setBounds(8, 91, 159, 24);
		panelUtama.add(ccTransaksi);
		
		btnPesan = new JButton("Pemesanan");
		btnPesan.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuPemesanan.png"));
		btnPesan.setBounds(8, 117, 159, 23);
		btnPesan.setVisible(false);
		panelUtama.add(btnPesan);
		
		btnRetur = new JButton("Retur");
		btnRetur.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/menuRetur.png"));
		btnRetur.setBounds(8, 140, 159, 23);
		btnRetur.setVisible(false);
		panelUtama.add(btnRetur);
	} //Akhir Konstruktor
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] ar)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MenuAccordion frame = new MenuAccordion();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}