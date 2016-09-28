<?php
 //change for local use
 define('HOST','localhost'); 
 define('User','root');
 define('PASS','op3n');
 define('DB','farmdelivery');

 //Connecting to Database
 $con = mysqli_connect(HOST,User,PASS,DB) or die('Unable to Connect');
