<?php 	

$localhost = "localhost";
$username = "root";
$password = "";
$dbname = "road_maintenance";

// db connection
$connect = new mysqli($localhost, $username, $password, $dbname);
// check connection
$serverpath="http://localhost/road-maintenance/admin/";
$serverimgpath="http://localhost/road-maintenance/android/";

if($connect->connect_error) {
  die("Connection Failed : " . $connect->connect_error);
} else {
  // echo "Successfully connected";
}

?>