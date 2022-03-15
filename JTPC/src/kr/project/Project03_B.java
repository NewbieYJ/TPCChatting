package kr.project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

public class Project03_B {

	public static void main(String[] args) {
		
		try {
			Workbook wb=new HSSFWorkbook(); //�޸𸮿� ����
			Sheet sheet=wb.createSheet("My Sample Excel"); //��Ʈ�����
			InputStream is=new FileInputStream("pingsam.jpg");
			byte[] bytes=IOUtils.toByteArray(is); //�̹����� ����Ʈ ������ �о ������ �迭�� ����
			int pictureIdx=wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			is.close();
			
			//�׸��� �����ͼ� ȭ�鿡 �ѷ���
			CreationHelper helper=wb.getCreationHelper(); //������ ������� �����ִ� �޼ҵ�
			Drawing drawing=sheet.createDrawingPatriarch(); //����� ��ü����
			ClientAnchor anchor=helper.createClientAnchor(); //��ġ����
			anchor.setCol1(1); //1��° �÷��� 2��°��
			anchor.setRow1(2);
			anchor.setCol2(2); //2��° �÷��� 3��°��
			anchor.setRow2(3);
			//������ ��ġ�� �̹��� ����
			Picture poct=drawing.createPicture(anchor, pictureIdx);
			
			Cell cell=sheet.createRow(2).createCell(1);
			int w=20*256; //�� �ϳ��� 256���� 1
			sheet.setColumnWidth(1, w);
			
			short h=120*20;
			cell.getRow().setHeight(h); //���� ����(�÷��� ���̰�)
			
			FileOutputStream fileOut=new FileOutputStream("myFile.xls"); //Excel ���� ����(Refresh)
			wb.write(fileOut); //���Ͽ� �̹��� �����
			fileOut.close();
			System.out.println("�̹��� ������ ����");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}