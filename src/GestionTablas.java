import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class GestionTablas extends JFrame {

	private JPanel contentPane;
	private JTable miTabla;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JButton btnAnadir;
	private JButton btnModificar;
	private JButton btnEliminar;
	private DefaultTableModel miModelo;



	public boolean isCellEditable (int row, int column)
	   {
	       return false;
	   }

	
	public static void main(String[] args) {
		GestionTablas frame = new GestionTablas();
		frame.setVisible(true);
	}

	private void VisibleAnadir() {
		if (!txtNombre.getText().equals("") && !txtApellido.getText().equals("") && !txtTelefono.getText().equals("")) {
			btnAnadir.setEnabled(true);
		} else
			btnAnadir.setEnabled(false);
	}

	private void limpiarTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		VisibleAnadir();
	}
	
	/**
	 * Create the frame.
	 */
	public GestionTablas() {
		setTitle("Gestion Tablas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 32, 362, 115);
		contentPane.add(scrollPane);

		miTabla = new JTable();
		miTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		miTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (miTabla.getSelectedRow() == -1) {
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
				}
				else {
					txtNombre.setText((String) miTabla.getValueAt(miTabla.getSelectedRow(), 0));
					txtApellido.setText((String) miTabla.getValueAt(miTabla.getSelectedRow(), 1));
					txtTelefono.setText((String) miTabla.getValueAt(miTabla.getSelectedRow(), 2));
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
				}
			}
		});
		miModelo = new DefaultTableModel(new String[][] { { "Pedro1", "Camacho", "123456" },
				{ "Pedro2", "Camacho", "123456" }, { "Pedro3", "Camacho", "123456" }, { "Pedro4", "Camacho", "123456" }

		}, new String[] { "Nombre", "Apellido", "Teléfono" });
		miTabla.setModel(miModelo);
		scrollPane.setViewportView(miTabla);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				VisibleAnadir();
			}
		});
		txtNombre.setBounds(33, 158, 116, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				VisibleAnadir();
			}
		});
		txtApellido.setColumns(10);
		txtApellido.setBounds(152, 158, 123, 20);
		contentPane.add(txtApellido);

		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				VisibleAnadir();
			}
		});
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(279, 158, 116, 20);
		contentPane.add(txtTelefono);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setEnabled(false);
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miModelo.addRow(new String[] { txtNombre.getText(), txtApellido.getText(), txtTelefono.getText() });
				limpiarTexto();
			}
		});
		btnAnadir.setBounds(47, 206, 89, 23);
		contentPane.add(btnAnadir);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miModelo.insertRow(miTabla.getSelectedRow(), new String[] { txtNombre.getText(), txtApellido.getText(), txtTelefono.getText() });
				miModelo.removeRow(miTabla.getSelectedRow());
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				limpiarTexto();
			}
		});
		btnModificar.setBounds(167, 206, 89, 23);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] seleccionados =  miTabla.getSelectedRows();
				for (int i = 0; i < seleccionados.length; i++) {
					miModelo.removeRow(seleccionados[i]);	
				}
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
			}
		});
		btnEliminar.setBounds(292, 206, 89, 23);
		contentPane.add(btnEliminar);
	}
}
