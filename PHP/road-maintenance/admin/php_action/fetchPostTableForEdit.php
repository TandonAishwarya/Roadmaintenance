<?php 	

require_once 'core.php';

$output = array('data' => array());
if($_SESSION['alogin'] == 'admin'){
 	$sql = "SELECT * from posted_query order by id desc";
}
$result = $connect->query($sql);

if($result->num_rows > 0) { 
 while($row = $result->fetch_array()) {
 	$id						= $row[0];	
 	$name 					= $row[2];
	$complaint				= $row[3];
 	$mobile_no				= $row[4];
 	$img					= $row[5];
 	$lat					= $row[8];
 	$long					= $row[9];
 	$statusvar				= $row[12];
	$image='<a href="'.$serverimgpath.$img.'" target="_blank"><img src="'.$serverimgpath.$img.'" height="100" width="100"></a>';
	$address='<a href="https://www.google.com/maps/search/?api=1&query='.$lat.','.$long.'" target="_blank">Show</a>';
	
	$status 			= 	'<!-- Single button -->
									<div class="btn-group">
								 	<a style="color:green" type="button" href="php_action/allow-to-edit.php?id='.$id.'&val=Accepted" onclick="return confirm(\'Are you sure?\')"> <i class="fa fa-edit"></i>Accepted</a>
								 	<a style="color:red" type="button" href="php_action/allow-to-edit.php?id='.$id.'&val=Rejected" onclick="return confirm(\'Are you sure?\')"> <i class="fa fa-edit"></i>Reject</a>
									
									</div>';
 			


   
 		$output['data'][] = array(
 		$id, 	
 		$image,	
 		$name,
 		$complaint,
 		$mobile_no,
 		$address,
 		$statusvar,
 		$status
 		); 
 	
 } // /while 

}// if num_rows

$connect->close();
echo json_encode($output);
?>