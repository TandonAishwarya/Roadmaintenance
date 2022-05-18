<?php 
require_once 'php_action/db_connect.php';

session_start();

if(isset($_SESSION['userId'])) {
    header('location:'.$serverpath.'dashboard.php'); 
}

$errors = array();

if($_POST) {        

    $username = $_POST['username'];
    $role = $_POST['role'];
    $password = md5($_POST['password']);
    $sql=""; $mainSql="";
    if(empty($username) || empty($password) || empty($role)) {
        if($username == "") {
            $errors[] = "Username is required";
        } 

        if($password == "") {
            $errors[] = "Password is required";
        }
        if($role == "") {
            $errors[] = "Please select role";
        }
    } else {
        if($role==1)
            $sql = "SELECT * FROM admin WHERE username = '$username'";
        else
            $sql = "SELECT * FROM user WHERE username = '$username'";
            
        $result = $connect->query($sql);

        if($result->num_rows == 1) {
            // exists
            if($role==1)    
                $mainSql = "SELECT * FROM admin WHERE username = '$username' AND password = '$password'";
            else
                $mainSql = "SELECT * FROM user WHERE username = '$username' AND password = '$password'";
                
            $mainResult = $connect->query($mainSql);

            if($mainResult->num_rows>0) {
                $value = $mainResult->fetch_assoc();
                $user_id = $value['id'];

                // set session
                $_SESSION['userId'] = $user_id;
                $_SESSION['alogin']=$username;
                if($role==1)    
                    $_SESSION['role'] = "admin";
                else
                    $_SESSION['role'] = "user";
                    
                header('location:'.$serverpath.'dashboard.php');    
            } else{
                
                $errors[] = "Incorrect username/password combination";
            } // /else
        } else {        
            $errors[] = "Username doesnot exists";      
        } // /else
    } // /else not empty username // password
    
} // /if $_POST
?>
<!DOCTYPE HTML>
<html>
<head>
    <title>Project Demo</title>
<!-- Favicon-->
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.PNG" /><!-- <script src="js/jquery-2.1.1.min.js"></script> -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <!-- Custom Theme files -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!--js-->
    <script src="js/jquery-2.1.1.min.js"></script> 
    <!--icons-css-->
    <link href="css/font-awesome.css" rel="stylesheet"> 
    <!--Google Fonts-->
    <link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
    <!--static chart-->
</head>


<body>
    <div class="container">
        <div class="col-md-3">
        </div>
       
        <div class="login-page col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign in</h3>
                </div>
                <div class="panel-body">

                    <div class="messages">
                        <?php if($errors) {
                            foreach ($errors as $key => $value) {
                                echo '<div class="alert alert-warning" role="alert">
                                <i class="fa fa-close"></i>
                                '.$value.'</div>';                                      
                            }
                        } ?>
                    </div>

                    <form class="form-horizontal" action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post" id="loginForm">
                        <fieldset>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username" autocomplete="off" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" autocomplete="off" />
                                </div>
                            </div>                              
                            <div class="form-group">
                                <label for="role" class="col-sm-2 control-label">Role</label>
                                <div class="col-sm-10">
                                    <input type="radio" class="form-inline" id="admin" name="role" value="1" checked="true" />Admin
                                   
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default"> <i class="glyphicon glyphicon-log-in"></i> Sign in</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <!-- panel-body -->
            </div>
            <!-- /panel -->
            <!-- /col-md-4 -->
        </div>
   
    </div>
    <!-- container -->  
</body>
<!--inner block end here-->
<!--copy rights start here-->
<!--scrolling js-->
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!--//scrolling js-->
<script src="js/bootstrap.js"> </script>
<!-- mother grid end here-->
</body>
</html>




