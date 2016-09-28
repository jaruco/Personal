<?php
	require_once('dbConnect.php');
	$sql = "SELECT email, name, lastname, position  FROM user";
	$r = mysqli_query($con,$sql);
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"email"=>$row['email'],
			"name"=>$row['name'],
			"lastname"=>$row['lastname'],
			"position"=>$row['position']
		));
	}
	echo json_encode(array('result'=>$result));
	mysqli_close($con);
