'use strict';

angular.module('MGL_Task1_app').controller('MGL_Task1_Controller',
		[ 'MGL_Task1_Service', function(MGL_Task1_Service) {
			var self = this;
			var filterTerm = "";
			self.game = {
				id : '',
				name : '',
				genre : ''
			};
			self.games = [];

			self.fetchAllGames = function(){
				MGL_Task1_Service.fetchAllGames(filterTerm).then(function(data) {
					self.games = data;
				});
				
			}

			self.addGame = function(){
				return MGL_Task1_Service.createGame(self.game).then( function() {
				self.fetchAllGames();
				});
			}

			
			self.removeGame = function(id){
				return MGL_Task1_Service.removeGame(id).then(function() {
				filterTerm = self.fetchAllGames();
				});
			}
			
			self.changeFilterTerm = function(){
				filterTerm = self.filterTerm
				console.log(filterTerm)
				self.fetchAllGames(filterTerm)
			}

			self.fetchAllGames();
		} ]);
