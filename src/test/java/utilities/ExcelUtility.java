package utilities;

//import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.support.Colors;

public class ExcelUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String xlsheet) throws IOException
	{
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
		
	}
	public int getColumnCount(String xlsheet, int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int cellcount=ws.getRow(rownum).getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
	}
	public String getCellData(String xlsheet, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(column);
		wb.close();
		fi.close();
		String data;
		try {
			//data=cell.toString(); //1st method or
			DataFormatter formatter= new DataFormatter(); //2nd method
			data=formatter.formatCellValue(cell); //Returns formatted value as a string regardless of cell type
		}
		catch(Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
		
	}
	public void setCellData(String xlsheet, int rownum, int column, String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())  				//if file not exists then create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(xlsheet)==1);  ///If sheet not exist then create new sheet
			wb.createSheet(xlsheet);
		ws=wb.getSheet(xlsheet);
		
		if(ws.getRow(rownum)==null);  ///If row not exist then create new row
			ws.createRow(rownum);	
		row=ws.getRow(rownum);
		
		cell=row.createCell(column);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	
	}
	public void fillGreenColor(String xlsheet, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(column);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public void fillRedColor(String xlsheet, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(column);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	
	}
}
