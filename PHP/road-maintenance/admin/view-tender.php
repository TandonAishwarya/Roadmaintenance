
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
                                    <h2 class="title">View Tenders</h2>
                                
                                </div>
                                
                                <!-- /.col-md-6 text-right -->
                            </div>
                            <!-- /.row -->
                            <div class="row breadcrumb-div">
                                <div class="col-md-6">
                                    <ul class="breadcrumb">
                                        <li><a href="dashboard.php"><i class="fa fa-home"></i> Home</a></li>
                                        <li class="active">Tender</li>
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
                                            <div class="panel-body p-20">
                                                    <table id="userTable"  class="display table table-striped table-bordered" cellspacing="0" width="100%">
                                                        <thead>
                                                            <tr>
                                                                <th>#</th>
                                                                <th>Title</th>
                                                                <th>Description</th>
                                                                <th>Type</th>
                                                                <th>Area</th>
                                                                <th>Taluka</th>
                                                                <th>District</th>
                                                                <th>State</th>
                                                                <th>Sq_km</th>
                                                                <th>Amount</th>
                                                                <th>Tender_date</th>
                                                                <th>Is Alloted</th>
                                                                <th>Alloted To</th>
                                                                <th>Allotment</th>
                                                            </tr>
                                                        </thead>
                                                    <tbody>
                                                        <?php 
                                                        $sql = "SELECT a.*,b.name from tender as a inner join contractor as b on a.alloted_to=b.id order by id desc";
                                                        $result = $connect->query($sql);

                                                        if($result->num_rows > 0) { 
                                                            $x=1;
                                                            while($row = $result->fetch_array()) {
                                                        ?>    
                                                            <tr>
                                                                <td><?php echo $x; ?></td>
                                                                
                                                                <td><?php echo $row[1]; ?></td>
                                                                <td><?php echo $row[2]; ?></td>
                                                                <td><?php echo $row[3]; ?></td>
                                                                <td><?php echo $row[4]; ?></td>
                                                                <td><?php echo $row[5]; ?></td>
                                                                <td><?php echo $row[6]; ?></td>
                                                                <td><?php echo $row[7]; ?></td>
                                                                <td><?php echo $row[8]; ?></td>
                                                                <td><?php echo $row[9]; ?></td>
                                                                <td><?php echo $row[10]; ?></td>
                                                                <?php if($row[11]==0){?>
                                                                <td><span class="label label-danger">Not Alloted</span></td>
                                                                <?php }else{?>
                                                                <td><span class="label label-success">Alloted</span></td>
                                                                <?php }?>
                                                                <?php if($row[12]==0){?>
                                                                <td></td>
                                                                <?php }else{ ?>
                                                                  <td><?php echo $row['name']; ?></td>
                                                                <?php } ?>
                                                                 <td><a class="btn btn-primary" href="allot-tender.php?tender_id=<?php echo $row[0];?>">Allot Tender</td>
                                                            </tr>
                                                        <?php
                                                            $x++;
                                                            } // /while 
                                                        }// if num_rows

                                                        ?>    
                                                        </tbody>    
                                                    </table>
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

