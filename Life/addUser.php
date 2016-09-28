<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$email     = $_POST['email'];
		$password  = $_POST['password'];
		$name      = $_POST['name'];
		$lastname  = $_POST['lastname'];
		$position  = $_POST['position'];

		$sql = "INSERT INTO user (email,password,name,lastname,position) VALUES ('$email','$password','$name','$lastname','$position');";

		require_once('dbConnect.php');

		if(mysqli_query($con,$sql)){
			echo 'User Added Successfully';
		}else{
			echo 'Could Not Add User';
		}

		mysqli_close($con);
	}
