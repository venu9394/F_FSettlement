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

<style>
input,input:read-only { 
      font-size: 11px;
       font-style: Arial;
     
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
    padding: 3px 5px;
    font-size: 11px;
     font-style: Arial;
	   
}
.settlement_table {
    border-collapse: collapse;
    
    
}
.settlement_table td {
	padding: 3px 5px;
    font-size: 12px;
  /*  font-weight: 600; */
    
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
	min-height: 140px;
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
	font-size: 18px !important;
    font-weight: 600;
    text-transform: uppercase;
    font-style: italic;
}

table{

    font-weight: 100;
    text-transform: uppercase; 
    font-style: Arial;
}
</style>
</head>
<body>

<table align="center" class="settlement_table">

<tr>
	<td colspan="7" align="center" class="header-txt">Full & Final Settlement</td>
</tr>


<tr>
<td>Employee ID</td>
<td><input type="text" id="empid" name="empid" onblur="fetchdata()" maxlength="6" autocomplete="off"> <input type="button" value="Reset" class="calculate" onclick="myFunction();"></td>
<td>Employee Name</td>
<td><input type="text" id="empname" name="empname" readonly></td>
<td>Date of Joining</td>
<td><input type="text" id="doj" name="doj" readonly></td>

</tr>


<tr>
<td>Department</td>
<td><input type="text" id="department" name="department" readonly></td>
<td>Designation</td>
<td><input type="text" id="designation" name="designation" readonly></td>
<td>Gross Monthly Salary</td>
<td><input type="text" id="gross_month" name="gross_month" readonly></td>
</tr>

<tr>
<td>Date of Resignation</td>
<td><input type="text" id="emp_resig" name="emp_resig" readonly></td>
<td>Last Working Date</td>
<td><input type="text" id="emp_relieving" name="emp_relieving" readonly></td>
<td>Last Payroll / Payroll End date</td>
<td><input type="text" id="last_payroll_date" name="last_payroll_date" readonly /></td>
</tr>


<tr>
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
</tr>

<tr>
<td>Payable Base Days</td>
<td><input type="text" id="payable_basedays" name="payable_basedays" readonly></td>
<td>Payperiod Count</td>
<td><input type="text" id="payperiod_months_count" name="payperiod_months_count" readonly></td>
<td></td>
<td></td>
</tr>


<tr>
<td>Available EL</td>
<td><input type="text" id="available_earning_leaves" name="available_earning_leaves" value='0' readonly></td>

<td>CTC Consider / Not </td>
<td>
Yes &nbsp; <input type="radio" name='ctc' id='ctc_y' onclick="radioButtonCheck()" checked /> 
No &nbsp; <input type="radio" name='ctc' id='ctc_n' onclick="radioButtonCheck()" />

</td>
<td><!-- CTC Deductions (Consider / Not) --></td>
<td><!-- Yes &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_y' /> 
No &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_n'  />  --></td>


</tr>
</table>
<br/>
<div align="center"><span id="Errormsg" style="color:red;" ></span></div>
<br/>
<table align="center" class="settlement_table">
<tr>

  <td colspan="3">
  
  <table border='1' class="hold_salary_table hold_salary_table1">
  <tr>
  <td align='center'>Hold Salary Release</td>  
  <td>
  <div id="myDiv" class="payperiod_block">
  <h3 align="center">Pay Periods</h3>
  <table id="dataTable5" align="center"></table>
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
<td><input type="text" id="CTCEarning" class="myclass" readonly /></td>
<td>Deduction <br/>
<span style="font-size:10px;color:red">(Consider/Not) 
Yes &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_y' onclick="TotalCalculation()" /> 
No &nbsp; <input type="radio" name='ctc_ded' id='ctc_ded_n'  onclick="TotalCalculation()" /> </span> </td>
<td><input type="text" id="CTCDeduction" class="myclass" readonly />
</td>
</tr>
<tr>
<td>OTH Earings</td>
<td><input type="text" id="OTH_Ear" class="myclass" readonly /></td>
<td>OTH Deductions</td>
<td><input type="text" id="OTH_Ded" class="myclass" readonly /></td>
</tr>
<tr>
<td>Others</td>
<td><input type="text" id="OTH_Others" class="myclass" readonly />

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
<td colspan='6' align='center'>Total : <input type='text' id="final_total" readonly /></td>
</tr>

<tr>

<td colspan='6' align='center' id="CTC_Calculate" style="display:none"><input class="calculate" type='button' value='Calculate CTC' onclick='loadCTC();' ></td>

</tr>


</table>
<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->

<table align="center" id="Total_Overall_Payable">
<tr>
<th>Pay Components</th>
<th>Earnings</th>
 <th>Deductions</th> 
<th>Others</th>
</tr>
<tr>
 <td><div style="overflow: scroll; height:300px;width:750px;overflow-x: auto;" class="scroll_table">								  
													  <table  align="center">
													  <tr>
													  <td>
													  <h3 align="center">Earnings</h3>
													  <div style="overflow: scroll; height:150px;width:350px;overflow-x: auto;" class="scroll_table">
													  <table id="dataTable" align="center"></table>
													  </div>
													  </td>
													   <td>
													  <h3 align="center">Deductions</h3>
													  <div style="overflow: scroll; height:150px;width:350px;overflow-x: auto;" class="scroll_table">
													  <table id="dataTable_ctcdeductions" align="center"></table>
													  </div>
													  </td>
													 
													  </tr>
													  <tr>
													   <td colspan="6"  align="center">
													  <h3 align="center">Annual Benifits</h3>
													  <div style="overflow: scroll; height:150px;width:350px;overflow-x: auto;" class="scroll_table">
													  <table id="dataTable_annual_benifits"></table>
													  </div>
													  </td>
													  </tr>
													  </table>
													  </div></td>
 <td><div id="EarningCal" style="overflow: scroll; height:300px;width:300px;overflow-x: auto;" class="scroll_table">								  
													  <table  id="dataTable2" align="center"></table>
													  </div></td>
 <td><div id="DeductionCal" style="overflow: scroll; height:300px;width:300px;overflow-x: auto;" class="scroll_table">								  
													  <table  id="dataTable3" align="center"></table>
													  </div></td>
<td><div id="Others_Caliculation" style="overflow: scroll; height:300px;width:300px;overflow-x: auto;" class="scroll_table">								  
													  <table  id="dataTable4" align="center"></table>
													  </div></td>

</tr>
</table> 
<select id="Components" style='display:none'></select>
<br>

<div id="FormSubmission" style="text-align: center;display:none" ><input type="button" value="Submit" class="calculate" onclick="FormSubmit();" /></div>

 <script>

 var componentsEr=[]; 
 var componentsDe=[]; 
 var Mindate="";
 var Maxdate="";
var Gratuity=0;
var CTC_Basic=0;
var EL_Amount_Cal=0;
var Loanamount=0.0;
var ImpressAmount=0;
 //var paycomponent_onhold=[];
 function fetchdata(){
	 
	 $('#Errormsg').html('');
	 $('#resig_date').val('');
	 var empid=$('#empid').val();
	 var available_earning_leaves=0;
	 var Experience="";
	// alert(empid.length);
	 if(empid==''){
		 
		 $('#Errormsg').html('Please Enter EmployeeID');
		 $('#empid').focus();
		 $('#empid').css("border","1px solid red");
		 return false;
	 }
	 
	 if(empid.length>=5){
		 
		//alert('success'); 
		 
		 $('#empid').css("border","1px solid #cfcfcf");

		var xhr = new XMLHttpRequest();
	/* 	var url = "url"; */
	var url = "AjaxData?type=basicdata&employeeid="+empid;
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		    //	alert(xhr.responseText);
		    
		        var json = JSON.parse(xhr.responseText);
		  
		    //    alert(componentsEr);
		        
				if(json=='101'){
					  
					$('#Errormsg').html('Please Contact Admin...');
					AjaxValidation();
				
				  }else if(json=='102'){
					  
					  
					  $('#Errormsg').html('Connection Problem. Please try again...');
					  AjaxValidation();
				   	 
				    	 
				  }else if(json=='204'){
					  
					  $('#Errormsg').html('Please Enter Valid EmployeeID');
					  AjaxValidation();
				   	 
				  }else if(json=='1004'){
					  
					  $('#Errormsg').html('Please Check Employee DateofResignation Submitted or not');
					  AjaxValidation();
				  } else{
					  
					  $('#empid').attr("disabled", "disabled");
					  $('#CTC_Calculate').hide();
					  
					  var data1=JSON.stringify(json[0]);
					  var data2=JSON.stringify(json[1]);
					// alert(data2);
					// alert(data2[1]);
					  var data3=JSON.stringify(json[2]);
					  var data4=JSON.stringify(json[3]);
					  var data5=JSON.stringify(json[4]);
					  var data6=json[5];
		
					  componentsEr=json[6];
					  componentsDe=json[7];
					 var paycomponent_onhold=JSON.stringify(json[8]);
					 // alert(json[8]);
					 var data9=JSON.stringify(json[9]);
					 var data10=JSON.stringify(json[10]);
					 var data11=JSON.stringify(json[11]);
					  
				        var data_1 = data1.replace("[", "").replace("]", "");
						var  jsondata_1 = JSON.parse(data_1);
						console.log(jsondata_1.CALLNAME);
					  
					    $('#empname').val(jsondata_1.CALLNAME);
					    $('#doj').val(jsondata_1.DATEOFJOIN);
					    $('#department').val(jsondata_1.Department);		    
					    $('#designation').val(jsondata_1.Designation);
					    $('#gross_month').val(jsondata_1.GROSS); 
				    	$('#emp_resig').val(jsondata_1.DATEOFRESIGNATION);
				    	$('#emp_relieving').val(jsondata_1.LASTWORKINGDATE);
				    //	$('#emp_relieving').val(jsondata_1.LASTWORKINGDATE);
				    	$('#available_earning_leaves').val(jsondata_1.EL);
				    	$('#relieving_date').val(jsondata_1.LASTWORKINGDATE);
				    	$('#experience').val(jsondata_1.EXPERIENCE);
				    	Experience=jsondata_1.EXPERIENCE;
				    	available_earning_leaves=jsondata_1.EL;
				 		
				    	$('#last_payroll_date').val(jsondata_1.PAYPERIOD+" / "+jsondata_1.LASTPAYROLLDATE);
					 
					    loadData(data2);
					    loadData2(data3);
					    loadData3(data4);
					    loadData4(data5);
						loadData5(paycomponent_onhold);
						loadData10(data10);
						loadData11(data11);
						
					    Mindate=jsondata_1.MINDATE;
						Maxdate=jsondata_1.LASTWORKINGDATE;
						Call1(Mindate,Maxdate);
						var data_9 = data9.replace("[", "").replace("]", "");
						var  jsondata_9 = JSON.parse(data_9);
						$('#84').val(jsondata_9.Recovery_Amount);
						$('#102-D').val(jsondata_9.IMPRESTAMT);
						Loanamount=jsondata_9.Recovery_Amount;
						 ImpressAmount=jsondata_9.IMPRESTAMT;
						
		var ddlCustomers = document.getElementById("Components");
         
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
              
        
		}
          CTC_Basic=document.getElementById('24_A').value;
          
          /*-------------------EL Encashment Cal--------*/        
          EL_Amount_Cal=Math.floor((CTC_Basic/30)*available_earning_leaves).toFixed(0);         
          $('#50-E').val(EL_Amount_Cal);
       
          /*-------------------End EL Encashment Cal--------*/
          
          /*----------------------------------Gratuity Cal-------------------------------------*/
 		
			if(Experience>=4.6){
				
				Gratuity=Math.floor((CTC_Basic/26)*15*5).toFixed(0);
				
			}else{
				Gratuity=Math.floor((CTC_Basic/26)*15*0).toFixed(0);
			}
			document.getElementById('52-E').value=Gratuity;
		/*-----------------------------------End Gratuity Cal------------------------------------*/
		
		FormDates();
     //     alert(jsondata_1.LASTPAYROLLDATE);
	/* 	if(jsondata_1.DATEOFRESIGNATION==''){
			alert("Please Update Date of Relieving in HRMS...");
			$('#emp_resig').focus();
			$('#emp_resig').css("border","1px solid red");
			return false;
		}else if(jsondata_1.DATEOFRESIGNATION < jsondata_1.LASTWORKINGDATE){
			
			alert("Please Update Date of Relieving in HRMS. It should be greater than Last Working Date");
			$('#emp_resig').focus();
			$('#emp_resig').css("border","1px solid red");
			return false;
		}
		
		else if(jsondata_1.LASTWORKINGDATE > jsondata_1.LASTPAYROLLDATE){    //jsondata_1.LASTPAYROLLDATE <= 
			alert("Please Update Last Working Date in HRMS...");
			$('#emp_relieving').focus();
			$('#emp_relieving').css("border","1px solid red");
			return false;
		}
		 */
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

function loadData(datas) {

			//alert(datas);
			var newdata = JSON.parse(datas);

			// alert("newdata-->"+newdata);

			var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
			$.each(newdata, function(index, value) {
				//alert(value);
				arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
			});

			// EXTRACT VALUE FOR TABLE HEADER.

			//alert(arrItems );
			var col = [];
			for ( var i = 0; i < arrItems.length; i++) {
				for ( var key in arrItems[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}

			// CREATE DYNAMIC TABLE.

			var table = document.createElement("table");
			table.setAttribute('id', 'table2excel');

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);
			//table.setAttribute('class', 'noExl');// TABLE ROW.

			for ( var i = 0; i < col.length; i++) {
				var th = document.createElement("th"); // TABLE HEADER.
				th.style.border = "1px solid #dddddd";
				/*  th.style.fontFamily=" verdana,arial,sans-serif";
				 th.style.fontsize="11px"; */
				th.innerHTML = col[i];
				tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for ( var i = 0; i < arrItems.length; i++) {

				tr = table.insertRow(-1);

				for ( var j = 0; j < col.length; j++) {
					var tabCell = tr.insertCell(-1);

					if (j == 0) {
						tabCell.innerHTML = arrItems[i][col[j]];
					}
					if (j == 1) {

						tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j+1]]+"_A' value='"+arrItems[i][col[j]]+"' readonly/>";
					}

					if (j == 2) {
						tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j]]+"_P'  value='0' readonly/>";

						//arrItems[i][col[j]];
					}

					//tabCell.style.border = "2px solid #a9c6c9";

				}

			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("dataTable");
			//document.getElementById("dataTable").style.border = "thick solid #0000FF";
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
		}
		



function loadData10(datas) {

	//alert(datas);
	var newdata = JSON.parse(datas);

	// alert("newdata-->"+newdata);

	var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
	$.each(newdata, function(index, value) {
		//alert(value);
		arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
	});

	// EXTRACT VALUE FOR TABLE HEADER.

	//alert(arrItems );
	var col = [];
	for ( var i = 0; i < arrItems.length; i++) {
		for ( var key in arrItems[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}

	// CREATE DYNAMIC TABLE.

	var table = document.createElement("table");
	table.setAttribute('id', 'table2excel');

	// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

	var tr = table.insertRow(-1);
	//table.setAttribute('class', 'noExl');// TABLE ROW.

	for ( var i = 0; i < col.length; i++) {
		var th = document.createElement("th"); // TABLE HEADER.
		th.style.border = "1px solid #dddddd";
		/*  th.style.fontFamily=" verdana,arial,sans-serif";
		 th.style.fontsize="11px"; */
		th.innerHTML = col[i];
		tr.appendChild(th);
	}

	// ADD JSON DATA TO THE TABLE AS ROWS.
	for ( var i = 0; i < arrItems.length; i++) {

		tr = table.insertRow(-1);

		for ( var j = 0; j < col.length; j++) {
			var tabCell = tr.insertCell(-1);

			if (j == 0) {
				tabCell.innerHTML = arrItems[i][col[j]];
			}
			if (j == 1) {

				tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j+1]]+"_A' value='"+arrItems[i][col[j]]+"' readonly/>";
			}

			if (j == 2) {
				tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j]]+"_P'  value='0' readonly/>";

				//arrItems[i][col[j]];
			}

			//tabCell.style.border = "2px solid #a9c6c9";

		}

	}

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById("dataTable_annual_benifits");
	//document.getElementById("dataTable").style.border = "thick solid #0000FF";
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
}


function loadData11(datas) {

	//alert(datas);
	var newdata = JSON.parse(datas);

	// alert("newdata-->"+newdata);

	var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
	$.each(newdata, function(index, value) {
		//alert(value);
		arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
	});

	// EXTRACT VALUE FOR TABLE HEADER.

	//alert(arrItems );
	var col = [];
	for ( var i = 0; i < arrItems.length; i++) {
		for ( var key in arrItems[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}

	// CREATE DYNAMIC TABLE.

	var table = document.createElement("table");
	table.setAttribute('id', 'table2excel');

	// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

	var tr = table.insertRow(-1);
	//table.setAttribute('class', 'noExl');// TABLE ROW.

	for ( var i = 0; i < col.length; i++) {
		var th = document.createElement("th"); // TABLE HEADER.
		th.style.border = "1px solid #dddddd";
		/*  th.style.fontFamily=" verdana,arial,sans-serif";
		 th.style.fontsize="11px"; */
		th.innerHTML = col[i];
		tr.appendChild(th);
	}

	// ADD JSON DATA TO THE TABLE AS ROWS.
	for ( var i = 0; i < arrItems.length; i++) {

		tr = table.insertRow(-1);

		for ( var j = 0; j < col.length; j++) {
			var tabCell = tr.insertCell(-1);

			if (j == 0) {
				tabCell.innerHTML = arrItems[i][col[j]];
			}
			if (j == 1) {

				tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j+1]]+"_A' value='"+arrItems[i][col[j]]+"' readonly/>";
			}

			if (j == 2) {
				//alert(arrItems[i][col[j]]+"_P");
		/* 		if(arrItems[i][col[j]]+"_P"=="30_P" || arrItems[i][col[j]]+"_P"=="31_P" || arrItems[i][col[j]]+"_P"=='32_P'){ */
				tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j]]+"_P'  value='0' readonly/>";
			/* 	}else{
				tabCell.innerHTML = "<input type='text' class='myclass' id='"+arrItems[i][col[j]]+"_P'  value='0' />";
				} */

				//arrItems[i][col[j]];
			}

			//tabCell.style.border = "2px solid #a9c6c9";

		}

	}

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById("dataTable_ctcdeductions");
	//document.getElementById("dataTable").style.border = "thick solid #0000FF";
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
}


		var EarningsComponents = [];
		function loadData2(datas) {

			var newdata = JSON.parse(datas);

			//	 alert("newdata-->"+newdata);

			var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
			$.each(newdata, function(index, value) {
				arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
			});

			// EXTRACT VALUE FOR TABLE HEADER.
			var col = [];
			for ( var i = 0; i < arrItems.length; i++) {
				for ( var key in arrItems[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}

			// CREATE DYNAMIC TABLE.
			var table = document.createElement("table");
			table.setAttribute('id', 'table2excel');

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);
			//table.setAttribute('class', 'noExl');// TABLE ROW.

			for ( var i = 0; i < col.length; i++) {
				var th = document.createElement("th"); // TABLE HEADER.
				th.style.border = "1px solid #dddddd";
				/*  th.style.fontFamily=" verdana,arial,sans-serif";
				 th.style.fontsize="11px"; */
				th.innerHTML = col[i];
				tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for ( var i = 0; i < arrItems.length; i++) {

				tr = table.insertRow(-1);

				for ( var j = 0; j < col.length; j++) {
					var tabCell = tr.insertCell(-1);

					if (j == 0) {
						tabCell.innerHTML = arrItems[i][col[j]];
					}
					if (j == 1) {
						tabCell.innerHTML = "<input type='text' class='myclass' id="+arrItems[i][col[j]]+" value='0' onkeyup='ClearTable()' />";
						EarningsComponents.push(arrItems[i][col[j]]);
					}
					//     tabCell.style.border = "2px solid #a9c6c9";

				}
			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("dataTable2");
			//document.getElementById("dataTable").style.border = "thick solid #0000FF";
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
		}

 		var DeductionComponents = [];

		function loadData3(datas) {

			var newdata = JSON.parse(datas);

			//	 alert("newdata-->"+newdata);

			var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
			$.each(newdata, function(index, value) {
				arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
			});

			// EXTRACT VALUE FOR TABLE HEADER.
			var col = [];
			for ( var i = 0; i < arrItems.length; i++) {
				for ( var key in arrItems[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}

			// CREATE DYNAMIC TABLE.
			var table = document.createElement("table");
			table.setAttribute('id', 'table2excel');

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);
			//table.setAttribute('class', 'noExl');// TABLE ROW.

			for ( var i = 0; i < col.length; i++) {
				var th = document.createElement("th"); // TABLE HEADER.
				th.style.border = "1px solid #dddddd";
			
				th.innerHTML = col[i];
				tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for ( var i = 0; i < arrItems.length; i++) {

				tr = table.insertRow(-1);

				for ( var j = 0; j < col.length; j++) {
					var tabCell = tr.insertCell(-1);
					if (j == 0) {
						tabCell.innerHTML = arrItems[i][col[j]];
					}
					if (j == 1) {
						tabCell.innerHTML = "<input type='text' class='myclass' id="+arrItems[i][col[j]]+" value='0' onkeyup='ClearTable()' />";
						DeductionComponents.push(arrItems[i][col[j]]);
					}

					//      tabCell.style.border = "2px solid #a9c6c9";

				}
			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("dataTable3");
			//document.getElementById("dataTable").style.border = "thick solid #0000FF";
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
		}

			 
		
		
		var Others_Components_Cal = [];

		function loadData4(datas) {

			var newdata = JSON.parse(datas);

			//	 alert("newdata-->"+newdata);

			var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
			$.each(newdata, function(index, value) {
				arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
			});

			// EXTRACT VALUE FOR TABLE HEADER.
			var col = [];
			for ( var i = 0; i < arrItems.length; i++) {
				for ( var key in arrItems[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}

			// CREATE DYNAMIC TABLE.
			var table = document.createElement("table");
			table.setAttribute('id', 'table2excel');

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);
			//table.setAttribute('class', 'noExl');// TABLE ROW.

			for ( var i = 0; i < col.length; i++) {
				var th = document.createElement("th"); // TABLE HEADER.
				th.style.border = "1px solid #dddddd";
				/*  th.style.fontFamily=" verdana,arial,sans-serif";
				 th.style.fontsize="11px"; */
				th.innerHTML = col[i];
				tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for ( var i = 0; i < arrItems.length; i++) {

				tr = table.insertRow(-1);

				for ( var j = 0; j < col.length; j++) {
					var tabCell = tr.insertCell(-1);
					if (j == 0) {
						tabCell.innerHTML = arrItems[i][col[j]];
					}
					if (j == 1) {

						//var idname=arrItems[i][col[j]].split("-");
						/*  var name=idname[0];
						 if(idname[1]=="E"){
						 OtherEar_annual.push(name);
						 }if(idname[1]=="D"){
						 OtherDed_annual.push(name);
						 } */

						 if(arrItems[i][col[j]]=="53-D"){
							 tabCell.innerHTML = "<input type='text' class='myclass' id="+arrItems[i][col[j]]+" value='0' onkeyup='ClearTable()'> <br> <textarea rows='2' cols='20' class='myclass' placeholder='reason'>";
						 }else{
							 tabCell.innerHTML = "<input type='text' class='myclass' id="+arrItems[i][col[j]]+" value='0' onkeyup='ClearTable()'>";
						 }
						 
						
						Others_Components_Cal.push(arrItems[i][col[j]]);
					}

					//    tabCell.style.border = "2px solid #a9c6c9";

				}
			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("dataTable4");
			//document.getElementById("dataTable").style.border = "thick solid #0000FF";
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
		}

		var payperiod_HoldData = [];

		function loadData5(datas) {

			var newdata = JSON.parse(datas);

			//		 alert("newdata-->"+newdata);

			var arrItems = []; // THE ARRAY TO STORE JSON ITEMS.
			$.each(newdata, function(index, value) {
				arrItems.push(value); // PUSH THE VALUES INSIDE THE ARRAY.
			});

			// EXTRACT VALUE FOR TABLE HEADER.
			var col = [];
			for ( var i = 0; i < arrItems.length; i++) {
				for ( var key in arrItems[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}

			// CREATE DYNAMIC TABLE.
			var table = document.createElement("table");
			table.setAttribute('id', 'table2excel');

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);
			//table.setAttribute('class', 'noExl');// TABLE ROW.

			for ( var i = 0; i < col.length - 2; i++) {
				var th = document.createElement("th"); // TABLE HEADER.
				th.style.border = "1px solid #dddddd";
				th.innerHTML = col[i];
				tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for ( var i = 0; i < arrItems.length; i++) {

				tr = table.insertRow(-1);

				for ( var j = 0; j < col.length - 1; j++) {
					var tabCell = tr.insertCell(-1);
					if (j == 0) {
						tabCell.innerHTML = "<input type='checkbox' id="+arrItems[i][col[j]]+" value="+arrItems[i][col[j+1]]+">"
								+ arrItems[i][col[j]];
					}

					//   tabCell.style.border = "2px solid #a9c6c9";

				}
			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("dataTable5");
			//document.getElementById("dataTable").style.border = "thick solid #0000FF";
			divContainer.innerHTML = "";
			divContainer.appendChild(table);

		}

		/* $("#resig_date").click(function() {

		//	$('#relieving_date').val('');
			$('#total_days').val('');
			$('#total_payable_days').val('');
			$('#lop').val('0');
			$('#payable_basedays').val('');
			$('#payperiod_months_count').val('');
			$('#CTC_Calculate').hide();

		}); */

		/* $(function() {

		    var myDate =  $("#resig_date").val();
		    //    $('#resig_date').datepicker();
		    
		    
		    $('#resig_date').datepicker({

		    	yearRange : "-90:+0",
				changeMonth : true,
				changeYear : true,
		        minDate: $("#resig_date").val(),
		    	dateFormat : 'dd.mm.yy',
		    	onSelect : function(Date) {
					$("#relieving_date").datepicker('option', 'minDate', myDate);
				}
		    	
		    });

		    $("#relieving_date").datepicker({
				
				yearRange : "-90:+0",
				changeMonth : true,
				changeYear : true,
				//minDate:0,
				dateFormat : 'dd.mm.yy'

			});

		});
		 */

		/*----------------------Dates Cal--------------*/

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
	//	 document.getElementById("ctc_y").checked = true;
		Calculation_Dates();
		//$('#ctc_y').attr('checked',true );
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
//	 alert('1');
	var yes=document.getElementById('ctc_y').checked;
	var no=document.getElementById('ctc_n').checked;

	if(yes==true){
		
		$('#FormSubmission').hide();
		
	//	$('#resig_date').val('');
		$('#resig_date').removeAttr('disabled');
	//	$('#relieving_date').val('');
		$('#relieving_date').removeAttr('disabled');
		
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
		 
		 for(var i=0;i<Others_Components_Cal.length;i++){
				
			 Others_ComponentID=document.getElementById(""+Others_Components_Cal[i]+"").value=0;
		 }
			$('#102-D').val(ImpressAmount);
		 document.getElementById('OTH_Others').value=0;

		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=false;
		
		 document.getElementById('50-E').value=EL_Amount_Cal;
		 FormDates(); 
		 
	}else{
		
		$('#FormSubmission').hide();
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val();
		$('#relieving_date').attr('disabled','disabled');
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
		 
		 for(var i=0;i<Others_Components_Cal.length;i++){
				
			 Others_ComponentID=document.getElementById(""+Others_Components_Cal[i]+"").value=0;
		 }
			$('#102-D').val(ImpressAmount);
		 document.getElementById('OTH_Others').value=0;

		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=false;
		 document.getElementById('50-E').value=EL_Amount_Cal;
	         
	}
 }
 
 // ********* caliculation part***********
 var CTCSum=0;
 var CTCSum_Dedu=0;
 function loadCTC(){
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
    	 Com_SumPaid=Com_Sum * emp_workdays;
    	 Com_paid_box.value=Math.floor(Com_SumPaid).toFixed(0);
    	 console.log(Com_SumPaid); 
    	 if(com_Obj!="22" ){
    	    CTCSum=CTCSum+Com_SumPaid;
    	    
    	 }  
     } 
     
     var Gross=document.getElementById("22_P");
         Gross=Gross.value;
     if(Gross!=CTCSum){
    	 document.getElementById("22_P").value=Math.floor(CTCSum);
     }
     
     
//     alert(Math.floor(CTCSum));
     
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
    document.getElementById('CTCEarning').value=Math.floor(CTCSum);
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
	 var Others_ComponentID=0;
	 var ComponentId_Others=document.getElementById('Others_Caliculation');
	 
	 for(var i=0;i<Others_Components_Cal.length;i++){
			
		 Others_ComponentID=document.getElementById(""+Others_Components_Cal[i]+"");
		 var idtype=Others_Components_Cal[i].split("-");
		 Others_ComponentID=Others_ComponentID.value;
		 if(idtype[1]=="E"){
		   Others_Cal_Ear=Others_Cal_Ear+eval(Others_ComponentID);
		 }
		 if(idtype[1]=="D"){
		   Others_Cal_Ded=Others_Cal_Ded+eval(Others_ComponentID);
		 }

		 Others_final=Others_Cal_Ear-Others_Cal_Ded;
		}
	
	 if(Others_final>0){
	 document.getElementById('OTH_Others_e_c').checked=true;
	 document.getElementById('OTH_Others_d_c').checked=false;
	 document.getElementById('OTH_Others').value=Others_final;
	 }else{
		 
		 document.getElementById('OTH_Others_e_c').checked=false;
		 document.getElementById('OTH_Others_d_c').checked=true;
		 document.getElementById('OTH_Others').value=eval(Others_final) * -1;
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
	  {
	    $('#myDiv1 :text').each(function() {
	      //if(values.indexOf($(this).val()) === -1){
	      values=values+parseFloat(($(this).val()));
	      // }
	    });
	    if(document.getElementById('ctc_ded_n').checked==true){
	    	
	    	values=values-document.getElementById('CTCDeduction').value;
	    }
	    document.getElementById('final_total').value=values;	
	  //  console.log( parseFloat(values));
	  }
	}
 
function myFunction() {
    location.reload();
}


function ClearTable(){
	
	$('#CTCEarning').val('');
	$('#CTCDeduction').val('');
	$('#OTH_Ear').val('');
	$('#OTH_Ded').val('');
	$('#OTH_Others').val('');
	document.getElementById('OTH_Others_e_c').checked=false;
	document.getElementById('OTH_Others_d_c').checked=false;
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
		$('#relieving_date').val();
		$('#relieving_date').attr('disabled','disabled');
		document.getElementById('ctc_n').checked=false;
		document.getElementById('ctc_y').checked=true;
		$('#ctc_y').attr('disabled','disabled');
		$('#ctc_n').attr('disabled','disabled');
		
		return false;
	}else if(date > date2){
		
		$('#Errormsg').html("Please Update Date of Resignation / Last Working Date in HRMS. Date of Resignation should not be Greater than Last Working Date");
		$('#emp_resig').focus();
		$('#emp_resig').css("border","1px solid red");
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val();
		$('#relieving_date').attr('disabled','disabled');
		return false;
	}
	
	else if(date2 <= date3){    //jsondata_1.LASTPAYROLLDATE <= 
		/* $('#Errormsg').html("Please Update Last Working Date in HRMS...");
		$('#emp_relieving').focus();
		$('#emp_relieving').css("border","1px solid red"); */
		$('#resig_date').val();
		$('#resig_date').attr('disabled','disabled');
		$('#relieving_date').val();
		$('#relieving_date').attr('disabled','disabled');
		document.getElementById('ctc_n').checked=true;
		document.getElementById('ctc_y').checked=false;
		$('#ctc_y').attr('disabled','disabled');
		$('#CTC_Calculate').show();
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
	
	alert("FormSubmit");
	
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
	
	xhr.send();	
	
}

	</script>
 

</body>
</html>