<?php
include 'db-config.php';
$response = array();
if (isset($_POST['item']) && isset($_POST['desc'])&& isset($_POST['tid'])) {
    
    $item = $_POST['item'];
    $desc = $_POST['desc'];
     $tid = $_POST['tid'];
    
  				 //$username = $_POST['username'];
   

    // mysql inserting a new row
    $result = mysqli_query($connection,"INSERT INTO reviews( `item`,`desc`,`tid`) VALUES( '$item','$desc','$tid')");

    // check if row inserted or not
    if ($result) {
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