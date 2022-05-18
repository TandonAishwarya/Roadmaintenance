<?php
require "init.php";

if($_SERVER['REQUEST_METHOD']=='POST'){

$mb =$_REQUEST['username'];
$password =$_REQUEST['password'];


$sql = "SELECT * FROM contractor WHERE mobileno='$mb' and password='$password';";
		$result=mysqli_query($conn,$sql);
		
		if(mysqli_num_rows($result)){
		while($row=mysqli_fetch_assoc($result))
			{
				$json['record'][]=$row;
			}	
		}
	}	
mysqli_close($conn);

echo json_encode($json);

?>