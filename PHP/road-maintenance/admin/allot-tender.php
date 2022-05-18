
<?php
require "php_action/core.php";
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin </title>
        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" >
        <link rel="stylesheet" href="css/font-awesome.min.css" media="screen" >
        <link rel="stylesheet" href="css/animate-css/animate.min.css" media="screen" >
        <link rel="stylesheet" href="css/lobipanel/lobipanel.min.css" media="screen" >
        <link rel="stylesheet" type="text/css" href="js/DataTables/datatables.min.css"/>
        <link rel="stylesheet" href="css/main.css" media="screen" >
        <link rel="stylesheet" href="css/prism/prism.css" media="screen" >
      
        <script src="js/modernizr/modernizr.min.js"></script>
          <style>
        .errorWrap {
    padding: 10px;
    margin: 0 0 20px 0;
    background: #fff;
    border-left: 4px solid #dd3d36;
    -webkit-box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
}
.succWrap{
    padding: 10px;
    margin: 0 0 20px 0;
    background: #fff;
    border-left: 4px solid #5cb85c;
    -webkit-box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
}
        </style>
    </head>
    <body class="top-navbar-fixed">
        <div class="main-wrapper">

            <!-- ========== TOP NAVBAR ========== -->
   <?php include('includes/topbar.php');?> 
            <!-- ========== WRAPPER FOR BOTH SIDEBARS & MAIN CONTENT ========== -->
            <div class="content-wrapper">
                <div class="content-container">
<?php include('includes/leftbar.php');?>  

                    <div class="main-page">
                        <div class="container-fluid">
                            <div class="row page-title-div">
                                <div class="col-md-6">
                                    <h2 class="title"> Allotment </h2>
                                
                                </div>
                                
                                <!-- /.col-md-6 text-right -->
                            </div>
                            <!-- /.row -->
                            <div class="row breadcrumb-div">
                                <div class="col-md-6">
                                    <ul class="breadcrumb">
                                        <li><a href="dashboard.php"><i class="fa fa-home"></i> Home</a></li>
                                        <li class="active">Upload Allotment Certificate</li>
                                    </ul>
                                </div>
                             
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.container-fluid -->

                         <section class="section">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5>View Details</h5>
                                                </div>
                                            </div>
                                            <div class="bs-example">
                                        <div id="success-messages"></div>   
                                            <form class="form-horizontal" id="BannerForm" action="" enctype="multipart/form-data"  method="post">         
                        <div class="page-header">
                            <center><h3>Upload Certificate</h3></center>
                        </div>  
                    
                    
                    <div class="row">    
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label" >Choose Contractor:</label>
                            <div class="col-md-9 col-sm-9">
                               <select name="contractor_id" id="contractor_id">
                                    <option>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;</option>
                                        <?php $sql = 'SELECT a.id,a.name FROM contractor as a inner join tender_request as b on a.id=b.contractor_id;';
                                        $retval = $connect->query($sql);
                                        while($row=mysqli_fetch_array($retval)) { ?>
                                            <option value="<?php echo $row['id'];?>"><?php echo $row['name'];?></option>
                                        <?php   }  ?>
                                </select> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label" style="text-align: right"> Certificate:</label>
                            <div class="col-sm-6">
                                <input type="file" class="form-control" name="bannerimg"  required>
                            </div>
                        </div>
                    </div>
                    
                        <div class="form-group submitButtonFooter">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="bannerBtn" name="submit" data-loading-text="Loading..." class="btn btn-success"><i class="fa fa-check"></i> Save Changes</button>
                                <button type="reset" class="btn btn-default" onclick="resetOrderForm()"><i class="fa fa-remove"></i> Reset</button>
                            </div>
                        </div>
                    </form>
                </div>  
                                        </div>
                                    </div><!-- /.col-md-12 -->
                                </div><!-- row-->                   
                            </div><!-- /.container fluid -->
                        </section>
                        <!-- /.section -->  

                    </div>
                    <!-- /.main-page -->

                    

                </div>
                <!-- /.content-container -->
            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- /.main-wrapper -->

        <!-- ========== COMMON JS FILES ========== -->
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/pace/pace.min.js"></script>
        <script src="js/lobipanel/lobipanel.min.js"></script>
        <script src="js/iscroll/iscroll.js"></script>

        <!-- ========== PAGE JS FILES ========== -->
        <script src="js/prism/prism.js"></script>
        <script src="js/DataTables/datatables.min.js"></script>

        <!-- ========== THEME JS ========== -->
        <script src="js/main.js"></script>
    
    </body>
</html>

<?php
    if(isset($_POST['submit'])){
        $contractor_id=$_POST["contractor_id"];
        $date = date("Ymdhmsu");
        
        $file = $_FILES['bannerimg']['name'];
        $file_loc = $_FILES['bannerimg']['tmp_name'];
        $folder="uploads/";
  
        $new_file = date("Ymdhms").".png";
        if(move_uploaded_file($file_loc,$folder.$new_file))
            {
                $bannerimg='admin/'.$folder.$new_file;
                $tender_id=$_GET['tender_id'];
                $sql="update tender set allotment_letter='$bannerimg',is_alloted=1,alloted_to='$contractor_id' where id='$tender_id';";
                $query = $connect->query($sql);
                echo "<script>alert(' Added')</script>";
            }
        else 
        {
            echo "<script>alert('image not added')</script>";
        }   
    }

?>