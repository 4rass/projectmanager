
							//ניהול עובדים
	
	angular.module("myApp").controller('employee1Controller', function($scope, $http){
		 $http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
		 	.then(function(response) {
		        $scope.listEmployee = response.data;
		    });
		 
		 $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
		 	.then(function(response) {
		 		$scope.selectProjectName;
		        $scope.listproject = response.data;
		    });
		
		$scope.del = function(id){
			var result = confirm('Are you sure to delete employee?');
			if (result===true){	
				
				$http.get("rest/employee/deleteEmployee?id="+id)
			    .then(function(response) {
			    	   var delresult = response.data;
				        
				        if(delresult.id == "0"){
				        	$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
						 	.then(function(response) {
						        $scope.listEmployee = response.data;
						 	});
								alert("The employee deleted successfuly!");
				        }else{
				        	alert("Could not delete .");
				        }
				});
			};
		};
		
		 $(document).ready(function(){
	    	    $('.dissMiss').on('click', function () {
	    	    	$('#EmployeeForm').trigger("reset");
		    	 });
	    });
		
		
		$scope.edit = function(){
			$http.get("rest/employee/updateEmployee?id="+$scope.id+
					"&firstname="+$scope.firstname+
					"&lastname="+$scope.lastname+
					"&email="+$scope.email+
					"&phone="+$scope.phone+
					"&username="+$scope.username+
					"&pass="+$scope.pass)
		    .then(function(response) {
		    	   var editresult = response.data;
			        
			        if(editresult.id == "0"){
			        	$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
					 	.then(function(response) {
					        $scope.listEmployee = response.data;
					 	});
							alert($scope.firstname+" updated successfuly");
							
							 $('#employeesForm').modal('hide');
			        }else{
			        	alert("Could not update .");
			        }
		    });
		};
		
		$scope.selectEdit = function(id){
			$("#addE").hide();
			$("#updateE").show();
			$(".modal-titleE").text('Please Update Employee :');
			$(".userAndPass").hide();
			
			var index = getSelectedIndex(id);
			var employee = $scope.listEmployee[index];
			$scope.id = employee.id;
			$scope.firstname = employee.firstname;
			$scope.lastname = employee.lastname;
			$scope.email = employee.email;
			$scope.phone = employee.phone;
			$scope.username = employee.username;
			$scope.pass = employee.pass;
		};
		
		
		$scope.showEmployeeForm = function(){
			$("#updateE").hide();
			$("#addE").show();
			$(".modal-titleE").text('Add new Employee :');
			$(".userAndPass").show();
		}
		
		$scope.add = function(id){
			if($scope.firstname != undefined && $scope.lastname != undefined && 
					$scope.email != undefined && $scope.phone != undefined  &&
					$scope.username != undefined && $scope.pass != undefined){
			
			 $http.get("http://localhost/projectmanager/rest/employee/creatNewEmployee?firstname="+$scope.firstname+
					 "&lastname="+$scope.lastname+
					 "&email="+$scope.email+
					 "&phone="+$scope.phone+
					 "&username="+$scope.username+
					 "&pass="+$scope.pass)
			    .then(function(response) {
			       var result = response.data;
			        
				        if(result != null){
				        	
				        	$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
						 	.then(function(response) {
						        $scope.listEmployee = response.data;
						    });
				        	
				        		alert($scope.firstname+$scope.lastname+ '  added to the employees !');
				        		 $('#myModal').modal('hide');
				        		 $('#employeesForm').modal('hide');
				        }else{
				        	alert("Could not add .");
				        }
			        
			    });
			 
			}else{alert("Yoy must fill ALL inputs .");}
			
					$scope.firstname = '';
					$scope.lastname = '';
					$scope.email = '';
					$scope.phone = '';
					$scope.username = '';
					$scope.pass = '';
			
		};
		
		<!-- Function finds unique product data based on its id -->
		function getSelectedIndex(id){
			for(var i=0; i<$scope.listEmployee.length; i++)
				if($scope.listEmployee[i].id==id)
					return i;
		};
		
		
	$scope.associate = function (employeeId,projectId){
		
		if(employeeId != undefined && projectId != undefined){
			
			$http.get("http://localhost/projectmanager/rest/employeeProject/createEmployeeProject?employee="+employeeId+"&project="+projectId)
		 	.then(function(response) {
		       var myData = response.data;
		        
		        if(myData  != null){
		        	alert("Employee assignment to project completed!");
					 $("#associate").modal('hide');

		        }else{
		        	alert("Could not associate .");
		        }
		        
		 	});
		}else{alert("You must fill ALL inputs.");}
	}
		
	
	
	
	$scope.projectStatus1 = function (index){
		

		$scope.StartDate =  $scope.listEmployee[index].startdate;
		$scope.EndDate =  $scope.listEmployee[index].enddate;
		$scope.ProjectName = $scope.listEmployee[index].projectname;
		$scope.customerCompanyname =  $scope.listEmployee[index].companyname;
		
		
		console.log($scope.StartDate);
		console.log($scope.EndDate);
		console.log($scope.ProjectName);
		console.log($scope.customerCompanyname);
		
		
		 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjectsAssocoateToCustomer?id="+index)
		    .then(function(response) {
		        $scope.myData = response.data;
		    });
		 
		 	var id = $scope.listEmployee[index].id;
		 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjectsAndEmployees?id="+id)
		    .then(function(response) {
		        $scope.employeesOfProject = response.data;
		    });
		
	}
		
	});
	