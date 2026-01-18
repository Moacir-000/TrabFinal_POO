package trabFinal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class JanelaPrincipal extends JFrame {
    //os arrays e paineis
    private ArrayList<Evento> listaEvento = new ArrayList<>();
    private Map<Evento, double[]> precosEvento = new HashMap<>();
    private CardLayout cardLayout = new CardLayout();
    private JPanel painelPrincipal = new JPanel();

    //componentes
    private JComboBox<String> comboEventosDisponiveis = new JComboBox<>();
    private JLabel lblmostraPreco = new JLabel("Selecione um evento...");

    private JTextField txtnomeAdm = new JTextField();
    private JFormattedTextField txtcpfAdm = new JFormattedTextField(criarMascaraCPF());
    private JTextField txtemailAdm = new JTextField();
    private JTextField txtnomeEvento = new JTextField();
    private JTextField txtlocalEvento = new JTextField();
    private JTextField txtdataEvento = new JTextField();
    private JTextField txtprecoEvento = new JTextField();
    private JTextField txtadpreEvento = new JTextField();

    private JTextField txtnomeCli = new JTextField();
    private JFormattedTextField txtcpfCli = new JFormattedTextField(criarMascaraCPF());
    private JTextField txtemailCli = new JTextField();
    private JComboBox<String> combotipoIngresso = new JComboBox<>(new String[]{"Comum", "VIP"});

    public JanelaPrincipal() {
        setTitle("Sistema de Bilheteria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        painelPrincipal.setLayout(cardLayout);
        JPanel telaMenu = criarTelaMenu();
        JPanel telaAdm = criartelaAdm();
        JPanel telaCli = criartelaCli();

        painelPrincipal.add(telaMenu, "MENU");
        painelPrincipal.add(telaAdm, "ADMIN");
        painelPrincipal.add(telaCli, "CLIENTE");

        add(painelPrincipal);

        carregarEventosDoArquivo();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel criarTelaMenu() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Bem-vindo, Usuário!");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnCli = new JButton("Entrar como Cliente");
        btnCli.setPreferredSize(new Dimension(200, 50));

        JButton btnAdm = new JButton("Criar Evento");
        btnAdm.setPreferredSize(new Dimension(100, 40));

        btnCli.addActionListener(e -> cardLayout.show(painelPrincipal, "CLIENTE"));
        btnAdm.addActionListener(e -> cardLayout.show(painelPrincipal, "ADMIN"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(10, 10, 40, 10);
        painel.add(titulo, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(10, 10, 20, 10);
        painel.add(btnCli, gbc);

        gbc.gridy = 2;
        painel.add(btnAdm, gbc);

        return painel;
    }

    //tela do adm
    private JPanel criartelaAdm() {
        JPanel painel = new JPanel(new BorderLayout());

        JButton btnVoltar = new JButton("Voltar ao Menu de Seleção");
        btnVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "MENU"));
        painel.add(btnVoltar, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(10, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(new JLabel("Nome:"));  form.add(txtnomeAdm);
        form.add(new JLabel("CPF:"));   form.add(txtcpfAdm);
        form.add(new JLabel("E-mail:"));    form.add(txtemailAdm);

        form.add(new JSeparator()); form.add(new JSeparator());

        form.add(new JLabel("Nome do Evento:"));    form.add(txtnomeEvento);
        form.add(new JLabel("Local do Evento:"));   form.add(txtlocalEvento);
        form.add(new JLabel("Data:"));  form.add(txtdataEvento);
        form.add(new JLabel("Preço Comum em R$:")); form.add(txtprecoEvento);
        form.add(new JLabel("Taxa Adicional VIP em R$:"));  form.add(txtadpreEvento);

        JButton btnCriar = new JButton("Publicar Evento");
        form.add(new JLabel("")); form.add(btnCriar);

        painel.add(form, BorderLayout.CENTER);

        btnCriar.addActionListener(e -> {
            try {
                if (validarcampoAdm()) {

                    double precoBase = Double.parseDouble(txtprecoEvento.getText().replace(",", "."));
                    double precoVip = Double.parseDouble(txtadpreEvento.getText().replace(",", "."));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataEvento;

                    try {
                        dataEvento = LocalDate.parse(txtdataEvento.getText(), formatter);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Data inválida! Use dd/MM/yyyy");
                        return;
                    }

                    Administrador admin = new Administrador(
                            txtnomeAdm.getText(),
                            txtcpfAdm.getText().replace(".", "").replace("-", ""),
                            txtemailAdm.getText()
                    );

                    Evento novoEvento = new Evento(
                            txtnomeEvento.getText(),
                            txtlocalEvento.getText(),
                            dataEvento,
                            admin
                    );

                    listaEvento.add(novoEvento);
                    precosEvento.put(novoEvento, new double[]{precoBase, precoVip});
                    comboEventosDisponiveis.addItem(novoEvento.getNome());

                    // SALVANDO EVENTO + ADMINISTRADOR NO ARQUIVO
                    String dadosEvento = String.format(
                            "Evento: %s | Local: %s | Data: %s | Preço base: %.2f | Preço adicional VIP: %.2f | Nome ADM: %s | CPF ADM: %s | Email ADM: %s",
                            novoEvento.getNome(),
                            novoEvento.getLocal(),
                            novoEvento.getData(),
                            precoBase,
                            precoVip,
                            admin.getNome(),
                            admin.getCpf(),
                            admin.getEmail()
                    );

                    ArquivoUtil.salvarEvento(dadosEvento);

                    JOptionPane.showMessageDialog(this, "Evento criado com sucesso!");
                    limpcampoAdm();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Apenas números nos preços!");
            }
        });


        return painel;
    }

    private JPanel criartelaCli() {
        JPanel painel = new JPanel(new BorderLayout());

        JButton btnVoltar = new JButton("Voltar ao Menu de Seleção");
        btnVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "MENU"));
        painel.add(btnVoltar, BorderLayout.NORTH);

        // Formulário
        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(new JLabel("Nome:"));       form.add(txtnomeCli);
        form.add(new JLabel("CPF:"));        form.add(txtcpfCli);
        form.add(new JLabel("E-mail:"));     form.add(txtemailCli);

        form.add(new JLabel("Selecionar Evento:"));
        form.add(comboEventosDisponiveis);

        form.add(new JLabel("Valores:"));
        lblmostraPreco.setForeground(Color.BLACK);
        lblmostraPreco.setFont(new Font("Arial", Font.BOLD, 12));
        form.add(lblmostraPreco);

        form.add(new JLabel("Tipo de Ingresso:"));
        form.add(combotipoIngresso);

        JButton btnComprar = new JButton("Finalizar Compra");
        form.add(new JLabel("")); form.add(btnComprar);
        painel.add(form, BorderLayout.CENTER);

        comboEventosDisponiveis.addActionListener(e -> atualizardisplayPreco());
        btnComprar.addActionListener(e -> {
            try {
                executarCompra();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        return painel;
    }

    private boolean validarcampoAdm() {
        if (txtnomeAdm.getText().isEmpty() ||
                txtcpfAdm.getText().contains("_") ||
                txtemailAdm.getText().isEmpty() ||
                txtnomeEvento.getText().isEmpty() ||
                txtprecoEvento.getText().isEmpty() ||
                txtadpreEvento.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!");
            return false;
        }
        return true;
    }


    private void limpcampoAdm() {
        txtnomeEvento.setText("");
        txtlocalEvento.setText("");
        txtdataEvento.setText("");
        txtprecoEvento.setText("");
        txtadpreEvento.setText("");
    }

    private void atualizardisplayPreco() {
        int index = comboEventosDisponiveis.getSelectedIndex();
        if (index >= 0 && index < listaEvento.size()) {
            Evento evento = listaEvento.get(index);
            double[] precos = precosEvento.get(evento);
            if (precos != null) {
                lblmostraPreco.setText(String.format("Comum: R$ %.2f | VIP: R$ %.2f", precos[0], precos[0] + precos[1]));
            }
        }
    }

    private void executarCompra() throws Exception {
        if (txtcpfCli.getText().contains("_")) throw new Exception("CPF do cliente incompleto!");

        if (listaEvento.isEmpty()) throw new Exception("Nenhum evento disponivel");

        int index = comboEventosDisponiveis.getSelectedIndex();

        Evento evento = listaEvento.get(index);
        double[] precos = precosEvento.get(evento);

        Cliente cliente = new Cliente(
                txtnomeCli.getText(),
                txtcpfCli.getText().replace(".", "").replace("-", ""),
                txtemailCli.getText()
        );

        Ingresso ingresso;
        String tipo = (String) combotipoIngresso.getSelectedItem();
        String cod = "COD-" + (Ingresso.totalIngressosVendidos + 1);

        if (tipo.equals("VIP")) {
            ingresso = new IngressoVIP(evento, precos[0], precos[1]);
        } else {
            ingresso = new IngressoComum(evento, precos[0]);
        }

        Venda v = new Venda();
        v.realizarVenda(cliente, ingresso);

        JOptionPane.showMessageDialog(this, "Compra feita com sucesso!");
    }

    private void carregarEventosDoArquivo() {
        ArrayList<String> eventosSalvos = ArquivoUtil.listarEventos();

        for (String linha : eventosSalvos) {

            String[] p = linha.split(";");

            if (p.length == 8) {

                String nome = p[0];
                String local = p[1];
                LocalDate data = LocalDate.parse(p[2]);

                double precoBase = Double.parseDouble(p[3]);
                double precoVip = Double.parseDouble(p[4]);

                Administrador admin = new Administrador(p[5], p[6], p[7]);

                Evento evento = new Evento(nome, local, data, admin);

                listaEvento.add(evento);
                precosEvento.put(evento, new double[]{precoBase, precoVip});
                comboEventosDisponiveis.addItem(nome);
            }
        }
    }

    private MaskFormatter criarMascaraCPF() {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            return mask;
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new JanelaPrincipal());
    }
}