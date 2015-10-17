<!DOCTYPE html>
<html>
<head> <meta charset="utf-8"> </head>
<?php
	include 'db-config.php';
    require_once ('codebird.php');
	include 'class.smtp.php';

    $cb = \Codebird\Codebird::getInstance();
    $cb->setConsumerKey('lzt2EUiD1erIjqigJi7ZwWVzQ', 'u5UN9HtBwp2LahvnGUsInCQc04vaq1s5BqWX6C6cUPur94QMMo');
    $cb->setToken('3914190079-ISMYVuhr4ftoNIt7DYelEuJWEjeSj83InO0MKNZ', 'SzofNRVJl6TGKG4epNeSx9d4IJ797ZwemWpoqIiUntz3c');
	
	
	
	//data retrieval shit starts from here...
	
	$params = array('q'=>'#TieHackReview');    
	$reply = (array) $cb->search_tweets($params);
	
	$data = (array) $reply['statuses'];
	
	
	$s = count($reply['statuses']);
	//print_r ($data);
	
	//returning username
	for ($a = 0; $a < $s; $a++) {
		
	    $status = $data[$a];
	    $tid= $status->user->screen_name ;
		echo $tid;
		$status_date=$status->created_at;
		$status_date= substr($status_date, 4);
		$mon=substr($status_date, 0,3);
		switch($mon){
			case 'Jan':
				$mon=1;
				break;
			case 'Feb':
				$mon=2;
				break;
			case 'Mar':
				$mon=3;
				break;
			case 'Apr':
				$mon=4;
				break;
			case 'May':
				$mon=5;
				break;
			case 'Jun':
				$mon=6;
				break;
			case 'Jul':
				$mon=7;
				break;
			case 'Aug':
				$mon=8;
				break;
			case 'Sep':
				$mon=9;
				break;
			case 'Oct':
				$mon=10;
				break;
			case 'Nov':
				$mon=11;
				break;
			case 'Dec':
				$mon=12;
				break;
			
			
		}
		$date=trim(substr($status_date,3,4));
		$time=trim(substr($status_date, 7,9));
		$time=strtr($time, array(':' => ''));
		
		$year=substr($status_date, -4);
		$tweet_date=$time.$year.$mon.$date;
		
		
		echo $tweet_date;
		
		//$current_date=date(d/m/y);
		$tweet= $status->text;
		echo $tweet;
		
		$sqlDate="SELECT created_at FROM tweets ORDER BY id DESC LIMIT 1;";
		$daters=mysqli_query($connection, $sqlDate);
		$resdate=mysqli_fetch_assoc($daters);
		$db_date= $resdate['created_at'];
	
		$db_time=substr($db_date,-8);
		$db_time=strtr($db_time,array(':'=>''));
		$db_dat=substr($db_date,0,10);
		$db_dat=strtr($db_dat,array('-'=>''));
		$db_date=$db_time.$db_dat;
		
		if($tweet_date>$db_date){
		
		
		$sqlemail="SELECT `email` FROM register WHERE `tid`='$tid'";
		$rsemail=mysqli_query($connection, $sqlemail)or die('sqlsdvksn');
		$resemail=mysqli_fetch_array($rsemail);
		
		$useremail=$resemail['email'];
		
		 $admin_email = "tiehackreview@gmail.com";
		  $text=chop($tweet,'#TieHackReview');
		  $subject = $text." Easy Review ";
		  
		  $sqlDes="SELECT `desc` FROM reviews where `item`='$text'";
		  $rsDes=mysqli_query($connection, $sqlDes);
		  $resDes=mysqli_fetch_assoc($rsDes);
		  
		  
		  $comment = $resDes['desc'];
  
  //send email
  mail($admin_email, "$subject", $comment, "From:" . $useremail);
		
		
		$sqltweet="INSERT INTO tweets(`tid`,`tweets`,`text`,`created_at`) VALUES ('$tid','$tweet','$text',now())";
		$sqlrs=mysqli_query($connection, $sqltweet);
	//returning tweets
	 
		}

	}
	
?>

</html>