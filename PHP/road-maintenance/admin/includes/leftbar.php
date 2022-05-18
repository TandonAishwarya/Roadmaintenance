<div class="left-sidebar bg-black-300 box-shadow leftbar" >
                        <div class="sidebar-content">
                            <div class="sidebar-nav">
                                <ul class="side-nav color-gray">
                                    <li class="nav-header">
                                        <span class="">Main Category</span>
                                    </li>
                                    <li>
                                        <a href="dashboard.php"><i class="fa fa-dashboard"></i> <span>Dashboard</span> </a>
                                     
                                    </li>

                                    <li class="nav-header">
                                        <span class="">Appearance</span>
                                    </li>
                                    <li class="has-children">
                                        <a href="#"><i class="fa fa-user"></i> <span>Tender</span> <i class="fa fa-angle-right arrow"></i></a>
                                        <ul class="child-nav">
                                            <li><a href="add-tender.php"><i class="fa fa-plus"></i> <span>Add Tender</span></a></li>
                                            <li><a href="view-tender.php"><i class="fa fa-edit"></i> <span>view Tender</span></a></li>
                                        </ul>
                                    </li>
                                
                                    <li><a href="change-password.php"><i class="fa fa-server"></i> <span> <?php echo $_SESSION['alogin']; ?> Change Password</span></a></li>
                                           
                                    </li>

                            </div>
                            <!-- /.sidebar-nav -->
                        </div>
                        <!-- /.sidebar-content -->
                    </div>