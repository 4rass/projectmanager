
									//ניהול לקוחות
angular.module("myApp").controller('customermanagmentCtrl', function($scope, $http) {
				$scope.content="1";
				
			    $http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=false")
			    .then(function(response) {
			    	$scope.selectCustomerName;
			        $scope.listcustomer = response.data;
			    });
			    
			    
			    $scope.customerStatus1 = function(index){
			    	
			    	var id = $scope.listcustomer[index].id;

					$scope.ProjectName =  $scope.listcustomer[index].projectname;
					$scope.Companyname =  $scope.listcustomer[index].companyname;
					$scope.Customerprojectmanager = $scope.listcustomer[index].customerprojectmanager;
					$scope.Projectmanageremail =  $scope.listcustomer[index].projectmanageremail;
					$scope.Projectmanagerphone = $scope.listcustomer[index].projectmanagerphone;
					$scope.startdate1 =  $scope.listcustomer[index].startdate;
					$scope.enddate1 = $scope.listcustomer[index].enddate;
					
					console.log($scope.ProjectName);
					console.log($scope.Companyname);
					console.log($scope.Customerprojectmanager);
					console.log($scope.Projectmanageremail);
					console.log($scope.Projectmanagerphone);
					console.log($scope.customerContactname);
					console.log($scope.startdate1);
					console.log($scope.enddate1);
					
			/* $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjectsAssocoateToCustomer?id="+index)
			    .then(function(response) {
			        $scope.myData = response.data;
			    });
			 */
			 	
			 $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfgetListOfProjectsAndEmployeesForCustomers?id="+id)
			    .then(function(response) {
			        $scope.employeesOfCustomer = response.data;
			    });
			    	
			    }
			    
			    
			    
			    $http.get("http://localhost/projectmanager/rest/customer/getBigCustomer")
			 	.then(function(response){
			 		$scope.listBigCustome1 = response.data;
			 	});
			 
			    
			    
			    
			    
			    
			    $(document).ready(function(){
			    	    $('.dissMiss').on('click', function () {
			    	    	$('#form').trigger("reset");
				    	 })
			    });
			    
			    <!-- Function finds unique product data based on its id -->
				function getSelectedIndex(id){
					for(var i=0; i<$scope.listcustomer.length; i++)
						if($scope.listcustomer[i].id == id){
							return i;
						}
							
				}
				
				$scope.del = function(id){
					var confirm1 = confirm('Are you sure?');
					if (confirm1===true){	
							$http.get("rest/customer/deleteCustomer?id="+id)
							    .then(function(response) {
							    	   var result = response.data;
								        if(result.id == "0"){
											var tr = document.getElementById("tr-"+id).style.display ="none";
												$(tr).remove();
								        }else{
								        	alert("Could not delete .");
								        }
				   			});
					}
				}
				
				$scope.edit = function(){
					$http.get("rest/customer/updateCustomer?id="+$scope.id+
							"&companyname="+$scope.companyname+
							"&companynumber="+$scope.companynumber+
							"&contactname="+$scope.contactname+
							"&email="+$scope.email+
							"&phone="+$scope.phone)
					    .then(function(response) {
					       var result = response.data;
					       
					       if( result.id == "0"){
								 $http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=false")
								    .then(function(response) {
								        $scope.listcustomer = response.data;
								        
								 });
								 
								 $http.get("http://localhost/projectmanager/rest/customer/getBigCustomer")
								 	.then(function(response){
								 		$scope.listBigCustome1 = response.data;
								 	});
								 
								 
								alert("Customer update completed!");
								 $scope.companyname='';
								 $scope.companynumber='';
								 $scope.contactname='';
								 $scope.email='';
								 $scope.phone='';
							}else{
								alert("Could not update .");
							}
				    });
				};
				
				$scope.selectEdit = function(id){
					$("#updateC").show();
					$("#addC").hide();
					$(".userPass").hide();
					$(".modal-title").text('Please Update Customer :') ;
					
					var index = getSelectedIndex(id);
					var product = $scope.listcustomer[index];
					$scope.id = product.id;
					$scope.companyname = product.companyname;
					$scope.companynumber = product.companynumber
					$scope.contactname = product.contactname;
					$scope.email = product.email;
					$scope.phone = product.phone;
				};
		
				$scope.showUserPass = function(){
					
					 
					$(".userPass").show();
					$("#updateC").hide();
					$("#addC").show();
					$(".modal-title").text('Please Add Customer :') ;
				}
				$scope.add = function(){
					 $http.get("/projectmanager/rest/customer/createCustomer?companyname="+$scope.companyname+
							 "&companynumber="+$scope.companynumber+
							 "&contactname="+$scope.contactname+
							 "&email="+$scope.email+
							 "&phone="+$scope.phone+
							 "&username="+$scope.username+
							 "&pass="+$scope.pass)
					    .then(function(response) {
					    	   var result = response.data;
						        console.log(result);
					        
						        <!-- Resets the form -->
								$scope.companyname = '';
								$scope.companynumber = '';
								$scope.contactname = '';
								$scope.email = '';
								$scope.phone = '';
								$scope.username='';
								$scope.pass = '';
								
								    if(result != null){
								    	
								    	$http.get("http://localhost/projectmanager/rest/customer/getCustomerActive?status=false")
										    .then(function(response) {
										    	$scope.selectCustomerName;
										        $scope.listcustomer = response.data;
										    });
								    	
								    	
								    	 $http.get("http://localhost/projectmanager/rest/customer/getBigCustomer")
										 	.then(function(response){
										 		$scope.listBigCustome1 = response.data;
										 	});
								    	 
								    	 alert("The custome added  successfuly!");
								    	 $('#myModal').modal('hide');
								    }else{
								    	alert("Could not add .");
								    }
					    });				
				}			
});