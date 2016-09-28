<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		$email = $_POST['email'];
		$name = $_POST['name'];
		$lastname = $_POST['lastname'];
		$position = $_POST['position'];

		require_once('dbConnect.php');

		$sql = "UPDATE user SET name = '$name', lastname = '$lastname', position = '$position' WHERE email = '$email';";

		if(mysqli_query($con,$sql)){
			echo 'User Updated Successfully';
		}else{
			echo 'Could Not Update User, Try Again';
		}

		mysqli_close($con);
	}
