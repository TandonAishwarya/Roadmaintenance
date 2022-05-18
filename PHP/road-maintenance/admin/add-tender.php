<?php require "includes/header.php"; date_default_timezone_set("Asia/Kolkata");?>

<style type="text/css">
    input[type="text"],textarea{
        text-transform: uppercase;
    }
</style>
<div class="main-page" style="background-color: white">
    <div class="container-fluid">
        <div class="row page-title-div">
            <div class="col-md-6">
                <h2 class="title">Add Tender</h2>
            </div>

            <!-- /.col-md-6 text-right -->
        </div>
        <!-- /.row -->
        <div class="row breadcrumb-div">
            <div class="col-md-6">
                <ul class="breadcrumb">
                    <li><a href="dashboard.php"><i class="fa fa-home"></i> Home</a></li>
                    <li> Tender</li>
                    <li class="active">Create New Tender</li>
                </ul>
            </div>

        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->

    <section class="section">
        <div class="container-fluid" >
            <fieldset>
                <form class="form-horizontal" id="addCollectionForm" action="php_action/add-tender.php"  method="post" autocomplete="off" enctype="multipart/form-data">  


                    <div class="row">
                        <div id="success-messages"></div> <!--/success-messages-->
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label" >Tender Information:</label>
                            <div class="col-md-9 col-sm-9">
                                 <?php $sql="select max(id) as id from tender"; 
                            $result=$connect->query($sql);
                            $data=$result->fetch_assoc();
                            $id=$data['id']+1;
                            ?>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-sm-12">
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label" >Id:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="id" class="form-control" id="id" value="<?php echo $id; ?>">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label" > Title:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="title" required="true" class="form-control" id="title">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Description:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="description" required="true" class="form-control" id="description">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Type Of Construction:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="type" required="true" class="form-control" id="type">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Area:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="area" required="true" class="form-control" id="area">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Taluka:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="taluka" required="true" class="form-control" id="taluka">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">District:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="district" required="true" class="form-control" id="district">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">State:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="state" required="true" class="form-control" id="state">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">in sq Km:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="sq-km" required="true" class="form-control" id="sq-km">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Estimate Amount:</label>
                                <div class="col-sm-9">
                                    <input type="text" required="true" name="amount" class="form-control" id="amount">
                                </div>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="form-group">
                                <label for="" class="col-sm-3 control-label">Last Date of Tender:</label>
                                <div class="col-sm-9">
                                    <input type="text" name="tender-date" required="true" class="form-control" id="tender-date">
                                </div>
                            </div>
                        </div>

                    </div>      
                    

                    <hr style="color: red;border: 1px solid">
                    <div class="row">
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-6">
                            <button type="submit" id="createOrderBtn" data-loading-text="Loading..." class="btn btn-success"><i class="fa fa-check"></i> Save Changes</button>
                             <button type="reset" class="btn btn-danger"><i class="fa fa-remove"></i> Reset</button>  
                        </div>  
                    </div>
             <hr style="color: red;border: 1px solid">   
        </form>
 </fieldset>                                    
</div>
</section>
<!-- /.section -->
</div>
<!-- /.main-page -->
<?php require "includes/footer.php"; ?>
<script>
   $(function() {
    $('#tender-date').datepicker({dateFormat: 'd-m-yy'});
    });
 
 

            // create order form function
            $("#addCollectionForm").unbind('submit').bind('submit', function() {
                $('.form-group').removeClass('has-error').removeClass('has-success');
                $('.text-danger').remove();
                var id                = $("#id").val();
             
 
                if(id == "-") {
                    $("#id").after('<p class="text-danger"> ID is required </p>');
                    $('#id').closest('.form-group').addClass('has-error');
                } else {
                    $('#id').closest('.form-group').addClass('has-success');
            } // /else

          
            if(loan_id) {
                var form = $(this);
                var formData = new FormData(this);

                $("#createOrderBtn").button('loading');
                $.ajax({
                    url : form.attr('action'),
                    type: form.attr('method'),
                    data: formData,                 
                    dataType: 'json',
                    cache: false,
                    contentType: false,
                    processData: false,
                    success:function(response) {
                        $("#createOrderBtn").button('reset');

                        if(response.success == true) {
                                // create order button
                                $("html, body, div.modal, div.modal-content, div.modal-body").animate({scrollTop: '0'}, 100);  
                                swal({
                                    title: "SUCCESS!",
                                    text: response.messages,
                                    icon: "success",
                                    button: "Done",
                                },function(){
                                    window.location.href = 'add-bg-collection.php';
                                }
                                );                              
                            } else {
                                        alert(response.messages);                               
                                    }
                                } // /response
                            }); // /ajax
                
            } // /if field validate is true    
            return false;
        });   
    </script>
</body>
</html>

