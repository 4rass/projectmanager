angular.module("myApp").controller('managerloginCtrl', function($scope, $http){
	
	
			 $scope.content="3";
			 $http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=true")
			 	.then(function(response) {
			        $scope.listCustomer= response.data;
			    });
			
			 $http.get("http://localhost/projectmanager/rest/customer/getBigCustomer")
			 	.then(function(response){
			 		$scope.listBigCustomer = response.data;
			 	});
			 
			 					//Ending Projects
			 $http.get("http://localhost/projectmanager/rest/project/getEndingProjects")
			 .then(function (response){
				 $scope.listPrject = response.data;
			 });

			 
});
			