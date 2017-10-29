var app = angular.module("myApp", ["ngRoute"]);

app.controller("myAppCtrl",function($scope,$location,$http, $interval){
	
	 var time = function() {
		    $scope.clock = Date.now();
		  }
	 	time();
		  $interval(time, 1000);
		
$scope.logo = function(){
	
	$location.path('/bigcustomer');
	
}
	
	
$scope.errorMail = function(){
		
	if($scope.subject =! undefined ){
	
		$http.get("http://localhost/projectmanager/rest/MailHelper/sendMail?subject="+$scope.subject+"&massage="+$scope.massage)
		.then(function(response) {
	        var result = response.data;
	        
	        if(result == "OK"){
	        	alert("Thank you for your report, it will help for us to make a better website .");
	        	
	        	$scope.subject='';
	        	$scope.massage='';
	        }else{
	        	alert("Sorry, the massage NOT sent,try again.");
	        }
		});
		
	}else{ alert(" You must insert subject !")}
}

	$scope.logout = function (){
		
		var logout = confirm('Are you sure ?');
		
			if(logout == true){
				$(".menu").hide();
				$(".logbutton").hide();
	    		$(".errorbutton").hide();
        		$(".EmployeePage").hide();
        		$(".CustomerPage").hide();
        		$(".logo").hide();
				$location.path('/');			
			}
		
	};
});
