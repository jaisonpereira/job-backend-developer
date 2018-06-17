angular.module('intelipostLogin').controller('LoginController',function($scope, $http) {
	$scope.error=undefined
	$scope.logado=false
	$scope.credenciais = {
			email:'',
			senha:''
	}
	
	$scope.login = ()=>{
		 $http.post('/login',$scope.credenciais).success((data, status, headers, config)=> {
			 $scope.error=undefined
			 $scope.logado=true
		 }).error((error)=> {
			 console.error(error);
			 $scope.error=error.message;		 
			 $scope.credenciais={};
			 $scope.logado=false
		 });
	}
		
	$scope.backToLogin=()=>{
		 $scope.logado=false
	}

});