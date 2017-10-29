		angular.module("myApp").controller("employeehourreporttableCtrl", function($scope, $http){
			
			 $http.get("http://localhost/projectmanager/rest/hourreport/getMonthBackOfEmployee?userId="+usernameId)
				 	.then(function(response) {
				        $scope.hourReport = response.data;
				    });
				 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjects?id="+usernameId)
				 	.then(function(response) {
				        $scope.projectlist = response.data;
				    });
			 
			 	$scope.report = function(){
			 		if($scope.projectId == undefined){
						$scope.projectId = 0;
					}
			 		if($scope.yearAndMonth =! undefined){
			 		$http.get("/projectmanager/rest/hourreport/getHourReportOfEmployee?userId="+usernameId+
			 				"&yearAndMonth="+$("#date").val()+"&project="+$scope.projectId)
				 	.then(function(response) {
				        $scope.hourReport = response.data;
				    });
			 		} 
			 	}
		});