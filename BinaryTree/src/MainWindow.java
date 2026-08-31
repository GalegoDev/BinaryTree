import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class MainWindow extends JFrame {
    private static final String MENU = "menu";
    private static final String REGISTER = "register";
    private static final String SEARCH = "search";
    private static final String LIST = "list";
    private static final String CAMPUSES = "campuses";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    private JComboBox<String> registerCampusCombo;
    private JTextField registerNameField;

    private JTextField searchNameField;
    private JLabel searchResultLabel;

    private JComboBox<String> listCampusCombo;
    private DefaultListModel<String> studentsListModel;

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }

    public MainWindow() {
        setTitle("FIAP - Cadastro de Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(640, 480));
        setLocationRelativeTo(null);

        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(createMenuPanel(), MENU);
        contentPanel.add(createRegisterPanel(), REGISTER);
        contentPanel.add(createSearchPanel(), SEARCH);
        contentPanel.add(createListPanel(), LIST);
        contentPanel.add(createCampusPanel(), CAMPUSES);

        add(contentPanel, BorderLayout.CENTER);
        showScreen(MENU);
    }

    private void showScreen(String screenName) {
        cardLayout.show(contentPanel, screenName);
    }

    private JPanel createHeaderPanel(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 45, 98));
        header.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 22f));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setForeground(new Color(200, 220, 240));
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.PLAIN, 13f));

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);

        return header;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(createHeaderPanel(
                "Sistema de Cadastro de Alunos",
                "Gerenciamento de alunos por campus usando Árvore Binária de Busca"
        ), BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(32, 24, 32, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(8, 0, 8, 0);

        gbc.gridy = 0;
        buttonsPanel.add(createMenuButton("Cadastrar Aluno", REGISTER), gbc);
        gbc.gridy = 1;
        buttonsPanel.add(createMenuButton("Buscar Aluno", SEARCH), gbc);
        gbc.gridy = 2;
        buttonsPanel.add(createMenuButton("Listar Alunos por Campus", LIST), gbc);
        gbc.gridy = 3;
        buttonsPanel.add(createMenuButton("Listar Campuses", CAMPUSES), gbc);
        gbc.gridy = 4;
        buttonsPanel.add(createMenuButton("Sair", null), gbc);

        panel.add(buttonsPanel, BorderLayout.CENTER);
        return panel;
    }

    private JButton createMenuButton(String text, String screen) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(320, 44));
        button.setFocusPainted(false);

        if (screen == null) {
            button.addActionListener(e -> System.exit(0));
        } else {
            button.addActionListener(e -> showScreen(screen));
        }

        return button;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(createHeaderPanel("Cadastrar Aluno", "Insira o campus e o nome do aluno"), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(32, 24, 16, 24));

        registerCampusCombo = new JComboBox<>(new DefaultComboBoxModel<>(getCampusArray()));
        registerNameField = new JTextField(24);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 8, 12);
        formPanel.add(new JLabel("Campus:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(registerCampusCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Nome do aluno:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(registerNameField, gbc);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(createActionBar(
                "Cadastrar",
                () -> {
                    String campusName = (String) registerCampusCombo.getSelectedItem();
                    String name = registerNameField.getText().trim();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Informe um nome válido.",
                                "Cadastro inválido",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    if (Main.registerStudent(name, campusName)) {
                        registerNameField.setText("");
                        JOptionPane.showMessageDialog(
                                this,
                                "Aluno cadastrado com sucesso em " + campusName + ".",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Este aluno já está cadastrado em algum campus.",
                                "Cadastro duplicado",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                },
                MENU
        ), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(createHeaderPanel("Buscar Aluno", "Pesquise o aluno em todos os campuses"), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(32, 24, 16, 24));

        searchNameField = new JTextField(24);
        searchResultLabel = new JLabel(" ");
        searchResultLabel.setForeground(new Color(0, 45, 98));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 8, 12);
        formPanel.add(new JLabel("Nome do aluno:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(searchNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        formPanel.add(searchResultLabel, gbc);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(createActionBar(
                "Buscar",
                () -> {
                    String name = searchNameField.getText().trim();

                    if (name.isEmpty()) {
                        searchResultLabel.setText("Informe um nome para busca.");
                        return;
                    }

                    String campusName = Main.findStudentCampus(name);

                    if (campusName != null) {
                        searchResultLabel.setText("Aluno encontrado no campus " + campusName + ".");
                    } else {
                        searchResultLabel.setText("Aluno não encontrado.");
                    }
                },
                MENU
        ), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(createHeaderPanel(
                "Listar Alunos",
                "Exibição em ordem alfabética (percurso em-ordem da árvore)"
        ), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new BorderLayout(0, 16));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 16, 24));

        listCampusCombo = new JComboBox<>(new DefaultComboBoxModel<>(getCampusArray()));

        JPanel campusRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        campusRow.setBackground(Color.WHITE);
        campusRow.add(new JLabel("Campus:"));
        campusRow.add(listCampusCombo);

        studentsListModel = new DefaultListModel<>();
        JList<String> studentsList = new JList<>(studentsListModel);
        JScrollPane scrollPane = new JScrollPane(studentsList);
        scrollPane.setPreferredSize(new Dimension(400, 220));

        formPanel.add(campusRow, BorderLayout.NORTH);
        formPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(createActionBar(
                "Listar",
                () -> {
                    String campusName = (String) listCampusCombo.getSelectedItem();
                    List<String> students = Main.getStudentsByCampus(campusName);

                    studentsListModel.clear();

                    if (students.isEmpty()) {
                        studentsListModel.addElement("Não há aluno cadastrado neste campus.");
                    } else {
                        for (String student : students) {
                            studentsListModel.addElement(student);
                        }
                    }
                },
                MENU
        ), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCampusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(createHeaderPanel("Campuses FIAP", "Unidades disponíveis para cadastro"), BorderLayout.NORTH);

        DefaultListModel<String> campusListModel = new DefaultListModel<>();
        for (String campusName : Main.getCampusList()) {
            campusListModel.addElement(campusName);
        }

        JList<String> campusList = new JList<>(campusListModel);
        JScrollPane scrollPane = new JScrollPane(campusList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(24, 24, 16, 24));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createActionBar(null, null, MENU), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createActionBar(String primaryAction, Runnable primaryHandler, String backScreen) {
        JPanel actionBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        actionBar.setBackground(new Color(245, 247, 250));
        actionBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 225, 230)));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showScreen(backScreen));
        actionBar.add(backButton);

        if (primaryAction != null && primaryHandler != null) {
            JButton primaryButton = new JButton(primaryAction);
            primaryButton.addActionListener(e -> primaryHandler.run());
            actionBar.add(primaryButton);
        }

        return actionBar;
    }

    private String[] getCampusArray() {
        List<String> campusList = Main.getCampusList();
        return campusList.toArray(new String[0]);
    }
}
