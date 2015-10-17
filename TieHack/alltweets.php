<?php
include 'db-config.php';
$response = array();
$result = mysqli_query($connection,"SELECT `tweets`,`text`,`tid` from tweets");
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["products"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["tweets"] = $row["tweets"];
        $product["text"] = $row["text"];
        $product["tid"] = $row["tid"];
        // push single product into final response array
        array_push($response["products"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>