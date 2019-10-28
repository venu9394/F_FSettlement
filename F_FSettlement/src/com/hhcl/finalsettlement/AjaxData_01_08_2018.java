package com.hhcl.finalsettlement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.util.Util;


public class AjaxData_01_08_2018 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AjaxData_01_08_2018() {
		super();
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
		response.setContentType("text/html");
		StringBuffer EmployeeBasicData=new StringBuffer();
		ArrayList<String> al=new ArrayList<String>();
		PrintWriter out=response.getWriter();
		
		
		StringBuffer CTCComponents=new StringBuffer();	
		StringBuffer Earnings=new StringBuffer();
		StringBuffer Deductions=new StringBuffer();
		StringBuffer OtherDeductions=new StringBuffer();
		StringBuffer PayPeriodCal=new StringBuffer();
		
		ArrayList components=new ArrayList();
		ArrayList ctccomponents=new ArrayList();
		ArrayList componentsEr=new ArrayList();
		ArrayList componentsDe=new ArrayList();
		
		
		ArrayList Payperiods=new ArrayList();
		
		JSONObject jsonObj1 = new JSONObject();
		JSONArray values1 = new JSONArray();


		JSONObject jsonObj = new JSONObject();
		JSONArray values = new JSONArray();
		
		JSONObject jsonObj3 = new JSONObject();
		JSONArray values3 = new JSONArray();
		
		JSONObject jsonObj4 = new JSONObject();
		JSONArray values4 = new JSONArray();
		
		JSONObject jsonObj5 = new JSONObject();
		JSONArray values5 = new JSONArray();
		
		JSONObject jsonObjComp = new JSONObject();

		
		JSONObject jsonObj6 = new JSONObject();
		JSONArray values6 = new JSONArray();
		
		String type=request.getParameter("type");

		String empid=request.getParameter("employeeid");

		String Payperiod=null;
		String Businessunit=null;
		String fromdate=null;
		String todate=null;
		
		/*EmployeeBasicData.append(" SELECT COUNT(*) COUNT,A.EMPLOYEESEQUENCENO,A.CALLNAME CALLNAME,B.DATEOFJOIN,D.NAME Department,E.NAME Designation,G.GROSS GROSS,");
		EmployeeBasicData.append(" B.DATEOFRESIGNATION DATEOFRESIGNATION");
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
		EmployeeBasicData.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_HRACTIONS HR ON A.EMPLOYEEID=HR.EMPLOYEEID");
		EmployeeBasicData.append(" WHERE HR.DOCUMENTTYPEID IN (2,10,12,13,20) AND A.EMPLOYEESEQUENCENO="+empid+" ");*/

		
		
		EmployeeBasicData.append(" SELECT COUNT(*) COUNT,A.EMPLOYEESEQUENCENO,A.CALLNAME CALLNAME,B.DATEOFJOIN,D.NAME Department,E.NAME Designation,G.GROSS GROSS,");
		EmployeeBasicData.append(" B.DATEOFRESIGNATION DATEOFRESIGNATION,HR.LASTWORKINGDATE LASTWORKINGDATE,H.PAYPERIOD PAYPERIOD, ");
		EmployeeBasicData.append(" IF(A.COMPANYID=PAYBU.BUSINESSUNITID,A.COMPANYID,PAYBU.BUSINESSUNITID) COMPANYID,DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y') STRTDATE");
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
		EmployeeBasicData.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_HRACTIONS HR ON A.EMPLOYEEID=HR.EMPLOYEEID");
		EmployeeBasicData.append(" LEFT JOIN (SELECT MAX(PAYPERIOD) PAYPERIOD,EMPLOYEEID ");
		EmployeeBasicData.append(" FROM ");
		EmployeeBasicData.append(" HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
		EmployeeBasicData.append(" GROUP BY EMPLOYEEID) H ON A.EMPLOYEEID=H.EMPLOYEEID");
		EmployeeBasicData.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS PAYBU ON H.EMPLOYEEID=PAYBU.EMPLOYEEID AND H.PAYPERIOD=PAYBU.PAYPERIOD ");		
		EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates I ON PAYBU.BUSINESSUNITID AND I.TRANSACTIONDURATION > H.PAYPERIOD AND I.TRANSACTIONTYPEID=1 ");
		EmployeeBasicData.append(" WHERE HR.DOCUMENTTYPEID IN (2,10,12,13,20) AND A.EMPLOYEESEQUENCENO="+empid+" ");
		
		System.out.println(EmployeeBasicData.toString());
		
		
		CTCComponents.append(" SELECT CONCAT(D.NAME,' (',E.COMPONENTTYPECODE,')') COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents.append(" 	LEFT JOIN ( ");
		CTCComponents.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents.append(" 	FROM ");
		CTCComponents.append(" 	HCLHRM_PROD.TBL_EMPLOYEE_CTC ");
		CTCComponents.append(" 	GROUP BY EMPLOYEEID ");
		CTCComponents.append(" 	)B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents.append(" 	LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND E.COMPONENTTYPEID IN (1,2) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC");

 //       System.out.println(CTCComponents.toString());


		Earnings.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID COMPONENTVALUE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID AND B.STATUS=1001 ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Earnings.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		Earnings.append(" AND E.COMPONENTTYPECODE IN ('E') ");
		Earnings.append(" ORDER BY COMPONENTNAME ");
		
		Deductions.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID COMPONENTVALUE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID AND B.STATUS=1001 ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		Deductions.append(" AND E.COMPONENTTYPECODE IN ('D') ");
		Deductions.append(" ORDER BY COMPONENTNAME ");
				
		
		OtherDeductions.append(" SELECT PAYCOMPONENTID COMPONENTVALUE,NAME COMPONENTNAME FROM hcladm_prod.tbl_pay_component WHERE PAYCOMPONENTID IN (21,51) ");
		
		
		
		
		
		
		
		con=Util.getConnection();



		try{

			if(con!=null){
				con.setAutoCommit(false);


				if(type.equalsIgnoreCase("basicdata")){



				//	System.out.println(EmployeeBasicData.toString());
					pstmt=con.prepareStatement(EmployeeBasicData.toString());
					rs=pstmt.executeQuery();

					while(rs.next()){
						
						/*al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(6));*/

						if(rs.getInt("COUNT")>0 && rs.getString("EMPLOYEESEQUENCENO")!=null){
							
						
							
							System.out.println(Payperiod+":::"+Businessunit);
							jsonObj1.put("CALLNAME", rs.getString("CALLNAME"));
							jsonObj1.put("DATEOFJOIN", rs.getString("DATEOFJOIN"));
							jsonObj1.put("Department", rs.getString("Department"));
							jsonObj1.put("Designation", rs.getString("Designation"));
							jsonObj1.put("GROSS", rs.getString("GROSS"));
							jsonObj1.put("DATEOFRESIGNATION", rs.getString("DATEOFRESIGNATION"));
							jsonObj1.put("LASTWORKINGDATE", rs.getString("LASTWORKINGDATE"));
							jsonObj1.put("STRTDATE", rs.getString("STRTDATE"));

							values1.add(jsonObj1);


							rs=null;

							pstmt=con.prepareStatement(CTCComponents.toString());
							rs=pstmt.executeQuery();

							while(rs.next()){


								jsonObj.put("HEAD",rs.getString("COMPONENTNAME"));
								jsonObj.put("ACTUAL",rs.getString("COMPONENTVALUE"));
								jsonObj.put("PAYABLE",rs.getString("PAYCOMPONENTID"));

								values.add(jsonObj);
								if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("E")){
								   componentsEr.add(rs.getString("PAYCOMPONENTID"));
								}else if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("D")){
									componentsDe.add(rs.getString("PAYCOMPONENTID"));
								
								}
								ctccomponents.add("\'"+rs.getString("COMPONENTTYPECODE")+"\'");
								components.add(rs.getString("PAYCOMPONENTID"));
							}

							rs=null;
							pstmt=con.prepareStatement(Earnings.toString());
							rs=pstmt.executeQuery();

							while(rs.next()){


								jsonObj3.put("HEAD",rs.getString("COMPONENTNAME"));
								jsonObj3.put("ACTUAL",rs.getString("COMPONENTVALUE"));
							//	jsonObj3.put("PAYCOMPONENTID",rs.getString("PAYCOMPONENTID"));
								values3.add(jsonObj3);
							}
							
							rs=null;
							pstmt=con.prepareStatement(Deductions.toString());
							rs=pstmt.executeQuery();

							while(rs.next()){


								jsonObj4.put("HEAD",rs.getString("COMPONENTNAME"));
								jsonObj4.put("ACTUAL",rs.getString("COMPONENTVALUE"));
								//jsonObj4.put("PAYCOMPONENTID",rs.getString("PAYCOMPONENTID"));
								values4.add(jsonObj4);
							}
							
							
							rs=null;
							pstmt=con.prepareStatement(OtherDeductions.toString());
							rs=pstmt.executeQuery();

							while(rs.next()){


								jsonObj5.put("HEAD",rs.getString("COMPONENTNAME"));
								jsonObj5.put("ACTUAL",rs.getString("COMPONENTVALUE"));
						//		jsonObj5.put("PAYCOMPONENTID",rs.getString("PAYCOMPONENTID"));
								values5.add(jsonObj5);
							}
							
							
							String json1 = new Gson().toJson(values1); 
							String json2 = new Gson().toJson(values); 
							String json3 = new Gson().toJson(values3); 
							String json4 = new Gson().toJson(values4); 
							String json5 = new Gson().toJson(values5); 
							
							response.setContentType("application/json"); 
							response.setCharacterEncoding("utf-8"); 
							
							
							String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+","+components.toString()+","+componentsEr.toString()+","+componentsDe.toString()+"]"; //Put both objects in an array of 2 elements

							System.out.println(bothJson+"----------");
							response.getWriter().write(bothJson);

						}else if(rs.getInt("COUNT")==0 && rs.getString("EMPLOYEESEQUENCENO")!=null){

							out.print("1004");
						}else{
							
							out.print("204");
						}
					}
					//	out.print(al.toString().replace("[", "").replace("]", ""));
				}else if(type.equalsIgnoreCase("payperiodcount")){
					
					
					Payperiod="201602";
					Businessunit="11";
					fromdate=request.getParameter("fromdate");
					todate=request.getParameter("todate");
					
					PayPeriodCal.append(" SELECT SUM(COUNTID) COUNTID,TRANSACTIONDURATION,FROMDATE,TODATE,DATETO,SUM(DATEDIFF(TODATE,FROMDATE)+1) DIFF,SUM(DATEDIFF(DATETO,FROMDATE)+1) ");
					PayPeriodCal.append(" FROM (SELECT COUNT(TRANSACTIONDURATION) COUNTID,TRANSACTIONDURATION,FROMDATE,TODATE, ");
					PayPeriodCal.append(" IF(DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d') < TODATE,DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'),TODATE) DATETO ");
					PayPeriodCal.append(" FROM HCLADM_PROD.tbl_transaction_dates ");
					PayPeriodCal.append(" WHERE TRANSACTIONTYPEID=1 ");
					PayPeriodCal.append(" AND BUSINESSUNITID="+Businessunit+" ");
					PayPeriodCal.append(" AND TRANSACTIONDURATION > "+Payperiod+" ");
					PayPeriodCal.append(" AND ((DATE_FORMAT(STR_TO_DATE('"+fromdate+"', '%d.%m.%Y'), '%Y-%m-%d') BETWEEN FROMDATE AND TODATE) OR (FROMDATE BETWEEN FROMDATE AND DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'))) GROUP BY TRANSACTIONDURATION ");
					PayPeriodCal.append(" ) AS A  ");
					
					rs=null;
					System.out.println(PayPeriodCal.toString());
					pstmt=con.prepareStatement(PayPeriodCal.toString());
					rs=pstmt.executeQuery();
					ResultSetMetaData rsmd=rs.getMetaData();  
				                                          
				    if( rs.next() )
				      {
				          for( int i = 1; i <= rsmd.getColumnCount(); i++ )
				          //   System.out.print( rs.getString(i) + " " ) ;
				          jsonObj6.put(rsmd.getColumnLabel(i),rs.getString(i));
				          values6.add(jsonObj6);
				          System.out.println(jsonObj6) ;
				      }
				    
				    
					/*while(rs.next()){
						
						 jsonObj6.put("COUNTID",rs.getString("COMPONENTNAME"));
						 jsonObj6.put("DIFF",rs.getString("COMPONENTNAME"));
				          values6.add(jsonObj6);
					}*/
					String json = new Gson().toJson(values6); 
				    
					response.setContentType("application/json"); 
					response.setCharacterEncoding("utf-8"); 
					
					
					String bothJson1 = "["+json+"]"; //Put both objects in an array of 2 elements

				//	System.out.println(bothJson1+"----------");
					response.getWriter().write(bothJson1);
				}

			

			}else{
				out.print("102");
			}
		}catch(Exception e){

			out.print("101");
		}finally {
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }


	}

}
