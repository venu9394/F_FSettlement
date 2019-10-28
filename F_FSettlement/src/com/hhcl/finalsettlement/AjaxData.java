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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.util.Util;


public class AjaxData extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AjaxData() {
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
		HttpSession session=request.getSession();
		
		StringBuffer CTCComponents=new StringBuffer();	
		StringBuffer Earnings=new StringBuffer();
		StringBuffer Deductions=new StringBuffer();
		
		StringBuffer CTCComponents_Deductions=new StringBuffer();
		StringBuffer OtherDeductions=new StringBuffer();
		StringBuffer PayPeriodHold=new StringBuffer();
		StringBuffer LoanAmt=new StringBuffer();
		StringBuffer CTCComponents_Annual_Benifints=new StringBuffer();
		
		
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
		
		JSONObject jsonObj7 = new JSONObject();
		JSONArray values7 = new JSONArray();
		
		JSONObject jsonObj8 = new JSONObject();
		JSONArray values8 = new JSONArray();
		
		JSONObject jsonObjComp = new JSONObject();

		
		JSONObject jsonObj6 = new JSONObject();
		JSONArray values6 = new JSONArray();
		
		JSONObject jsonObj9 = new JSONObject();
		JSONArray values9 = new JSONArray();
		
		JSONObject jsonObj10 = new JSONObject();
		JSONArray values10 = new JSONArray();
		
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

		//-------------------------------------------11.08.2018-----------------------------------------------------------
		
		/*EmployeeBasicData.append(" SELECT COUNT(*) COUNT,A.EMPLOYEESEQUENCENO,A.CALLNAME CALLNAME,DATE_FORMAT(B.DATEOFJOIN,'%d.%m.%Y') DATEOFJOIN,D.NAME Department,E.NAME Designation,G.GROSS GROSS,");
		EmployeeBasicData.append(" IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') DATEOFRESIGNATION,DATE_FORMAT(HR.LASTWORKINGDATE,'%d.%m.%Y') LASTWORKINGDATE,H.PAYPERIOD PAYPERIOD, ");
		EmployeeBasicData.append(" IF(A.COMPANYID=PAYBU.BUSINESSUNITID,A.COMPANYID,PAYBU.BUSINESSUNITID) COMPANYID,IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') STRTDATE, ");
		EmployeeBasicData.append(" H.PAYPERIOD PAYPERIOD,PAYBU.BUSINESSUNITID,ifnull(LQ.AVAILABLEQTY,0.0) EL, DATE_FORMAT(MIN(I.FROMDATE),'%d.%m.%Y')  MINDATE, ");
		EmployeeBasicData.append(" DATE_FORMAT(MAX(I.TODATE),'%d.%m.%Y')  MAXDATE,ROUND((TIMESTAMPDIFF(MONTH, B.DATEOFJOIN, HR.LASTWORKINGDATE))/12,'1') EXPERIENCE ");
		EmployeeBasicData.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		EmployeeBasicData.append(" LEFT JOIN  HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID ");
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
		EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates I ON PAYBU.BUSINESSUNITID=I.BUSINESSUNITID AND I.TRANSACTIONDURATION > H.PAYPERIOD AND I.TRANSACTIONTYPEID=1 ");
		EmployeeBasicData.append(" LEFT JOIN (SELECT EMPLOYEEID,AVAILABLEQTY ");
		EmployeeBasicData.append(" FROM HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA ");
		EmployeeBasicData.append(" WHERE YEAR=2018 AND LEAVETYPEID=4) LQ ON A.EMPLOYEEID=LQ.EMPLOYEEID ");
		EmployeeBasicData.append(" WHERE HR.DOCUMENTTYPEID IN (2,10,12,13,20)  AND A.EMPLOYEESEQUENCENO="+empid+" ");
		*/
		
		//--------------------------------------------end --------------------------------------------------------------
		
		EmployeeBasicData.append(" SELECT COUNT(*) COUNT,A.EMPLOYEESEQUENCENO,A.CALLNAME CALLNAME,DATE_FORMAT(B.DATEOFJOIN,'%d.%m.%Y') DATEOFJOIN,D.NAME Department,E.NAME Designation,G.GROSS GROSS,");
		EmployeeBasicData.append(" IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') DATEOFRESIGNATION,DATE_FORMAT(HR.LASTWORKINGDATE,'%d.%m.%Y') LASTWORKINGDATE,H.PAYPERIOD PAYPERIOD, ");
		EmployeeBasicData.append(" IF(A.COMPANYID=PAYBU.BUSINESSUNITID,A.COMPANYID,PAYBU.BUSINESSUNITID) COMPANYID,IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') STRTDATE, ");
		EmployeeBasicData.append(" H.PAYPERIOD PAYPERIOD,PAYBU.BUSINESSUNITID,ifnull(LQ.AVAILABLEQTY,0.0) EL, DATE_FORMAT(MIN(I.FROMDATE),'%d.%m.%Y')  MINDATE, ");
		EmployeeBasicData.append(" DATE_FORMAT(MAX(I.TODATE),'%d.%m.%Y')  MAXDATE,ROUND((TIMESTAMPDIFF(MONTH, B.DATEOFJOIN, HR.LASTWORKINGDATE))/12,'1') EXPERIENCE,DATE_FORMAT(TRDATES.TODATE,'%d.%m.%Y') LASTPAYROLLDATE ");
		EmployeeBasicData.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		EmployeeBasicData.append(" LEFT JOIN  HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID ");
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
		EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates I ON PAYBU.BUSINESSUNITID=I.BUSINESSUNITID AND I.TRANSACTIONDURATION > H.PAYPERIOD AND I.TRANSACTIONTYPEID=1 ");
		EmployeeBasicData.append(" LEFT JOIN (SELECT EMPLOYEEID,AVAILABLEQTY ");
		EmployeeBasicData.append(" FROM HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA ");
		EmployeeBasicData.append(" WHERE YEAR=2018 AND LEAVETYPEID=4) LQ ON A.EMPLOYEEID=LQ.EMPLOYEEID ");		
		EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates TRDATES ON PAYBU.BUSINESSUNITID=TRDATES.BUSINESSUNITID ");
		EmployeeBasicData.append(" AND TRDATES.TRANSACTIONDURATION = H.PAYPERIOD AND TRDATES.TRANSACTIONTYPEID=1 ");	
		EmployeeBasicData.append(" WHERE HR.DOCUMENTTYPEID IN (2,6,10,12,13,20)  AND A.EMPLOYEESEQUENCENO="+empid+" ");
		
		
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
		CTCComponents.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND E.COMPONENTTYPEID IN (1) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC");

        System.out.println(CTCComponents.toString());


		Earnings.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID COMPONENTVALUE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Earnings.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		Earnings.append(" AND E.COMPONENTTYPECODE IN ('E') AND B.STATUS=1001 AND D.STATUS=1001");
		Earnings.append(" ORDER BY COMPONENTNAME ");
		
		 System.out.println(Earnings.toString());
		
		Deductions.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID COMPONENTVALUE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID AND B.STATUS=1001 ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		Deductions.append(" AND E.COMPONENTTYPECODE IN ('D') ");
		Deductions.append(" ORDER BY COMPONENTNAME ");
		
		
	/*	CTCComponents_Deductions.append(" SELECT D.NAME COMPONENTNAME,'0' COMPONENTVALUE,D.PAYCOMPONENTID PAYCOMPONENTID,COMPONENTTYPECODE ");
		CTCComponents_Deductions.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID AND B.STATUS=1001 ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents_Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		CTCComponents_Deductions.append(" AND D.PAYCOMPONENTID IN (84,85) ");
		CTCComponents_Deductions.append(" UNION  ");
		CTCComponents_Deductions.append(" SELECT D.NAME COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE ");
		CTCComponents_Deductions.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents_Deductions.append(" LEFT JOIN ( ");
		CTCComponents_Deductions.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents_Deductions.append("   	FROM ");
		CTCComponents_Deductions.append("   	HCLHRM_PROD.TBL_EMPLOYEE_CTC  	GROUP BY EMPLOYEEID ");
		CTCComponents_Deductions.append("     )B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents_Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND D.STATUS=1001 AND ");
		CTCComponents_Deductions.append(" E.COMPONENTTYPEID IN (2) ");
		CTCComponents_Deductions.append(" UNION ");
		CTCComponents_Deductions.append(" SELECT A.NAME COMPONENTNAME,'0' COMPONENTVALUE,A.PAYCOMPONENTID PAYCOMPONENTID,COMPONENTTYPECODE ");
		CTCComponents_Deductions.append("  FROM  hcladm_prod.tbl_pay_component A  LEFT JOIN HCLADM_PROD.tbl_pay_component_type B ON A.COMPONENTTYPEID=B.COMPONENTTYPEID ");
		CTCComponents_Deductions.append("  WHERE A.PAYCOMPONENTID IN (21) ");*/
		
	//	System.out.println(CTCComponents_Deductions.toString());
		
		
		CTCComponents_Deductions.append(" SELECT D.NAME COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents_Deductions.append(" 	LEFT JOIN ( ");
		CTCComponents_Deductions.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents_Deductions.append(" 	FROM ");
		CTCComponents_Deductions.append(" 	HCLHRM_PROD.TBL_EMPLOYEE_CTC ");
		CTCComponents_Deductions.append(" 	GROUP BY EMPLOYEEID ");
		CTCComponents_Deductions.append(" 	)B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents_Deductions.append(" 	LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		CTCComponents_Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents_Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND E.COMPONENTTYPEID IN (2) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC");
		
		
		CTCComponents_Annual_Benifints.append(" SELECT D.NAME COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE ");
		CTCComponents_Annual_Benifints.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents_Annual_Benifints.append(" LEFT JOIN ( ");
		CTCComponents_Annual_Benifints.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents_Annual_Benifints.append("   	FROM ");
		CTCComponents_Annual_Benifints.append("   	HCLHRM_PROD.TBL_EMPLOYEE_CTC  GROUP BY EMPLOYEEID ");
		CTCComponents_Annual_Benifints.append("   )B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents_Annual_Benifints.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents_Annual_Benifints.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID ");
		CTCComponents_Annual_Benifints.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents_Annual_Benifints.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND D.STATUS=1001 AND ");
		CTCComponents_Annual_Benifints.append(" E.COMPONENTTYPEID IN (5) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC ");

	//	System.out.println(CTCComponents_Annual_Benifints.toString());
	/*	Deductions.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID COMPONENTVALUE,IF(D.PAYCOMPONENTID=84,IFNULL(LOAN.Recovery_Amount,0),0) PAYVALUE ");
		Deductions.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID ");
		Deductions.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Deductions.append(" LEFT JOIN ( ");
		Deductions.append(" SELECT A.EMPID,A.LOAN_AMT,A.LOAN_AMT-SUM(B.PAID_EMI) 'Recovery_Amount' FROM HCLHRM_PROD_LOAN.LOAN_CAL A ");
		Deductions.append(" 		LEFT JOIN HCLHRM_PROD_LOAN.EMI_PROCESS B ON A.EMPID=B.EMP_ID AND A.LOAN_ID=B.LOAN_ID ");
		Deductions.append(" 		WHERE PAYMODE=1 ");
		Deductions.append(" 		GROUP BY ");
		Deductions.append(" 		A.LOAN_ID,A.EMPID ");
		Deductions.append(" 		HAVING ");
		Deductions.append(" 		A.LOAN_AMT-SUM(B.PAID_EMI)!=0 AND A.LOAN_AMT-SUM(B.PAID_EMI)!=-ABS(A.LOAN_AMT-SUM(B.PAID_EMI)) ");
		Deductions.append(" 		ORDER BY ");
		Deductions.append(" 		A.EMPID,A.LOAN_ID ");
		Deductions.append(" ) LOAN ON A.EMPLOYEESEQUENCENO=LOAN.EMPID ");
		Deductions.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+"  AND E.COMPONENTTYPECODE IN ('D') ");
		Deductions.append(" AND D.STATUS=1001 AND B.STATUS=1001 ");
		Deductions.append(" ORDER BY COMPONENTNAME ");
				*/
	//	System.out.println(Deductions.toString());
		
	//	OtherDeductions.append(" SELECT PAYCOMPONENTID COMPONENTVALUE,NAME COMPONENTNAME FROM hcladm_prod.tbl_pay_component WHERE PAYCOMPONENTID IN (21,51,106,62) ");
		
		
		OtherDeductions.append(" SELECT A.PAYCOMPONENTID COMPONENTVALUE,A.NAME COMPONENTNAME,A.COMPONENTTYPEID, ");
		OtherDeductions.append(" IF((B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=10),'E',IF((B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=11),'D',IF(B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=5,'E',B.COMPONENTTYPECODE))) COMPONENTTYPECODE ");
		OtherDeductions.append(" FROM ");
		OtherDeductions.append(" hcladm_prod.tbl_pay_component A ");
		OtherDeductions.append(" LEFT JOIN HCLADM_PROD.tbl_pay_component_type B ON A.COMPONENTTYPEID=B.COMPONENTTYPEID ");
		OtherDeductions.append(" WHERE A.COMPONENTTYPEID IN (10,11,6) AND A.PAYCOMPONENTID NOT IN (55,105) "); //PAYCOMPONENTID IN (21,51,106,62)
		
		
		
		
		
		
	//	System.out.println(OtherDeductions.toString());
		
		PayPeriodHold.append(" SELECT C.COMPONENTID,B.PAYPERIOD,C.NETVALUE ");
		PayPeriodHold.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		PayPeriodHold.append(" LEFT JOIN ( ");
		PayPeriodHold.append(" SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
		PayPeriodHold.append(" WHERE STATUS=1092 ");
		PayPeriodHold.append(" GROUP BY PAYPERIOD,EMPLOYEEID ");
		PayPeriodHold.append(" )B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		PayPeriodHold.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA C ON B.EMPLOYEEID=C.EMPLOYEEID AND B.PAYPERIOD=C.PAYPERIOD ");
		PayPeriodHold.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		PayPeriodHold.append(" AND C.STATUS=1001 ");
		PayPeriodHold.append(" AND C.COMPONENTID=46 ");
		PayPeriodHold.append(" GROUP BY B.PAYPERIOD ");
		
		
		LoanAmt.append(" SELECT IFNULL(IMPRS.assetpropertyvalue,'0') IMPRESTAMT,IFNULL(LOAN.Recovery_Amount,0) Recovery_Amount ");
		LoanAmt.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		LoanAmt.append(" LEFT JOIN ( ");
		LoanAmt.append("  select  assetpropertyvalue,issueddate,returneddate,employeeid from hclhrm_prod.tbl_employee_assets ");
		LoanAmt.append(" where assetid=16 group by employeeid ");
		LoanAmt.append(" )IMPRS ON IMPRS.employeeid=A.employeeid ");
		LoanAmt.append(" LEFT JOIN ( ");
		LoanAmt.append(" SELECT A.EMPID,A.LOAN_AMT,A.LOAN_AMT-SUM(B.PAID_EMI) 'Recovery_Amount' FROM HCLHRM_PROD_LOAN.LOAN_CAL A ");
		LoanAmt.append(" LEFT JOIN HCLHRM_PROD_LOAN.EMI_PROCESS B ON A.EMPID=B.EMP_ID AND A.LOAN_ID=B.LOAN_ID ");
		LoanAmt.append(" WHERE PAYMODE=1 ");
		LoanAmt.append(" GROUP BY ");
		LoanAmt.append(" A.LOAN_ID,A.EMPID ");
		LoanAmt.append(" HAVING ");
		LoanAmt.append(" A.LOAN_AMT-SUM(B.PAID_EMI)!=0 AND A.LOAN_AMT-SUM(B.PAID_EMI)!=-ABS(A.LOAN_AMT-SUM(B.PAID_EMI)) ");
		LoanAmt.append(" ORDER BY ");
		LoanAmt.append(" A.EMPID,A.LOAN_ID ");
		LoanAmt.append(" ) LOAN ON A.EMPLOYEESEQUENCENO=LOAN.EMPID ");
		LoanAmt.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		
		
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
							
							
							
							session.setAttribute("Payperiod", rs.getString("PAYPERIOD"));
							session.setAttribute("Businessunit", rs.getString("BUSINESSUNITID"));
						//	Payperiod=rs.getString("PAYPERIOD");
									
							System.out.println(Payperiod+":::"+rs.getString("BUSINESSUNITID"));
							jsonObj1.put("CALLNAME", rs.getString("CALLNAME"));
							jsonObj1.put("DATEOFJOIN", rs.getString("DATEOFJOIN"));
							jsonObj1.put("Department", rs.getString("Department"));
							jsonObj1.put("Designation", rs.getString("Designation"));
							jsonObj1.put("GROSS", rs.getString("GROSS"));
							jsonObj1.put("DATEOFRESIGNATION", rs.getString("DATEOFRESIGNATION"));
							jsonObj1.put("LASTWORKINGDATE", rs.getString("LASTWORKINGDATE"));
						//	jsonObj1.put("STRTDATE", rs.getString("STRTDATE"));
							jsonObj1.put("EL", rs.getString("EL"));
							jsonObj1.put("MINDATE", rs.getString("MINDATE"));
							jsonObj1.put("MAXDATE", rs.getString("MAXDATE"));
							jsonObj1.put("PAYPERIOD", rs.getString("PAYPERIOD"));
							jsonObj1.put("EXPERIENCE", rs.getString("EXPERIENCE"));
							jsonObj1.put("LASTPAYROLLDATE", rs.getString("LASTPAYROLLDATE"));
							
							
							
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
								}/*else if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("D")){
									componentsDe.add(rs.getString("PAYCOMPONENTID"));
								
								}*/
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
							//	jsonObj4.put("PAYABLE",rs.getString("PAYCOMPONENTID"));
								values4.add(jsonObj4);
								
							
							}
						
							
							rs=null;
					try{
							pstmt=con.prepareStatement(OtherDeductions.toString());
							rs=pstmt.executeQuery();

							while(rs.next()){

								if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("E")){
									
									jsonObj5.put("HEAD",rs.getString("COMPONENTNAME").concat(" (+) "));
								}else if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("D")){
									
									jsonObj5.put("HEAD",rs.getString("COMPONENTNAME").concat(" (-)"));
								}else{
									jsonObj5.put("HEAD",rs.getString("COMPONENTNAME"));
								}
								
								jsonObj5.put("ACTUAL",rs.getString("COMPONENTVALUE")+"-"+rs.getString("COMPONENTTYPECODE"));
						//		jsonObj5.put("PAYCOMPONENTID",rs.getString("PAYCOMPONENTID"));
								values5.add(jsonObj5);
							}
							
					}catch(Exception wer){
						 System.out.println("wer :::" +wer);
						
					}
					
					
					rs=null;
					pstmt=con.prepareStatement(PayPeriodHold.toString());
					rs=pstmt.executeQuery();
					System.out.println(PayPeriodHold.toString());
					while(rs.next()){
						
						jsonObj7.put("PayPeriods",rs.getString("PAYPERIOD"));
						jsonObj7.put("NETVALUE",rs.getString("NETVALUE"));
						
						
						values7.add(jsonObj7);
					
					}
					
					rs=null;
						pstmt=con.prepareStatement(LoanAmt.toString());
						rs=pstmt.executeQuery();
					//	System.out.println(LoanAmt.toString());
						while(rs.next()){
							
							jsonObj8.put("IMPRESTAMT",rs.getString("IMPRESTAMT"));
							jsonObj8.put("Recovery_Amount",rs.getString("Recovery_Amount"));
							
							
							values8.add(jsonObj8);
						
						}
						
						rs=null;
						pstmt=con.prepareStatement(CTCComponents_Annual_Benifints.toString());
						rs=pstmt.executeQuery();
					//	System.out.println(LoanAmt.toString());
						while(rs.next()){
							
							jsonObj9.put("HEAD",rs.getString("COMPONENTNAME"));
							jsonObj9.put("ACTUAL",rs.getString("COMPONENTVALUE"));
							jsonObj9.put("PAYABLE",rs.getString("PAYCOMPONENTID"));
							values9.add(jsonObj9);
						
						}
						
						rs=null;
						pstmt=con.prepareStatement(CTCComponents_Deductions.toString());
						rs=pstmt.executeQuery();

						while(rs.next()){


							jsonObj10.put("HEAD",rs.getString("COMPONENTNAME"));
							jsonObj10.put("ACTUAL",rs.getString("COMPONENTVALUE"));
							jsonObj10.put("PAYABLE",rs.getString("PAYCOMPONENTID"));
							values10.add(jsonObj10);
							
							 if(rs.getString("COMPONENTTYPECODE").equalsIgnoreCase("D")){
									componentsDe.add(rs.getString("PAYCOMPONENTID"));
								
								}
								ctccomponents.add("\'"+rs.getString("COMPONENTTYPECODE")+"\'");
								components.add(rs.getString("PAYCOMPONENTID"));
						}
						
							
							String json1 = new Gson().toJson(values1); 
							String json2 = new Gson().toJson(values); 
							String json3 = new Gson().toJson(values3); 
							String json4 = new Gson().toJson(values4); 
							String json5 = new Gson().toJson(values5); 
							String json6 = new Gson().toJson(values7); 
							String json7 = new Gson().toJson(values8); 
							String json8 = new Gson().toJson(values9); 
							String json9 = new Gson().toJson(values10); 
							
							response.setContentType("application/json"); 
							response.setCharacterEncoding("utf-8"); 
							
							
							String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+","+components.toString()+","+componentsEr.toString()+","+componentsDe.toString()+","+json6+","+json7+","+json8+","+json9+"]"; //Put both objects in an array of 2 elements

						//	System.out.println(bothJson+"----------");
							response.getWriter().write(bothJson);

						}else if(rs.getInt("COUNT")==0 && rs.getString("EMPLOYEESEQUENCENO")!=null){

							out.print("1004");
						}else{
							
							out.print("204");
						}
					}
					//	out.print(al.toString().replace("[", "").replace("]", ""));
				}else if(type.equalsIgnoreCase("payperiodcount")){
					
					
				//	System.out.println(Payperiod+":::Payperiod:::" +session.getAttribute("Payperiod"));
					Payperiod=(String) session.getAttribute("Payperiod");
					Businessunit=(String) session.getAttribute("Businessunit");
					fromdate=request.getParameter("fromdate");
					todate=request.getParameter("todate");
					
					/*PayPeriodCal.append(" SELECT SUM(COUNTID) PAYPERIODCOUNT,TRANSACTIONDURATION,FROMDATE,TODATE,DATETO,SUM(DATEDIFF(TODATE,FROMDATE)+1) DAYS_PAYPERIOD,SUM(DATEDIFF(DATETO,FROMDATE)+1) ");
					PayPeriodCal.append(" FROM (SELECT COUNT(TRANSACTIONDURATION) COUNTID,TRANSACTIONDURATION,FROMDATE,TODATE, ");
					PayPeriodCal.append(" IF(DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d') < TODATE,DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'),TODATE) DATETO ");
					PayPeriodCal.append(" FROM HCLADM_PROD.tbl_transaction_dates ");
					PayPeriodCal.append(" WHERE TRANSACTIONTYPEID=1 ");
					PayPeriodCal.append(" AND BUSINESSUNITID="+Businessunit+" ");
					PayPeriodCal.append(" AND TRANSACTIONDURATION > "+Payperiod+" ");
					PayPeriodCal.append(" AND ((DATE_FORMAT(STR_TO_DATE('"+fromdate+"', '%d.%m.%Y'), '%Y-%m-%d') BETWEEN FROMDATE AND TODATE) OR (FROMDATE BETWEEN FROMDATE AND DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'))) GROUP BY TRANSACTIONDURATION ");
					PayPeriodCal.append(" ) AS A  ");
					*/
					
					PayPeriodCal.append("  SELECT IFNULL(SUM(COUNTID),0) PAYPERIODCOUNT,TRANSACTIONDURATION,FROMDATE,TODATE,DATETO,IFNULL(SUM(DATEDIFF(TODATE,FROMDATE)+1),0) DAYS_PAYPERIOD, ");
					PayPeriodCal.append("  SUM(DATEDIFF(DATETO,FROMDATE)+1)  FROM ( ");
					PayPeriodCal.append("  SELECT COUNT(TRANSACTIONDURATION) COUNTID, ");
					PayPeriodCal.append("  TRANSACTIONDURATION,FROMDATE,TODATE, ");
					PayPeriodCal.append("  IF(DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d') < TODATE,DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'),TODATE) DATETO ");
					PayPeriodCal.append(" FROM HCLADM_PROD.tbl_transaction_dates ");
					PayPeriodCal.append("  WHERE TRANSACTIONTYPEID=1  AND BUSINESSUNITID="+Businessunit+"  ");
					PayPeriodCal.append("  AND TRANSACTIONDURATION > "+Payperiod+"  AND ");
					PayPeriodCal.append("   (((DATE_FORMAT(STR_TO_DATE('"+fromdate+"', '%d.%m.%Y'), '%Y-%m-%d') BETWEEN FROMDATE AND TODATE) ");
					PayPeriodCal.append("   AND (FROMDATE BETWEEN FROMDATE AND DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d'))) ");
					PayPeriodCal.append("   OR  ");
					PayPeriodCal.append(" ((FROMDATE BETWEEN DATE_FORMAT(STR_TO_DATE('"+fromdate+"', '%d.%m.%Y'), '%Y-%m-%d') AND TODATE) ");
					PayPeriodCal.append(" AND (FROMDATE BETWEEN FROMDATE AND DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d.%m.%Y'), '%Y-%m-%d')))) ");
					PayPeriodCal.append(" GROUP BY TRANSACTIONDURATION ");
					PayPeriodCal.append("    ) AS A ");
					
					
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
				//out.print("102");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }


	}

}
