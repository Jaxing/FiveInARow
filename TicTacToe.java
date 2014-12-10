package tictactoe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tictactoe.Tile.TileState;

public class TicTacToe extends JFrame implements ActionListener {

	private JPanel gamePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel turnPanel = new JPanel();

	private JLabel turnLabel = new JLabel("Cross turn");

	private JButton newGame = new JButton("New Game");
	private JButton cancle = new JButton("Cancle");

	private Icon circle = new ImageIcon("circle1.png");
	private Icon cross = new ImageIcon("cross.png");

	private int turn = 0;

	private final int WIDTH = 10;
	private final int HEIGHT = 10;
	private Tile[][] gameboard = new Tile[WIDTH][HEIGHT];

	public TicTacToe() {
		setLocation(200, 200);
		setSize(400, 400);
		add(buttonPanel, BorderLayout.SOUTH);
		add(turnPanel, BorderLayout.NORTH);
		add(gamePanel);
		gamePanel.setLayout(new GridLayout(WIDTH, HEIGHT));
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(newGame);
		buttonPanel.add(cancle);
		turnPanel.add(turnLabel);
		newGame.addActionListener(this);
		cancle.addActionListener(this);
		newGame();
	}

	private void newGame() {
		gamePanel.removeAll();
		for (int i = 0; i < gameboard.length; i++) {// clears the board
			for (int k = 0; k < gameboard[i].length; k++) {
				gameboard[i][k] = new Tile(i, k);
			}
		}
		for (int i = 0; i < gameboard.length; i++) {// clears the board
			for (int k = 0; k < gameboard[i].length; k++) {
				gameboard[i][k].setTileState(TileState.EMPTY);
			}
		}
		for (int i = 0; i < gameboard.length; i++) {
			for (int k = 0; k < gameboard[i].length; k++) {
				gamePanel.add(gameboard[i][k]);
				gameboard[i][k].addActionListener(this);
			}
		}

		revalidate();
	}

	private boolean isFiveInRow(int indexW, int indexH) {
		if (isPosOK(indexW, indexH)) {
			if (isEmpty(indexW, indexH)) {
				return false;
			}
			return isFiveInRowRek(indexW, indexH + 1,
					gameboard[indexW][indexH].getTileState(), "D", 1)
					|| isFiveInRowRek(indexW, indexH - 1,
							gameboard[indexW][indexH].getTileState(), "D", 1)
					|| isFiveInRowRek(indexW - 1, indexH,
							gameboard[indexW][indexH].getTileState(), "L", 1)
					|| isFiveInRowRek(indexW + 1, indexH,
							gameboard[indexW][indexH].getTileState(), "R", 1)
					|| isFiveInRowRek(indexW - 1, indexH + 1,
							gameboard[indexW][indexH].getTileState(), "DL", 1)
					|| isFiveInRowRek(indexW + 1, indexH + 1,
							gameboard[indexW][indexH].getTileState(), "DR", 1)
					|| isFiveInRowRek(indexW - 1, indexH - 1,
							gameboard[indexW][indexH].getTileState(), "UL", 1)
					|| isFiveInRowRek(indexW - 1, indexH + 1,
							gameboard[indexW][indexH].getTileState(), "UR", 1);
		}
		return false;
	}

	private boolean isPosOK(int indexW, int indexH) {
		if (indexW > WIDTH - 1 || indexH > HEIGHT - 1 || indexW < 0
				|| indexH < 0) {
			return false;
		}
		return true;
	}

	private boolean isEmpty(int indexW, int indexH) {
		return gameboard[indexW][indexH].getTileState() == TileState.EMPTY;
	}

	private boolean isFiveInRowRek(int indexW, int indexH, TileState prevState,
			String direction, int index) {
		if (isPosOK(indexW, indexH)) {

			if (isEmpty(indexW, indexH)) {
				return false;
			}
			if (index == 4) {
				return prevState == gameboard[indexW][indexH].getTileState();
			}
			if (direction.equals("DR") && indexH < WIDTH && indexW < HEIGHT) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW + 1, indexH + 1, prevState,
								direction, index + 1);
			} else if (direction.equals("D") && indexH < HEIGHT) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW, indexH + 1, prevState,
								direction, index + 1);
			} else if (direction.equals("R") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW + 1, indexH, prevState,
								direction, index + 1);
			} else if (direction.equals("L") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW - 1, indexH, prevState,
								direction, index + 1);
			} else if (direction.equals("DL") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW - 1, indexH + 1, prevState,
								direction, index + 1);
			} else if (direction.equals("UL") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW - 1, indexH - 1, prevState,
								direction, index + 1);
			} else if (direction.equals("UR") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW + 1, indexH - 1, prevState,
								direction, index + 1);
			} else if (direction.equals("U") && indexW < WIDTH) {
				return prevState == gameboard[indexW][indexH].getTileState()
						&& isFiveInRowRek(indexW, indexH - 1, prevState,
								direction, index + 1);
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		System.out.println("source");

		if (source.getClass() == newGame.getClass()) {
			System.out.println("button");
			JButton button = (JButton) source;
			if (button.getText().equals("New Game")) {
				System.out.println("new game");
				newGame();
			} else if (button.getText().equals("Cancle")) {
				System.exit(0);
			}

		} else if (source.getClass() == gameboard[0][0].getClass()) {
			Tile tile = (Tile) source;
			if (tile.getTileState() == TileState.EMPTY) {

				if (turn == 0) {
					tile.setIcon(cross);
					tile.setTileState(TileState.CROSS);
					turn++;
					turnLabel.setText("Circle turn");
					revalidate();
					System.out.println(tile.getTileState());

					if (isFiveInRow((int) tile.getPoint().getX(), (int) tile
							.getPoint().getY())) {
						JOptionPane.showMessageDialog(cancle, "GameOver");
						System.exit(0);

					}
				} else {
					tile.setIcon(circle);
					tile.setTileState(TileState.CIRCLE);
					turn--;
					turnLabel.setText("Cross turn");
					revalidate();
					System.out.println(tile.getTileState());
					if (isFiveInRow((int) tile.getPoint().getX(), (int) tile
							.getPoint().getY())) {
						JOptionPane.showMessageDialog(cancle, "GameOver");
						System.exit(0);

					}
				}
			}
		}

	}

}
