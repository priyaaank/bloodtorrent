bloodtorrent ?= {}
bloodtorrent.settings ?= {}

bloodtorrent.settings.start = (isItForFirstTime, callback)->
  bloodtorrent.settings.controller
    views:
      userSetupPage: calatrava.bridge.pages.pageNamed "userSetup"
    changePage: calatrava.bridge.changePage
    isItForFirstTime: isItForFirstTime
    settingsSaved: callback