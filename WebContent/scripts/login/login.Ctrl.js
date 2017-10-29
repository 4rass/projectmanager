var usernameId ;
angular.module("myApp").controller("mainCtrl",function($scope,$http,$location,$rootScope){
	
	$scope.forgotPass = function(){
		
		if($scope.username != undefined ){
			
		
			$http.get("http://localhost/projectmanager/rest/systemSettings/getForgottedPassword?username="+$scope.username).then(function(response) {
		        var result = response.data;
		        console.log(result);
		        if(result.id == "0"){
		        	alert('Massage sent seccsecfuly!!!');
		        	 $('#myModal').modal('hide');
		        }else{
		        	alert('Massgae not send try again , ');
		        }
			});
			
		}else{alert("you must insert user name .");}
	}
	
	
	$scope.login= function(){
		
		
		$rootScope.currentUserId = '';
		
		
		if($scope.user != undefined && $scope.pass != undefined){
		
		$http.get("http://localhost/projectmanager/rest/usersService/getByUser?user="+$scope.user+"&pass="+$scope.pass).then(function(response) {
	        var result = response.data;
	        console.log(result);
	        
	       if(result != null){
	   	    	if(result.type == "employee"){
	   	    		usernameId = result.id;
	   	    		
	   	    		$rootScope.currentUserId = result.username;
	   	    		
	        		$(".menu").show();
	        		$(".EmployeePage").show();
	        		$(".logbutton").show();
	        		$(".errorbutton").show();
	        		$(".CustomerPage").hide();
	        		$(".Manager").hide();
	        		$(".logo").show();
	        		$location.path("/employeehourreportform");
	        	};
	        	if(result.type == "customer"){
	        		usernameId = result.id;
	        		
	        		$rootScope.currentUserId = result.username;
	        		
	     	  	 		console.log("Customer Got in ");
	        		$(".menu").show();	
	        		$(".CustomerPage").show();
	        		$(".logbutton").show();
	        		$(".errorbutton").show();
	        		$(".Manager").hide();
	        		$(".EmployeePage").hide();
	        		$(".logo").show();
	        		$location.path("/activproject");
	        	};
	        	if(result.type == "manager"){
	        		
	        		$rootScope.currentUserId = result.username;
	        		
	        			console.log("<---Manager got in");
	        			$(".menu").show();
	        			$(".logbutton").show();
		        		$(".errorbutton").show();
		        		$(".EmployeePage").hide();
		        		$(".CustomerPage").hide();
		        		$(".Manager").show();
		        		$(".logo").show();
	        		$location.path("/bigcustomer");
	        	};
	        	
	        	
	        }else{
	        	alert("Please try again, User name or Password Incorrect.  ");
	        }
	      });
		}else{alert("You must insert user name & password in the fields.");} 	
	};
	

});