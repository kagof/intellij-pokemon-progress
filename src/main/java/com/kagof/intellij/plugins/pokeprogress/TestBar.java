package com.kagof.intellij.plugins.pokeprogress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestBar {

    public static final String PATH = "com/kagof/intellij/plugins/pokeprogress/sprites/";;

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel dimensionPanel;
    private JTextField xShift;
    private JTextField yShift;
    private JComboBox<Pokemon> pokemonComboBox;
    private JProgressBar progressBar;
    private JButton updateButton;

    private Pokemon selectedPokemon;

    public TestBar() {
        selectedPokemon = Pokemon.MIMIKYU;
        initializePokemonIcons();

        frame = new JFrame();
        initializeDimensionPanel();
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 200));
        contentPanel.setLayout(new GridLayout(4, 1));

        initializePokemonComboBox();
        contentPanel.add(pokemonComboBox);

        initializeProgressBar();
        contentPanel.add(progressBar);
        contentPanel.add(dimensionPanel);

        initializeUpdateButton();
        contentPanel.add(updateButton);

        frame.setTitle("Pokemon ProgressBar TestUI");
        frame.setContentPane(contentPanel);
        frame.setSize(new Dimension(400, 200));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void initializePokemonComboBox() {
        pokemonComboBox = new JComboBox<>(Pokemon.values());
        pokemonComboBox.setSelectedItem(selectedPokemon);
        pokemonComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon = (Pokemon)((JComboBox<Pokemon>)e.getSource()).getSelectedItem();
                initializePokemonIcons();
                xShift.setText(selectedPokemon.getXShift() + "");
                yShift.setText(selectedPokemon.getYShift() + "");
                progressBar.setUI(new PokemonProgressBarUi(selectedPokemon));
                frame.repaint();
            }

        });
    }

    private void initializePokemonIcons() {
        selectedPokemon.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(PATH + selectedPokemon.getName().toLowerCase() + ".gif")));
        selectedPokemon.setIconR(new ImageIcon(this.getClass().getClassLoader().getResource(PATH + selectedPokemon.getName().toLowerCase() + "_r.gif")));
    }

    private void initializeUpdateButton() {
        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            updatePositionAndUI();
        });
    }

    private void updatePositionAndUI() {
        try{
            selectedPokemon.setXShift(Integer.parseInt(xShift.getText()));
            selectedPokemon.setYShift(Integer.parseInt(yShift.getText()));
            progressBar.setUI(new PokemonProgressBarUi(selectedPokemon));
            frame.repaint();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void initializeProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setUI(new PokemonProgressBarUi(selectedPokemon));
        progressBar.setValue(50);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
    }

    private void initializeDimensionPanel() {
        dimensionPanel = new JPanel();
        dimensionPanel.setLayout(new GridLayout(1, 4));
        xShift = new JTextField();
        yShift = new JTextField();
        xShift.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon.setXShift(Integer.parseInt(xShift.getText()));
                selectedPokemon.setYShift(Integer.parseInt(yShift.getText()));
                frame.repaint();
            }
        });
        yShift.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon.setXShift(Integer.parseInt(xShift.getText()));
                selectedPokemon.setYShift(Integer.parseInt(yShift.getText()));
                frame.repaint();
            }
        });
        dimensionPanel.add(new JLabel("xShift"));
        dimensionPanel.add(xShift);
        dimensionPanel.add(new JLabel("yShift"));
        dimensionPanel.add(yShift);
        xShift.setText(selectedPokemon.getXShift() + "");
        yShift.setText(selectedPokemon.getYShift() + "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestBar();
            }
        });
    }
}
