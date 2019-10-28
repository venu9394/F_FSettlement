<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 <!--  <link rel="stylesheet" href="/resources/demos/style.css"> -->
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  
	  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
</head>
<body>

<table align="center">
<tr>
<td>EmployeeCodeNumber</td>
<td><input type="text" id="empid" name="empid" onkeyup="fetchdata()" maxlength="6" autocomplete="off"></td>
</tr>

<tr>
<td>Name of the Employee</td>
<td><input type="text" id="empname" name="empname" readonly></td>
</tr>

<tr>
<td>Designation</td>
<td><input type="text" id="designation" name="designation" readonly></td>
</tr>

<tr>
<td>Department</td>
<td><input type="text" id="department" name="department" readonly></td>
</tr>

<tr>
<td>Date of Joining</td>
<td><input type="text" id="doj" name="doj" readonly></td>
</tr>

<tr>
<td>Date of Resignation/Termination</td>
<td><input type="text" id="resig_date" name="resig_date" readonly></td>
</tr>

<tr>
<td>Date of Relieving</td>
<td><input type="text" id="relieving_date" name="relieving_date" readonly></td>
<td>Days</td>
<td><input type="text" id="days" name="days"></td>
</tr>

<tr>
<td>Gross Monthly Salary</td>
<td><input type="text" id="gross_month" name="gross_month" readonly></td>
</tr>

<tr>
<td>Total days Payable (Salary)</td>
<td><input type="text" id="total_days" name="total_days" readonly></td>
</tr>

<tr>
<td>Total Leaves Payable / Recoverable (+/-)</td>
<td><input type="number" id="total_leaves" name="total_leaves" ></td>
</tr>

<!-- 
<tr>
<td>EmployeeCodeNumber</td>
<td><input type="text" id="" name="" readonly></td>
</tr> -->





</table>
<div>
 <div id="showData"></div>
 <div id="OtherEarnings"></div>
 <div id="OtherDeductions"></div>
 </div>
 
 <script>
 
 
 function fetchdata(){
	 
	 var empid=$('#empid').val();
	 
	// alert(empid.length);
	 if(empid==''){
		 
		 alert('Please Enter EmployeeID');
		 return false;
	 }
	 
	 if(empid.length>=5){
		 
		//alert('success'); 
		 
var url = "AjaxData?type=basicdata&employeeid="+empid;
		
		
		var xhttp = new XMLHttpRequest();
		
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		      
		   // alert(this.responseText);	
		    
		    var data=this.responseText;
		    console.log(data.split(","));
		    
		  //  alert(data.split(",")[0].length+"<1>");		    
		    if(data.split(",")[0].length>0){
		    	
		    $('#empname').val(data.split(",")[0]);
		    $('#doj').val(data.split(",")[1]);
		    $('#department').val(data.split(",")[2]);		    
		    $('#designation').val(data.split(",")[3]);
		    $('#gross_month').val(data.split(",")[4]);
		    
		    
		    }else{
		    	
		    	alert('Please Enter Valid EmployeeID');
		    	 $('#empname').val('');
		    	 $('#designation').val('');
		    	 $('#department').val('');
		    	 $('#doj').val('');
		    	 $('#resig_date').val('');
		    	 $('#relieving_date').val('');	    	 
		    	 $('#gross_month').val('');
		    	 $('#empname').val('');
		    	 
		    }
		    
		    
		    }
		    
		    //return LtaAmount;
		  };
		  xhttp.open("GET", url, true);
		  xhttp.send();
		
		 
		 
	 }else{
		 
		 $('#empname').val('');
    	 $('#designation').val('');
    	 $('#department').val('');
    	 $('#doj').val('');
    	 $('#resig_date').val('');
    	 $('#relieving_date').val('');
    	 $('#gross_month').val('');
    	 
	 }
	 
	 
 }
 
  
 $("#resig_date").datepicker({
	 
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

	});
 
 
 $('#relieving_date').on('keyup change', function(e) {
	
		var fromdate = document.getElementById('resig_date').value.split('.').reverse().join('-');
		var todate = document.getElementById('relieving_date').value.split('.').reverse().join('-');
		var from = new Date(fromdate);
		var to = new Date(todate);
		var diffDays = parseInt((to - from) / (1000 * 60 * 60 * 24));
		var Days = diffDays + 1;
		
		
		if($('#resig_date').val()=="")
			{
			alert('Please Select From Date...');
			
			$('#relieving_date').val('');			
			$('#days').val('');		 
			}
		document.getElementById('days').value=Days;
		
		});

		/*function daysdiff() {
			
		 
			

			
		}*/
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
		
 
 
 
 
 
 
 </script>
 

</body>
</html>