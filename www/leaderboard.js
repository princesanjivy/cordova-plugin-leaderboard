// princesanjivy

module.exports = {
  init: function(success, error){
    cordova.exec(success, error, "Leaderboard", "init", []);
  },

  setScore: function(leaderboard_id, score, success, error){
    cordova.exec(success, error, "Leaderboard", "setScore", [leaderboard_id, score]);
  },

  showLeaderboard: function(leaderboard_id, success, error){
    cordova.exec(success, error, "Leaderboard", "showLeaderboard", [leaderboard_id]);
  }
};
