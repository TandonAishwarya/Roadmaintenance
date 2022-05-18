<?php   

require_once 'core.php';

$valid['success'] = array('success' => false, 'messages' => array()); 
    if($_POST) 
       {
        $id                             = $_POST["id"];
        $title                          = $_POST["title"];
        $description                    = $_POST["description"];
        $type                           = $_POST["type"];
        $area                           = $_POST["area"];
        $taluka                         = $_POST["taluka"];
        $district                       = $_POST["district"];
        $state                          = $_POST["state"];
        $sq_km                          = $_POST["sq-km"];
        $amount                         = $_POST["amount"];
        $tender_date                    = date('Y-m-d', strtotime($_POST["tender-date"]));    
      
        $sql="insert into tender(title,description,type,area,taluka,district,state,sq_km,amount,tender_date) values ('$title','$description','$type','$area','$taluka','$district','$state','$sq_km','$amount','$tender_date');";
                
                        if($connect->query($sql)){
                                        $valid['success'] = true;
                                        $valid['messages'] ="Added Successfully";
                                }
                                else{
                                        $valid['success'] = false;
                                        $valid['messages'] ="Not Added";
                                }
      
    echo json_encode($valid);
    $connect->close();
    } // if $_POST
?>