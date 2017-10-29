
						//ניהול פרוייקטים
		angular.module("myApp").controller("projectmanagmentCtrl", function($scope, $http,$filter){
		  $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
			 	.then(function(response) {
			 		$scope.selectProjectName;
			        $scope.listproject = response.data;
			    });
		  $http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
				.then(function(response) {
					$scope.selectEmployeeName;
					$scope.listEmployee = response.data;
				});
			 
		  $http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=false")
			.then(function(response) {
				$scope.selectEmployeeName;
				$scope.listCustomer = response.data;
			});
		  
			function getSelectedIndex(id){
				for(var i=0; i<$scope.listproject.length; i++)
					if($scope.listproject[i].id == id){
						return i;
					};
			}
			$scope.del = function(id){
				var result1 = confirm('Are you sure to delete project?');
					if (result1===true){	
						
							$http.get("rest/project/deleteProject?id="+id)
						    .then(function(response) {
						    	   var result = response.data;
							        
							        if(result.id == "0"){
										var tr = document.getElementById("tr-"+id).style.display ="none";
											$(tr).remove();
											alert("The project delete successfuly!");
							        }else{
							        	alert("Could not delete .");
							        }
							});
					};
			};
			$scope.edit = function(){
				
				$http.get("http://localhost/projectmanager/rest/project/updateProject?id="+$scope.id+
						"&projectname="+$scope.projectname+
						"&customer="+$scope.customer+
						"&customerprojectmanager="+$scope.customerprojectmanager+
						"&projectmanageremail="+$scope.projectmanageremail+
						"&projectmanagerphone="+$scope.projectmanagerphone+
						"&startdate="+$.datepicker.formatDate('yy-mm-dd',$scope.startdate)
						+"&enddate="+$.datepicker.formatDate('yy-mm-dd',$scope.enddate))
			   		 .then(function(response) {
					    	  var result = response.data;
					    	   
					    	   if(result.id == '0'){
									 $http.get("http://localhost/projectmanager/rest/project/getAllProjects")
									 	.then(function(response) {
									 		$scope.selectProjectName;
									        $scope.listproject = response.data;
									    });
									 $("#ProjectModal").modal('hide');
									 
									alert("Project successfully updated !");
								}else{
									alert("Could not update .");
								}
			    });
			};
			
			 $(document).ready(function(){
		    	    $('.dissMiss').on('click', function () {
		    	    	$('#formProjects').trigger("reset");
			    	 })
		    });
			
			$scope.selectEdit = function(id){
				
				$("#addP").hide();
				$("#updateP").show();
				$(".modal-titleP").text('Please Update Project :');
				
				var index = getSelectedIndex(id);
				var project = $scope.listproject[index];
				$scope.id = project.id;
				$scope.projectname = project.projectname;
				$scope.customer = project.customer.companyname;
				$scope.customerprojectmanager = project.customerprojectmanager;
				$scope.projectmanageremail = project.projectmanageremail;
				$scope.projectmanagerphone = project.projectmanagerphone;
				$scope.startdate = project.startdate;
				$scope.enddate = project.enddate;
			};
			
			$scope.showProjectForm = function(){
				$("#updateP").hide();
				$("#addP").show();
				$(".modal-titleP").text('Add new Project :');
			}
			
			$scope.add = function(id){
			
				if($scope.projectname != undefined &&
					$scope.customer != undefined &&
					$scope.customerprojectmanager != undefined &&
					$scope.projectmanageremail != undefined &&
					$scope.projectmanagerphone != undefined && 
					$scope.startdate != undefined &&
					$scope.enddate != undefined ){
			
				 $http.get("rest/project/createProject?projectname="+$scope.projectname+
						 "&customer="+$scope.customer+
						 "&customerprojectmanager="+$scope.customerprojectmanager+
						 "&projectmanageremail="+$scope.projectmanageremail+
						 "&projectmanagerphone="+$scope.projectmanagerphone+
						 "&startdate="+$.datepicker.formatDate('yy-mm-dd',$scope.startdate)+
						 "&enddate="+$.datepicker.formatDate('yy-mm-dd',$scope.enddate))
				    .then(function(response) {
				    	   var result = response.data;
				    	   
					        if(result != null){
								$scope.projectname = '';
								$scope.customer= '';
								$scope.customerprojectmanager = '';
								$scope.projectmanageremail = '';
								$scope.projectmanagerphone = '';
								$scope.project = '';
								$scope.startdate = '';
								$scope.enddate = '';
						 
								$http.get("http://localhost/projectmanager/rest/project/getAllProjects")
							 	.then(function(response) {
							 		$scope.selectProjectName;
							        $scope.listproject = response.data;
							 	 });
								
									 alert("Project was added successfuly!");
									 $("#ProjectModal").modal('hide');
							}else{
									alert("Could not add .");
								}		
						});
				}else{alert("YOU MUST FILL ALL INPUT !!");}
										
			}
						
			$scope.associate = function (employeeId,projectId){
				
				if( employeeId != undefined && projectId != undefined ){
				
					$http.get("http://localhost/projectmanager/rest/employeeProject/createEmployeeProject?employee="+employeeId+
							"&project="+projectId)
				 	.then(function(response) {
				       var myData = response.data;
				        
				        if(myData  != null){
				        	alert("Employee assignment to project completed!");
							 $("#Associate").modal('hide');

				        }else{
				        	alert("Could not associate .");
				        }
				 	});
				}else{alert("You MUST FILL ALL INPUT!!");}
			}
			
			
			$scope.projectStatus1 = function(index){
				console.log($scope.listproject[index].startdate);

				
				$scope.customerStartdate =  $scope.listproject[index].startdate;
				$scope.customerEnddate =  $scope.listproject[index].enddate;
				$scope.customerProject = $scope.listproject[index].projectname;
				$scope.customerCompanyname =  $scope.listproject[index].customer.companyname;
				$scope.customerCompanynumber = $scope.listproject[index].customer.companynumber;
				$scope.customerContactname =  $scope.listproject[index].customer.contactname;
				$scope.customerEmail = $scope.listproject[index].customer.email;
				$scope.customerPhone =  $scope.listproject[index].customer.phone;
				
				console.log($scope.customerStartdate);
				console.log($scope.customerEnddate);
				console.log($scope.customerProject);
				console.log($scope.customerCompanyname);
				console.log($scope.customerCompanynumber);
				console.log($scope.customerContactname);
				console.log($scope.customerEmail);
				console.log($scope.customerPhone);
				
		 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjectsAssocoateToCustomer?id="+index)
		    .then(function(response) {
		        $scope.myData = response.data;
		    });
		 
		 	var id = $scope.listproject[index].id;
		 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjectsAndEmployees?id="+id)
		    .then(function(response) {
		        $scope.employeesOfProject = response.data;
		    });
		 
		/*$scope.images1 = [   //can put images on an employee .
			 "/images.jpg",
			 "/img/kendo-bg2-big.jpg",
			 "/img/logo.png",
			 "/img/Logo.png",
			 "/img/Project.jpg"];*/
		 
		/*	$scope.employeeFirstName =  $scope.employeesOfProject[index].employee.firstname;
			$scope.employeeLastName =  $scope.employeesOfProject[index].employee.lastname;
			$scope.employeeMail =  $scope.employeesOfProject[index].employee.email;
			$scope.employeePhone =  $scope.employeesOfProject[index].employee.phone;
		 console.log($scope.employeeFirstName);
		 console.log($scope.employeeLastName);
		 console.log($scope.employeeMail);
		 console.log($scope.employeePhone);
		 */
		
			}
			
			
			
			
		 });