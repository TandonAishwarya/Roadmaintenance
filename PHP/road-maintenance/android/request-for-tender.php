<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$tender_id=$_POST['tenderid'];
	$contractor_id=$_POST['contractorid'];
	$amount=$_POST['amount'];
	
	
	require_once('init.php');
		
		$sql = "INSERT INTO tender_request (tender_id,contractor_id,amount) VALUES ('$tender_id','$contractor_id','$amount');";
		
		if(mysqli_query($conn,$sql)){
			echo "1";
		}
		else
		{
			echo "0";
		}
	}else{
	echo 'error';
	}
?>