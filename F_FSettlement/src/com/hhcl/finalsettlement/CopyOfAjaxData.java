package com.hhcl.finalsettlement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Util;


public class CopyOfAjaxData extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public CopyOfAjaxData() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer EmployeeBasicData=new StringBuffer();
		ArrayList<String> al=new ArrayList<String>();
		PrintWriter out=response.getWriter();
		
		String type=request.getParameter("type");
		
		String empid=request.getParameter("employeeid");
		
		
		con=Util.getConnection();
		
		if(con!=null){

			try{
				con.setAutoCommit(false);
	
				
				if(type.equalsIgnoreCase("basicdata")){
					
				
				EmployeeBasicData.append(" SELECT A.EMPLOYEESEQUENCENO,A.CALLNAME,B.DATEOFJOIN,D.NAME Department,E.NAME Designation,G.GROSS");
				EmployeeBasicData.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
				EmployeeBasicData.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID ");
				EmployeeBasicData.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON A.EMPLOYEEID=C.EMPLOYEEID ");
				EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT D ON C.DEPARTMENTID=D.DEPARTMENTID ");
				EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION E ON C.DESIGNATIONID=E.DESIGNATIONID ");
				EmployeeBasicData.append(" LEFT JOIN ( ");
				EmployeeBasicData.append(" select distinct max(ctctransactionid) CTCID, EMPLOYEEID ");
				EmployeeBasicData.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC ");
				EmployeeBasicData.append(" GROUP BY EMPLOYEEID ");
				EmployeeBasicData.append(" )F ON A.EMPLOYEEID=F.EMPLOYEEID ");
				EmployeeBasicData.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC G ON F.CTCID=G.CTCTRANSACTIONID ");
				EmployeeBasicData.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");

				System.out.println(EmployeeBasicData.toString());
			pstmt=con.prepareStatement(EmployeeBasicData.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
			
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(6));
				
			}
			out.print(al.toString().replace("[", "").replace("]", ""));
		}else if(type.equalsIgnoreCase("")){
			
		StringBuffer CTCComponents=new StringBuffer();	
	
		CTCComponents.append(" SELECT D.NAME,C.COMPONENTVALUE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents.append(" 	LEFT JOIN ( ");
		CTCComponents.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents.append(" 	FROM ");
		CTCComponents.append(" 	HCLHRM_PROD.TBL_EMPLOYEE_CTC ");
		CTCComponents.append(" 	GROUP BY EMPLOYEEID ");
		CTCComponents.append(" 	)B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents.append(" 	LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		
		pstmt=con.prepareStatement(CTCComponents.toString());
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			
			
			al.add(rs.getString(1));
			
			
		}
		
		
		}
				
				
				
			}catch(Exception e){
				
			}
			
			
		}
		
		
		
	}

}
