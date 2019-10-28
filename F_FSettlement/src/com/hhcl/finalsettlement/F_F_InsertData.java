package com.hhcl.finalsettlement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.Util;


public class F_F_InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public F_F_InsertData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("doPost"+request.getParameter("empid"));
		HttpSession session=request.getSession();
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	response.setContentType("text/html");
	
	String message="Invalid Data please check and re-submit";
		
	PrintWriter pw=response.getWriter();
	response.setContentType("text/html");
	Map params=new HashMap();
	Map params_2=new HashMap();
	ArrayList components=new ArrayList();
	ArrayList components_two=new ArrayList();
	con=Util.getConnection();
	try {
		con.setAutoCommit(false);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	/*Enumeration en=request.getParameterNames();

	while(en.hasMoreElements())
	{
		Object objOri=en.nextElement();
		String param=(String)objOri;
		String value=request.getParameter(param);
		System.out.println(value+":::"+param);
		pw.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");
	}*/		
		//pw.close();	
	
	String Key_temp=null;
	 Enumeration keys = request.getParameterNames();
	   while (keys.hasMoreElements() )
	   {
	      String key = (String)keys.nextElement();
	      String value = request.getParameter(key);
	    //  System.out.println(value);
	  
	      if(key.indexOf("_P")!=-1){
	      Key_temp=key.replace("_P", "");
	      components.add(Key_temp);
	      }
	      
	  /*    if(key.indexOf("_H")!=-1){
		      Key_temp=key.replace("_H", "");
		      components.add(Key_temp);
		      }*/
	      
	      /*if(key.indexOf("_A")!=-1){
		  Key_temp=key.replace("_A", "");
		  }*/
	      Key_temp=key.replace("_P", "");
	      params.put(key, value);
	      
	   }
	
	System.out.println(components.toString()+"::"+params.toString());

	   
	   String Key_temp_two=null;
		 Enumeration keys2 = request.getParameterNames();
		   while (keys2.hasMoreElements() )
		   {
		      String key_2 = (String)keys2.nextElement();
		      String value = request.getParameter(key_2);
		    //  System.out.println(value);
		  
		      if(key_2.indexOf("_H")!=-1){
		    	  Key_temp_two=key_2.replace("_H", "");
		      components_two.add(Key_temp_two);
		      }
		      
		      Key_temp_two=key_2.replace("_H", "");
		      params_2.put(key_2, value);
		      
		   }
		   System.out.println(components_two.toString()+"::"+params_2.toString());
	   
	   

	String empid=(String) session.getAttribute("empid");
	String empname=request.getParameter("empname");
	String doj=request.getParameter("doj");
	String department=request.getParameter("department");
	String designation=request.getParameter("designation");
	String gross_month=request.getParameter("gross_month");
	String emp_resig=request.getParameter("emp_resig");
	String emp_relieving=request.getParameter("emp_relieving");
	String last_payroll_date=request.getParameter("last_payroll_date");
	String resig_date=request.getParameter("resig_date");
	String relieving_date=request.getParameter("relieving_date");
	String experience=request.getParameter("experience");
	String total_days=request.getParameter("total_days");
	String lop=request.getParameter("lop");
	String total_payable_days=request.getParameter("total_payable_days");
	String payable_basedays=request.getParameter("payable_basedays");
	String payperiod_months_count=request.getParameter("payperiod_months_count");
	String available_earning_leaves=request.getParameter("available_earning_leaves");
	String ctc=request.getParameter("ctc");
	String manual_earning_leaves=request.getParameter("manual_earning_leaves");
	
	String CTCEarning=request.getParameter("CTCEarning");
	String CTCDeduction=request.getParameter("CTCDeduction");
	String ctc_ded=request.getParameter("ctc_ded");
	String OTH_Ear=request.getParameter("OTH_Ear");
	String OTH_Ded=request.getParameter("OTH_Ded");
	String OTH_Others=request.getParameter("OTH_Others");
	String holdsalary=request.getParameter("holdsalary");
	String final_total=request.getParameter("final_total");
	String final_total_final=request.getParameter("final_total_final");
	String OTH_Others_d_c=request.getParameter("OTH_Others_d_c");
	
	
	
	String ComponentTitle="NA";
	String ComponentTypeID="NA";
	
	Map paramTitles=new HashMap();

	paramTitles=(Map) session.getAttribute("paramTitles");
	
//System.out.println(paramTitles);

	int a=0,b=0,c=0,d=0;
	
	int sno=0;
   String EmployeeF_F = "INSERT INTO test.tbl_f_f_employee_basicdata "
   		+ "(EMPLOYEEID, EMPLOYEESEQUENCENO,CALLNAME, DOJ, DEPARTMENT, DESIGNATION, GROSS, DATEOFRESIGNATION, LASTWORKINGDATE,"
   		+ "PAYROLL_ENDDATE, PAYMENT_CONSIDER_FROM, PAYMENT_CONSIDER_TO, CURRENT_EXPERIENCE, TOTAL_DAYS, LOP, TOTAL_PAYABLE_DAYS,"
   		+ "MONTHLY_PAYABLE_DAYS, PAYPERIOD_COUNT, AVAILABLE_EL, MANUAL_EL, CTC_CONSIDERATION) VALUES "
   		+ "((SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO="+empid+"),?,?,"
   				+ "DATE_FORMAT(STR_TO_DATE(?,'%d.%m.%Y'),'%Y-%m-%d'),?,?,?,DATE_FORMAT(STR_TO_DATE(?,'%d.%m.%Y'),'%Y-%m-%d')"
   				+ ",DATE_FORMAT(STR_TO_DATE(?,'%d.%m.%Y'),'%Y-%m-%d'),?,DATE_FORMAT(STR_TO_DATE(?,'%d.%m.%Y'),'%Y-%m-%d'),"
   				+ "DATE_FORMAT(STR_TO_DATE(?,'%d.%m.%Y'),'%Y-%m-%d'),?,?,?,?,?,?,?,?,?)";   
   try {
        PreparedStatement ps = con.prepareStatement(EmployeeF_F.toString(),Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, empid);
        ps.setString(2, empname);
        ps.setString(3, doj);
        ps.setString(4, department);
        ps.setString(5, designation);
        ps.setString(6, gross_month);
        ps.setString(7, emp_resig);
        ps.setString(8, emp_relieving);
        ps.setString(9, last_payroll_date);
        ps.setString(10, resig_date);
        ps.setString(11, relieving_date);
        ps.setString(12, experience);
        ps.setString(13, total_days);
        ps.setString(14, lop);
        ps.setString(15, total_payable_days);
        ps.setString(16, payable_basedays);
        ps.setString(17, payperiod_months_count);
        ps.setString(18, available_earning_leaves);
        ps.setString(19, manual_earning_leaves);
        ps.setString(20, ctc);
        
         a=ps.executeUpdate(); 
		 rs = ps.getGeneratedKeys();
		 
		 while (rs.next()) {
         	
         	sno=rs.getInt(1);
             System.out.println("Key returned from getGeneratedKeys():"
                     + rs.getInt(1));
         } 
        System.out.println("Employee Executed"+sno);
        
      }
      catch (Exception q) {
        q.printStackTrace();
      }



   
   
   Iterator<String> namesIterator=components.iterator();
   
   
   String CTCData=" INSERT INTO test.tbl_fandf_components (SNO, EMPLOYEEID, COMPONENTID,COMPONENTNAME, COMPONENTTILE, ACTUAL, PAYABLE) "
   		+ "VALUES (?,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?),?,?,?,?,?)";
   
   PreparedStatement ps;
   double component_actual=0,comp_netvalue=0;
   
try {
	ps = con.prepareStatement(CTCData.toString());
	
	while(namesIterator.hasNext()){
		
		String Comp=namesIterator.next().toString();

	//	System.out.println(params.get(Comp).toString() +"~~~~");
		
	   ps.setInt(1, sno);
	   ps.setString(2, empid);
	   ps.setString(3, Comp);
	try{   
	   if(params.get(Comp+"_A").toString()==null){
		   component_actual=0;
	   }else{
		   component_actual=Double.parseDouble(params.get(Comp+"_A").toString());
	   }
	}catch(Exception er){
		component_actual=0;
	}
	
	try{   
		   if(params.get(Comp+"_P").toString()==null){
			   comp_netvalue=0;
		   }else{
			   comp_netvalue=Double.parseDouble(params.get(Comp+"_P").toString());
		   }
		}catch(Exception er){
			 comp_netvalue=0;
		}
	
	try{   
		ComponentTitle=paramTitles.get(Comp+"_T").toString();
		ComponentTypeID=paramTitles.get(Comp+"_TYPE").toString();
		
	//	System.out.println(paramTitles.get(Comp+"_T").toString());
		  
	}catch(Exception er){
			
		}
	
	   ps.setString(4, ""+paramTitles.get(Comp+"_T").toString()+"");   
	   ps.setString(5, ""+paramTitles.get(Comp+"_TYPE").toString()+"");
	
	   ps.setString(6, ""+component_actual+"");   
	   ps.setString(7, ""+comp_netvalue+"");
	 

	   ps.addBatch();
	   b=ps.executeUpdate(); 
	}
	
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}catch(Exception err){
	err.printStackTrace();
}
 
/*Iterator<String> namesIterator_hold=components_two.iterator();
String PaiID="000";

String HoldPayperiod="INSERT INTO test.tbl_holdpayperiod_salary (SNO, EMPLOYEEID, PAYPERIOD, NETVALUE, TOTAL_SALARY ) VALUES (?,?,?,?,?)";

try{
	
ps = con.prepareStatement(HoldPayperiod.toString());
	
	while(namesIterator_hold.hasNext()){
		
		String Comp1=namesIterator_hold.next().toString();

	//	System.out.println(params.get(Comp).toString() +"~~~~");
		
	   ps.setInt(1, sno);
	   ps.setString(2, empid);
	   ps.setString(3, Comp1);
	   
	   try{   
		   if(params_2.get(Comp1+"_H").toString()==null){
			   PaiID="0";
		   }else{
			   PaiID=params_2.get(Comp1+"_H").toString();
		   }
		}catch(Exception er){
			PaiID="0";
		}
	   
	   ps.setString(4, ""+PaiID+"");
	   ps.setString(5, "0");
	   
	   ps.addBatch();
	   c=ps.executeUpdate();
	}
	
}catch(SQLException e){
	
}catch(Exception err){
	err.printStackTrace();
}*/
  



 
String fandf_finalinsert="INSERT INTO test.tbl_fandf_final_total "
		+ "(SNO, EMPLOYEEID, CTC_GROSS, CTC_DEDUCTION, DEDUCTION_CONSIDERED, MONTHLY_EARNINGS, MONTHLY_DEDUCTION, OTHERS, HOLD_SALARY, TOTAL, FINAL_TOTAL) "
		+ " VALUES (?,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?),?,?,?,?,?,?,?,?,?) ";

try{
	
ps = con.prepareStatement(fandf_finalinsert.toString());

ps.setInt(1, sno);
ps.setString(2,  empid);
ps.setString(3,  CTCEarning);
ps.setString(4,  CTCDeduction);
ps.setString(5, ctc_ded);
ps.setString(6,  OTH_Ear);
ps.setString(7,  OTH_Ded);
ps.setString(8,  OTH_Others);
ps.setString(9,  holdsalary);
ps.setString(10, final_total);
ps.setString(11, final_total);

//ps.addBatch();
d=ps.executeUpdate();
}catch(SQLException eq){
	eq.printStackTrace();
}catch(Exception err){
	err.printStackTrace();
}


if(a>0 && b>0 && d>0){
	
	try {
		
		String FFS_DETAILS="INSERT INTO HCLHRM_PROD.TBL_EMPLOYEE_FFS_DETAILS (EMPLOYEEID,AMOUNT,CHEQUENO,CHEQUEDATE,BANKID) "
				+ "VALUES ((SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?),?,0,DATE(NOW()),'1') "
				+ " ON DUPLICATE KEY UPDATE EMPLOYEEID=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?),AMOUNT=?,CHEQUEDATE=DATE(NOW())  ";
		
		ps = con.prepareStatement(FFS_DETAILS.toString());
		ps.setString(1,empid);
		ps.setString(2,final_total);
		ps.setString(3,empid);
		ps.setString(4,final_total);
		
		ps.executeUpdate();
	
		String Employee_Primary_bkp=" INSERT INTO HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY_HISTORY (EMPLOYEEID, COSTCENTERID, FIRSTNAME, LASTNAME, CALLNAME, GENDER, TITLE, "
				+ "DATEOFBIRTH, STATUS, COMPANYID, EMPLOYMENTTYPEID, EMPLOYEESEQUENCENO, APPLICANTID, OFFERLETTERID, TRANSFERTRANSACTIONID, CREATEDBY, DATECREATED, "
				+ "MODIFIEDBY, DATEMODIFIED, VERIFIEDBY, DATEVERIFIED, LOGID, PREVLUPDATE) "
				+ "(SELECT EMPLOYEEID, COSTCENTERID, FIRSTNAME, LASTNAME, CALLNAME, GENDER, TITLE, DATEOFBIRTH, STATUS, COMPANYID, EMPLOYMENTTYPEID, EMPLOYEESEQUENCENO,"
				+ " APPLICANTID, OFFERLETTERID,0, CREATEDBY, DATECREATED, MODIFIEDBY, DATEMODIFIED, VERIFIEDBY, DATEVERIFIED, LOGID, LUPDATE "
				+ "FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?) ";
		
		ps = con.prepareStatement(Employee_Primary_bkp.toString());
		ps.setString(1,empid);
		
		ps.executeUpdate();
		
		
		String Employee_Primary_update="UPDATE HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY SET STATUS=1092 WHERE EMPLOYEESEQUENCENO=?";

		ps = con.prepareStatement(Employee_Primary_update.toString());
		ps.setString(1,empid);
		
		ps.executeUpdate();
		
		String Update_Bonus_status="UPDATE hclhrm_prod.tbl_employee_bonus SET STATUS=1002 "
				+ "WHERE EMPLOYEEID IN (SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=?) AND STATUS=1001";
		
		ps = con.prepareStatement(Update_Bonus_status.toString());
		ps.setString(1,empid);
		
		ps.executeUpdate();
		con.commit();
		con.setAutoCommit(true);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	message="F&F Successfully processed ...!";
}else{
	
	try {
		con.rollback();
		message="Invalid Data please check and re-submit";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message="Invalid Data please check and re-submit";
	}	 
}
request.setAttribute("message",message);
RequestDispatcher rd=request.getRequestDispatcher("full_final.jsp");  
//servlet2 is the url-pattern of the second servlet  

rd.forward(request, response);//method may be include or forward  
		//response.sendRedirect("full_final.jsp");
		
	}

}
