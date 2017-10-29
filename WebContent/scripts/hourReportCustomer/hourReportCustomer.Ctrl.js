
							//דוח שעות ללקוח
			angular.module("myApp").controller('hourreportforcustomerCtrl', function($scope, $http) {
			    $http.get("http://localhost/projectmanager/rest/hourreport/getMonthBackOfCustomer?userId="+usernameId)
			    .then(function(response) {
			        $scope.myData = response.data;
			    });
			    
			    $http.get("http://localhost/projectmanager/rest/project/getProjectsByCustomer?userId="+usernameId)
			    .then(function(response) {
			        $scope.projectlist = response.data;
			    });
			    
			    $scope.report =  function(){
			    	
			    	if($scope.projectId == undefined){
						$scope.projectId = 0;
					}
			    	
			    	if($scope.yearAndMonth != undefined){
						
					
			    	$http.get("http://localhost/projectmanager/rest/hourreport/getHourReportOfCustomer?userId="+usernameId+
				    		"&yearAndMonth="+
				    		$("#date").val()+
				    		"&project="+$scope.projectId)
				    .then(function(response) {
				        $scope.myData = response.data;
				    });
			    	
			    	}
			    }
			    
			   
			});
							
						