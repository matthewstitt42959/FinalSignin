package report;

import java.awt.Desktop;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import backend.JDBC_Connect;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class PrinttoJasper {

	
	// Connection Variables
		private JDBC_Connect connect = null;
		//private ResultSet rs = null;
		//private PreparedStatement pst = null; 
		private Connection conn = null;
		
	// private Timer t;
	//private Map<String, Object> startMap;
	//private Map<String, Object> endMap; 
	
	public void selectStudentRpt(String startDate, String endDate){
		/*try{
			
		connect = new JDBC_Connect();
		conn = connect.connect(); 
		
		Map<String, Object> parametersMap = new HashMap<String, Object>();  
		Object id = startDate;
		parametersMap.put("id",id);
		
		System.out.println("Loading Report Designs");
		InputStream input = new FileInputStream(new File("Resources/StudentRpt.jrxml"));
		JasperDesign jasperDesign = JRXmlLoader.load(input);

		System.out.println("Compiling Report Designs");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		System.out.println("Creating JasperPrint Object");
		JOptionPane
		.showMessageDialog(null,
				"Creating JasperPrint Object");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ReportTitle", "PDF JasperReport");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametersMap,conn);

		//Exporting the report
		FileOutputStream output = new FileOutputStream(new File("Resources/StudentReport.pdf"));

		JasperExportManager.exportReportToPdfStream(jasperPrint, output);

		System.out.println("Report Generation Complete");
		JOptionPane
		.showMessageDialog(null,
				"Report Generation Complete");
		
		conn.close();
		output.close();
		
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (JRException e) {
		e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DisplayStudentReport();*/
		
		}

	public void selectCarinaRpt(String startDate, String endDate) {
		try{
			
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			
			Map<String, Object> parametersMap = new HashMap<String, Object>();  
			//Map<String, Object> parametersMap2 = new HashMap<String, Object>(); 
			
			Object sdate = "select log.UIN, log.DATE, internet, printscan, msoffice, testing, studying, tutor " + 
					"from session_log log, report where log.UIN = report.UIN AND LOG.DATE BETWEEN " + "'" + startDate + "'" + 
					" And " + "'" + endDate + "'" +	" ORDER BY DATE";
			
			//Object edate = endDate; 
			parametersMap.put("sdate",sdate);
			//parametersMap2.put("edate", edate); 
			
			System.out.println("Loading Report Designs");
			InputStream input = new FileInputStream(new File("Resources/CarinaRpt.jrxml"));
			JasperDesign jasperDesign2 = JRXmlLoader.load(input);

			System.out.println("Compiling Report Designs");

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign2);

			System.out.println("Creating JasperPrint Object");
			JOptionPane
			.showMessageDialog(null,
					"Creating JasperPrint Object");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("ReportTitle", "PDF JasperReport");

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametersMap,conn);
			          
			
			//Exporting the report
			FileOutputStream output = new FileOutputStream(new File("Resources/CarinaReport.pdf"));

			JasperExportManager.exportReportToPdfStream(jasperPrint, output);

			System.out.println("Report Generation Complete");
			JOptionPane
			.showMessageDialog(null,
					"Report Generation Complete");
			
			conn.close();
			output.close();
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (JRException e) {
			e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DisplayCarinaReport();
			
			}

	public void DisplayStudentReport(){
				 
		try {
			 
			File pdfFile = new File("Resources/StudentReport.pdf");
			if (pdfFile.exists()) {
	 
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}
	 
			} else {
				System.out.println("File is not exists!");
			}
	 
			System.out.println("Done");
	 
		  } catch (Exception ex) {
			ex.printStackTrace();
		  }
	 
		}
	
	public void DisplayCarinaReport(){
		 
		try {
			 
			File pdfFile = new File("Resources/CarinaReport.pdf");
			if (pdfFile.exists()) {
	 
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}
	 
			} else {
				System.out.println("File is not exists!");
			}
	 
			System.out.println("Done");
	 
		  } catch (Exception ex) {
			ex.printStackTrace();
		  }
	 
		}
	
	
}
