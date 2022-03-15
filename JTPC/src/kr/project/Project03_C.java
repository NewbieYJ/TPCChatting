package kr.project;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class Project03_C {

	public static void main(String[] args) {
		
		String fileName="cellDataType.xls";
		
		try(FileInputStream fis=new FileInputStream(fileName)) {
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rows=sheet.rowIterator(); //해당하는 줄 생성
			
			while(rows.hasNext()) {
				HSSFRow row=(HSSFRow)rows.next();
				Iterator<Cell> cells=row.cellIterator(); //한 칸씩 읽음
				
				while(cells.hasNext()) {
					HSSFCell cell=(HSSFCell)cells.next();
					CellType type=cell.getCellType(); //셀 타입 검증
					
					if(type==CellType.STRING) {
						System.out.println("["+cell.getRowIndex()+","
						        + cell.getColumnIndex()+"] = STRING; Value=" 
						    	+ cell.getRichStringCellValue().toString()); 
					}else if(type==CellType.NUMERIC) {
						System.out.println("["+cell.getRowIndex()+","
								+ cell.getColumnIndex()+"] = NUMERIC; Value=" 
								+ cell.getNumericCellValue());
					}else if(type==CellType.BOOLEAN) {
						System.out.println("["+cell.getRowIndex()+","
								+ cell.getColumnIndex()+"] = BOOLEAN; Value=" 
								+ cell.getBooleanCellValue());
					}else if(type==CellType.BLANK) {
						//비어있는 칸 검증
						System.out.println("["+cell.getRowIndex()+","
								+ cell.getColumnIndex()+"] = BLANK CELL");
					}
					
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}