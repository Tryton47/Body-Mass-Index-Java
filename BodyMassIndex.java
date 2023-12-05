//framework javaswing dan java.awt sebagai GUInya
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//memperluas(extended) methodnya menggunakan JFrame
public class BodyMassIndex extends JFrame
{
    //variabel akses private untuk input tinggi badan & berat badan
    private JTextField beratTextField;
    private JTextField tinggiTextField;

    public BodyMassIndex()
    {
        //Set GUInya
        setTitle("Body Mass Index");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);


        //icon GUInya
        ImageIcon iconnya = new ImageIcon("images/OIP.png");
        setIconImage(iconnya.getImage());

        //Setup Panel/Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(204,204,255)); //ungu muda untuk background

        //Label input (komponen)
        JLabel beratLabel = new JLabel("Berat (kg)");
        JLabel tinggiLabel = new JLabel("Tinggi (cm)");

        
        beratTextField = new JTextField(10);
        beratTextField.setPreferredSize(new Dimension(150, 30));
        tinggiTextField = new JTextField(10);
        tinggiTextField.setPreferredSize(new Dimension(150, 30));
        
        JButton hitungButton = new JButton("Lihat Hasil");

        //Style komponen
        beratTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        tinggiTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        hitungButton.setFont(new Font("Arial", Font.BOLD, 14));
        hitungButton.setForeground(Color.WHITE);
        hitungButton.setBackground(new Color(76,0,153)); //ungu tua

        //Menambahkan komponen ke main Panel
        mainPanel.add(beratLabel);
        mainPanel.add(beratTextField);
        mainPanel.add(tinggiLabel);
        mainPanel.add(tinggiTextField);
        mainPanel.add(hitungButton);

        //menambahkan main Panel ke dalam frame
        add(mainPanel);

        //Fungsi button
        hitungButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                hitungBMI();
            }
        });
    }

    //Membuat fungsi untuk hitungBMI
    private void hitungBMI()
    {
        try
        {
            //Input berat dan tingginya
            double berat = Double.parseDouble(beratTextField.getText());
            double tinggi = Double.parseDouble(tinggiTextField.getText());

            //Untuk default satuan tingginya Meter jadi saya convert ke Cm
            double tinggiMeter = tinggi / 100.0;

            //Rumus BMI
            double bmi = berat / (tinggiMeter * tinggiMeter);

            //untuk hasil tampilkan menggunakan popup
            String kategoriBmi = determinekategoriBmi(bmi);
            JOptionPane.showMessageDialog(this, "BMI kamu: " + String.format("%.2f" , bmi) + "\nKategori: " + kategoriBmi, "Hasil BMI", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Input Salah! Masukan tinggi dan berat dengan angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String determinekategoriBmi(double bmi)
    {
        if (bmi < 18.5)
        {
            return "Kamu Kurangan Berat Badan";
        }
        else if (bmi >= 18.5 && bmi <= 24.9)
        {
            return "Berat Badan Kamu Normal";
        }
        else if (bmi >= 25 && bmi <= 29.9)
        {
            return "Kamu Kelebihan Berat Badan";
        }
        else
        {
            return "Kamu Sedang Obesitas jadi Kurangi Berat Badanmu!!";
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run()
            {
                new BodyMassIndex().setVisible(true);
            }
        });
    }
}
