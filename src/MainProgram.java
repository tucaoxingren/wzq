import javax.swing.JOptionPane;


public class MainProgram {

	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		Object[] possibleValues = { "�˻�ģʽ", "˫�˶�ս", "������ս" }; 
		try{
			Object selectedValue = JOptionPane.showInputDialog(null, 
					"��ѡ��һ�ֶ�սģʽ", "��������Ϸ",JOptionPane.INFORMATION_MESSAGE,
					null,possibleValues, possibleValues[0]);
			
			if(selectedValue.equals("˫�˶�ս"))
			{
				Gobang twoAginst = new Gobang();
				twoAginst.init();
			}
			else if (selectedValue.equals("�˻�ģʽ"))
			{
				ComouterPlay manRboot = new ComouterPlay();
				manRboot.init();
			}
		}catch(ArrayIndexOutOfBoundsException e){}
	}

}