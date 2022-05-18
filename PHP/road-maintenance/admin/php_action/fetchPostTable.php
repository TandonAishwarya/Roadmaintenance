<?php 	

require_once 'core.php';

$output = array('data' => array());

$sql = "SELECT * from posted_query order by id desc";
$result = $connect->query($sql);

if($result->num_rows > 0) { 
 while($row = $result->fetch_array()) {
 	$id						= $row[1];	
 	$name 					= $row[2];
	$complaint				= $row[3];
 	$mobile_no				= $row[4];
 	$status					= $row[12];
 	$img					= $row[5];
 	$lat					= $row[8];
 	$long					= $row[9];

 	$image='<a href="'.$serverimgpath.$img.'" target="_blank"><img src="'.$serverimgpath.$img.'" height="100" width="100"></a>';
	$address='<a href="https://www.google.com/maps/search/?api=1&query='.$lat.','.$long.'" target="_blank">Show</a>';
	$output['data'][] = array(
 		$id, 	
 		$image,	
 		$name,
 		$complaint,
 		$mobile_no,
 		$address,
 		$status,

 		); 
 } // /while 

}// if num_rows

$connect->close();
echo json_encode($output);
?>