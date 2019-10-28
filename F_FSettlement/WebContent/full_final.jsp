<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 <!--  <link rel="stylesheet" href="/resources/demos/style.css"> -->
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  
	  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="jquery.table2excel.js"></script>
<script src="js/tabledisplay.js"></script>

<%
String message="";
try{

message=request.getAttribute("message").toString();
}catch(Exception err){
	System.out.println("HI iam in JSP::"+err);
}

if(message==null){
	message="";
}
%>
<style>
body {
	overflow-x: hidden;
}
* {
	margin: 0;
	padding: 0;
}
input,input:read-only { 
      font-size: 11px;
       font-style: Arial;
     /* width: 100px; */
}
div.a{
  font-weight: 100;
 /*  font-style: Arial; */
  text-align: center;
}
.myclass {
    width: 80px;
}
tr:nth-child(odd) {background-color: #eaeaea;}
tr:nth-child(even) {background-color: #f3f2f2;}
.settlement_table input {
	outline: none;
    border: 1px solid #cfcfcf;
    padding: 3px;
    font-size: 9px;
	   
}
.settlement_table {
    border-collapse: collapse;
    width: 98%;
    margin: 0px 15px 15px 15px;
}
.settlement_table td {
	padding: 2px 8px;
    font-size: 9px;
	background-color: #fff;
    border: 1px solid #333;
    
}
.hold_salary_table td {
	/* padding: 24px 20px !important; */
}
.scroll_table::-webkit-scrollbar {
    width: 5px;
}

/* Track */
.scroll_table::-webkit-scrollbar-track {
    background: #f1f1f1; 
    
}
 
/* Handle */
.scroll_table::-webkit-scrollbar-thumb {
    background: #888; 
}

/* Handle on hover */
.scroll_table::-webkit-scrollbar-thumb:hover {
    background: #555; 
}

.payperiod_block::-webkit-scrollbar {
    width: 5px;
}

/* Track */
.payperiod_block::-webkit-scrollbar-track {
    background: #f1f1f1; 
}
 
/* Handle */
.payperiod_block::-webkit-scrollbar-thumb {
    background: #888; 
}

/* Handle on hover */
.payperiod_block::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
.hold_salary_table1 {
	min-height: 120px;
    max-height: 120px;
    width: 100%;
    overflow: auto;
}
.payperiod_block {
	min-height: 125px;
	max-height: 125px;
	overflow: auto;
	width:120px;
}
.scroll_table table td {
	font-size: 12px;
}
.scroll_table table th {
	font-size: 12px;
}
.gross_ctc {
	min-height: 131px;
	width: 100%;
}
.gross_ctc td {
	text-align: center;
}
.calculate {
	background-color: #333;
    border: none;
    outline: none;
    color: #fff;
    padding: 5px !important;
    font-size: 12px;
    font-weight: 600;
    border-radius: 5px;
}
.header-txt {
	font-size: 13px !important;
    font-weight: 600;
    text-transform: uppercase;
}

table{
	border-collapse: collapse;
    font-weight: 100;
    text-transform: uppercase; 
    font-style: Arial;
}
.pay_components {
	width: 98%;
    margin: 0px 15px 15px 15px;
}
.pay_components td {
	text-align: center;
	background-color: #fff;
    border: 1px solid #333;
    vertical-align: top;
}
.sub_pay_components {
	width: 100%;
}
.sub_pay_components th {
	font-size: 9px;
	font-weight: 600;
	text-align: center;
	padding: 5px 0px;
	border: 1px solid #333;
    background-color: #fff;
}
.sub_pay_components td {
	font-size: 9px;
	text-align: center;
	/* padding: 5px 0px; */
	border: 1px solid #333;
	background-color: #fff;
	vertical-align: top;
	border-right: none;
    border-left: none;
}
.sub_datatable td {
	padding: 5px 0px;
}
.sub_datatable input {
	width: 75px;
    text-align: right;
}
.sub_datatable {
	width: 100%;
	/* border-right: 1px solid #333;
    border-left: 1px solid #333; */
}
/* .table-htclear {
	height: 250px;
    overflow: auto;
}*/
.sub_datatable_deductions {
	border-right: none;
    border-left: none;
}
.sub_datatable td:first-child {
	border-right: 1px solid #333;
}
.sub_datatable_payable {
	border-left: none;
}
.footer_table {
	width: 50%;
    margin: 0 auto;
}

.border-right {
	border-right: 1px solid #333 !important;
}

.sub_pay_components_one{
	width: 50%;
}
.sub_pay_components_one th {
	font-size: 9px;
	font-weight: 600;
	text-align: center;
	padding: 5px 0px;
	border: 1px solid #333;
    background-color: #fff;
}
.sub_pay_components_one td {
	font-size: 9px;
	text-align: center;
	padding: 5px 0px;
	border: 1px solid #333;
	background-color: #fff;
	vertical-align: top;
	border-right: none;
    border-left: none;
}
</style>
<script>
function CallMsg(){
	

	document.getElementById("Errormsg").innerHTML="<%=message%>" ;
	
}
</script>
</head>
<body Onload="CallMsg();">
<form method="post" autocomplete="off" action="F_F_InsertData" name="f1" id="myform">
<table align="center" id="EmployeeData" class="settlement_table">

<tr>
	<td colspan="8" align="center" class="header-txt">Full & Final Settlement</td>
</tr>


<tr>
<td>Employee ID</td>
<td><input type="text" id="empid" name="empid" onblur="fetchdata()" onkeypress="return isNumber(event)" maxlength="6" autocomplete="off"> <input type="button" value="Reset" class="calculate" onclick="myFunction();"></td>
<td>Employee Name</td>
<td><input type="text" id="empname" name="empname" readonly></td>
<td>Date of Joining</td>
<td><input type="text" id="doj" name="doj" readonly></td>
<td>Department</td>
<td><input type="text" id="department" name="department" readonly></td>

</tr>


<tr>
<td>Designation</td>
<td><input type="text" id="designation" name="designation" readonly></td>
<td>Gross Monthly Salary</td>
<td><input type="text" id="gross_month" name="gross_month" readonly></td>
<td>Date of Resignation</td>
<td><input type="text" id="emp_resig" name="emp_resig" readonly></td>
<td>Last Working Date</td>
<td><input type="text" id="emp_relieving" name="emp_relieving" readonly></td>
</tr>

<tr>
<td>Last Payroll / Payroll End date</td>
<td><input type="text" id="last_payroll_date" name="last_payroll_date" readonly /></td>
<td>Payment Consider From</td>
<td><input type="text" id="resig_date" name="resig_date" onchange="Call()" readonly></td>
<td>Payment Consider To</td>
<td><input type="text" id="relieving_date" name="relieving_date" onchange="Call()" readonly></td>
<td>Current Experience (Years)</td>
<td><input type="text" id="experience" name="experience" readonly /></td>
</tr>



<tr>
<td>Total Days</td>
<td><input type="text" id="total_days" name="total_days"></td>
<td>LOP</td>
<td><input type="text" id="lop" name="lop" value='0' onblur="PayableDays()"/></td>
<td>Total Payable Days</td>
<td><input type="text" id="total_payable_days" name="total_payable_days"></td>
<td>Payable Base Days</td>
<td><input type="text" id="payable_basedays" name="payable_basedays" readonly></td>
</tr>

<tr>
<td>Payperiod Count</td>
<td><input type="text" id="payperiod_months_count" name="payperiod_months_count" readonly></td>
<td>Available EL</td>
<td><input type="text" id="available_earning_leaves" name="available_earning_leaves" value='0' readonly></td>
<td>Manual EL</td>
<td><input type="text" id="manual_earning_leaves" name="manual_earning_leaves" value='0' onkeypress="return isNumber(event)"  maxlength="5"></td>   <!-- onkeypress='return RateisNumber(event,this)' -->

<td>CTC Consider / Not </td>
<td>
Yes &nbsp; <input type="radio" name='ctc' id='ctc_y' value="yes" onclick="radioButtonCheck()" checked /> 
No &nbsp; <input type="radio" name='ctc' id='ctc_n' value="no" onclick="radioButtonCheck()" />

</td>
</tr>


</table>
<div align="center"><span id="Errormsg" style="color:red;" ></span></div>

<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->

<table align="center" class="pay_components">
	<tr>
		<td colspan="2" class="header-txt">PAY COMPONENTS	</td>
	</tr>
	<tr>
		<td>
		<div class="table-htclear">
			<table class="sub_pay_components">
				<tr>
					<th colspan="1">EARNINGS</th>
					<th colspan="1">DEDUCTIONS</th>
					<th colspan="1">ANNUAL BENIFITS</th>
				</tr>
				<tr>
					<td class="border-right"><div><table id="dataTable" class="sub_datatable"></table></div>
					</td>										 
					<td class="border-right"><div><table id="dataTable_ctcdeductions" class="sub_datatable sub_datatable_deductions"></table></div>
					</td>	
					<td><div><table id="dataTable_annual_benifits" class="sub_datatable"></table></div>
					</td>								  
													  
				</tr>
				<!-- <tr>
					<td>Total Earnings</td>

					<td>Total Deductions</td>
			
					<td>Total Annual Benifits</td>
					
				</tr> -->
			</table>
			</div>
		</td>
		<td style="width: 50%;">
		<div class="table-htclear">
			<table class="sub_pay_components">
				<tr>
					<th colspan="1">EARNINGS</th>
					<th colspan="1">DEDUCTIONS</th>
					<th colspan="1">ANNUAL BENIFITS</th>
				</tr>
			
				<tr>
					<td class="border-right"><div><table id="dataTable_payable" class="sub_datatable sub_datatable_payable"></table></div>
					</td>
					<td class="border-right"><div><table id="dataTable_ctcdeductions_payable" class="sub_datatable sub_datatable_deductions"></table></div>
					</td>
					<td><div><table id="dataTable_annual_benifits_payable" class="sub_datatable"></table></div>
					</td>								  													  
				</tr>
				
				<!-- <tr>
					<td>Total Earnings</td>
					
					<td>Total Deductions</td>
				
					<td>Total Annual Benifits</td>
					
				</tr> -->
			</table>
			</div>
		</td>
	</tr>
</table>
<table align="center" class="pay_components">
	<tr>
		<td>
			<table class="sub_pay_components">
				<tr>
					<th>MONTHLY EARNINGS</th>
					<th>MONTHLY DEDUCTIONS</th>
					<th>OTHERS</th>
				</tr>
				<tr>
					<td class="border-right"><div><table id="dataTable2" class="sub_datatable sub_datatable_payable"></table></div></td>
					<td class="border-right"><div><table id="dataTable3" class="sub_datatable sub_datatable_payable"></table></div></td>
					<td><div>
					<table class="sub_pay_components">
					<tr>
					<th>Earnings</th>
					<th>Deductions</th>
					</tr>
					<tr>
					<td class="border-right"><div><table id="dataTable4" class="sub_datatable sub_datatable_payable"></table></div></td>
					<td><div><table id="dataTable5" class="sub_datatable sub_datatable_payable"></table></div></td>
					</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<!-- <table id="expense_components" align="center" ></table>

	<table id="add" ></table>  -->

<table align="center" class="settlement_table footer_table">
<tr>

  <td colspan="3" style="display:none">
  
  <table border='1' class="hold_salary_table hold_salary_table1">
  <tr>
  <td align='center'>Hold Salary Release</td>  
  <td>
  <div id="myDiv" class="payperiod_block">
  <h3 align="center">Pay Periods</h3>
  <table id="dataTable6" align="center"></table>
  </div>
  </td>
  <td align='center'>
  <input type='text' class="myclass" name='holdsalary'  id='holdsalary'  value=0 readOnly >
  </td>
  
  </table>
  
  
  </td>
<!-- --------------********************* -->
<td colspan="3">
  <div id="myDiv1">
  <table border='1' class="gross_ctc">
 <tr>
<td>Gross</td>
<td><input type="text" id="CTCEarning" name="CTCEarning" value="0" class="myclass" readonly /></td>
<td>Deduction <br/>
<span style="font-size:10px;color:red">(Consider/Not) 
Yes &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_y' value="yes" onclick="TotalCalculation()" /> 
No &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_n'  value="no" onclick="TotalCalculation()" checked/> </span> </td>
<td><input type="text" id="CTCDeduction" name="CTCDeduction" value="0" class="myclass" readonly />
</td>
</tr>
<tr>
<td>OTH Earings</td>
<td><input type="text" id="OTH_Ear" name="OTH_Ear" value="0" class="myclass" readonly /></td>
<td>OTH Deductions</td>
<td><input type="text" id="OTH_Ded" name="OTH_Ded" value="0" class="myclass" readonly /></td>
</tr>
<tr>
<td>Others</td>
<td><input type="text" id="OTH_Others" name="OTH_Others" value="0" class="myclass" readonly />

<span id="Others_display" style="display:none">
 <input type='checkbox' id="OTH_Others_e_c" name="OTH_Others_e_c">
 <input type='checkbox' id="OTH_Others_d_c" name="OTH_Others_d_c">
 </span>
</td>
<!-- <td>EL Amount</td>
<td><input type="text" id="EL_Amount" name="EL_Amount" class="myclass" readonly></td> -->
</tr>


    
</table>
  
 </div> 
  </td>
  <!-- ------------------------*************************************** -->
</tr>

<tr>
<td colspan='3' style='display:none'></td>
<td colspan='0' align="center">Total : <input type='text' id="final_total" name="final_total" value='0' readonly /></td>
<td colspan='0' style='display:none'>Final Total : <input type='text' id="final_total_final" name="final_total_final" value='0' readonly /></td>
</tr>

<!-- <tr>

<td colspan='6' align='center' id="CTC_Calculate" style="display:none"><input class="calculate" type='button' value='Calculate CTC' onclick='loadCTC();' ></td>

</tr> -->


</table>
<br>

<div id="CTC_Calculate" style="text-align: center;display:none"><input class="calculate" type='button' value='Calculate CTC' onclick='loadCTC();' ></div>
<br>
<div id="FormSubmission" style="text-align: center;display:none" ><input type="button" value="Submit" class="calculate" onclick="FormSubmit();" /></div>

<select id="Components" style='display:none'></select>
<br>



 <script>

 var componentsEr=[]; 
 var componentsDe=[]; 
 
	var EarningsComponents = [];
	var DeductionComponents = [];	
	var Others_Earning_Components = [];
	var Others_Deduction_Components = [];
	var payperiod_HoldData = [];
	var Expense_Components=[];
 
 var Mindate="";
 var Maxdate="";
var Gratuity=0;
var CTC_Basic=0;
var EL_Amount_Cal=0;
var Loanamount=0.0;
var ImpressAmount=0;
var lastworkingdate="";
var available_earning_leaves=0;
var Experience="";

var bonus_final="";
var actual_bonus="";
 //var paycomponent_onhold=[];
 function fetchdata(){
	/*  $('#dataTable').html('');
	 $('#dataTable_ctcdeductions').html('');
	 $('#dataTable_annual_benifits').html('');
	 $('#dataTable_payable').html('');
	 $('#dataTable_ctcdeductions_payable').html('');
	 $('#dataTable_annual_benifits_payable').html('');
	 $('#dataTable2').html('');
	 
	 $('#dataTable3').html('');
	 $('#dataTable4').html('');
	 $('#dataTable5').html('');
	 
	 $('#dataTable6').html('');
	 
	 
	 $('#total_days').val('');
	 $('#lop').val('0');
	 $('#total_payable_days').val('');
	 $('#payable_basedays').val('');
	 $('#payperiod_months_count').val('');
	 $('#available_earning_leaves').val('0');
	 
	 document.getElementById('ctc_y').checked=true; */
	 
	 
	 $('#Errormsg').html('');
	 $('#resig_date').val('');
	 var empid=$('#empid').val();
	 available_earning_leaves=0;
	 Experience="";
	// alert(empid.length);
	 if(empid==''){
		 
		 $('#Errormsg').html('Please Enter EmployeeID');
		 $('#empid').focus();
		 $('#empid').css("border","1px solid red");
		 return false;
	 }
	 
	 if(empid.length>=5){
		 
	//	alert('success'); 
		$('#empid').attr('disabled','disabled');
	
		 $('#empid').css("border","1px solid #cfcfcf");

		var xhr = new XMLHttpRequest();
	/* 	var url = "url"; */
	var url = "FetchFormData?type=basicdata&employeeid="+empid;
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
	   // 	alert(xhr.responseText);
	    	
	          var myObj,x;
		       myObj=JSON.parse(xhr.responseText);
		       
		       var Data = myObj.EMPDATA[0];
		       
		       var data_2=JSON.stringify(Data);
		       var data_3 = data_2.replace("[", "").replace("]", "");
			   var  jsondata_1 = JSON.parse(data_3);
				
			   if(jsondata_1.STATUS==1002 && jsondata_1.STATUS1==1002){
			//	   alert('1');
				   $('#Errormsg').html(jsondata_1.Error);
					
					return false;
				   
			   } if(jsondata_1.STATUS==1002 && jsondata_1.STATUS1==1001){
			//	   alert('2');
				   $('#Errormsg').html(jsondata_1.Error);
					
					return false;
				   
			   } if(jsondata_1.STATUS==1001 && jsondata_1.STATUS1==1002){
			//	   alert('3');
				   $('#Errormsg').html(jsondata_1.Error);	
				   return false;
				   
			   }if(jsondata_1.STATUS==1001 && jsondata_1.STATUS1==1004){
			//	   alert('4');
				   $('#Errormsg').html(jsondata_1.Error);
					
					return false;
				   
			   }if(jsondata_1.STATUS==1002 && jsondata_1.STATUS1==1004){
			//	   alert('4');
				   $('#Errormsg').html(jsondata_1.Error);
					
					return false;
				   
			   }else{
	        
			//	   alert('5');
				for (xx in Data) {
		        //	alert(xx +"~~"+Data[xx]);
		        //	document.getElementById("demo").innerHTML += x[xx];
		        	   $('#empname').val(Data.CALLNAME);
					    $('#doj').val(Data.DATEOFJOIN);
					    $('#department').val(Data.Department);		    
					    $('#designation').val(Data.Designation);
					    $('#gross_month').val(Data.GROSS); 
				    	$('#emp_resig').val(Data.DATEOFRESIGNATION);
				    	$('#emp_relieving').val(Data.LASTWORKINGDATE);
				    //	$('#emp_relieving').val(jsondata_1.LASTWORKINGDATE);
				    	$('#available_earning_leaves').val(Data.EL);
				    	$('#relieving_date').val(Data.LASTWORKINGDATE);
				    	$('#experience').val(Data.EXPERIENCE);
				    	Experience=Data.EXPERIENCE;
				    	available_earning_leaves=Data.EL;
				 		lastworkingdate=Data.LASTWORKINGDATE;
				 		
				 		
				 			$('#last_payroll_date').val(Data.PAYPERIOD+" / "+Data.LASTPAYROLLDATE);
				 		
				    	
				    	  Mindate=Data.MINDATE;
							Maxdate=Data.LASTWORKINGDATE;
							Call1(Mindate,Maxdate);
		        	}
				var tr;

				for(var i=0;i<myObj.Earnings.length;i++){

					x = myObj.Earnings[i];
					
					/* "COMPONENTNAME": "TRAVEL ALLOWANCE",
					"COMPONENTVALUE": "0",
					"PAYCOMPONENTID": "18",
					"COMPONENTTYPECODE": "E" */
				
					componentsEr.push(x.PAYCOMPONENTID);		
				//	alert(componentsEr);
					
			if(i!=0){
					    tr = $('<tr>');
			            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
			            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
			            $('#dataTable').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Actual</td></tr>");		         
		            $('#dataTable').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
		            $('#dataTable').append(tr);
			   }
					
				}
				
				
				for(var i=0;i<myObj.Earnings.length;i++){

					x = myObj.Earnings[i];
					
				
					if(i!=0){
					    tr = $('<tr>');
			            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
			            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
			            $('#dataTable_payable').append(tr);
					}else{
						   tr = $('<tr>');
				            tr.append("<td>Head</td>");  
				            tr.append("<td>Payable</td></tr>");		         
				            $('#dataTable_payable').append(tr); 
				            
				            tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
				            $('#dataTable_payable').append(tr);
						
					}
		
					}
				
				for(var i=0;i<myObj.Deductions.length;i++){

					x = myObj.Deductions[i];
					 
					componentsDe.push(x.PAYCOMPONENTID);	
					
					//alert(componentsDe);
					
					
			        if(i!=0){
			            	   tr = $('<tr>');
					            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
					            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
					            $('#dataTable_ctcdeductions').append(tr);
			
				   }  else{
					   
					   tr = $('<tr>');
			            tr.append("<td>Head</td>");  
			            tr.append("<td>Actual</td></tr>");		         
			            $('#dataTable_ctcdeductions').append(tr); 
			            
			            tr = $('<tr>');
			            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
			            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
			            $('#dataTable_ctcdeductions').append(tr);
				   }
			            
			            
					}
				
				
				
				for(var i=0;i<myObj.Deductions.length;i++){

					x = myObj.Deductions[i];
					 
		
			        if(i!=0){
			            	   tr = $('<tr>');
					            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
					            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
					            $('#dataTable_ctcdeductions_payable').append(tr);
					            
					            
			
				   }  else{
					   
					   tr = $('<tr>');
			            tr.append("<td>Head</td>");  
			            tr.append("<td>Payable</td></tr>");		         
			            $('#dataTable_ctcdeductions_payable').append(tr); 
			            
			            tr = $('<tr>');
			            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
			            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
			            $('#dataTable_ctcdeductions_payable').append(tr);
				   }
			            
			            
					}
				
				
				
				
				
				
				for(var i=0;i<myObj.Annual_Benifits.length;i++){

					x = myObj.Annual_Benifits[i];
					
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
				            $('#dataTable_annual_benifits').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Actual</td></tr>");		         
		            $('#dataTable_annual_benifits').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td > <input type='text' id='"+x.PAYCOMPONENTID.concat('_A')+"' name='"+x.PAYCOMPONENTID.concat('_A')+"' value='"+x.COMPONENTVALUE+"' readonly /> </td></tr>");		         
		            $('#dataTable_annual_benifits').append(tr);
			   }
					   
		
					}
				
				for(var i=0;i<myObj.Annual_Benifits.length;i++){

					x = myObj.Annual_Benifits[i];
					
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
				            $('#dataTable_annual_benifits_payable').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Payable</td></tr>");		         
		            $('#dataTable_annual_benifits_payable').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID.concat('_P')+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' readonly /> </td></tr>");		         
		            $('#dataTable_annual_benifits_payable').append(tr);
			   }
					   
		
					}
				
				
				for(var i=0;i<myObj.Monthly_Earnings.length;i++){

					x = myObj.Monthly_Earnings[i];
					
					EarningsComponents.push(x.PAYCOMPONENTID);
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)'/> </td></tr>");		         
				            $('#dataTable2').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Payable</td></tr>");		         
		            $('#dataTable2').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
		            $('#dataTable2').append(tr);
			   }
					   
		
					}
					
				
				
				for(var i=0;i<myObj.Monthly_Deductions.length;i++){

					x = myObj.Monthly_Deductions[i];
					
					DeductionComponents.push(x.PAYCOMPONENTID);
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
				            $('#dataTable3').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Payable</td></tr>");		         
		            $('#dataTable3').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
		            $('#dataTable3').append(tr);
			   }
					   
		
					}
				
				

				for(var i=0;i<myObj.Monthly_Other_Earnings.length;i++){

					x = myObj.Monthly_Other_Earnings[i];
					
					Others_Earning_Components.push(x.PAYCOMPONENTID);
					
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
				            $('#dataTable4').append(tr);
		
			   }  else{
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Payable</td></tr>");		         
		            $('#dataTable4').append(tr); 
		            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
		            $('#dataTable4').append(tr);
			   }
					   
		
					}
				
				
				for(var i=0;i<myObj.Monthly_Other_Deductions.length;i++){

					x = myObj.Monthly_Other_Deductions[i];
					Others_Deduction_Components.push(x.PAYCOMPONENTID);
					
					  if(i!=0){
		            
		            	   tr = $('<tr>');
				            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
				            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
				            $('#dataTable5').append(tr);
		
			   }  else{
				   
				   
				   tr = $('<tr>');
		            tr.append("<td>Head</td>");  
		            tr.append("<td>Payable</td></tr>");		         
		            $('#dataTable5').append(tr); 
						            
		            tr = $('<tr>');
		            tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
		            tr.append("<td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P')+"' value='0' onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
		            $('#dataTable5').append(tr);
			   }
					   
		
					}
				

				
				
				for(var i=0;i<myObj.Hold_Payperiod.length;i++){

					x = myObj.Hold_Payperiod[i];
					
					  tr = $('<tr>');
			  //          tr.append("<td>" + x.COMPONENTNAME+ "</td>");  
			            tr.append("<td ><input type='checkbox' id='"+x.PAYPERIOD+"' name='"+x.PAYPERIOD.concat('_H')+"' value='"+x.NETVALUE+"' onchange='ClearTable()'/>"+x.PAYPERIOD+"</td></tr>");		         
			            $('#dataTable6').append(tr);
				
				}
				
				
				
			
				
			/* 	for(var i=0;i<myObj.Expense_Components.length;i++){

					x = myObj.Expense_Components[i];
					Expense_Components.push(x.PAYCOMPONENTID);
					
					  if(i!=0){
		            
		            	   tr = $('<th>');
				            tr.append(x.COMPONENTNAME+ "</th>");  
				            tr.append("<tr><td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P[]')+"' value='0'  onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
				          $('#expense_components').append(tr);
		
			   }  else{
				   
						            
		            tr = $('<th>');
		            tr.append(x.COMPONENTNAME+ "</th>");  
		            
		            tr.append("<tr><td ><input type='text' id='"+x.PAYCOMPONENTID+"' name='"+x.PAYCOMPONENTID.concat('_P[]')+"' value='0' onkeyup='ClearTable()' onkeypress='return RateisNumber(event,this)' /> </td></tr>");		         
		           $('#expense_components').append(tr);
			   }
					  
					}	
				  tr = $('<tr>');
				  tr.append("<input type='button' value='AddRow' id='addrecord' onclick='validData()' \></tr>");
		          $('#expense_components').append(tr); 
				 */
				   var Data2 = myObj.Loan_Impress[0];
			        
				 //  alert(Data2);
			        
					for (yy in Data2) {
						
						Loanamount=Data2.Recovery_Amount;
						 ImpressAmount=Data2.IMPRESTAMT;
						$('#84').val(Data2.Recovery_Amount);
						$('#105').val(ImpressAmount);
					//	$('#102-D').val(Data2.IMPRESTAMT);
					}
					
					
				//	alert(document.getElementById('39_A').value);
					var Data3= myObj.Bonus_calculation;
						
					/*  for(zz in Data3){  */
						
						
						bonus_final=Data3.BONUS;
						actual_bonus="";
					//	alert(myObj.Bonus_calculation[0]);
					/* if(Data3.COSTCENTER="FIELD"){
						if(!document.getElementById('94')){
							$('#94').val('0');
						}else{
							$('#94').val(bonus_final);
						}
					}else{
						
						if(!document.getElementById('94')){
							$('#94').val('0');
						}else{
							$('#94').val('0');
						}
						
					}	 */
					
						if(!document.getElementById('94')){
							$('#94').val('0');
						}else{
							$('#94').val(myObj.Bonus_calculation[0]);
						}
					
					
						if(!document.getElementById('39_A')){
							$('#69').val('0');
							
						}else{
							$('#69').val(document.getElementById('39_A').value);
						}
					
						
					/*  }  */
						
					
					
					/* 	$('#84_P').val(jsondata_9.Recovery_Amount);
						$('#102-D').val(jsondata_9.IMPRESTAMT);
						Loanamount=jsondata_9.Recovery_Amount;
						 ImpressAmount=jsondata_9.IMPRESTAMT; */
						
		/* var ddlCustomers = document.getElementById("Components");
         
          //Add the Options to the DropDownList.
          for (var i = 0; i < data6.length; i++) {
              var option = document.createElement("OPTION");

              //Set Customer Name in Text part.
              option.text = data6[i];

              //Set CustomerId in Value part.
              option.value = data6[i];

              //Add the Option element to DropDownList.
       //       ddlCustomers.options.add(option,null);
              
              ddlCustomers.options.add(option);
              
        
		} */
          CTC_Basic=document.getElementById('24_A').value;
          
          /*-------------------EL Encashment Cal--------*/        
          EL_Amount_Cal=Math.floor((CTC_Basic/30)*available_earning_leaves).toFixed(0);         
          $('#50').val(EL_Amount_Cal);
       
          /*-------------------End EL Encashment Cal--------*/
          
          /*----------------------------------Gratuity Cal-------------------------------------*/
 		
			if(Experience>=4.6){
				
				Gratuity=Math.round((CTC_Basic/26)*15*Math.round(Experience)).toFixed(0);
				
			}else{
				Gratuity=Math.round((CTC_Basic/26)*15*0).toFixed(0);
			}
          
          if(!document.getElementById('52')){
        	 
          }else{
        	  document.getElementById('52').value=Gratuity; 
          }
			
        
          
		/*-----------------------------------End Gratuity Cal------------------------------------*/
		
		FormDates();
		    }
						
					}
				};

				xhr.send();
			} else {
				
				$('#Errormsg').html('Please Enter EmployeeID length should be minimum 5 & max length 6');
				
				 AjaxValidation();

				//  $('#CTCEarning').val('');
			return false;
			}

		}

	/*------------------------------------------------------Earning Leaves---------------------------------------------------*/
$('#manual_earning_leaves').keyup(function(){

	var dynamic_el=document.getElementById('available_earning_leaves').value;	
	var manual_el=document.getElementById('manual_earning_leaves').value;
	
	
	if(manual_el>0){
		
		 EL_Amount_Cal=Math.floor((CTC_Basic/30)*manual_el).toFixed(0);         
         $('#50').val(EL_Amount_Cal);		
	}else{
		 EL_Amount_Cal=Math.floor((CTC_Basic/30)*dynamic_el).toFixed(0);         
         $('#50').val(EL_Amount_Cal);
	}
	
});

    /*------------------------------------------------------Earning Leaves End---------------------------------------------------*/
 /*----------------------------------------------------addrow---------------------------------------------------------------------*/   
    
  
 
  /*   function validData(){
    	//alert('1');
    	var tr;
    	        for (var i = 0; i < Expense_Components.length; i++) {
    		        
   		    	 com_Obj=Expense_Components[i].toString();
   		    	var Com_value= document.getElementById(""+com_Obj+"");	    
   		    	
   		      tr = $('<th>'); 
	          tr.append("<tr><td>"+Com_value.value+ "</td></tr>");	
	          $('#add').append(tr); 
   		     }  
    	        tr = $('<th>');
    	        tr.append("<tr><td><input type='button' value='deleterow'></td></tr></th> ");
    	        $('#add').append(tr);    
    	     //   $('#add').append("<br/>");

    } */
 
 
 /*----------------------------------------------------addrow---------------------------------------------------------------------*/   
    
 
	/*---------------------------------------------------------Dates Cal------------------------------------------------------*/

		function Mindate1() {
			//alert(Mindate);
			return Mindate;
		}
		function Maxdate1() {
			//alert(Maxdate);
			return Maxdate;
		}
		function Call() {

		
			// alert(Maxdate1());
			$("#resig_date").datepicker(
					{
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd.mm.yy',
						minDate : Mindate1(),
						maxDate : Maxdate1(),
						// numberOfMonths: 3,
						 onClose : function(selectedDate) {
							$("#relieving_date").datepicker("option","minDate", selectedDate);
						
						}
					
					});

	 	$("#relieving_date").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd.mm.yy',
				yearRange : '-12:+0',
				maxDate : Maxdate1(),
				/* onClose: function (selectedDate) {
				     $("#resig_date").datepicker("option", "maxDate", selectedDate);
				 } */
			}); 
	 	
	 	var resig_date=$("#resig_date").val();
	 	var relieving_date=$("#relieving_date").val();
	 	
	if(resig_date!='' && relieving_date!='') 	{
		Calculation_Dates();
	}

		}
		function Call1(Mindate, Maxdate) {

			//alert(Mindate);

			$("#resig_date").datepicker(
					{
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd.mm.yy',
						minDate : Mindate,
						maxDate : Maxdate,

						// numberOfMonths: 3,
					 onClose : function(selectedDate) {
							$("#relieving_date").datepicker("option","minDate", selectedDate);
							//alert(selectedDate);
						}
					});

				$("#relieving_date").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd.mm.yy',
				yearRange : '-12:+0',
				maxDate : Maxdate,
			// numberOfMonths: 3,
		   onClose: function (selectedDate) {
			     $("#resig_date").datepicker("option", "maxDate", selectedDate);
			 }  
			});
				
		}

		/* $("#resig_date").datepicker({
			 
			yearRange : "-90:+0",
			changeMonth : true,
			changeYear : true, 
			maxDate:0,
			dateFormat : 'dd.mm.yy',
			onSelect : function(date) {
				
				$("#relieving_date").datepicker('option', 'minDate', date);
				
			}
			
		});

		$("#relieving_date").datepicker({
			
			yearRange : "-90:+0",
			changeMonth : true,
			changeYear : true,
			minDate:0,
			dateFormat : 'dd.mm.yy'

		}); */

		/*------------------Dates End-----------------------------*/

		/* $("#resig_date").datepicker({
		 
			yearRange : "-90:+0",
			changeMonth : true,
			changeYear : true, 
			maxDate: $("#resig_date").val(),
			dateFormat : 'dd.mm.yy',
			onSelect : function(date) {
				$("#relieving_date").datepicker('option', 'minDate', date);
			}
		}); */

		function Calculation_Dates(){
//alert($('#relieving_date').val());
			var fromdate = document.getElementById('resig_date').value.split('.').reverse().join('-');
			var todate = document.getElementById('relieving_date').value.split('.').reverse().join('-');
			//	alert(fromdate + "::" +todate);
			var from = new Date(fromdate);
			var to = new Date(todate);
			var diffDays = parseInt((to - from)/ (1000 * 60 * 60 * 24));
			var Days = diffDays + 1;
			if ($('#resig_date').val() == "") {
				$('#Errormsg').html('Please Select Start Date...');
		//	$('#relieving_date').val('');
			$('#total_days').val('');
			$('#total_payable_days').val('');
			$('#lop').val('0');
			} else {
			document.getElementById('total_days').value = Days;
			document.getElementById('total_payable_days').value = Days;
			PayableDays();
			$('#CTC_Calculate').show();
			}
		}

		/* 	
			function daysdiff1() {
				var fromdate = document.getElementById('fromdate1').value.split('.').reverse().join('-');
				var todate = document.getElementById('todate1').value.split('.').reverse().join('-');
				var from = new Date(fromdate);
				var to = new Date(todate);

				var diffDays = parseInt((to - from) / (1000 * 60 * 60 * 24));
			//	alert(diffDays);
				var Days = diffDays + 1;
				document.getElementById('induction_totaldays').value = Days;
			}
		 */
	</script>
 <script>
 
function radioButtonCheck(){
	// alert(document.getElementById('ctc_y').checked);
	var yes=document.getElementById('ctc_y').checked;
	var no=document.getElementById('ctc_n').checked;

	if(yes==true){
		
		$('#FormSubmission').hide();
		
	//	$('#resig_date').val('');
		$('#resig_date').removeAttr('disabled');
	//	$('#relieving_date').val('');
		$('#relieving_date').removeAttr('disabled');
		

		$('#total_days').removeAttr('disabled');
		$('#lop').removeAttr('disabled');
		$('#total_payable_days').removeAttr('disabled');
		$('#payable_basedays').removeAttr('disabled');
		$('#payperiod_months_count').removeAttr('disabled');
		
		$('#CTC_Calculate').hide();
		 $('#resig_date').val('');
		 $('#relieving_date').val(lastworkingdate);
		 $('#total_days').val('');
		 $('#lop').val('0');
		 $('#total_payable_days').val('');
		 $('#payable_basedays').val('');
		 $('#payperiod_months_count').val('');
		 $('#OTH_Others').val('0');
		  for (var i = 0; i < componentsEr.length; i++) {
		        
		    	 com_Obj=componentsEr[i].toString();
		    	// alert(com_Obj);
		    	 com_Obj_A=com_Obj.concat("_A");
		    	 com_Obj_P=com_Obj.concat("_P");
	
		    	document.getElementById(""+com_Obj_P+"").value=0;
		     } 

		     for (var i = 0; i < componentsDe.length; i++) {
		         
		    	 com_Obj=componentsDe[i].toString();
		    	 com_Obj_A=com_Obj.concat("_A");
		    	 com_Obj_P=com_Obj.concat("_P");
		    	document.getElementById(""+com_Obj_P+"").value=0;;
		    	
		     } 
		     
		     document.getElementById('CTCEarning').value=0;
	         document.getElementById('CTCDeduction').value=0;
	        for(var i=0;i<EarningsComponents.length;i++){
	     		docId=document.getElementById(""+EarningsComponents[i]+"").value=0;
	     	}
	     document.getElementById('OTH_Ear').value=0;
	     for(var i=0;i<DeductionComponents.length;i++){			
			 deduction_componentId=document.getElementById(""+DeductionComponents[i]+"").value=0;
			}
	 	$('#84').val(Loanamount);

		 document.getElementById('OTH_Ded').value=0;
		 
		 
		 
		 for(var i=0;i<Others_Earning_Components.length;i++){
			
			 Others_ComponentID=document.getElementById(""+Others_Earning_Components[i]+"").value=0; 

			}
		 for(var i=0;i<Others_Deduction_Components.length;i++){
				
			 Others_Deduction_ComponentID=document.getElementById(""+Others_Deduction_Components[i]+"").value=0; 

			}
		 
			$('#105').val(ImpressAmount);
			document.getElementById('52').value=Gratuity;
		 document.getElementById('OTH_Others').value=0;

		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=false;
		
		 document.getElementById('50').value=EL_Amount_Cal;
		 document.getElementById('final_total').value='0';
		 document.getElementById('final_total_final').value='0';
		 
		 $('#94').val(Math.round(bonus_final));
			
			if(!document.getElementById('39_A')){
				$('#69').val('0');
				
			}else{
				$('#69').val(document.getElementById('39_A').value);
			}
		 
		 FormDates(); 
		 
	}else{
		
		$('#FormSubmission').hide();
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val('');
		$('#relieving_date').attr('disabled','disabled');
		

		$('#total_days').val('');
		$('#total_days').attr('disabled','disabled');
		
		$('#lop').val('');
		$('#lop').attr('disabled','disabled');
		
		$('#total_payable_days').val('');
		$('#total_payable_days').attr('disabled','disabled');
		
		$('#payable_basedays').val('');
		$('#payable_basedays').attr('disabled','disabled');
		
		$('#payperiod_months_count').val('');
		$('#payperiod_months_count').attr('disabled','disabled');
		
		$('#CTC_Calculate').show();
		 $('#resig_date').val('');
		// $('#relieving_date').val('');
		 $('#total_days').val('');
		 $('#lop').val('0');
		 $('#total_payable_days').val('');
		 $('#payable_basedays').val('');
		 $('#payperiod_months_count').val('');
		 $('#OTH_Others').val('0');
		  for (var i = 0; i < componentsEr.length; i++) {
		        
		    	 com_Obj=componentsEr[i].toString();
		    	// alert(com_Obj);
		    	 com_Obj_A=com_Obj.concat("_A");
		    	 com_Obj_P=com_Obj.concat("_P");
	
		    	document.getElementById(""+com_Obj_P+"").value=0;;
		     } 

		     for (var i = 0; i < componentsDe.length; i++) {
		         
		    	 com_Obj=componentsDe[i].toString();
		    	 com_Obj_A=com_Obj.concat("_A");
		    	 com_Obj_P=com_Obj.concat("_P");
		    	document.getElementById(""+com_Obj_P+"").value=0;;
		    	
		     } 
		     
		     document.getElementById('CTCEarning').value=0;
	         document.getElementById('CTCDeduction').value=0;
	        for(var i=0;i<EarningsComponents.length;i++){
	     		docId=document.getElementById(""+EarningsComponents[i]+"").value=0;
	     	}
	     document.getElementById('OTH_Ear').value=0;
	     for(var i=0;i<DeductionComponents.length;i++){			
			 deduction_componentId=document.getElementById(""+DeductionComponents[i]+"").value=0;
			}
	     $('#84').val(Loanamount);
	
		 document.getElementById('OTH_Ded').value=0;
		 
		 for(var i=0;i<Others_Earning_Components.length;i++){
				
			 Others_ComponentID=document.getElementById(""+Others_Earning_Components[i]+"").value=0; 

			}
		 for(var i=0;i<Others_Deduction_Components.length;i++){
				
			 Others_Deduction_ComponentID=document.getElementById(""+Others_Deduction_Components[i]+"").value=0; 

			}
			$('#105').val(ImpressAmount);
			document.getElementById('52').value=Gratuity;
		 document.getElementById('OTH_Others').value=0;

		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=false;
		 document.getElementById('50').value=EL_Amount_Cal;
		 document.getElementById('final_total').value='0';
		 document.getElementById('final_total_final').value='0';
		 
	         
	}
 }
 
 // ********* caliculation part***********
 var CTCSum=0;
 var CTCSum_Dedu=0;
 var LTA_97=0;
 var BONUS_98=0;
 function loadCTC(){
	  LTA_97=0;
	  BONUS_98=0;
	 $('#Errormsg').html('');
	 $('#FormSubmission').show();
	 var SelObj = document.getElementById("Components");
     var com_Ob="";
     var Com_gross=0;
     var Com_Paid=0;
     var Com_Sum=0;
     var Com_SumPaid=0;
     //var emp_workdays=20;
     var emp_workdays=document.getElementById('total_payable_days').value;
     var Sum_of_payble_payperiod_days=document.getElementById('payable_basedays').value;  
     var payperiod_count=document.getElementById('payperiod_months_count').value;
     var com_Obj_A=0;
     var com_Obj_P=0;
     CTCSum=0;
     CTCSum_Dedu=0;
   //  alert(componentsEr+"::"+componentsDe);
     //Add the Options to the DropDownList.
     
     
    //  alert(document.getElementById('ctc_n').checked=true);
     
  try{
	 
	  
	  if(document.getElementById('ctc_n').checked==true){
		  $('#CTC_Calculate').show();
		  CTCSum=0; 
		  document.getElementById('CTCEarning').value=0;
          document.getElementById('CTCDeduction').value=0;
	     }
	  else if(document.getElementById('ctc_y').checked==true){
	  
	if($('#resig_date').val()==''){
		$('#Errormsg').html('Please Select CTC CONSIDER FROM');
		$('#FormSubmission').hide();
		return false;
	}else if($('#relieving_date').val()==''){
		$('#Errormsg').html('Please Select LAST WORKING DATE');
		$('#FormSubmission').hide();
		return false;
	}	  
		  
		  
		  
     for (var i = 0; i < componentsEr.length; i++) {
        
    	 com_Obj=componentsEr[i].toString();
    	// alert(com_Obj);
    	 com_Obj_A=com_Obj.concat("_A");
    	 com_Obj_P=com_Obj.concat("_P");
    	 var Com_gross= document.getElementById(""+com_Obj_A+"");
    	 var Com_paid_box=document.getElementById(""+com_Obj_P+"");
    	 Com_Sum=(eval(payperiod_count)*eval(Com_gross.value)) / eval(Sum_of_payble_payperiod_days);
   // 	 alert(Com_Sum);
    	 Com_SumPaid=Com_Sum * emp_workdays;
 //   	 alert(Com_SumPaid);
    	 Com_paid_box.value=Math.floor(Com_SumPaid).toFixed(0);
    	 console.log(Com_SumPaid); 
    	 if(com_Obj=="22"){
    		 
    	    CTCSum=CTCSum+Com_SumPaid;
    	    
    	 }  
     } 
     
  
     
     var Gross=document.getElementById("22_P");
         Gross=Gross.value;
     if(Gross!=CTCSum){
    	 document.getElementById("22_P").value=Math.round(CTCSum);
     }
     
     
     //alert(Math.floor(CTCSum));
     
      com_Ob="";
      Com_gross=0;
      Com_Paid=0;
      Com_Sum=0;
      Com_SumPaid=0;
      com_Obj_A=0;
      com_Obj_P=0;
     
     for (var i = 0; i < componentsDe.length; i++) {
         
    	 com_Obj=componentsDe[i].toString();
    	 com_Obj_A=com_Obj.concat("_A");
    	 com_Obj_P=com_Obj.concat("_P");
    	 var Com_gross= document.getElementById(""+com_Obj_A+"");
    	 var Com_paid_box=document.getElementById(""+com_Obj_P+"");
    	 
    	 if(com_Obj=="30" || com_Obj=="31" || com_Obj=="32"){
    	 Com_Sum=eval(Com_gross.value) / eval(Sum_of_payble_payperiod_days);
    	 Com_SumPaid=Com_Sum * emp_workdays;
    	 Com_paid_box.value=Math.floor(Com_SumPaid).toFixed(0);
    	 }else{
    		 Com_Sum=eval(Com_paid_box.value);
    		 Com_SumPaid=Com_Sum;
    	//	 Com_paid_box.value=Math.floor(Com_SumPaid).toFixed(0);
    	 }
    	 
    	 console.log(Com_SumPaid); 
    	 if(com_Obj!="22" ){
    		 CTCSum_Dedu=CTCSum_Dedu+Com_SumPaid;
    	 }  
     } 
     
     console.log(CTCSum +"<--E  D-->"+  CTCSum_Dedu);
    // CTCSum=CTCSum;
      
     if(document.getElementById('97_P') && document.getElementById('98_P')){
    CTCSum=CTCSum+eval(document.getElementById('97_P').value)+eval(document.getElementById('98_P').value);
     }else if(!document.getElementById('97_P') && document.getElementById('98_P')){
    	 CTCSum=CTCSum+eval(0)+eval(document.getElementById('98_P').value);
     }else if(document.getElementById('97_P') && !document.getElementById('98_P')){
    	 CTCSum=CTCSum+eval(document.getElementById('97_P').value)+eval(0);
     }else{
    	 CTCSum=CTCSum+eval(0)+eval(0);
     }
    document.getElementById('CTCEarning').value=Math.round(CTCSum);
    document.getElementById('CTCDeduction').value=Math.floor(CTCSum_Dedu);
 //    alert(CTCSum + "~~~" +CTCSum_Dedu);
   //  alert("SUM::::" + evalCTCSum-CTCSum_Dedu);
     CTCSum=eval(CTCSum)-eval(CTCSum_Dedu);
   //  alert(CTCSum +" ::SUM ");
   
  }
     EarningCal(CTCSum);
     TotalCalculation();
  
  }catch(err){
	  alert(err.message);
  }
	 
 }
 
 var Ear_Sum=0;
 
 function EarningCal(ErSum){
	 Ear_Sum=0;
	 var EarningCal=document.getElementById('EarningCal');
	 
//	alert(EarningsComponents);
	var docId=0;
	for(var i=0;i<EarningsComponents.length;i++){
		
		docId=document.getElementById(""+EarningsComponents[i]+"");
		//alert(docId+"::ids");
		docId=docId.value;
		Ear_Sum=Ear_Sum+eval(docId);
		// console.log(docId); 
	}
	//CTCSum=ErSum.toFixed(0);
document.getElementById('OTH_Ear').value=Ear_Sum.toFixed(0);
DeductionCal();
	// alert(docId+ "::NetPaidSalary ::" +CTCSum);
	 
 }
 
 var Other_Deduction_sum=0;
 
 function DeductionCal(){
	 
	 Other_Deduction_sum=0;
	 var deduction_componentId=0;
	 var Other_Deduction=document.getElementById('DeductionCal');
//	 alert(DeductionComponents.length);
	 for(var i=0;i<DeductionComponents.length;i++){
			
		 deduction_componentId=document.getElementById(""+DeductionComponents[i]+"");
		 deduction_componentId=deduction_componentId.value;
	     Other_Deduction_sum=Other_Deduction_sum+eval(deduction_componentId);

		}
	 
	 document.getElementById('OTH_Ded').value=Other_Deduction_sum;
	 OtherCal();
	 
 }
 
 var Others_Cal_Ear=0;
 var Others_Cal_Ded=0;
 var Others_final=0;
 
 function OtherCal(){
	 
	 document.getElementById('OTH_Others_e_c').checked=false;
	 document.getElementById('OTH_Others_d_c').checked=false;
	 Others_Cal_Ear=0;
	 Others_final=0;
	 Others_Cal_Ded=0;
	 var Earning_componentId_others=0;
	 var Deduction_componentId_others=0;
	 var ComponentId_Others=document.getElementById('Others_Caliculation');
	 
	 for(var i=0;i<Others_Earning_Components.length;i++){
		
		 Earning_componentId_others=document.getElementById(""+Others_Earning_Components[i]+"");
		 Earning_componentId_others=Earning_componentId_others.value;
		 Others_Cal_Ear=Others_Cal_Ear+eval(Earning_componentId_others);
		}
	 
//	 alert(Others_Cal_Ear);
	 for(var i=0;i<Others_Deduction_Components.length;i++){
		 
		 Deduction_componentId_others=document.getElementById(""+Others_Deduction_Components[i]+"");
		 Deduction_componentId_others=Deduction_componentId_others.value;
		 Others_Cal_Ded=Others_Cal_Ded+eval(Deduction_componentId_others);
	 }
//	 alert(Others_Cal_Ded);
	 
	 Others_final=Others_Cal_Ear-Others_Cal_Ded;
	
	 if(Others_final>0){
	 document.getElementById('OTH_Others_e_c').checked=true;
	 document.getElementById('OTH_Others_d_c').checked=false;
	 document.getElementById('OTH_Others').value=Others_final;
	 }else{
		 
		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=true;
		 document.getElementById('OTH_Others').value=eval(Others_final) ;
	 }
  
	
 }
 
 
 var sum_payabledays;
 
function PayableDays(){
	 
	// alert('relieving_date');
	sum_payabledays=0;
	var empid=document.getElementById('empid').value;
	var fromdate=document.getElementById('resig_date').value;
	var todate=document.getElementById('relieving_date').value;
	
	var gross_month=document.getElementById('gross_month').value;
	var total_days=document.getElementById('total_days').value;
	var lop=document.getElementById('lop').value;
	var total_payable_days=0;
	
	
	if(empid==''){
		
		$('#Errormsg').html('Please Enter EmployeeID');
		return false;
	}
	else if(fromdate=='' && todate==''){
		$('#Errormsg').html('Please select start and end date');
	 $('#total_days').val('');	
		$('#total_payable_days').val('');
		$('#lop').val('0');
		return false;
	}else if(fromdate==''){
		
		$('#Errormsg').html('Please select start date');
		return false;
		
	}else if(todate==''){
		$('#Errormsg').html('Please select end date');
		return false;		
	} /* else if(todate < fromdate){
		
		alert('end date should be greater than start date');
		return false;
	} */ else if(lop==''){
		
		$('#Errormsg').html('Please enter LOP >= 0');
		return false;
	}else{
		total_payable_days=total_days-lop;
		document.getElementById('total_payable_days').value=total_payable_days;
		
	}
	
if(fromdate!='' && todate!='' && lop!=''){
		

	var xhr = new XMLHttpRequest();
	var url = "AjaxData?type=payperiodcount&fromdate="+fromdate+"&todate="+todate;
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		    	//alert(xhr.responseText);
		    	
		    	   var data = JSON.parse(xhr.responseText);
		    	   var data_1=JSON.stringify(data[0]);
		    	   var data_2 = data_1.replace("[", "").replace("]", "");
					var  jsondata_2 = JSON.parse(data_2);
					console.log(jsondata_2.PAYPERIODCOUNT);
					
					var payperiod_count=jsondata_2.PAYPERIODCOUNT;
					var payperiod_days=jsondata_2.DAYS_PAYPERIOD;
					
					sum_payabledays=((payperiod_count*gross_month)/payperiod_days)*total_payable_days;

					console.log(sum_payabledays);
					document.getElementById('payperiod_months_count').value=payperiod_count;
					document.getElementById('payable_basedays').value=payperiod_days;
					
		    }
		};
	
	xhr.send();	
	
	}
	
 }
 
$('#myDiv').change(function() {
	  var values = 0.00;
	  {
	    $('#myDiv :checked').each(function() {
	      //if(values.indexOf($(this).val()) === -1){
	      values=values+parseFloat(($(this).val()));
	      // }
	    });
	    document.getElementById('holdsalary').value=values;
	    
	  //  console.log( parseFloat(values));
	  }
	});
	
	
function TotalCalculation(){
	  var values = 0.00;
	  var holdsalary=$('#holdsalary').val();
	  var CTCDeduction=document.getElementById('CTCDeduction').value;
	  var OTH_Ded=document.getElementById('OTH_Ded').value;
	  {
	    $('#myDiv1 :text').each(function() {
	    //	alert($(this).attr('id')=="CTCDeduction");
	    	
	      //if(values.indexOf($(this).val()) === -1){
	    	  
	if($(this).attr('id')!="CTCDeduction" && $(this).attr('id')!="OTH_Ded"){
		
		 values=values+parseFloat(($(this).val()));
	}
	
	// values=values+parseFloat(($(this).val()));
	//alert(values);
	     
	      // }
	    });
	    if(document.getElementById('ctc_ded_n').checked==true){
	    	
	    	values=values-eval(OTH_Ded);
	    }else if(document.getElementById('ctc_ded_y').checked==true){
	    //	alert(values);
	    	sum_ded=eval(CTCDeduction)+eval(OTH_Ded);
	    	values=values-sum_ded;
	    }
	    
	    if(values>0){
	    	  document.getElementById('final_total').value=values;
	    	  holdsalary=eval(holdsalary)+values;
	    	  $('#final_total_final').val(holdsalary);
	    }else{
	    	  document.getElementById('final_total').value=values ;	
	    	  holdsalary=eval(holdsalary)+ (values);
	    	  $('#final_total_final').val(holdsalary);
	    }
	  
	  //  console.log( parseFloat(values));
	  }
	}
 
function myFunction() {
    location.reload();
}


function ClearTable(){
	
	$('#CTCEarning').val('0');
	$('#CTCDeduction').val('0');
	$('#OTH_Ear').val('0');
	$('#OTH_Ded').val('0');
	$('#OTH_Others').val('0');
	document.getElementById('OTH_Others_e_c').checked=false;
	document.getElementById('OTH_Others_d_c').checked=false;
	$('#final_total').val('0');
	$('#final_total_final').val('0');
	document.getElementById('ctc_ded_n').checked=true;
	document.getElementById('ctc_ded_y').checked=false;
	
	$('#FormSubmission').hide();
//	$('#CTCEarning').val('');
}




function FormDates(){
	
	var emp_resig=$('#emp_resig').val();
	var parts=emp_resig.split('.');	
	var date =new Date(parts[2], parts[1] - 1, parts[0]);
	//alert(date);
	var emp_relieving=$('#emp_relieving').val();
	var parts2=emp_relieving.split('.');
	var date2 =new Date(parts2[2], parts2[1] - 1, parts2[0]);   
	
	
	var last_payroll_date=$('#last_payroll_date').val();
	var date1=last_payroll_date.split('/');
	var parts3=date1[1].split('.');	
	var date3=new Date(parts3[2], parts3[1] - 1, parts3[0]);   
	
	if(emp_resig==''){
		$('#Errormsg').html("Please Update Date of Resignation in HRMS...");
		$('#emp_resig').focus();
		$('#emp_resig').css("border","1px solid red");
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val('');
		$('#relieving_date').attr('disabled','disabled');
		document.getElementById('ctc_n').checked=false;
		document.getElementById('ctc_y').checked=true;
		$('#ctc_y').attr('disabled','disabled');
		$('#ctc_n').attr('disabled','disabled');
		
		$('#total_days').val('');
		$('#total_days').attr('disabled','disabled');
		
		$('#lop').val('');
		$('#lop').attr('disabled','disabled');
		
		$('#total_payable_days').val('');
		$('#total_payable_days').attr('disabled','disabled');
		
		$('#payable_basedays').val('');
		$('#payable_basedays').attr('disabled','disabled');
		
		$('#payperiod_months_count').val('');
		$('#payperiod_months_count').attr('disabled','disabled');
		
		return false;
	}else if(date > date2){
		
		$('#Errormsg').html("Please Update Date of Resignation / Last Working Date in HRMS. Date of Resignation should not be Greater than Last Working Date");
		$('#emp_resig').focus();
		$('#emp_resig').css("border","1px solid red");
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val('');
		$('#relieving_date').attr('disabled','disabled');
		
		$('#total_days').val('');
		$('#total_days').attr('disabled','disabled');
		
		$('#lop').val('');
		$('#lop').attr('disabled','disabled');
		
		$('#total_payable_days').val('');
		$('#total_payable_days').attr('disabled','disabled');
		
		$('#payable_basedays').val('');
		$('#payable_basedays').attr('disabled','disabled');
		
		$('#payperiod_months_count').val('');
		$('#payperiod_months_count').attr('disabled','disabled');
		return false;
	}
	
	else if(date2 <= date3){    //jsondata_1.LASTPAYROLLDATE <= 
		/* $('#Errormsg').html("Please Update Last Working Date in HRMS...");
		$('#emp_relieving').focus();
		$('#emp_relieving').css("border","1px solid red"); */
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val('');
		$('#relieving_date').attr('disabled','disabled');
		document.getElementById('ctc_n').checked=true;
		document.getElementById('ctc_y').checked=false;
		$('#ctc_y').attr('disabled','disabled');
		$('#CTC_Calculate').show();
		
		$('#total_days').val('');
		$('#total_days').attr('disabled','disabled');
		
		$('#lop').val('');
		$('#lop').attr('disabled','disabled');
		
		$('#total_payable_days').val('');
		$('#total_payable_days').attr('disabled','disabled');
		
		$('#payable_basedays').val('');
		$('#payable_basedays').attr('disabled','disabled');
		
		$('#payperiod_months_count').val('');
		$('#payperiod_months_count').attr('disabled','disabled');
		
	//	return false;
	}
	
	
	
}


function AjaxValidation(){
		    $('#empname').val('');
			$('#designation').val('');
			$('#department').val('');
			$('#doj').val('');
			$('#resig_date').val('');
			$('#relieving_date').val('');
			$('#gross_month').val('');
			$('#emp_relieving').val('');
			$('#emp_resig').val('');
			$('#total_days').val('');
			$('#lop').val('0');
			$('#total_payable_days').val('');
			$('#payable_basedays').val('');
			$('#payperiod_months_count').val('');
			$('#available_earning_leaves').val('0');
			$('#dataTable').html('');
			$('#dataTable2').html('');
			$('#dataTable3').html('');
			$('#dataTable4').html('');
			$('#dataTable5').html('');
			$('#holdsalary').val('0');
			$('#CTCEarning').val('');
			$('#CTCDeduction').val('');
			$('#OTH_Ear').val('');
			$('#OTH_Ded').val('');
			$('#OTH_Others').val('');
			$('#CTC_Calculate').hide();
		   	document.getElementById('OTH_Others_e_c').checked=false;
		   	document.getElementById('OTH_Others_d_c').checked=false;

		}
		
		
		
/* $('#empid').change(function(){
	$('#empid').css("border","1px solid #cfcfcf");
}); */


 function FormSubmit(){
	
	
	document.f1.action="F_F_InsertData";
	document.f1.submit();
	
	
	/* alert("FormSubmit");
	
	var empid=$('#empid').val();
	
	
	var xhr = new XMLHttpRequest();
	var url = "F_F_InsertData?empid="+empid;
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		    	//alert(xhr.responseText);					
		    }
		};
	
	xhr.send();	 */
	
}

</script>

<script type="text/javascript">


function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode >= 48 && charCode <= 57) {
		return true;
	}
	return false;
}


function RateisNumber(evt,val) {
	 evt = (evt) ? evt : window.event;
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	 // var dat=document.getElementById('VAT').value;
	 var y = String.fromCharCode(event.keyCode);
	 var Data= document.getElementById(""+val.id+"").value;
	 //alert(Data.length);
	 if(Data.length==0){

	 if(y=="."){
	 // alert(1);
	 return false;
	 }
	 }
	 Data=Data.concat(y);
	 var char='';
	 var count=0;
	 try{
	 for(var i=0;i<Data.length;i++){
	 char=Data.charAt(i);

	 if(char=="."){

	 count=count+1; 
	 }
	 //alert("char::"+char);

	 }
	 }catch(err){

	 alert(err.message);
	 }
	 //alert(count+"::count")
	 if(count>1){
	 return false;
	 }


	 if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=46) {

	 return false;
	 }
	 return true;
	 }




</script>

 </form>

</body>
</html>