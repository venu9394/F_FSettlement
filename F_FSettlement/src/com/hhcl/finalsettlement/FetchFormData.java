package com.hhcl.finalsettlement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.util.Util;

public class FetchFormData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FetchFormData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request,response);
	}


	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String LOGINBY="103447"; //100971
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		response.setContentType("text/html");
		StringBuffer EmployeeBasicData=new StringBuffer();
		ArrayList<String> al=new ArrayList<String>();
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		ResultSetMetaData rsmd=null;
		JSONObject jsonObj = new JSONObject();
		
		JSONObject jsonObj_main = new JSONObject();
		
		JSONArray values = new JSONArray();
		JSONArray values_EMPDATA = new JSONArray();
		
		JSONObject jsonObj_Earnings = new JSONObject();
		JSONArray values_Earnings = new JSONArray();
		
		JSONObject jsonObj_Deductions = new JSONObject();
		JSONArray values_Deductions = new JSONArray();
		
		JSONObject jsonObj_Annual_Benifits = new JSONObject();
		JSONArray values_Annual_Benifits = new JSONArray();
		
		
		JSONObject jsonObj_hold_payperiod=new JSONObject();
		JSONArray values_hold_payperiod = new JSONArray();
		
		JSONObject jsonObj_Monthly_Earnings=new JSONObject();
		JSONArray values_Monthly_Earnings = new JSONArray();
		
		JSONObject jsonObj_Monthly_Deductions=new JSONObject();
		JSONArray values_Monthly_Deductions = new JSONArray();
		
		JSONObject jsonObj_other_monthly_Earnings=new JSONObject();
		JSONArray values_other_monthly_Earnings = new JSONArray();
		
		JSONObject jsonObj_other_monthly_Deductions=new JSONObject();
		JSONArray values_other_monthly_Deductions = new JSONArray();
		
		
		JSONObject jsonObj_Loan_Impress=new JSONObject();
		JSONArray values_Loan_Impress = new JSONArray();
		
		JSONObject jsonObj_Expense_Components=new JSONObject();
		JSONArray values_Expense_Components = new JSONArray();
		
		
		JSONObject jsonObj_Bonus_Calculation=new JSONObject();
		JSONArray values_Bonus_Calculation = new JSONArray();
		
		
		JSONObject jsonObj_Bonus_Amount=new JSONObject();
		JSONArray values_Bonus_Amount = new JSONArray();
		
			
		ArrayList components=new ArrayList();
		String type=request.getParameter("type");

		String empid=request.getParameter("employeeid");
		
		

		String Payperiod=null;
		String Businessunit=null;
		String fromdate=null;
		String todate=null;
		
		StringBuffer CTCComponents=new StringBuffer();	
		StringBuffer Earnings=new StringBuffer();
		StringBuffer Deductions=new StringBuffer();
		
		StringBuffer CTCComponents_Deductions=new StringBuffer();
		StringBuffer OtherDeductions=new StringBuffer();
		StringBuffer PayPeriodHold=new StringBuffer();
		StringBuffer LoanAmt=new StringBuffer();
		StringBuffer CTCComponents_Annual_Benifints=new StringBuffer();
		StringBuffer PayPeriodCal=new StringBuffer();
		
		int Count=0,loginBU=0;
		int lastpaymonth=0;
		String lastpayperioddate=null;
		double total_bonus=0.00;
		Map paramTitles=new HashMap();
		
		EmployeeBasicData.append(" SELECT COUNT(*) COUNT,A.EMPLOYEESEQUENCENO,A.CALLNAME CALLNAME,DATE_FORMAT(B.DATEOFJOIN,'%d.%m.%Y') DATEOFJOIN,D.CODE Department,E.CODE Designation,G.GROSS GROSS,");
		EmployeeBasicData.append(" IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') DATEOFRESIGNATION,DATE_FORMAT(HR.LASTWORKINGDATE,'%d.%m.%Y') LASTWORKINGDATE,H.PAYPERIOD PAYPERIOD, ");
		EmployeeBasicData.append(" IF(A.COMPANYID=PAYBU.BUSINESSUNITID,A.COMPANYID,PAYBU.BUSINESSUNITID) COMPANYID,IFNULL(DATE_FORMAT(B.DATEOFRESIGNATION,'%d.%m.%Y'),'') STRTDATE, ");
		EmployeeBasicData.append(" H.PAYPERIOD PAYPERIOD,PAYBU.BUSINESSUNITID,ifnull(LQ.AVAILABLEQTY,0.0) EL, DATE_FORMAT(MIN(I.FROMDATE),'%d.%m.%Y')  MINDATE, ");
		EmployeeBasicData.append(" DATE_FORMAT(MAX(I.TODATE),'%d.%m.%Y')  MAXDATE,ROUND((TIMESTAMPDIFF(MONTH, B.DATEOFJOIN, HR.LASTWORKINGDATE))/12,'1') EXPERIENCE,DATE_FORMAT(TRDATES.TODATE,'%d.%m.%Y') LASTPAYROLLDATE ");
		EmployeeBasicData.append(" ,DATE_FORMAT(HR.LASTWORKINGDATE,'%Y%m') LASTPAYMONTH ,HR.LASTWORKINGDATE LASTPAYDATE ");  
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
		EmployeeBasicData.append(" WHERE YEAR=YEAR(DATE(NOW())) AND LEAVETYPEID=4) LQ ON A.EMPLOYEEID=LQ.EMPLOYEEID ");		
		EmployeeBasicData.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates TRDATES ON PAYBU.BUSINESSUNITID=TRDATES.BUSINESSUNITID ");
		EmployeeBasicData.append(" AND TRDATES.TRANSACTIONDURATION = H.PAYPERIOD AND TRDATES.TRANSACTIONTYPEID=1 ");
		EmployeeBasicData.append(" JOIN ( ");
		EmployeeBasicData.append(" 		SELECT BUSINESSUNITID FROM HCLHRM_PROD.TBL_EMPLOYEE_BUSINESSUNIT WHERE EMPLOYEEID="+LOGINBY+" ");    //100971
		EmployeeBasicData.append(" 		)B ON A.COMPANYID=B.BUSINESSUNITID ");
		EmployeeBasicData.append(" WHERE HR.DOCUMENTTYPEID IN (2,6,10,12,13,20)  AND A.EMPLOYEESEQUENCENO="+empid+" ");
	
		
			System.out.println(EmployeeBasicData.toString()+":::");
		
		

		CTCComponents.append(" SELECT D.NAME COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		CTCComponents.append(" 	LEFT JOIN ( ");
		CTCComponents.append(" 	SELECT EMPLOYEEID,MAX(CTCTRANSACTIONID) CTCTRANSACTIONID ");
		CTCComponents.append(" 	FROM ");
		CTCComponents.append(" 	HCLHRM_PROD.TBL_EMPLOYEE_CTC ");
		CTCComponents.append(" 	GROUP BY EMPLOYEEID ");
		CTCComponents.append(" 	)B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		CTCComponents.append(" 	LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS C ON B.CTCTRANSACTIONID=C.CTCTRANSACTIONID ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON C.COMPONENTID=D.PAYCOMPONENTID AND D.STATUS=1001 ");
		CTCComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		CTCComponents.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" AND E.COMPONENTTYPEID IN (1,2,5) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC");

     //   System.out.println(CTCComponents.toString());


		Earnings.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID PAYCOMPONENTID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID ");
		Earnings.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		Earnings.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
		Earnings.append(" AND E.COMPONENTTYPECODE IN ('E') AND B.STATUS=1001 AND D.STATUS=1001");
	//	Earnings.append(" AND FIND_IN_SET(D.PAYCOMPONENTID,(SELECT GROUP_CONCAT(FORMULACOMPONENTID) FROM HCLADM_PROD.TBL_PAY_COMPONENT WHERE PAYCOMPONENTID!=95)) ");
		Earnings.append(" ORDER BY COMPONENTNAME ");
		
	//	 System.out.println(Earnings.toString());
		
		Deductions.append(" SELECT D.NAME COMPONENTNAME,D.PAYCOMPONENTID PAYCOMPONENTID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
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
		
		
		/*CTCComponents_Deductions.append(" SELECT D.NAME COMPONENTNAME,C.COMPONENTVALUE,D.PAYCOMPONENTID,E.COMPONENTTYPECODE COMPONENTTYPECODE FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
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
		CTCComponents_Annual_Benifints.append(" E.COMPONENTTYPEID IN (5) ORDER BY D.PAYCOMPONENTID,E.COMPONENTTYPECODE DESC ");*/

		
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
		
		
		OtherDeductions.append(" SELECT A.PAYCOMPONENTID PAYCOMPONENTID,A.NAME COMPONENTNAME,A.COMPONENTTYPEID, ");
		OtherDeductions.append(" IF((B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=10),'E',IF((B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=11),'D',IF(B.COMPONENTTYPECODE='O' AND A.COMPONENTTYPEID=5,'E',B.COMPONENTTYPECODE))) COMPONENTTYPECODE ");
		OtherDeductions.append(" FROM ");
		OtherDeductions.append(" hcladm_prod.tbl_pay_component A ");
		OtherDeductions.append(" LEFT JOIN HCLADM_PROD.tbl_pay_component_type B ON A.COMPONENTTYPEID=B.COMPONENTTYPEID ");
		OtherDeductions.append(" WHERE A.COMPONENTTYPEID IN (10,11,6) AND A.STATUS=1001"); //A.PAYCOMPONENTID NOT IN (55,105) //PAYCOMPONENTID IN (21,51,106,62)
				
		
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
		
	//	System.out.println(PayPeriodHold.toString());
		
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
		
		System.out.println(LoanAmt.toString());
		
		StringBuffer ExpenseComponents=new StringBuffer();
		
		ExpenseComponents.append(" SELECT A.EMPLOYEEID,A.COMPANYID,D.PAYCOMPONENTID,D.NAME COMPONENTNAME ");
		ExpenseComponents.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		ExpenseComponents.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_PAY_COMPONENT B ON A.COMPANYID=B.BUSINESSUNITID ");
		ExpenseComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT D ON B.PAYCOMPONENTID=D.PAYCOMPONENTID ");
		ExpenseComponents.append(" LEFT JOIN HCLADM_PROD.TBL_PAY_COMPONENT_TYPE E ON D.COMPONENTTYPEID=E.COMPONENTTYPEID ");
		ExpenseComponents.append(" WHERE  D.STATUS=1001 AND ");
		ExpenseComponents.append(" B.STATUS=1001 AND ");
		ExpenseComponents.append(" A.EMPLOYEESEQUENCENO="+empid+" AND ");
		ExpenseComponents.append(" FIND_IN_SET(D.PAYCOMPONENTID,(SELECT GROUP_CONCAT(FORMULACOMPONENTID) FROM HCLADM_PROD.TBL_PAY_COMPONENT WHERE PAYCOMPONENTID=95)); ");
		
		
		
		StringBuffer Bonus=new StringBuffer();
		
		
		
		
		System.out.println(Bonus.toString());
		
				
		jsonObj.put("STATUS","1002");
		jsonObj.put("STATUS1","1002");
		jsonObj.put("Error","Invalid Request Please try again/contact admin");
	
		con=Util.getConnection();
		
	try{	
		if(con!=null){
			
			con.setAutoCommit(false);
			if(type.equalsIgnoreCase("basicdata")){	
				
			try{
				pstmt=con.prepareStatement(EmployeeBasicData.toString());
				rs=pstmt.executeQuery();
				//System.out.println(EmployeeBasicData.toString()+":::");
				rsmd=rs.getMetaData();  
				if(rs.next()){

					session.setAttribute("Payperiod", rs.getString("PAYPERIOD"));
					session.setAttribute("Businessunit", rs.getString("BUSINESSUNITID"));
			//		session.setAttribute("paymonth", rs.getString("LASTPAYMONTH"));
					
					if(rs.getInt("COUNT")>0 && rs.getString("EMPLOYEESEQUENCENO")!=null){
						//Count=rs.getInt(1);
						
						lastpayperioddate=rs.getString("LASTPAYDATE");
						System.out.println(lastpayperioddate);
						lastpaymonth=rs.getInt("LASTPAYMONTH");
						for( int i = 1; i <= rsmd.getColumnCount(); i++ ){
							jsonObj.put(rsmd.getColumnLabel(i),rs.getString(i));
							
						}

						StringBuffer checkData=new StringBuffer();
						checkData.append("SELECT COUNT(*) FROM test.tbl_f_f_employee_basicdata WHERE EMPLOYEESEQUENCENO="+empid+" AND STATUS=1001");
						pstmt=con.prepareStatement(checkData.toString());
						rs=pstmt.executeQuery();
						if(rs.next()){
						Count=rs.getInt(1);
				//		System.out.println(Count+"::COunt");
						if(Count==0){
							jsonObj.put("STATUS1","1001");
							jsonObj.put("Error","");
						}else{
							jsonObj.put("STATUS1","1001");
							jsonObj.put("Error","F&F Already Submitted");
						}
						
						}
						
						// System.out.println(jsonObj) ;
					}
					else if(rs.getInt("COUNT")==0 && rs.getString("EMPLOYEESEQUENCENO")!=null){
						
						StringBuffer LoginBasedRights=new StringBuffer();
						
						LoginBasedRights.append(" SELECT COUNT(*) FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
						LoginBasedRights.append(" JOIN ( ");
						LoginBasedRights.append(" SELECT BUSINESSUNITID FROM HCLHRM_PROD.TBL_EMPLOYEE_BUSINESSUNIT WHERE EMPLOYEEID="+LOGINBY+" ");
						LoginBasedRights.append(" )B ON A.COMPANYID=B.BUSINESSUNITID ");
						LoginBasedRights.append(" WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
						
					//	System.out.println(LoginBasedRights);
						pstmt=con.prepareStatement(LoginBasedRights.toString());
						rs=pstmt.executeQuery();
						if(rs.next()){
							loginBU=rs.getInt(1);
							
							if(loginBU==0 ){
								
						//		System.out.println("Employee Does not belongs to this BU DOJ:: "+rs.getString("EMPLOYEESEQUENCENO"));
								jsonObj.put("STATUS1","1002");
								jsonObj.put("Error","Employee does not belongs to this BU / No Authorization to access this Employee");
								
							}else{
								
							//	System.out.println("Employee Does not belongs to this BU DOJ:: "+rs.getString("EMPLOYEESEQUENCENO"));
								jsonObj.put("STATUS1","1004");
								jsonObj.put("Error","Please Check Employee DateofResignation Submitted or not");
								
							}
						}
						
						
					
					}else {
			//			System.out.println("Please Enter Valid Employee ID :: ");
						jsonObj.put("Error","Please Enter Valid Employee ID");
					}	  
				}
		
				
			}catch(Exception erd){
				System.out.println("erd" +erd);
			}if(Count==0){
				
				try{
				rs=null;
				pstmt=null;
				pstmt=con.prepareStatement(CTCComponents.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd1=rs.getMetaData();   			 
				while(rs.next()){
					
					paramTitles.put(rs.getString(3)+"_T",rs.getString("COMPONENTNAME"));
					paramTitles.put(rs.getString(3)+"_TYPE",rs.getString("COMPONENTTYPECODE"));
					String looP_condition=rs.getString("COMPONENTTYPECODE").toString();
					
					jsonObj_Earnings=new JSONObject();
					jsonObj_Deductions=new JSONObject();
					jsonObj_Annual_Benifits=new JSONObject();
					for( int i = 1; i <= rsmd1.getColumnCount(); i++ ){
						
					//	System.out.println(rsmd1.getColumnLabel(i) +":::"+rs.getString(i));

						if(looP_condition.equalsIgnoreCase("E")){
							
							jsonObj_Earnings.put(rsmd1.getColumnLabel(i) , rs.getString(i));
							//values_Earnings.add(jsonObj_Earnings);
																
						}else if(looP_condition.equalsIgnoreCase("D")){
							jsonObj_Deductions.put(rsmd1.getColumnLabel(i) , rs.getString(i));
							//values_Deductions.add(jsonObj_Deductions);
							
						}else if(looP_condition.equalsIgnoreCase("O")){
							jsonObj_Annual_Benifits.put(rsmd1.getColumnLabel(i) , rs.getString(i));
							//values_Annual_Benifits.add(jsonObj_Annual_Benifits);
							
						}
							
					}
					components.add(rs.getString("PAYCOMPONENTID"));
			//		System.out.println(rs.getString("PAYCOMPONENTID"));
					
						
					if(looP_condition.equalsIgnoreCase("E")){
						
						values_Earnings.add(jsonObj_Earnings);
															
					}else if(looP_condition.equalsIgnoreCase("D")){
							values_Deductions.add(jsonObj_Deductions);
						
					}else if(looP_condition.equalsIgnoreCase("O")){
						values_Annual_Benifits.add(jsonObj_Annual_Benifits);
						
					}
					
					
					//}
					//jsonObj.put("STATUS","1001");

				}
				
				
				

				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(Earnings.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd2=rs.getMetaData();   			 
				while(rs.next()){
					
					paramTitles.put(rs.getString(2)+"_T",rs.getString("COMPONENTNAME"));
					paramTitles.put(rs.getString(2)+"_TYPE","ME");
					
					for( int i = 1; i <= rsmd2.getColumnCount(); i++ ){	
						jsonObj_Monthly_Earnings.put(rsmd2.getColumnLabel(i) , rs.getString(i));
						
					}
					values_Monthly_Earnings.add(jsonObj_Monthly_Earnings);
				//	jsonObj.put("STATUS","1001");
				}
				
				rs=null;
				pstmt=null;
				pstmt=con.prepareStatement(Deductions.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd3=rs.getMetaData();   			 
				while(rs.next()){
					paramTitles.put(rs.getString(2)+"_T",rs.getString("COMPONENTNAME"));
					paramTitles.put(rs.getString(2)+"_TYPE","MD");
					for( int i = 1; i <= rsmd3.getColumnCount(); i++ ){	
						jsonObj_Monthly_Deductions.put(rsmd3.getColumnLabel(i) , rs.getString(i));
						
					}
					values_Monthly_Deductions.add(jsonObj_Monthly_Deductions);
				//	jsonObj.put("STATUS","1001");
				}
				
	
					
				
				rs=null;
				pstmt=null;
				pstmt=con.prepareStatement(OtherDeductions.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd4=rs.getMetaData();   			 
				/*while(rs.next()){
					for( int i = 1; i <= rsmd4.getColumnCount(); i++ ){	
						jsonObj_Monthly_OtherDeductions.put(rsmd4.getColumnLabel(i) , rs.getString(i));
						
					}
					values_Monthly_OtherDeductions.add(jsonObj_Monthly_OtherDeductions);
				//	jsonObj.put("STATUS","1001");
				}
				*/
				while(rs.next()){
		
					paramTitles.put(rs.getString(1)+"_T",rs.getString("COMPONENTNAME"));
					paramTitles.put(rs.getString(1)+"_TYPE",rs.getString("COMPONENTTYPECODE"));
					
					   String looP_condition=rs.getString("COMPONENTTYPECODE").toString();
						
						jsonObj_other_monthly_Earnings=new JSONObject();
						jsonObj_other_monthly_Deductions=new JSONObject();
					
						for( int i = 1; i <= rsmd4.getColumnCount(); i++ ){
							
						//	System.out.println(rsmd1.getColumnLabel(i) +":::"+rs.getString(i));
							
							if(looP_condition.equalsIgnoreCase("E")){
								
								jsonObj_other_monthly_Earnings.put(rsmd4.getColumnLabel(i) , rs.getString(i));
								//values_Earnings.add(jsonObj_Earnings);
																	
							}else if(looP_condition.equalsIgnoreCase("D")){
								jsonObj_other_monthly_Deductions.put(rsmd4.getColumnLabel(i) , rs.getString(i));
								//values_Deductions.add(jsonObj_Deductions);
								
							}/*else if(looP_condition.equalsIgnoreCase("O")){
								jsonObj_Annual_Benifits.put(rsmd4.getColumnLabel(i) , rs.getString(i));
								//values_Annual_Benifits.add(jsonObj_Annual_Benifits);
								
							}*/
						}
						
						
							
						if(looP_condition.equalsIgnoreCase("E")){
							
							values_other_monthly_Earnings.add(jsonObj_other_monthly_Earnings);
																
						}else if(looP_condition.equalsIgnoreCase("D")){
							values_other_monthly_Deductions.add(jsonObj_other_monthly_Deductions);
							
						}
						
						
						//}
					//	jsonObj.put("STATUS","1001");
				
				}
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(PayPeriodHold.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd5=rs.getMetaData();   			 
				while(rs.next()){
					for( int i = 1; i <= rsmd5.getColumnCount(); i++ ){	
						jsonObj_hold_payperiod.put(rsmd5.getColumnLabel(i) , rs.getString(i));
						
					}
					values_hold_payperiod.add(jsonObj_hold_payperiod);
				//	jsonObj.put("STATUS","1001");
				}
				
				
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(LoanAmt.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd6=rs.getMetaData();   			 
				if(rs.next()){
					for( int i = 1; i <= rsmd6.getColumnCount(); i++ ){	
						jsonObj_Loan_Impress.put(rsmd6.getColumnLabel(i) , rs.getString(i));	
					}
					values_Loan_Impress.add(jsonObj_Loan_Impress);
				//	jsonObj.put("STATUS","1001");
				}
				
			/*	rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(ExpenseComponents.toString());
				rs=pstmt.executeQuery();
				ResultSetMetaData rsmd7=rs.getMetaData();   			 
				while(rs.next()){
					for( int i = 1; i <= rsmd7.getColumnCount(); i++ ){	
						jsonObj_Expense_Components.put(rsmd7.getColumnLabel(i) , rs.getString(i));
					}
					values_Expense_Components.add(jsonObj_Expense_Components);
				//	jsonObj.put("STATUS","1001");
				}*/
				
	/*----------------------------------------------------------Bonus Calculation----------------------------------------------------*/			
	
				String qry1="SELECT * FROM HCLADM_PROD.tbl_financial_year_months where MONTH="+lastpaymonth+"";
				String fyear=null;
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(qry1.toString());
				rs=pstmt.executeQuery();
				 
				if(rs.next()){
					
					fyear=rs.getString("FINANCIALYEAR");
				}

				
				StringBuffer CurrentBonus=new StringBuffer();

			/*	CurrentBonus.append(" SELECT IFNULL(FF.MMCOUNT,0) count_1, IFNULL(F3.EMPLOYEEID,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO="+empid+") ) EMPLOYEEID,IFNULL(SUM(F4.NETVALUE),0) SUM  FROM ");    // FF.MMCOUNT count_1,F3.EMPLOYEEID,SUM(F4.NETVALUE) SUM
				CurrentBonus.append(" (SELECT F2.*,F1.* FROM ");
				CurrentBonus.append("  ( ");
				CurrentBonus.append(" SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
				CurrentBonus.append(" ) F2 ");
				CurrentBonus.append(" LEFT JOIN ( ");
				CurrentBonus.append(" SELECT COUNT(MONTH) MMCOUNT FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
				CurrentBonus.append(" ) F1 ON 1=1 ");
				CurrentBonus.append(" ) FF ");
				CurrentBonus.append(" LEFT JOIN (SELECT MIN(MONTH) MM1 FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+") MM1 ON 1=1 ");
				CurrentBonus.append(" LEFT JOIN ( ");
				CurrentBonus.append(" SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
				CurrentBonus.append(" WHERE EMPLOYEESEQUENCENO="+empid+" GROUP BY EMPLOYEEID,PAYPERIOD ");
				CurrentBonus.append(" )F3 ON FF.MONTH=F3.PAYPERIOD ");
				CurrentBonus.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA F4 ON F3.EMPLOYEEID=F4.EMPLOYEEID AND F3.PAYPERIOD=F4.PAYPERIOD ");
				CurrentBonus.append(" WHERE F4.STATUS=1001 ");
				CurrentBonus.append(" AND F4.COMPONENTID=94 ");
				CurrentBonus.append(" AND F3.PAYPERIOD BETWEEN MM1.MM1 AND "+lastpaymonth+" ");*/
				
				
				CurrentBonus.append(" SELECT HH.*,TIMESTAMPDIFF(MONTH,FROMDATE,'"+lastpayperioddate+"') count_1 FROM ( ");
				CurrentBonus.append(" SELECT  IFNULL(F3.EMPLOYEEID,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO="+empid+") ) EMPLOYEEID,IFNULL(SUM(F4.NETVALUE),0) SUM,MM1.MM1  FROM ");    // FF.MMCOUNT count_1,F3.EMPLOYEEID,SUM(F4.NETVALUE) SUM
				CurrentBonus.append(" (SELECT F2.* FROM ");
				CurrentBonus.append("  ( ");
				CurrentBonus.append(" SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
				CurrentBonus.append(" ) F2 ");
			//	CurrentBonus.append(" LEFT JOIN ( ");
			//	CurrentBonus.append(" SELECT COUNT(MONTH) MMCOUNT FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
			//	CurrentBonus.append(" ) F1 ON 1=1 ");
				CurrentBonus.append(" ) FF ");
				CurrentBonus.append(" LEFT JOIN (SELECT MIN(MONTH) MM1 FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+") MM1 ON 1=1 ");
				CurrentBonus.append(" LEFT JOIN ( ");
				CurrentBonus.append(" SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
				CurrentBonus.append(" WHERE EMPLOYEESEQUENCENO="+empid+" GROUP BY EMPLOYEEID,PAYPERIOD ");
				CurrentBonus.append(" )F3 ON FF.MONTH=F3.PAYPERIOD ");
				CurrentBonus.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA F4 ON F3.EMPLOYEEID=F4.EMPLOYEEID AND F3.PAYPERIOD=F4.PAYPERIOD ");
				CurrentBonus.append(" WHERE F4.STATUS=1001 ");
				CurrentBonus.append(" AND F4.COMPONENTID=94 ");
				CurrentBonus.append(" AND F3.PAYPERIOD BETWEEN MM1.MM1 AND "+lastpaymonth+" ");	
				CurrentBonus.append(" ) HH ");
				CurrentBonus.append(" LEFT JOIN HCLADM_PROD.tbl_transaction_dates LL ON 1=1 ");
				CurrentBonus.append(" WHERE BUSINESSUNITID=(SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO="+empid+") AND TRANSACTIONDURATION=HH.MM1 ");
				CurrentBonus.append(" AND TRANSACTIONTYPEID=1 ");
				
				

				System.out.println(CurrentBonus);
				double checkpaidbonus_curr=0.00;
				int paycount_curr=0;
				int BONUS_CAL_1=0;
				int BONUS_CAL_2=0;
				
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(CurrentBonus.toString());
				rs=pstmt.executeQuery();
				
				while(rs.next()){
				
					checkpaidbonus_curr=rs.getDouble("SUM");
					paycount_curr=rs.getInt("count_1");
			//		System.out.println(checkpaidbonus_curr+"@@@@@@@@"+paycount_curr+"::::::paycount_curr");
				}
				System.out.println(checkpaidbonus_curr+"@@@@@@@@"+paycount_curr+"::::::paycount_curr");
				StringBuffer calc=new StringBuffer();
				
				calc.append("  SELECT CC.NAME COSTCENTER,BU.CALLNAME,A.EMPLOYEEID,PAYMONTH,B.COMPONENTVALUE,IF(BU.CALLNAME='HYD',((D.COMPONENTVALUE/12)*"+paycount_curr+"),((B.COMPONENTVALUE/12)*"+paycount_curr+")) BONUS_CAL ");
				calc.append("  FROM ( ");
				calc.append("  select distinct max(ctctransactionid) CTCTRANSACTION, ");
				calc.append("  date_format(effectivedate,'%Y%m') PAYMONTH, ");
				calc.append("  EMPLOYEEID ");
				calc.append("  from HCLHRM_PROD.tbl_employee_ctc where ");
				calc.append("   effectivedate <= LAST_DAY(DATE_FORMAT(STR_TO_DATE('"+lastpaymonth+"','%Y%m'),'%Y-%m-01')) group by employeeid ");
				calc.append("  )A  ");
				calc.append("  LEFT JOIN ( ");
				calc.append("  SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS ");
				calc.append("  WHERE COMPONENTID IN (24) ");
				calc.append("  )B ON A.CTCTRANSACTION=B.CTCTRANSACTIONID ");
				calc.append("  LEFT JOIN ( ");
				calc.append("  SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS ");
				calc.append("  WHERE COMPONENTID IN (36) ");
				calc.append("  ) D ON A.CTCTRANSACTION=D.CTCTRANSACTIONID ");
				calc.append("  LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY C ON A.EMPLOYEEID=C.EMPLOYEEID ");
				calc.append("  LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT BU ON C.COMPANYID=BU.BUSINESSUNITID ");
				calc.append("  LEFT JOIN HCLADM_PROD.TBL_COSTCENTER CC ON C.COSTCENTERID=CC.COSTCENTERID ");
				calc.append("  WHERE C.EMPLOYEESEQUENCENO="+empid+"  ");
				System.out.println(calc.toString());
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(calc.toString());
				rs=pstmt.executeQuery();
				
				
				String COSTCENTER=null;
				
				while(rs.next()){
					
					BONUS_CAL_1=rs.getInt("BONUS_CAL");	
					COSTCENTER=rs.getString("COSTCENTER");
					
				}
				
				
				if(checkpaidbonus_curr==0){
							
					/*
					String qry2="SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR<'"+fyear+"' ORDER BY FINANCIALYEAR DESC LIMIT 1";
					String fyear_1=null;
					rs=null;
					pstmt=null;
					rsmd=null;
					pstmt=con.prepareStatement(qry2.toString());
					rs=pstmt.executeQuery();
					 
					if(rs.next()){
						
						
						fyear_1=rs.getString("FINANCIALYEAR");
					}
					
					
					StringBuffer previousbonus=new StringBuffer();
					
					previousbonus.append(" SELECT COUNT(F6.PAYPERIOD) COUNT,AA.employeeid,AA.sum,AA.MIN,AA.MAX FROM (SELECT F4.employeeid,SUM(F5.NETVALUE) sum,MIN,MAX ");
					previousbonus.append(" FROM ( ");
					previousbonus.append(" SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear_1+"' ");
					previousbonus.append(" ) F2 ");
					previousbonus.append(" LEFT JOIN ( ");
					previousbonus.append(" SELECT FINANCIALYEAR,MIN(MONTH) MIN, MAX(MONTH) MAX FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear_1+"' ");
					previousbonus.append(" GROUP BY FINANCIALYEAR ");
					previousbonus.append(" ) F3 ON F2.FINANCIALYEAR=F3.FINANCIALYEAR ");
					previousbonus.append(" LEFT JOIN ( ");
					previousbonus.append(" SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
					previousbonus.append(" WHERE EMPLOYEESEQUENCENO="+empid+" GROUP BY EMPLOYEEID,PAYPERIOD ");
					previousbonus.append(" )F4 ON F2.MONTH=F4.PAYPERIOD ");
					previousbonus.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA F5 ON F4.EMPLOYEEID=F5.EMPLOYEEID AND F4.PAYPERIOD=F5.PAYPERIOD ");
					previousbonus.append(" WHERE F5.STATUS=1001 ");
					previousbonus.append(" AND F5.COMPONENTID=94 ");
					previousbonus.append(" AND F4.PAYPERIOD BETWEEN F3.MIN AND F3.MAX ");
					previousbonus.append("  ) AA ");
					previousbonus.append(" LEFT JOIN ( SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear_1+"' ) CF ON 1=1 ");
					previousbonus.append(" 		LEFT JOIN (SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
					previousbonus.append(" 		WHERE EMPLOYEESEQUENCENO="+empid+" GROUP BY EMPLOYEEID,PAYPERIOD ");
					previousbonus.append(" )F6 ON CF.MONTH=F6.PAYPERIOD ");

					System.out.println(previousbonus.toString());
					rs=null;
					pstmt=null;
					rsmd=null;
					pstmt=con.prepareStatement(previousbonus.toString());
					rs=pstmt.executeQuery();
					String EmpGeneratedID=null;
					int paycount=0;
					int minpayperiod=0;
					int maxpayperiod=0;
					double prevbonus_sum=0;
					while(rs.next()){
						
						EmpGeneratedID=rs.getString("employeeid");
						paycount=rs.getInt("COUNT");
						minpayperiod=rs.getInt("MIN");
						maxpayperiod=rs.getInt("MAX");
						prevbonus_sum=rs.getDouble("sum");
					}
					
				
						
					StringBuffer calc_2=new StringBuffer();
					
					calc_2.append("  SELECT BU.CALLNAME,A.EMPLOYEEID,PAYMONTH,B.COMPONENTVALUE,IF(BU.CALLNAME='HYD',((D.COMPONENTVALUE/12)*"+paycount+"),((B.COMPONENTVALUE/12)*"+paycount+")) BONUS_CAL ");
					calc_2.append("  FROM ( ");
					calc_2.append("  select distinct max(ctctransactionid) CTCTRANSACTION, ");
					calc_2.append("  date_format(effectivedate,'%Y%m') PAYMONTH, ");
					calc_2.append("  EMPLOYEEID ");
					calc_2.append("  from HCLHRM_PROD.tbl_employee_ctc where ");
					calc_2.append("   effectivedate <= LAST_DAY(DATE_FORMAT(STR_TO_DATE('"+maxpayperiod+"','%Y%m'),'%Y-%m-01')) group by employeeid ");
					calc_2.append("  )A  ");
					calc_2.append("  LEFT JOIN ( ");
					calc_2.append("  SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS ");
					calc_2.append("  WHERE COMPONENTID IN (24) ");
					calc_2.append("  )B ON A.CTCTRANSACTION=B.CTCTRANSACTIONID ");
					calc_2.append("  LEFT JOIN ( ");
					calc_2.append("  SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC_DETAILS ");
					calc_2.append("  WHERE COMPONENTID IN (36) ");
					calc_2.append("  ) D ON A.CTCTRANSACTION=D.CTCTRANSACTIONID ");
					calc_2.append("  LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY C ON A.EMPLOYEEID=C.EMPLOYEEID ");
					calc_2.append("  LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT BU ON C.COMPANYID=BU.BUSINESSUNITID ");
					calc_2.append("  WHERE C.EMPLOYEESEQUENCENO="+empid+"  ");
					
					
					
					System.out.println(calc_2.toString());
					rs=null;
					pstmt=null;
					rsmd=null;
					pstmt=con.prepareStatement(calc_2.toString());
					rs=pstmt.executeQuery();
					
					
					
					while(rs.next()){
						
						BONUS_CAL_2=rs.getInt("BONUS_CAL");	
						
					}
*/
					
					StringBuffer BonusFinal=new StringBuffer();							
					BonusFinal.append(" SELECT A.EMPLOYEEID,C.NAME COSTCENTER,IFNULL(B.BONUS,0) BONUS ");
					BonusFinal.append(" 	FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
					BonusFinal.append(" 	LEFT JOIN ( ");
					BonusFinal.append(" 	SELECT EMPLOYEEID,SUM(BONUSAMOUNT) BONUS FROM hclhrm_prod.tbl_employee_bonus WHERE STATUS=1001 ");
					BonusFinal.append(" 	GROUP BY EMPLOYEEID ");
					BonusFinal.append(" 	) B ON A.EMPLOYEEID=B.EMPLOYEEID ");
					BonusFinal.append(" 	LEFT JOIN hcladm_prod.tbl_costcenter C ON A.COSTCENTERID=C.COSTCENTERID ");
					BonusFinal.append(" 	WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
						
					
					rs=null;
					pstmt=null;
					rsmd=null;
					pstmt=con.prepareStatement(BonusFinal.toString());
					rs=pstmt.executeQuery();
					
					if(rs.next()){
						
						BONUS_CAL_2=rs.getInt("BONUS");
					
					}
					
				}
				
				
				if(COSTCENTER.equalsIgnoreCase("OFFICE")){
					total_bonus=BONUS_CAL_1+BONUS_CAL_2;
				}else{
					total_bonus=0.00;
				}
				values_Bonus_Calculation.add(total_bonus);
				
				
/*----------------------------------------------------------Bonus Calculation End------------------------------------------------------------------------*/	
/*				
				String qry1="SELECT * FROM HCLADM_PROD.tbl_financial_year_months where MONTH="+lastpaymonth+"";
				String fyear=null;
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(qry1.toString());
				rs=pstmt.executeQuery();
				 
				if(rs.next()){
					
					fyear=rs.getString("FINANCIALYEAR");
				}

				
				StringBuffer CurrentBonus=new StringBuffer();

				CurrentBonus.append(" SELECT IFNULL(FF.MMCOUNT,0) count_1, IFNULL(F3.EMPLOYEEID,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO=10411) ) EMPLOYEEID,IFNULL(SUM(F4.NETVALUE),0) SUM FROM ");
				CurrentBonus.append(" (SELECT F2.*,F1.* FROM ");
				CurrentBonus.append("  ( ");
				CurrentBonus.append(" SELECT * FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
				CurrentBonus.append(" ) F2 ");
				CurrentBonus.append(" LEFT JOIN ( ");
				CurrentBonus.append(" SELECT COUNT(MONTH) MMCOUNT FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+" ");
				CurrentBonus.append(" ) F1 ON 1=1 ");
				CurrentBonus.append(" ) FF ");
				CurrentBonus.append(" LEFT JOIN (SELECT MIN(MONTH) MM1 FROM HCLADM_PROD.tbl_financial_year_months WHERE FINANCIALYEAR='"+fyear+"' AND MONTH<="+lastpaymonth+") MM1 ON 1=1 ");
				CurrentBonus.append(" LEFT JOIN ( ");
				CurrentBonus.append(" SELECT * FROM HCLHRM_PROD.TBL_EMPLOYEE_PAYPERIOD_DETAILS ");
				CurrentBonus.append(" WHERE EMPLOYEESEQUENCENO="+empid+" GROUP BY EMPLOYEEID,PAYPERIOD ");
				CurrentBonus.append(" )F3 ON FF.MONTH=F3.PAYPERIOD ");
				CurrentBonus.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA F4 ON F3.EMPLOYEEID=F4.EMPLOYEEID AND F3.PAYPERIOD=F4.PAYPERIOD ");
				CurrentBonus.append(" WHERE F4.STATUS=1001 ");
				CurrentBonus.append(" AND F4.COMPONENTID=94 ");
				CurrentBonus.append(" AND F3.PAYPERIOD BETWEEN MM1.MM1 AND "+lastpaymonth+" ");

				System.out.println(CurrentBonus);
				double checkpaidbonus_curr=0.00;
				
				rs=null;
				pstmt=null;
				rsmd=null;
				pstmt=con.prepareStatement(CurrentBonus.toString());
				rs=pstmt.executeQuery();
				
				while(rs.next()){
				
					checkpaidbonus_curr=rs.getDouble("SUM");
			//		paycount_curr=rs.getInt("count_1");
			//		System.out.println(checkpaidbonus_curr+"@@@@@@@@"+paycount_curr+"::::::paycount_curr");
				}	
				
				
				
			StringBuffer BonusFinal=new StringBuffer();	
			
			if(checkpaidbonus_curr==0){
				
			BonusFinal.append(" SELECT A.EMPLOYEEID,C.NAME COSTCENTER,IFNULL(B.BONUS,0) BONUS ");
			BonusFinal.append(" 	FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			BonusFinal.append(" 	LEFT JOIN ( ");
			BonusFinal.append(" 	SELECT EMPLOYEEID,SUM(BONUSAMOUNT) BONUS FROM hclhrm_prod.tbl_employee_bonus WHERE STATUS=1001 ");
			BonusFinal.append(" 	GROUP BY EMPLOYEEID ");
			BonusFinal.append(" 	) B ON A.EMPLOYEEID=B.EMPLOYEEID ");
			BonusFinal.append(" 	LEFT JOIN hcladm_prod.tbl_costcenter C ON A.COSTCENTERID=C.COSTCENTERID ");
			BonusFinal.append(" 	WHERE A.EMPLOYEESEQUENCENO="+empid+" ");
				
			
			rs=null;
			pstmt=null;
			rsmd=null;
			pstmt=con.prepareStatement(BonusFinal.toString());
			rs=pstmt.executeQuery();
				
			ResultSetMetaData rsmd10=rs.getMetaData();   			 
			if(rs.next()){
				for( int i = 1; i <= rsmd10.getColumnCount(); i++ ){	
					jsonObj_Bonus_Amount.put(rsmd10.getColumnLabel(i) , rs.getString(i));	
				}
				values_Bonus_Amount.add(jsonObj_Bonus_Amount);
			//	jsonObj.put("STATUS","1001");
			}	
			
			}
			*/
				
				jsonObj.put("STATUS","1001");				
			}catch(Exception erd){
				System.out.println("Exception  ..!"+erd);
			}
			
		
		}
			
			session.setAttribute("empid", empid);
			session.setAttribute("paramTitles", paramTitles);
	//	System.out.println(paramTitles);
			
			values_EMPDATA.add(jsonObj);			
			jsonObj_main.put("EMPDATA", values_EMPDATA);
			jsonObj_main.put("Earnings", values_Earnings);
			jsonObj_main.put("Deductions", values_Deductions);
			jsonObj_main.put("Annual_Benifits", values_Annual_Benifits);		
			jsonObj_main.put("Monthly_Earnings", values_Monthly_Earnings);
			jsonObj_main.put("Monthly_Deductions", values_Monthly_Deductions);
			jsonObj_main.put("Monthly_Other_Earnings", values_other_monthly_Earnings);
			jsonObj_main.put("Monthly_Other_Deductions", values_other_monthly_Deductions);
			jsonObj_main.put("Hold_Payperiod", values_hold_payperiod);
			jsonObj_main.put("Loan_Impress", values_Loan_Impress);
		//	jsonObj_main.put("Expense_Components", values_Expense_Components);
			jsonObj_main.put("Bonus_calculation", values_Bonus_Calculation);
			
		//************************************************************************
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 				
		//	String bothJson = "["+values.toString()+","+jArray1.toString()+"]"; //Put both objects in an array of 2 elements
			System.out.println(jsonObj_main);
			out.write(jsonObj_main.toString());
		
			
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
			//	System.out.println(PayPeriodCal.toString());
				pstmt=con.prepareStatement(PayPeriodCal.toString());
				rs=pstmt.executeQuery();
				rsmd=rs.getMetaData();   
			                                          
			    if( rs.next() )
			      {
			          for( int i = 1; i <= rsmd.getColumnCount(); i++ )
			       
			          jsonObj.put(rsmd.getColumnLabel(i),rs.getString(i));
			          values.add(jsonObj);
			       
			      }

				response.setContentType("application/json"); 
				response.setCharacterEncoding("utf-8"); 		
				String bothJson1 = "["+values.toString()+"]"; //Put both objects in an array of 2 elements

			//	System.out.println(bothJson1+"----------");
				out.write(bothJson1);
			}

			
			
			
		}else{
			System.out.println(" Connection not established Please Relogin again...! ");
		}
	}catch(Exception e){
		
	}
		
		
	}

}
