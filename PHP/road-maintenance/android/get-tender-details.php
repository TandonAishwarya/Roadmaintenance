<?php 
require "init.php";

if($_SERVER['REQUEST_METHOD']=='POST')
{
$sql="select * from tender where is_alloted=0;";

$result = mysqli_query($conn,$sql);
$json = array();	
 
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