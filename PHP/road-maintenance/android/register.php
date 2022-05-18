<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$name=$_POST['name'];
	$emailid=$_POST['emailid'];
	$mobile=$_POST['mobile'];
	$password=$_POST['password'];
	
	require_once('init.php');
		
		$sql = "INSERT INTO contractor (name,emailid,mobileno,password) VALUES ('$name','$emailid','$mobile','$password');";
		
		if(mysqli_query($conn,$sql)){
			echo "Successfully Registered";
		}
		else
		{
			echo "Not Register";

		}
	}else{
	echo 'error';
	}
?>