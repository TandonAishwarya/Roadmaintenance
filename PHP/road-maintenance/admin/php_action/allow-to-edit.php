<?php
require_once 'core.php';

if($_GET['id']){

	$id=$_GET['id'];
	$val=$_GET['val'];
	if($val=="Accepted")
		$sql="update posted_query set status='Accepted' where id=$id";
	else if($val=="Rejected")
		$sql="update posted_query set status='Rejected' where id=$id";

	 if($connect->query($sql)){
	 		header('location:../pending.php?status=changed');
	}else{
	 		header('location:../pending.php?status=notchanged');
		}
}

?>