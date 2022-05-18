<?php
$servername = "localhost";
$username = "root";
$pass = "";
$database="road_maintenance";

 
// Create connection
$conn = mysqli_connect($servername, $username, $pass,$database);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
else{
	
//	echo "Connection Successful";
}



?>