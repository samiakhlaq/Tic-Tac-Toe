import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class TicTacToe {
    Scanner sc = new Scanner(System.in);

    int Board_Width = 600;
    int Board_Height = 650;

    JFrame Frame = new JFrame("Tic Tac Toe");
    JLabel TextLabel = new JLabel("Tic-Tac-Toe");
    JPanel TextPanel = new JPanel();
    JPanel BoardPanel = new JPanel();

    JButton[][] Board = new JButton[3][3];
    String NamePlayerX = GetInput(1);
    String NamePlayerO = GetInput(2);
    String PlayerX = NamePlayerX.substring(0,1);
    String PlayerO = NamePlayerO.substring(0,1);
    String CurrentPlayer = PlayerX;
    int Turns = 0;

    Boolean GameOver = false;

    TicTacToe()
    {
        Frame.setVisible(true);
        Frame.setSize(Board_Width,Board_Height);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(new BorderLayout());

        TextLabel.setBackground(Color.BLACK);
        TextLabel.setForeground(Color.GREEN);
        TextLabel.setFont(new Font("Arial",Font.BOLD,50));
        TextLabel.setHorizontalAlignment(JLabel.CENTER);
        TextLabel.setOpaque(true);

        TextPanel.setLayout(new BorderLayout());
        TextPanel.add(TextLabel,BorderLayout.NORTH);
        Frame.add(TextPanel,BorderLayout.NORTH);

        BoardPanel.setLayout(new GridLayout(3,3));
        BoardPanel.setBackground(Color.BLACK);
        Frame.add(BoardPanel);

        for(int row=0; row<3; row++)
        {
            for(int col=0; col<3; col++)
            {
                JButton Tiles = new JButton();
                Board[row][col] = Tiles;
                BoardPanel.add(Tiles);

                Tiles.setBackground(Color.BLACK);
                Tiles.setForeground(Color.RED);
                Tiles.setFont(new Font("Arial",Font.BOLD,120));
                Tiles.setFocusable(false);

                Tiles.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (GameOver) return;
                        JButton Tiles = (JButton) e.getSource();

                        if(Tiles.getText() == "")
                        {
                            Tiles.setText(CurrentPlayer);
                            Turns++;
                            CheckWinner();
                            if(!GameOver)
                            {
                                CurrentPlayer = CurrentPlayer == PlayerX ? PlayerO : PlayerX;
                                TextLabel.setText(CheckPlayerName(CurrentPlayer)+"'s Turn");
                                TextLabel.setForeground(Color.YELLOW);
                            }
                        }
                    }
                });

            }
        }

    }
    public void CheckWinner()
    {
        for(int row=0; row<3; row++)
        {
            if (Board[row][0].getText() == "") continue;
            if (Board[row][0].getText() == Board[row][1].getText() &&
            Board[row][1].getText() == Board[row][2].getText())
            {
                for(int i = 0; i<3; i++)
                {
                    for(int j=0; j<3; j++)
                    {
                        if(Board[i][j].getText() != "")
                        {
                            SetLoser(Board[i][j]);
                        }
                    }
                }
                for(int i = 0 ; i<3; i++)
                {
                    SetWinner(Board[row][i]);
                }
                GameOver = true;
                return ;
            }
        }

        for(int col=0; col<3; col++)
        {
            if (Board[0][col].getText() == "") continue;
            if (Board[0][col].getText() == Board[1][col].getText() &&
            Board[1][col].getText() == Board[2][col].getText())
            {
                for(int i = 0; i<3; i++)
                {
                    for(int j=0; j<3; j++)
                    {
                        if(Board[i][j].getText() != "")
                        {
                            SetLoser(Board[i][j]);
                        }
                    }
                }
                for(int i = 0 ; i<3; i++)
                {
                    SetWinner(Board[i][col]);
                }
                GameOver = true;
                return ;
            }
        }
        
        if(Board[0][0].getText() == Board[1][1].getText() &&
        Board[1][1].getText() == Board[2][2].getText() &&
        Board[0][0].getText() != "")
        {
            for(int i = 0; i<3; i++)
                {
                    for(int j=0; j<3; j++)
                    {
                        if(Board[i][j].getText() != "")
                        {
                            SetLoser(Board[i][j]);
                        }
                    }
                }
            for(int i = 0 ; i<3; i++)
            {
                SetWinner(Board[i][i]);
            }
            GameOver = true;
            return ;
        }
        if(Board[0][2].getText() == Board[1][1].getText() &&
        Board[1][1].getText() == Board[2][0].getText() &&
        Board[0][2].getText() != "")
        {
            for(int i = 0; i<3; i++)
                {
                    for(int j=0; j<3; j++)
                    {
                        if(Board[i][j].getText() != "")
                        {
                            SetLoser(Board[i][j]);
                        }
                    }
                }
            for(int i = 0 ; i<3; i++)
            {
                SetWinner(Board[i][2-i]);
            }
            GameOver = true;
            return ;
        }
        if(Turns == 9)
        {
            for(int row=0; row<3; row++)
            {
                for(int col=0; col<3; col++)
                {
                    SetTie(Board[row][col]);
                }
            }
            GameOver = true;
        }


    }
    public void SetWinner(JButton Tiles)
    {
        Tiles.setBackground(Color.GREEN);
        Tiles.setForeground(Color.BLACK);
        TextLabel.setText("Winner: "+CheckPlayerName(CurrentPlayer));
        TextLabel.setForeground(Color.GREEN);
    }

    public void SetLoser(JButton Tiles)
    {
        Tiles.setBackground(Color.RED);
        Tiles.setForeground(Color.BLACK);   
    }

    public void SetTie(JButton Tiles)
    {
        Tiles.setBackground(Color.ORANGE);
        Tiles.setForeground(Color.BLACK);
        TextLabel.setText("Tie!!");
        TextLabel.setForeground(Color.ORANGE);
    }
    public String CheckPlayerName(String s)
    {
        if(s.equals(NamePlayerX.substring(0,1)))
        {
            return NamePlayerX.toUpperCase();
        }
        else
        {
            return NamePlayerO.toUpperCase();
        }
    }
    
    public String GetInput(int n)
    {
        return JOptionPane.showInputDialog(null,"(" + n+") Player Name / Symbol: ", "Symbol Input", JOptionPane.QUESTION_MESSAGE);
    }


    
}
