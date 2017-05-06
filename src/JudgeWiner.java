
public class JudgeWiner {
	
	public boolean pd_qp(int xPos,int yPos,String[][] board,int flag,int BOARD_SIZE){
		int win = 0;
		int i,hang,lie;
		String qz="╋";
		hang = xPos;
		lie = yPos;
		String qp[][] = new String[BOARD_SIZE][BOARD_SIZE];
		qp=board;
		if (flag % 2 == 0)
			qz = "○";
		else
			qz = "●";
		{
			//向上数5个棋子
			for(i=0;i<5;i++){
				if(hang==-1 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				hang=hang-1;
			}
			if (win>=5)
				return true;
			//向下数5个棋子
			hang = xPos;
			lie = yPos;
			win=win-1;
			
			for(i=0;i<5;i++){
				if(hang==15 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				hang=hang+1;
			}
			if (win>=5)
				return true;
			//向左数5个棋子
			hang = xPos;
			lie = yPos;
			win=0;
			
			for(i=0;i<5;i++){
				if(lie==-1 || qp[hang][lie]!=qz)//
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie-1;
			}
			if (win>=5)
				return true;
			//向右数5个棋子
			hang = xPos;
			lie = yPos;
			win=win-1;
			
			for(i=0;i<5;i++){
				if(lie==15 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie+1;
			}
			if (win>=5)
				return true;
			//向左上数5个棋子
			win=0;
			hang = xPos;
			lie = yPos;
			
			for(i=0;i<5;i++){
				if(lie==-1 || hang==-1 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie-1;
				hang=hang-1;
			}
			if (win>=5)
				return true;
			//向右下数5个棋子
			hang = xPos;
			lie = yPos;
			win=win-1;
			
			for(i=0;i<5;i++){
				if(lie==15 || hang==15 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie+1;
				hang=hang+1;
			}
			if (win>=5)
				return true;	
			//向右上数5个棋子
			hang = xPos;
			lie = yPos;
			win=0;
			
			for(i=0;i<5;i++){
				if(lie==15 || hang==-1 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie+1;
				hang=hang-1;
			}
			if (win>=5)
				return true;
			//向左下数5个棋子
			hang = xPos;
			lie = yPos;
			win=win-1;
			
			for(i=0;i<5;i++){
				if(lie==-1 || hang==15 || qp[hang][lie]!=qz)
					break;
				else
					if (qp[hang][lie]==qz)
						win=win+1;
				lie=lie-1;
				hang=hang+1;
			}
			if (win>=5)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
