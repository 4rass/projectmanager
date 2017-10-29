				//דוח שעות למנהל 
angular.module("myApp").controller('hourreportCtrl', function($scope, $http) {
			$http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=false")
			.then(function(response) {
				$scope.listCustomers = response.data;
			});
		  
					 $http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
					 	.then(function(response) {
					        $scope.listEmployees = response.data;
					    });
							 $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
							 	.then(function(response) {
							        $scope.listProjects = response.data;
							    });
							 
								 $http.get("http://localhost/projectmanager/rest/hourreport/getAllHourReport")
								 	.then(function(response) {
								        $scope.hourReportList = response.data;
								    });
								 
				$scope.report = function(){
					
					if($scope.employeeId == undefined){
						$scope.employeeId = 0;
					}
					if($scope.projectId == undefined){
						$scope.projectId = 0;
					}
					if($scope.customerId == undefined){
						$scope.customerId = 0;
					}
					if($scope.yearAndMonth != undefined){
					
							 $http.get("/projectmanager/rest/hourreport/getHourReportByUser?yearAndMonth="+
									 $("#date").val()+
									 "&employee="+$scope.employeeId+
									 "&project="+$scope.projectId+
									 "&customer="+$scope.customerId)
							 	.then(function(response) {
							       $scope.hourReportList = response.data;
							        
							    });
					}else{
						alert("You must insert something!!");
					}
				}
});