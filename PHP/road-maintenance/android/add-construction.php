<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$tender_id=$_POST['tender_id'];
	$worker=$_POST['worker'];
	$estimate=$_POST['estimate'];
	$supervisor=$_POST['supervisor'];
	$raw_material=$_POST['raw_material'];
	
	require_once('init.php');
		
		$sql = "INSERT INTO construction_data (tender_id,workers,estimate_no_of_days,supervisor,raw_material) VALUES ('$tender_id','$worker','$estimate','$supervisor','$raw_material');";
		
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