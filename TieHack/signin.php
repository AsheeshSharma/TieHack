
<?php
include 'db-config.php';
$response = array();
if (isset($_POST['tid']) && isset($_POST['pwd'])) {
    
    $tid = $_POST['tid'];
    $pword = $_POST['pwd'];
    //$username = $_POST['username'];
   

    // mysql inserting a new row
    $result = mysqli_query($connection,"SELECT pwd FROM register WHERE `tid`='$tid' AND `pwd`='$pword' ");

    // check if row inserted or not
    if (mysqli_num_rows($result) > 0) {
        // successfully inserted into database
	$response["success"] = 1;
        $response["message"] = "User successfully Registered.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>