/**
 * Description:
 * <br/>Copyright (C), 2001-2017, tucaoxingren
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:单机五子棋
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
	// 下面三个位图分别代表棋盘、黑子、白子
	BufferedImage table;
	BufferedImage black;
	BufferedImage white;
	// 当鼠标移动时候的选择框
	BufferedImage selected;
	// 定义棋盘的大小
	private static int BOARD_SIZE = 15;
	// 定义棋盘宽、高多少个像素
	private final int TABLE_WIDTH = 535;//535
	private final int TABLE_HETGHT = 626;//536
	// 定义棋盘坐标的像素值和棋盘数组之间的比率。
	private final int RATE = TABLE_WIDTH / BOARD_SIZE;
	// 定义棋盘坐标的像素值和棋盘数组之间的偏移距。
	private final int X_OFFSET = 5;
	private final int Y_OFFSET = 95;//6,55
	// 定义一个二维数组来充当棋盘
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
	// 五子棋游戏的窗口
	JFrame f = new JFrame("五子棋游戏");
	MainFrame MF = new MainFrame(f,TABLE_WIDTH,TABLE_HETGHT);
	// 五子棋游戏棋盘对应的Canvas组件
	ChessBoard chessBoard = new ChessBoard();
	// 当前选中点的坐标
	private int selectedX = -1;
	private int selectedY = -1;
		
	int flag = 0; //偶数为黑方(玩家)，奇数为白方(电脑)
	String player = "黑";//玩家标记
	boolean flagwins = false;//玩家胜出标记
	int xPos;int yPos;
	Object selectedValue;
	public void init()throws Exception
	{
		table = ImageIO.read(new File("image/board.jpg"));
		black = ImageIO.read(new File("image/black.gif"));
		white = ImageIO.read(new File("image/white.gif"));
		selected = ImageIO.read(new File("image/selected.gif"));
		
		// 把每个元素赋为"╋"，"╋"代表没有棋子
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				board[i][j] = "╋";
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
						// 将用户鼠标事件的坐标转换成棋子数组的坐标。
						xPos = (int)((e.getX() - X_OFFSET) / RATE);//xPos
						yPos = (int)((e.getY() - Y_OFFSET ) / RATE);//yPos

						try
						{
							if (board[xPos][yPos] == "╋")
							{
								board[xPos][yPos] = "○";					
								flag++;
								player = "白";
							}
						}
						catch (ArrayIndexOutOfBoundsException e1){}
					}
					// 当鼠标退出棋盘区后，复位选中点坐标
					public void mouseExited(MouseEvent e)
					{
						selectedX = -1;
						selectedY = -1;
						chessBoard.repaint();
					}
					public void mousePressed(MouseEvent e)
					{
						if(e.getX() >= 410 && e.getX() <= 466 && e.getY() >= 20 && e.getY() <= 50)
						{//关于
							JOptionPane.showMessageDialog(null, 
									"修        改：吐槽星人\n联系邮箱：tucaoxingren@gmail.com", 
									"关于游戏", JOptionPane.INFORMATION_MESSAGE);
						}
						else if (e.getX() >= 65 && e.getX() <= 111 && e.getY() >= 20 && e.getY() <= 50)
						{//悔棋
							chessBoard.regretChess();
							chessBoard.repaint();
						}
						else if (e.getX() >= 201 && e.getX() <= 309 && e.getY() >= 20 && e.getY() <= 50)
						{//重新开始
							chessBoard.clear();
							chessBoard.repaint();
						}
					}
				});
				chessBoard.addMouseMotionListener(new MouseMotionAdapter()
				{
					// 当鼠标移动时，改变选中点的坐标
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
		// 重写JPanel的paint方法，实现绘画
		public void paint(Graphics g)
		{
			// 将绘制五子棋棋盘
			g.drawImage(table , 0 , 0 , null);
			
			//设置字体格式
			g.setFont(new Font("黑体",Font.BOLD,20));
			//输出玩家信息
			String playertemp = player;
			if (flag != 0)
			{
				if(flag % 2 ==0)
				{
					playertemp = "黑";
				}
				else
				{
					playertemp = "白";
				}
			}
			else
				playertemp = "黑";
			
			if(flagwins)
			{
				if (playertemp.equals("黑"))
					playertemp = "白";
				else
					playertemp = "黑";
				g.drawString("恭喜"+playertemp+"方玩家获胜", 265, 94);
			}
			
			g.drawString("请"+playertemp+"方落子", 30, 94);
			
			// 绘制选中点的红框
			if (selectedX >= 0 && selectedY >= 0)//selectedY >=0
				g.drawImage(selected , selectedX * RATE + X_OFFSET ,
			selectedY * RATE + Y_OFFSET, null);
			// 遍历数组，绘制棋子。
			for (int i = 0 ; i < BOARD_SIZE ; i++)
			{
				for ( int j = 0 ; j < BOARD_SIZE ; j++)
				{
					// 绘制黑棋
					if (board[i][j].equals("●"))
					{
						g.drawImage(black , i * RATE + X_OFFSET
							, j * RATE + Y_OFFSET, null);
					}
					// 绘制白棋
					if (board[i][j].equals("○"))
					{
						g.drawImage(white, i * RATE  + X_OFFSET
							, j * RATE  + Y_OFFSET, null);
					}
				}
			}
		}

		public void regretChess() {
			// TODO 自动生成的方法存根
			board[xPos][yPos] = "╋";
			flag++;	
		}

		public void clear() {
			// TODO 自动生成的方法存根
			for(int k=0;k<board.length;++k)
				for(int j=0;j<board.length;++j)
					board[k][j]="╋";
			selectedX=selectedY=-1;
			flag = 0;
			flagwins = false;
		}
	}
}