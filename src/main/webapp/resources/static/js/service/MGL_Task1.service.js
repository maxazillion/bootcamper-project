'use strict';

angular.module('MGL_Task1_app').factory('MGL_Task1_Service', ['$http', function($http) {

		var REST_SERVICE_URI = 'http://localhost:8083/';

		var factory = {
			fetchAllGames : fetchAllGames,
			createGame : createGame,
			removeGame : removeGame
		};

		return factory;


		function fetchAllGames(filterTerm) {
		
			var id = parseFloat(filterTerm)
			
			if(filterTerm === undefined || filterTerm.length < 1){
				return $http.get(REST_SERVICE_URI + 'getAll').then(function(response) {
							console.log("hit1")
							return response.data;
						}
					);
			}
			
			if(isNaN(parseFloat(filterTerm))){
				return $http.put(REST_SERVICE_URI + 'getFiltered', filterTerm).then(function(response) {
						console.log("hit2")
						return response.data;
					}
				);
			}
		
			return $http.put(REST_SERVICE_URI + 'getFilteredById', id).then(function(response) {
						console.log("hit2")
						return response.data;
					}
			);	
		}		

		function createGame(game) {
			return $http.post(REST_SERVICE_URI + 'createGame', game).then(function(response) {
					return response.data;
				}
			);
			
		}
		
		function removeGame(id){
			return $http.post(REST_SERVICE_URI + 'removeGame', id).then(function(response) {
					return response.data;
				}
			);
		}

}]);
