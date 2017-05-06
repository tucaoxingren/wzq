/**
 * Description:
 * <br/>Copyright (C), 2001-2017, tucaoxingren
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:����������
 * <br/>Date:2017/08/13
 * @author tucaoxingren tucaoxingren@gmail.com
 * @version 2.0
 */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComouterPlay
{
	// ��������λͼ�ֱ�������̡����ӡ�����
	BufferedImage table;
	BufferedImage black;
	BufferedImage white;
	// ������ƶ�ʱ���ѡ���
	BufferedImage selected;
	// �������̵Ĵ�С
	private static int BOARD_SIZE = 15;
	// �������̿��߶��ٸ�����
	private final int TABLE_WIDTH = 535;//535
	private final int TABLE_HETGHT = 626;//536
	// �����������������ֵ����������֮��ı��ʡ�
	private final int RATE = TABLE_WIDTH / BOARD_SIZE;
	// �����������������ֵ����������֮���ƫ�ƾࡣ
	private final int X_OFFSET = 5;
	private final int Y_OFFSET = 95;//6,55
	// ����һ����ά�������䵱����
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
	// ��������Ϸ�Ĵ���
	JFrame f = new JFrame("��������Ϸ");
	MainFrame MF = new MainFrame(f,TABLE_WIDTH,TABLE_HETGHT);
	// ��������Ϸ���̶�Ӧ��Canvas���
	ChessBoard chessBoard = new ChessBoard();
	// ��ǰѡ�е������
	private int selectedX = -1;
	private int selectedY = -1;
		
	int flag = 0; //ż��Ϊ�ڷ�(���)������Ϊ�׷�(����)
	String player = "��";//��ұ��
	boolean flagwins = false;//���ʤ�����
	int xPos;int yPos;
	Object selectedValue;
	public void init()throws Exception
	{
		table = ImageIO.read(new File("image/board.jpg"));
		black = ImageIO.read(new File("image/black.gif"));
		white = ImageIO.read(new File("image/white.gif"));
		selected = ImageIO.read(new File("image/selected.gif"));
		
		// ��ÿ��Ԫ�ظ�Ϊ"��"��"��"����û������
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				board[i][j] = "��";
			}
		}
		if(flag % 2 == 0)
		{
			chessBoard.setPreferredSize(new Dimension(
					TABLE_WIDTH , TABLE_HETGHT));
				//chessBoard.repaint();
				chessBoard.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						// ���û�����¼�������ת����������������ꡣ
						xPos = (int)((e.getX() - X_OFFSET) / RATE);//xPos
						yPos = (int)((e.getY() - Y_OFFSET ) / RATE);//yPos

						try
						{
							if (board[xPos][yPos] == "��")
							{
								board[xPos][yPos] = "��";					
								flag++;
								player = "��";
							}
						}
						catch (ArrayIndexOutOfBoundsException e1){}
					}
					// ������˳��������󣬸�λѡ�е�����
					public void mouseExited(MouseEvent e)
					{
						selectedX = -1;
						selectedY = -1;
						chessBoard.repaint();
					}
					public void mousePressed(MouseEvent e)
					{
						if(e.getX() >= 410 && e.getX() <= 466 && e.getY() >= 20 && e.getY() <= 50)
						{//����
							JOptionPane.showMessageDialog(null, 
									"��        �ģ��²�����\n��ϵ���䣺tucaoxingren@gmail.com", 
									"������Ϸ", JOptionPane.INFORMATION_MESSAGE);
						}
						else if (e.getX() >= 65 && e.getX() <= 111 && e.getY() >= 20 && e.getY() <= 50)
						{//����
							chessBoard.regretChess();
							chessBoard.repaint();
						}
						else if (e.getX() >= 201 && e.getX() <= 309 && e.getY() >= 20 && e.getY() <= 50)
						{//���¿�ʼ
							chessBoard.clear();
							chessBoard.repaint();
						}
					}
				});
				chessBoard.addMouseMotionListener(new MouseMotionAdapter()
				{
					// ������ƶ�ʱ���ı�ѡ�е������
					public void mouseMoved(MouseEvent e)
					{
						selectedX = (e.getX() - X_OFFSET) / RATE;
						selectedY = (e.getY() - Y_OFFSET) / RATE;
						chessBoard.repaint();
					}
				});
				f.add(chessBoard);
				f.pack();
				f.setVisible(true);
				}
		}
		
	public static void main(String[] args)throws Exception
	{
		ComouterPlay gb = new ComouterPlay();
		gb.init();
	}
	@SuppressWarnings("serial")
	class ChessBoard extends JPanel
	{
		// ��дJPanel��paint������ʵ�ֻ滭
		public void paint(Graphics g)
		{
			// ����������������
			g.drawImage(table , 0 , 0 , null);
			
			//���������ʽ
			g.setFont(new Font("����",Font.BOLD,20));
			//��������Ϣ
			String playertemp = player;
			if (flag != 0)
			{
				if(flag % 2 ==0)
				{
					playertemp = "��";
				}
				else
				{
					playertemp = "��";
				}
			}
			else
				playertemp = "��";
			
			if(flagwins)
			{
				if (playertemp.equals("��"))
					playertemp = "��";
				else
					playertemp = "��";
				g.drawString("��ϲ"+playertemp+"����һ�ʤ", 265, 94);
			}
			
			g.drawString("��"+playertemp+"������", 30, 94);
			
			// ����ѡ�е�ĺ��
			if (selectedX >= 0 && selectedY >= 0)//selectedY >=0
				g.drawImage(selected , selectedX * RATE + X_OFFSET ,
			selectedY * RATE + Y_OFFSET, null);
			// �������飬�������ӡ�
			for (int i = 0 ; i < BOARD_SIZE ; i++)
			{
				for ( int j = 0 ; j < BOARD_SIZE ; j++)
				{
					// ���ƺ���
					if (board[i][j].equals("��"))
					{
						g.drawImage(black , i * RATE + X_OFFSET
							, j * RATE + Y_OFFSET, null);
					}
					// ���ư���
					if (board[i][j].equals("��"))
					{
						g.drawImage(white, i * RATE  + X_OFFSET
							, j * RATE  + Y_OFFSET, null);
					}
				}
			}
		}

		public void regretChess() {
			// TODO �Զ����ɵķ������
			board[xPos][yPos] = "��";
			flag++;	
		}

		public void clear() {
			// TODO �Զ����ɵķ������
			for(int k=0;k<board.length;++k)
				for(int j=0;j<board.length;++j)
					board[k][j]="��";
			selectedX=selectedY=-1;
			flag = 0;
			flagwins = false;
		}
	}
}