
						//  פרוייקטים פעילים ללקוח
		angular.module("myApp").controller('activprojectscustomerCtrl', function($scope, $http){
			 $http.get("http://localhost/projectmanager/rest/project/getActiveProjectByCustomer?customer="+usernameId)
			 	.then(function(response) {
			        $scope.Proj= response.data;
			 });
		});
