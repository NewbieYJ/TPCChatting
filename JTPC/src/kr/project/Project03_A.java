package kr.project;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import kr.soldesk.ExcelVO;

public class Project03_A {

	public static void main(String[] args) {
		
		String fileName="bookList.xls";
		
		List<ExcelVO> data=new ArrayList<ExcelVO>();
		
		try {
			FileInputStream fis=new FileInputStream(fileName);
			
			HSSFWorkbook workbook=new HSSFWorkbook(fis); //input�� bookList.xls������ �޸𸮿� �����Ŵ
			HSSFSheet sheet=workbook.getSheetAt(0); //�޸𸮿� sheet����
			Iterator<Row> rows=sheet.rowIterator(); //��Ʈ�� �ִ� �� ��ȸ�Ͽ� ���� ��ȯ
			rows.next(); //ù��°�� ��������
			String[] imsi=new String[5]; //�÷��ȿ� �����͸� �����ͼ� ������ ������
			
			while(rows.hasNext()) {
				HSSFRow row=(HSSFRow)rows.next(); //�� ��������
				Iterator<Cell> cells=row.cellIterator(); //���� �÷� ��ȸ�Ͽ� ���� ��ȯ
				int i=0;
				//���� ������ ��������
				while(cells.hasNext()) {
					HSSFCell cell=(HSSFCell)cells.next(); //�� �б�
					imsi[i]=cell.toString();
					i++;
				}
				//VOŬ������ ����
				ExcelVO vo=new ExcelVO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]); 
				//�÷��ǿ� ������ ��ü ���
				data.add(vo);
				
			}
			showExcelData(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showExcelData(List<ExcelVO> data) {
		for(ExcelVO vo:data) {
			System.out.println(vo);
		}
	}
	
}