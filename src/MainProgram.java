import javax.swing.JOptionPane;


public class MainProgram {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		Object[] possibleValues = { "人机模式", "双人对战", "联机对战" }; 
		try{
			Object selectedValue = JOptionPane.showInputDialog(null, 
					"请选择一种对战模式", "五子棋游戏",JOptionPane.INFORMATION_MESSAGE,
					null,possibleValues, possibleValues[0]);
			
			if(selectedValue.equals("双人对战"))
			{
				Gobang twoAginst = new Gobang();
				twoAginst.init();
			}
			else if (selectedValue.equals("人机模式"))
			{
				ComouterPlay manRboot = new ComouterPlay();
				manRboot.init();
			}
		}catch(ArrayIndexOutOfBoundsException e){}
	}

}