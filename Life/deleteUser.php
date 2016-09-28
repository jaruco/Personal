<?php

	$email = $_GET['email'];
	require_once('dbConnect.php');

	$sql = "DELETE FROM user WHERE email = '$email';";

	if(mysqli_query($con,$sql)){
		echo 'User Deleted Successfully';
	}else{
		echo 'Could Not Delete User, Try Again';
	}

	mysqli_close($con);
