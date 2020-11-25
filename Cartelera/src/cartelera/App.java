package cartelera;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
        FrameCalculadora frame = new FrameCalculadora();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class FrameCalculadora extends JFrame {
    public FrameCalculadora() {
        setTitle("Calculadora");
        setBounds(500, 300, 450, 300);
        LaminaCalculadora lamina = new LaminaCalculadora();
        add(lamina);
        // pack(); //Este metodo adapta el tamanio del conetenedor al contenido
    }
}

class LaminaCalculadora extends JPanel {
    public LaminaCalculadora() {
        principio = true; // Conseguimos este valor en principio para conseguir que en el metodo
                          // InsertaNumero se borre el 0 inicial de la calculadora
        setLayout(new BorderLayout()); // Definir el tipo de Layout
        pantalla = new JButton("0");
        pantalla.setEnabled(false);
        add(pantalla, BorderLayout.NORTH); // Colocal el area de resultado

        lamina2 = new JPanel();
        lamina2.setLayout(new GridLayout(4, 4)); // Definir tipo de layout a seguir en el area correspondiente

        ActionListener insertar = new InsertaNumero();
        ActionListener orden = new accionOrden();

        ponerBoton("7", insertar);
        ponerBoton("8", insertar);
        ponerBoton("9", insertar);
        ponerBoton("/", orden);
        ponerBoton("4", insertar);
        ponerBoton("5", insertar);
        ponerBoton("6", insertar);
        ponerBoton("x", orden);
        ponerBoton("1", insertar);
        ponerBoton("2", insertar);
        ponerBoton("3", insertar);
        ponerBoton("-", orden);
        ponerBoton("0", insertar);
        ponerBoton(".", insertar);
        ponerBoton("=", orden);
        ponerBoton("+", orden);

        add(lamina2, BorderLayout.CENTER);// Colocar botones en el area definida
        ultimaOperacion = "=";
    }

    private void ponerBoton(String rotulo, ActionListener oyente) // Metodo para agregar botones //SETTER
    {
        JButton boton = new JButton(rotulo);
        boton.addActionListener(oyente);
        lamina2.add(boton);
    }

    private class InsertaNumero implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada = e.getActionCommand();
            if (principio) {
                pantalla.setText("");// Se borra el 0 de la pantalla
                principio = false;
            }
            pantalla.setText(pantalla.getText() + entrada);
        }

    }

    private class accionOrden implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String operacion = e.getActionCommand();
            // System.out.println(operacion); COMPROBAR QUE VAMOS BIEN
            calcular(Double.parseDouble(pantalla.getText()));
            ultimaOperacion = operacion; // se alamacena ultima operacion hecha
            principio = true;
        }

        public void calcular(Double x) {
            if (ultimaOperacion.equals("+")) {
                resultado += x;
                // System.out.println(resultado);
            } else if (ultimaOperacion.equals("-")) {
                resultado -= x;
            } else if (ultimaOperacion.equals("x")) {
                resultado *= x;
            } else if (ultimaOperacion.equals("/")) {
                resultado /= x;
            } else if (ultimaOperacion.equals("=")) {
                resultado = x;
            }

            pantalla.setText("" + resultado);
        }

    }

    private JPanel lamina2;
    private JButton pantalla;
    private boolean principio;
    private double resultado;
    private String ultimaOperacion;
}
