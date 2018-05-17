import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;
 
import javax.swing.*;
 
public class InterfaceGrafica {
   
   
    JFrame janelaPrincipal = new JFrame ();
    JPanel painelPrincipal = new JPanel ();
   
    private String nome = "Visitante";
    private String ip;
    private Integer porta = 0;
    private Cliente c;
    private Boolean conectado = false;
//  private Vector<String> mensagens = new Vector<>();
    String mensagens = "";
   
    public InterfaceGrafica () {
        montarJanelaPrincipal();
    }
   
    public void montarJanelaPrincipal(){
       
        JPanel painelNome = new JPanel();
        JPanel painelServidor = new JPanel();
        JPanel painelChat = new JPanel();
       
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
       
        JButton selecionarNome = new JButton(new AbstractAction("Selecionar Nome") {
            public void actionPerformed(ActionEvent e) {
                mostrarJanelaSelecionaNome();
            }
        });
       
        JButton mostrarNome =  new JButton(new AbstractAction("Mostrar Nome") {
            public void actionPerformed(ActionEvent e) {
                mostrarJanelaMostraNome();
            }
        });
       
        JButton selecionarIP = new JButton(new AbstractAction("Selecionar Servidor") {
            public void actionPerformed(ActionEvent e) {
                mostrarJanelaSelecionaIP();
            }
        });
       
        JButton mostrarIP = new JButton(new AbstractAction("Exibir Servidor") {
            public void actionPerformed(ActionEvent e) {
                mostrarJanelaMostraIP();
            }
        });
       
        JButton conectar = new JButton(new AbstractAction("Conectar") {
            public void actionPerformed(ActionEvent e) {
                mostrarJanelaConectar();
            }
        });
       
        JButton chat = new JButton(new AbstractAction("XAT") {
            public void actionPerformed(ActionEvent e) {
                mostrarChat();
            }
        });
       
        painelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelServidor.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelChat.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        selecionarNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostrarNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        selecionarIP.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostrarIP.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        painelNome.add(selecionarNome);
        painelNome.add(mostrarNome);
        painelServidor.add(selecionarIP);
        painelServidor.add(mostrarIP);
        painelServidor.add(conectar);
        painelChat.add(chat);
       
       
//      painelPrincipal.add(selecionarNome);
//      painelPrincipal.add(mostrarNome);
        painelPrincipal.add(painelNome);
        painelPrincipal.add(painelServidor);
        painelPrincipal.add(painelChat);
//      painelPrincipal.add(selecionarIP);
//      painelPrincipal.add(mostrarIP);
       
        janelaPrincipal.getRootPane().setDefaultButton(selecionarNome);
 
        janelaPrincipal.add(painelPrincipal);
        janelaPrincipal.setSize(540, 540);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }
   
    public void mostrarJanelaSelecionaNome() {
        JFrame janelaNome = new JFrame();
        JPanel painelNome = new JPanel();
       
        JLabel nomeLabel = new JLabel("nome");
        JTextField nomeField = new JTextField(10);
       
        painelNome.add(nomeLabel);
        painelNome.add(nomeField);
       
        JButton adicionar = new JButton(new AbstractAction("Selecionar") {
            public void actionPerformed(ActionEvent e) {
                nome = nomeField.getText();
                janelaPrincipal.setVisible(true);
                janelaNome.setVisible(false);
            }
        });
       
        painelNome.add(adicionar);
       
        painelNome.add(new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaNome.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        }));
       
       
       
        janelaNome.add(painelNome);
        janelaNome.pack();
 
        //janelaNome.setSize(540, 540);
       
        janelaNome.getRootPane().setDefaultButton(adicionar);
 
       
        janelaNome.setLocationRelativeTo(null);
        janelaNome.setVisible(true);
        janelaPrincipal.setVisible(false);
       
    }
   
    public void mostrarJanelaMostraNome() {
       
        JFrame janelaNome = new JFrame();
        JPanel painelNome = new JPanel();
       
        JLabel nomeLabel = new JLabel(nome);
        System.out.println(nome);
       
        painelNome.add(nomeLabel);
       
        JButton voltar = new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaNome.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        });
       
        painelNome.add(voltar);
        janelaNome.add(painelNome);
        janelaNome.pack();
 
        //janelaNome.setSize(540, 540);
        janelaNome.getRootPane().setDefaultButton(voltar);
        janelaNome.setLocationRelativeTo(null);
        janelaNome.setVisible(true);
        janelaPrincipal.setVisible(false);
    }
   
    public void mostrarJanelaSelecionaIP() {
        JFrame janelaIP = new JFrame();
        JPanel painelIP = new JPanel();
       
        if (ip==null) {
            ip = "192.168.177.212";
        }
       
        if (porta==0) {
            porta = 80;
        }
       
        JLabel ipLabel = new JLabel("IP");
        JLabel portaLabel = new JLabel("Porta");
        JTextField ipField = new JTextField(ip);
        JTextField portaField = new JTextField(03);
        portaField.setText(porta.toString());
       
        painelIP.add(ipLabel);
        painelIP.add(ipField);
        painelIP.add(portaLabel);
        painelIP.add(portaField);
       
        JButton adicionar = new JButton(new AbstractAction("Selecionar") {
            public void actionPerformed(ActionEvent e) {
                String ip1 = ipField.getText();
                String portaString = portaField.getText();
                if (validaIP(ip1) && validaPorta(portaString) ) {
                    ip = ip1;
                    porta = Integer.parseInt(portaString);
                    janelaPrincipal.setVisible(true);
                    janelaIP.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(janelaIP,"IP ou Porta inválidos");
                }
            }
        });
       
        painelIP.add(adicionar);
       
        painelIP.add(new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaIP.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        }));
       
        janelaIP.add(painelIP);
        janelaIP.pack();
 
        //janelaNome.setSize(540, 540);
       
        janelaIP.getRootPane().setDefaultButton(adicionar);
        janelaIP.setLocationRelativeTo(null);
        janelaIP.setVisible(true);
        janelaPrincipal.setVisible(false);
       
    }
   
    public void mostrarJanelaMostraIP() {
       
        JFrame janelaIP = new JFrame();
        JPanel painelIP = new JPanel();
       
        JLabel ipLabel = new JLabel("IP: " + ip);
        JLabel portaLabel = new JLabel("  Porta: " + porta.toString());
       
        painelIP.add(ipLabel);
        painelIP.add(portaLabel);
       
        JButton voltar = new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaIP.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        });
       
        painelIP.add(voltar);
        janelaIP.add(painelIP);
        janelaIP.pack();
 
        //janelaNome.setSize(540, 540);
        janelaIP.getRootPane().setDefaultButton(voltar);
        janelaIP.setLocationRelativeTo(null);
        janelaIP.setVisible(true);
        janelaPrincipal.setVisible(false);
    }
   
    public void mostrarJanelaConectar() {
       
       
        JFrame janelaConectar = new JFrame();
        JPanel painelConectar = new JPanel();
       
        JLabel conectadoLabel = new JLabel();
        if (conectado == false) {
            conectadoLabel.setText("Não está conectado");
        }else {
            conectadoLabel.setText("Está conectado com" + ip);
        }
       
        painelConectar.add(conectadoLabel);
       
        JButton conectar = new JButton(new AbstractAction("Conectar") {
            public void actionPerformed(ActionEvent e) {
                    conectado = conectar();
                    janelaConectar.setVisible(false);
                    janelaPrincipal.setVisible(true);
            }
        });
       
        JButton voltar = new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaConectar.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        });
       
        painelConectar.add(conectar);
        painelConectar.add(voltar);
        janelaConectar.add(painelConectar);
        janelaConectar.pack();
 
        //janelaNome.setSize(540, 540);
        janelaConectar.setLocationRelativeTo(null);
        janelaConectar.setVisible(true);
        janelaPrincipal.setVisible(false);
       
       
    }
   
    public void mostrarChat() {
 
       
        JFrame janelaChat = new JFrame();
        janelaChat.setLayout(new BorderLayout());
        JPanel painelChat = new JPanel();
       
        JTextArea textoChat = new JTextArea();
        textoChat.setEditable(false);
        JScrollPane scroll = new JScrollPane(textoChat);
        scroll.setPreferredSize(new Dimension(400,400));
       
       
        textoChat.setText("Teste aaa \n");
       
       
       
        //conectado = conectar();
 
        if (conectado == false) {
            JOptionPane.showMessageDialog(janelaChat,"Impossivel Conectar");
        }
       
        JLabel nomeLabel = new JLabel(nome);
        JTextField mensagemField = new JTextField(10);
       
        painelChat.add(nomeLabel);
        painelChat.add(mensagemField);
       
        JButton enviar = new JButton(new AbstractAction("Enviar") {
            public void actionPerformed(ActionEvent e) {
                String mensagem = nome + ":" + mensagemField.getText();
                mensagemField.setText("");
                try {
                    conectado = conectar();
                    if (conectado == false) {
                        JOptionPane.showMessageDialog(janelaChat,"Impossivel Conectar");
                    }
                    c.executa(mensagem);
                    c.fecha();
                } catch (UnknownHostException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
               
                textoChat.append(mensagem + "\n");
            }
        });
       
        painelChat.add(enviar);
       
        painelChat.add(new JButton(new AbstractAction("Voltar") {
            public void actionPerformed(ActionEvent e) {
                janelaChat.setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        }));
       
       
       
        janelaChat.add(scroll,BorderLayout.NORTH);
        janelaChat.add(painelChat,BorderLayout.SOUTH);
        janelaChat.pack();
 
        //janelaNome.setSize(540, 540);
       
        janelaChat.getRootPane().setDefaultButton(enviar);
 
        //mensagemField.requestDefaultFocus();
        janelaChat.setLocationRelativeTo(null);
        janelaChat.setVisible(true);
        janelaPrincipal.setVisible(false);
       
    }
   
   
    //Funcoes de validacao
    public boolean validaIP(String ip) {
        if(ip.length()==15) {
            return true;
        }
       
        return false;
    }
   
    public boolean validaPorta(String porta) {
        if(porta.length()>10) {
            return false;
        }else {
            for (int i = 0; i < porta.length(); i++) {
                int n = porta.charAt(i)-'0';
                if(n>9 || n<0) {
                    return false;
                }
            }
            return true;
        }
    }
   
    public boolean conectar() {
        c = new Cliente(ip, porta);
        try {
            c.conecta();
            return true;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();;
            return false;
        }
    }
   
   
}