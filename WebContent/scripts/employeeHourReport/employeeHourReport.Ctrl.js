
angular.module("myApp").controller("employeehourreportformCtrl", function($scope, $http){
			  $http.get("http://localhost/projectmanager/rest/hourreport/getSevenDaysBackOfEmployee?id="+usernameId)
				 	.then(function(response) {
				        $scope.listemployee = response.data;
				    });
			  $http.get("http://localhost/projectmanager/rest/employeeProject/getListOfProjects?id="+usernameId)
			 	.then(function(response) {
			        $scope.projectlist = response.data;
			    });
			  
			  
			  $http.get("http://localhost/projectmanager/rest/systemSettings/getSystemDays")
				.then(function(response) {
			    var days = response.data;
			    var daysArr = days.split(",");
			    
			    
			    
			 $('#date').datepicker({
			        beforeShowDay: function(date) {
			            var day = date.getDay();
			            var result = daysArr[day].includes("true"); 
			            return [result, '', ''];
			        }
			    });
			});
			 
			  $http.get("http://localhost/projectmanager/rest/systemSettings/getSystemHours")
			 	.then(function(response) {
			        $scope.hours = response.data;
			        var twoHours = $scope.hours.split(",");
			        
			        var hourbegin = twoHours[0];
			        var hourbegin1 = hourbegin.split("-");
			        var hour1 = parseInt(hourbegin1[0]);
			        
			        
			        var hourend = twoHours[1];
			        var hourend1 = hourend.split("-");
			        var hour2 = parseInt(hourend1[0]);
			        
			        var time ;
			        for (var i = hour1 ; i <= hour2 ; i++) {
			        	
			        	if (i < 10 ){
							i = "0"+i;
						}
			        	
			        	time=i+"-"+hourend1[1];
			        	
						$("#starttime").append("<option value="+time+">"+time+"</option>");
						$("#endtime").append("<option value="+time+">"+time+"</option>");
					}
			        
			    });
			  
			  $scope.add = function (){
				  var date = $("#date").datepicker({ dateFormat: 'yy-mm-dd' }).val();
				  var newdate=date.split("/");
				  date=newdate[2]+"-"+newdate[0]+"-"+newdate[1];
				  
				  
		  if(date != undefined
				  && $scope.project != undefined 
				  && $("#starttime").val() !=  undefined
				  && $("#endtime").val() != undefined 
				  && $scope.description != undefined ){
			  
				  $http.get("http://localhost/projectmanager/rest/hourreport/createHourreport?employee="+usernameId+
						  "&project="+$scope.project+
						  "&date="+date+
						  "&starttime="+$("#starttime").val()+
						  "&endtime="+$("#endtime").val()+
						  "&description="+$scope.description)
				 	.then(function(response) {
				      var result = response.data;
				        	if(result != null){
			        		$http.get("http://localhost/projectmanager/rest/hourreport/getSevenDaysBackOfEmployee?id="+usernameId)
						 	.then(function(response) {
						        $scope.listemployee = response.data;
						    });
				        		 alert("Hour report Has been reported seccsecfuly!");
				        		 
				        		 $("#myModal").modal('hide');
				        	}else{
				        		 alert("Hour report has NOT reported, try again  .");
				        	}
				        	$scope.employee = '';
				        	$scope.project = '';
				        	$scope.startdate = '';
				        	$scope.enddate = '';
				        	$scope.description = '';
				    });
		  }else{ alert("YOU MUST FILL ALL INPUTS !");}
			  }
});	
		
		  