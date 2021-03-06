package punto_venta_java;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
 * @author Equipo 4
 */
public class Punto_Venta_Java extends javax.swing.JFrame {

    public static String[][] arreglo;
    public static int num_venta;

    public static boolean bool = false;
    public static String[] arreglo2 = new String[4];

    public static double total = 0;
    String cantidad_pattern = "((?<id>([0-9]+)))\\*(?<cantidad>([0-9]+))";
    String cantidad_uno = "((?<id>([0-9]+)))";

    java.util.Date fecha = new Date();

    public Punto_Venta_Java() throws IOException {
        initComponents();
        setLayout(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize, ySize);
        getContentPane().setBackground(new Color(192, 192, 255));
        table.setBounds(0, 0, xSize, ySize);

        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc1 = tcm.getColumn(0);
        TableColumn tc2 = tcm.getColumn(1);
        TableColumn tc3 = tcm.getColumn(2);
        TableColumn tc4 = tcm.getColumn(3);
        tc1.setHeaderValue("Precio");
        tc2.setHeaderValue("Nombre");
        tc3.setHeaderValue("Cantidad");
        tc4.setHeaderValue("Total");
        th.repaint();
        introducirDatos.setText("\n");

        date.setText("" + fecha);

        recargaBTN.setIcon(Foto("/img/phone.png"));
        cuenta.setIcon(Foto("/img/cuenta.png"));

        //Codigo para obtener la ultima venta realizada
        try {
            String numero = sendPOST("https://aloapq.000webhostapp.com/ventas_last_id.php");
            String numero2 = numero.replaceAll("\t", "");
            int num_venta2 = Integer.parseInt(numero2) + 1;
            num_venta = num_venta2;
            //
        } catch (Exception e) {
            System.out.println("No se pudo conectar con la base de datos. Problemas con el Server"
                    + "Intentar otra vez");
        }

        //Codigo para jalar los datos del archivo php de la base de datos
        //Aqui jala literal la linea completa del php y lo guarda en el string resultado
        String resultado = sendPOST("https://aloapq.000webhostapp.com/productos_lista.php");
        System.out.println("Aqui esta la linea completa: \n"+resultado);
        String[] productos_array;
        //Aqui aplicas un split para guardar en un arreglo cada valor antes de la "," 
        productos_array = resultado.split(",");
        //Aqui el primer valor esta mal y solo le quitas la tabulacion del principio
        productos_array[0] = productos_array[0].replaceAll("\t", "");
        System.out.println("Aqui ya estan los productos en un arreglo: \n"+Arrays.toString(productos_array));
        //De aqui comienza el proceso de acomodarlo pa que quede chido
        String[][] productos = new String[productos_array.length / 3][3];
        int i = 0;
        int j = 0;
        //Aqui recorres el arreglo productos_array que es el que tiene todos los productos
        //y como tu sabes que cada 3 inicia un nuevo producto (esa es la clave)
        while (i < productos_array.length) {
            productos[j][0] = productos_array[i];
            productos[j][1] = productos_array[i + 1];
            productos[j][2] = productos_array[i + 2];
            i = i + 3;
            j++;
        }
        arreglo = productos;
        System.out.println("y aqui ya estan acomodados: \n"+ Arrays.deepToString(productos));
        //hasta aqui

    }

    public ImageIcon Foto(String ruta) {
        ImageIcon picture = new ImageIcon(getClass().getResource(ruta));
        Image image = picture.getImage(); // transform it
        Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        picture = new ImageIcon(newimg);  // transform it back
        return picture;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        introducirDatos = new javax.swing.JTextPane();
        totallabel = new javax.swing.JLabel();
        cuenta = new javax.swing.JButton();
        recargaBTN = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 102));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        introducirDatos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        introducirDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                introducirDatosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(introducirDatos);

        totallabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totallabel.setText("Total = ");

        cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentaActionPerformed(evt);
            }
        });

        recargaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargaBTNActionPerformed(evt);
            }
        });

        date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date.setText(".");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("SISTEMA DE PUNTO DE VENTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(414, 414, 414)
                .addComponent(cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(recargaBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totallabel, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(554, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(355, 355, 355)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(date)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totallabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(recargaBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(cuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel2)
                    .addContainerGap(831, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed


    }//GEN-LAST:event_tableKeyPressed

    private void introducirDatosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_introducirDatosKeyPressed

        //introducirDatos.setText("");
        if (evt.VK_ENTER == evt.getKeyCode()) {
            String texto = introducirDatos.getText();
            String remove = texto.substring(1, texto.length());
            introducirDatos.setText("");
            Pattern p = Pattern.compile(cantidad_pattern);
            Matcher m = p.matcher(remove);
            Pattern p2 = Pattern.compile(cantidad_uno);
            Matcher m2 = p2.matcher(remove);
            int indexOf = remove.indexOf("*");
            try {
                String id = remove.substring(0, indexOf);
                String cantidad = remove.substring(indexOf + 1, remove.length());
                for (int i = 0; i < arreglo.length; i++) {
                    if (m.matches()) {
                        if (id.equals(arreglo[i][0])) {
                            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                            Object[] fila = new Object[6];
                            fila[0] = arreglo[i][2];
                            fila[1] = arreglo[i][1];
                            fila[2] = cantidad;
                            fila[3] = Float.parseFloat(arreglo[i][2]) * Float.parseFloat(cantidad);
                            modelo.addRow(fila);
                            table.setModel(modelo);
                            total += Double.parseDouble(arreglo[i][2]) * Double.parseDouble(cantidad);
                            totallabel.setText("Total = " + total);
                        }
                    }
                }
            } catch (Exception e) {

            }

            if (m2.matches()) {
                for (int i = 0; i < arreglo.length; i++) {
                    if (remove.equals(arreglo[i][0])) {
                        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                        Object[] fila = new Object[6];
                        fila[0] = arreglo[i][2];
                        fila[1] = arreglo[i][1];
                        fila[2] = 1 + "";
                        fila[3] = Float.parseFloat(arreglo[i][2]);
                        modelo.addRow(fila);
                        table.setModel(modelo);
                        total += Double.parseDouble(arreglo[i][2]);
                        totallabel.setText("Total = " + total);
                    }
                }
            }

        } else if (evt.VK_ESCAPE == evt.getKeyCode()) {
            try {

                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                String temp = table.getValueAt(table.getRowCount() - 1, 3).toString();
                total -= Double.parseDouble(temp);
                totallabel.setText("Total = " + total);
                modelo.removeRow(table.getRowCount() - 1);
                table.setModel(modelo);
            } catch (Exception e) {

            }
        } else if (evt.VK_P == evt.getKeyCode()) {
            try {
                imprimirTicket();
                introducirDatos.setText("" + "\n");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_introducirDatosKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentaActionPerformed
        try {
            // TODO add your handling code here:
            //JOptionPane.showMessageDialog(this, "¿Seguro que desea terminar?");
            imprimirTicket();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cuentaActionPerformed

    private void recargaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recargaBTNActionPerformed
        // TODO add your handling code here:
        recargas recarga;
        try {
            recarga = new recargas();
            recarga.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_recargaBTNActionPerformed

    public void imprimirTicket() throws FileNotFoundException, IOException {

        //Para guardar el archivo en el mismo paquete
        File miDir = new File(".");
        File archivo = new File(miDir.getCanonicalPath() + "/src/tickets/ticket" + num_venta + ".txt");
        archivo.createNewFile();

        // File archivo = new File("C:/Users/Martin Espinoza/Desktop/Insert_Ventas.txt");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);

            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
            int rows = modelo.getRowCount();
            int columns = modelo.getColumnCount();

            pw.println("\t \t \t \t Abarrotes Covid");
            pw.println(fecha);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    pw.print("\t" + modelo.getValueAt(i, j) + "\t" + "|");
                }
                pw.println("");
                pw.println("--------------------------------------------------"
                        + "-----------------------------------");

            }
            pw.println("\t" + "Total = " + total);

            //guardar en base de datos
            guarda_db();
            //borrar datos de la tabla
            limpiar_tabla();

            JOptionPane.showMessageDialog(this, "Imprimiento Ticket");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static void guarda_db() throws IOException {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        int rows = modelo.getRowCount();
        String numero = sendPOST("https://aloapq.000webhostapp.com/ventas_last_id.php");
        String numero2 = numero.replaceAll("\t", "");
        int num_venta2 = Integer.parseInt(numero2) + 1;
        num_venta = num_venta2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < arreglo.length; j++) {
                if (arreglo[j][1].equals(modelo.getValueAt(i, 1))) {
                    sendGET("https://aloapq.000webhostapp.com/Insert_Ventas.php?"
                            + "num_venta=" + num_venta + "&id_producto_venta=" + arreglo[j][0] + "&cantidad_venta="
                            + modelo.getValueAt(i, 2));
                }
            }
            //en caso de que sea una recarga
            if (modelo.getValueAt(i, 1).equals("Recarga: Telcel")
                    || modelo.getValueAt(i, 1).equals("Recarga: Movistar")
                    || modelo.getValueAt(i, 1).equals("Recarga: AT&T")) {

                int x = 0;
                if (modelo.getValueAt(i, 3).toString().equals("100.0")) {
                    x = 10;
                } else if (modelo.getValueAt(i, 3).toString().equals("200.0")) {
                    x = 11;
                } else if (modelo.getValueAt(i, 3).toString().equals("500.0")) {
                    x = 12;
                }

                sendGET("https://aloapq.000webhostapp.com/Insert_Ventas.php?"
                        + "num_venta=" + num_venta + "&id_producto_venta=" + x + "&cantidad_venta="
                        + modelo.getValueAt(i, 2));
            }
        }

    }

    public void limpiar_tabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
            int filas = table.getRowCount();
            for (int i = 0; i <= filas; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public static final String USER_AGENT = "Mozilla/5.0";

    public static void sendGET(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        //System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static String sendPOST(String POST_URL) throws IOException {
        String result = "";
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        //os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        //System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            result = response.toString();
        } else {
            System.out.println("POST request not worked");
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Punto_Venta_Java.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Punto_Venta_Java.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Punto_Venta_Java.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Punto_Venta_Java.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Punto_Venta_Java().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Punto_Venta_Java.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cuenta;
    private javax.swing.JLabel date;
    private javax.swing.JTextPane introducirDatos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton recargaBTN;
    public static javax.swing.JTable table;
    public static javax.swing.JLabel totallabel;
    // End of variables declaration//GEN-END:variables

}
